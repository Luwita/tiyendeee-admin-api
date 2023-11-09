<template>
  <section class="tables">
    <div class="row">
      <!-- <Breadcrumb :breadcrumbs="breadcrumbs" /> -->
      <div class="col-lg-12">
        <GmapMap
          :options="{
            zoomControl: true,
            mapTypeControl: false,
            scaleControl: false,
            streetViewControl: false,
            rotateControl: false,
            fullscreenControl: true,
            disableDefaultUi: false,
            styles: this.mapStyle,
          }"
          :center="{ lat: 28.6238385, lng: 77.0335197 }"
          :zoom="12"
          style="height: 650px; position: relative"
        >
          <GmapMarker
            :key="index"
            v-for="(m, index) in markers"
            :position="m.position"
            :clickable="true"
            :draggable="true"
            :animation="2"
            @click="toggleInfo(m, index, $event.latLng, $event)"
            :icon="{
              url: require('../../../assets/images/bus-marker-logo.svg'),
            }"
          />
          <gmap-info-window
            :options="infoOptions"
            :position="infoWindowPos"
            :opened="infoWinOpen"
            @closeclick="infoWinOpen = false"
          >
            <div v-html="infoContent"></div>
          </gmap-info-window>
        </GmapMap>
      </div>
      <div class="col-lg-3 offset-lg-0" style="position: absolute">
        <div class="card">
          <div class="card-body">
            <div class="card-title text-center">{{ breadcrumbs.title }}</div>
            <b-form @submit.prevent="createLocation">
              <input type="hidden" v-model.trim="form.id" />
              <b-form-group
                label="Name"
                label-for="title-input"
                label-cols-sm="4"
                label-cols-lg="3"
                content-cols-sm
                content-cols-lg="9"
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
                content-cols-lg="9"
              >
                <gmap-autocomplete
                  class="form-control"
                  placeholder="Start typing address"
                  @keyup.enter="useAddressData"
                  @place_changed="getAddressData"
                  :state="validateState('address')"
                  :class="{
                    'is-invalid': submitted && $v.form.address.$error,
                  }"
                  ref="gmapAutocomplete"
                >
                </gmap-autocomplete>
                <b-form-invalid-feedback
                  v-if="submitted && !$v.form.address.required"
                  >Address is a required field.</b-form-invalid-feedback
                >
              </b-form-group>
              <!-- <b-form-group
                label="Lat"
                label-for="latitude-input"
                label-cols-sm="4"
                label-cols-lg="3"
                content-cols-sm
                content-cols-lg="9"
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
                label="Lng"
                label-for="longitude-input"
                label-cols-sm="4"
                label-cols-lg="3"
                content-cols-sm
                content-cols-lg="9"
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
                content-cols-lg="9"
              >
                <b-form-select
                  v-model.trim="$v.form.type.$model"
                  class="mb-3"
                  :state="validateState('type')"
                >
                  <template #first>
                    <b-form-select-option :value="null" disabled
                      >-- select an type --</b-form-select-option
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
                content-cols-lg="9"
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
              <br />
              <b-form-group
                label="Pictures"
                label-for="type-input"
                label-cols-sm="3"
                label-cols-lg="0"
                content-cols-sm
                content-cols-lg="12"
              >
                <vue-upload-multiple-image
                  @upload-success="uploadImageSuccess"
                  @before-remove="beforeRemove"
                  @edit-image="editImage"
                  :data-images="form.files"
                  browseText="Select file"
                  dragText="Drop files here"
                  accept="image/jpeg,image/png,image/jpg"
                  popupText=""
                  primaryText=""
                  markIsPrimaryText=""
                ></vue-upload-multiple-image>
              </b-form-group>
              <br />
              <b-form-group class="col-md-12">
                <b-button
                  type="submit"
                  name="location-submit"
                  class="btn btn-success btn-md text-center"
                  >{{ button.text }}</b-button
                >
                <b-button
                  type="button"
                  name="location-reset"
                  class="btn btn-default btn-md text-center ml-2"
                  @click="resetForm"
                  >Reset</b-button
                >
              </b-form-group>
            </b-form>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
//import Breadcrumb from "../../../components/breadcrumb";
import { validationMixin } from "vuelidate";
import { required } from "vuelidate/lib/validators";
import { locationService } from "../../../services"; //uploadService
import mapData from "./mapStyle.json";
import VueUploadMultipleImage from "vue-upload-multiple-image";

