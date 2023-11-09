<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-8 offset-lg-2 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <b-form @submit.prevent="createPass">
                <b-form-group
                  label="No of Ride"
                  label-for="no-of-ride-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="4"
                >
                  <b-form-spinbutton
                    id="demo-sb"
                    v-model.trim="form.no_of_rides"
                    min="1"
                    max="100"
                  ></b-form-spinbutton>
                </b-form-group>
                <b-form-group
                  label="Valid days"
                  label-for="no-of-valid-days-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="4"
                >
                  <b-form-input
                    id="no-of-valid-days-input"
                    v-model.trim="$v.form.no_of_valid_days.$model"
                    type="number"
                    placeholder="Enter no of valid days"
                    :class="{
                      'is-invalid':
                        submitted && $v.form.no_of_valid_days.$error,
                    }"
                    :state="validateState('no_of_valid_days')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.no_of_valid_days.required"
                    class="invalid-feedback"
                  >
                    no of days is required
                  </div>
                </b-form-group>
                <b-form-group
                  label="Price per km"
                  label-for="price-per-km-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="4"
                >
                  <b-form-input
                    id="price-per-km-input"
                    v-model.trim="$v.form.price_per_km.$model"
                    type="number"
                    step="0.01"
                    value="0.00"
                    placeholder="Enter price per km"
                    :class="{
                      'is-invalid': submitted && $v.form.price_per_km.$error,
                    }"
                    :state="validateState('price_per_km')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.price_per_km.required"
                    class="invalid-feedback"
                  >
                    price per km is required
                  </div>
                </b-form-group>

                <b-form-group
                  label="Discount(%)"
                  label-for="discount-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="4"
                >
                  <b-form-input
                    id="discount-input"
                    v-model.trim="$v.form.discount.$model"
                    type="number"
                    placeholder="Enter discount"
                    :class="{
                      'is-invalid': submitted && $v.form.discount.$error,
                    }"
                    :state="validateState('discount')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.discount.required"
                    class="invalid-feedback"
                  >
                    discount is required
                  </div>
                </b-form-group>

                <b-form-group
                  label="Description"
                  label-for="description-input"
                  invalid-feedback="description is required"
                  class="mt-3"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="9"
                >
                  <vue-editor
                    class="mt-3"
                    v-model="form.description"
                    :class="{
                      'is-invalid': submitted && $v.form.description.$error,
                    }"
                    :state="validateState('description')"
                  ></vue-editor>
                </b-form-group>

                <b-form-group
                  label=" terms"
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
                  content-cols-lg="7"
                >
                  <b-form-radio-group
                    :options="options"
                    v-model="form.status"
                    name="status"
                  ></b-form-radio-group>

                  <!-- <b-form-invalid-feedback
                    v-if="submitted && !$v.form.status.required"
                    >Please select one</b-form-invalid-feedback
                  > -->
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
import { required, numeric } from "vuelidate/lib/validators";
import { passService } from "../../../services";
import { VueEditor } from "vue2-editor";

export default {
  name: "passcreate",
  mixins: [validationMixin],
  data() {
    return {
      breadcrumbs: {
        title: "Create Pass",
        b1: "Manage Passes",
        b2: "Passes",
        b3: "Index",
        link: true,
        name: "passes",
      },
      form: {
        no_of_rides: "",
        no_of_valid_days: "",
        price_per_km: "",
        discount: "",
        status: "",
        terms: "",
        description: "",
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
    VueEditor,
  },
  validations: {
    form: {
      no_of_valid_days: { required },
      price_per_km: { required },
      discount: { required, numeric },
      description: { required },
      terms: { required },
    },
  },
  methods: {
    validateState(name) {
      const { $dirty, $error } = this.$v.form[name];
      return $dirty ? !$error : null;
    },
    async createPass() {
      try {
        this.submitted = true;

        // stop here if form is invalid
        this.$v.$touch();
        if (this.$v.$invalid) {
          return;
        }
        const response = await passService.create(this.form);
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
                path: `/passes`,
              }),
            2000
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
