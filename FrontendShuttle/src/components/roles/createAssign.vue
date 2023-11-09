<template>
  <div>
    <b-form @submit.prevent="updateAssignRole">
      <b-form-group
        label="Roles"
        label-for="role-input"
        label-cols-sm="4"
        label-cols-lg="4"
        content-cols-sm
        content-cols-lg="5"
      >
        <b-form-select
          v-model.trim="$v.form.role.$model"
          :options="roles"
          :class="{
            'is-invalid': submitted && $v.form.role.$error,
          }"
          :state="validateState('role')"
          @change="changeRole"
        >
          <div
            v-if="submitted && !$v.form.role.required"
            class="invalid-feedback"
          >
            role is required
          </div>
        </b-form-select>
      </b-form-group>

      <b-form-group
        v-for="item in load"
        :key="item.id"
        :label="item.name"
        label-for="role-input"
        label-cols-sm="4"
        label-cols-lg="4"
        content-cols-sm
        content-cols-lg="8"
        v-slot="{ ariaDescribedby }"
      >
        <b-form-checkbox-group
          v-model="form.permissions"
          :options="item.permissions"
          name="flavour-1a"
          :aria-describedby="ariaDescribedby"
          switches
        >
        </b-form-checkbox-group>
      </b-form-group>
    </b-form>
  </div>
</template>

<script>
import { validationMixin } from "vuelidate";
import { required } from "vuelidate/lib/validators";
import { resourceService } from "../../services/resource.service";

export default {
  mixins: [validationMixin],
  name: "createAssign",
  props: {
    roles: Array,
  },
  data() {
    return {
      form: {
        role: "admin",
        permissions: [{}],
      },
      submitted: false,
      load: [],
    };
  },
  validations: {
    form: {
      role: { required },
    },
  },
  mounted() {
    this.loadResources();
  },
  methods: {
    validateState(name) {
      const { $dirty, $error } = this.$v.form[name];
      return $dirty ? !$error : null;
    },
    async changeRole(value) {
      const result = await resourceService.load(value);
      this.load = result.data;
    },
    async loadResources() {
      const result = await resourceService.load(this.form.role);
      this.load = result.data;
    },
    async updateAssignRole() {
      try {
        this.submitted = true;

        // stop here if form is invalid
        this.$v.$touch();
        if (this.$v.$invalid) {
          return;
        }
      } catch (err) {
        return err;
      }
    },
  },
};
</script>

<style>
</style>