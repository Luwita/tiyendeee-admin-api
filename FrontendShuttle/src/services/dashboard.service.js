///import baseURL from "../helpers/baseurl";
import useAuthApi from "../helpers/authHook";
//import moment from "moment-timezone";

export const dashboardService = {
  loadCountData,
};

async function loadCountData() {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("dashboard/count");
    return response.data;
  } catch (e) {
    console.log("dashboard", e);
    return e.response;
  }
}
