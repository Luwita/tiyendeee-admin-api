<template>
  <div>
    <section class="tables">
      <b-container>
        <b-row>
          <b-col cols="4">No of rides </b-col>
          <b-col cols="8">{{ modalData.no_of_rides }}</b-col>

          <b-col cols="4">No of valid days</b-col>
          <b-col cols="8">{{ modalData.no_of_valid_days }}</b-col>
          <b-col cols="4">Price per km</b-col>
          <b-col cols="8">{{ modalData.price_per_km }}</b-col>
          <b-col cols="4">Discount</b-col>
          <b-col cols="8">{{ modalData.discount }}</b-col>
          <b-col cols="4">Description</b-col>
          <b-col cols="8"><span v-html="modalData.description"></span> </b-col>

          <b-col cols="4">Terms</b-col>
          <b-col cols="8"><span v-html="modalData.terms"></span> </b-col>

          <b-col cols="4">Status</b-col>
          <b-col cols="8">{{ modalData.status }}</b-col>

          <b-col cols="4">Created At</b-col>
          <b-col cols="8">{{ dateConvert(modalData.createdAt) }}</b-col>
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

<style></style>
