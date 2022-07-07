<template>
  <section>
    <article class="remove-page">
      <div class="form">
        <form name="removeForm" id="removeForm" method="post" action="">
          <h3>Withdrawal</h3>
          <label>Password</label>
          <input
            id="password"
            name="password"
            type="password"
            class="form-control form-control-lg"
            v-model="password"
          />
          <button
            id="yesBtn"
            class="btn btn-dark btn-lg btn-block"
            @click.prevent="deleteUser"
          >
            Ok
          </button>
          <button
            id="noBtn"
            class="btn btn-dark btn-lg btn-block"
            @click="moveMyPage"
          >
            Cancel
          </button>
        </form>
      </div>
    </article>
  </section>
</template>

<script>
import bcryptjs from "bcryptjs";
import store from "@/store/index";
import router from "@/router/index";

export default {
  data() {
    return {
      password: "",
    };
  },
  computed: {
    userInfo() {
      return this.$store.state.userInfo;
    },
  },
  methods: {
    async deleteUser() {
      // console.log(bcryptjs.compare(this.password, this.userInfo.password));
      let email = this.userInfo.email;
      await bcryptjs.compare(
        this.password,
        this.userInfo.password,
        function (err, result) {
          if (err) {
            console.log(err);
          }
          if (result == false) {
            alert("비밀번호를 확인해주세요.");
          } else {
            console.log(email);
            store.dispatch("delete", email);
            router.push({ path: "/" });
          }
        }
      );
      // console.log(same);
    },
    moveMyPage() {
      this.$router.push({ path: "/user/mypage" });
    },
  },
};
</script>

<style></style>
