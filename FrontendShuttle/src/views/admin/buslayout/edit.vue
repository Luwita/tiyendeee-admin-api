<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-12 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <b-form @submit.prevent="updatebustype">
                <b-container>
                  <b-row>
                    <b-col cols="7">
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
                        label="Layout"
                        label-for="layout-input"
                        label-cols-sm="4"
                        label-cols-lg="3"
                        content-cols-sm
                        content-cols-lg="7"
                      >
                        <b-form-select
                          v-model.trim="$v.form.layout.$model"
                          class="mb-3"
                          :state="validateState('layout')"
                        >
                          <template #first>
                            <b-form-select-option :value="null" disabled
                              >-- Please select an layout
                              --</b-form-select-option
                            >
                          </template>

                          <!-- These options will appear after the ones from 'options' prop -->
                          <b-form-select-option value="layout-1"
                            >1 X 1</b-form-select-option
                          >
                          <b-form-select-option value="layout-2"
                            >1 X 2</b-form-select-option
                          >
                          <b-form-select-option value="layout-3"
                            >2 X 1</b-form-select-option
                          >
                          <b-form-select-option value="layout-4"
                            >2 X 2</b-form-select-option
                          >
                          <b-form-select-option value="layout-5"
                            >2 X 3</b-form-select-option
                          >
                          <b-form-select-option value="layout-6"
                            >3 X 2</b-form-select-option
                          >
                        </b-form-select>
                        <b-form-invalid-feedback
                          v-if="submitted && !$v.form.layout.required"
                          >layout is a required field.</b-form-invalid-feedback
                        >
                      </b-form-group>

                      <b-form-group
                        label="max Seats"
                        label-for="max-seats-input"
                        label-cols-sm="4"
                        label-cols-lg="3"
                        content-cols-sm
                        content-cols-lg="7"
                      >
                        <b-form-input
                          id="max-seats-input"
                          v-model.trim="$v.form.max_seats.$model"
                          type="number"
                          min="0"
                          placeholder="Enter max seats."
                          :class="{
                            'is-invalid': submitted && $v.form.max_seats.$error,
                          }"
                          :state="validateState('max_seats')"
                          @keyup="myFunction"
                        ></b-form-input>
                        <div
                          v-if="submitted && !$v.form.max_seats.required"
                          class="invalid-feedback"
                        >
                          max seats is required
                        </div>
                      </b-form-group>

                      <!-- <b-form-group
              label="Last Seat"
              label-for="last-seat-input"
              label-cols-sm="4"
              label-cols-lg="3"
              content-cols-sm
              content-cols-lg="7"
            >
              <b-form-input
                id="last-seat-input"
                v-model.trim="form.last_seat"
                type="number"
                min="0"
              ></b-form-input>
              <span>Leave it blank if there is no unique layout .</span>
            </b-form-group> -->

                      <b-form-group
                        label="Seat Number"
                        label-for="seat-number-input"
                        label-cols-sm="4"
                        label-cols-lg="3"
                        content-cols-sm
                        content-cols-lg="7"
                      >
                        <b-form-textarea
                          id="seat-numbers"
                          v-model="form.seat_numbers"
                          placeholder="Enter something..."
                          rows="6"
                          max-rows="14"
                          size="lg"
                        ></b-form-textarea>
                        <small class="text-danger"
                          >Use comma to separate the input</small
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
                    <b-col cols="5">
                      <div class="box-body row" id="layouts">
                        <!----------- Left Row Start ---------->
                        <div class="col-md-3 nopadding">
                          <div class="row">
                            <div class="col-md-4 nopadding apps-container">
                              <div
                                class="col-md-12 nopadding app"
                                v-for="row in seat_details['left_1']
                                  ? seat_details['left_1']
                                  : form.combine_seats[0]"
                                :key="row.bus"
                              >
                                <div :class="row.type"></div>
                                <span>{{ row.seat_no }}</span>
                              </div>
                            </div>
                            <div class="col-md-4 nopadding apps-container">
                              <div
                                class="col-md-12 nopadding app"
                                v-for="row in seat_details['left_2']
                                  ? seat_details['left_2']
                                  : form.combine_seats[1]"
                                :key="row.bus"
                              >
                                <div :class="row.type"></div>
                                <span>{{ row.seat_no }}</span>
                              </div>
                            </div>
                            <div class="col-md-4 nopadding apps-container">
                              <div
                                class="col-md-12 nopadding app"
                                v-for="row in seat_details['left_3']
                                  ? seat_details['left_3']
                                  : form.combine_seats[2]"
                                :key="row.bus"
                              >
                                <div :class="row.type"></div>
                                <span>{{ row.seat_no }}</span>
                              </div>
                            </div>
                          </div>
                        </div>
                        <!----------- Left Row Start ---------->

                        <!----------- Middle Row  ---------->
                        <div class="col-md-2 nopadding"></div>
                        <!----------- Middle Row  ---------->

                        <!----------- Right Row Start ---------->
                        <div class="col-md-3 nopadding">
                          <div class="row">
                            <div class="col-md-4 nopadding apps-container">
                              <div
                                class="col-md-12 nopadding app"
                                v-for="row in seat_details['right_1']
                                  ? seat_details['right_1']
                                  : form.combine_seats[3]"
                                :key="row.bus"
                              >
                                <div :class="row.type"></div>
                                <span>{{ row.seat_no }}</span>
                              </div>
                            </div>
                            <div class="col-md-4 nopadding apps-container">
                              <div
                                class="col-md-12 nopadding app"
                                v-for="row in seat_details['right_2']
                                  ? seat_details['right_2']
                                  : form.combine_seats[4]"
                                :key="row.bus"
                              >
                                <div :class="row.type"></div>
                                <span>{{ row.seat_no }}</span>
                              </div>
                            </div>
                            <div class="col-md-4 nopadding apps-container">
                              <div
                                class="col-md-12 nopadding app"
                                v-for="row in seat_details['right_3']
                                  ? seat_details['right_3']
                                  : form.combine_seats[5]"
                                :key="row.bus"
                              >
                                <div :class="row.type"></div>
                                <span>{{ row.seat_no }}</span>
                              </div>
                            </div>
                          </div>
                        </div>

                        <!----------- Right Row Start ---------->

                        <!----------- Last Row Start ---------->

                        <!-- <div class="col-md-12 nopadding apps-container">
                            <div class="row">
                              <div
                                class="col-md-2 nopadding app"
                                v-for="row in last_row"
                                :key="row.bus"
                              >
                                <div :class="row.type"></div>
                                <span>{{ row.seat_no }}</span>
                              </div>
                            </div>
                          </div> -->

                        <!----------- Last Row End ---------->
                      </div>
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
import { buslayoutService } from "../../../services";
import { validationMixin } from "vuelidate";
import { required, integer } from "vuelidate/lib/validators";

