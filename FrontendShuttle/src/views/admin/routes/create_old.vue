<template>
  <section class="tables">
    <b-form @submit.prevent="createRoute">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />

        <div class="col-lg-12 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <h4 class="card-title">Create Route</h4>
              <b-form-group
                label="Departure / Arrival location"
                label-for="title-input"
                label-cols-sm="4"
                label-cols-lg="3"
                content-cols-sm
                content-cols-lg="7"
              >
                <v-select
                  @search="fetchLocations"
                  label="type"
                  :filterable="false"
                  :options="options"
                  v-model="form.location"
                >
                  <template slot="no-options">
                    type to search locations..
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
                label="Title"
                label-for="title-input"
                label-cols-sm="4"
                label-cols-lg="3"
                content-cols-sm
                content-cols-lg="7"
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
            </div>
          </div>
        </div>

        <div class="col-lg-12 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <table id="ferriRouteTable" class="table table-bordered">
                <thead>
                  <tr>
                    <th scope="col" class="col-md-3">Location</th>
                    <th scope="col" class="col-md-3">Duration</th>
                    <th scope="col" class="col-md-3">Minimum Fare</th>
                    <th scope="col" class="col-md-3">Price per km</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  <tr
                    id="fr_Route_122"
                    v-for="(data, k) in this.form.stops"
                    :key="k"
                  >
                    <td scope="row">
                      <div class="row">
                        <div class="col-sm-12">
                          <div class="form-group">
                            <v-select
                              @search="fetchPDLocations"
                              label="type"
                              :filterable="false"
                              :options="options"
                              v-model="data.location"
                            >
                              <template slot="no-options">
                                type to search locations..
                              </template>

                              <template slot="option" slot-scope="option">
                                <div class="d-center">
                                  {{ option.title }}
                                </div>
                              </template>
                              <template
                                slot="selected-option"
                                slot-scope="option"
                              >
                                <div class="selected d-center">
                                  {{ option.title }}
                                </div>
                              </template>
                            </v-select>
                          </div>
                        </div>
                      </div>
                    </td>
                    <td>
                      <div class="row">
                        <label class="col-sm-3">if pick-up</label>
                        <div class="col-sm-6">
                          <input
                            type="number"
                            class="form-control"
                            v-model="data.duration_pickup"
                            value="1"
                          />
                        </div>
                        <label class="col-sm-3">mins</label>
                      </div>
                      <div class="row">
                        <label class="col-sm-3">if drop</label>
                        <div class="col-sm-6">
                          <input
                            type="number"
                            class="form-control"
                            v-model="data.duration_drop"
                            value="1"
                          />
                        </div>
                        <label class="col-sm-3">mins</label>
                      </div>
                    </td>
                    <td>
                      <div class="row">
                        <label class="col-sm-3">if pick-up</label>
                        <div class="col-sm-9">
                          <input
                            type="number"
                            class="form-control"
                            v-model="data.minimum_fare_pickup"
                            value="1"
                          />
                        </div>
                      </div>
                      <div class="row">
                        <label class="col-sm-3">if drop</label>
                        <div class="col-sm-9">
                          <input
                            type="number"
                            class="form-control"
                            v-model="data.minimum_fare_drop"
                            value="1"
                          />
                        </div>
                      </div>
                    </td>

                    <td>
                      <div class="row">
                        <label class="col-sm-3">if pick-up</label>
                        <div class="col-sm-9">
                          <div class="input-group">
                            <div class="input-group-prepend">
                              <span
                                class="input-group-text bg-primary text-white"
                                >₹</span
                              >
                            </div>
                            <input
                              type="text"
                              v-model="data.price_pickup"
                              class="form-control"
                              aria-label="Amount (to the nearest rupee)"
                            />
                            <div class="input-group-append">
                              <span class="input-group-text">.00</span>
                            </div>
                          </div>
                        </div>
                      </div>

                      <div class="row">
                        <label class="col-sm-3">if drop</label>
                        <div class="col-sm-9">
                          <div class="input-group">
                            <div class="input-group-prepend">
                              <span
                                class="input-group-text bg-primary text-white"
                                >₹</span
                              >
                            </div>
                            <input
                              type="text"
                              v-model="data.price_drop"
                              class="form-control"
                              aria-label="Amount (to the nearest rupee)"
                            />
                            <div class="input-group-append">
                              <span class="input-group-text">.00</span>
                            </div>
                          </div>
                        </div>
                      </div>
                    </td>

                    <td class="trashIconContainer">
                      <span v-if="k > 0">
                        <i
                          @click="removeRow(k, data)"
                          class="mdi mdi-delete-forever mdi-24px"
                        ></i>
                      </span>
                    </td>
                  </tr>
                </tbody>
              </table>
              <div class="d-flex justify-content-center">
                <input
                  name="addpd"
                  @click="addRow"
                  class="btn btn-outline-primary mt-4"
                  type="button"
                  value="+ Add pick-up / drop off location"
                />
              </div>
            </div>
          </div>
        </div>

        <div class="col-lg-12 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <b-form-group
                label="Bus"
                label-for="bus-type-input"
                label-cols-sm="4"
                label-cols-lg="3"
                content-cols-sm
                content-cols-lg="7"
              >
                <b-form-select
                  v-model.trim="$v.form.busId.$model"
                  :options="buses"
                  :class="{
                    'is-invalid': submitted && $v.form.busId.$error,
                  }"
                  :state="validateState('busId')"
                >
                  <template #first>
                    <b-form-select-option :value="null" disabled
                      >-- Please select an bus --</b-form-select-option
                    >
                  </template>
                </b-form-select>
                <div
                  v-if="submitted && !$v.form.busId.required"
                  class="invalid-feedback"
                >
                  bus is required
                </div>
              </b-form-group>

              <b-form-group>
                <b-button type="submit" class="btn btn-success text-center"
                  >Submit</b-button
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
import { validationMixin } from "vuelidate";
import { required } from "vuelidate/lib/validators";
import vSelect from "vue-select";
import "vue-select/src/scss/vue-select.scss";
import { locationService, busService, routeService } from "../../../services";
import lodash from "lodash";

