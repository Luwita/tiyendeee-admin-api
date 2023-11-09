//import Vue from "vue";
import VueRouter from "vue-router";
import NProgress from "nprogress";
NProgress.configure({ easing: "ease", speed: 3000, showSpinner: true });
import { useAuth } from "../store/useAuth";
import { checkPermission } from "../helpers/PermissionCheck";
// Dashboard Components
//import store from "../store";

//  Admin Components
import mainView from "../views/mainview";

import login from "../views/auth/login";
//import register from "../views/auth/register";
import forget from "../views/auth/forget";
import change from "../views/auth/change";

import term from "../views/term";
import policy from "../views/policy";

const routes = [
  {
    path: "/",
    redirect: "/dashboard",
    component: mainView,
    children: [
      {
        path: "dashboard",
        name: "dashboard",
        component: () => import("../views/admin/dashboard"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "manage.dashboard"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
          title: "Dashboard",
        },
      },
      {
        path: "my-profile",
        name: "profile",
        component: () => import("../views/auth/profile.vue"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "auth.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "buses",
        name: "bus",
        component: () => import("../views/admin/buses"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "bus.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "bus/create",
        name: "buscreate",
        component: () => import("../views/admin/buses/create"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "bus.create"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "bus/:id",
        name: "busedit",
        component: () => import("../views/admin/buses/edit"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "bus.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },

      {
        path: "bustypes",
        name: "bustype",
        component: () => import("../views/admin/bustypes"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "bus.type.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "bustype/create",
        name: "bustypecreate",
        component: () => import("../views/admin/bustypes/create"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "bus.type.create"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "bustype/:id",
        name: "bustypeedit",
        component: () => import("../views/admin/bustypes/edit"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "bus.type.edit"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },

      {
        path: "locations",
        name: "location",
        component: () => import("../views/admin/locations"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "stop.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "location/create",
        name: "locationcreate",
        component: () => import("../views/admin/locations/create"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "stop.create"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "location/:id",
        name: "locationedit",
        component: () => import("../views/admin/locations/create"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "stop.edit"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "routes",
        name: "route",
        component: () => import("../views/admin/routes"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "route.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "route/create",
        name: "routecreate",
        component: () => import("../views/admin/routes/create"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "route.create"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "route/:id",
        name: "routeedit",
        component: () => import("../views/admin/routes/edit"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "route.edit"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "drivers",
        name: "driver",
        component: () => import("../views/admin/drivers"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "driver.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "driver/create",
        name: "drivercreate",
        component: () => import("../views/admin/drivers/create"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "driver.create"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "driver/:id",
        name: "driveredit",
        component: () => import("../views/admin/drivers/edit"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "driver.edit"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "agents",
        name: "agent",
        component: () => import("../views/admin/agents"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "agent.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "agent/create",
        name: "agentcreate",
        component: () => import("../views/admin/agents/create"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "agent.create"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "users",
        name: "user",
        component: () => import("../views/admin/users"),
        meta: {
          requiresAuth: true,
          authorize: ["admin"],
          redirect: { name: "login" },
          permission: ["master.admin", "user.view"],
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "user/create",
        name: "usercreate",
        component: () => import("../views/admin/users/create"),
        meta: {
          requiresAuth: true,
          authorize: ["admin"],
          redirect: { name: "login" },
          permission: ["master.admin", "user.create"],
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "user/:id",
        name: "useredit",
        component: () => import("../views/admin/users/edit"),
        meta: {
          requiresAuth: true,
          authorize: ["admin"],
          redirect: { name: "login" },
          permission: ["master.admin", "user.edit"],
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "buslayouts",
        name: "buslayout",
        component: () => import("../views/admin/buslayout"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "bus.layout.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "buslayout/create",
        name: "buslayoutcreate",
        component: () => import("../views/admin/buslayout/create"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "bus.layout.create"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "buslayout/:id",
        name: "buslayoutedit",
        component: () => import("../views/admin/buslayout/edit"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "bus.layout.edit"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "help-and-support",
        name: "helpandsupport",
        component: () => import("../views/admin/helpAndSupport"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "help.support.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "administration-tools/application-settings",
        name: "applicationsetting",
        component: () => import("../views/admin/applicationSetting"),
        meta: {
          requiresAuth: true,
          authorize: ["admin"],
          permission: ["master.admin", "manage.application.settings"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "administration-tools/currency",
        name: "currency",
        component: () => import("../views/admin/currencies"),
        meta: {
          requiresAuth: true,
          authorize: ["admin"],
          permission: ["master.admin", "currency.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "administration-tools/country",
        name: "country",
        component: () => import("../views/admin/countries"),
        meta: {
          requiresAuth: true,
          authorize: ["admin"],
          permission: ["master.admin"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "trips/assign/list",
        name: "tripassignlist",
        component: () => import("../views/trips/assign/list"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "booking.assigns.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "trips/assign/create",
        name: "tripassigncreate",
        component: () => import("../views/trips/assign/create"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "booking.assigns.create"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "trips/assign/:id",
        name: "tripassignedit",
        component: () => import("../views/trips/assign/edit"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "booking.assigns.edit"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },

      {
        path: "timetable/create",
        name: "timetablecreate",
        component: () => import("../views/admin/timetables/create"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "timetable.create"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "timetables",
        name: "timeTables",
        component: () => import("../views/admin/timetables"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "timetable.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "timetable/:id",
        name: "timetableedit",
        component: () => import("../views/admin/timetables/edit"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "timetable.edit"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "offers",
        name: "offer",
        component: () => import("../views/admin/offers"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "offer.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "offer/create",
        name: "offercreate",
        component: () => import("../views/admin/offers/create"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "offer.create"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "offer/:id",
        name: "offeredit",
        component: () => import("../views/admin/offers/edit"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "offer.edit"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "passes",
        name: "pass",
        component: () => import("../views/admin/passes"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "pass.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "pass/create",
        name: "passcreate",
        component: () => import("../views/admin/passes/create"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "pass.create"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "pass/:id",
        name: "passedit",
        component: () => import("../views/admin/passes/edit"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "pass.edit"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "roles",
        name: "role",
        component: () => import("../views/admin/roles"),
        meta: {
          requiresAuth: true,
          authorize: ["admin"],
          permission: ["master.admin", "roles"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "role/create",
        name: "rolecreate",
        component: () => import("../views/admin/roles/create"),
        meta: {
          requiresAuth: true,
          authorize: ["admin"],
          permission: ["master.admin", "roles"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "role/:id",
        name: "roleedit",
        component: () => import("../views/admin/roles/edit"),
        meta: {
          requiresAuth: true,
          authorize: ["admin"],
          permission: ["master.admin", "roles"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "role-permission/:id",
        name: "rolepermission",
        component: () => import("../views/admin/roles/permission"),
        meta: {
          requiresAuth: true,
          authorize: ["admin"],
          permission: ["master.admin", "permissions"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "assign-role",
        name: "assignrole",
        component: () => import("../views/admin/roles/assign/create"),
        meta: {
          requiresAuth: true,
          authorize: ["admin"],
          permission: ["master.admin", "permissions"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "bookings/:travel_status",
        name: "booking",
        component: () => import("../views/admin/bookings"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "booking.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "booking-details/:id",
        name: "bookingdetail",
        component: () => import("../views/admin/bookings/detail"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "booking.edit"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "payments/:travel_status",
        name: "payment",
        component: () => import("../views/admin/payments"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "payment.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "referrals",
        name: "referral",
        component: () => import("../views/referrals"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "referrals"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "referrals/:userId",
        name: "referralview",
        component: () => import("../views/referrals/view"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "referrals.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "customers",
        name: "customer",
        component: () => import("../views/customers"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          redirect: { name: "login" },
          permission: ["master.admin", "customer.view"],
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "customer/:id",
        name: "customeredit",
        component: () => import("../views/customers/edit"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "customer.edit"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "customer-wallet-history/:userId/:walletId",
        name: "customerwallethistory",
        component: () => import("../views/customers/wallet/history"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "customer.wallet.history.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "customers/wallet-recharge",
        name: "customerwalletrecharge",
        component: () => import("../views/customers/wallet/create"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "customer.wallet.create"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "suggests",
        name: "suggest",
        component: () => import("../views/admin/suggests"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "suggests"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "notifications/create",
        name: "notification-create",
        component: () => import("../views/notifications/create"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "notification.create"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
      {
        path: "notifications",
        name: "notification-list",
        component: () => import("../views/notifications"),
        meta: {
          requiresAuth: true,
          authorize: ["admin", "staff"],
          permission: ["master.admin", "notification.view"],
          redirect: { name: "login" },
          forbiddenRedirect: "/403",
        },
      },
    ],
  },

  {
    path: "/403",
    name: "403",
    component: () => import("../views/errors/403"),
    meta: {
      requiresAuth: false,
    },
  },
  {
    path: "/404",
    name: "404",
    component: () => import("../views/errors/404"),
    meta: {
      requiresAuth: false,
    },
  },
  {
    path: "*",
    // redirect: "/auth/error_404",
    component: {
      render(c) {
        return c("router-view");
      },
    },
    children: [
      {
        path: "/auth/login",
        name: "login",
        component: login,
        meta: {
          requiresAuth: false,
        },
      },
      // {
      //   path: "/auth/register",
      //   name: "register",
      //   component: register,
      //   meta: {
      //     requiresAuth: false,
      //   },
      // },
      {
        path: "/auth/forget-password",
        name: "forget",
        component: forget,
        meta: {
          requiresAuth: false,
        },
      },
      {
        path: "/auth/new-password/:resetToken",
        name: "change",
        component: change,
        meta: {
          requiresAuth: false,
        },
      },
      {
        path: "/terms",
        name: "term",
        component: term,
        meta: {
          requiresAuth: false,
        },
      },
      {
        path: "/cookies-&-policy",
        name: "policy",
        component: policy,
        meta: {
          requiresAuth: false,
        },
      },
    ],
  },
];

const router = new VueRouter({
  linkActiveClass: "active",
  mode: "hash",
  //  base: process.env.BASE_URL,
  routes,
});

router.beforeEach(async (to, from, next) => {
  const auth = useAuth();
  const isAuthenticated = auth.isAuth;
  const userAccess = checkPermission(to.meta.permission);
  console.log(" to.fullPath ", isAuthenticated, "to.meta.requiresAuth", to);
  if (!isAuthenticated && to.meta.requiresAuth) {
    console.log(" to.fullPath 111", isAuthenticated);
    return next("/auth/login"); //next("/auth/login");
  }
  if (to.meta.permission && isAuthenticated && !userAccess) {
    //  console.log(" to.fullPath ", to.meta.permission);
    return next("/403");
  }
  if (isAuthenticated && to.path === "/auth/login") {
    return { name: from.fullPath };
  }
  //  if (isAuthenticated && to.path === "/register") return { name: from.fullPath}
  next();
});

router.beforeResolve((to, from, next) => {
  if (to.name) {
    NProgress.start();
  }
  next();
});

router.afterEach(() => {
  NProgress.done();
});

// router.beforeEach((to, from, next) => {
//   if (to.matched.some((record) => record.meta.requiresAuth)) {
//     console.log("router isAuthenticated: ", store.getters.isAuthenticated);
//     if (store.getters.isAuthenticated) {
//       next();
//       return;
//     }
//     next("/auth/login");
//   } else {
//     next();
//   }
// });

// router.beforeEach(async (to, from, next) => {
//   // redirect to login page if not logged in and trying to access a restricted page
//   const publicPages = [
//     "/auth/login",
//     "/auth/forget-password",
//     "/auth/register",
//     "/terms",
//     "/cookies-&-policy",
//   ];
//   const authRequired = !publicPages.includes(to.path);
//   const loggedIn = localStorage.getItem("user");
//   //let loggedIn = await store.dispatch("auth/isLoggedIn");

//   const { authorize } = to.meta;
//   //console.log("router.meta", to.meta);
//   // /forbiddenRedirect
//   console.log("authRequired", authorize);
//   const role = localStorage.getItem("role");
//   if (to.name == "change" && !loggedIn) {
//     next();
//   } else if (authRequired && !loggedIn) {
//     return next("/auth/login");
//   } else if (authorize && !authorize.includes(role)) {
//     console.log("sadasdas ");
//     return next("/403");
//   }
//   // console.log("34234 ");

//   //  else if (
//   //   authorize &&
//   //   authorize.length &&
//   //   !authorize.includes(this.$store.auth.role)
//   // ) {
//   //   console.log("to.path", to.path, "authorize", authorize, forbiddenRedirect);
//   //   router.push({ path: forbiddenRedirect });
//   // }

//   next();
// });

export default router;
