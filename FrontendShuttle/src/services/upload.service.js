import useAuthApi from "../helpers/authHook";
//import moment from "moment-timezone";

export const uploadService = {
  store,
  remove,
};

async function store(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.post("uploads/store", Objparams);
    console.log("response ", response);
    return response.data;
    //return Objparams;
  } catch (e) {
    return e.response.data;
  }
}

async function remove(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.delete("uploads/delete", Objparams);
    return response.data;
  } catch (e) {
    return e.response.data;
  }
}
