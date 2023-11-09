<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-10 offset-lg-1 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <h4 class="card-title">Edit offer</h4>
              <b-form @submit.prevent="updateOffer">
                <b-form-group
                  label="Offer name"
                  label-for="name-input"
                  label-cols-sm="4"
                  label-cols-lg="5"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="name-input"
                    v-model.trim="$v.form.name.$model"
                    type="text"
                    placeholder="Enter name"
                    :class="{
                      'is-invalid': submitted && $v.form.name.$error,
                    }"
                    :state="validateState('name')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.name.required"
                    class="invalid-feedback"
                  >
                    offer name is required
                  </div>
                </b-form-group>
                <b-form-group
                  label="Start Date"
                  label-for="start-date-input"
                  label-cols-sm="4"
                  label-cols-lg="5"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <Datetime
                    type="date"
                    title="start date"
                    v-model="form.start_date"
                    input-class="form-control"
                    zone="Asia/Kolkata"
                    class="theme-ferri"
                    :backdrop-click="true"
                  ></Datetime>
                </b-form-group>

                <b-form-group
                  label="End Date"
                  label-for="end-date-input"
                  label-cols-sm="4"
                  label-cols-lg="5"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <Datetime
                    type="date"
                    title="end date"
                    v-model="form.end_date"
                    input-class="form-control"
                    zone="Asia/Kolkata"
                    class="theme-ferri"
                    :backdrop-click="true"
                  ></Datetime>
                </b-form-group>

                <b-form-group
                  label="Offer Code"
                  label-for="offer-code-input"
                  label-cols-sm="4"
                  label-cols-lg="5"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="chassis-no-input"
                    v-model.trim="$v.form.code.$model"
                    type="text"
                    placeholder="Enter offer code."
                    :class="{
                      'is-invalid': submitted && $v.form.code.$error,
                    }"
                    :state="validateState('code')"
                    :formatter="formatter"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.code.required"
                    class="invalid-feedback"
                  >
                    offer code is required
                  </div>
                </b-form-group>

                <b-form-group
                  label="Discount (%)"
                  label-for="discount-input"
                  label-cols-sm="4"
                  label-cols-lg="5"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="discount-input"
                    v-model.trim="form.discount"
                    type="number"
                    placeholder="Enter discount %."
                  ></b-form-input>
                </b-form-group>

                <b-form-group
                  label="No of attempt"
                  label-for="attempt-input"
                  label-cols-sm="4"
                  label-cols-lg="5"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="attempt-input"
                    v-model.trim="form.attempt"
                    type="number"
                    placeholder="Enter no of attempt."
                  ></b-form-input>
                </b-form-group>

                <b-form-group
                  label="Offer picture (213x102)"
                  label-for="picture-input"
                  label-cols-sm="4"
                  label-cols-lg="5"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <div v-if="!form.picture">
                    <b-form-file
                      id="picture-input"
                      accept="image/jpeg, image/png, image/jpg"
                      placeholder="Choose a Offer picture or drop it here..."
                      @change="onFileChange($event, 'picture')"
                      :class="{
                        'is-invalid': submitted && $v.form.picture.$error,
                      }"
                      :state="validateState('picture')"
                    ></b-form-file>
                    <b-form-invalid-feedback
                      v-if="submitted && !$v.form.picture.required"
                      >picture is required</b-form-invalid-feedback
                    >
                  </div>
                  <div v-else>
                    <img
                      class="img-fluid"
                      :src="form.picture"
                      width="213"
                      height="102"
                    />
                    <button
                      class="btn social-btn btn-rounded btn-danger mr-4"
                      @click="removeImage('picture')"
                    >
                      <i class="mdi mdi-close"></i>
                    </button>
                  </div>
                </b-form-group>

                <b-form-group
                  label="type"
                  label-for="route-type-input"
                  class="mt-3"
                  label-cols-sm="4"
                  label-cols-lg="5"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-radio-group
                    :options="routeoptions"
                    v-model="form.type"
                    :class="{
                      'is-invalid': submitted && $v.form.type.$error,
                    }"
                    :state="validateState('type')"
                    @change="changeRoute($event)"
                  ></b-form-radio-group>

                  <b-form-invalid-feedback
                    v-if="submitted && !$v.form.type.required"
                    >Please select type</b-form-invalid-feedback
                  >
                </b-form-group>

                <b-form-group
                  v-if="route"
                  label="Route Name"
                  label-for="route-input"
                  label-cols-sm="4"
                  label-cols-lg="5"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-select v-model.trim="form.routeId" :options="routes">
                    <template #first>
                      <b-form-select-option :value="null" disabled
                        >-- Please select an routes --</b-form-select-option
                      >
                    </template>
                  </b-form-select>
                </b-form-group>

                <b-form-group
                  label="Offer terms"
                  label-for="terms-input"
                  invalid-feedback="terms is required"
                  class="mt-3"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="9"
                >
                  <vue-editor
                    class="mt-3"
                    v-model="form.terms"
                    :class="{
                      'is-invalid': submitted && $v.form.terms.$error,
                    }"
                    :state="validateState('terms')"
                  ></vue-editor>
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
                    v-model="form.status"
                    name="status"
                  ></b-form-radio-group>

                  <b-form-invalid-feedback
                    v-if="submitted && !$v.form.status.required"
                    >Please select one</b-form-invalid-feedback
                  >
                </b-form-group>

                <b-form-group class="col-md-6 offset-md-5">
                  <b-button
                    type="submit"
                    class="btn btn-lg btn-success text-center"
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
import { offerService, routeService } from "../../../services";
import { validationMixin } from "vuelidate";
import { required } from "vuelidate/lib/validators";
import { Datetime } from "vue-datetime";
import "vue-datetime/dist/vue-datetime.css";
import moment from "moment-timezone";
import { VueEditor } from "vue2-editor";
import { mapState } from "pinia";
import { fetchUsers } from "../../../store/fetchUsers.js";

