<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-10 offset-lg-1 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <h4 class="card-title">Edit bus</h4>
              <b-form @submit.prevent="updateBus">
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
                  label="Reg No."
                  label-for="reg-no-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="reg-no-input"
                    v-model.trim="$v.form.reg_no.$model"
                    type="text"
                    placeholder="Enter reg no."
                    :class="{
                      'is-invalid': submitted && $v.form.reg_no.$error,
                    }"
                    :state="validateState('reg_no')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.reg_no.required"
                    class="invalid-feedback"
                  >
                    reg no is required
                  </div>
                </b-form-group>

                <b-form-group
                  label="Model No."
                  label-for="model-no-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="model-no-input"
                    v-model.trim="$v.form.model_no.$model"
                    type="text"
                    placeholder="Enter model no."
                    :class="{
                      'is-invalid': submitted && $v.form.model_no.$error,
                    }"
                    :state="validateState('model_no')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.model_no.required"
                    class="invalid-feedback"
                  >
                    model no is required
                  </div>
                </b-form-group>

                <b-form-group
                  label="Chassis No."
                  label-for="chassis-no-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="chassis-no-input"
                    v-model.trim="$v.form.chassis_no.$model"
                    type="text"
                    placeholder="Enter chassis no."
                    :class="{
                      'is-invalid': submitted && $v.form.chassis_no.$error,
                    }"
                    :state="validateState('chassis_no')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.chassis_no.required"
                    class="invalid-feedback"
                  >
                    chassis no is required
                  </div>
                </b-form-group>

                <b-form-group
                  label="Brand No"
                  label-for="brand-no-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-input
                    id="chassis-no-input"
                    v-model.trim="$v.form.brand.$model"
                    type="text"
                    placeholder="Enter brand name."
                    :class="{
                      'is-invalid': submitted && $v.form.brand.$error,
                    }"
                    :state="validateState('brand')"
                  ></b-form-input>
                  <div
                    v-if="submitted && !$v.form.brand.required"
                    class="invalid-feedback"
                  >
                    brand no is required
                  </div>
                </b-form-group>

                <b-form-group
                  label="Amenities"
                  label-for="tags-pills"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-tags
                    v-model="form.amenities"
                    no-outer-focus
                    class="mb-2"
                  >
                    <template
                      v-slot="{
                        tags,
                        inputAttrs,
                        inputHandlers,
                        addTag,
                        removeTag,
                      }"
                    >
                      <b-input-group aria-controls="my-custom-tags-list">
                        <input
                          v-bind="inputAttrs"
                          v-on="inputHandlers"
                          placeholder="New amenities - Press enter to add"
                          class="form-control"
                        />
                        <b-input-group-append>
                          <b-button @click="addTag()" variant="primary"
                            >Add</b-button
                          >
                        </b-input-group-append>
                      </b-input-group>
                      <ul
                        id="my-custom-tags-list"
                        class="list-unstyled d-inline-flex flex-wrap mb-0"
                        aria-live="polite"
                        aria-atomic="false"
                        aria-relevant="additions removals"
                      >
                        <!-- Always use the tag value as the :key, not the index! -->
                        <!-- Otherwise screen readers will not read the tag
               additions and removals correctly -->
                        <b-card
                          v-for="tag in form.amenities"
                          :key="tag"
                          :id="`my-custom-tags-tag_${tag.replace(/\s/g, '_')}_`"
                          tag="li"
                          class="mt-1 mr-1"
                          body-class="py-1 pr-2 text-nowrap"
                        >
                          <strong>{{ tag }}</strong>
                          <b-button
                            @click="removeTag(tag)"
                            variant="link"
                            size="sm"
                            :aria-controls="`my-custom-tags-tag_${tag.replace(
                              /\s/g,
                              '_'
                            )}_`"
                            >remove</b-button
                          >
                        </b-card>
                      </ul>
                    </template>
                  </b-form-tags>
                </b-form-group>

                <b-form-group
                  label="Bus Type"
                  label-for="bus-type-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-select
                    v-model.trim="$v.form.bustypeId.$model"
                    :options="bustypes"
                    :class="{
                      'is-invalid': submitted && $v.form.bustypeId.$error,
                    }"
                    :state="validateState('bustypeId')"
                  >
                    <template #first>
                      <b-form-select-option :value="null" disabled
                        >-- Please select an bus type --</b-form-select-option
                      >
                    </template>
                  </b-form-select>
                  <div
                    v-if="submitted && !$v.form.bustypeId.required"
                    class="invalid-feedback"
                  >
                    bus type is required
                  </div>
                </b-form-group>

                <b-form-group
                  label="Bus Layout"
                  label-for="bus-layout-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <b-form-select
                    v-model.trim="$v.form.buslayoutId.$model"
                    :options="buslayouts"
                    :class="{
                      'is-invalid': submitted && $v.form.buslayoutId.$error,
                    }"
                    :state="validateState('buslayoutId')"
                  >
                    <template #first>
                      <b-form-select-option :value="null" disabled
                        >-- Please select an bus layout --</b-form-select-option
                      >
                    </template>
                  </b-form-select>
                  <div
                    v-if="submitted && !$v.form.buslayoutId.required"
                    class="invalid-feedback"
                  >
                    bus layout is required
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
                  label="Bus picture"
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
                  label="Certificate registration"
                  label-for="certificate-registration-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <div v-if="!form.certificate_registration">
                    <b-form-file
                      id="certificate-registration-input"
                      accept="image/jpeg, image/png, image/jpg"
                      placeholder="Choose a Licence or drop it here..."
                      @change="onFileChange($event, 'certificate_registration')"
                    ></b-form-file>
                  </div>
                  <div v-else>
                    <img
                      class="img-fluid"
                      :src="form.certificate_registration"
                      width="350"
                      height="250"
                    />
                    <button
                      class="btn social-btn btn-rounded btn-danger mr-4"
                      @click="removeImage('certificate_registration')"
                    >
                      <i class="mdi mdi-close"></i>
                    </button>
                  </div>
                </b-form-group>
                <b-form-group
                  label="Certificate Pollution"
                  label-for="certificate-pollution-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <div v-if="!form.certificate_pollution">
                    <b-form-file
                      id="certificate-pollution-input"
                      accept="image/jpeg, image/png, image/gif"
                      placeholder="Choose a certificate pollution or drop it here..."
                      @change="onFileChange($event, 'certificate_pollution')"
                    ></b-form-file>
                  </div>
                  <div v-else>
                    <img
                      class="img-fluid"
                      :src="form.certificate_pollution"
                      width="350"
                      height="250"
                    />
                    <button
                      class="btn social-btn btn-rounded btn-danger mr-4"
                      @click="removeImage('certificate_pollution')"
                    >
                      <i class="mdi mdi-close"></i>
                    </button>
                  </div>
                </b-form-group>
                <b-form-group
                  label="Certificate Insurance"
                  label-for="certificate-insurance-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <div v-if="!form.certificate_insurance">
                    <b-form-file
                      id="certificate-insurance-input"
                      accept="image/jpeg, image/png, image/gif"
                      placeholder="Choose a certificate insurance or drop it here..."
                      @change="onFileChange($event, 'certificate_insurance')"
                    ></b-form-file>
                  </div>
                  <div v-else>
                    <img
                      class="img-fluid"
                      :src="form.certificate_insurance"
                      width="350"
                      height="250"
                    />
                    <button
                      class="btn social-btn btn-rounded btn-danger mr-4"
                      @click="removeImage('certificate_insurance')"
                    >
                      <i class="mdi mdi-close"></i>
                    </button>
                  </div>
                </b-form-group>

                <b-form-group
                  label="Certificate Fitness"
                  label-for="certificate-fitness-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <div v-if="!form.certificate_fitness">
                    <b-form-file
                      id="certificate-fitness-input"
                      accept="image/jpeg, image/png, image/gif"
                      placeholder="Choose a certificate fitness or drop it here..."
                      @change="onFileChange($event, 'certificate_fitness')"
                    ></b-form-file>
                  </div>
                  <div v-else>
                    <img
                      class="img-fluid"
                      :src="form.certificate_fitness"
                      width="350"
                      height="250"
                    />
                    <button
                      class="btn social-btn btn-rounded btn-danger mr-4"
                      @click="removeImage('certificate_fitness')"
                    >
                      <i class="mdi mdi-close"></i>
                    </button>
                  </div>
                </b-form-group>

                <b-form-group
                  label="Certificate Permit"
                  label-for="certificate-permit-input"
                  label-cols-sm="4"
                  label-cols-lg="3"
                  content-cols-sm
                  content-cols-lg="7"
                >
                  <div v-if="!form.certificate_permit">
                    <b-form-file
                      id="certificate-ermit-input"
                      accept="image/jpeg, image/png, image/gif"
                      placeholder="Choose a certificate permit or drop it here..."
                      @change="onFileChange($event, 'certificate_permit')"
                    ></b-form-file>
                  </div>
                  <div v-else>
                    <img
                      class="img-fluid"
                      :src="form.certificate_permit"
                      width="350"
                      height="250"
                    />
                    <button
                      class="btn social-btn btn-rounded btn-danger mr-4"
                      @click="removeImage('certificate_permit')"
                    >
                      <i class="mdi mdi-close"></i>
                    </button>
                  </div>
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
import {
  busService,
  buslayoutService,
  bustypeService,
} from "../../../services";
import { validationMixin } from "vuelidate";
import { required } from "vuelidate/lib/validators";

export default {
  name: "busedit",
  mixins: [validationMixin],
  data() {
    return {
      breadcrumbs: {
        title: "Edit bus",
        b1: "Manage buses",
        b2: "bus",
        b3: "Index",
        link: true,
        name: "buses",
      },
      options: [
        { text: "Active", value: "true" },
        { text: "Inactive", value: "false", default: true },
      ],
      form: {
        adminId: "",
        name: "",
        reg_no: "",
        brand: "",
        model_no: "",
        chassis_no: "",
        bustypeId: null,
        buslayoutId: null,
        picture: "",
        amenities: [],
        certificate_registration: "",
        certificate_pollution: "",
        certificate_insurance: "",
        certificate_fitness: "",
        certificate_permit: "",
        status: "",
      },
      submitted: false,
      loading: false,
      error: "",
      success: "",
      bustypes: [],
      buslayouts: [],
    };
  },
  validations: {
    form: {
      name: { required },
      reg_no: { required },
      brand: { required },
      model_no: { required },
      chassis_no: { required },
      bustypeId: { required },
      buslayoutId: { required },
      status: { required },
      certificate_registration: { required },
    },
  },
  components: {
    Breadcrumb,
  },
  methods: {
    async loadBustypeItems() {
      const response = await bustypeService.load();
      if (response.status) {
        this.bustypes = response.data;
      }
    },
    async loadBuslayoutItems() {
      const response = await buslayoutService.load();
      if (response.status) {
        this.buslayouts = response.data;
      }
    },
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
    async getbus() {
      try {
        const response = await busService.find(this.$route.params.id);
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
    async updateBus() {
      try {
        this.submitted = true;
        // stop here if form is invalid
        this.$v.$touch();
        if (this.$v.$invalid) {
          return;
        }

        const response = await busService.update(
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
                path: `/buses`,
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
    this.getbus();
    this.loadBustypeItems();
    this.loadBuslayoutItems();
  },
};
</script>

<style lang="scss" scoped></style>
