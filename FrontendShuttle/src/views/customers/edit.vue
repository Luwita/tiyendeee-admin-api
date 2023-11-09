<template>
  <div>
    <section class="tables">
      <div class="row">
        <div class="col-lg-12">
          <Breadcrumb :breadcrumbs="breadcrumbs" />
        </div>
        <div class="col-lg-8 offset-lg-2 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <b-form @submit.prevent="updateCustomer">
                <b-form-group
                  label="First name"
                  label-for="firstname-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="firstname-input"
                    v-model.trim="form.firstname"
                    type="text"
                    placeholder="Enter first name"
                  ></b-form-input>
                </b-form-group>

                <b-form-group
                  label="Last name"
                  label-for="lastname-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="lastname-input"
                    v-model.trim="form.lastname"
                    type="text"
                    placeholder="Enter last name"
                  ></b-form-input>
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
                    v-model.trim="form.phone"
                    placeholder="Enter phone number"
                    readonly
                  ></b-form-input>
                </b-form-group>

                <b-form-group
                  label="Refer Code"
                  label-for="refercode-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="refercode-input"
                    v-model.trim="form.refercode"
                    type="text"
                    readonly
                  ></b-form-input>
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
import Breadcrumb from "../../components/breadcrumb";
import { customerService } from "../../services";
import { validationMixin } from "vuelidate";
import { required, email } from "vuelidate/lib/validators";
export default {
  name: "customeredit",
  mixins: [validationMixin],
  data() {
    return {
      breadcrumbs: {
        title: "Edit Customer",
        b1: "Manage customers",
        b2: "customer",
        b3: "Index",
        link: true,
        name: "customers",
      },
      form: {
        id: "",
        firstname: "",
        lastname: "",
        gender: "",
        phone: "",
        email: "",
        refercode: "",
      },
      options: [
        { text: "Active", value: "true" },
        { text: "Inactive", value: "false", default: true },
      ],
      submitted: false,
    };
  },
  validations: {
    form: {
      firstname: { required },
      lastname: { required },
      email: { required, email },
    },
  },
  components: {
    Breadcrumb,
  },
  mounted() {
    this.getcustomer();
  },
  methods: {
    validateState(name) {
      const { $dirty, $error } = this.$v.form[name];
      return $dirty ? !$error : null;
    },
    async getcustomer() {
      try {
        const response = await customerService.find(this.$route.params.id);
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
    async updateCustomer() {
      try {
        this.submitted = true;
        // stop here if form is invalid
        this.$v.$touch();
        if (this.$v.$invalid) {
          return;
        }

        const response = await customerService.update(
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
                path: `/customers`,
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
