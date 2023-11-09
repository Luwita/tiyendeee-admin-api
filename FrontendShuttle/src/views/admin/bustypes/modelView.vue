<template>
  <div>
    <section class="tables">
      <b-container>
        <b-row>
          <b-col cols="4">Name</b-col>
          <b-col cols="8">{{ modalData.name }} </b-col>

          <b-col cols="4">Status</b-col>
          <b-col cols="8">{{ modalData.status }}</b-col>
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

<style lang="scss" scoped></style>
