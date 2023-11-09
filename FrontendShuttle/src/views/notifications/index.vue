<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-12">
          <listForm />
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import Breadcrumb from "../../components/breadcrumb";
import listForm from "../../components/notify/listform";
export default {
  name: "notifications",
  data() {
    return {
      breadcrumbs: {
        title: "Notifications",
        b1: "Manage Notifications",
        b2: "Notifications",
        b3: "Index",
        link: false,
        name: "Notifications",
      },
    };
  },
  components: {
    Breadcrumb,
    listForm,
  },
};
</script>

<style>
</style>