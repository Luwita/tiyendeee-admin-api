<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-4 offset-lg-4 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <b-form @submit.prevent="updatebustype">
                <b-container>
                  <b-row>
                    <b-col cols="12">
                      <b-form-group
                        label="Name"
                        label-for="name-input"
                        label-cols-sm="4"
                        label-cols-lg="3"
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
                          name is required
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
                          v-model.trim="$v.form.status.$model"
                          name="status"
                        ></b-form-radio-group>

                        <b-form-invalid-feedback
                          v-if="submitted && !$v.form.status.required"
                          >Please select one</b-form-invalid-feedback
                        >
                      </b-form-group>

                      <b-form-group class="col-md-6 offset-md-4">
                        <b-button
                          type="submit"
                          class="btn btn-lg btn-success text-center"
                          >Submit</b-button
                        >
                      </b-form-group>
                    </b-col>
                  </b-row>
                </b-container>
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
import { bustypeService } from "../../../services";
import { validationMixin } from "vuelidate";
import { required } from "vuelidate/lib/validators";

export default {
  name: "bustypeedit",
  mixins: [validationMixin],
  data() {
    return {
      breadcrumbs: {
        title: "Edit Bus type",
        b1: "Manage Bus types",
        b2: "Bus types",
        b3: "Index",
        link: true,
        name: "bustypes",
      },
      options: [
        { text: "Active", value: "true" },
        { text: "Inactive", value: "false", default: true },
      ],
      form: {
        name: "",
        status: "",
      },
      submitted: false,
      loading: false,
      error: "",
      success: "",
    };
  },
  validations: {
    form: {
      name: { required },
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
    async getbustype() {
      try {
        const response = await bustypeService.find(this.$route.params.id);
        if (response.status) {
          this.form = response.data;
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
    async updatebustype() {
      try {
        this.submitted = true;
        // stop here if form is invalid
        this.$v.$touch();
        if (this.$v.$invalid) {
          return;
        }

        const response = await bustypeService.update(
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
                path: `/bustypes`,
              }),
            3000
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
    this.getbustype();
  },
};
</script>

<style lang="scss" scoped></style>
