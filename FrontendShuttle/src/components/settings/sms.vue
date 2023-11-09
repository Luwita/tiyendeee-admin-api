<template>
  <div>
    <b-form @submit.prevent="updateSMS">
      <input type="hidden" v-model="form.sms.id" />
      <b-form-group
        label="Is production"
        label-for="status-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-form-radio-group
          :options="statusOptions"
          v-model.trim="form.sms.is_production"
        ></b-form-radio-group>
      </b-form-group>

      <b-form-group
        label="Sender ID"
        label-for="senderId-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input v-model="form.sms.senderId" placeholder="senderId"> </b-input>
      </b-form-group>

      <b-form-group
        label="Username"
        label-for="username-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input v-model="form.sms.username" placeholder="username"> </b-input>
      </b-form-group>

      <b-form-group
        label="Password"
        label-for="password-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input v-model="form.sms.password" placeholder="password"> </b-input>
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
  name: "sms",
  data() {
    return {
      statusOptions: [
        { text: "Activated", value: true },
        { text: "Deactivated", value: false },
      ],
      form: {
        type: "sms",
        sms: {
          id: "",
          is_production: "",
          senderId: "",
          username: "",
          password: "",
        },
      },
    };
  },
  methods: {
    async getSetting() {
      try {
        const response = await settingService.find("sms");
        if (response.status) {
          this.form.sms = response.data;
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
    async updateSMS() {
      try {
        this.submitted = true;
        const response = await settingService.update(
          this.form.sms.id,
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
