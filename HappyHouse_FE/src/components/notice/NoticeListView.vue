<template>
  <div id="list">
    <div id="addBtn">
      <b-button
        type="submit"
        variant="primary"
        @click.prevent="add"
        v-if="admin"
        >글쓰기</b-button
      >
    </div>
    <b-table striped hover :fields="fields" :items="boards">
      <template #cell(subject)="data">
        <a class="mouseOver" @click="getDetail(data.item.articleno)">
          {{ data.item.subject }}
        </a>
      </template>
    </b-table>
    <div class="overflow-auto" id="pageBar">
      <b-pagination
        v-model="noticeCurrentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
      ></b-pagination>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  data() {
    return {
      noticeCurrentPage: 1,
      perPage: 10,
      fields: [
        {
          key: "articleno",
          label: "번호",
        },
        {
          key: "subject",
          label: "제목",
        },
        {
          key: "name",
          label: "작성자",
        },
        {
          // A regular column with custom formatter
          key: "regtime",
          label: "작성일",
        },
      ],
    };
  },
  methods: {
    ...mapActions("noticeStore", [
      "getNoticeList",
      "getTotalNotice",
      "setCurrentPage",
    ]),
    add() {
      this.$router.push({ name: "noticeAdd" });
    },
    getDetail(value) {
      // console.log(value);
      this.$router.push({ name: "noticeDetail", params: { articleno: value } });
    },
  },
  created() {
    if (this.$store.state.pageClear) {
      this.$store.dispatch("pageClear", false);
      this.setCurrentPage(1);
      // console.log("noticePage: ", this.noticePage);
    }
    this.noticeCurrentPage = this.noticePage;
    console.log("dispatch before create list");
    this.getNoticeList(this.qnaPage);

    if (this.$store.state.user == false) {
      return;
    }
    console.log("dispatch before create total");
    this.getTotalNotice();
  },
  computed: {
    ...mapState("noticeStore", ["noticeList", "noticeTotal", "noticePage"]),
    boards() {
      return this.noticeList;
    },
    rows() {
      return this.noticeTotal;
    },
    admin() {
      return this.$store.state.admin;
    },
  },
  watch: {
    noticeCurrentPage() {
      // this.$store.state.noticeCurrentPage = this.noticeCurrentPage;
      console.log("dispatch before watch");
      this.setCurrentPage(this.noticeCurrentPage);
      this.getNoticeList(this.noticeCurrentPage);
    },
  },
};
</script>

<style scoped>
.mouseOver {
  cursor: pointer;
}
#list {
  margin: 5%;
}
#pageBar {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}

#addBtn {
  text-align: right;
}
</style>
