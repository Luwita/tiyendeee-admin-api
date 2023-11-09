import useAuthApi from "../helpers/authHook";

export const currencyService = {
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
    const response = await privateAuth.get("currencies");
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function getAll(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("currencies/search", {
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
    const response = await privateAuth.post("currencies", Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function update(id, Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.patch("currencies/" + id, Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function find(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("currencies/" + id);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function remove(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.delete("currencies/" + id);
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
      symbol: item.symbol,
      status: item.status == true ? "Active" : "Inactive",
      createdAt: item.createdAt,
    });
  });
  return selectableItems;
}
