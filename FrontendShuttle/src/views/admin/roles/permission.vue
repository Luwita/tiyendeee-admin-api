<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-10 offset-lg-1 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <b-form-group :label="`Permissions :- ` + roleName">
                <template #label>
                  <b>Choose your flavours:</b><br />
                  <b-form-checkbox
                    v-model="allSelected"
                    :indeterminate="indeterminate"
                    aria-describedby="options"
                    aria-controls="options"
                    @change="toggleAll"
                  >
                    {{ allSelected ? "Un-select All" : "Select All" }}
                  </b-form-checkbox>
                </template>

                <template v-slot="{ ariaDescribedby }">
                  <b-form-checkbox-group
                    size="lg"
                    id="checkbox-group-1"
                    :aria-describedby="ariaDescribedby"
                    v-model.trim="$v.form.permission.$model"
                    :options="options"
                    :state="validateState('permission')"
                    @change="changePermission"
                    name="permission-1"
                    stacked
                  ></b-form-checkbox-group>
                  <b-form-invalid-feedback :state="validateState('permission')"
                    >Please select permission</b-form-invalid-feedback
                  >
                </template>
              </b-form-group>
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
import { validationMixin } from "vuelidate";
import { required } from "vuelidate/lib/validators";

export default {
  name: "roleedit",
  mixins: [validationMixin],
  data() {
    return {
      formtype: false,
      breadcrumbs: {
        title: "Role Permissions",
        b1: "Manage role",
        b2: "Role",
        b3: "Index",
        link: true,
        name: "role",
      },
      roleName: "",
      permissions: false,
      allSelected: false,
      indeterminate: false,
      form: { permission: [] },
      submitted: false,
      options: [],
    };
  },
  components: {
    Breadcrumb,
  },
  validations: {
    form: {
      permission: { required },
    },
  },
  methods: {
    validateState(name) {
      const { $dirty, $error } = this.$v.form[name];
      return $dirty ? !$error : null;
    },
    toggleAll(checked) {
      console.log("this.options.slice()", this.options);
      this.form.permission = checked ? this.options.slice() : [];
    },
    async loadPermission() {
      try {
        const response = await permissionService.load();
        if (response.status) {
          this.options = response.data;
        }
      } catch (err) {
        this.$toast.open({
          message: err,
          type: "error",
          position: "top-right",
          duration: 5000,
        });
      }
    },
    async changePermission(checked) {
      try {
        console.log("checked", checked);
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
          this.roleName = response.data.name;
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
  },
  mounted() {
    this.getrole();
    this.loadPermission();
  },
  watch: {
    permission(newValue, oldValue) {
      console.log(newValue, oldValue);
      if (newValue.length === 0) {
        this.permissions = false;
      } else if (oldValue.length === 0) {
        this.permissions = false;
      } else {
        this.permissions = true;
      }
    },
  },
};
</script>
