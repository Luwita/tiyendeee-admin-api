import useAuthApi from "../helpers/authHook";
//import moment from "moment-timezone";

export const customerService = {
  getAll,
  tranform,
  deleteUser,
  update,
  find,
  walletHistory,
  q,
  //load,
};

// async function load() {
//   try {
//     const response = await baseURL.get("roles");
//     return response.data;
//   } catch (e) {
//     if (e.response.status === 401) {
//       return e.response.data;
//     }
//   }
// }

async function walletHistory(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("users/wallet-histories", {
      params: Objparams,
    });
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function find(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("users/" + id);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function getAll(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("users/search", {
      params: Objparams,
    });
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function q(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("users/q", {
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
  rows.forEach((user) => {
    selectableItems.push({
      id: i++,
      fullname: user.fullname,
      gender: user.gender,
      email: user.email,
      phone: user.phone,
      mode: user.mode,
      refercode: user.refercode,
      ProfilePic: user.ProfilePic,
      home_address: user.home_address,
      home_lat: user.home_lat,
      home_lng: user.home_lng,
      home_timing: user.home_timing,
      office_timing: user.office_timing,
      office_address: user.office_address,
      office_lat: user.office_lat,
      office_lng: user.office_lng,
      status: user.status,
      createdAt: user.createdAt,
    });
  });
  return selectableItems;
}

async function update(id, Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.patch("users/" + id, Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function deleteUser(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.delete("users/" + id);
    return response.data;
  } catch (e) {
    return e.response;
  }
}
