<template>
  <div id="detail">
    <div id="formBtn">
      <b-button variant="primary" @click.prevent="modifyNotice" v-if="admin"
        >수정하기</b-button
      >
      <b-button v-b-modal.deleteModal variant="danger" v-if="admin"
        >삭제하기</b-button
      >
    </div>
    <b-form>
      <b-form-group id="input-group" label="제목:" label-for="input-1">
      </b-form-group>
      <b-form-input
        id="input-2"
        required
        readonly
        :value="notice.subject"
      ></b-form-input>
      <b-form-group id="input-group" label="내용:" label-for="input-3">
        <b-form-textarea
          id="textarea"
          rows="3"
          max-rows="6"
          readonly
          :value="notice.content"
        ></b-form-textarea>
      </b-form-group>
    </b-form>

    <b-modal id="deleteModal" title="게시글 삭제">
      <p class="my-4">정말 삭제하시겠습니까?</p>
      <template #modal-footer="{ cancel }">
        <b-button size="sm" variant="danger" @click="deleteNotice">
          삭제
        </b-button>
        <b-button size="sm" @click="cancel()"> 취소 </b-button>
      </template>
    </b-modal>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  created() {
    this.getNoticeData(this.$route.params.articleno);
  },
  computed: {
    ...mapState("noticeStore", ["notice"]),
    admin() {
      return this.$store.state.admin;
    },
  },
  methods: {
    ...mapActions("noticeStore", ["getNoticeData", "noticeDelete"]),
    modifyNotice() {
      this.$router.push({
        name: "modifyNotice",
      });
    },
    deleteNotice() {
      this.noticeDelete(this.$route.params.articleno);
      this.$router.push({ name: "noticeList" });
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
