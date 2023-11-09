<template>
  <section class="tables">
    <b-form @submit.prevent="updateTimeTable">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />

        <div class="col-lg-8 offset-lg-2 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <b-form-group
                label="Route Name"
                label-for="route-input"
                label-cols-sm="5"
                label-cols-lg="4"
                content-cols-sm
                content-cols-lg="7"
              >
                <v-select
                  @search="fetchRoutes"
                  label="pageid"
                  :filterable="false"
                  :options="options"
                  v-model="form.routeId"
                  @input="changeRoutes"
                >
                  <template slot="no-options">
                    type to search route name..
                  </template>

                  <template slot="option" slot-scope="option">
                    <div class="d-center">
                      {{ option.title }}
                    </div>
                  </template>
                  <template slot="selected-option" slot-scope="option">
                    <div class="selected d-center">
                      {{ option.title }}
                    </div>
                  </template>
                </v-select>
              </b-form-group>

              <b-form-group
                label="Buses"
                label-for="buses-input"
                label-cols-sm="5"
                label-cols-lg="4"
                content-cols-sm
                content-cols-lg="7"
              >
                <b-form-select
                  v-model.trim="$v.form.busId.$model"
                  :class="{
                    'is-invalid': submitted && $v.form.busId.$error,
                  }"
                  :state="validateState('busId')"
                  :options="routeoptions"
                >
                  <template #first>
                    <b-form-select-option :value="null" disabled
                      >-- Please select an bus name --</b-form-select-option
                    >
                  </template>
                </b-form-select>
                <div
                  v-if="submitted && !$v.form.busId.required"
                  class="invalid-feedback"
                >
                  bus name is required
                </div>
              </b-form-group>

              <b-form-group
                label="Direction "
                label-for="direction-input"
                invalid-feedback="direction is required"
                class="mt-3"
                label-cols-sm="5"
                label-cols-lg="4"
                content-cols-sm
                content-cols-lg="7"
              >
                <b-form-radio-group
                  :options="directionOptions"
                  v-model.trim="$v.form.direction.$model"
                  name="direction"
                  @change="getDirection($event)"
                  :state="validateState('direction')"
                ></b-form-radio-group>
                <b-form-invalid-feedback :state="validateState('direction')"
                  >Please select one</b-form-invalid-feedback
                >
              </b-form-group>

              <b-form-group
                :label="directionName"
                label-for="direction-input"
                invalid-feedback="direction is required"
                class="mt-3"
                label-cols-sm="5"
                label-cols-lg="4"
                content-cols-sm
                content-cols-lg="2"
              >
                <Datetime
                  type="time"
                  :title="directionTitle"
                  v-model.trim="$v.form.time.$model"
                  input-class="form-control theme-ferri"
                  zone="Asia/Kolkata"
                ></Datetime>
                <div
                  v-if="submitted && !$v.form.time.required"
                  class="invalid-feedback"
                >
                  {{ directionTitle }} no is required
                </div>
              </b-form-group>

              <b-form-group
                v-for="(data, key) in form.stops"
                :key="key"
                :label="data.location.title"
                label-for="routes-input"
                label-cols-sm="5"
                label-cols-lg="4"
                content-cols-sm
                content-cols-lg="7"
              >
                <b-form-group
                  label="Arrival Time:"
                  label-for="routes-input"
                  label-cols-sm="5"
                  label-cols-lg="5"
                  content-cols-sm
                  content-cols-lg="4"
                >
                  <Datetime
                    type="time"
                    title="Arrival Time"
                    v-model="data.arrival_time"
                    input-class="form-control"
                    zone="Asia/Kolkata"
                    class="theme-ferri"
                  ></Datetime>
                </b-form-group>

                <b-form-group
                  label="Departure Time :"
                  label-for="departure-time-input"
                  label-cols-sm="5"
                  label-cols-lg="5"
                  content-cols-sm
                  content-cols-lg="4"
                >
                  <Datetime
                    type="time"
                    title="Departure Time"
                    v-model="data.departure_time"
                    input-class="form-control"
                    zone="Asia/Kolkata"
                    class="theme-ferri"
                  ></Datetime>
                </b-form-group>
              </b-form-group>

              <b-form-group
                label="Every "
                label-for="every-input"
                invalid-feedback="Every is required"
                class="mt-3"
                label-cols-sm="5"
                label-cols-lg="4"
                content-cols-sm
                content-cols-lg="8"
              >
                <b-form-checkbox-group
                  id="checkbox-group-1"
                  v-model.trim="$v.form.every.$model"
                  :options="everyoptions"
                  :state="validateState('every')"
                  name="every-1"
                ></b-form-checkbox-group>
                <b-form-invalid-feedback :state="validateState('every')"
                  >Please select every</b-form-invalid-feedback
                >
              </b-form-group>

              <b-form-group
                label="Period operating "
                label-for="period-operating-input"
                invalid-feedback="period operating is required"
                class="mt-3"
                label-cols-sm="5"
                label-cols-lg="4"
                content-cols-sm
                content-cols-lg="8"
              >
                <div class="row">
                  <datetime
                    v-model="form.start_date"
                    input-class="form-control"
                  ></datetime
                  >&nbsp; <span class="">To</span> &nbsp;
                  <datetime
                    v-model="form.end_date"
                    input-class="form-control"
                  ></datetime>
                </div>
              </b-form-group>

              <b-form-group
                label="Status "
                label-for="status-input"
                invalid-feedback="status is required"
                class="mt-3"
                label-cols-sm="5"
                label-cols-lg="4"
                content-cols-sm
                content-cols-lg="7"
              >
                <b-form-radio-group
                  :options="statusOptions"
                  v-model.trim="$v.form.status.$model"
                  name="status"
                  :state="validateState('status')"
                ></b-form-radio-group>
                <b-form-invalid-feedback :state="validateState('status')"
                  >Please select one</b-form-invalid-feedback
                >
              </b-form-group>

              <b-form-group class="col-md-6 offset-md-5">
                <b-button
                  type="submit"
                  class="btn btn-success btn-lg text-center"
                  >Update</b-button
                >
              </b-form-group>
            </div>
          </div>
        </div>
      </div>
    </b-form>
  </section>
