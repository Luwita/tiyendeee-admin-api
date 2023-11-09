<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-8 offset-lg-2 stretch-card">
          <div class="card">
            <div class="card-body">
              <h4 class="card-title">Create Driver</h4>
              <customForm :formtype="formtype" />
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import Breadcrumb from "../../../components/breadcrumb";
import customForm from "../../../components/drivers/customform";
//const isPhone = (value) => /^\+?[0-9]+$/.test(value);

export default {
  name: "drivercreate",
  data() {
    return {
      formtype: false,
      breadcrumbs: {
        title: "Create Driver",
        b1: "Manage Drivers",
        b2: "Drivers",
        b3: "Index",
        link: true,
        name: "drivers",
      },
    };
  },
  components: {
    Breadcrumb,
    customForm,
  },
};
</script>

<style scoped lang="scss"></style>
