<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-12 stretch-card">
          <div class="card">
            <div class="card-body">
              <b-form :if="formtype" @submit.prevent="createRole">
                <b-form-group
                  label="Name"
                  label-for="name-input"
                  label-cols-sm="4"
                  label-cols-lg="5"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="name-input"
                    v-model.trim="$v.form.name.$model"
                    type="text"
                    placeholder="Enter Role Name"
                    :class="{
                      'is-invalid': submitted && $v.form.name.$error,
                    }"
                    :state="validateState('name')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.name.required"
                    class="invalid-feedback"
                  >
                    name is required
                  </div>
                </b-form-group>

                <b-table
                  hover
                  bordered
                  :items="items"
                  :fields="fields"
                  caption-top
                  responsive="sm"
                >
                  <template #table-caption>Set Permisson</template>

                  <template #cell(create)="data">
                    <b-form-checkbox
                      v-model="data.item.create"
                      @change="
                        changeResource(
                          data.item.id,
                          data.item.ids,
                          data.item.name,
                          data.item.create,
                          'create'
                        )
                      "
                    ></b-form-checkbox>
                  </template>

                  <template #cell(read)="data">
                    <b-form-checkbox
                      v-model="data.item.read"
                      @change="
                        changeResource(
                          data.item.id,
                          data.item.ids,
                          data.item.name,
                          data.item.read,
                          'read'
                        )
                      "
                    ></b-form-checkbox>
                  </template>

                  <template #cell(update)="data">
                    <b-form-checkbox
                      v-model="data.item.update"
                      @change="
                        changeResource(
                          data.item.id,
                          data.item.ids,
                          data.item.name,
                          data.item.update,
                          'update'
                        )
                      "
                    ></b-form-checkbox>
                  </template>

                  <template #cell(delete)="data">
                    <b-form-checkbox
                      v-model="data.item.delete"
                      @change="
                        changeResource(
                          data.item.id,
                          data.item.ids,
                          data.item.name,
                          data.item.delete,
                          'delete'
                        )
                      "
                    ></b-form-checkbox>
                  </template>
                </b-table>

                <b-form-group class="col-md-2 offset-md-6">
                  <b-button
                    type="submit"
                    class="btn btn-lg btn-success text-center"
                    >Save</b-button
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
import { roleService, resourceService } from "../../../services";
import Breadcrumb from "../../../components/breadcrumb";
import { validationMixin } from "vuelidate";
import { required } from "vuelidate/lib/validators";

export default {
  name: "rolecreate",
  mixins: [validationMixin],
  data() {
    return {
      formtype: false,
      breadcrumbs: {
        title: "Create Role",
        b1: "Manage role",
        b2: "Role",
        b3: "Index",
        link: true,
        name: "role",
      },
      form: {
        id: "",
        name: "",
        resource_roles: [],
      },
      fields: [
        { key: "id", label: "S.No." },
        { key: "name", label: "Model title" },
        { key: "create", label: "can create" },
        { key: "read", label: "can read" },
        { key: "update", label: "can update" },
        { key: "delete", label: "can delete" },
      ],
      items: [{}],
      submitted: false,
    };
  },
  components: {
    Breadcrumb,
  },
  validations: {
    form: {
      name: { required },
    },
  },
  mounted() {
    this.loadResourceItems();
  },
  methods: {
    loadResourceItems() {
      resourceService.load().then((response) => {
        this.items = response.data;
      });
    },
    validateState(name) {
      const { $dirty, $error } = this.$v.form[name];
      return $dirty ? !$error : null;
    },
    changeResource(id, ids, n, c, type) {
      console.log("asdasdasd : ", id, ids, n, c, type);
      const resourceroles = this.form.resource_roles;
      if (resourceroles.length > 0) {
        console.log("resourceroles.length", resourceroles.length);
        for (const rr of resourceroles) {
          if (rr.id == id) {
            resourceroles.push({
              id: rr.id,
              ids: rr.ids,
              name: rr.name,
              create: type === "create" ? c : false,
              read: type === "read" ? c : false,
              update: type === "update" ? c : false,
              delete: type === "delete" ? c : false,
            });
          }
        }
      } else {
        resourceroles.push({
          id: id,
          ids: ids,
          name: n,
          create: type === "create" ? c : false,
          read: type === "read" ? c : false,
          update: type === "update" ? c : false,
          delete: type === "delete" ? c : false,
        });
      }
    },
    async createRole() {
      try {
        this.submitted = true;

        // stop here if form is invalid
        this.$v.$touch();
        if (this.$v.$invalid) {
          return;
        }

        const reponse = await roleService.create(this.form);
        if (reponse.status) {
          this.$toast.open({
            message: reponse.message,
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
};
</script>

<style lang="scss" scoped></style>
