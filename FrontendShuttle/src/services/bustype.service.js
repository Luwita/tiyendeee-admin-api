import useAuthApi from "../helpers/authHook";

export const bustypeService = {
  getAll,
  create,
  update,
  deleteBustype,
  tranform,
  find,
  load,
};

async function load() {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("bustypes");
    return response.data;
  } catch (e) {
    return e.response;
  }
}
async function getAll(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("bustypes/search", {
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
    const response = await privateAuth.post("bustypes", Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function update(id, Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.patch("bustypes/" + id, Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function find(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("bustypes/" + id);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function deleteBustype(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.delete("bustypes/" + id);
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
      status: item.status == true ? "Active" : "Inactive",
      createdAt: item.createdAt,
    });
  });
  return selectableItems;
}