export default {
  name: "buslayoutedit",
  mixins: [validationMixin],
  data() {
    return {
      breadcrumbs: {
        title: "Edit Bus layout",
        b1: "Manage Bus layouts",
        b2: "Bus layouts",
        b3: "Index",
        link: true,
        name: "buslayouts",
      },
      options: [
        { text: "Active", value: "true" },
        { text: "Inactive", value: "false", default: true },
      ],
      form: {
        name: "",
        status: "",
        max_seats: "",
        layout: "",
        seat_numbers: "",
        combine_seats: [],
      },
      submitted: false,
      loading: false,
      error: "",
      success: "",
      layout_count: 0,
      layout_name: "",
      each_row: 0,
      diff_count: 0,
      layout_var: [],
      seat_details: [],
      totallast: "",
      last_row: [],
    };
  },
  validations: {
    form: {
      name: { required },
      status: { required },
      max_seats: { required, integer },
      layout: { required },
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
    myFunction() {
      var inner;
      inner = this.form.max_seats;
      var s = "1";
      //  count = this.form.max_seats;
      //
      // if (this.form.last_seat > 0) {
      //   inner = parseInt(count - this.form.last_seat);
      //   var totallast = "";
      //   this.totallast = "count";
      //   if (this.form.last_seat < 5) {
      //     totallast = parseInt(5 - this.form.last_seat);
      //   } else {
      //     totallast = "0";
      //   }

      //   this.last_row = [];
      //   this.totallast = parseInt(this.form.last_seat) + parseInt(totallast);
      //   var tsum = parseInt(count);
      //   console.log(
      //     "count",
      //     count,
      //     "inner",
      //     inner,
      //     "tsum",
      //     tsum,
      //     "totallast",
      //     totallast
      //   );
      //   for (var k = inner; k < tsum; k++) {
      //     this.last_row.push({ bus: k, type: "sleeper", seat_no: "G" + s });
      //     s++;
      //   }
      // }
      // console.log("last_row", this.last_row);

      var layout_divide = [0, 2, 3, 3, 4, 4, 4];
      var layout_name = this.form.layout.split("-");
      this.layout_name = layout_name;
      var layout_type = parseInt(layout_name[1]);
      this.layout_count = layout_divide[layout_type];
      console.log(
        "layout_type ",
        layout_type,
        "layout count ",
        this.layout_count
      );
      var each_row = parseInt(inner / this.layout_count);
      var total_count = each_row * this.layout_count;
      var diff_count = parseInt(inner - total_count);
      this.each_row = each_row;
      this.diff_count = diff_count;

      // console.log("inner = " + inner);
      // console.log("layout_count = " + this.layout_count);
      // console.log("each_row = " + each_row);
      // console.log("diff_count = " + diff_count);

      var layout_var = [];
      layout_var[1] = ["left_1", "right_2"];
      layout_var[2] = ["left_1", "right_1", "right_2"];
      layout_var[3] = ["left_1", "left_2", "right_2"];
      layout_var[4] = ["left_1", "left_2", "right_1", "right_2"];
      layout_var[5] = ["left_1", "left_2", "right_1", "right_2", "right_3"];
      layout_var[6] = ["left_1", "left_2", "left_3", "right_1", "right_2"];

      var seat_no = [];
      seat_no = ["A", "B", "C", "D", "E", "F"];
      this.layout_var = layout_var;
      this.seat_details = [];

      this.seat_details["left_1"] = [];
      this.seat_details["left_2"] = [];
      this.seat_details["left_3"] = [];
      this.seat_details["right_1"] = [];
      this.seat_details["right_2"] = [];
      this.seat_details["right_3"] = [];

      var seat_number = [];
      let is_ladies = false;
      for (var i = 0; i < layout_var[layout_type].length; i++) {
        var seat_count = each_row;

        if (i == parseInt(layout_var[layout_type].length - 1)) {
          seat_count = each_row + diff_count;
        }
        if (i == 0) {
          s = "";
        } else {
          s = seat_count * i;
        }
        for (var j = 0; j < seat_count; j++) {
          var seat = parseInt(1 + j);
          var num = parseInt(s + j);

          if (layout_type == 5 || layout_type == 6) {
            is_ladies =
              seat_no[i] + seat == "A1" ||
              seat_no[i] + seat == "B1" ||
              seat_no[i] + seat == "A2" ||
              seat_no[i] + seat == "B2" ||
              seat_no[i] + seat == "C1" ||
              seat_no[i] + seat == "D1" ||
              seat_no[i] + seat == "C2" ||
              seat_no[i] + seat == "D2" ||
              seat_no[i] + seat == "E1" ||
              seat_no[i] + seat == "E2"
                ? true
                : false;
          } else if (layout_type == 4) {
            is_ladies =
              seat_no[i] + seat == "A1" ||
              seat_no[i] + seat == "B1" ||
              seat_no[i] + seat == "A2" ||
              seat_no[i] + seat == "B2" ||
              seat_no[i] + seat == "C1" ||
              seat_no[i] + seat == "D1" ||
              seat_no[i] + seat == "C2" ||
              seat_no[i] + seat == "D2"
                ? true
                : false;
          } else if (layout_type == 3) {
            is_ladies =
              seat_no[i] + seat == "A1" ||
              seat_no[i] + seat == "B1" ||
              seat_no[i] + seat == "A2" ||
              seat_no[i] + seat == "B2" ||
              seat_no[i] + seat == "C1" ||
              seat_no[i] + seat == "C2"
                ? true
                : false;
          } else if (layout_type == 2) {
            is_ladies =
              seat_no[i] + seat == "A1" ||
              seat_no[i] + seat == "A2" ||
              seat_no[i] + seat == "B1" ||
              seat_no[i] + seat == "B2" ||
              seat_no[i] + seat == "C1" ||
              seat_no[i] + seat == "C2"
                ? true
                : false;
          }

          this.seat_details[layout_var[layout_type][i]].push({
            bus: num,
            type: is_ladies ? "sleeper-pink" : "sleeper",
            seat_no: seat_no[i] + seat,
            is_ladies: is_ladies,
          });

          seat_number.push(seat_no[i] + seat);
        }
      }
      var combine_seats = [
        ...this.seat_details["left_1"],
        ...this.seat_details["left_2"],
        ...this.seat_details["left_3"],
        ...this.seat_details["right_1"],
        ...this.seat_details["right_2"],
        ...this.seat_details["right_3"],
      ];

      this.form.seat_numbers = combine_seats
        .map(function (x) {
          return x.seat_no;
        })
        .join(" , ");

      if (this.last_row.length > 0) {
        var last_row = this.last_row
          .map(function (x) {
            return x.seat_no;
          })
          .join(" , ");
        this.form.seat_numbers += " , " + last_row;
      }

      this.form.combine_seats = [
        this.seat_details["left_1"],
        this.seat_details["left_2"],
        this.seat_details["left_3"],
        this.seat_details["right_1"],
        this.seat_details["right_2"],
        this.seat_details["right_3"],
      ];

      // console.log("checkkval, num", this.form.max_seats);
    },
    async getbustype() {
      try {
        const response = await buslayoutService.find(this.$route.params.id);
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

        const response = await buslayoutService.update(
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
                path: `/buslayouts`,
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

<style lang="scss" scoped>
#seat-numbers {
  color: green;
  font-weight: 700;
}
.box-body {
  border-top-left-radius: 0;
  border-top-right-radius: 0;
  border-bottom-right-radius: 3px;
  border-bottom-left-radius: 3px;
  padding: 10px;
}
.nopadding {
  padding: 0px !important;
}
.sleeper {
  background-image: url("../../../assets/seats/seat.png");
  background-repeat: no-repeat;
  width: 46px;
  margin-top: 10px;
  height: 41px;
}
.sleeper-pink {
  background-image: url("../../../assets/seats/pink.png");
  background-repeat: no-repeat;
  width: 46px;
  margin-top: 10px;
  height: 41px;
}
</style>
