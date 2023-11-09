<template>
  <div>
    <section class="tables">
      <b-container>
        <b-row>
          <b-col cols="4">Pick Address</b-col>
          <b-col cols="8">{{ modalData.pickup_address }}</b-col>

          <b-col cols="4">Drop Address</b-col>
          <b-col cols="8">{{ modalData.drop_address }}</b-col>
          <b-col cols="4">Customer Name</b-col>
          <b-col cols="8">{{ modalData.userId_firstname }}</b-col>

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
