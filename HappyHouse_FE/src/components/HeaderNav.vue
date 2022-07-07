<template>
  <div>
    <b-navbar
      pills
      justified
      class="d-flex align-items-center justify-content-between"
      style="background-color: rgb(102, 103, 171)"
      id="bNav"
    >
      <div class="profile">
        <b-navbar-nav
          ><router-link to="/"
            ><img src="@/assets/logo.png" class="profile_img" /></router-link
        ></b-navbar-nav>
      </div>
      <b-navbar-nav
        ><router-link
          :to="{ name: 'apt' }"
          class="mr-5 text-white"
          style="text-decoration: none"
          >View House Deals</router-link
        ></b-navbar-nav
      >
      <b-navbar-nav
        ><router-link
          :to="{ name: 'notice' }"
          class="fs-5 text-white"
          style="text-decoration: none"
          >Notice</router-link
        ></b-navbar-nav
      >
      <b-navbar-nav
        ><router-link
          href="#"
          :to="{ name: 'qna' }"
          class="mr-5 text-white"
          style="text-decoration: none"
          >QnA</router-link
        ></b-navbar-nav
      >
      <b-navbar-nav v-if="!user">
        <router-link
          :to="{ name: 'login' }"
          class="mr-5 text-white"
          style="text-decoration: none"
          >Login</router-link
        >
      </b-navbar-nav>
      <b-navbar-nav v-if="!user"
        ><router-link
          :to="{ name: 'register' }"
          class="mr-5 text-white"
          style="text-decoration: none"
          >Register</router-link
        ></b-navbar-nav
      >
      <b-navbar-nav v-else
        ><router-link
          :to="{ name: 'mypage' }"
          class="mr-5 text-white"
          style="text-decoration: none"
          >{{ userInfo.name }}'s Page</router-link
        ></b-navbar-nav
      >
    </b-navbar>
  </div>
  <!-- <div class="header"> -->
</template>

<script>
export default {
  computed: {
    user() {
      return this.$store.state.user;
    },
    userInfo() {
      return this.$store.state.userInfo;
    },
    userToken() {
      return this.$store.state.userToken;
    },
  },
};
</script>

<style scoped>
.profile_img {
  /* margin: 5px 5px; */
  width: 140px;
  height: 70px;
  /* width: 50px;
  height: 30px; */
}
#bNav {
  width: 100%;
}
ul > * {
  cursor: default;
}
</style>
