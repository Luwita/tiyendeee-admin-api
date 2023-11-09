<template>
  <div>
    <b-form @submit.prevent="updatePayment">
      <input type="hidden" v-model="form.payments.id" />
      <b-form-group
        label="Is production"
        label-for="is_production-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-form-radio-group
          :options="statusOptions"
          v-model.trim="form.payments.is_production"
        ></b-form-radio-group>
      </b-form-group>

      <b-form-group
        label="Razorpay Logo"
        label-for="logo-input"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <div v-if="!form.payments.logo">
          <b-form-file
            id="payment-input"
            accept="image/jpeg, image/png, image/jpg"
            placeholder="Choose a logo or drop it here..."
            @change="onFileChange($event, 'logo')"
          ></b-form-file>
        </div>
        <div v-else>
          <img
            class="img-fluid"
            :src="form.payments.logo"
            width="150"
            height="150"
          />
          <button
            class="btn social-btn btn-rounded btn-danger mr-4"
            @click="removeImage('logo')"
          >
            <i class="mdi mdi-close"></i>
          </button>
        </div>
      </b-form-group>

      <b-form-group
        label="Razorpay Key"
        label-for="key-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input v-model="form.payments.key" placeholder="Razorpay key">
        </b-input>
      </b-form-group>

      <b-form-group
        label="Razorpay Secret"
        label-for="secret-input"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input v-model="form.payments.secret" placeholder="Razorpay secret">
        </b-input>
      </b-form-group>

      <b-form-group
        label="Razorpay Text"
        label-for="text-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input
          v-model="form.payments.text_name"
          placeholder="Pay via TheFerri"
        >
        </b-input>
      </b-form-group>

      <b-form-group
        label="Payment Capture"
        label-for="payment-capture-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-form-radio-group
          :options="paymentCaptureOptions"
          v-model.trim="form.payments.payment_capture"
        ></b-form-radio-group>
      </b-form-group>

      <b-form-group
        label="Theme Color"
        label-for="theme-color-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <color-panel v-model="form.payments.theme_color"></color-panel>
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
        <b-input v-model="form.payments.email" placeholder="email address">
        </b-input>
      </b-form-group>

      <b-form-group
        label="Name"
        label-for="name-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input v-model="form.payments.name" placeholder="name"> </b-input>
      </b-form-group>

      <b-form-group
        label="Contact"
        label-for="contact-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input v-model="form.payments.contact" placeholder="contact">
        </b-input>
      </b-form-group>

      <b-form-group
        label="Currency"
        label-for="currency-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input v-model="form.payments.currency" placeholder="currency">
        </b-input>
      </b-form-group>

      <b-form-group class="col-md-6 offset-md-4">
        <b-button
          type="submit"
          class="btn btn-success btn-lg text-center"
          disabled
          >Submit</b-button
        >
      </b-form-group>
    </b-form>
  </div>
</template>

<script>
import { settingService } from "../../services";

export default {
  name: "payment",
  data() {
    return {
      statusOptions: [
        { text: "Activated", value: true },
        { text: "Deactivated", value: false },
      ],
      paymentCaptureOptions: [
        { text: "Active", value: true },
        { text: "InActive", value: false },
      ],
      form: {
        type: "payments",
        payments: {
          id: "",
          is_production: "",
          key: "",
          secret: "",
          logo: "",
          text_name: "",
          payment_capture: "",
          theme_color: "", //#59c7f9
          email: "",
          name: "",
          contact: "",
          currency: "",
        },
      },
    };
  },
  methods: {
    async getSetting() {
      try {
        const response = await settingService.find("payments");
        if (response.status) {
          this.form.payments = response.data;
        }
      } catch (e) {
        this.$toast.open({
          message: e,
          type: "error",
          position: "top-right",
          duration: 5000,
        });
      }
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
        vm.form.payments[fileTitle] = e.target.result;
      };
      reader.readAsDataURL(file);
    },
    removeImage: function (titlename) {
      this.form.payments[titlename] = "";
    },
    updateColor(eventData) {
      this.form.payments.theme_color = eventData.cssColor;
    },
    async updatePayment() {
      try {
        this.submitted = true;
        const response = await settingService.update(
          this.form.payments.id,
          this.form
        );
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
