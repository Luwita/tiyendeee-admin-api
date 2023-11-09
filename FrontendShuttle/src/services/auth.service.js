import { baseApi, authApi } from "../helpers/auth";
import useAuthApi from "../helpers/authHook";
import TokenService from "./token.service";
// import router from "@/router";
// import Vue from "vue";

export const authService = {
  getAll,
  login,
  logout,
  register,
  sendPasswordRest,
  resetPassword,
  getAgents,
  updateProfile,
  profile,
};

async function getAll(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("users", {
      params: Objparams,
    });
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function profile(id) {
  try {
    const response = await authApi.get("auth/" + id);
    return response.data.data;
  } catch (e) {
    return e.response;
  }
}

async function updateProfile(id, Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.patch("users/" + id, Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function getAgents(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("agents", {
      params: Objparams,
    });
    return response.data;
  } catch (e) {
    return e.response;
  }
}

function login(email, password) {
  try {
    return baseApi
      .post("/auth/login", {
        email,
        password,
      })
      .then((response) => {
        let user = response.data;
        // let accessToken = response.data.token.accessToken;
        // let refreshToken = response.data.token.refreshToken;
        // TokenService.setUser(user);
        // TokenService.setLocalRefreshToken(refreshToken);
        // TokenService.setLocalAccessToken(accessToken);
        // TokenService.setRole(user.role);

        return user;
      });
  } catch (e) {
    if (e.response.status === 401) {
      return e.response.data;
    }
  }
}

function logout() {
  TokenService.removeAccessToken();
  TokenService.removeRefreshToken();
  TokenService.removeUser();
  TokenService.removeRole();
}

async function register(user) {
  try {
    const register = await baseApi.post("auth/register", user);
    return register;
  } catch (e) {
    return e.response;
  }
}

async function sendPasswordRest(email) {
  try {
    const response = await baseApi.post("auth/send-password-reset", {
      email,
    });

    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function resetPassword(email, password, resetToken) {
  try {
    const response = await baseApi.post("auth/reset-password", {
      email,
      password,
      resetToken,
    });
    return response.data;
  } catch (e) {
    return e.response;
  }
}
