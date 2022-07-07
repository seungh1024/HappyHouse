import { instanceWithAuth } from "@/api";
import axios from "axios";

const aptStore = {
  namespaced: true,
  state: {
    apts: [],
    dongCode: "",
    deals: [],
    selected: [], //최단경로 탐색을 위한 선택 배열
    startPoint: {},
    dijkstra: [],
    dijkstraResult: [],
    dijkstraDistance: [],
    show: false,
    clickedApt: null,
  },
  getters: {
    getOverlay(state) {
      return state.overlay;
    },
  },

  mutations: {
    GET_APTS_LIST(state, apts) {
      state.apts = apts;
      // 선택한 기존 아파트 체크 리스트도 삭제함
      state.selected = [];
      // 최적 경로 정보도 새로운 아파트가 들어왔으니 없앰
      state.dijkstra = [];
      state.dijkstraResult = [];
    },
    DONG_CODE(state, dongCode) {
      state.dongCode = dongCode;
    },
    SEARCH_BY_NAME(state, apts) {
      state.apts = apts;
      // 선택한 기존 아파트 체크 리스트도 삭제함
      state.selected = [];
      // 최적 경로 정보도 새로운 아파트가 들어왔으니 없앰
      state.dijkstra = [];
      state.dijkstraResult = [];
    },
    SEARCH_BY_NAME_DONG(state, apts) {
      state.apts = apts;
      // 선택한 기존 아파트 체크 리스트도 삭제함
      state.selected = [];
      // 최적 경로 정보도 새로운 아파트가 들어왔으니 없앰
      state.dijkstra = [];
      state.dijkstraResult = [];
    },
    APT_CLEAR(state) {
      state.apts = [];
      state.dongCode = "";
      state.deals = [];
      state.selected = [];
      state.startPoint = {};

      state.dijkstra = [];
      state.dijkstraResult = [];
      state.dijkstraDistance = [];
      state.show = false;
      state.clickedApt = null;
    },
    APT_DEAL_INFO(state, deals) {
      state.deals = deals;
    },
    SET_START_POINT(state, startPoint) {
      state.startPoint = startPoint;
    },
    DIJKSTRA_RESULT(state, dijkstraResult) {
      state.dijkstraResult = dijkstraResult;
    },
    DIJKSTRA_DISTANCE(state, dijkstraDistance) {
      state.dijkstraDistance = dijkstraDistance;
    },
    DIJKSTRA_CLEAR(state) {
      if (state.show) {
        state.show = false;
      }
      state.dijkstraResult = [];
    },
    SET_SHOW(state, value) {
      state.show = value;
    },
    SET_NOW_APT(state, apt) {
      state.clickedApt = apt;
    },
  },
  actions: {
    getAptsList(context, dongCode) {
      context.commit("APT_CLEAR");
      context.commit("DONG_CODE", dongCode);
      instanceWithAuth.get(`/apt/info/${dongCode}`).then(({ data }) => {
        // console.log(data);
        context.commit("GET_APTS_LIST", data);
      });
    },
    searchByNameDong(context, aptName) {
      instanceWithAuth
        .get(`/apt/info/${this.state.aptStore.dongCode}/${aptName}`)
        .then(({ data }) => {
          // console.log(data);
          context.commit("SEARCH_BY_NAME_DONG", data);
        });
    },
    searchByName(context, aptName) {
      instanceWithAuth
        .get(`/apt/search/allDeal/${aptName}`)
        .then(({ data }) => {
          // console.log(data);
          context.commit("SEARCH_BY_NAME", data);
        });
    },
    aptClear(context) {
      context.commit("APT_CLEAR");
    },
    getDealInfo(context, apt) {
      // console.log("APT DATA: ", apt);
      // console.log("SETNOWPOINT: ", apt.aptCode, apt.lat, apt.lng);
      instanceWithAuth.get(`/apt/deal/${apt.aptCode}`).then(({ data }) => {
        context.commit("APT_DEAL_INFO", data);
        context.commit("SET_NOW_APT", apt); // 클릭한 아파트 위치 정보 변경
      });
    },
    setNowPoint(context, nowPoint) {
      context.commit("SET_START_POINT", nowPoint);
    },
    // 다익스트라 알고리즘으로 각 포인트별 거리와 시간을 구함 -> 최단 시간으로 추천 경로를 띄어줄 것임
    async getShortCut(context) {
      this.state.aptStore.dijkstra = [];
      let pointers = {};
      pointers = JSON.parse(JSON.stringify(this.state.aptStore.selected));
      pointers.push(this.state.aptStore.startPoint);
      let startIndex = 0;
      let endIndex = 1;
      let size = pointers.length;
      let totalLength = 0; // 전체 경로 가지수
      let totalCheck = 0; // 전체 경로 가지수 카운트 체크
      for (let i = 1; i < size; i++) {
        totalLength += i;
      }
      console.log("size: ", size);

      let distances = [];
      let playInterval = setInterval(() => {
        axios
          .create({
            headers: {
              "Content-type": "application/json",
              appKey: "l7xxcd5931953f104b2c9eaa99c986a7b74b",
            },
          })
          .post(
            "https://apis.openapi.sk.com/tmap/routes?version=1&format=json&callback=result",
            {
              appKey: "l7xxcd5931953f104b2c9eaa99c986a7b74b",
              startX: pointers[startIndex].lng,
              startY: pointers[startIndex].lat,
              endX: pointers[endIndex].lng,
              endY: pointers[endIndex].lat,
              reqCoordType: "WGS84GEO",
              resCoordType: "EPSG3857",
              searchOption: "2",
              trafficInfo: "N",
            }
          )
          .then(({ data }) => {
            var resultData = data.features;
            var tDistance =
              "총 거리 : " +
              (resultData[0].properties.totalDistance / 1000).toFixed(1) +
              "km,";
            var tTime =
              " 총 시간 : " +
              (resultData[0].properties.totalTime / 60).toFixed(0) +
              "분,";
            console.log(tDistance, tTime);
            this.state.aptStore.dijkstra.push({
              from: startIndex,
              to: endIndex,
              distance: resultData[0].properties.totalDistance / 1000,
              time: resultData[0].properties.totalTime / 60,
            });
            if (startIndex == 0) {
              distances.push({
                distance: (
                  resultData[0].properties.totalDistance / 1000
                ).toFixed(1),
                time: (resultData[0].properties.totalTime / 60).toFixed(0),
              });
            }
          })
          .then(() => {
            endIndex++;
            if (endIndex == size) {
              startIndex++;
              endIndex = startIndex + 1;
            }
            if (startIndex == size - 1) {
              clearInterval(playInterval);
            }
            totalCheck++;
            if (totalCheck == totalLength) {
              console.log("dijkstra: ", this.state.aptStore.dijkstra);
              getDijkstra(size);
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }, 500);
      const getDijkstra = (size) => {
        instanceWithAuth
          .post("/apt/route", {
            dijkstraData: JSON.stringify(this.state.aptStore.dijkstra),
            size: size,
          })
          .then(({ data }) => {
            console.log(data);
            console.log(this.state.aptStore.selected[0].apartmentName);
            console.log(distances);
            let dijkstraResult = [];
            for (let d = 0; d < data.length - 1; d++) {
              console.log(this.state.aptStore.selected);
              console.log(this.state.aptStore.selected[d].apartmentName);
              dijkstraResult.push({
                건물명: this.state.aptStore.selected[d].apartmentName,
                주소:
                  this.state.aptStore.selected[d].dong +
                  " " +
                  this.state.aptStore.selected[d].jibun,
                "선택 위치까지 거리": distances[d].distance + " km",
                "선택 위치까지 시간": distances[d].time + "분(자동차 기준)",
              });
            }
            context.commit("DIJKSTRA_RESULT", dijkstraResult);
          });
      };
    }, // shortcut end
    dijkstraClear(context) {
      context.commit("DIJKSTRA_CLEAR");
    },
    setShow(context, value) {
      context.commit("SET_SHOW", value);
    },
  },
};

export default aptStore;
