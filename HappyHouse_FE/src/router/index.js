import Vue from "vue";
import VueRouter from "vue-router";
import AptView from "../views/AptView.vue";
import MainContentView from "../components/MainContent.vue";
import UserView from "../views/UserView.vue";
import LoginView from "@/components/user/LoginView.vue";
import RegisterView from "@/components/user/RegisterView.vue";
import MyPageView from "@/components/user/MyPageView.vue";
import DeleteUserView from "@/components/user/DeleteUserView.vue";
import ModifyUserInfoView from "@/components/user/ModifyUserInfoView.vue";
import FindPwView from "@/components/user/FindPwView.vue";

// import qnaStore from "@/store/modules/qnaStore";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "mainContent",
    component: MainContentView,
  },
  {
    path: "/user",
    name: "user",
    component: UserView,
    // beforeEnter: beforeAuth(true),
  },
  {
    path: "/user/login",
    name: "login",
    component: LoginView,
    // beforeEnter: beforeAuth(false),
  },
  {
    path: "/user/register",
    name: "register",
    component: RegisterView,
    // beforeEnter: beforeAuth(false),
  },
  {
    path: "/user/mypage",
    name: "mypage",
    component: MyPageView,
    // beforeEnter: beforeAuth(true),
  },
  {
    path: "/user/delete",
    name: "delete",
    component: DeleteUserView,
    // beforeEnter: beforeAuth(true),
  },
  {
    path: "/user/modify",
    name: "modify",
    component: ModifyUserInfoView,
    // beforeEnter: beforeAuth(true),
  },
  {
    path: "/user/findPw",
    name: "findPw",
    component: FindPwView,
    // beforeEnter: beforeAuth(false),
  },

  // apt router
  {
    path: "/apt",
    name: "apt",
    component: AptView,
  },

  // qna 게시판 router
  {
    path: "/qna",
    name: "qna",
    component: () => import("@/views/QnaView.vue"),
    redirect: `/qna/list`,
    children: [
      {
        path: "list",
        name: "qnaList",
        component: () => import("@/components/qna/QnaListView.vue"),
      },
      {
        path: "add",
        name: "qnaAdd",
        component: () => import("@/components/qna/QnaCreateView.vue"),
      },
      {
        path: "detail/:articleno/:userid",
        name: "qnaDetail",
        component: () => import("@/components/qna/QnaDetailView.vue"),
      },
      {
        path: "modify",
        name: "qnaModify",
        component: () => import("@/components/qna/QnaModifyView.vue"),
      },
      {
        path: "comment",
        name: "comment",
        component: () => import("@/components/comment/CommentView.vue"),
      },
    ],
  },
  // 공지사항 router
  {
    path: "/notice",
    name: "notice",
    component: () => import("@/views/NoticeView.vue"),
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "noticeList",
        component: () => import("@/components/notice/NoticeListView.vue"),
      },
      {
        path: "add",
        name: "noticeAdd",
        component: () => import("@/components/notice/NoticeCreateView.vue"),
      },
      {
        path: "detail/:articleno",
        name: "noticeDetail",
        component: () => import("@/components/notice/NoticeDetailView.vue"),
      },
      {
        path: "modify",
        name: "modifyNotice",
        component: () => import("@/components/notice/NoticeModifyView.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
