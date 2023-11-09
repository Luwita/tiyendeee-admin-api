import useAuthApi from "../helpers/authHook";

export const driverService = {
  getAll,
  create,
  tranform,
  find,
  update,
  deleteDriver,
  changeStatus,
  q,
};

async function changeStatus(status, type, id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.patch("drivers/" + id + "/status", {
      status,
      type,
    });
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function q(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("drivers/q", {
      params: Objparams,
    });
    return response.data;
  } catch (e) {
    return e.response;
  }
}
async function getAll(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("drivers", {
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
    const response = await privateAuth.post("drivers", Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function update(id, Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.patch("drivers/" + id, Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function find(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("drivers/" + id);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function deleteDriver(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.delete("drivers/" + id);
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
      agents: item.agent_name,
      firstname: item.firstname,
      lastname: item.lastname,
      email: item.email,
      phone: item.phone,
      type: item.type,
      profile_picture: item.picture,
      licence: item.document_licence,
      adhar_card: item.document_adhar_card,
      police_vertification: item.document_police_vertification,
      status: item.status == true ? "Active" : "Inactive",
      createdAt: item.createdAt,
    });
  });
  return selectableItems;
}
