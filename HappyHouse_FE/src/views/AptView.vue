<template>
  <div id="home" class="mw-100 mh-100">
    <b-col cols="4" id="searchBar">
      <b-row align-h="center">
        <apt-search></apt-search>
      </b-row>
      <b-row>
        <apt-list></apt-list>
      </b-row>
    </b-col>

    <b-row class="mw-100 mh-100" id="kakaoMap">
      <kakao-map class="mw-100 mh-100" id="kakaoMapContainer"></kakao-map>
    </b-row>

    <div class="modal" id="myModal">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title">아파트 거래정보</h4>
            <button type="button" class="close" data-dismiss="modal">
              &times;
            </button>
          </div>

          <div class="container">
            <table class="table" id="dealTable">
              <thead>
                <tr>
                  <th>Firstname</th>
                  <th>Lastname</th>
                  <th>Email</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>John</td>
                  <td>Doe</td>
                  <td>john@example.com</td>
                </tr>
                <tr>
                  <td>Mary</td>
                  <td>Moe</td>
                  <td>mary@example.com</td>
                </tr>
                <tr>
                  <td>July</td>
                  <td>Dooley</td>
                  <td>july@example.com</td>
                </tr>
              </tbody>
            </table>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <button type="button" class="btn btn-danger" data-dismiss="modal">
              Close
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import KakaoMap from "@/components/KakaoMap.vue";
import AptSearch from "@/components/apt/AptSearch.vue";
import AptList from "@/components/apt/AptList.vue";

export default {
  data() {
    return {};
  },
  components: {
    KakaoMap,
    AptSearch,
    AptList,
  },
};
</script>

<style scoped>
#home {
  position: relative;
}
#kakaoMap {
  position: absolute;
  z-index: -1;
  /* width: 20; */
  /* height: 100%; */
}
#searchBar {
  position: absolute;
  z-index: 2;
  background-color: rgba(255, 255, 255, 0.8);
  height: 1000vh;
}
</style>
