import useAuthApi from "../helpers/authHook";
import moment from "moment-timezone";

export const offerService = {
  getAll,
  create,
  update,
  deleteOffer,
  tranform,
  find,
  load,
};

async function load() {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("offers");
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function getAll(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("offers/search", {
      params: Objparams,
    });
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function create(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.post("offers", Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function update(id, Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.patch("offers/" + id, Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function find(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("offers/" + id);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function deleteOffer(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.delete("offers/" + id);
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
      name: item.name,
      start_date: item.start_date,
      end_date: item.end_date,
      code: item.code,
      discount: item.discount,
      type: item.type ? item.type : "",
      terms: item.terms,
      picture: item.picture,
      status: item.status == true ? "Active" : "Inactive",
      createdAt: moment.utc(item.createdAt).tz("Asia/Kolkata").format("LLL"),
    });
  });
  return selectableItems;
}
