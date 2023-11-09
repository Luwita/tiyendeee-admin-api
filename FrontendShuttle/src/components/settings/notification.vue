<template>
  <div>
    <b-form @submit.prevent="updateNotification">
      <input type="hidden" v-model="form.notifications.id" />

      <b-form-group
        label="Customer Secret Key"
        label-for="customer-secret-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-textarea
          v-model="form.notifications.customer_secret_key"
          placeholder="Customer secret key"
          rows="5"
          cols="10"
        >
        </b-textarea>
      </b-form-group>

      <b-form-group
        label="Driver Secret Key"
        label-for="driver-secret-key-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-textarea
          v-model="form.notifications.driver_secret_key"
          placeholder="Driver secret key"
          rows="5"
          cols="10"
        >
        </b-textarea>
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
  name: "notification",
  data() {
    return {
      form: {
        type: "notifications",
        notifications: {
          id: "",
          customer_secret_key: "",
          driver_secret_key: "",
        },
      },
    };
  },
  methods: {
    async getSetting() {
      try {
        const response = await settingService.find("notifications");
        if (response.status) {
          this.form.notifications = response.data;
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
    async updateNotification() {
      try {
        this.submitted = true;
        const response = await settingService.update(
          this.form.notifications.id,
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
