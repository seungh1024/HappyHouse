<template>
  <div class="d-flex justify-content-center" id="container">
    <div id="map" class="mw-100 mh-100"></div>
    <!-- <div id="searchInput"><input type="text" name="" id="" /></div> -->
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";
let overlay = null;
export default {
  data() {
    return {
      map: null,
      markers: [],
      customs: [],
      // overlay: [],
    };
  },
  methods: {
    ...mapActions("aptStore", ["setNowPoint", "setOverlay"]),
    ...mapGetters("aptStore", ["getOverlay"]),
    initMap() {
      this.map = null;
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      this.map = new window.kakao.maps.Map(container, options);
      if (this.apts.length > 0) {
        this.makeMarkers();
      }

      var nowMarker = new window.kakao.maps.Marker({
        // 지도 중심좌표에 마커를 생성합니다
        nowPosition: this.map.getCenter(),
      });
      // 지도에 마커를 표시합니다
      nowMarker.setMap(this.map);

      // =======마우스 클릭시 해당 위치에 마커 표시=============
      window.kakao.maps.event.addListener(this.map, "click", (mouseEvent) => {
        // 클릭한 위도, 경도 정보를 가져옵니다
        var latlng = mouseEvent.latLng;
        console.log("now Pointer: ", latlng);

        // 마커 위치를 클릭한 위치로 옮깁니다
        nowMarker.setPosition(latlng);

        // var message = "클릭한 위치의 위도는 " + latlng.getLat() + " 이고, ";
        // message += "경도는 " + latlng.getLng() + " 입니다";

        // console.log(message);
        // console.log(this);
        //현재 위치 등록 -> 최단경로 사용하기 위함
        this.setNowPoint({
          aptName: "현재 위치",
          lat: latlng.getLat(),
          lng: latlng.getLng(),
        });
        // console.log(this.$store.state.aptStore.startPoint);
      });
    },
    makeMap(positions, map) {
      if (overlay) {
        overlay.setMap(null);
      }
      // ===========건물 정보 마커 표시==============
      // 마커 이미지의 이미지 주소입니다

      var imageSrc = require("@/assets/homeMarker.png");

      // 마커 이미지의 이미지 크기 입니다
      var imageSize = new window.kakao.maps.Size(24, 35);

      // 마커 이미지를 생성합니다
      var markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize);
      for (let i = 0; i < positions.length; i++) {
        // 마커를 생성합니다
        var marker = new window.kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: positions[i].latlng, // 마커를 표시할 위치
          // title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
          // clickable: true,
          // ======= 커스텀 오버레이 -> 길찾기로 연결해주는 상태창 =============
        });
        // 이전에 기록한 마커 지우기 위해 배열에 푸쉬
        this.markers.push(marker);

        // ======= 커스텀 오버레이 생성 시작 ============
        var customOverlay = new window.kakao.maps.CustomOverlay({
          position: positions[i].latlng,
          // removeable: true,
          content: `
        <div class="customoverlay">
            <a href="https://map.kakao.com/link/to/${positions[i].title},${positions[i].latlng.Ma},${positions[i].latlng.La}" target="_blank">
              <span class="title">${positions[i].title}</span>
            </a>
          </div>
          `,
        });
        customOverlay.setMap(null);

        // 이벤트 등록 -> 마커 클릭하면 오버레이 띄워줌
        window.kakao.maps.event.addListener(
          marker,
          "click",
          makeClickListener(map, customOverlay)
        );

        this.customs.push(customOverlay);
      }
      function makeClickListener(map, customOverlay) {
        return function () {
          console.log(overlay);
          if (overlay) {
            overlay.setMap(null);
          }
          overlay = customOverlay;
          customOverlay.setMap(map);
        };
      }
      console.log("first position:! ", positions[0].latlng);
      var firstCustomOverlay = new window.kakao.maps.CustomOverlay({
        position: positions[0].latlng,
        // removeable: true,
        content: `
        <div class="customoverlay">
            <a href="https://map.kakao.com/link/to/${positions[0].title},${positions[0].latlng.Ma},${positions[0].latlng.La}" target="_blank">
              <span class="title">${positions[0].title}</span>
            </a>
          </div>
          `,
      });
      // if (!overlay) {
      // }
      firstCustomOverlay.setMap(map);
      overlay = firstCustomOverlay;

      // ======= 커스텀 오버레이 생성 완료 =============
    },

    panTo(latlng, map) {
      // 이동할 위도 경도 위치를 생성합니다
      latlng["La"] = latlng["La"] - 0.012;
      latlng["Ma"] = latlng["Ma"] - 0.0025380516287;
      var moveLatLon = latlng;

      // 지도 중심을 부드럽게 이동시킵니다
      // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
      map.setCenter(moveLatLon);
    },
    closeOverlay(overlay) {
      overlay.setMap(null);
    },
    // 지도 마커 생성 메소드
    makeMarkers() {
      // 기존 마커 있다면 삭제 (중복 방지)
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => {
          marker.setMap(null);
        });
      }
      // 기존 커스텀 오버레이 있다면 삭제
      if (this.customs.length > 0) {
        this.customs.forEach((custom) => {
          custom.setMap(null);
        });
      }

      let positions = [];
      if (this.apts.length > 0) {
        this.apts.forEach((apt) => {
          positions.push({
            title: apt.apartmentName,
            latlng: new window.kakao.maps.LatLng(apt.lat, apt.lng),
          });
        });

        this.makeMap(positions, this.map);
        this.panTo(positions[0].latlng, this.map);
      }
    },
    setOverlay(apt, map) {
      if (overlay) {
        overlay.setMap(null);
      }
      let latlng = new window.kakao.maps.LatLng(apt.lat, apt.lng);
      let firstCustomOverlay = new window.kakao.maps.CustomOverlay({
        position: latlng,
        // removeable: true,
        content: `
        <div class="customoverlay">
            <a href="https://map.kakao.com/link/to/${apt.apartmentName},${apt.lat},${apt.lng}" target="_blank">
              <span class="title">${apt.apartmentName}</span>
            </a>
          </div>
          `,
      });
      // if (!overlay) {
      // }
      firstCustomOverlay.setMap(map);
      overlay = firstCustomOverlay;
      this.panTo(latlng, map);
    },
  },
  mounted() {
    if (!window.kakao || !window.kakao.maps) {
      // 카카오 맵 안만들어 졌을 때만 생성
      const script = document.createElement("script"); // script 객체
      // script src 등록
      script.onload = () => window.kakao.maps.load(this.initMap);
      script.type = "application/javascript";
      script.src = `https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAO_KEY}`;
      document.head.appendChild(script);
    } else {
      // console.log("이미 지도 로딩함", window.kakao);
      this.initMap();
    }
  },

  computed: {
    ...mapState("aptStore", ["apts", "clickedApt"]),
  },

  watch: {
    apts() {
      if (this.apts.length > 0) {
        this.makeMarkers();
      }
    },
    clickedApt() {
      console.log("clicked apt: ", this.clickedApt);
      this.setOverlay(this.clickedApt, this.map);
    },
  },
};
</script>

<style>
#map {
  width: 1000vh;
  height: 100vh;
}
.info-title {
  display: block;
  background: #50627f;
  color: #fff;
  text-align: center;
  height: 24px;
  line-height: 22px;
  border-radius: 4px;
  padding: 0px 10px;
}
.customoverlay {
  position: relative;
  bottom: 50px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
}
.customoverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.customoverlay a {
  display: block;
  text-decoration: none;
  color: #000;
  text-align: center;
  border-radius: 6px;
  font-size: 14px;
  font-weight: bold;
  overflow: hidden;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.customoverlay .title {
  display: block;
  text-align: center;
  background: #fff;
  margin-right: 35px;
  padding: 10px 15px;
  font-size: 14px;
  font-weight: bold;
}
.customoverlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: hidden;
}
.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.info .link {
  color: #5085bb;
}
/* #container {
  position: absolute;
  width: 100%;
  height: 100%;
} */
</style>
