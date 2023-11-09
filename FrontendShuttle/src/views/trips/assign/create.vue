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
import createForm from "../../../components/trips/assign/createform";

export default {
  name: "tripassigncreate",
  data() {
    return {
      breadcrumbs: {
        title: "Create Assign",
        b1: "Manage Assign",
        b2: "Assign",
        b3: "Index",
        link: true,
        name: "Assign",
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