export default {
  mixins: [validationMixin],
  name: "routes",
  components: {
    Breadcrumb,
    vSelect,
  },
  data() {
    return {
      breadcrumbs: {
        title: "Create Route",
        b1: "Manage Routes",
        b2: "Routes",
        b3: "Index",
        link: true,
        name: "Route",
      },
      submitted: false,
      loading: false,
      options: [],
      pds: [],
      form: {
        title: "",
        location: "",
        busId: null,
        stops: [
          {
            location: {},
            duration_pickup: "",
            duration_drop: "",
            minimum_fare_pickup: "",
            minimum_fare_drop: "",
            price_pickup: "",
            price_drop: "",
          },
        ],
        status: false,
      },
      buses: [],
    };
  },
  validations: {
    form: {
      title: { required },
      busId: { required },
    },
  },
  methods: {
    addRow: function () {
      this.form.stops.push({
        location: {},
        duration_pickup: "",
        duration_drop: "",
        minimum_fare_pickup: "",
        minimum_fare_drop: "",
        price_pickup: "",
        price_drop: "",
      });
    },
    removeRow(index, data) {
      console.log("data", index, data);
      var idx = this.form.stops.indexOf(data);
      console.log(idx, index);
      if (idx > -1) {
        this.form.stops.splice(idx, 1);
      }
    },
    validateState(name) {
      const { $dirty, $error } = this.$v.form[name];
      return $dirty ? !$error : null;
    },
    loadBusItems() {
      busService.load().then((response) => {
        this.buses = response.data;
      });
    },
    async createRoute() {
      try {
        this.submitted = true;

        // stop here if form is invalid
        this.$v.$touch();
        if (this.$v.$invalid) {
          return;
        }

        const response = await routeService.create(this.form);
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
                path: `/${this.$store.state.auth.role}/routes`,
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
    fetchLocations(search, loading) {
      if (search.length) {
        loading(true);
        let type = "DA";
        this.search(loading, search, type, this);
      }
    },
    search: lodash.debounce(async (loading, search, type, vm) => {
      try {
        const response = await locationService.search({
          type: type,
          search: search,
        });
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
    fetchPDLocations(search, loading) {
      if (search.length) {
        loading(true);
        let type = "PD";
        this.pdSearch(loading, search, type, this);
      }
    },
    pdSearch: lodash.debounce(async (loading, search, type, vm) => {
      try {
        const response = await locationService.search({
          type: type,
          search: search,
        });
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
  },
  mounted() {
    this.loadBusItems();
  },
};
</script>

<style scoped>
.trashIconContainer i {
  color: #e74c3c;
  cursor: pointer;
}
.d-center {
  display: flex;
  align-items: center;
}
.v-select .dropdown li {
  border-bottom: 1px solid rgba(112, 128, 144, 0.1);
}

.v-select .dropdown li:last-child {
  border-bottom: none;
}

.v-select .dropdown li a {
  padding: 10px 20px;
  width: 100%;
  font-size: 1.25em;
  color: #3c3c3c;
}

.v-select .dropdown-menu .active > a {
  color: #fff;
}
.vs__dropdown-toggle {
  padding: 0 0 10px 0 !important;
}
.vs__actions {
  padding: 13px 6px 0 3px !important;
}
.vs__selected {
  margin: 14px 2px 0px 2px !important;
}
</style>
