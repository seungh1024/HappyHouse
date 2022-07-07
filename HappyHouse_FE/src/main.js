import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

Vue.config.productionTip = false;

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

import { mapActions } from "vuex";

new Vue({
  router,
  store,
  created() {
    this.aptClear();
    // localStorage.removeItem("vuex");
    // this.$store.state.token = null;
  },
  beforeDestroy() {
    localStorage.removeItem("vuex");
  },
  methods: {
    ...mapActions("aptStore", ["aptClear"]),
  },

  render: (h) => h(App),
}).$mount("#app");
