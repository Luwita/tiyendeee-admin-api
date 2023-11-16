const httpStatus = require('http-status');
const { omit, isEmpty } = require('lodash');
const Booking = require('../models/booking.model');
const Payment = require('../models/payment.model');
const Currency = require('../models/currency.model');
const mongoose = require('mongoose');
const moment = require('moment-timezone');
const { VARIANT_ALSO_NEGOTIATES } = require('http-status');
const { bookingChart } = require('../services/dashboardService');
const { bookingWithRefund, bookingWithoutRefund } = require('../services/bookingService');
/**
 * count payment
 * @returns
 */
exports.count = async (req, res, next) => {
  try {
    const payment_status = req.params.status.toUpperCase();
    const TODAY = req.params.start_date; // moment().tz("Asia/Kolkata").format("YYYY-MM-DD");
    const YEAR_BEFORE = req.params.end_date; // moment().subtract(1, 'years').format("YYYY-MM-DD");

    let condition = {};
    condition = {
      travel_status: payment_status,
      booking_date: { $gte: new Date(YEAR_BEFORE), $lte: new Date(TODAY) },
    };

    const result = await bookingChart(TODAY, YEAR_BEFORE, condition);
	console.log("result",result);
    res.status(httpStatus.OK);
    res.json({
      message: 'booking count fetched successfully.',
      years_data: result.length > 0 ? result[0].years_data : [],
      data: result.length ? result[0].data : [],
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
exports.get = async (req, res) => {
  try {
    const booking = await Booking.findById(req.params.bookingId)
      .populate({ path: 'pickupId', select: '_id title' })
      .populate({ path: 'dropoffId', select: '_id title' })
      .populate({ path: 'routeId', select: '_id title' })
      .populate({ path: 'busId', select: '_id name reg_no model_no' })
      .populate({
        path: 'userId',
        select: '_id firstname lastname phone email gender ',
      });
    // .populate({
    //   path: 'payments',
    //   select: '_id orderId payment_status is_pass payment_created createdAt method amount ferriOrderId paymentId passId',
    //   populate: { path: 'passId', select: '_id no_of_rides' },
    // });
    const formatedData = await Booking.transformSingleData(booking);
    const paymentDetail = await Payment.findOne({
      bookingId: { $in: [mongoose.Types.ObjectId(formatedData.id)] },
    }).populate({ path: 'passId', select: 'no_of_rides' });
    formatedData.payment_detail = await Payment.transformSingleData(paymentDetail);
    formatedData.default_currency = await Currency.defaultPaymentCurrency();
    formatedData.payment_detail.default_currency =
      formatedData.default_currency;
    // console.log('formatedData', formatedData);
    res.status(httpStatus.OK);
    res.json({
      message: 'booking fetched successfully.',
      data: formatedData,
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
    let condition = req.query.global_search
      ? {
        $or: [
          {
            pnr_no: {
              $regex: new RegExp(req.query.global_search),
              $options: 'i',
            },
          },
        ],
        travel_status: req.query.travel_status,
        is_deleted: false,
      }
      : {
        travel_status: req.query.travel_status,
        is_deleted: false,
      };
    // "payments.payment_status":'Completed'
    let sort = {};
    if (!req.query.sort) {
      sort = { _id: -1 };
    } else {
      const data = JSON.parse(req.query.sort);
      sort = { [data.name]: data.order != 'none' ? data.order : 'asc' };
    }

    if (req.query.filters) {
      const filtersData = JSON.parse(req.query.filters);

      if (filtersData.type === 'select') {
        console.log('name', filtersData.name, filtersData.selected_options[0]);
        condition = {
          travel_status: req.query.travel_status,
          is_deleted: false,
        };
      } else if (filtersData.type === 'date') {
        const today = moment(filtersData.value.startDate);
        condition = {
          booking_date: {
            $gte: today.toDate(),
            $lte: today.endOf('day').toDate(),
          },
          travel_status: req.query.travel_status,
          is_deleted: false,
        };
      }
    }

    //    console.log('1212', sort);
    const paginationoptions = {
      page: req.query.page || 1,
      limit: req.query.per_page || 10,
      collation: { locale: 'en' },
      customLabels: {
        totalDocs: 'totalRecords',
        docs: 'bookings',
      },
      sort,
      populate: [
        { path: 'pickupId', select: '_id title' },
        { path: 'dropoffId', select: '_id title' },
        { path: 'routeId', select: '_id title' },
        { path: 'busId', select: '_id name reg_no model_no' },
        {
          path: 'userId',
          select: '_id firstname lastname  phone email gender ',
        },
        // {
        //   path: 'payments',
        //   select: '_id orderId payment_status is_pass payment_created createdAt method amount ferriOrderId paymentId passId',
        //   populate: { path: 'passId', select: '_id no_of_rides' },
        // },
      ],
      lean: true,
    };
    // ,{path:"userId",select:"_id firstname lastname phone"}
    const result = await Booking.paginate(condition, paginationoptions);

    result.bookings = await Booking.transformData(result.bookings);

    // console.log('result.bookings', result.bookings);
    res.json({ data: result });
  } catch (error) {
    console.log(error);
    next(error);
  }
};

/**
 * Get booking Cancel list
 * @public
 */
exports.cancel = async (req, res, next) => {
  const { is_refund } = req.body;
  const { pnr_no } = req.params;
  const session = await mongoose.startSession();
  try {
    // Start session
    await session.startTransaction();

    if (is_refund) { // /is refund
      const result = await bookingWithRefund(pnr_no);
      if (!result) {
        res.status(httpStatus.NotFound);
        res.json({
          message: 'No booking pnr no found.',
          status: false,
        });
      }
    } else {
      const result = await bookingWithoutRefund(pnr_no);
      if (!result) {
        res.status(httpStatus.NotFound);
        res.json({
          message: 'No booking pnr no found.',
          status: false,
        });
      }
    }
    // finish transcation
    await session.commitTransaction();
    session.endSession();

    res.status(httpStatus.OK);
    res.json({
      message: 'booking cancelled successfully.',
      status: true,
    });
  } catch (error) {
    console.log(err);
    await session.abortTransaction();
    session.endSession();
    console.log(error);
    next(error);
  }
};
