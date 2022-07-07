<template>
  <div class="login-page">
    <form class="form" name="loginform" id="loginform" method="post" action="">
      <input type="hidden" name="act" id="act" value="login" />
      <h3>Login</h3>
      <label>Email Address</label>
      <input
        id="email"
        name="email"
        type="email"
        class="form-control form-control-lg"
        v-model="email"
      />
      <label>Password</label>
      <input
        id="password"
        name="password"
        type="password"
        class="form-control form-control-lg"
        v-model="password"
      />
      <button
        id="loginBtn"
        @click="login"
        class="btn btn-dark btn-lg btn-block"
      >
        LOGIN
      </button>
      <p class="forgot-password text-right mt-2 mb-4" @click="findPw">
        Forgot password ?
      </p>
      <div class="social-icons">
        <ul>
          <li>
            <a href="#"><i class="fa fa-google"></i></a>
          </li>
          <li>
            <a href="#"><i class="fa fa-facebook"></i></a>
          </li>
          <li>
            <a href="#"><i class="fa fa-twitter"></i></a>
          </li>
        </ul>
      </div>
    </form>
  </div>

  <!-- <div class="login-page">
    <div class="form">
      <form name="loginform" id="loginform" method="post" action="">
        <input type="hidden" name="act" id="act" value="login" />
        <input
          id="email"
          name="email"
          type="text"
          placeholder="EMAIL"
          v-model="email"
        />
        <input
          id="password"
          name="password"
          type="password"
          placeholder="Password"
          v-model="password"
        />
        <button type="button" id="loginBtn" @click="login">로그인</button>
        <button @click="register">회원가입</button>
        <button @click="findPw">비밀번호 찾기</button>
      </form>
    </div>
  </div> -->
</template>

<script>
import { mapActions } from "vuex";
export default {
  data() {
    return {
      email: "",
      password: "",
    };
  },

  methods: {
    ...mapActions("aptStore", ["aptClear"]),
    login() {
      if (!this.email) {
        alert("아이디를 입력해주세요.");
      } else if (!this.password) {
        alert("비밀번호를 입력해주세요.");
      } else {
        let user = {
          email: this.email,
          password: this.password,
        };
        this.$store.dispatch("login", user);
        this.moveMain();
        this.aptClear();
      }
    },
    findPw() {
      this.$router.push({ path: "/user/findPw" });
    },
    moveMain() {
      this.$router.push({ path: "/" });
    },
  },
};
</script>

<style></style>
