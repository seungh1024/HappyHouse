<template>
  <section>
    <article class="change-page">
      <div class="form">
        <form name="modifyForm" id="modifyForm" method="post" action="">
          <!-- <input type="hidden" name="act" id="act" value="yesBtn" /> -->
          <h3>Edit Profile</h3>
          <label>Email Address</label>
          <input
            class="form-control form-control-lg"
            v-model="userInfo.email"
            readonly
          />
          <label>Password</label>
          <input
            id="password"
            name="password"
            type="password"
            class="form-control form-control-lg"
            v-model="password"
          />
          <label>Verify Password</label>
          <input
            id="pwcheck"
            name="pwcheck"
            type="password"
            class="form-control form-control-lg"
            v-model="pwcheck"
          />
          <label>Name</label>
          <input
            id="name"
            name="name"
            type="text"
            class="form-control form-control-lg"
            v-model="name"
          />
          <button
            id="yesBtn"
            class="btn btn-dark btn-lg btn-block"
            @click.prevent="modify"
          >
            Ok
          </button>
          <button
            id="noBtn"
            class="btn btn-dark btn-lg btn-block"
            @click="moveMyPage"
          >
            cancel
          </button>
        </form>
      </div>
    </article>
  </section>
</template>

<script>
export default {
  data() {
    return {
      password: "",
      pwcheck: "",
      name: this.$store.state.userInfo.name,
    };
  },
  computed: {
    userInfo() {
      return this.$store.state.userInfo;
    },
  },
  methods: {
    modify() {
      if (!this.password) {
        alert("비밀번호를 입력해주세요.");
      } else if (!this.pwcheck) {
        alert("비밀번호를 재입력해주세요.");
      } else if (this.password != this.pwcheck) {
        alert("비밀번호를 확인해주세요.");
      } else if (!this.name) {
        alert("이름을 입력해주세요.");
      } else {
        let user = {
          email: this.userInfo.email,
          password: this.password,
          name: this.name,
        };
        this.$store.dispatch("modify", user);
        this.moveMyPage();
      }
    },
    moveMyPage() {
      this.$router.push({ path: "/user/mypage" });
    },
  },
};
</script>

<style></style>
