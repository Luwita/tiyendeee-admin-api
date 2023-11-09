<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-8 offset-lg-2 stretch-card">
          <div class="card">
            <div class="card-body">
              <b-form @submit.prevent="updateDriver">
                <b-form-group
                  label="First Name"
                  label-for="firstname-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="firstname-input"
                    v-model.trim="$v.form.firstname.$model"
                    type="text"
                    placeholder="Enter first name"
                    :class="{
                      'is-invalid': submitted && $v.form.firstname.$error,
                    }"
                    :state="validateState('firstname')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.firstname.required"
                    class="invalid-feedback"
                  >
                    first name is required
                  </div>
                </b-form-group>
                <b-form-group
                  label="Last Name"
                  label-for="lastname-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="lastname-input"
                    v-model.trim="$v.form.lastname.$model"
                    type="text"
                    placeholder="Enter last name"
                    :class="{
                      'is-invalid': submitted && $v.form.lastname.$error,
                    }"
                    :state="validateState('lastname')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.lastname.required"
                    class="invalid-feedback"
                  >
                    last name is required
                  </div>
                </b-form-group>
                <b-form-group
                  label="Email Address"
                  label-for="email-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="email-input"
                    v-model.trim="$v.form.email.$model"
                    type="email"
                    placeholder="Enter email address"
                    :class="{
                      'is-invalid': submitted && $v.form.email.$error,
                    }"
                    :state="validateState('email')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.email.required"
                    class="invalid-feedback"
                  >
                    email address is required
                  </div>
                </b-form-group>
                <b-form-group
                  label="Phone Number"
                  label-for="phone-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="phone-input"
                    v-model.trim="$v.form.phone.$model"
                    placeholder="Enter phone number"
                    :class="{
                      'is-invalid': submitted && $v.form.phone.$error,
                    }"
                    :state="validateState('phone')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.phone.required"
                    class="invalid-feedback"
                  >
                    phone number is required
                  </div>
                  <div class="invalid-feedback" v-if="!$v.form.phone.minLength">
                    Name must have at least
                    {{ $v.form.phone.$params.minLength.min }} letters.
                  </div>
                </b-form-group>

                <b-form-group
                  label="Type "
                  label-for="type-input"
                  invalid-feedback="type is required"
                  class="mt-3"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-radio-group
                    :options="option_types"
                    v-model="form.type"
                    name="type"
                    @change="checkType"
                  ></b-form-radio-group>

                  <b-form-invalid-feedback
                    v-if="submitted && !$v.form.type.required"
                    >Please select one</b-form-invalid-feedback
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
                    v-model="form.status"
                    name="status"
                  ></b-form-radio-group>

                  <b-form-invalid-feedback
                    v-if="submitted && !$v.form.status.required"
                    >Please select one</b-form-invalid-feedback
                  >
                </b-form-group>
                <p class="card-description fw-500"><b>Documents</b></p>
                <br />
                <b-form-group
                  label="Profile picture"
                  label-for="picture-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <div v-if="!form.picture">
                    <b-form-file
                      id="picture-input"
                      accept="image/jpeg, image/png, image/jpg"
                      placeholder="Choose a Profile picture or drop it here..."
                      @change="onFileChange($event, 'picture')"
                    ></b-form-file>
                  </div>
                  <div v-else>
                    <img
                      class="img-fluid"
                      :src="form.picture"
                      width="250"
                      height="250"
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
                  label="Licence"
                  label-for="licence-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                  v-if="show"
                >
                  <div v-if="!form.document_licence">
                    <b-form-file
                      id="licence-input"
                      accept="image/jpeg, image/png, image/jpg"
                      placeholder="Choose a Licence or drop it here..."
                      @change="onFileChange($event, 'document_licence')"
                    ></b-form-file>
                  </div>
                  <div v-else>
                    <img
                      class="img-fluid"
                      :src="form.document_licence"
                      width="250"
                      height="250"
                    />
                    <button
                      class="btn social-btn btn-rounded btn-danger mr-4"
                      @click="removeImage('document_licence')"
                    >
                      <i class="mdi mdi-close"></i>
                    </button>
                  </div>
                </b-form-group>
                <b-form-group
                  label="Adhar Card"
                  label-for="adhar-card-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                  v-if="show"
                >
                  <div v-if="!form.document_adhar_card">
                    <b-form-file
                      id="adhar-card-input"
                      accept="image/jpeg, image/png, image/gif"
                      placeholder="Choose a Adhar Card or drop it here..."
                      @change="onFileChange($event, 'document_adhar_card')"
                    ></b-form-file>
                  </div>
                  <div v-else>
                    <img
                      class="img-fluid"
                      :src="form.document_adhar_card"
                      width="250"
                      height="250"
                    />
                    <button
                      class="btn social-btn btn-rounded btn-danger mr-4"
                      @click="removeImage('document_adhar_card')"
                    >
                      <i class="mdi mdi-close"></i>
                    </button>
                  </div>
                </b-form-group>
                <b-form-group
                  label="Police Vertification"
                  label-for="police-vertification-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <div v-if="!form.document_police_vertification">
                    <b-form-file
                      id="police-vertification-input"
                      accept="image/jpeg, image/png, image/gif"
                      placeholder="Choose a Police Vertification or drop it here..."
                      @change="
                        onFileChange($event, 'document_police_vertification')
                      "
                    ></b-form-file>
                  </div>
                  <div v-else>
                    <img
                      class="img-fluid"
                      :src="form.document_police_vertification"
                      width="250"
                      height="250"
                    />
                    <button
                      class="btn social-btn btn-rounded btn-danger mr-4"
                      @click="removeImage('document_police_vertification')"
                    >
                      <i class="mdi mdi-close"></i>
                    </button>
                  </div>
                </b-form-group>

                <b-form-group class="col-md-6 offset-md-4">
                  <b-button
                    type="submit"
                    class="btn btn-lg btn-success text-center"
                    >Update</b-button
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
import { driverService } from "../../../services";
import { validationMixin } from "vuelidate";
import { required, numeric, email, minLength } from "vuelidate/lib/validators";
import { mapState } from "pinia";
import { fetchUsers } from "../../../store/fetchUsers.js";

