<template>
  <div>
    <b-form @submit.prevent="updateSMTP">
      <input type="hidden" v-model="form.smtp.id" />
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
          v-model.trim="form.smtp.is_production"
        ></b-form-radio-group>
      </b-form-group>

      <b-form-group
        label="Email Engine"
        label-for="email-engine-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-form-radio-group
          :options="engineOptions"
          v-model.trim="form.smtp.type"
        ></b-form-radio-group>
      </b-form-group>

      <b-form-group
        label="Host"
        label-for="host-input"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input v-model="form.smtp.host" placeholder="Host"> </b-input>
      </b-form-group>

      <b-form-group
        label="Port"
        label-for="port-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input v-model="form.smtp.port" placeholder="port"> </b-input>
      </b-form-group>

      <b-form-group
        label="Encryption"
        label-for="encryption-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input v-model="form.smtp.encryption" placeholder="Encryption">
        </b-input>
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
        <b-input v-model="form.smtp.username" placeholder="username"> </b-input>
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
        <b-input v-model="form.smtp.password" placeholder="password"> </b-input>
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
        <b-input v-model="form.smtp.name" placeholder="Name"> </b-input>
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
        <b-input v-model="form.smtp.email" placeholder="Email Address">
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
  name: "smtp",
  data() {
    return {
      statusOptions: [
        { text: "Activated", value: true },
        { text: "Deactivated", value: false },
      ],
      engineOptions: [
        { text: "SMTP", value: "SMTP" },
        { text: "SENDMAIL", value: "SENDMAIL" },
      ],
      form: {
        type: "smtp",
        smtp: {
          id: "",
          is_production: "",
          type: "",
          username: "",
          host: "",
          port: "",
          encryption: "",
          password: "",
          name: "",
          email: "",
        },
      },
    };
  },
  methods: {
    async getSetting() {
      try {
        const response = await settingService.find("smtp");
        if (response.status) {
          this.form.smtp = response.data;
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
    async updateSMTP() {
      try {
        this.submitted = true;
        const response = await settingService.update(
          this.form.smtp.id,
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
