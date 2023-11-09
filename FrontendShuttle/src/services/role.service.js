import useAuthApi from "../helpers/authHook";
import moment from "moment-timezone";

export const roleService = {
  getAll,
  create,
  update,
  deleteRole,
  tranform,
  find,
  load,
  attachPermission,
};

async function attachPermission(id, Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.patch(
      "roles/attach/" + id + "/permissions",
      Objparams
    );
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function load() {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("roles");
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function getAll(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("roles/search", {
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
    const response = await privateAuth.post("roles", Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function update(id, Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.patch("roles/" + id, Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function find(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("roles/" + id);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function deleteRole(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.delete("roles/" + id);
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
      slug: item.slug,
      createdAt: moment.utc(item.createdAt).tz("Asia/Kolkata").format("LLL"),
    });
  });
  return selectableItems;
}
