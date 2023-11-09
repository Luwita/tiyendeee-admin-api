<template lang="html">
  <b-navbar
    id="template-header"
    class="navbar default-layout col-lg-12 col-12 p-0 fixed-top d-flex flex-row"
    toggleable="md"
  >
    <div
      class="text-center navbar-brand-wrapper d-flex align-items-top justify-content-center"
    >
      <router-link class="navbar-brand brand-logo" to="/">
        <!-- <img src="../../assets/images/logo_dark.svg" alt="logo" /> -->
        {{ this.$appName }}
      </router-link>
      <router-link class="navbar-brand brand-logo-mini" to="/">
        <!-- <img src="../../assets/images/logo_dark_mini.svg" alt="logo" /> -->
        SR
      </router-link>
    </div>
    <div class="navbar-menu-wrapper d-flex align-items-center ml-auto ml-lg-0">
      <button
        class="navbar-toggler navbar-toggler align-self-center"
        type="button"
        @click="collapedSidebar()"
      >
        <span class="mdi mdi-menu"></span>
      </button>
      <!-- <b-navbar-nav
        class="navbar-nav navbar-nav-left header-links d-none d-md-flex"
      >
        <b-nav-item class="font-weight-semibold d-none d-lg-block" href="#"
          >Help : +050 2992 709</b-nav-item
        >
      </b-navbar-nav> -->
      <b-navbar-nav class="navbar-nav-right ml-auto">
        <form class="mr-auto search-form d-none d-md-block" action="#">
          <div class="form-group">
            <input
              type="search"
              class="form-control"
              placeholder="Search Here"
            />
          </div>
        </form>

        <b-nav-item-dropdown right class="preview-list user-dropdown">
          <template slot="button-content">
            <div class="d-flex align-items-center">
              <img
                class="img-xs rounded-circle"
                :src="getUser.picture"
                alt="Profile image"
              />
            </div>
          </template>
          <b-dropdown-item>
            <div class="dropdown-header text-center w-100">
              <img
                class="img-md rounded-circle"
                :src="getUser.picture"
                alt="Profile image"
              />
              <p class="mb-1 mt-3 font-weight-semibold">
                {{ getUser.firstname }} {{ getUser.lastname }}
              </p>
              <p class="font-weight-light text-muted mb-0">
                {{ getUser.email }}
              </p>
            </div>
          </b-dropdown-item>
          <b-dropdown-item>
            <router-link
              :to="{
                path: '/my-profile',
              }"
            >
              My Profile <i class="dropdown-item-icon ti-dashboard"></i>
            </router-link>
          </b-dropdown-item>
          <!-- <b-dropdown-item
            >Messages<i class="dropdown-item-icon ti-comment-alt"></i
          ></b-dropdown-item>
          <b-dropdown-item
            >Activity<i class="dropdown-item-icon ti-location-arrow"></i
          ></b-dropdown-item>
          <b-dropdown-item
            >FAQ<i class="dropdown-item-icon ti-help-alt"></i
          ></b-dropdown-item> -->
          <span v-if="isAuth">
            <b-dropdown-item @click="logout"
              >Sign Out<i class="dropdown-item-icon ti-power-off"></i
            ></b-dropdown-item>
          </span>
        </b-nav-item-dropdown>
      </b-navbar-nav>
      <button
        class="navbar-toggler navbar-toggler-right align-self-center"
        type="button"
        @click="collapedMobileSidebar()"
      >
        <span class="mdi mdi-menu"></span>
      </button>
    </div>
  </b-navbar>
</template>

<script lang="js">
// eslint-disable-next-line
import JQuery from 'jquery'
import  { mapState,mapActions } from "pinia";
import { useAuth } from "../../store/useAuth.js";
import { fetchUsers } from "../../store/fetchUsers.js";
 //import store from "../../store";
let $ = JQuery


export default {
  name: 'the-ferri-header',
  computed:{
    ...mapState(fetchUsers,['getUser']),
    ...mapState(useAuth,['isAuth'])
  },
  methods: {
    ...mapActions(useAuth,['signOut']),
    async logout(){
   //   await store.dispatch('auth/logout')
      this.signOut();
    },
    collapedSidebar: function () {
      $('body').toggleClass('sidebar-icon-only')
    },
    collapedMobileSidebar: function () {
      $('#sidebar').toggleClass('active')
    }
  }
}
</script>

<style scoped lang="scss">
.app-header {
}
</style>
