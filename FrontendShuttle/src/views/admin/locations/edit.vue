<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-8 offset-2 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <b-form @submit.prevent="updateLocation">
                <b-form-group
                  label="Stop Name"
                  label-for="title-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="title-input"
                    v-model.trim="$v.form.title.$model"
                    type="text"
                    placeholder="Enter title "
                    :class="{
                      'is-invalid': submitted && $v.form.title.$error,
                    }"
                    :state="validateState('title')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.title.required"
                    class="invalid-feedback"
                  >
                    title is required
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
                  <gmap-autocomplete
                    class="form-control"
                    placeholder="Start typing address"
                    v-model.trim="$v.form.address.$model"
                    @place_changed="getAddressData"
                    :state="validateState('address')"
                    :class="{
                      'is-invalid': submitted && $v.form.address.$error,
                    }"
                  >
                  </gmap-autocomplete>
                  <b-form-invalid-feedback
                    v-if="submitted && !$v.form.address.required"
                    >Address is a required field.</b-form-invalid-feedback
                  >
                </b-form-group>
                <!-- <b-form-group
                  label="Latitude"
                  label-for="latitude-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="lat-input"
                    v-model.trim="$v.form.lat.$model"
                    placeholder="Enter latitude "
                    :class="{
                      'is-invalid': submitted && $v.form.lat.$error,
                    }"
                    :state="validateState('lat')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.lat.required"
                    class="invalid-feedback"
                  >
                    latitude is required
                  </div>
                </b-form-group>

                <b-form-group
                  label="Longitude"
                  label-for="longitude-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="lat-input"
                    v-model.trim="$v.form.lng.$model"
                    placeholder="Enter longitude "
                    :class="{
                      'is-invalid': submitted && $v.form.lng.$error,
                    }"
                    :state="validateState('lng')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.lat.required"
                    class="invalid-feedback"
                  >
                    longitude is required
                  </div>
                </b-form-group> -->
                <b-form-group
                  label="Type"
                  label-for="type-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-select
                    v-model.trim="$v.form.type.$model"
                    class="mb-3"
                    :state="validateState('type')"
                  >
                    <template #first>
                      <b-form-select-option :value="null" disabled
                        >-- Please select an type --</b-form-select-option
                      >
                    </template>

                    <!-- These options will appear after the ones from 'options' prop -->
                    <b-form-select-option value="DA"
                      >Departure / Arrival location</b-form-select-option
                    >
                    <b-form-select-option value="PD"
                      >Pick-up / Drop off location</b-form-select-option
                    >
                  </b-form-select>
                  <b-form-invalid-feedback
                    v-if="submitted && !$v.form.type.required"
                    >Type is a required field.</b-form-invalid-feedback
                  >
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
                    :options="options"
                    v-model.trim="$v.form.status.$model"
                    :state="validateState('status')"
                  ></b-form-radio-group>

                  <!-- <b-form-invalid-feedback
                  v-if="submitted && !$v.form.status.required"
                  >Please select one</b-form-invalid-feedback
                > -->
                </b-form-group>

                <b-form-group>
                  <b-button type="submit" class="btn btn-success text-center"
                    >Submit</b-button
                  >
                </b-form-group>
              </b-form>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import Breadcrumb from "../../../components/breadcrumb";
import { validationMixin } from "vuelidate";
import { required } from "vuelidate/lib/validators";
import { locationService } from "../../../services";

export default {
  mixins: [validationMixin],
  name: "modeedit",
  data() {
    return {
      breadcrumbs: {
        title: "Edit Stop",
        b1: "Fixed Routes",
        b2: "stops",
        b3: "Index",
        link: true,
        name: "stops",
      },
      options: [
        { text: "Active", value: "true" },
        { text: "Inactive", value: "false", default: true },
      ],
      form: {
        id: "",
        title: "",
        address: "",
        lat: "",
        lng: "",
        type: null,
        status: "",
      },
      submitted: false,
      loading: false,
    };
  },
  validations: {
    form: {
      title: { required },
      address: { required },
      type: { required },
      status: { required },
    },
  },
  components: {
    Breadcrumb,
  },
  methods: {
    validateState(name) {
      const { $dirty, $error } = this.$v.form[name];
      return $dirty ? !$error : null;
    },
    getAddressData: function (addressData) {
      this.form.address = addressData.formatted_address;
      this.form.lat = addressData.geometry.location.lat();
      this.form.lng = addressData.geometry.location.lng();
    },
    async getLocation() {
      try {
        const response = await locationService.find(this.$route.params.id);
        if (response.status) {
          this.form = response.data;
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
    async updateLocation() {
      try {
        this.submitted = true;
        // stop here if form is invalid
        this.$v.$touch();
        if (this.$v.$invalid) {
          return;
        }

        const response = await locationService.update(
          this.$route.params.id,
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
          setTimeout(
            () =>
              this.$router.push({
                path: `/locations`,
              }),
            2000
          );
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
  },
  mounted() {
    this.getLocation();
  },
};
</script>

<style></style>