export default {
  name: "driveredit",
  mixins: [validationMixin],
  data() {
    return {
      breadcrumbs: {
        title: "Edit Driver",
        b1: "Manage Drivers",
        b2: "Drivers",
        b3: "Index",
        link: true,
        name: "drivers",
      },
      options: [
        { text: "Active", value: "true" },
        { text: "Inactive", value: "false", default: true },
      ],
      option_types: [
        { text: "Driver", value: "driver" },
        { text: "Assistant", value: "assistant", default: "assistant" },
      ],
      form: {
        adminId: "",
        firstname: "",
        lastname: "",
        email: "",
        phone: "",
        picture: "",
        document_licence: "",
        document_adhar_card: "",
        document_police_vertification: "",
        status: "",
        type: "",
      },
      submitted: false,
      loading: false,
      error: "",
      success: "",
      show: true,
    };
  },
  validations: {
    form: {
      email: { required, email },
      firstname: { required },
      lastname: { required },
      status: { required },
      type: { required },
      phone: { required, numeric, minLength: minLength(10) },
      //   picture: { required },
      //   document_licence: { required },
      //   document_adhar_card: { required },
      //   document_police_vertification: { required },
    },
  },
  computed: {
    ...mapState(fetchUsers, ["getUser"]),
  },
  components: {
    Breadcrumb,
  },
  mounted() {
    this.getDriver();
  },
  methods: {
    checkType(val) {
      if (val === "assistant") {
        this.show = false;
      } else {
        this.show = true;
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
    validateState(name) {
      const { $dirty, $error } = this.$v.form[name];
      return $dirty ? !$error : null;
    },
    async getDriver() {
      try {
        const response = await driverService.find(this.$route.params.id);
        console.log("response", response);
        if (response.status) {
          if (response.data.type === "assistant") {
            this.show = false;
          } else {
            this.show = true;
          }
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
    async updateDriver() {
      try {
        this.submitted = true;
        // stop here if form is invalid
        this.$v.$touch();
        if (this.$v.$invalid) {
          return;
        }
        this.form.adminId = this.getUser.id; // admin Id
        const response = await driverService.update(
          this.$route.params.id,
          this.form
        );
        if (response.status) {
          this.$toast.open({
            message: response.message,
            type: "success",
            position: "top-right",
            duration: 3000,
            // all of other options may go here
          });
          setTimeout(
            () =>
              this.$router.push({
                path: `/drivers`,
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
