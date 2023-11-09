<template lang="html">
  <section class="register">
    <div class="container-scroller">
      <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="content-wrapper auth p-0 theme-two">
          <div class="row d-flex align-items-stretch">
            <div
              class="col-md-4 banner-section d-none d-md-flex align-items-stretch justify-content-center"
            >
              <div class="slide-content bg-2"></div>
            </div>
            <div class="col-12 col-md-8 h-100 bg-white">
              <div
                class="auto-form-wrapper d-flex align-items-center justify-content-center flex-column"
              >
                <div class="nav-get-started">
                  <p>Already have an account?</p>
                  <router-link class="btn get-started-btn" to="login"
                    >SIGN IN
                  </router-link>
                </div>
                <Register />
              </div>
            </div>
          </div>
        </div>
        <!-- content-wrapper ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
  </section>
</template>

<script lang="js">
import Register from '../../components/auth/Register';
export default {
  name: 'register',
  components:{
      Register,
  }
}
</script>
