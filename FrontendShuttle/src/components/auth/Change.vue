<template>
  <div>
    <b-alert :show="isSuccess" variant="success"> {{ success }}</b-alert>
    <b-alert :show="isError" variant="danger"> {{ error }}</b-alert>
    <b-form @submit.stop.prevent="changePassword">
      <div class="form-group">
        <label class="label">Email Address</label>
        <div class="input-group">
          <input
            type="email"
            class="form-control"
            placeholder="Email Address"
            v-model.trim="$v.email.$model"
            :class="{ 'is-invalid': submitted && $v.email.$error }"
          />
          <div class="input-group-append">
            <span class="input-group-text">
              <i
                class="mdi mdi-check-circle-outline"
                :class="{ 'text-danger': submitted && $v.email.$error }"
              ></i>
            </span>
          </div>
          <div v-if="submitted && !$v.email.required" class="invalid-feedback">
            email address is required
          </div>
        </div>
      </div>
      <br />
      <div class="form-group">
        <label class="label">Password</label>
        <div class="input-group">
          <input
            type="password"
            class="form-control"
            placeholder="Password"
            v-model.trim="$v.password.$model"
            :class="{ 'is-invalid': submitted && $v.password.$error }"
          />
          <div class="input-group-append">
            <span class="input-group-text">
              <i
                class="mdi mdi-check-circle-outline"
                :class="{ 'text-danger': submitted && $v.password.$error }"
              ></i>
            </span>
          </div>
          <div
            v-if="submitted && !$v.password.required"
            class="invalid-feedback"
          >
            password is required
          </div>
        </div>
      </div>
      <br />
      <div class="form-group">
        <label class="label">Confirm Password</label>
        <div class="input-group">
          <input
            type="password"
            class="form-control"
            placeholder="Confirm Password"
            v-model.trim="$v.confirmPassword.$model"
            :class="{ 'is-invalid': submitted && $v.confirmPassword.$error }"
          />
          <div class="input-group-append">
            <span class="input-group-text">
              <i
                class="mdi mdi-check-circle-outline"
                :class="{
                  'text-danger': submitted && $v.confirmPassword.$error,
                }"
              ></i>
            </span>
          </div>
          <div
            v-if="submitted && $v.confirmPassword.$error"
            class="invalid-feedback left"
          >
            <span v-if="!$v.confirmPassword.required"
              >Confirm Password is required</span
            >
            <span v-if="confirmPassword && !$v.confirmPassword.sameAsPassword"
              >Password and Confirm Password should match</span
            >
          </div>
        </div>
      </div>
      <br />
      <div class="form-group">
        <button
          class="btn btn-primary submit-btn btn-block"
          :disabled="loading"
        >
          Change Password
          <span
            class="spinner-border spinner-border-sm"
            v-show="loading"
          ></span>
        </button>
      </div>
    </b-form>
  </div>
</template>

<script>
import { validationMixin } from "vuelidate";
import { required, email, minLength, sameAs } from "vuelidate/lib/validators";

import { authService } from "../../services";
export default {
  mixins: [validationMixin],
  name: "change",
  data() {
    return {
      email: "",
      password: "",
      confirmPassword: "",
      submitted: false,
      loading: false,
      error: "",
      success: "",
      isError: false,
      isSuccess: false,
    };
  },
  validations: {
    email: { required, email },
    password: { required, minLength: minLength(6) },
    confirmPassword: { required, sameAsPassword: sameAs("password") },
  },
  methods: {
    async changePassword() {
      this.submitted = true;
      this.loading = true;
      // stop here if form is invalid
      this.$v.$touch();
      if (this.$v.$invalid) {
        return;
      }

      const { data } = await authService.resetPassword(
        this.email,
        this.password,
        this.$route.params.resetToken
      );

      if (data && data.status == 401) {
        this.error = data.message;
        this.isError = true;
        this.loading = false;
      } else if (data && data.status == 400) {
        this.error = data.message;
        this.isError = true;
        this.loading = false;
      } else {
        // this.success = data.message;
        // this.isSuccess = true;
        this.loading = false;
        setTimeout(() => this.$router.push({ path: "/auth/login" }), 5000);
      }
    },
  },
};
</script>

<style lang="scss" scoped></style>
