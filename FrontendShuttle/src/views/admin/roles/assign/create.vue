<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-10 offset-lg-1 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <create-assign :roles="roles" />
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import Breadcrumb from "../../../../components/breadcrumb";
import createAssign from "../../../../components/roles/createAssign.vue";
import { roleService } from "../../../../services";
export default {
  name: "roleAssginCreate",
  data() {
    return {
      breadcrumbs: {
        title: "Create Role Assign",
        b1: "Manage Assign",
        b2: "Role Assign",
        b3: "Index",
        link: true,
        name: "Assign",
      },
      roles: [],
    };
  },
  components: {
    Breadcrumb,
    createAssign,
  },
  mounted() {
    this.loadRole();
  },
  methods: {
    loadRole() {
      roleService.load().then((response) => {
        if (response.data.length > 0) {
          this.roles = response.data;
        } else {
          this.$toast.open({
            message: "No role found",
            type: "error",
            position: "top-right",
            duration: 3000,
          });
        }
      });
    },
  },
};
</script>

<style>
</style>