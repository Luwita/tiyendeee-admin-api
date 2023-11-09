import useAuthApi from "../helpers/authHook";

export const helperService = {
  getAll,
  deleteHelper,
  update,
  // tranform,
};

async function getAll(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("helpers/search", {
      params: Objparams,
    });
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function update(id, Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.patch("helpers/" + id, Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function deleteHelper(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.delete("helpers/" + id);
    return response.data;
  } catch (e) {
    return e.response;
  }
}
