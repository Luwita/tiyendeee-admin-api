<template>
  <div class="col-lg-12">
    <div class="level">
      <div class="level-left">
        <div class="level-item">
          <p class="title is-5">{{ breadcrumbs.title }}</p>
        </div>
      </div>
      <div class="level-right">
        <div class="level-item">
          <nav class="breadcrumb is-small is_bold">
            <ul>
              <li class="is-active">
                <a>{{ breadcrumbs.b1 }}</a>
              </li>
              <li class="is-active" v-if="!breadcrumbs.link">
                <a>{{ breadcrumbs.b2 }}</a>
              </li>
              <li v-else>
                <router-link :to="{ name: breadcrumbs.name }">{{
                  breadcrumbs.b2
                }}</router-link>
              </li>
              <li class="is-active">
                <a>{{ breadcrumbs.b3 }}</a>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "breadcrumb",
  props: ["breadcrumbs"],
};
</script>

<style lang="scss" scoped></style>
