<template>
  <div>
    <b-form @submit.prevent="updateAWS">
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
          v-model.trim="form.s3.is_production"
        ></b-form-radio-group>
      </b-form-group>

      <b-form-group
        label="Access ID"
        label-for="access-ID-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input
          v-model="form.s3.access_key"
          placeholder="access ID"
          :class="{
            'is-invalid': submitted && $v.form.s3.access_key.$error,
          }"
          :state="validateState('access_key')"
        >
        </b-input>
        <div
          v-if="submitted && !$v.form.s3.access_key.required"
          class="invalid-feedback"
        >
          access_key is required
        </div>
      </b-form-group>

      <b-form-group
        label="Secret Key"
        label-for="secret-key-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input
          v-model="form.s3.secret_key"
          placeholder="secret key"
          :class="{
            'is-invalid': submitted && $v.form.s3.secret_key.$error,
          }"
          :state="validateState('secret_key')"
        >
        </b-input>
        <div
          v-if="submitted && !$v.form.s3.secret_key.required"
          class="invalid-feedback"
        >
          secret_key is required
        </div>
      </b-form-group>

      <b-form-group
        label="Region"
        label-for="region-input"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input v-model="form.s3.region" placeholder="region"> </b-input>
      </b-form-group>

      <b-form-group
        label="Bucket Name"
        label-for="bucket-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input
          v-model="form.s3.bucket"
          placeholder="Bucket name"
          :class="{
            'is-invalid': submitted && $v.form.s3.bucket.$error,
          }"
          :state="validateState('bucket')"
        >
        </b-input>
        <div
          v-if="submitted && !$v.form.s3.bucket.required"
          class="invalid-feedback"
        >
          bucket is required
        </div>
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
import { validationMixin } from "vuelidate";
import { required } from "vuelidate/lib/validators";
export default {
  mixins: [validationMixin],
  name: "aws",
  data() {
    return {
      submitted: false,
      statusOptions: [
        { text: "Activated", value: true },
        { text: "Deactivated", value: false },
      ],
      form: {
        type: "aws",
        s3: {
          id: "",
          is_production: false,
          access_key: "",
          secret_key: "",
          region: "",
          bucket: "",
        },
      },
    };
  },
  validations: {
    form: {
      s3: {
        access_key: { required },
        secret_key: { required },
        bucket: { required },
      },
    },
  },
  methods: {
    validateState(name) {
      const { $dirty, $error } = this.$v.form.s3[name];
      return $dirty ? !$error : null;
    },
    async getSetting() {
      try {
        const response = await settingService.find("aws");
        if (response.status) {
          this.form.s3 = response.data;
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
    async updateAWS() {
      try {
        this.submitted = true;

        // stop here if form is invalid
        this.$v.$touch();
        if (this.$v.$invalid) {
          return;
        }
        const response = await settingService.update(
          this.form.s3.id,
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
