<template>
  <div>
    <b-form @submit.prevent="updateGeneral">
      <input type="hidden" v-model="general.id" />
      <b-form-group
        label="Name"
        label-for="name-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input
          v-model.trim="$v.general.name.$model"
          placeholder="name"
          :class="{
            'is-invalid': submitted && $v.general.name.$error,
          }"
          :state="validateState('name')"
        >
        </b-input>
        <div
          v-if="submitted && !$v.general.name.required"
          class="invalid-feedback"
        >
          name is required
        </div>
      </b-form-group>

      <b-form-group
        label="Logo"
        label-for="general-input"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <div v-if="!general.logo">
          <b-form-file
            id="general-input"
            accept="image/jpeg, image/png, image/jpg"
            placeholder="Choose a logo or drop it here..."
            @change="onFileChange($event, 'logo')"
          ></b-form-file>
        </div>
        <div v-else>
          <img class="img-fluid" :src="general.logo" width="150" height="150" />
          <button
            class="btn social-btn btn-rounded btn-danger mr-4"
            @click="removeImage('logo')"
          >
            <i class="mdi mdi-close"></i>
          </button>
        </div>
      </b-form-group>

      <b-form-group
        label="Email"
        label-for="email-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input
          v-model.trim="$v.general.email.$model"
          placeholder="Enter email address"
          :class="{
            'is-invalid': submitted && $v.general.email.$error,
          }"
          :state="validateState('email')"
        >
        </b-input>
        <div
          v-if="submitted && !$v.general.email.required"
          class="invalid-feedback"
        >
          email address is required
        </div>
      </b-form-group>

      <b-form-group
        label="Address"
        label-for="address-input"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-form-textarea
          id="address-1-input"
          v-model.trim="general.address"
          placeholder="Enter address..."
          rows="5"
          max-rows="8"
        ></b-form-textarea>
      </b-form-group>

      <b-form-group
        label="Phone Number"
        label-for="phone-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input
          v-model.trim="$v.general.phone.$model"
          placeholder="phone Number"
          :class="{
            'is-invalid': submitted && $v.general.phone.$error,
          }"
          :state="validateState('phone')"
        >
        </b-input>

        <div
          v-if="submitted && !$v.general.phone.required"
          class="invalid-feedback"
        >
          phone number is required
        </div>
        <div class="invalid-feedback" v-if="!$v.general.phone.minLength">
          Name must have at least
          {{ $v.general.phone.$params.minLength.min }} letters.
        </div>
      </b-form-group>

      <b-form-group
        label="Google Key"
        label-for="googlekey-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input v-model="general.google_key" placeholder="google secret key">
        </b-input>
      </b-form-group>

      <b-form-group
        label="Tax (%)"
        label-for="tax-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input
          v-model.trim="$v.general.tax.$model"
          placeholder="Enter tax percent number"
          :class="{
            'is-invalid': submitted && $v.general.tax.$error,
          }"
          :state="validateState('tax')"
        >
        </b-input>

        <div
          v-if="submitted && !$v.general.tax.required"
          class="invalid-feedback"
        >
          tax is required
        </div>
        <div class="invalid-feedback" v-if="!$v.general.tax.minLength">
          Name must have at least
          {{ $v.general.tax.$params.minLength.min }} letters.
        </div>
      </b-form-group>

      <b-form-group
        label="Fee"
        label-for="fee-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input
          type="number"
          min="0"
          step="0.01"
          pattern="^\d+(?:\.\d{1,2})?$"
          v-model.trim="general.fee"
          placeholder="Enter fee"
        >
        </b-input>
      </b-form-group>

      <b-form-group class="col-md-6 offset-md-4">
        <b-button type="submit" class="btn btn-success btn-lg text-center"
          >Submit</b-button
        >
      </b-form-group>
    </b-form>
  </div>
</template>

<script>
import { settingService } from "../../services";
import { validationMixin } from "vuelidate";
import { required, numeric, email, minLength } from "vuelidate/lib/validators";

export default {
  mixins: [validationMixin],
  name: "general",
  data() {
    return {
      submitted: false,
      general: {
        id: "",
        name: "",
        logo: "",
        email: "",
        address: "",
        phone: "",
        google_key: "",
        tax: "",
        fee: "",
      },
    };
  },
  validations: {
    general: {
      name: { required },
      email: { required, email },
      phone: { numeric, minLength: minLength(10) },
      tax: { numeric, minLength: minLength(0) },
    },
  },
  methods: {
    validateState(name) {
      const { $dirty, $error } = this.$v.general[name];
      return $dirty ? !$error : null;
    },
    onFileChange(e, fileTitle) {
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length) return;
      this.createImage(files[0], fileTitle);
    },
    createImage(file, fileTitle) {
      // var picture = new Image();
      var reader = new FileReader();
      var vm = this;

      reader.onload = (e) => {
        vm.general[fileTitle] = e.target.result;
      };
      reader.readAsDataURL(file);
    },
    removeImage: function (titlename) {
      this.general[titlename] = "";
    },
    async getSetting() {
      try {
        const response = await settingService.find("general");
        if (response.status) {
          this.general = response.data;
          // this.general.google_key = response.data.google_key.replace(
          //   /[a-zA-Z]/g,
          //   "X"
          // );
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
    async updateGeneral() {
      try {
        this.submitted = true;

        // stop here if form is invalid
        this.$v.$touch();
        if (this.$v.$invalid) {
          return;
        }
        const response = await settingService.update(this.general.id, {
          type: "general",
          general: this.general,
        });
        if (response.status) {
          this.$toast.open({
            message: response.message,
            type: "success",
            position: "top-right",
            duration: 2000,
            // all of other options may go here
          });
        }
      } catch (e) {
        this.$toast.open({
          message: e,
          type: "error",
          position: "top-right",
          duration: 4000,
        });
      }
    },
  },
  mounted() {
    this.getSetting();
  },
};
</script>

<style lang="scss" scoped></style>
