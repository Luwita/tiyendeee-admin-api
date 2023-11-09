<template>
  <div>
    <b-container>
      <b-row>
        <b-col cols="11" offset-lg="1">
          <b-form ref="form" @submit.stop.prevent="updateSubmit">
            <b-form-group
              label="First Name"
              label-for="firstname-input"
              invalid-feedback="First Name is required"
              label-cols-sm="4"
              label-cols-lg="3"
              content-cols-sm
              content-cols-lg="7"
            >
              <b-form-input
                id="firstname-input"
                v-model="form.firstname"
                required
              ></b-form-input>
            </b-form-group>

            <b-form-group
              label="Last Name"
              label-for="lastname-input"
              invalid-feedback="Last Name is required"
              label-cols-sm="4"
              label-cols-lg="3"
              content-cols-sm
              content-cols-lg="7"
            >
              <b-form-input
                id="lastname-input"
                v-model="form.lastname"
                required
              ></b-form-input>
            </b-form-group>

            <b-form-group
              label="Email Address"
              label-for="email-input"
              invalid-feedback="Email address is required"
              label-cols-sm="4"
              label-cols-lg="3"
              content-cols-sm
              content-cols-lg="7"
            >
              <b-form-input
                id="email-input"
                type="email"
                v-model="form.email"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group
              label="Phone "
              label-for="phone-input"
              invalid-feedback="Phone is required"
              class="mt-3"
              label-cols-sm="4"
              label-cols-lg="3"
              content-cols-sm
              content-cols-lg="7"
            >
              <VuePhoneNumberInput
                :default-country-code="'IN'"
                :required="true"
                v-model="form.phone"
                :no-example="true"
                id="phone-input"
              />
            </b-form-group>

            <b-form-group
              label="Status "
              label-for="status-input"
              invalid-feedback="status is required"
              class="mt-3"
              label-cols-sm="4"
              label-cols-lg="3"
              content-cols-sm
              content-cols-lg="7"
            >
              <b-form-radio-group
                v-model="form.status"
                :options="options"
                name="radio-inline"
              ></b-form-radio-group>
            </b-form-group>
            <p class="card-description fw-500"><b>Documents</b></p>
            <br />
            <b-form-group
              label="Profile picture"
              label-for="picture-input"
              invalid-feedback="profile is required"
              class="mt-3"
              label-cols-sm="4"
              label-cols-lg="3"
              content-cols-sm
              content-cols-lg="7"
            >
              <b-form-file
                accept="image/jpeg, image/png, image/gif"
                placeholder="Choose a file or drop it here..."
              ></b-form-file>
              <b-img :src="form.picture"></b-img>
            </b-form-group>

            <b-form-group
              label="Licence "
              label-for="licence-input"
              invalid-feedback="licence is required"
              class="mt-3"
              label-cols-sm="4"
              label-cols-lg="3"
              content-cols-sm
              content-cols-lg="7"
            >
              <b-form-file
                accept="image/jpeg, image/png, image/gif"
                placeholder="Choose a licence or drop it here..."
              ></b-form-file>
              <b-img
                :src="form.document_licence"
                width="75"
                height="80"
              ></b-img>
            </b-form-group>

            <b-form-group
              label="Adhar Card "
              label-for="adhar-card-input"
              invalid-feedback="Adhar card is required"
              class="mt-3"
              label-cols-sm="4"
              label-cols-lg="3"
              content-cols-sm
              content-cols-lg="7"
            >
              <b-form-file
                accept="image/jpeg, image/png, image/gif"
                placeholder="Choose a adhar card or drop it here..."
              ></b-form-file>
              <b-img
                width="75"
                height="80"
                :src="form.document_adhar_card"
              ></b-img>
            </b-form-group>

            <b-form-group
              label="Adhar Card "
              label-for="adhar-card-input"
              invalid-feedback="Adhar card is required"
              class="mt-3"
              label-cols-sm="4"
              label-cols-lg="3"
              content-cols-sm
              content-cols-lg="7"
            >
              <b-form-file
                accept="image/jpeg, image/png, image/gif"
                placeholder="Choose a adhar card or drop it here..."
              ></b-form-file>
              <b-img
                width="75"
                height="80"
                :src="form.document_police_vertification"
              ></b-img>
            </b-form-group>
          </b-form>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import VuePhoneNumberInput from "vue-phone-number-input";
import "vue-phone-number-input/dist/vue-phone-number-input.css";
export default {
  name: "driverForm",
  props: ["form"],
  data() {
    return {
      imageProps: { blank: true, width: 75, height: 75, class: "m1" },
      options: [
        { text: "Active", value: "true" },
        { text: "Inactive", value: "false" },
      ],
    };
  },
  components: { VuePhoneNumberInput },
  methods: {
    updateSubmit() {},
  },
};
</script>

<style lang="scss" scoped></style>
