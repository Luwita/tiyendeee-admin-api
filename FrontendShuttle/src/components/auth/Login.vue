<template>
  <div>
    <b-form class="pt-3" @submit.stop.prevent="loginUser">
      <b-form-group label="Email Address" label-for="email-input-1">
        <b-form-input
          id="email-input-1"
          type="email"
          name="email-input-1"
          v-model="$v.form.email.$model"
          placeholder="Enter email Address"
          :state="validateState('email')"
          aria-describedby="input-email-live-feedback"
        ></b-form-input>

        <b-form-invalid-feedback
          class="text-small"
          id="input-email-live-feedback"
          >Email Address is a required.</b-form-invalid-feedback
        >
      </b-form-group>
      <b-form-group label="Password" label-for="password-input-1">
        <b-form-input
          id="password-input-1"
          name="password-input-1"
          type="password"
          placeholder="************"
          v-model="$v.form.password.$model"
          :state="validateState('password')"
          aria-describedby="input-password-live-feedback"
        ></b-form-input>

        <b-form-invalid-feedback
          class="text-small"
          id="input-password-live-feedback"
          >Password is a required.</b-form-invalid-feedback
        >
      </b-form-group>

      <b-form-group>
        <button type="submit" class="btn btn-primary submit-btn btn-block">
          Login
          <span
            class="pl-2 spinner-border spinner-border-sm"
            v-show="loading"
          ></span>
        </button>
      </b-form-group>
      <div class="my-2 d-flex justify-content-between align-items-center">
        <router-link
          :to="{ name: 'forget' }"
          class="text-small forgot-password text-black mr-3"
        >
          Forgot Password ?</router-link
        >
      </div>
    </b-form>
    <!-- <form method="POST">
      <div class="form-group">
        <label class="label">Email Address</label>
        <div class="input-group">
          <input
            type="email"
            id="email"
            class="form-control"
            placeholder="Email Address"
            v-model="email"
            required
          />
          <div class="input-group-append">
            <span class="input-group-text">
              <i class="mdi mdi-check-circle-outline"></i>
            </span>
          </div>
        </div>
      </div>
      <div class="form-group">
        <label class="label">Password</label>
        <div class="input-group">
          <input
            type="password"
            id="password"
            class="form-control"
            placeholder="*********"
            v-model="password"
            required
          />
          <div class="input-group-append">
            <span class="input-group-text">
              <i class="mdi mdi-check-circle-outline"></i>
            </span>
          </div>
        </div>
      </div>

      <button class="btn btn-primary submit-btn btn-block">Login</button> 
      <b-overlay
        :show="busy"
        rounded
        opacity="0.6"
        spinner-small
        spinner-variant="primary"
        class="d-inline-block float-right mb-4"
        @hidden="onHidden"
      >
        <router-link
          :to="{ name: 'forget' }"
          class="text-small forgot-password text-black mr-3"
        >
          Forgot Password ?</router-link
        >
      </b-overlay>

      <div class="form-group d-flex justify-content-between">
        <div class="form-check form-check-flat mt-0"></div>
      </div>
    </form> -->
  </div>
</template>

<script>
import { validationMixin } from "vuelidate";
import { required, email } from "vuelidate/lib/validators";
import { mapActions } from "pinia";
import { useAuth } from "../../store/useAuth.js";

export default {
  name: "login",
  mixins: [validationMixin],
  data() {
    return {
      form: {
        email: null,
        passsword: null,
      },
      busy: false,
      timeout: null,
      submitted: false,
      loading: false,
    };
  },

  beforeDestroy() {
    this.clearTimeout();
  },
  computed: {
    // loggingIn() {
    //   return this.$store.state.auth.status.loggingIn;
    // },
  },
  created() {
    // this.$store.dispatch("auth/logout");
  },
  validations: {
    form: {
      email: { required, email },
      password: { required },
    },
  },
  methods: {
    ...mapActions(useAuth, ["UserLogin"]),
    validateState(name) {
      const { $dirty, $error } = this.$v.form[name];
      return $dirty ? !$error : null;
    },
    clearTimeout() {
      if (this.timeout) {
        clearTimeout(this.timeout);
        this.timeout = null;
      }
    },
    setTimeout(callback) {
      this.clearTimeout();
      this.timeout = setTimeout(() => {
        this.clearTimeout();
        callback();
      }, 2000);
    },
    onHidden() {
      // Return focus to the button once hidden
      this.$refs.button.focus();
    },
    async loginUser() {
      this.submitted = true;
      this.loading = true;
      this.$v.form.$touch();
      if (this.$v.form.$anyError) {
        this.loading = false;
        return;
      }

      const { email, password } = this.form;
      //  const { dispatch } = this.$store;

      if (email && password) {
        await this.UserLogin(email, password);
        // dispatch("auth/login", { email, password }).then(
        //   () => {
        //     this.$router.push("/dashboard");
        //   },
        //   (error) => {
        //     this.loading = false;
        //     this.message =
        //       (error.response &&
        //         error.response.data &&
        //         error.response.data.message) ||
        //       error.message ||
        //       error.toString();
        //   }
        // );
        //dispatch("auth/login", { email, password });
        this.busy = true;
        // Simulate an async request
        this.setTimeout(() => {
          this.busy = false;
        });
      }
    },
  },
};
</script>

<style lang="scss" scoped></style>
