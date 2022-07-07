<template>
  <div>
    <div id="commentArea">
      <input type="text" name="" id="" v-model="comment" />
      <b-button class="w-10 p-2" variant="success" @click="addComment"
        >등록</b-button
      >
    </div>
    <div>
      <table id="book-list">
        <thead>
          <tr>
            <th>no</th>
            <th>이름</th>
            <th>댓글</th>
            <th>작성시간</th>
            <th>수정,삭제</th>
          </tr>
        </thead>
        <tbody>
          <tr v-show="check">
            <td colspan="4">
              <input type="text" name="" id="" v-model="modifyComment" />
            </td>
            <td colspan="2">
              <b-button variant="success" class="w-25 p-1" @click="updateBtn()"
                >수정</b-button
              ><b-button
                variant="secondary"
                class="w-25 p-1"
                @click="cancelBtn()"
                >취소</b-button
              >
            </td>
          </tr>
          <tr v-for="(com, index) in comments" :key="index">
            <td>{{ com.comment_no }}</td>
            <td>{{ com.user_name }}</td>
            <td>{{ com.comment }}</td>
            <td>{{ com.comment_time }}</td>
            <td colspan="2" v-show="checkUser(com.user_name)">
              <b-button
                variant="primary"
                class="w-25 p-1"
                @click="makeUpdate(com.comment_no, com.comment, com.user_name)"
                >수정</b-button
              ><b-button
                variant="danger"
                class="w-25 p-1"
                @click="deleteBtn(com.comment_no, com.user_name)"
                >삭제</b-button
              >
            </td>
            <td colspan="2" v-show="!checkUser(com.user_name)"></td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  data: function () {
    return {
      comment: "",
      check: false,
      modifyComment: "",
      modifyNo: 0,
    };
  },
  created() {
    this.setArticleNo(this.$route.params.articleno);
    console.log(this.articleno);
    this.getComments(this.articleno);
    // this.$store.dispatch("getComments", articleno);
  },
  methods: {
    ...mapActions("qnaStore", [
      "getComments",
      "makeComment",
      "updateComment",
      "deleteComment",
      "setArticleNo",
    ]),
    addComment() {
      console.log(this.$store.state.userInfo);
      let body = {
        comment: this.comment,
        articleno: this.articleno,
        user_name: this.$store.state.userInfo["name"],
      };
      this.makeComment(body);
      // this.$store.dispatch("makeComment", this.comment);
      this.comment = "";
    },
    makeUpdate(no, com, userName) {
      if (userName != this.$store.state.userInfo["name"]) {
        alert("권한이 없습니다! 자신의 댓글만 수정 가능합니다!");
        return;
      }
      this.check = true;
      this.modifyComment = com;
      this.modifyNo = no;
    },
    updateBtn() {
      console.log("modifyNo: ", this.modifyNo);
      this.updateComment({
        comment_no: this.modifyNo,
        comment: this.modifyComment,
      });
      // this.$store.dispatch("updateComment", {
      //   comment_no: this.modifyNo,
      //   comment: this.modifyComment,
      // });
      this.check = false;
    },
    cancelBtn() {
      this.check = false;
    },
    deleteBtn(no, userName) {
      if (userName != this.$store.state.userInfo["name"]) {
        alert("권한이 없습니다! 자신의 댓글만 삭제 가능합니다!");
        return;
      }
      this.deleteComment(no);
      // this.$store.dispatch("deleteComment", no);
    },
    checkUser(userName) {
      if (userName == this.$store.state.userInfo["name"]) {
        return true;
      }
      return false;
    },
  },
  computed: {
    ...mapState("qnaStore", ["comments", "articleno"]),
    // comments() {
    //   return this.$store.state.comments;
    // },
  },
};
</script>

<style scoped>
input {
  width: 90%;
  padding: 12px 20px;
  margin: 8px 10px;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  color: #787878;
  font-size: medium;
}
#book-list {
  border-collapse: collapse;
  width: 100%;
}

#book-list thead {
  background-color: #ccc;
  font-weight: bold;
}

#book-list td,
#book-list th {
  text-align: center;
  border-bottom: 1px solid #ddd;
  height: 50px;
}
</style>
