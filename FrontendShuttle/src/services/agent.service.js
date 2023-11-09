import useAuthApi from "../helpers/authHook";
//import moment from "moment-timezone";

export const agentService = {
  getAll,
  tranform,
  create,
  deleteAgent,
};

async function getAll(Objparams) {
  try {
    const privateAuth = useAuthApi();
    Objparams.role = "agent";
    const response = await privateAuth.get("agents", {
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
    const response = await privateAuth.post("agents", Objparams);
    console.log("response ", response);
    return response.data;
    //return Objparams;
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
      profile_picture: item.picture,
      firstname: item.firstname,
      lastname: item.lastname,
      email: item.email,
      phone: item.phone,
      city: item.city,
      company: item.company,
      address_1: item.address_1,
      address_2: item.address_2,
      pincode: item.pincode,
      status: item.is_active,
      last_login: item.last_login,
      createdAt: item.createdAt,
    });
  });
  return selectableItems;
}

async function deleteAgent(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.delete("agents/" + id);
    return response.data;
  } catch (e) {
    return e.response;
  }
}
