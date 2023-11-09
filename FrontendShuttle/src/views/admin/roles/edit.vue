<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-8 offset-lg-2 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <b-form :if="formtype" @submit.prevent="editRole">
                <b-form-group
                  label="Name"
                  label-for="name-input"
                  label-cols-sm="4"
                  label-cols-lg="2"
                  content-cols-sm
                  content-cols-lg="8"
                >
                  <b-form-input
                    id="name-input"
                    v-model.trim="form.name"
                    type="text"
                    placeholder="Enter Role Name"
                    readonly
                  ></b-form-input>
                </b-form-group>

                <b-form-group
                  label="Permission"
                  label-for="permission-input"
                  label-cols-sm="4"
                  label-cols-lg="2"
                  content-cols-sm
                  content-cols-lg="10"
                >
                  <multiselect
                    v-model="form.permissions"
                    tag-placeholder="Add this as new tag"
                    placeholder="Search or add a tag"
                    label="name"
                    track-by="code"
                    :options="options"
                    :multiple="true"
                    :taggable="true"
                  ></multiselect>
                </b-form-group>

                <b-form-group class="col-md-2 offset-md-6">
                  <b-button
                    type="submit"
                    class="btn btn-lg btn-success text-center"
                    >Update</b-button
                  >
                </b-form-group>
              </b-form>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { roleService, permissionService } from "../../../services";
import Breadcrumb from "../../../components/breadcrumb";
import Multiselect from "vue-multiselect";

export default {
  name: "roleedit",
  data() {
    return {
      formtype: false,
      breadcrumbs: {
        title: "Edit Role",
        b1: "Manage role",
        b2: "Roles",
        b3: "Index",
        link: true,
        name: "role",
      },
      form: {
        id: "",
        name: "",
        permissions: [],
      },
      options: [],
      submitted: false,
    };
  },
  components: {
    Breadcrumb,
    Multiselect,
  },
  methods: {
    async getpermissions() {
      try {
        const response = await permissionService.load();
        if (response.status) {
          this.options = response.data;
        }
      } catch (e) {
        console.log("params", e);
        this.$toast.open({
          message: e,
          type: "error",
          position: "top-right",
          duration: 5000,
        });
      }
    },
    async getrole() {
      try {
        const response = await roleService.find(this.$route.params.id);
        if (response.status) {
          this.form = response.data;
        }
      } catch (e) {
        console.log("params", e);
        this.$toast.open({
          message: e,
          type: "error",
          position: "top-right",
          duration: 5000,
        });
      }
    },
    async editRole() {
      try {
        this.submitted = true;
        const response = await roleService.attachPermission(
          this.$route.params.id,
          this.form
        );
        if (response.status) {
          this.$toast.open({
            message: response.message,
            type: "success",
            position: "top-right",
            duration: 3000,
            // all of other options may go here
          });
          setTimeout(
            () =>
              this.$router.push({
                path: `/roles`,
              }),
            1000
          );
        }
      } catch (e) {
        this.$toast.open({
          message: e + "dfsdf",
          type: "error",
          position: "top-right",
          duration: 5000,
        });
      }
    },
  },
  mounted() {
    this.getrole();
    this.getpermissions();
  },
};
</script>
<style scoped>
.multiselect__tag {
  background: #000 !important;
}
</style>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
