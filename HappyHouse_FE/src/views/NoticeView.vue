<template>
  <div>
    <b-row>
      <b-col cols="1"></b-col>
      <b-col cols="10">
        <div id="noticeNav" class="mt-5">
          <h1>공지 사항</h1>
        </div>
        <router-view />
      </b-col>
      <b-col cols="1"></b-col>
    </b-row>
  </div>
</template>

<script>
export default {};
</script>

<style>
#noticeNav {
  text-align: center;
}
</style>
