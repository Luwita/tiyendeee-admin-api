import useAuthApi from "../helpers/authHook";

export const cityService = {
  load,
};

async function load() {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("cities");
    return response.data;
  } catch (e) {
    return e.response;
  }
}
