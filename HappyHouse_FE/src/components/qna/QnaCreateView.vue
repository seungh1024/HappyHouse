<template>
  <div id="create">
    <b-form @submit.prevent="onSubmit" @reset="onReset" v-if="show">
      <b-form-group id="input-group" label="제목:" label-for="input-1">
      </b-form-group>
      <b-form-input
        id="input-2"
        v-model="form.subject"
        placeholder="Enter subject"
        required
      ></b-form-input>
      <b-form-group id="input-group" label="내용:" label-for="input-3">
        <b-form-textarea
          id="textarea"
          v-model="form.content"
          placeholder="Enter something..."
          rows="3"
          max-rows="6"
        ></b-form-textarea>
      </b-form-group>

      <b-button type="submit" variant="primary">게시글 생성</b-button>
      <b-button type="reset" variant="danger">초기화</b-button>
    </b-form>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  data() {
    return {
      form: {
        subject: "",
        content: "",
      },

      show: true,
    };
  },
  methods: {
    ...mapActions("qnaStore", ["qnaCreate", "setCurrentPage"]),
    onSubmit() {
      console.log(this.form);
      this.qnaCreate(this.form);
      this.setCurrentPage(1);
      this.$router.push({ name: "qnaList" });
    },
    onReset(event) {
      event.preventDefault();
      // Reset our form values
      this.form.subject = "";
      this.form.content = "";

      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
  },
};
</script>

<style scoped>
#create {
  margin: 10%;
}

#input-group {
  text-align: left;
}
</style>
