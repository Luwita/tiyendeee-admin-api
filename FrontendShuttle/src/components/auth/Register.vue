<template>
  <div>
    <b-form @submit.prevent="registerUser">
      <h3 class="mr-auto">Register</h3>
      <p class="mb-5 mr-auto">Enter your details below.</p>
      <div class="form-group">
        <div class="input-group">
          <div class="input-group-prepend">
            <span class="input-group-text">
              <i class="mdi mdi-account-outline"></i>
            </span>
          </div>
          <input
            type="text"
            id="firstname"
            class="form-control"
            placeholder="Firstname"
            v-model="register.firstname"
            autocomplete="off"
          />
        </div>
      </div>
      <div class="form-group">
        <div class="input-group">
          <div class="input-group-prepend">
            <span class="input-group-text">
              <i class="mdi mdi-account-outline"></i>
            </span>
          </div>
          <input
            type="text"
            id="lastname"
            class="form-control"
            placeholder="Lastname"
            v-model="register.lastname"
            autocomplete="off"
          />
        </div>
      </div>
      <div class="form-group">
        <div class="input-group">
          <div class="input-group-prepend">
            <span class="input-group-text">
              <i class="mdi mdi-email-outline"></i>
            </span>
          </div>
          <input
            type="email"
            id="email"
            class="form-control"
            placeholder="Email Address"
            v-model="register.email"
            autocomplete="off"
          />
        </div>
      </div>
      <div class="form-group">
        <VuePhoneNumberInput
          :default-country-code="'IN'"
          :required="true"
          v-model="register.phone"
          :no-example="true"
        />
      </div>
      <div class="form-group">
        <div class="input-group">
          <div class="input-group-prepend">
            <span class="input-group-text">
              <i class="mdi mdi-lock-outline"></i>
            </span>
          </div>
          <input
            type="password"
            id="password"
            class="form-control"
            placeholder="Password"
            v-model="register.password"
            autocomplete="off"
          />
        </div>
      </div>
      <div class="form-group">
        <div class="input-group">
          <div class="input-group-prepend">
            <span class="input-group-text">
              <i class="mdi mdi-lock-outline"></i>
            </span>
          </div>
          <input
            type="password"
            id="confirm-password"
            class="form-control"
            placeholder="Confirm Password"
            v-model="register.confirm_password"
            autocomplete="off"
          />
        </div>
      </div>
      <div class="form-group">
        <button class="btn btn-primary submit-btn">SIGN IN</button>
      </div>
      <div class="wrapper mt-5 text-gray">
        <p class="footer-text">
          Copyright © 2019 Bootstrapdash. All rights reserved.
        </p>
        <ul class="auth-footer text-gray">
          <li>
            <router-link to="/terms">Terms & Conditions</router-link>
          </li>
          <li>
            <router-link :to="{ name: 'policy' }">Cookie Policy</router-link>
          </li>
        </ul>
      </div>
    </b-form>
  </div>
</template>

<script>
import VuePhoneNumberInput from "vue-phone-number-input";
import "vue-phone-number-input/dist/vue-phone-number-input.css";
import { mapActions } from "vuex";

export default {
  name: "register",
  data() {
    return {
      register: {
        firstname: "",
        lastname: "",
        phone: "",
        email: "",
        password: "",
        confirm_password: "",
      },
      showError: false,
    };
  },
  components: {
    VuePhoneNumberInput,
  },
  methods: {
    ...mapActions(["Register"]),
    async registerUser() {
      try {
        console.log("this.register", this.register);
        const formData = {
          firstname: this.register.firstname,
          lastname: this.register.lastname,
          phone: this.register.phone.replace(/\s/g, ""),
          email: this.register.email,
          password: this.register.password,
        };

        await this.Register(formData);
        this.$toast.success("Registration Was successful!");
        this.$router.push("/auth/login");
      } catch (err) {
        this.showError = true;
        this.$vToastiy.error("Something Went Wrong");
        console.log("Error", "Something Went Wrong", "error");
        console.log(err);
      }
    },
  },
};
</script>

<style lang="scss" scoped></style>
