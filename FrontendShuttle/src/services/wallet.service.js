import useAuthApi from "../helpers/authHook";

export const walletService = {
  create,
};

async function create(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.post("wallets", Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}
