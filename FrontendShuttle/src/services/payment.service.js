import useAuthApi from "../helpers/authHook";
//import moment from "moment-timezone";

export const paymentService = {
  getAll,
  tranform,
  checkStatus,
  find,
  walletCount,
};

async function walletCount(status, start_date, end_date, is_wallet) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get(
      `payments/count/${status}/${start_date}/${end_date}/${is_wallet}`
    );
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function find(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("payments/" + id);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function checkStatus(orderId, Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.post(
      "payments/check/" + orderId,
      Objparams
    );
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function getAll(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("payments/search", {
      params: Objparams,
    });
    return response.data;
  } catch (e) {
    return e.response;
  }
}

function tranform(rows) {
  const selectableItems = [];
  let i = 1;
  rows.forEach((item) => {
    selectableItems.push({
      id: i++,
      booking_pnr: item.booking_pnr,
      customer_name: item.customer_name,
      customer_phone: item.customer_phone,
      orderId: item.orderId,
      paymentId: item.paymentId,
      amount: item.amount,
      refund_amount: item.refund_amount,
      method: item.method,
      travel_status: item.travel_status,
      is_pass: item.is_pass,
      payment_status: item.payment_status,
      createdAt: item.createdAt,
    });
  });
  return selectableItems;
}
