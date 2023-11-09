import { defineStore } from "pinia";
import { authApi } from "../helpers/auth";
import { authService } from "../services";
import { fetchUsers } from "./fetchUsers";

import jwtDecode from "jwt-decode";
export const useAuth = defineStore("userAuth", {
  state: () => ({
    authToken: {},
    isAuth: false,
    getRolePermissions: [],
  }),
  getters: {},
  actions: {
    async UacPermission(roleId) {
      try {
        const getPerms = await authApi.post("/auth/access", { roleId });
        this.getRolePermissions = getPerms.data.permissions;
      } catch (error) {
        if (error?.response?.data?.message) {
          alert(error.response.data.message);
        } else alert(error.message);
      }
    },
    async UserLogin(email, password) {
      try {
        const getUser = fetchUsers();
        const response = await authService.login(email, password);
        this.authToken = response.token;
        let user = await jwtDecode(this.authToken.accessToken);
        await this.UacPermission(user.roleId);
 
        if (this.getRolePermissions.length > 0) {
          await getUser.showUser(user.sub);
          getUser.userRoleId = user.roleId;
          this.isAuth = true;
          this.$router.replace({ name: "dashboard" });
          //   alert("LOGGED IN!!");
        }
      } catch (error) {
     

        if (error?.response?.data?.message) {
          alert(error.response.data.message);
        } else alert(error.message);
      }
    },
    // async UserReg(data) {
    //   try {
    //     const getUser = fetchUsers();
    //     const response = await baseURL.post("/register", {
    //       data: data,
    //     });

    //     this.authToken = response.data.authToken;
    //     let user = await jwtDecode(this.authToken);
    //     const getPer_ = await this.UacPermission(user.role, user.Email);
    //     if (this.getRolePermissions.length > 0) {
    //       await getUser.showUser(user.Email);
    //       getUser.userRole = user.role;
    //       this.isAuth = true;
    //       this.$router.replace({ name: "home", params: { user: user.Email } });
    //       alert("USER CREATED!!");
    //     }
    //   } catch (error) {
    //     if (error?.response?.data?.message) {
    //       alert(error.response.data.message);
    //     } else alert(error.message);
    //   }
    // },
    async signOut() {
      try {
        //  const getUser = fetchUsers();
        // const response = await baseURL.post("/logout");
        //  getUser.$reset();
        this.$reset();
        this.$router.replace({ path: "/auth/login" });
      } catch (error) {
        if (error?.response?.data?.message) {
          alert(error.response.data.message);
        } else alert(error.message);
      }
    },
  },
  persist: true,
});
