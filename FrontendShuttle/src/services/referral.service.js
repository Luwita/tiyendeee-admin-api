import useAuthApi from "../helpers/authHook";
//import moment from "moment-timezone";

export const referralService = {
  getAll,
  transform,
  getSingle,
};

async function getAll(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("referrals/search", {
      params: Objparams,
    });
    return response.data;
  } catch (e) {
    return e.response;
  }
}
async function getSingle(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("referrals/cust", {
      params: Objparams,
    });
    return response.data;
  } catch (e) {
    return e.response;
  }
}

function transform(rows) {
  const selectableItems = [];
  let i = 1;
  rows.forEach((item) => {
    selectableItems.push({
      id: i++,
      cust_name: item.user.firstname,
      ref_name: item.referral.firstname,
      amount: item.amount,
      start_date: item.start_date,
      end_date: item.end_date,
      pending_amount: item.pending_amount,
      payment_status: item.payment_status,
    });
  });
  return selectableItems;
}
