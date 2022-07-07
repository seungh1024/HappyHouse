import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import codeStore from "@/store/modules/codeStore.js";
import aptStore from "@/store/modules/aptStore.js";
import qnaStore from "@/store/modules/qnaStore.js";
import noticeStore from "@/store/modules/noticeStore.js";
import { instance, instanceWithAuth } from "@/api/index";

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    token: null,
    tokenIndex: -1,
    userInfo: {},
    user: false,
    userToken: {},
    test: false,
    checkCode: -1,
    admin: false,
    pageClear: false,
  },
  getters: {
    aptStore() {
      return aptStore.getters;
    },
  },
  mutations: {
    TOKEN(state, payload) {
      state.token = payload;
    },
    TOKEN_INDEX(state, payload) {
      state.tokenIndex = payload;
    },
    USER_EXIST(state, payload) {
      state.user = payload;
    },
    USER_INFO(state, payload) {
      state.userInfo = payload;
    },
    USER_TOKEN(state, payload) {
      state.userToken = payload;
    },
    CHECK_CODE(state, payload) {
      state.checkCode = payload;
    },
    // NOTICE_LIST(state, payload) {
    //   state.noticeList = payload;
    // },
    // NOTICE_DATA(state, payload) {
    //   state.notice = payload;
    // },
    // NOTICE_TOTAL(state, payload) {
    //   state.noticeTotal = payload;
    // },
    ADMIN(state, payload) {
      state.admin = payload;
    },
    PAGE_CLEAR(state, status) {
      state.pageClear = status;
    },
  },
  actions: {
    login(context, payload) {
      console.log(context);
      console.log(payload);
      instance.post("user/login", payload).then(({ data }) => {
        console.log(data);
        if (data.code == "200") {
          alert("로그인 성공");
          // auth.headers.token = data.result.accessToken;
          context.commit("TOKEN", data.result.accessToken);
          context.commit("TOKEN_INDEX", data.result.refreshIdx);
          context.commit("USER_EXIST", true);
          context.commit("USER_TOKEN", data);
          console.log(data.result.refreshIdx);
          context.dispatch("getInfo", data.result.refreshIdx);
          instanceWithAuth.defaults.headers.common[
            "token"
          ] = `${data.result.accessToken}`;
          console.log("qnaStore: ", qnaStore);
          console.log("qnaStore.actions: ", qnaStore.actions);
          console.log("context: ", context);
          context.commit("PAGE_CLEAR", true);
        }
      });
    },
    getInfo(context, payload) {
      console.log(context);
      console.log(payload);
      instance.post("/user/info", payload).then(({ data }) => {
        console.log(data);
        if (data) {
          context.commit("USER_INFO", data);
          if (data.role == "ADMIN") {
            context.commit("ADMIN", true);
          } else {
            context.commit("ADMIN", false);
          }
        }
      });
    },
    compareToken(context, payload) {
      console.log(context);
      console.log(payload);
      instance
        .post("/user/refreshToken", payload)
        .then(({ data }) => {
          console.log(data);
          if (data) {
            context.commit("USER_TOKEN", data);
            context.commit("TOKEN_INDEX", data.result.refreshIdx);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    register(context, payload) {
      console.log(context);
      instance.post("/user/register", payload).then(({ data }) => {
        if (data == "success") {
          alert("회원가입 성공");
        } else {
          alert("회원가입 실패");
        }
      });
    },
    modify(context, payload) {
      console.log(context);
      instanceWithAuth.put("/user/modify", payload).then(({ data }) => {
        if (data == "fail") {
          alert("정보 수정 실패");
        } else {
          alert("정보 수정 성공");
          context.commit("USER_INFO", data);
        }
        console.log(data);
      });
    },
    async delete(context, payload) {
      console.log("stored token: ", this.state.token);
      console.log(context);
      console.log(payload);
      await instanceWithAuth
        .delete("/user/delete/" + payload)
        .then(({ data }) => {
          if (data == "success") {
            alert("회원탈퇴 성공");
            context.commit("USER_EXIST", false);
            context.commit("USER_INFO", null);
            localStorage.removeItem("vuex");
          } else {
            alert("회원탈퇴 실패");
          }
        });
    },
    findPw(context, payload) {
      console.log(context);
      console.log(payload);
      instance.post("/user/findpw", payload).then(({ data }) => {
        console.log(data);
        if (data) {
          alert("임시 비밀번호입니다.\n\n" + data.password);
        } else {
          alert("아이디와 이름을 확인해주세요.");
        }
      });
    },
    checkEmail(context, payload) {
      console.log(context);
      instance
        .post("/user/mail", payload)
        .then(({ data }) => {
          console.log("post mail", data);
        })
        .catch((error) => {
          console.log("mail error", error);
        });
    },
    async verifyCode(context, payload) {
      await instance.post("/user/verifyCode", payload).then(({ data }) => {
        // alert(data);
        context.commit("CHECK_CODE", data);
        console.log("post code", data);
      });
    },
    pageClear(context, status) {
      context.commit("PAGE_CLEAR", status);
    },
  },
  modules: {
    codeStore,
    aptStore,
    qnaStore,
    noticeStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: localStorage,
    }),
  ],
});

export default store;
