<template>
  <div>
    <section class="tables">
      <b-container>
        <b-row>
          <b-col cols="4">Offer Name</b-col>
          <b-col cols="8">{{ modalData.name }} </b-col>

          <b-col cols="4">Start Date</b-col>
          <b-col cols="8">{{ modalData.start_date }} </b-col>

          <b-col cols="4">End Date</b-col>
          <b-col cols="8">{{ modalData.end_date }} </b-col>

          <b-col cols="4">Offer Code</b-col>
          <b-col cols="8">{{ modalData.code }} </b-col>

          <b-col cols="4">Discount</b-col>
          <b-col cols="8">{{ modalData.discount }} </b-col>

          <b-col cols="4">Route Name</b-col>
          <b-col cols="8">{{ modalData.route_name }} </b-col>

          <b-col cols="4">Status</b-col>
          <b-col cols="8">{{ modalData.status }}</b-col>

          <b-col cols="4">Created By</b-col>
          <b-col cols="8">{{ modalData.created_by }}</b-col>

          <b-col cols="4">Offer Picture</b-col>
          <b-col cols="8"
            ><b-img
              class="img-fluid"
              width="350"
              height="200"
              :src="modalData.picture"
            ></b-img
          ></b-col>
          <br />
          <b-col cols="4">Terms</b-col>
          <b-col cols="8"><span v-html="modalData.terms"></span> </b-col>
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
