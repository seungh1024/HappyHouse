<template>
  <section>
    <article class="user-info">
      <div class="form">
        <form name="info-form" class="info-form" method="post" action="">
          <input type="hidden" name="act" id="act" value="info" />
          <div class="nickname">
            <h3>{{ userInfo.name }}'s Profile</h3>
          </div>
          <label>Email Address:</label>
          <input
            class="form-control form-control-lg"
            v-model="userInfo.email"
            readonly
          />
          <label>Name:</label>
          <input
            class="form-control form-control-lg"
            v-model="userInfo.name"
            readonly
          />
          <button
            id="modifyBtn"
            @click="modifyUserInfo"
            class="btn btn-dark btn-lg btn-block"
          >
            Edit Profile
          </button>
          <button
            id="removeBtn"
            @click="deleteUser"
            class="btn btn-dark btn-lg btn-block"
          >
            Withdrawal Request
          </button>
          <button
            id="logoutBtn"
            @click="logout"
            class="btn btn-dark btn-lg btn-block"
          >
            LOGOUT
          </button>
        </form>
      </div>
    </article>
  </section>
</template>

<script>
import { mapMutations } from "vuex";
// import { instanceWithAuth } from "@/api/index";

export default {
  computed: {
    userInfo() {
      return this.$store.state.userInfo;
    },
  },
  methods: {
    modifyUserInfo() {
      this.$router.push({ path: "/user/modify" });
    },
    deleteUser() {
      this.$router.push({ path: "/user/delete" });
    },
    ...mapMutations(["USER_EXIST", "USER_INFO", "TOKEN"]),
    logout() {
      this.USER_EXIST(false);
      this.USER_INFO(null);
      this.TOKEN(null);
      alert("logout!");
      localStorage.removeItem("vuex");
      // instanceWithAuth.defaults.headers.common["token"] = `${null}`;
      this.$router.push({ path: "/" });
    },
  },
};
</script>

<style></style>
