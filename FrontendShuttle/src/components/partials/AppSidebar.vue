<template lang="html">
  <section class="app-sidebar">
    <nav class="sidebar sidebar-offcanvas" id="sidebar">
      <ul class="nav">
        <li class="nav-item nav-profile">
          <a href="#" class="nav-link">
            <div class="profile-image">
              <img
                class="img-xs rounded-circle"
                :src="getUser.picture"
                alt="profile image"
              />
              <div class="dot-indicator bg-success"></div>
            </div>
            <div class="text-wrapper">
              <div class="profile-name">{{ getUser.firstname }}</div>
              <div class="designation">{{ getUser.lastname }}</div>
            </div>
          </a>
        </li>
        <li class="nav-item nav-category">Main Menu</li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{ path: '/dashboard' }">
            <i class="menu-icon typcn typcn-home-outline typcn-3x"></i>
            <span class="menu-title">Dashboard</span></router-link
          >
        </li>
        <!-- <li class="nav-item">
          <router-link class="nav-link" to="/locations">
            <i class="menu-icon typcn typcn-document-add"></i>
            <span class="menu-title">Manage Locations</span></router-link
          >
        </li> -->
        <li class="nav-item">
          <a class="nav-link" v-b-toggle="'ui-basic'">
            <i class="menu-icon typcn typcn-coffee"></i>
            <span class="menu-title">Manage Vehicles</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="ui-basic">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/buses',
                  }"
                  >All Buses</router-link
                >
              </li>
              <!-- <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/staff/buses',
                  }"
                  >All Buses</router-link
                >
              </li> -->

              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/bus/create',
                  }"
                  >Create Bus</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/buslayouts',
                  }"
                  >All Bus Layout</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/buslayout/create',
                  }"
                  >Add Bus Layout
                </router-link>
              </li>

              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/bustypes',
                  }"
                  >All Bus Type</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/bustype/create',
                  }"
                  >Add Bus Type
                </router-link>
              </li>
            </ul>
          </b-collapse>
        </li>
        <li class="nav-item">
          <span class="nav-link" v-b-toggle="'manage-driver-dropdown'">
            <i class="menu-icon typcn typcn-group-outline"></i>
            <span class="menu-title"> Manage Drivers </span>
            <i class="menu-arrow"></i>
          </span>
          <b-collapse id="manage-driver-dropdown">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link class="nav-link" :to="{ path: '/drivers' }"
                  >All Drivers</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/driver/create',
                  }"
                  >Create Driver</router-link
                >
              </li>
            </ul>
          </b-collapse>
        </li>
        <li class="nav-item">
          <a class="nav-link" v-b-toggle="'manage-routes'">
            <i class="menu-icon typcn typcn-location-arrow-outline"></i>
            <span class="menu-title">Fixed routes</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="manage-routes">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/locations',
                  }"
                  >All stops</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/location/create',
                  }"
                  >Create stops</router-link
                >
              </li>

              <li class="nav-item">
                <router-link class="nav-link" :to="{ path: '/routes' }"
                  >All Routes</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/route/create',
                  }"
                  >Create Route</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/timetables',
                  }"
                  >All Timetables</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/timetable/create',
                  }"
                  >Create Timetable</router-link
                >
              </li>
            </ul>
          </b-collapse>
        </li>
        <li v-if="getName.role == 'admin'" class="nav-item nav-category">
          Administrations Settings
        </li>
        <li class="nav-item">
          <a class="nav-link" v-b-toggle="'manage-customers'">
            <i class="menu-icon typcn typcn-group-outline"></i>
            <span class="menu-title">Manage Customers</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="manage-customers">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link class="nav-link" :to="{ path: '/customers' }"
                  >All Customers</router-link
                >
              </li>
              <li class="nav-item">
                <router-link class="nav-link" :to="{ path: '/referrals' }"
                  >Referrals</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{ path: '/customers/wallet-recharge' }"
                  >Wallet Recharge</router-link
                >
              </li>
              <li class="nav-item">
                <router-link class="nav-link" :to="{ path: '/suggests' }"
                  >Suggestions</router-link
                >
              </li>
            </ul>
          </b-collapse>
        </li>
        <li class="nav-item">
          <a class="nav-link" v-b-toggle="'manage-agents'">
            <i class="menu-icon typcn typcn-group-outline"></i>
            <span class="menu-title">Manage Agents</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="manage-agents">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link class="nav-link" :to="{ path: '/agents' }"
                  >All Agents</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/agent/create',
                  }"
                  >Create Agent</router-link
                >
              </li>
            </ul>
          </b-collapse>
        </li>

        <li class="nav-item">
          <a class="nav-link" v-b-toggle="'manage-offers'">
            <i class="menu-icon typcn typcn-group-outline"></i>
            <span class="menu-title">Manage Offers</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="manage-offers">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link class="nav-link" :to="{ path: '/offers' }"
                  >All Offers</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/offer/create',
                  }"
                  >Create Offer</router-link
                >
              </li>
            </ul>
          </b-collapse>
        </li>

        <li class="nav-item">
          <a class="nav-link" v-b-toggle="'manage-passes'">
            <i class="menu-icon typcn typcn-group-outline"></i>
            <span class="menu-title">Manage Passes</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="manage-passes">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link class="nav-link" :to="{ path: '/passes' }"
                  >All Passes</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/pass/create',
                  }"
                  >Create pass</router-link
                >
              </li>
            </ul>
          </b-collapse>
        </li>

        <!-- <li class="nav-item">
          <router-link class="nav-link" to="/agents">
            <i class="menu-icon typcn typcn-group-outline"></i>
            <span class="menu-title">All Agents</span>
          </router-link>
        </li> -->
        <li class="nav-item">
          <router-link
            class="nav-link"
            :to="{
              path: '/help-and-support',
            }"
          >
            <i class="menu-icon typcn typcn-group-outline"></i>
            <span class="menu-title"> Help & Support</span>
          </router-link>
        </li>
        <!-- <li class="nav-item">
          <router-link
            class="nav-link"
            :to="{
              path: '/' + this.$store.state.auth.role + '/bookings',
            }"
          >
            <i class="menu-icon typcn typcn-clipboard"></i>
            <span class="menu-title">Manage Bookings</span>
          </router-link>
        </li> -->

        <li class="nav-item">
          <a class="nav-link" v-b-toggle="'trips'">
            <i class="menu-icon typcn typcn-user-outline"></i>
            <span class="menu-title">Manage Trips</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="trips">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/trips/assign/create',
                    // name: 'tripassigncreate',
                  }"
                  >Create</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/trips/assign/list',
                    //   name: 'tripassignlist',
                  }"
                  >List</router-link
                >
              </li>
            </ul>
          </b-collapse>
        </li>

        <li class="nav-item">
          <a class="nav-link" v-b-toggle="'bookings'">
            <i class="menu-icon typcn typcn-user-outline"></i>
            <span class="menu-title">Manage Bookings</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="bookings">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/bookings/scheduled',
                    // name: 'booking',
                    // params: { travel_status: 'scheduled' },
                  }"
                  >Scheduled</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/bookings/onboarded',
                    // name: 'booking',
                    // params: { travel_status: 'onboarded' },
                  }"
                  >Onboarded</router-link
                >
              </li>

              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/bookings/completed',
                    // name: 'booking',
                    // params: { travel_status: 'completed' },
                  }"
                  >Completed</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/bookings/cancelled',
                    // name: 'booking',
                    // params: { travel_status: 'cancelled' },
                  }"
                  >Cancelled</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/bookings/expired',
                    // name: 'booking',
                    // params: { travel_status: 'expired' },
                  }"
                  >Expired</router-link
                >
              </li>
            </ul>
          </b-collapse>
        </li>

        <li class="nav-item">
          <a class="nav-link" v-b-toggle="'payments'">
            <i class="menu-icon typcn typcn-user-outline"></i>
            <span class="menu-title">Manage Payments</span>
            <i class="menu-arrow"></i>
          </a>

          <b-collapse id="payments">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/payments/refunded',
                    // name: 'payment',
                    // params: { travel_status: 'refunded' },
                  }"
                  >Refunded</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/payments/processing',
                    // name: 'payment',
                    // params: { travel_status: 'refunded' },
                  }"
                  >Processing</router-link
                >
              </li>
            </ul>
          </b-collapse>
        </li>

        <!-- <li class="nav-item">
          <a class="nav-link" v-b-toggle="'icons'">
            <i class="menu-icon typcn typcn-user-outline"></i>
            <span class="menu-title">Icons</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="icons">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link class="nav-link" to="/mdiIcons/">MDI</router-link>
              </li>
            </ul>
          </b-collapse>
        </li>
        <li class="nav-item">
          <a class="nav-link" v-b-toggle="'auth'">
            <i class="menu-icon typcn typcn-document-add"></i>
            <span class="menu-title">User Pages</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="auth">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link class="nav-link" to="/pages/login/"
                  >Login</router-link
                >
              </li>
              <li class="nav-item">
                <router-link class="nav-link" to="/pages/register/"
                  >Register</router-link
                >
              </li>
            </ul>
          </b-collapse>
        </li> -->

        <li
          v-if="getUser.role === 'admin' || getUser.role === 'staff'"
          class="nav-item nav-category"
        >
          {{ getUser.role.toUpperCase() }} Menu
        </li>
        <li class="nav-item">
          <a class="nav-link" v-b-toggle="'manage-notifications'">
            <i class="menu-icon typcn typcn-group-outline"></i>
            <span class="menu-title">Manage Notifications</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="manage-notifications">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/notifications',
                  }"
                  >Lists</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/notifications/create',
                  }"
                  >Create</router-link
                >
              </li>
            </ul>
          </b-collapse>
        </li>
        <li v-if="getUser.role === 'admin'" class="nav-item">
          <a class="nav-link" v-b-toggle="'manage-users'">
            <i class="menu-icon typcn typcn-group-outline"></i>
            <span class="menu-title">Manage Users</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="manage-users">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link class="nav-link" :to="{ path: '/users' }"
                  >Manage Users</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/user/create',
                  }"
                  >Create User</router-link
                >
              </li>
            </ul>
          </b-collapse>
        </li>

        <li v-if="getUser.role === 'admin'" class="nav-item">
          <a class="nav-link" v-b-toggle="'auth'">
            <i class="menu-icon typcn typcn-document-add"></i>
            <span class="menu-title">Role and Permission</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="auth">
            <ul class="nav flex-column sub-menu">
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    path: '/roles',
                  }"
                  >Manage Role</router-link
                >
              </li>
              <!-- <li class="nav-item">
                <router-link class="nav-link" to="#">Assign Role</router-link>
              </li> -->
            </ul>
          </b-collapse>
        </li>
        <li v-if="getUser.role == 'admin'" class="nav-item">
          <a class="nav-link" v-b-toggle="'administration-tools'">
            <i class="menu-icon typcn typcn-document-add"></i>
            <span class="menu-title">Administration Tools</span>
            <i class="menu-arrow"></i>
          </a>
          <b-collapse id="administration-tools">
            <ul class="nav flex-column sub-menu">
              <!-- <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    name: 'country',
                  }"
                >
                  <i class="menu-icon typcn typcn-cog-outline"></i>
                  <span class="menu-title">Country</span>
                </router-link>
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    name: 'currency',
                  }"
                >
                  <i class="menu-icon typcn typcn-cog-outline"></i>
                  <span class="menu-title">Currency</span>
                </router-link>
              </li> -->
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  :to="{
                    name: 'applicationsetting',
                  }"
                >
                  <i class="menu-icon typcn typcn-cog-outline"></i>
                  <span class="menu-title">App Settings</span>
                </router-link>
              </li>
            </ul>
          </b-collapse>
        </li>
      </ul>
    </nav>
  </section>
</template>

<script lang="js">

import JQuery from 'jquery'
// import _ from 'lodash';
import  { mapState } from "pinia";
import { useAuth } from "../../store/useAuth.js";
import { fetchUsers } from "../../store/fetchUsers.js";

// eslint-disable-next-line
let $ = JQuery
export default {
  name: 'the-ferri-sidebar',
  computed:{
    ...mapState(fetchUsers,['getUser']),
    ...mapState(fetchUsers,['getName']),
    ...mapState(useAuth,['isAuth'])
  },
  mounted () {
    const body = document.querySelector('body')
    // add class 'hover-open' to sidebar navitem while hover in sidebar-icon-only menu
    document.querySelectorAll('.sidebar .nav-item').forEach(function (el) {
      el.addEventListener('mouseover', function () {
        if (body.classList.contains('sidebar-icon-only')) {
          el.classList.add('hover-open')
        }
      })
      el.addEventListener('mouseout', function () {
        if (body.classList.contains('sidebar-icon-only')) {
          el.classList.remove('hover-open')
        }
      })
    })
  }
}
</script>

<style scoped lang="scss">
.app-sidebar {
}
</style>
