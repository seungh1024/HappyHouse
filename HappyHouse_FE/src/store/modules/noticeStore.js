import { instance, instanceWithAuth } from "@/api/index";

const noticeStore = {
  namespaced: true,
  state: {
    noticeList: [{}],
    notice: {},
    noticeTotal: 0,
    noticePage: 1,
    articleno: null,
  },
  getters: {},
  mutations: {
    NOTICE_LIST(state, payload) {
      state.noticeList = payload;
    },
    NOTICE_DATA(state, payload) {
      state.notice = payload;
    },
    NOTICE_TOTAL(state, payload) {
      state.noticeTotal = payload;
    },
    SET_CURRENT_PAGE(state, noticePage) {
      state.noticePage = noticePage;
    },
    SET_ARTICLE_NO(state, no) {
      state.articleno = no;
    },
  },
  actions: {
    noticeCreate(context, payload) {
      payload.userid = this.state.userInfo["idx"];
      instanceWithAuth.post("/board", payload).then(({ data }) => {
        if (data == "success") {
          alert("게시글 등록 완료");
          context.dispatch("getNoticeList", 1);
          context.dispatch("getTotalNotice");
        }
      });
    },
    noticeDelete(context, payload) {
      instanceWithAuth.delete(`/board/${payload}`).then(({ data }) => {
        if (data == "success") {
          alert("notice 삭제 성공");
        } else {
          alert("notice 삭제 실패");
        }
        context.dispatch("getNoticeList", this.state.noticeStore.noticePage);
        context.dispatch("getTotalNotice");
      });
    },
    getNoticeData(context, payload) {
      instanceWithAuth.get("/board/" + payload).then(({ data }) => {
        console.log("notice: ", data);
        context.commit("NOTICE_DATA", data);
      });
    },
    getNoticeList(context, payload) {
      console.log(payload);
      console.log("noticePage", this.state.noticeStore.noticePage);
      instanceWithAuth
        .get(`/board/list/${this.state.noticeStore.noticePage}`)
        .then(({ data }) => {
          console.log(data);
          context.commit("NOTICE_LIST", data);
        });
    },
    noticeModify(context, payload) {
      console.log("noticeModify: ", payload.articleno);
      instanceWithAuth
        .put(`/board/${payload.articleno}`, payload)
        .then(({ data }) => {
          if (data == "success") {
            alert("notice 수정 완료");
          } else {
            alert("notice 수정 실패");
          }
          context.dispatch("getNoticeList", this.state.noticeStore.noticePage);
          context.dispatch("getTotalNotice");
        });
    },

    getTotalNotice(context) {
      instance.get("/board/total").then(({ data }) => {
        console.log(data);
        context.commit("NOTICE_TOTAL", data);
      });
    },
    setCurrentPage(context, page) {
      context.commit("SET_CURRENT_PAGE", page);
    },
    setArticleNo(context, no) {
      context.commit("SET_ARTICLE_NO", no);
    },
  },
};

export default noticeStore;
