const mongoose = require('mongoose');
const mongoosePaginate = require('mongoose-paginate-v2');
const moment = require('moment-timezone');
/**
 * City Schema
 * @private
 */

const countrySchema = new mongoose.Schema({
  name: {
    type: String, index: true, trim: true, default: '',
  },
  code: {
    type: String, index: true, trim: true, default: '',
  },
  status: { type: Boolean, index: true },
  is_deleted: { type: Boolean, default: false },
}, {
  timestamps: true,
});


countrySchema.statics = {
  transform() {
    const transformed = {};
    const fields = [
      'id',
      'name',
      'code',
      'status',
      'createdAt',
    ];

    fields.forEach((field) => {
      transformed[field] = this[field];
    });

    return transformed;
  },
  transformData(rows) {
    const selectableItems = [];
    let i = 1;
    rows.forEach((item) => {
      selectableItems.push({
        id: i++,
        ids: item._id,
        name: item.name,
        code: item.code,
        status: item.status,
        createdAt: moment
          .utc(item.createdAt)
          .tz('Asia/Kolkata')
          .format('DD MMM YYYY'),
      });
    });
    return selectableItems;
  },
};

countrySchema.plugin(mongoosePaginate);

/**
 * @typedef Country
 */
module.exports = mongoose.model('Country', countrySchema);
