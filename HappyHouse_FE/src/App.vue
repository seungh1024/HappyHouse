<template>
  <div id="app">
    <header-nav id="headerView"></header-nav>
    <router-view id="routerView" />
  </div>
</template>

<script>
import HeaderNav from "@/components/HeaderNav.vue";

export default {
  name: "App",
  components: { HeaderNav },
};
</script>

<style>
#app {
  position: absolute;
  width: 100%;
}
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  list-style: none;
}

a.nav-link {
  font-weight: bold;
  font-family: sans-serif;
  font-size: large;
  text-decoration: none;
  color: #020202;
}

a:hover {
  cursor: pointer;
}

.login-page,
.user-info,
.change-page,
.remove-page,
.logout-page,
.find-pw-page {
  width: 500px;
  padding: 7% 0 0;
  margin: auto;
}
.form {
  position: relative;
  z-index: 1;
  background: #ffffff;
  max-width: 500px;
  margin: 0 auto 100px;
  border-radius: 10px;
  padding: 45px;
  text-align: center;
  box-shadow: 0 0 20px 0 #6868acaa, 0 5px 5px 0 #6868acaa;
}
.form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  background: #f2f2f2;
  width: 100%;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}
.form button {
  margin: 0 0 15px;
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #6868ac;
  width: 100%;
  border: 0;
  padding: 15px;
  color: #ffffff;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}
.form button:hover,
.form button:active,
.form button:focus {
  background: #6868acaa;
}

.info-form div h2 {
  display: inline;
}

.nickname {
  margin-bottom: 20px;
}

.user {
  margin-bottom: 20px;
}

.user h3 {
  display: inline;
}

label {
  float: left;
  font-weight: 500;
}
.forgot-password,
.forgot-password a {
  text-align: right;
  font-size: 13px;
  padding-top: 10px;
  color: #7a7a7a;
  margin: 0;
}
.forgot-password a {
  color: #2554ff;
}
.social-icons {
  text-align: center;
  font-family: "Open Sans";
  font-weight: 300;
  font-size: 1.5em;
  color: #222222;
}
.social-icons ul {
  list-style: none;
  margin: 0;
  padding: 0;
}
.social-icons ul li {
  display: inline-block;
  zoom: 1;
  width: 65px;
  vertical-align: middle;
  border: 1px solid #e3e8f9;
  font-size: 15px;
  height: 40px;
  line-height: 40px;
  margin-right: 5px;
  background: #f4f6ff;
}
.social-icons ul li a {
  display: block;
  font-size: 1.4em;
  margin: 0 5px;
  text-decoration: none;
}
.social-icons ul li a i {
  -webkit-transition: all 0.2s ease-in;
  -moz-transition: all 0.2s ease-in;
  -o-transition: all 0.2s ease-in;
  -ms-transition: all 0.2s ease-in;
  transition: all 0.2s ease-in;
}
.social-icons ul li a:focus i,
.social-icons ul li a:active i {
  transition: none;
  color: #222222;
}
#routerView {
  width: 100%;
  height: 100%;
}
#headerView {
  width: 100%;
  position: relative;
}
</style>
