<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-10 offset-lg-1 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <createForm :formtype="formtype" />
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import Breadcrumb from "../../../components/breadcrumb";
import createForm from "../../../components/bus/createform";
//const isPhone = (value) => /^\+?[0-9]+$/.test(value);

export default {
  name: "buscreate",
  data() {
    return {
      formtype: false,
      breadcrumbs: {
        title: "Create bus",
        b1: "Manage bus",
        b2: "bus",
        b3: "Index",
        link: true,
        name: "bus",
      },
    };
  },
  components: {
    Breadcrumb,
    createForm,
  },
};
</script>

<style scoped lang="scss"></style>
