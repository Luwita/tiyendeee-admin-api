<template>
  <div>
    <table class="table table-bordered mt-4">
      <tr v-if="propPaymentObj.paymentId">
        <th>Payment Id</th>
        <td>{{ propPaymentObj.paymentId }}</td>
      </tr>
      <tr>
        <th>Payment Title</th>
        <td>{{ propPaymentObj.title }}</td>
      </tr>
      <tr>
        <th>Payment Amount</th>
        <td>
          <span v-html="propPaymentObj.default_currency.symbol"></span
          >{{ propPaymentObj.amount }}
        </td>
      </tr>
      <tr>
        <th>Is Pass</th>
        <td>{{ propPaymentObj.is_pass ? "Yes" : "No" }}</td>
      </tr>
      <tr v-if="propPaymentObj.is_pass">
        <th>No of pass ride</th>
        <td>{{ propPaymentObj.no_of_pass_ride }}</td>
      </tr>
      <tr>
        <th>Method</th>
        <td>{{ propPaymentObj.method }}</td>
      </tr>
      <tr>
        <th>Payment status</th>
        <td>{{ propPaymentObj.payment_status }}</td>
      </tr>

      <tr>
        <th>Payment Created</th>
        <td>{{ propPaymentObj.payment_created }}</td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  name: "payment-detail",
  props: {
    propPaymentObj: {
      type: Object,
    },
  },
};
</script>

<style lang="scss" scoped></style>
