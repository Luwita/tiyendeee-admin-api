<template>
  <div>
    <table class="table table-bordered mt-4">
      <tr>
        <th>PNR No</th>
        <td>{{ propBookingObj.pnr_no }}</td>
      </tr>
      <tr>
        <th>Route Name</th>
        <td>{{ propBookingObj.route_name }}</td>
      </tr>
      <tr>
        <th>Pickup Name</th>
        <td>{{ propBookingObj.pickupId_title }}</td>
      </tr>
      <tr>
        <th>Drop Name</th>
        <td>{{ propBookingObj.dropoffId_title }}</td>
      </tr>
      <tr>
        <th>Seat No</th>
        <td>{{ propBookingObj.seat_nos }}</td>
      </tr>

      <tr>
        <th>Sub total</th>
        <td>
          <span v-html="propBookingObj.default_currency.symbol"></span
          >{{ propBookingObj.sub_total }}
        </td>
      </tr>
      <tr>
        <th>Fee</th>
        <td>
          <span v-html="propBookingObj.default_currency.symbol"></span
          >{{ propBookingObj.fee }}
        </td>
      </tr>
      <tr>
        <th>tax({{ propBookingObj.tax }}%)</th>
        <td>
          <span v-html="propBookingObj.default_currency.symbol"></span
          >{{ propBookingObj.tax_amount }}
        </td>
      </tr>
      <tr>
        <th>Final total fare</th>
        <td>
          <span v-html="propBookingObj.default_currency.symbol"></span
          >{{ propBookingObj.final_total_fare }}
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  name: "Booking-detail",
  data() {
    return {};
  },
  props: {
    propBookingObj: Object,
  },
};
</script>

<style lang="scss" scoped></style>
