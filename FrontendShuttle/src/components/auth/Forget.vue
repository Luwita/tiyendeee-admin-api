<template>
  <div>
    <form method="POST" @submit.prevent="forgetPassword">
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
          <!-- <div class="input-group-append">
            <span class="input-group-text">
              <i
                class="mdi mdi-check-circle-outline"
                :class="{ 'text-danger': submitted && $v.email.$error }"
              ></i>
            </span>
          </div> -->
          <div
            v-if="submitted && !$v.email.required"
            class="text-small invalid-feedback"
          >
            Email address is required
          </div>
        </div>
      </div>
      <br />
      <div class="form-group">
        <button
          class="btn btn-primary submit-btn btn-block"
          :disabled="loading"
        >
          Submit
          <span
            class="spinner-border spinner-border-sm"
            v-show="loading"
          ></span>
        </button>
      </div>
      <div v-if="error" class="alert alert-danger alert-sm">{{ error }}</div>
      <div v-if="success" class="alert alert-success alert-sm">
        {{ success }}
      </div>
    </form>
  </div>
</template>

<script>
import { validationMixin } from "vuelidate";
import { required, email } from "vuelidate/lib/validators";

import { authService } from "../../services";

export default {
  mixins: [validationMixin],
  name: "forget-password",
  data() {
    return {
      email: "",
      submitted: false,
      loading: false,
      error: "",
      success: "",
    };
  },
  validations: {
    email: { required, email },
  },
  methods: {
    async forgetPassword() {
      this.submitted = true;

      // stop here if form is invalid
      this.$v.$touch();
      if (this.$v.$invalid) {
        return;
      }
      const response = await authService.sendPasswordRest(this.email);
      if (response.status == 401) {
        this.error = response.message;
        this.loading = false;
      } else {
        this.success = response.message;
        this.loading = false;
      }
    },
  },
};
</script>

<style lang="scss" scoped></style>
