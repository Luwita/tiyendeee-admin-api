<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-10 offset-lg-1 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <createForm />
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import Breadcrumb from "../../../components/breadcrumb";
import createForm from "../../../components/customers/wallet/createform";

export default {
  name: "walletcreate",
  data() {
    return {
      breadcrumbs: {
        title: "Create Wallet Recharge",
        b1: "Manage  Wallet Recharge",
        b2: "Wallet Recharge",
        b3: "Index",
        link: true,
        name: "customerwalletrecharge",
      },
    };
  },
  components: {
    Breadcrumb,
    createForm,
  },
};
</script>

<style>
</style>