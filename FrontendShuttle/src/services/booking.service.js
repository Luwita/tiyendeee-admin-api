import useAuthApi from "../helpers/authHook";
//import moment from "moment-timezone";

export const bookingService = {
  getAll,
  //   create,
  //   update,
  //   deleteBus,
  tranform,
  find,
  bookingCount,
  //   load,
};

async function bookingCount(status, start_date, end_date) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get(
      `bookings/count/${status}/${start_date}/${end_date}`
    );
    return response.data;
  } catch (e) {
    return e.response;
  }
}

// async function load() {
//   try {
//     const response = await baseURL.get("buses");
//     return response.data;
//   } catch (e) {
//     if (e.response.status === 401) {
//       return e.response.data;
//     }
//   }
// }

async function find(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("bookings/" + id);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function getAll(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("bookings/search", {
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
      pnr_no: item.pnr_no,
      has_return: item.has_return ? "Yes" : "No",
      customer_name: item.customer_name,
      customer_phone: item.customer_phone,
      customer_email: item.customer_email,
      customer_gender: item.customer_gender,
      stopping_points: item.stopping_points,
      distance: item.distance,
      passengers: item.passengers,
      bus_name: item.bus_name,
      bus_model_no: item.bus_model_no,
      seat_nos: item.seat_nos,
      tax_amount: item.tax_amount,
      tax: item.tax,
      fee: item.fee,
      final_total_fare: item.final_total_fare,
      orderId: item.orderId,
      travel_status: item.travel_status,
      payment_status: item.payment_status,
      payment_created: item.payment_created,
    });
  });
  return selectableItems;
}
