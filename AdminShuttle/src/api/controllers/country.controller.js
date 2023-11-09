const httpStatus = require('http-status');
const { omit, isEmpty } = require('lodash');
const Country = require('../models/country.model');
const { VARIANT_ALSO_NEGOTIATES } = require('http-status');
const APIError = require('../utils/APIError');

/**
 * Get country
 * @public
 */
exports.get = async (req, res, next) => {
  try {
    const country = await Country.findById(req.params.countryId);
    res.status(httpStatus.OK);
    res.json({
      message: 'country get successfully.',
      data: country.transform(),
      status: true,
    });
  } catch (error) {
    console.log(error);
    throw new APIError(error);
  }
};

/**
 * Create new bus
 * @public
 */
exports.create = async (req, res, next) => {
  try {
    const {
      name, code, status,
    } = req.body;

    if (status) { // check is status is true
      const getCountryStatus = await Country.find({ status });
      const CountryIds = getCountryStatus.filter(v => v._id);
      await Country.updateMany({ _id: { $in: CountryIds } }, { status: false });
    }

    const country = await new Country({
      name,
      code,
      status,
    }).save();
    return res.json({
      status: true,
      message: 'country created successfully',
      data: country,
    });
  } catch (error) {
    console.log(error);
    throw new APIError(error);
  }
};

/**
 * Update existing country
 * @public
 */

exports.update = async (req, res, next) => {
  try {
    const {
      name, code, symbol, status,
    } = req.body;
    const countryexists = await Country.findById(req.params.countryId).exec();
    if (countryexists) {
      if (status) { // check is status is true
        const getCountryStatus = await Country.find({ status });
        const CountryIds = getCountryStatus.filter(v => v._id);
        await Country.updateMany({ _id: { $in: CountryIds } }, { status: false });
      }

      const objUpdate = {
        name,
        code,
        status,
      };

      const updateCountry = await Country.findByIdAndUpdate(
        req.params.countryId,
        {
          $set: objUpdate,
        },
        {
          new: true,
        },
      );
      if (updateCountry) {
        res.status(httpStatus.CREATED);
        return res.json({
          status: true,
          message: 'Country updated successfully',
          data: updateCountry,
        });
      }
    } else {
      res.status(httpStatus.OK);
      res.json({
        status: true,
        message: 'No country found.',
      });
    }
  } catch (error) {
    console.log(error);
    throw new APIError(error);
  }
};

/**
 * Get country list
 * @public
 */
exports.list = async (req, res, next) => {
  try {
    const condition = req.query.global_search
      ? {
        $or: [
          {
            name: {
              $regex: new RegExp(req.query.global_search),
              $options: 'i',
            },
          },
          {
            code: {
              $regex: new RegExp(req.query.global_search),
              $options: 'i',
            },
          },
          { status: req.query.global_search != 'inactive' },
        ],
        is_deleted: false,
      }
      : {
        is_deleted: false,
      };

    let sort = {};
    if (!req.query.sort) {
      sort = { _id: -1 };
    } else {
      const data = JSON.parse(req.query.sort);
      sort = {
        [data.name]: data.order != 'none' ? data.order : 'asc',
      };
    }

    const paginationoptions = {
      page: req.query.page || 1,
      limit: req.query.per_page || 10,
      collation: { locale: 'en' },
      customLabels: {
        totalDocs: 'totalRecords',
        docs: 'currencies',
      },
      sort,
    };

    const result = await Country.paginate(condition, paginationoptions);
    result.currencies = Country.transformData(result.currencies);

    res.json({ data: result });
  } catch (error) {
    console.log('343 ', error);
    next(error);
  }
};

/**
 * Delete bus
 * @public
 */
exports.remove = (req, res, next) => {
  Country.updateOne(
    {
      _id: req.params.countryId,
    },
    {
      $set: { is_deleted: true },
    },
  )
    .then(() => {
      res.status(httpStatus.OK).json({
        status: true,
        message: 'Country deleted successfully.',
      });
    })
    .catch(e => next(e));
};
