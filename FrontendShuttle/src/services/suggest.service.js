import useAuthApi from "../helpers/authHook";

export const suggestService = {
  getAll,
  find,
  transform,
};

async function getAll(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("suggests/search", {
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
    const response = await privateAuth.get("suggests/" + id);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

function transform(rows) {
  const selectableItems = [];
  let i = 1;
  rows.forEach((item) => {
    selectableItems.push({
      id: i++,
      pickup_address: item.pickup_address,
      drop_address: item.drop_address,
      customer_name: item.userId_firstname,
      createdAt: item.createdAt,
    });
  });
  return selectableItems;
}
