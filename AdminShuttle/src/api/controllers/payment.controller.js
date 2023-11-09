const httpStatus = require('http-status');
const { omit, isEmpty } = require('lodash');
const Booking = require('../models/booking.model');
const User = require('../models/user.model');
const Payment = require('../models/payment.model');
const Setting = require('../models/setting.model');
const { settingRazorPay } = require('../utils/setting');
const APIError = require('../utils/APIError');
const { user } = require('../notifications');
const { paymentChart } = require('../services/dashboardService');


exports.checkStatus = async (req, res, next) => {
  try {
    const { orderId } = req.params;
    const { paymentId } = req.body;
    const razorPay = await settingRazorPay();
    const fetchPayments = await razorPay.razor.orders.fetchPayments(orderId);
    if (fetchPayments && fetchPayments.count === 0) {
      throw new APIError({
        status: 200,
        message: 'payment not found.',
      });
    } else {
      const payment = fetchPayments.items.find((item, index) =>
        (item.status === 'captured' && item.captured == true ? item : {}));
      if (payment) {
        const updatePayment = await Payment.findByIdAndUpdate(paymentId, {
          payment_status: 'Completed',
          payment_created: payment.created_at,
          paymentId: payment.id,
        });

        if (updatePayment.bookingId != null && updatePayment.bookingId.length > 0) {
          const getbooking = await Booking.findOneAndUpdate(
            { _id: { $in: updatePayment.bookingId } },
            { travel_status: 'SCHEDULED' },
          )
            .populate({ path: 'userId', select: 'device_token' })
            .exec();

          if (getbooking) {
            await user.UserNotification(
              'Booking payment',
              `Booking pnr no: ${getbooking.pnr_no} payment Successfully`,
              '',
              getbooking.userId.device_token,
            );
          }
        } else {
          const getUser = await User.findById(updatePayment.userId)
            .select('device_token')
            .lean();
          if (getUser) {
            await user.UserNotification(
              'wallet recharge',
              'Wallet recharge payment Successfully',
              '',
              getUser.device_token,
            );
          }
        }
        res.json({
          message: 'payment status checked and updated.',
          status: true,
        });
      }
    }
  } catch (err) {
    return next({
      status: err.status ? err.status : err.statusCode,
      message: err.error ? err.error.description : err.message,
    });
  }
};


/**
 * count payment
 * @returns
 */
exports.count = async (req, res, next) => {
  try {
    const payment_status = req.params.status;
    const TODAY = req.params.start_date; // moment().tz("Asia/Kolkata").format("YYYY-MM-DD");
    const YEAR_BEFORE = req.params.end_date; // moment().subtract(1, 'years').format("YYYY-MM-DD");

    let condition = {};
    if (payment_status === 'Refunded') {
      condition = {
        payment_status,
        createdAt: { $gte: new Date(YEAR_BEFORE), $lte: new Date(TODAY) },
      };
    } if (payment_status === 'Completed' && req.params.is_wallet === '1') {
      condition = {
        payment_status,
        title: 'Wallet recharge',
        createdAt: { $gte: new Date(YEAR_BEFORE), $lte: new Date(TODAY) },
        // $and: [{ bookingId: { $exists: false } }, { bookingId: null }],
      };
    } else if (payment_status === 'Completed') {
      condition = {
        payment_status,
        createdAt: { $gte: new Date(YEAR_BEFORE), $lte: new Date(TODAY) },
        bookingId: { $exists: true, $ne: [] },
        title: 'Ride paid',
      };
    }

    const result = await paymentChart(TODAY, YEAR_BEFORE, condition);
    res.status(httpStatus.OK);
    res.json({
      message: 'payment count fetched successfully.',
      years_data: result[0].years_data,
      data: result[0].data,
      status: true,
    });
  } catch (err) {
    console.log(err);
    next(err);
  }
};


/**
 * Get booking
 * @public
 */
exports.fetch = async (req, res) => {
  try {
    res.status(httpStatus.OK);
    res.json({
      message: 'payment fetched successfully.',
      data: Payment.transformSingleData(req.param.paymentId),
      status: true,
    });
  } catch (error) {
    console.log(error);
    return error;
  }
};

/**
 * Get booking
 * @public
 */
exports.get = async (req, res) => {
  try {
    res.status(httpStatus.OK);
    res.json({
      message: 'payment fetched successfully.',
      data: Payment.transformSingleData(booking),
      status: true,
    });
  } catch (error) {
    console.log(error);
    return error;
  }
};


/**
 * Get booking layout list
 * @public
 */
exports.list = async (req, res, next) => {
  try {
    console.log('req.query', req.query.filters);
    const payment_status = Payment.capitalizeFirstLetter(req.query.filters); // refunded | Completed
    const condition = req.query.global_search
      ? {
        $or: [
          {
            name: {
              $regex: new RegExp(req.query.global_search),
              $options: 'i',
            },
          },
        ],
        payment_status,
        is_deleted: false,
      }
      : {
        payment_status,
        is_deleted: false,
      };
    let sort = {};
    if (!req.query.sort) {
      sort = { _id: -1 };
    } else {
      const data = JSON.parse(req.query.sort);
      sort = { [data.name]: data.order != 'none' ? data.order : 'asc' };
    }


    //    console.log('1212', sort);
    const paginationoptions = {
      page: req.query.page || 1,
      limit: req.query.per_page || 10,
      collation: { locale: 'en' },
      customLabels: {
        totalDocs: 'totalRecords',
        docs: 'payments',
      },
      sort,
      populate: [
        { path: 'bookingId', select: '_id pnr_no discount' },
        { path: 'userId', select: '_id firstname lastname  phone email gender ' },
        { path: 'payments', select: '_id orderId payment_status payment_created amount ferriOrderId paymentId' },

      ],
      lean: true,
    };
    // ,{path:"userId",select:"_id firstname lastname phone"}
    const result = await Payment.paginate(condition, paginationoptions);
    // console.log("result.bookings", result.bookings);
    const refundssettings = await Setting.getrefunds();
    result.payments = Payment.transformDataLists(result.payments, refundssettings);
    res.json({ data: result });
  } catch (error) {
    next(error);
  }
};


exports.updateStatus = async (req, res, next) => {
  try {
    const { paymentId } = req.params;
    const { payment_status, payment_id, payment_created } = req.body;
    const updatePayment = await Payment.findByIdAndUpdate(paymentId, {
      payment_status,
      payment_created,
      paymentId: payment_id,
    });
    if (updatePayment) {
      await Booking.findOneAndUpdate(
        { _id: { $in: updatePayment.bookingId } },
        { travel_status: 'SCHEDULED' },
      );
    }
    res.json({
      message: 'payment updated successfully.',
      status: true,
    });
  } catch (err) {
    return next(err);
  }
};
