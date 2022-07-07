<template>
  <div id="detail">
    <div id="formBtn" v-if="userCheck()">
      <b-button variant="primary" @click="modifyQna">수정하기</b-button>
      <b-button v-b-modal.deleteModal variant="danger">삭제하기</b-button>
    </div>
    <b-form>
      <b-form-group id="input-group" label="제목:" label-for="input-1">
      </b-form-group>
      <b-form-input
        id="input-2"
        required
        readonly
        :value="qna.subject"
      ></b-form-input>
      <b-form-group id="input-group" label="내용:" label-for="input-3">
        <b-form-textarea
          id="textarea"
          rows="3"
          max-rows="6"
          readonly
          :value="qna.content"
        ></b-form-textarea>
      </b-form-group>
    </b-form>

    <b-modal id="deleteModal" title="게시글 삭제">
      <p class="my-4">정말 삭제하시겠습니까?</p>
      <template #modal-footer="{ cancel }">
        <b-button size="sm" variant="danger" @click="deleteQna">
          삭제
        </b-button>
        <b-button size="sm" @click="cancel()"> 취소 </b-button>
      </template>
    </b-modal>
    <comment-view></comment-view>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import CommentView from "@/components/comment/CommentView.vue";
export default {
  components: {
    CommentView,
  },
  created() {
    this.getQnaData(this.$route.params.articleno);
    // this.$store.dispatch("getQnaData", this.$route.params.articleno);
  },
  computed: {
    ...mapState("qnaStore", ["qna"]),
    // qna() {
    //   return this.$store.state.qna;
    // },
  },
  methods: {
    ...mapActions("qnaStore", ["getQnaData", "qnaDelete", "setCurrentPage"]),
    modifyQna() {
      if (this.$route.params.userid != this.$store.state.userInfo["idx"]) {
        alert("게시글 작성자만 수정할 수 있습니다");
        return;
      }
      this.$router.push({
        name: "qnaModify",
      });
    },
    deleteQna() {
      if (this.$route.params.userid != this.$store.state.userInfo["idx"]) {
        alert("게시글 작성자만 삭제할 수 있습니다");
        return;
      }
      this.qnaDelete(this.$route.params.articleno);
      // this.$store.dispatch("qnaDelete", this.$route.params.articleno);
      this.$router.push({ name: "qnaList" });
    },
    userCheck() {
      console.log("check userid: ", this.$route.params.userid);
      console.log("check userInfo idx: ", this.$store.state.userInfo["idx"]);
      if (this.$route.params.userid != this.$store.state.userInfo["idx"]) {
        return false;
      }
      return true;
    },
  },
};
</script>

<style scoped>
#detail {
  margin: 10%;
}

#input-group {
  text-align: left;
}
#formBtn {
  float: right;
}
</style>
