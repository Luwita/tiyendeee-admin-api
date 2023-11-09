import useAuthApi from "../helpers/authHook";
import moment from "moment-timezone";

export const routeService = {
  getAll,
  tranform,
  create,
  find,
  update,
  changeStatus,
  deleteRoute,
  deleteRouteDetail,
  load,
  getLocationRoutes,
  formatedData,
  search,
  findStops,
};

async function findStops(routeId) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("routes/stops/" + routeId);
    return response.data;
  } catch (e) {
    if (e.response.status === 401) {
      return e.response.data;
    }
  }
}

async function search(name) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("routes/find/" + name);
    return response.data;
  } catch (e) {
    if (e.response.status === 401) {
      return e.response.data;
    }
  }
}

async function load() {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("routes");
    return response.data;
  } catch (e) {
    if (e.response.status === 401) {
      return e.response.data;
    }
  }
}

async function changeStatus(status, id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.patch("routes/" + id + "/status", {
      status,
    });
    return response.data;
  } catch (e) {
    if (e.response.status === 401) {
      return e.response.data;
    }
  }
}

async function getAll(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("routes/search", {
      params: Objparams,
    });
    return response.data;
  } catch (e) {
    if (e.response.status === 401) {
      return e.response.data;
    }
  }
}

async function create(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.post("routes", Objparams);
    return response.data;
  } catch (e) {
    return e.response.data;
  }
}

async function update(id, Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.patch("routes/" + id, Objparams);
    return response.data;
  } catch (e) {
    return e.response.data;
  }
}

async function find(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("routes/" + id);
    return response.data;
  } catch (e) {
    return e.response.data;
  }
}

async function getLocationRoutes(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("routes/" + id + "/options");
    return response.data;
  } catch (e) {
    return e.response.data;
  }
}

async function deleteRoute(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.delete("routes/" + id);
    return response.data;
  } catch (e) {
    return e.response.data;
  }
}

async function deleteRouteDetail(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.delete("routes/route-detail/" + id);
    return response.data;
  } catch (e) {
    return e.response.data;
  }
}

function formatedData(rows) {
  const selectableItems = [];
  let i = 1;
  rows.forEach((item) => {
    selectableItems.push({
      id: i++,
      title: item.title,
      busname: item.busId,
      location_name: item.locationId ? item.locationId.name : "",
      location_type: item.locationId ? item.locationId.type : "",
      stops: item.routedetails ? item.routedetails : [{}],
      status: item.status,
    });
  });
  return selectableItems;
}

function tranform(rows) {
  const selectableItems = [];
  let i = 1;
  rows.forEach((item) => {
    selectableItems.push({
      id: i++,
      title: item.title,
      // type: item.type,
      // address: item.location.address,
      // latitude: item.location.coordinates[1],
      // longitude: item.location.coordinates[0],
      status: item.status,
      createdAt: moment.utc(item.createdAt).tz("Asia/Kolkata").format("LLL"),
    });
  });
  return selectableItems;
}
