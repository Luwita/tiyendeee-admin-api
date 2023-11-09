<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-8 offset-lg-2 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <b-form @submit.prevent="createAgent">
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

                <p class="card-description fw-500"><b>Company Details</b></p>
                <br />

                <b-form-group
                  label="Company Name"
                  label-for="company-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="company-input"
                    v-model.trim="$v.form.company.$model"
                    placeholder="Enter company number"
                    :class="{
                      'is-invalid': submitted && $v.form.company.$error,
                    }"
                    :state="validateState('company')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.company.required"
                    class="invalid-feedback"
                  >
                    company name is required
                  </div>
                </b-form-group>

                <b-form-group
                  label="Contact No"
                  label-for="contact-no-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="contact-no-input"
                    v-model.trim="$v.form.contact_no.$model"
                    placeholder="Enter contact number"
                    :class="{
                      'is-invalid': submitted && $v.form.contact_no.$error,
                    }"
                    :state="validateState('contact_no')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.contact_no.required"
                    class="invalid-feedback"
                  >
                    contact number is required
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
                  <b-form-input
                    id="city-input"
                    v-model.trim="$v.form.city.$model"
                    placeholder="Enter city name"
                    :class="{
                      'is-invalid': submitted && $v.form.city.$error,
                    }"
                    :state="validateState('city')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.city.required"
                    class="invalid-feedback"
                  >
                    city name is required
                  </div>
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
                  label="Office Address"
                  label-for="address-2-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-textarea
                    id="address-2-input"
                    v-model.trim="$v.form.address_2.$model"
                    placeholder="Enter office address..."
                    :class="{
                      'is-invalid': submitted && $v.form.address_2.$error,
                    }"
                    rows="3"
                    max-rows="5"
                    :state="validateState('address_2')"
                  ></b-form-textarea>

                  <div
                    v-if="submitted && !$v.form.address_2.required"
                    class="invalid-feedback"
                  >
                    office address is required
                  </div>
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
                    v-model.trim="$v.form.pincode.$model"
                    placeholder="Enter Pincode"
                    :class="{
                      'is-invalid': submitted && $v.form.pincode.$error,
                    }"
                    :state="validateState('pincode')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.pincode.required"
                    class="invalid-feedback"
                  >
                    pincode is required
                  </div>
                  <div
                    class="invalid-feedback"
                    v-if="!$v.form.pincode.minLength"
                  >
                    Name must have at least
                    {{ $v.form.pincode.$params.minLength.min }} letters.
                  </div>
                </b-form-group>

                <b-form-group
                  label="Pancard"
                  label-for="pancard-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <div v-if="!form.document_pancard">
                    <b-form-file
                      id="pancard-input"
                      accept="image/jpeg, image/png, image/jpg"
                      placeholder="Choose a Pancard or drop it here..."
                      @change="onFileChange($event, 'document_pancard')"
                    ></b-form-file>
                  </div>
                  <div v-else>
                    <img
                      class="img-fluid"
                      :src="form.document_pancard"
                      width="300"
                      height="200"
                    />
                    <button
                      class="btn social-btn btn-rounded btn-danger mr-4"
                      @click="removeImage('document_pancard')"
                    >
                      <i class="mdi mdi-close"></i>
                    </button>
                  </div>
                </b-form-group>
                <b-form-group
                  label="GST Certificate"
                  label-for="gst-certificate-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <div v-if="!form.document_gst_certificate">
                    <b-form-file
                      id="gst-certificate-input"
                      accept="image/jpeg, image/png, image/jpg"
                      placeholder="Choose a GST Certificate or drop it here..."
                      @change="onFileChange($event, 'document_gst_certificate')"
                    ></b-form-file>
                  </div>
                  <div v-else>
                    <img
                      class="img-fluid"
                      :src="form.document_gst_certificate"
                      width="350"
                      height="250"
                    />
                    <button
                      class="btn social-btn btn-rounded btn-danger mr-4"
                      @click="removeImage('document_gst_certificate')"
                    >
                      <i class="mdi mdi-close"></i>
                    </button>
                  </div>
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
import { agentService } from "../../../services";

export default {
  name: "agentcreate",
  mixins: [validationMixin],
  data() {
    return {
      breadcrumbs: {
        title: "Create Agent",
        b1: "Manage Agents",
        b2: "Agents",
        b3: "Index",
        link: true,
        name: "agents",
      },
      form: {
        firstname: "",
        lastname: "",
        email: "",
        phone: "",
        picture: "",
        company: "",
        address_1: "",
        address_2: "",
        city: "",
        pincode: "",
        is_active: "",
        contact_no: "",
        document_pancard: "",
        document_gst_certificate: "",
        role: "agents",
      },
      submitted: false,
      options: [
        { text: "Active", value: true },
        { text: "Inactive", value: false, default: true },
      ],
    };
  },
  components: {
    Breadcrumb,
  },
  validations: {
    form: {
      email: { required, email },
      firstname: { required },
      lastname: { required },
      // status: { required },
      phone: { required, numeric, minLength: minLength(10) },
      company: { required },
      address_2: { required },
      pincode: { required, numeric, minLength: minLength(6) },
      city: { required },
      // document_pancard: { required },
      // document_gst_certificate: { required },
      contact_no: { required, numeric, minLength: minLength(10) },
    },
  },
  methods: {
    validateState(name) {
      const { $dirty, $error } = this.$v.form[name];
      return $dirty ? !$error : null;
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
    async createAgent() {
      try {
        this.submitted = true;

        // stop here if form is invalid
        this.$v.$touch();
        if (this.$v.$invalid) {
          return;
        }
        const response = await agentService.create(this.form);
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
                path: `/${this.$store.state.auth.role}/agents`,
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

<style lang="scss" scoped></style>
