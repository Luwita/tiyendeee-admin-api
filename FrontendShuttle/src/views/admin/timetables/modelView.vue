<template>
  <div>
    <section class="tables">
      <b-container>
        <b-row>
          <b-col cols="4">Route Name</b-col>
          <b-col cols="8">{{ form.route_name }}</b-col>
          <b-col cols="4">Bus Name</b-col>
          <b-col cols="8">{{ form.bus_name }}</b-col>
          <b-col cols="4">Direction</b-col>
          <b-col cols="8">{{ form.direction }}</b-col>
          <b-col cols="4">Time</b-col>
          <b-col cols="8">{{ form.time }}</b-col>
          <b-col cols="4">Status</b-col>
          <b-col cols="8">{{
            form.status == true ? "Active" : "Inactive"
          }}</b-col>

          <b-col cols="4">Created At</b-col>
          <b-col cols="8">{{ form.createdAt }} </b-col>
        </b-row>
      </b-container>
    </section>
  </div>
</template>

<script>
import moment from "moment-timezone";

export default {
  name: "modelView",
  props: ["form"],
  methods: {
    dateConvert(data) {
      return moment.utc(data).tz("Asia/Kolkata").format("LLL");
    },
  },
};
</script>

<style></style>
