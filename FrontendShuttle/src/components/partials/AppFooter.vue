<template lang="html">
  <section class="app-footer">
    <footer class="footer">
      <div class="container-fluid clearfix">
        <span
          class="text-muted text-center text-sm-left d-block d-sm-inline-block"
          >Copyright ©
          <a href="https://sourcemonster.in/" target="_blank"
            >{{ this.$appName }} </a
          >{{ this.$year }}</span
        >
      </div>
    </footer>
  </section>
</template>

<script lang="js">
export default {
  name: 'the-ferri-footer'
}
</script>

<style scoped lang="scss">
.app-footer {
}
</style>
