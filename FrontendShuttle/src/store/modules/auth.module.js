import { authService } from "../../services";

// import apiAxios from "../../utils/apiAxios";

const user = JSON.parse(localStorage.getItem("user"));
const role = localStorage.getItem("role");
const accessToken = localStorage.getItem("accessToken");
const refreshToken = localStorage.getItem("refreshToken");

const initialState = user
  ? {
      authStatus: "",
      status: { loggedIn: true },
      user,
      role,
      accessToken,
      refreshToken,
      // expiresIn,
      // tokenType,
    }
  : {
      authStatus: "",
      status: {},
      user: null,
      role: null,
      accessToken: null,
      refreshToken: null,
      // expiresIn: null,
      // tokenType: null,
    };

export const auth = {
  namespaced: true,
  state: initialState,
  getters: {
    authStatus: (state) => state.authStatus,
    role: (state) => state.role,
    accessToken: (state) => state.accessToken,
    isLoggedIn: (getters) => {
      // quick check of the state
      return getters.authStatus === "success";
    },
  },
  actions: {
    login({ dispatch, commit }, { email, password }) {
      return authService.login(email, password).then(
        (user) => {
          commit("loginSuccess", user);
          return Promise.resolve(user);
        },
        (error) => {
          commit("loginFailure", error);
          dispatch("alert/error", error, { root: true });
          return Promise.reject(error);
        }
      );
    },
    logout({ commit }) {
      authService.logout();
      commit("logout");
    },
    refreshToken({ commit }, accessToken) {
      commit("refreshToken", accessToken);
    },
  },
  mutations: {
    async loginSuccess(state, user) {
      state.authStatus = "success";
      state.status.loggedIn = true;
      state.user = user;
      state.role = user.role;
    },
    loginFailure(state) {
      state.authStatus = "";
      state.status.loggedIn = false;
      state.status = {};
      state.user = null;
      state.role = null;
      state.accessToken = null;
      state.refreshToken = null;
    },
    logout(state) {
      state.authStatus = "";
      state.status.loggedIn = false;
      state.status = {};
      state.user = null;
      state.role = null;
      state.accessToken = null;
      state.refreshToken = null;
    },
    refreshToken(state, accessToken) {
      state.status.loggedIn = true;
      //  state.user = { ...state.user, accessToken: accessToken };
      state.accessToken = accessToken;
    },
  },
};
