const mongoose = require('mongoose');

const { Schema } = mongoose;
const { ObjectId } = Schema;
const mongoosePaginate = require('mongoose-paginate-v2');
const moment = require('moment-timezone');

const scheduledNotificationSchema = new mongoose.Schema({
  to: { type: String },
  user_type: { type: String, index: true },
  message_type: { type: String, index: true, default: 'push' },
  schedule: { type: String },
  time: {
    type: String, index: true,
  },
  days: {
    type: [Number],
  },
  notification: { type: Object },
  status: { type: Boolean, default: true },
  send_total: {
    success_count: Number,
    default: 0,
    failed_count: Number,
    default: 0,
  },
  is_deleted: { type: Boolean, default: false },
}, {
  timestamps: true,
});

scheduledNotificationSchema.statics = {

  async updateStatus(id, obj) {
    return await this.updateOne({ _id: id }, { status: obj == 'Active' });
  },
  transformData(data) {
    const selectableItems = [];
    let i = 1;
    data.forEach((item) => {
      selectableItems.push({
        id: i++,
        ids: item.id,
        to: item.to,
        user_type: item.user_type,
        time: item.time,
        days: this.transformDays(item.days),
        notification: item.notification,
        send_total: item.send_total,
        schedule: item.schedule,
		    status: item.status == true ? 'Active' : 'Inactive',
        send_total: (item.send_total) ? `success count : ${item.send_total.success_count}<br/>failed count : ${item.send_total.failed_count}` : 'success count : 0<br/>failed count : 0',
        createdAt: moment(item.createdAt).tz('Asia/Kolkata').format('DD MMM YYYY'),
      });
    });
    return selectableItems;
  },
  transformDays(data) {
    const days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    const result = days.filter((str, idx) => data.includes(idx));
    return result.toString();
  },
};

scheduledNotificationSchema.plugin(mongoosePaginate);


module.exports = mongoose.model('scheduled_Notification', scheduledNotificationSchema);