</template>

<script>
import Breadcrumb from "../../../components/breadcrumb";
import { routeService, timetableService, busService } from "../../../services"; //busService, routeService
import vSelect from "vue-select";
import "vue-select/src/scss/vue-select.scss";
import lodash from "lodash";
import { validationMixin } from "vuelidate";
import { required } from "vuelidate/lib/validators";
import { Datetime } from "vue-datetime";
import "vue-datetime/dist/vue-datetime.css";

export default {
  mixins: [validationMixin],
  name: "timetableEdit",
  components: {
    Breadcrumb,
    vSelect,
    Datetime,
  },
  data() {
    return {
      breadcrumbs: {
        title: "Edit Timetable",
        b1: "Manage Timetable",
        b2: "Timetable",
        b3: "Index",
        link: true,
        name: "admin.timeTables",
      },
      options: [
        {
          pageid: 0,
          title: "",
        },
      ],
      submitted: false,
      routeoptions: [],
      form: {
        time: "",
        every: [],
        routeId: "",
        busId: "",
        stops: [],
        status: "",
        direction: "",
        start_date: "",
        end_date: "",
      },
      statusOptions: [
        { text: "Active", value: true },
        { text: "Inactive", value: false },
      ],
      directionOptions: [
        { text: "Arriving", value: "arriving" },
        { text: "Departing", value: "departing" },
      ],
      everyoptions: [
        { text: "Sunday", value: "sunday" },
        { text: "Monday", value: "monday" },
        { text: "Tuesday", value: "tuesday" },
        { text: "Wednesday", value: "wednesday" },
        { text: "Thursday", value: "thursday" },
        { text: "Friday", value: "friday" },
        { text: "Saturday", value: "saturday" },
      ],
      directionName: "Arriving at",
      directionTitle: "Arriving at",
    };
  },
  validations: {
    form: {
      busId: { required },
      every: { required },
      time: { required },
      status: { required },
      direction: { required },
    },
  },
  methods: {
    async getTimetable() {
      try {
        const response = await timetableService.find(this.$route.params.id);
        if (response.status) {
          //this.selectRouteLocation(response.data.locationId);
          this.getDirection(response.data.direction);
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
    getDirection(value) {
      if (value == "Departing") {
        this.directionName =
          value.charAt(0).toUpperCase() + value.slice(1) + " at";
        this.directionTitle =
          value.charAt(0).toUpperCase() + value.slice(1) + " at";
      } else {
        this.directionName =
          value.charAt(0).toUpperCase() + value.slice(1) + " at";
        this.directionTitle =
          value.charAt(0).toUpperCase() + value.slice(1) + " at";
      }
    },
    async changeRoutes(value) {
      try {
        const response = await routeService.findStops(value.id);
        if (response.status) {
          this.form.stops = response.data;
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
    loadBusRoute() {
      busService.loadBusByRoute().then((response) => {
        if (response.data.length > 0) {
          this.routeoptions = response.data;
        } else {
          this.$toast.open({
            message: "No route found",
            type: "error",
            position: "top-right",
            duration: 3000,
          });
        }
      });
    },
    fetchRoutes(search, loading) {
      if (search.length) {
        loading(true);
        this.search(loading, search, this);
      }
    },
    search: lodash.debounce(async (loading, search, vm) => {
      try {
        const response = await routeService.search(search);
        vm.options = response.items;
        loading(false);
      } catch (err) {
        this.$toast.open({
          message: err,
          type: "error",
          position: "top-right",
          duration: 5000,
        });
      }
    }, 350),
    validateState(name) {
      const { $dirty, $error } = this.$v.form[name];
      return $dirty ? !$error : null;
    },
    async updateTimeTable() {
      try {
        this.submitted = true;

        // stop here if form is invalid
        this.$v.$touch();
        if (this.$v.$invalid) {
          return;
        }
        const response = await timetableService.update(
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
                path: `/timetables`,
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
    this.getTimetable();
    this.loadBusRoute();
  },
  watch: {
    testdata: function () {
      console.log("testdata");
    },
  },
  //   beforeUpdate() {
  //     if (this.form.locationId) {
  //       this.selectRouteLocation(this.form.locationId);
  //     }
  //   },
};
</script>

<style scoped></style>
