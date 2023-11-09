import { defineStore } from "pinia";
import { baseApi } from "../helpers/auth";
import { fetchUsers } from "./fetchUsers";
import { useAuth } from "./useAuth";
export const refreshAuth = defineStore("refreshId", {
  state: () => {
    return {
      refID: "",
    };
  },

  actions: {
    async refresh() {
      try {
        const token = useAuth();
        const fetchUser = fetchUsers();
        const email = fetchUser.getUser.email;
        const refreshToken = token.authToken.refreshToken;
        //  console.log("refresh email", email, "refreshToken", refreshToken);
        const refresh = await baseApi.post("/auth/refresh-token", {
          email,
          refreshToken,
        });
        fetchUser.getUser = refresh.data.user;
        fetchUser.getName = `${refresh.data.user.firstname} ${refresh.data.user.lastname}`;
        fetchUser.userRole = refresh.data.user.role;
        fetchUser.userRoleId = refresh.data.user.roleId;
        token.authToken = refresh.data.token;
        this.refID = refresh.data.token.accessToken;
        token.isAuth = true;
        return refresh.data.token.accessToken;
      } catch (error) {
        console.log("refreshId error.message", error.message);
        if (error?.response?.data?.message) {
          alert(error.response.data.message);
        } else alert(error.message);
      }
    },
  },
});
