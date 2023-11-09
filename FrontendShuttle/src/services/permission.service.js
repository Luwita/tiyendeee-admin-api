import useAuthApi from "../helpers/authHook";

export const permissionService = {
  load,
};

async function load() {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("permissions");
    return response.data;
  } catch (e) {
    return e.response;
  }
}
