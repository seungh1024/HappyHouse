<template>
  <div id="modify">
    <b-form @submit.prevent="onSubmit" @reset.prevent="onReset">
      <b-form-group id="input-group-1" label="제목:" label-for="input-1">
      </b-form-group>
      <b-form-input
        id="input-2"
        required
        :value="qna.subject"
        v-model="qna.subject"
      ></b-form-input>
      <b-form-group id="input-group-3" label="내용:" label-for="input-3">
        <b-form-textarea
          id="textarea"
          rows="3"
          max-rows="6"
          :value="qna.content"
          v-model="qna.content"
        ></b-form-textarea>
      </b-form-group>

      <b-button type="submit" variant="primary">수정하기</b-button>
      <b-button type="reset" variant="danger">내용 초기화</b-button>
    </b-form>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  data() {
    return {
      qnaData: {},
    };
  },
  created() {
    this.qnaData = this.qna;
  },
  methods: {
    ...mapActions("qnaStore", ["qnaModify"]),
    onSubmit() {
      this.qnaModify(this.qna);
      // this.$store.dispatch("qnaModify", this.qna);
      this.$router.push({ name: "qnaList" });
    },
    onReset() {
      this.qna.content = "";

      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
  },
  computed: {
    ...mapState("qnaStore", ["qna"]),
  },
};
</script>

<style scoped>
#modify {
  margin: 10%;
}

#input-group {
  text-align: left;
}
</style>
