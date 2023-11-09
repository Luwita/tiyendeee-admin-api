<template>
  <section class="tables">
    <div class="row">
      <Breadcrumb :breadcrumbs="breadcrumbs" />
      <div class="col-lg-10 offset-lg-1 grid-margin stretch-card">
        <div class="card">
          <div class="card-body">
            <editForm />
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Breadcrumb from "../../../components/breadcrumb";
import editForm from "../../../components/trips/assign/editform";

export default {
  name: "tripassignedit",
  data() {
    return {
      breadcrumbs: {
        title: "Edit Assign",
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
    editForm,
  },
};
</script>

<style>
</style>