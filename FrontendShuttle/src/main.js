import Vue from "vue";
import App from "./App";
import { createPinia, PiniaVuePlugin } from "pinia";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";
import VueRouter from "vue-router";
import router from "./router";
import BootstrapVue from "bootstrap-vue";
import Vuelidate from "vuelidate";
import JQuery from "jquery";
import VueSweetalert2 from "vue-sweetalert2";
import VueToast from "vue-toast-notification";
import VueGoodTablePlugin from "vue-good-table";
import VueMeta from "vue-meta";
import * as VueGoogleMaps from "vue2-google-maps";
import { ColorPicker, ColorPanel } from "one-colorpicker";
import VueLazyload from "vue-lazyload";

//import setupInterceptors from "./services/setupInterceptors";

Vue.use(VueGoogleMaps, {
  load: {
    key: process.env.GOOGLE_MAPS_API_KEY,
    libraries: "places,directions", // This is required if you use the Autocomplete plugin
    // OR: libraries: 'places,drawing'
    // OR: libraries: 'places,drawing,visualization'
    // (as you require)

    //// If you want to set the version, you can do so:
    // v: '3.26',
    region: "IN",
    language: "en",
  },
});

// eslint-disable-next-line
let $ = JQuery

const options = {
  confirmButtonColor: "#41b882",
  cancelButtonColor: "#ff7674",
};

// import the styles

import "sweetalert2/dist/sweetalert2.min.css";
import "vue-good-table/dist/vue-good-table.css";
import "vue-toast-notification/dist/theme-sugar.css";
import "nprogress/nprogress.css";

Vue.use(BootstrapVue);
Vue.use(VueSweetalert2, options);
Vue.use(Vuelidate);
Vue.use(VueToast); //.provide("$toast", Vue.config.globalProperties.$toast);
Vue.use(VueMeta);
Vue.use(VueLazyload);

Vue.use(VueGoodTablePlugin);
Vue.use(ColorPanel);
Vue.use(ColorPicker);

//add router to pinia
Vue.use(PiniaVuePlugin);
const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);
pinia.use(({ store }) => {
  return (store.$router = router);
});
//setActivePinia(pinia);

Vue.use(VueRouter);

Vue.prototype.$appName = "Shuttle Ridein";
Vue.prototype.$year = new Date().getFullYear();

Vue.config.productionTip = true;

//setupInterceptors(store);

// Handle all Vue errors
//Vue.config.errorHandler = (error) => ErrorService.onError(error);

// Vue.config.errorHandler = function (err, vm, info) {
//   console.log(`Error: ${err.toString()}\nInfo: ${info}`);
// };

// Vue.config.warnHandler = function (msg, vm, trace) {
//   console.log(`Warn: ${msg}\nTrace: ${trace}`);
// };

new Vue({
  pinia,
  router,
  render: (h) => h(App),
}).$mount("#app");
