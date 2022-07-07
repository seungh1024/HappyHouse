<template>
  <section>
    <article class="find-pw-page">
      <div class="form">
        <form name="findPwForm" id="findPwForm" method="post" action="">
          <input type="hidden" name="act" id="act" value="findBtn" />
          <h3>Find Password</h3>
          <label>Email Address</label>
          <input
            id="email"
            name="email"
            type="email"
            class="form-control form-control-lg"
            v-model="email"
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
            id="findBtn"
            class="btn btn-dark btn-lg btn-block"
            @click="findPw"
          >
            Find Password
          </button>
          <button
            id="backBtn"
            class="btn btn-dark btn-lg btn-block"
            @click="moveLogin"
          >
            Cancel
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
      email: "",
      name: "",
    };
  },
  methods: {
    findPw() {
      if (!this.email) {
        alert("아이디를 입력해주세요.");
      } else if (!this.name) {
        alert("이름을 입력해주세요.");
      } else {
        let user = {
          email: this.email,
          password: "",
          name: this.name,
        };
        this.$store.dispatch("findPw", user);
        this.moveLogin();
      }
    },
    moveLogin() {
      this.$router.push({ path: "/user/login" });
    },
  },
};
</script>

<style></style>
