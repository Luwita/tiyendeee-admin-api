import useAuthApi from "../helpers/authHook";

export const countryService = {
  getAll,
  create,
  update,
  remove,
  tranform,
  find,
  load,
};

async function load() {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("countries");
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function getAll(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("countries/search", {
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
    const response = await privateAuth.post("countries", Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function update(id, Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.patch("countries/" + id, Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function find(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("countries/" + id);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function remove(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.delete("countries/" + id);
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
      code: item.code,
      status: item.status == true ? "Active" : "Inactive",
      createdAt: item.createdAt,
    });
  });
  return selectableItems;
}
