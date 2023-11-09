import useAuthApi from "../helpers/authHook";

export const locationService = {
  getAll,
  tranform,
  create,
  search,
  update,
  deleteLocation,
  find,
  markers,
};

async function find(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("locations/" + id);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function getAll(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("locations", {
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
    const response = await privateAuth.post("locations", Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function update(id, Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.patch("locations/" + id, Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function deleteLocation(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.delete("locations/" + id);
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
      title: item.title,
      type: item.type,
      address: item.location_address,
      latitude: item.location_lat,
      longitude: item.location_lng,
      status: item.status,
      createdAt: item.createdAt,
    });
  });
  return selectableItems;
}

async function markers() {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("locations/markers");
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function search(Objparams) {
  try {
    const privateAuth = useAuthApi();
    //locations/search
    const response = await privateAuth.get("locations/search", {
      params: Objparams,
    });
    return response.data;
  } catch (e) {
    return e.response;
  }
}
