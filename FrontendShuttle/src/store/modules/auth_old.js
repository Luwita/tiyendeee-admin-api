//store/modules/auth.jsnpm i
import axios from "axios";
import moment from "moment-timezone";

const API_URL = "http://localhost:3030/v1/";

const state = {
  status: "",
  user: null,
  token: localStorage.getItem("token") || null,
  refreshToken: localStorage.getItem("refreshToken") || null,
  expiresIn: localStorage.getItem("expiresIn") || null,
  tokenType: "",
};
const getters = {
  isAuthenticated: (state) => !!state.user,
  authStatus: (state) => state.status,
  StateUser: (state) => state.user,
  Token: (state) => state.token,
  tokenType: (state) => state.tokenType,
};
const actions = {
  LogIn: async ({ commit }, userInfo) => {
    try {
      let response = await axios.post(API_URL + "auth/login", userInfo);
      console.log("response", response);
      let token = response.data.token.accessToken;
      let refreshToken = response.data.token.refreshToken;
      let expiresIn = response.data.token.expiresIn;
      let tokenType = response.data.token.tokenType;
      let user = response.data.user;
      localStorage.setItem("token", token);
      localStorage.setItem("refreshToken", refreshToken);
      localStorage.setItem("expiresIn", expiresIn);
      localStorage.setItem("tokenType", tokenType);
      commit("setUser", user);
    } catch (err) {
      console.log(err);
    }
  },
  register: async ({ commit }, user) => {
    try {
      await axios.post(API_URL + "auth/register", user);
      commit("authRequest");
    } catch (err) {
      console.log(err);
    }
  },
  logOut: ({ commit }) => {
    let user = null;
    commit("LOGOUT", user);
    localStorage.removeItem("token");
    localStorage.removeItem("refreshToken");
    localStorage.removeItem("expiresIn");
    localStorage.removeItem("tokenType");
    delete axios.defaults.headers.common["Authorization"];
  },
  refreshTokens: async ({ commit, getters }) => {
    try {
      let refreshToken = localStorage.getItem("refreshToken");
      let email = getters.StateUser.email;

      const res = await axios.post(API_URL + "auth/refresh-token", {
        email,
        refreshToken,
      });

      commit("UPDATE_TOKEN", res.data.token);
      commit("setUser", res.data.user);
    } catch (err) {
      console.log(err);
    }
  },
  checkAccessTokenExpiry: ({ state, getters, dispatch }) => {
    // inspect the store access token's expiration
    let currentTime = moment().local().unix();
    let expiresIn = state.expiresIn;
    if (getters.authStatus == "success") {
      if (currentTime > expiresIn) {
        dispatch("refreshTokens");
      }
    }
  },
  isLoggedIn: ({ getters, dispatch }) => {
    let refresh = localStorage.getItem("refreshToken");
    if (refresh) {
      if (getters.authStatus == "success") {
        dispatch("checkAccessTokenExpiry");
      }
      //return getters.isAuthenticated;
    }
    return false;
  },
};
const mutations = {
  setUser(state, user) {
    state.status = "success";
    state.user = user;
  },
  LOGOUT: (state, user) => {
    state.user = user;
    state.status = "";
    state.token = "";
    state.refreshToken = "";
    state.expiresIn = "";
  },
  authRequest: (state) => {
    state.status = "loading";
  },
  authError: (state) => {
    state.status = "error";
  },
  UPDATE_TOKEN: (state, newToken) => {
    state.status = "success";
    localStorage.setItem("token", newToken.accessToken);
    localStorage.setItem("refreshToken", newToken.refreshToken);
    localStorage.setItem("expiresIn", newToken.expiresIn);
    localStorage.setItem("tokenType", newToken.tokenType);
  },
};
export default {
  state,
  getters,
  actions,
  mutations,
};
