<template>
  <div class="float-left">
    <section class="information">
      <article class="information_text">
        <h4 id="page_title"></h4>
      </article>

      <b-table
        sticky-header="660px"
        class="mb-3"
        striped
        hover
        :fields="fields"
        :items="apts"
        fixed
      >
        <template #cell(aptCode)="row">
          <input sm="10" size="lg" type="checkbox" aria-label="Checkbox for
          following text input" @change="checkBoxClicked(row.item, $event)"
          class="checkBox ml-4 mr-n2" id = />
        </template>
        <template #cell(apartmentName)="row">
          <a id="mouseOver" size="sm" @click="getDeal(row.item)" class="mr-2">
            {{ row.item.apartmentName }}
          </a>
          <!-- <a
            v-b-toggle.my-collapse
            class="mouseOver"
            @click="getDetail(data.item.aptCode)"
          >
            {{ data.item.aptName }}
          </a> -->
        </template>
        <template #cell(jibun)="row">
          <div colspan="2">
            {{ row.item.dong + " " + row.item.jibun }}
          </div>
        </template>
        <!-- 토글 -> 아파트 상세 정보 출력  -->
        <template #row-details="row">
          <b-card v-for="(deal, index) in deals" :key="index">
            <b-row class="mb-2">
              <b-col sm="3" class="text-sm-right"><b>건물명:</b></b-col>
              <b-col>{{ row.item.apartmentName }}</b-col>
            </b-row>

            <b-row class="mb-2">
              <b-col sm="3" class="text-sm-right"><b>거래 금액:</b></b-col>
              <b-col>{{ deal.dealAmount }}</b-col>
            </b-row>
            <b-row class="mb-2">
              <b-col sm="3" class="text-sm-right"><b>거래 일자:</b></b-col>
              <b-col>{{
                deal.dealYear + "." + deal.dealMonth + "." + deal.dealDay
              }}</b-col>
            </b-row>
            <b-row class="mb-2">
              <b-col sm="3" class="text-sm-right"><b>층/면적:</b></b-col>
              <b-col>{{ deal.floor + "/" + deal.dealAmount }}</b-col>
            </b-row>
          </b-card>
          <b-button
            class="container text-center"
            id="closeBtn"
            pill
            variant="info"
            @click="row.toggleDetails"
            >닫기</b-button
          >
        </template>
      </b-table>
    </section>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
export default {
  data() {
    return {
      fields: [
        {
          key: "aptCode",
          label: "경로탐색",
        },
        {
          key: "apartmentName",
          label: "건물명",
        },
        {
          key: "jibun",
          label: "주소",
          formatter: "fullAddress",
        },
        {
          // A regular column with custom formatter
          key: "buildYear",
          label: "건축년도",
        },
      ],
    };
  },
  created() {
    this.selected.splice(0, 4);
  },
  methods: {
    ...mapActions("aptStore", ["aptClear", "getDealInfo", "dijkstraClear"]),
    getDeal(item) {
      // collapse 이벤트를 받아와서 처리 -> getDealInfo 요청 보내기 위해서 한번에 처리함
      if (item._showDetails) {
        this.$set(item, "_showDetails", false);
      } else {
        this.$nextTick(() => {
          this.$set(item, "_showDetails", true);
        });
      }
      console.log("apt item: ", item.lat);
      this.getDealInfo(item);
    },
    fullAddress(value) {
      // console.log(value);
      // console.log(this.address);
      return this.address + value;
    },
    checkBoxClicked(value, event) {
      this.dijkstraClear();
      // 선택된 장소가 4개이상이고 체크박스 클릭 이벤트가 true로 바뀌었다면 -> 체크되게 하면 안됨
      if (this.selected.length >= 4 && event.target.checked) {
        alert("경로 탐색 서비스는 4개 까지 선택할 수 있습니다");
        // console.log(this.selected);
        // console.log(event);
        event.target.checked = false;
        return;
      }
      for (let i = 0; i < this.selected.length; i++) {
        if (this.selected[i] == value) {
          //중복 값이 있다면 제거 -> 체크 버튼 해제가 클릭된 경우
          this.selected.splice(i, 1);
          return; // 제거하면 리턴
        }
      }
      console.log(value);
      if (event.target.checked) {
        // 체크로 바뀐 경우에만 넣어줌
        this.selected.push(value);
      }
    },
  },
  computed: {
    ...mapState("aptStore", ["apts", "deals", "selected"]),
  },
  watch: {
    // 체크박스로 선택된 데이터들이 초기화 되었을 때 버튼 클릭 해제를 함
    selected() {
      if (this.selected.length == 0) {
        const checkBox = document.getElementsByClassName("checkBox");
        // console.log(checkBox[0].checked);
        const cbSize = checkBox.length;
        for (let i = 0; i < cbSize; i++) {
          checkBox[i].checked = false;
        }
      }
    },
  },
};
</script>

<style scoped>
#mouseOver {
  cursor: pointer;
}
table {
  height: "400px";
}
#closeBtn {
  position: relative;
  text-align: center;
}
.checkBox {
  text-align: center;
}
input[type="checkbox"] {
  transform: scale(1.8);
}
</style>
