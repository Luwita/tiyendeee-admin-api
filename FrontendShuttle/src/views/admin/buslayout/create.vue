<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-12 grid-margin stretch-card">
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
import createForm from "../../../components/buslayout/createform";
//const isPhone = (value) => /^\+?[0-9]+$/.test(value);

export default {
  name: "buslayoutcreate",
  data() {
    return {
      formtype: false,
      breadcrumbs: {
        title: "Create Bus layout",
        b1: "Manage Bus layouts",
        b2: "Bus layouts",
        b3: "Index",
        link: true,
        name: "buslayouts",
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
