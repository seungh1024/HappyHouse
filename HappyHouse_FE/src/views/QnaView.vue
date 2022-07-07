<template>
  <div>
    <b-row>
      <b-col cols="1"></b-col>
      <b-col cols="10">
        <div id="qnaNav" class="mt-5">
          <h1>QnA 게시판</h1>
          <router-link to="/qna/add">[QnA 작성하기]</router-link>
          <router-link to="/qna/list">[게시글 보기]</router-link>
        </div>
        <router-view />
      </b-col>
      <b-col cols="1"></b-col>
    </b-row>
  </div>
</template>

<style scoped>
#qnaNav {
  text-align: center;
}
</style>
