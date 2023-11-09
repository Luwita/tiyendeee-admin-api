<template>
  <div>
    <table class="table table-bordered mt-4">
      <tr>
        <th>Customer Name</th>
        <td>{{ propCustomerObj.customer_name }}</td>
      </tr>
      <tr>
        <th>Customer Email</th>
        <td>{{ propCustomerObj.customer_email }}</td>
      </tr>
      <tr>
        <th>Customer Phone</th>
        <td>{{ propCustomerObj.customer_phone }}</td>
      </tr>
      <tr>
        <th>Customer Gender</th>
        <td>{{ propCustomerObj.customer_gender }}</td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  name: "customer-detail",
  props: {
    propCustomerObj: {
      type: Object,
    },
  },
};
</script>

<style lang="scss" scoped></style>
