<template>  <div>
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
  </div></template>

<script>
import Breadcrumb from "../../components/breadcrumb";
import createForm from "../../components/notify/createform";

export default {
  name: "createNotification",
  data() {
    return {
      breadcrumbs: {
        title: "Notifications",
        b1: "Create Notifications",
        b2: "Notifications",
        b3: "Index",
        link: false,
        name: "Notifications",
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