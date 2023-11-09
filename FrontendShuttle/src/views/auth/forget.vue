<template lang="html">
  <section class="login">
    <div class="container-scroller">
      <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="content-wrapper d-flex align-items-center auth">
          <div class="row w-100 flex-grow">
            <div class="col-xl-4 col-lg-6 mx-auto">
              <div class="auth-form-light text-left p-5">
                <div class="brand-logo">
                  <img
                    class="img"
                    src="https://ridein.in/images/logos/page_logo.png?v=1840225420"
                  />
                </div>
                <h5 class="font-weight-light">Forget Password.</h5>
                <Forget />
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
import { useAuth  } from "../../store/useAuth";
import Forget from '../../components/auth/Forget';

export default
{
      beforeRouteEnter (to, from, next) {
        const auth = useAuth();
      const isAuthenticated = auth.isAuth;
       if (isAuthenticated) {
          return next({path :'/'})
       }
       next()
  },
  name: 'forget',
  components:{
      Forget
  }
}
</script>

<style scoped lang="scss">
.login {
}
</style>
