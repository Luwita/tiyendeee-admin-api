import useAuthApi from "../helpers/authHook";

export const timetableService = {
  getAll,
  create,
  update,
  deleteTimeTable,
  find,
  changeStatus,
};

async function changeStatus(status, id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.patch("timetables/" + id + "/status", {
      status,
    });
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function getAll(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("timetables", {
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
    const response = await privateAuth.post("timetables", Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function update(id, Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.patch("timetables/" + id, Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function find(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("timetables/" + id);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function deleteTimeTable(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.delete("timetables/" + id);
    return response.data;
  } catch (e) {
    return e.response;
  }
}
