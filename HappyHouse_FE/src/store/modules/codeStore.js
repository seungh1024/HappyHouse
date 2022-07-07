import { instanceWithAuth } from "@/api/index";

const codeStore = {
  namespaced: true,
  state: {
    sidos: [],
    guguns: [],
    dongs: [],
  },
  getters: {},
  mutations: {
    GET_SIDO(state, sidos) {
      state.sidos = sidos;
    },
    GET_GUGUN(state, guguns) {
      state.guguns = guguns;
    },
    GET_DONG(state, dongs) {
      state.dongs = dongs;
    },
    CODE_CLEAR(state) {
      state.guguns = [];
      state.dongs = [];
    },
    DONG_CLEAR(state) {
      state.dongs = [];
    },
  },
  actions: {
    getSido(context) {
      instanceWithAuth.get("/code/sido").then(({ data }) => {
        context.commit("CODE_CLEAR");
        context.commit("GET_SIDO", data);
      });
    },
    getGugun(context, sidocode) {
      console.log(sidocode);
      context.commit("DONG_CLEAR");
      instanceWithAuth.get(`/code/gugun/${sidocode}`).then(({ data }) => {
        context.commit("GET_GUGUN", data);
      });
    },
    getDong(context, gugunCode) {
      gugunCode = gugunCode.substr(0, 5);
      console.log("split: ", gugunCode);
      instanceWithAuth.get(`/code/dong/${gugunCode}`).then(({ data }) => {
        context.commit("GET_DONG", data);
      });
    },
  },
};

export default codeStore;
