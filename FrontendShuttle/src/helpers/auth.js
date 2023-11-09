import axios from "axios";
const BASE_URL = process.env.VUE_APP_API_URL;
export const baseApi = axios.create({
  mode: "no-cors",
  baseURL: BASE_URL,
  headers: { "Content-Type": "application/json" },
  //  withCredentials: true,
});
export const authApi = axios.create({
  mode: "no-cors",
  baseURL: BASE_URL,
  headers: { "Content-Type": "application/json" },
  // withCredentials: true,
});
