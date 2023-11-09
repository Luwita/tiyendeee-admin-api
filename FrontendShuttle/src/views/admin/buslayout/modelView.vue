<template>
  <div>
    <section class="tables">
      <b-container>
        <b-row>
          <b-col cols="4">Full Name</b-col>
          <b-col cols="8">{{ modalData.name }} </b-col>

          <b-col cols="4">Status</b-col>
          <b-col cols="8">{{ modalData.status }}</b-col>
          <b-col cols="4">Layout</b-col>
          <b-col cols="8">{{ modalData.layout }}</b-col>
          <b-col cols="4">Max Seats</b-col>
          <b-col cols="8">{{ modalData.max_seats }}</b-col>
          <b-col cols="4">Bus seats</b-col>
          <b-col cols="8">
            <div class="box-body row" id="layouts">
              <!----------- Left Row Start ---------->
              <div class="col-md-3 nopadding">
                <div class="row">
                  <div class="col-md-4 nopadding apps-container">
                    <div
                      class="col-md-12 nopadding app"
                      v-for="row in modalData.combine_seats[0]"
                      :key="row.bus"
                    >
                      <div :class="row.type"></div>
                      <span>{{ row.seat_no }}</span>
                    </div>
                  </div>
                  <div class="col-md-4 nopadding apps-container">
                    <div
                      class="col-md-12 nopadding app"
                      v-for="row in modalData.combine_seats[1]"
                      :key="row.bus"
                    >
                      <div :class="row.type"></div>
                      <span>{{ row.seat_no }}</span>
                    </div>
                  </div>
                  <div class="col-md-4 nopadding apps-container">
                    <div
                      class="col-md-12 nopadding app"
                      v-for="row in modalData.combine_seats[2]"
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
                      v-for="row in modalData.combine_seats[3]"
                      :key="row.bus"
                    >
                      <div :class="row.type"></div>
                      <span>{{ row.seat_no }}</span>
                    </div>
                  </div>
                  <div class="col-md-4 nopadding apps-container">
                    <div
                      class="col-md-12 nopadding app"
                      v-for="row in modalData.combine_seats[4]"
                      :key="row.bus"
                    >
                      <div :class="row.type"></div>
                      <span>{{ row.seat_no }}</span>
                    </div>
                  </div>
                  <div class="col-md-4 nopadding apps-container">
                    <div
                      class="col-md-12 nopadding app"
                      v-for="row in modalData.combine_seats[5]"
                      :key="row.bus"
                    >
                      <div :class="row.type"></div>
                      <span>{{ row.seat_no }}</span>
                    </div>
                  </div>
                </div>
              </div>
              <!----------- Right Row Start ---------->
            </div>
          </b-col>
        </b-row>
      </b-container>
    </section>
  </div>
</template>

<script>
import moment from "moment-timezone";

export default {
  name: "modelView",
  props: ["modalData"],
  methods: {
    dateConvert(data) {
      return moment.utc(data).tz("Asia/Kolkata").format("LLL");
    },
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
</style>
