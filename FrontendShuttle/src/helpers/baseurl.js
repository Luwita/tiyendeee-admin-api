// import Vue from "vue";
import axios from "axios";
import store from "../store";
// import jwt from "jwt-simple";
import TokenService from "../services/token.service";
// const jwtSecret =
//   "bA2xcjpf8y5aSUFsNB2qN5yymUBSs6es3qHoFpGkec75RCeBb8cpKauGefw5qy4"; //process.env.JWT_SECRET;

const axiosInstance = axios.create({
  mode: "no-cors",
  baseURL: process.env.VUE_APP_API_URL,
  headers: {
    "Content-Type": "application/json",
    // 'Access-Control-Allow-Origin': '*',
  },
});

axiosInstance.interceptors.request.use(
  (config) => {
    const token = store.getters["auth/accessToken"];
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (err) => Promise.reject(err)
);

axiosInstance.interceptors.response.use(
  (response) => response,
  async function (err) {
    const { config: originalRequest, response } = err;
    if (response && response.status === 401 && !originalRequest._retry) {
      try {
        originalRequest._retry = true;
        let refreshToken = TokenService.getLocalRefreshToken();
        const user = TokenService.getUser();
        let email = user.email;
        const res = await axiosInstance.post("auth/refresh-token", {
          email,
          refreshToken,
        });
        let accessToken = res.data.token.accessToken;
        let newrefreshToken = res.data.token.refreshToken;
        let tokenType = res.data.token.tokenType;

        store.dispatch("auth/refreshToken", accessToken);
        TokenService.updateLocalAccessToken(accessToken);
        TokenService.updateLocalRefreshToken(newrefreshToken);
        response.config.headers[
          "Authorization"
        ] = `${tokenType} ${accessToken}`;
        return axiosInstance(originalRequest);
      } catch (e) {
        //   console.log(" 12312sds", e.response);
        if (
          e === "user has not logged in" ||
          (e.response && e.response.status === 401)
        ) {
          store.dispatch("auth/logout", true);
        }
      }
    }
    return Promise.reject(err);
  }
);

// before any API call make sure that the access token is good
// axiosInstance.interceptors.request.use(async function () {
//   // console.log("dataDev", store.auth);
//   const loggedIn = localStorage.getItem("user");
//   if (loggedIn) {
//     await store.dispatch("auth/isLoggedIn");
//   }
// });

// Vue.prototype.$http = axiosInstance;

// export default Vue.prototype.$http;
export default axiosInstance;
