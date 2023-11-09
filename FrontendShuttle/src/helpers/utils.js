import moment from "moment-timezone";

const getCurrentDate = () => {
  return moment().tz("Asia/Kolkata");
};

export default {
  getCurrentDate,
};