export default {
  name: "busedit",
  mixins: [validationMixin],
  data() {
    return {
      breadcrumbs: {
        title: "Edit offer",
        b1: "Manage offers",
        b2: "offer",
        b3: "Index",
        link: true,
        name: "offers",
      },
      isEditing: false,
      options: [
        { text: "Active", value: true },
        { text: "Inactive", value: false, default: true },
      ],
      routeoptions: [
        { text: "All", value: true, default: true },
        { text: "Specific route", value: false },
      ],
      form: {
        adminId: "",
        name: "",
        start_date: "",
        end_date: "",
        code: "",
        discount: "",
        attempt: "",
        routeId: "",
        picture: "",
        terms: "",
        status: "",
        type: "",
      },
      submitted: false,
      loading: false,
      route: false,
      routes: [],
    };
  },
  validations: {
    form: {
      name: { required },
      code: { required },
      picture: { required },
      type: { required },
      terms: { required },
      status: { required },
    },
  },
  computed: {
    ...mapState(fetchUsers, ["getUser"]),
  },
  components: {
    Breadcrumb,
    Datetime,
    VueEditor,
  },
  mounted() {
    this.form.start_date == ""
      ? moment().tz("Asia/Kolkata").format()
      : this.form.start_date;
    this.form.end_date == ""
      ? moment().tz("Asia/Kolkata").add(7, "days").format()
      : this.form.start_date;
    console.log("this.form.type ", this.form.type);
    this.form.type == false ? (this.route = true) : (this.route = false);
    this.loadRouteItems();
    this.getoffer();
  },
  methods: {
    loadRouteItems() {
      routeService.load().then((response) => {
        this.routes = response.data;
      });
    },
    changeRoute(value) {
      if (value == false) {
        this.route = true;
      } else {
        this.route = false;
        this.form.routeId = "";
      }
    },
    onFileChange(e, fileTitle) {
      var files = e.target.files || e.dataTransfer.files;
      console.log(files);
      if (!files.length) return;
      this.createImage(files[0], fileTitle);
    },
    createImage(file, fileTitle) {
      // var picture = new Image();
      var reader = new FileReader();
      var vm = this;

      reader.onload = (e) => {
        vm.form[fileTitle] = e.target.result;
      };
      reader.readAsDataURL(file);
    },
    removeImage: function (titlename) {
      this.form[titlename] = "";
    },
    formatter(value) {
      return value.toUpperCase();
    },
    validateState(name) {
      const { $dirty, $error } = this.$v.form[name];
      return $dirty ? !$error : null;
    },

    async getoffer() {
      try {
        const response = await offerService.find(this.$route.params.id);
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
    async updateOffer() {
      try {
        this.submitted = true;
        // stop here if form is invalid
        this.$v.$touch();
        if (this.$v.$invalid) {
          return;
        }
        this.form.adminId = this.getUser.id; // admin Id
        const response = await offerService.update(
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
                path: `/offers`,
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
};
</script>

<style lang="scss" scoped></style>
