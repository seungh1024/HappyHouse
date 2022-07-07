import { instanceWithAuth } from "@/api/index";

const qnaStore = {
  namespaced: true,
  state: {
    qnaList: [],
    qna: {},
    comments: [],
    total: 0,
    qnaPage: 1,
    articleno: null,
  },
  getters: {},
  mutations: {
    QNA_LIST(state, payload) {
      state.qnaList = payload;
    },
    QNA_DATA(state, payload) {
      state.qna = payload;
    },
    GET_COMMENTS(state, payload) {
      state.comments = payload;
    },
    QNA_TOTAL(state, payload) {
      state.total = payload;
    },
    SET_CURRENT_PAGE(state, page) {
      state.qnaPage = page;
    },
    SET_ARTICLE_NO(state, no) {
      state.articleno = no;
    },
  },
  actions: {
    qnaCreate(context, payload) {
      payload.userid = this.state.userInfo["idx"];
      console.log("qnaCreate: ", payload);
      instanceWithAuth.post("/qna/create", payload).then(({ data }) => {
        if (data == "success") {
          alert("게시글 등록 완료");
          context.dispatch("getQnaList", 1);
          context.dispatch("getTotalQna");
        }
      });
    },
    qnaDelete(context, payload) {
      instanceWithAuth.delete(`/qna/${payload}`).then(({ data }) => {
        if (data == "success") {
          alert("qna 삭제 성공");
        } else {
          alert("qna 삭제 실패");
        }
        context.dispatch("getQnaList", this.state.qnaStore.qnaPage);
        context.dispatch("getTotalQna");
      });
    },
    getQnaData(context, payload) {
      instanceWithAuth.get(`/qna/${payload}`).then(({ data }) => {
        console.log("qna: ", data);
        context.commit("QNA_DATA", data);
      });
    },
    getQnaList(context, payload) {
      console.log(payload);
      instanceWithAuth.get(`/qna/list/${payload}`).then(({ data }) => {
        console.log(data);
        context.commit("QNA_LIST", data);
      });
    },
    qnaModify(context, payload) {
      console.log("qnaModify: ", payload.articleno);
      instanceWithAuth
        .put(`/qna/${payload.articleno}`, payload)
        .then(({ data }) => {
          if (data == "success") {
            alert("qna 수정 완료");
          } else {
            alert("qna 수정 실패");
          }
          context.dispatch("getQnaList", this.state.qnaStore.qnaPage);
          context.dispatch("getTotalQna");
        });
    },

    getTotalQna(context) {
      instanceWithAuth.get("/qna/total").then(({ data }) => {
        console.log(data);
        context.commit("QNA_TOTAL", data);
      });
    },

    getComments(context, payload) {
      instanceWithAuth.get(`/comment/${payload}`).then(({ data }) => {
        console.log(data);
        context.commit("GET_COMMENTS", data);
      });
    },
    makeComment(context, payload) {
      console.log(payload);
      console.log("articleno:", this.state.qnaStore.articleno);
      instanceWithAuth.post("/comment/create", payload).then(({ data }) => {
        if (data == "success") {
          alert("댓글 등록 완료");
        } else {
          alert("댓글 등록 실패");
        }
        context.dispatch("getComments", this.state.qnaStore.articleno);
      });
    },
    updateComment(context, payload) {
      console.log(payload["articleno"]);
      instanceWithAuth.put("/comment", payload).then(({ data }) => {
        if (data == "success") {
          alert("댓글 수정 완료");
          context.dispatch("getComments", this.state.qnaStore.articleno);
        } else {
          alert("댓글 수정 실패");
        }
      });
    },
    deleteComment(context, payload) {
      instanceWithAuth.delete(`/comment/${payload}`).then(({ data }) => {
        if (data == "success") {
          alert("댓글 삭제 완료");
          context.dispatch("getComments", this.state.qnaStore.articleno);
        } else {
          alert("댓글 삭제 실패");
        }
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

export default qnaStore;
