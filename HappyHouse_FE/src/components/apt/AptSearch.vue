<template>
  <div data-v-ec63c95a>
    <section class="setter">
      <article class="set_local">
        <select
          v-on:change="sidoClicked($event)"
          class="local_box"
          id="local_si"
        >
          <option value="">시 선택</option>
          <option
            :value="sido.sidoCode"
            v-for="(sido, index) in sidos"
            :key="index"
          >
            {{ sido.sidoName }}
          </option>
        </select>
        <select
          v-on:change="gugunClicked($event)"
          class="local_box"
          id="local_gu"
          name="local_gu"
        >
          <option value="">구 선택</option>
          <option
            :value="gugun.gugunCode"
            v-for="(gugun, index) in guguns"
            :key="index"
          >
            {{ gugun.gugunName }}
          </option>
        </select>
        <select @change="dongClicked($event)" class="local_box" id="local_dong">
          <option value="">동 선택</option>
          <option
            :value="dong.dongCode"
            v-for="(dong, index) in dongs"
            :key="index"
          >
            {{ dong.dongName }}
          </option>
        </select>

        <div class="d-flex justify-content-center">
          <b-input-group prepend="건물명" class="w-75 mt-3">
            <b-form-input
              v-model="keyword"
              @keyup.enter.prevent="searchBtn"
            ></b-form-input>
            <b-input-group-append>
              <b-button variant="success" @click.prevent="searchBtn"
                >검색</b-button
              >
            </b-input-group-append>
          </b-input-group>
        </div>
        <b-button variant="primary" @click.prevent="dijkstraBtn()" class="m-2"
          >최적 경로 탐색</b-button
        >
        <b-button
          variant="danger"
          @click.prevent="dijkstraClearBtn"
          class="mg-1"
          >경로 탐색 초기화</b-button
        >
      </article>
    </section>

    <!-- 다익스트라 결과값 모달창 -->
    <b-modal id="modal-scoped" size="lg">
      <template #modal-header>
        <h5>[추천 방문 순서]</h5>
      </template>

      <template>
        <div v-if="!show">
          <p>경로 탐색 중</p>
          <b-overlay :show="true" rounded="sm">
            <b-card :aria-hidden="show ? 'true' : null" class="h-100"> </b-card>
          </b-overlay>
        </div>
        <div v-if="show">
          <b-table stacked :items="dijkstraResult">ㅇ</b-table>
        </div>
      </template>

      <template #modal-footer="{ ok }">
        <b-button size="sm" variant="success" @click="ok()"> 닫기 </b-button>
      </template>
    </b-modal>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
export default {
  data() {
    return {
      keyword: undefined,
      map: null,
    };
  },
  created() {
    this.getSido();
  },

  methods: {
    ...mapActions("codeStore", ["getSido", "getGugun", "getDong"]),
    ...mapActions("aptStore", [
      "getAptsList",
      "searchByName",
      "searchByNameDong",
      "getShortCut",
      "dijkstraClear",
      "setShow",
    ]),
    sidoClicked(event) {
      const value = event.target.value.substr(0, 2);
      // this.$store.dispatch("getGugun", value);
      this.getGugun(value);
    },
    gugunClicked(event) {
      const value = event.target.value;
      this.getDong(value);
      // this.$store.dispatch("getDong", value);
    },
    dongClicked(event) {
      const dongCode = event.target.value;
      if (!dongCode) {
        this.$store.state.aptStore.dongCode = "";
      }
      this.getAptsList(dongCode);
    },
    searchBtn() {
      console.log(this.keyword);
      if (!this.keyword) {
        if (!this.$store.state.aptStore.dongCode) {
          alert("카테고리 또는 키워드를 동까지 선택해 주세요");
          return;
        }
        this.getAptsList(this.$store.state.aptStore.dongCode);
        return;
      }
      // console.log(this.dongCode);
      if (this.keyword.length < 2) {
        alert("키워드는 2자리 이상 입력해 주세요");
        return;
      }
      if (!this.dongCode) {
        // 동 코드가 없다면
        this.searchByName(this.keyword);
      } else {
        this.searchByNameDong(this.keyword);
      }
    },
    dijkstraBtn() {
      if (!this.startPoint.aptName) {
        // 출발지 선택을 하지 않은 경우 예외 처리
        alert("출발지를 지도에 클릭해 주세요");
        return;
      }
      if (this.selected.length == 0) {
        // 탐색할 곳 선택하지 않은 경우 예외 처리
        alert("탐색할 곳을 선택해 주세요");
        return;
      }

      this.$bvModal.show("modal-scoped");
      this.getShortCut();
    },
    dijkstraClearBtn() {
      this.dijkstraClear();
      this.selected.splice(0, 4); //체크 초기화
      console.log(this.selected);
    },
  },
  computed: {
    ...mapState("codeStore", ["sidos", "guguns", "dongs"]),
    ...mapState("aptStore", [
      "dongCode",
      "selected",
      "startPoint",
      "dijkstraResult",
      "dijkstraDistance",
      "show",
    ]),
  },
  watch: {
    dijkstraResult() {
      if (this.dijkstraResult.length > 0) {
        this.setShow(true);
      }
    },
  },
};
</script>

<style scoped>
.set_local {
  border: 1px solid black;
  padding: 10px 0px;
  text-align: center;
  /* min-width: 500px; */
  margin: auto;
}

.local_box {
  margin: 10px 15px;
  display: inline-block;
  max-width: 350px;
  width: 25%;
  height: 50px;
  text-align: center;
  border: 1px solid black;
  vertical-align: middle;
  border-radius: 10px;
  font-size: 120%;
  font-weight: bold;
  border: 0;
}

#local_si {
  border: 2px solid rgb(136, 136, 136);
  border-radius: 30px;
}

#local_gu {
  border: 2px solid rgb(136, 136, 136);
  border-radius: 30px;
}

#local_dong {
  border: 2px solid rgb(136, 136, 136);
  border-radius: 30px;
}

.information_text {
  text-align: center;
  font-size: 50px;
  /* min-width: 800px; */
}

.information_table_box {
  /* min-width: 1000px; */
}

.information_table {
  margin: auto;
  width: 90%;
  /* min-width: 800px; */
  /* min-width: 500px; */
}

.page_switch_box {
  text-align: center;
}

.information_table tr td {
  padding: 0 10px;
}

h4 {
  margin: 30px 0px;
  font-weight: bold;
  font-size: 40px;
}

.page-link {
  border: none;
  font-weight: bold;
}

.prev_btn,
.next_btn {
  font-weight: bold;
}
</style>
