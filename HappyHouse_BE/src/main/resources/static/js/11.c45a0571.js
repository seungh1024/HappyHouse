"use strict";(self["webpackChunkvue_board_bootstrap"]=self["webpackChunkvue_board_bootstrap"]||[]).push([[11],{9011:function(e,t,a){a.r(t),a.d(t,{default:function(){return c}});var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"list"}},[a("b-table",{attrs:{striped:"",hover:"",fields:e.fields,items:e.boards},scopedSlots:e._u([{key:"cell(subject)",fn:function(t){return[a("a",{staticClass:"mouseOver",on:{click:function(a){return e.getDetail(t.item.articleno,t.item.userid)}}},[e._v(" "+e._s(t.item.subject)+" ")])]}}])}),a("div",{staticClass:"overflow-auto",attrs:{id:"pageBar"}},[a("b-pagination",{attrs:{"total-rows":e.rows,"per-page":e.perPage,"aria-controls":"my-table"},model:{value:e.currentPage,callback:function(t){e.currentPage=t},expression:"currentPage"}})],1)],1)},s=[],n=a(4665),i={data(){return{currentPage:1,perPage:10,fields:[{key:"articleno",label:"번호"},{key:"subject",label:"제목"},{key:"name",label:"작성자"},{key:"regtime",label:"작성일"}]}},methods:{...(0,n.nv)("qnaStore",["getQnaList","getTotalQna","setCurrentPage"]),getDetail(e,t){this.$router.push({name:"qnaDetail",params:{articleno:e,userid:t}})}},created(){this.$store.state.pageClear&&(this.$store.dispatch("pageClear",!1),this.setCurrentPage(1)),console.log(this.qnaPage),this.currentPage=this.qnaPage,console.log("currentPage create: ",this.currentPage),this.getQnaList(this.qnaPage),0!=this.$store.state.user&&this.getTotalQna()},computed:{...(0,n.rn)("qnaStore",["qnaList","total","qnaPage"]),boards(){return this.qnaList},rows(){return this.total}},watch:{currentPage(){this.setCurrentPage(this.currentPage),this.getQnaList(this.currentPage),console.log("currentPage watch: ",this.qnaPage)}}},o=i,l=a(1001),u=(0,l.Z)(o,r,s,!1,null,"3b903bfa",null),c=u.exports}}]);
//# sourceMappingURL=11.c45a0571.js.map