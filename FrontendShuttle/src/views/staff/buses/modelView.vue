<template>
  <div>
    <section class="tables">
      <b-container>
        <b-row>
          <b-col cols="4">Bus Name</b-col>
          <b-col cols="8">{{ modalData.name }} </b-col>

          <b-col cols="4">Reg No.</b-col>
          <b-col cols="8">{{ modalData.reg_no }} </b-col>

          <b-col cols="4">Max Seats</b-col>
          <b-col cols="8">{{ modalData.max_seats }} </b-col>

          <b-col cols="4">Type</b-col>
          <b-col cols="8">{{ modalData.type }} </b-col>

          <b-col cols="4">Status</b-col>
          <b-col cols="8">{{ modalData.status }}</b-col>

          <b-col cols="4">Created By</b-col>
          <b-col cols="8">{{ modalData.created_by }}</b-col>

          <b-col cols="4">Bus Picture</b-col>
          <b-col cols="8"
            ><b-img
              class="img-fluid"
              width="350"
              height="200"
              :src="modalData.picture"
            ></b-img
          ></b-col>
          <b-col cols="12"
            ><p><b class="text-uppercase mt-3 mb-3">Documents</b></p></b-col
          >
          <b-col cols="4">Certificate Registration</b-col>
          <b-col cols="8"
            ><b-img
              class="img-fluid"
              width="350"
              height="200"
              :src="modalData.certificate_registration"
            ></b-img
          ></b-col>

          <b-col cols="4">Certificate Pollution</b-col>
          <b-col cols="8"
            ><b-img
              class="img-fluid"
              width="350"
              height="200"
              :src="modalData.certificate_pollution"
            ></b-img
          ></b-col>

          <b-col cols="4">Certificate Insurance</b-col>
          <b-col cols="8"
            ><b-img
              class="img-fluid"
              width="350"
              height="200"
              :src="modalData.certificate_insurance"
            ></b-img
          ></b-col>

          <b-col cols="4">Certificate Fitness</b-col>
          <b-col cols="8"
            ><b-img
              class="img-fluid"
              width="350"
              height="200"
              :src="modalData.certificate_fitness"
            ></b-img
          ></b-col>

          <b-col cols="4">Certificate permit</b-col>
          <b-col cols="8"
            ><b-img
              class="img-fluid"
              width="350"
              height="200"
              :src="modalData.certificate_permit"
            ></b-img
          ></b-col>
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
