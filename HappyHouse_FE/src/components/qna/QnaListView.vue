<template>
  <div id="list">
    <b-table striped hover :fields="fields" :items="boards">
      <template #cell(subject)="data">
        <a
          class="mouseOver"
          @click="getDetail(data.item.articleno, data.item.userid)"
        >
          {{ data.item.subject }}
        </a>
      </template>
    </b-table>
    <div class="overflow-auto" id="pageBar">
      <b-pagination
        v-model="currentPage"
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
      currentPage: 1,
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
    ...mapActions("qnaStore", ["getQnaList", "getTotalQna", "setCurrentPage"]),
    getDetail(value, userid) {
      // console.log(value);
      this.$router.push({
        name: "qnaDetail",
        params: { articleno: value, userid: userid },
      });
    },
  },
  created() {
    if (this.$store.state.pageClear) {
      this.$store.dispatch("pageClear", false);
      this.setCurrentPage(1);
    }
    console.log(this.qnaPage);
    this.currentPage = this.qnaPage;
    console.log("currentPage create: ", this.currentPage);
    this.getQnaList(this.qnaPage);

    if (this.$store.state.user == false) {
      return;
    }

    this.getTotalQna();
    // this.$store.dispatch("getQnaList", 1);
    // this.$store.dispatch("getTotalQna");
  },
  computed: {
    ...mapState("qnaStore", ["qnaList", "total", "qnaPage"]),
    boards() {
      return this.qnaList;
    },
    rows() {
      return this.total;
    },
  },
  watch: {
    currentPage() {
      // this.$store.state.currentPage = this.currentPage;
      this.setCurrentPage(this.currentPage);
      this.getQnaList(this.currentPage);
      console.log("currentPage watch: ", this.qnaPage);
      // this.$store.dispatch("getQnaList", this.$store.state.currentPage);
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
</style>
