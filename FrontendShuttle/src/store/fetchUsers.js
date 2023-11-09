import { defineStore } from "pinia";
import useAuthApi from "../helpers/authHook";

export const fetchUsers = defineStore("fetch", {
  state: () => ({
    getUser: {},
    getName: "",
    userRole: "",
    userRoleId: "",
    oneUser: {},
  }),
  actions: {
    async fetchAll() {
      const privateAuth = useAuthApi();
      try {
        const users = await privateAuth.get("/user");
        return users.data.users;
      } catch (error) {
        if (error?.response?.data?.err) {
          alert(error.response.data.err);
        } else alert(error.message);
      }
    },
    async showUser(userId) {
      const privateAuth = useAuthApi();
      try {
        const showUser = await privateAuth.get(`/auth/${userId}`);
        this.getUser = showUser.data.user;
        this.getName = showUser.data.fullname;
        this.userRole = showUser.data.user.role;
        return showUser.data.user;
      } catch (error) {
        if (error?.response?.data?.err) {
          alert(error.response.data.err);
        } else alert(error.message);
      }
    },

    async getListUser(user) {
      const privateAuth = useAuthApi();
      try {
        const showUser = await privateAuth.get(`/user/${user}`);
        let fullname = showUser.data.fullname;
        this.oneUser = { ...showUser.data.user, fullname };
        return showUser.data.user;
      } catch (error) {
        if (error?.response?.data?.err) {
          alert(error.response.data.err);
        } else alert(error.message);
      }
    },
    async updateUser(user, data) {
      const privateAuth = useAuthApi();
      try {
        const updateUser = await privateAuth.put(`/auth/${user}`, data);
        this.getUser = updateUser.data.updatedInfo;
        this.getName = updateUser.data.fullname;
        this.$router.push({ name: "profile" });
        //alert("User details updated!!");
        this.$toast.open({
          message: "Update profile successfully",
          type: "success",
          position: "top-right",
          duration: 3000,
        });
      } catch (error) {
        if (error?.response?.data?.err) {
          alert(error.response.data.err);
        } else alert(error.message);
      }
    },
    async delUser(user) {
      const privateAuth = useAuthApi();
      try {
        const delUser = await privateAuth.delete(`/user/${user}`);
        this.fetchAll();
        alert(delUser.data.message);
      } catch (error) {
        if (error?.response?.data?.err) {
          alert(error.response.data.err);
        } else alert(error.message);
      }
    },
  },
  persist: true,
});
