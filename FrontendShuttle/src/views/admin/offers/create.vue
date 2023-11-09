<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-8 offset-lg-2 grid-margin stretch-card">
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
import createForm from "../../../components/offer/createform.vue";
//const isPhone = (value) => /^\+?[0-9]+$/.test(value);

export default {
  name: "offercreate",
  data() {
    return {
      formtype: false,
      breadcrumbs: {
        title: "Create offer",
        b1: "Manage offer",
        b2: "offer",
        b3: "Index",
        link: true,
        name: "offer",
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
