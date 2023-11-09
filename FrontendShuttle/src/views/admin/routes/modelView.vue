<template>
  <div>
    <section class="tables">
      <b-container>
        <b-row>
          <b-col cols="4">Title</b-col>
          <b-col cols="8">{{ form.title }}</b-col>

          <b-col cols="4">Bus Name</b-col>
          <b-col cols="8">{{ form.busname }}</b-col>

          <b-col cols="4">Stops</b-col>
          <b-col cols="8">{{ form.total_stops }}</b-col>
          <!-- <b-col cols="4">Longitude</b-col>
          <b-col cols="8">{{ form.location_lng }}</b-col> -->
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
