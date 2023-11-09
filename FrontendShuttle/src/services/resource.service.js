import useAuthApi from "../helpers/authHook";

export const resourceService = {
  //   getAll,
  //   create,
  //   update,
  //   deleteOffer,
  //   tranform,
  //   find,
  load,
};

async function load(role) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("resources/" + role);
    return response.data;
  } catch (e) {
    return e.response;
  }
}
