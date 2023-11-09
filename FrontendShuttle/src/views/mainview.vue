<template lang="html">
  <section class="main-view">
    <div class="container-scroller">
      <app-header />
      <div class="container-fluid page-body-wrapper">
        <app-sidebar />
        <div class="main-panel">
          <div class="content-wrapper">
            <router-view></router-view>
          </div>
          <!-- content wrapper ends -->
          <app-footer />
        </div>
        <!-- main panel ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
  </section>
</template>

<script lang="js">
import AppHeader from '../components/partials/AppHeader';
import AppSidebar from '../components/partials/AppSidebar';
import AppFooter from '../components/partials/AppFooter';
import  { mapState  } from "pinia";
import { useAuth } from "../store/useAuth.js";

export default {
  name: 'main',
  computed:{
    ...mapState(useAuth,['isAuth'])
  },
  components: {
    AppHeader,
    AppSidebar,
    AppFooter,
  }
}
</script>

<style scoped lang="scss">
.main-view {
}
</style>