export default {
  mixins: [validationMixin],
  name: "locationCreate",
  data() {
    return {
      breadcrumbs: {
        title: "CREATE STOP",
        b1: "Manage Routes",
        b2: "stops",
        b3: "Index",
        link: true,
        name: "stop",
      },
      button: {
        text: "Create Stop",
      },
      mapStyle: mapData,
      options: [
        { text: "Active", value: "true" },
        { text: "Inactive", value: "false", default: true },
      ],
      images: [],
      form: {
        id: "",
        title: "",
        address: "",
        lat: "",
        lng: "",
        type: null,
        status: "",
        files: [],
      },
      submitted: false,
      loading: false,
      markers: [],
      shape: {
        coords: [10, 10, 10, 15, 15, 15, 15, 10],
        type: "poly",
      },
      infoContent: "",
      infoWindowPos: {
        lat: 0,
        lng: 0,
      },
      infoWinOpen: false,
      currentMidx: null,
      //optional: offset infowindow so it visually sits nicely on top of our marker
      infoOptions: {
        pixelOffset: {
          width: 0,
          height: -35,
        },
      },
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
    VueUploadMultipleImage,
  },
  methods: {
    uploadImageSuccess(formData, index, fileList) {
      this.form.files = fileList;
      // console.log("success ", formData, fileList);
    },
    beforeRemove(index, done) {
      // console.log("index", index, fileList);
      // var r = confirm("remove image");
      // if (r == true) {
      //    this.form.files.splice(index, 1);
      //   done();
      // }
      this.$swal
        .fire({
          title: "Are you sure you want to remove?",
          text: "",
          icon: "warning",
          showCancelButton: true,
          confirmButtonColor: "#3085d6",
          cancelButtonColor: "#d33",
          confirmButtonText: "Yes, delete it!",
        })
        .then((result) => {
          if (result.isConfirmed) {
            this.form.files.splice(index, 1);
            done();
          }
        });
    },
    editImage(formData, index, fileList) {
      console.log("edit data", formData, index, fileList);
    },
    resetForm() {
      this.button.text = "Create Stop";
      this.breadcrumbs.title = "CREATE STOP";
      this.form = {
        id: "",
        title: "",
        address: "",
        lat: "",
        lng: "",
        type: null,
        status: "",
      };
    },
    async getLocation() {
      try {
        const response = await locationService.find(this.$route.params.id);
        if (response.status) {
          this.$refs.gmapAutocomplete.$refs.input.value = response.data.address;
          this.form = response.data;
          this.breadcrumbs.title = "UPDATE STOP";
          this.button.text = "Update Stop";
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
    async loadMarkerItems() {
      const response = await locationService.markers();
      if (response.status) {
        this.markers = response.data;
      }
    },
    toggleInfo(marker, key, location, event) {
      console.log("markers ", marker, event);
      this.$refs.gmapAutocomplete.$refs.input.value = marker.address;
      this.form = {
        lat: location.lat() ? location.lat() : marker.lat,
        lng: location.lng() ? location.lng() : marker.lng,
        id: marker.id,
        title: marker.title,
        address: marker.address,
        type: marker.type,
        status: marker.status,
      };
      this.button.text = "Update Stop";
      this.breadcrumbs.title = "UPDATE STOP";

      this.infoWindowPos = marker.position;
      this.infoContent = `<p>${marker.title}</p><p>${marker.type}</p>`;
      //check if its the same marker that was selected if yes toggle
      if (this.currentMidx == key) {
        this.infoWinOpen = !this.infoWinOpen;
      }
      //if different marker set infowindow to open and reset current marker index
      else {
        this.infoWinOpen = true;
        this.currentMidx = key;
      }
    },
    validateState(name) {
      const { $dirty, $error } = this.$v.form[name];
      return $dirty ? !$error : null;
    },
    getAddressData: function (addressData) {
      this.form.address = addressData.formatted_address;
      this.form.lat = addressData.geometry.location.lat();
      this.form.lng = addressData.geometry.location.lng();
    },
    async createLocation() {
      try {
        this.submitted = true;
        // stop here if form is invalid
        this.$v.$touch();
        if (this.$v.$invalid) {
          return;
        }

        if (this.form.id != "") {
          const response = await locationService.update(
            this.form.id,
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
        } else {
          const response = await locationService.create(this.form);
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
                  path: `/${this.$store.state.auth.role}/locations`,
                }),
              2000
            );
          }
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
    this.loadMarkerItems();
    this.getLocation();
  },
};
</script>

<style scoped>
.mt-6 {
  margin-top: 5rem !important;
}
#my-strictly-unique-vue-upload-multiple-image {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

h1,
h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
