<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-8 offset-lg-2 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <b-form @submit.prevent="createUser">
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

                  <div
                    v-if="submitted && !$v.form.email.ValidEmailDomain"
                    class="invalid-feedback"
                  >
                    email address end with @theferri.com is required
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
                  label="Role"
                  label-for="role-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-select
                    v-model.trim="$v.form.role.$model"
                    :options="roles"
                    :class="{
                      'is-invalid': submitted && $v.form.role.$error,
                    }"
                    :state="validateState('role')"
                  >
                    <template #first>
                      <b-form-select-option :value="null" disabled
                        >-- Please select an role --</b-form-select-option
                      >
                    </template>
                  </b-form-select>
                  <div
                    v-if="submitted && !$v.form.role.required"
                    class="invalid-feedback"
                  >
                    role is required
                  </div>
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
                    v-model="form.is_active"
                    name="status"
                  ></b-form-radio-group>

                  <!-- <b-form-invalid-feedback
                    v-if="submitted && !$v.form.status.required"
                    >Please select one</b-form-invalid-feedback
                  > -->
                </b-form-group>

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
                      width="200"
                      height="200"
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
                  label="City"
                  label-for="city-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <multiselect
                    v-model="form.city"
                    :options="cities"
                    :multiple="true"
                    :close-on-select="false"
                    :clear-on-select="false"
                    :preserve-search="true"
                    placeholder="start typing for cities"
                    label="name"
                    track-by="name"
                    :preselect-first="true"
                    :options-limit="50"
                  >
                  </multiselect>
                </b-form-group>

                <b-form-group
                  label="Home Address"
                  label-for="address-1-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-textarea
                    id="address-1-input"
                    v-model.trim="form.address_1"
                    placeholder="Enter home address..."
                    rows="3"
                    max-rows="5"
                  ></b-form-textarea>
                </b-form-group>

                <b-form-group
                  label="Pincode"
                  label-for="pincode-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="pincode-input"
                    v-model.trim="form.pincode"
                    placeholder="Enter Pincode"
                  ></b-form-input>
                </b-form-group>

                <b-form-group class="col-md-6 offset-md-4">
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
import { validationMixin } from "vuelidate";
import { required, numeric, email, minLength } from "vuelidate/lib/validators";
import { userService, roleService, cityService } from "../../../services";
import Multiselect from "vue-multiselect";

const ValidEmailDomain = (mailaddr) => {
  var re = /.+@(hotmail|gmail|outlook)\.com$/;
  return re.test(mailaddr);
};

export default {
  name: "usercreate",
  mixins: [validationMixin],
  data() {
    return {
      breadcrumbs: {
        title: "Create User",
        b1: "Manage Users",
        b2: "Users",
        b3: "Index",
        link: true,
        name: "users",
      },
      form: {
        firstname: "",
        lastname: "",
        email: "",
        phone: "",
        picture: "",
        role: null,
        company: "",
        address_1: "",
        address_2: "",
        city: [],
        pincode: "",
        is_active: "",
        contact_no: "",
        document_pancard: "",
        document_gst_certificate: "",
        is_agent: false,
      },
      submitted: false,
      options: [
        { text: "Active", value: "true" },
        { text: "Inactive", value: "false", default: true },
      ],
      roles: [],
      cities: [],
      isLoading: false,
    };
  },
  components: {
    Breadcrumb,
    Multiselect,
  },
  validations: {
    form: {
      email: { required, email, ValidEmailDomain },
      firstname: { required },
      lastname: { required },
      role: { required },
      phone: { required, numeric, minLength: minLength(10) },
    },
  },
  mounted() {
    this.loadRole();
    this.loadCity();
  },
  methods: {
    validateState(name) {
      const { $dirty, $error } = this.$v.form[name];
      return $dirty ? !$error : null;
    },
    loadRole() {
      roleService.load().then((response) => {
        if (response.data.length > 0) {
          this.roles = response.data;
        } else {
          this.$toast.open({
            message: "No role found",
            type: "error",
            position: "top-right",
            duration: 3000,
          });
        }
      });
    },
    loadCity() {
      cityService.load().then((response) => {
        if (response.data.length > 0) {
          this.cities = response.data;
        } else {
          this.$toast.open({
            message: "No role found",
            type: "error",
            position: "top-right",
            duration: 3000,
          });
        }
      });
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
    async createUser() {
      try {
        this.submitted = true;

        // stop here if form is invalid
        this.$v.$touch();
        if (this.$v.$invalid) {
          return;
        }
        const response = await userService.create(this.form);
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
                path: `/users`,
              }),
            3000
          );
        }
      } catch (e) {
        console.log("e", e);
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

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style lang="scss" scoped></style>
