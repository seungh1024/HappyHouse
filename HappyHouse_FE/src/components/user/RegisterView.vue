<template>
  <section>
    <div class="login-page">
      <div class="form">
        <form name="register-form" id="register-form" method="post" action="">
          <input type="hidden" name="act" id="act" value="register" />
          <h3>Register</h3>
          <label>Email Address</label>
          <input
            id="email"
            name="email"
            type="email"
            class="form-control form-control-lg"
            v-model="email"
          />
          <button class="py-2 px-4 rounded" @click="sendMail">
            Verify Email
          </button>

          <input
            class="form-control form-control-lg"
            id="code"
            name="code"
            type="text"
            v-model="code"
            required=""
            aria-label="Code"
            placeholder="Enter Code"
          />
          <button class="py-2 px-4 rounded" @click.prevent="checkAuth">
            Verify Code
          </button>

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
            type="button"
            id="registerBtn"
            class="btn btn-dark btn-lg btn-block"
            @click.prevent="register"
          >
            Register
          </button>
        </form>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      email: "",
      password: "",
      pwcheck: "",
      name: "",
      code: "",
      emailSent: false,
      verified: false,
    };
  },
  // computed: {
  //   checkCode() {
  //     return this.$store.state.checkCode;
  //   },
  // },
  methods: {
    register() {
      if (!this.email) {
        alert("이메일을 입력해주세요.");
      } else if (!this.code) {
        alert("이메일 인증번호를 입력해주세요.");
      } else if (!this.password) {
        alert("비밀번호를 입력해주세요.");
      } else if (!this.pwcheck) {
        alert("비밀번호를 재입력해주세요.");
      } else if (this.password != this.pwcheck) {
        alert("비밀번호를 확인해주세요.");
      } else if (!this.name) {
        alert("이름을 입력해주세요.");
      } else {
        let user = {
          email: this.email,
          password: this.password,
          name: this.name,
        };
        console.log(user);
        this.$store.dispatch("register", user);
        this.moveMain();
      }
    },
    moveMain() {
      this.$router.push({ path: "/" });
    },
    sendMail() {
      this.$store.dispatch("checkEmail", this.email);
      alert(this.email + "으로 인증번호가 전송되었습니다.");
      this.emailSent = true;
    },
    async checkAuth() {
      if (this.emailSent) {
        await this.$store.dispatch("verifyCode", this.code).then((value) => {
          console.log(value);
          console.log(this.$store.state.checkCode);
          if (this.$store.state.checkCode == 1) {
            alert("메일 인증이 완료되었습니다.");
            this.verified = true;
          } else {
            alert(this.code + "가 인증번호와 일치하지 않습니다.");
            this.verified = false;
          }
        });
      } else {
        alert("메일을 입력하여 인증번호를 받아주세요.");
      }
    },
  },
};
</script>

<style></style>
