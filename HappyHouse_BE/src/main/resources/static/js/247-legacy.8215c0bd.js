"use strict";(self["webpackChunkvue_board_bootstrap"]=self["webpackChunkvue_board_bootstrap"]||[]).push([[247],{9247:function(t,e,a){a.r(e),a.d(e,{default:function(){return d}});var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"list"}},[a("div",{attrs:{id:"addBtn"}},[t.admin?a("b-button",{attrs:{type:"submit",variant:"primary"},on:{click:function(e){return e.preventDefault(),t.add.apply(null,arguments)}}},[t._v("글쓰기")]):t._e()],1),a("b-table",{attrs:{striped:"",hover:"",fields:t.fields,items:t.boards},scopedSlots:t._u([{key:"cell(subject)",fn:function(e){return[a("a",{staticClass:"mouseOver",on:{click:function(a){return t.getDetail(e.item.articleno)}}},[t._v(" "+t._s(e.item.subject)+" ")])]}}])}),a("div",{staticClass:"overflow-auto",attrs:{id:"pageBar"}},[a("b-pagination",{attrs:{"total-rows":t.rows,"per-page":t.perPage,"aria-controls":"my-table"},model:{value:t.noticeCurrentPage,callback:function(e){t.noticeCurrentPage=e},expression:"noticeCurrentPage"}})],1)],1)},r=[],n=a(3019),o=a(4665),s={data:function(){return{noticeCurrentPage:1,perPage:10,fields:[{key:"articleno",label:"번호"},{key:"subject",label:"제목"},{key:"name",label:"작성자"},{key:"regtime",label:"작성일"}]}},methods:(0,n.Z)((0,n.Z)({},(0,o.nv)("noticeStore",["getNoticeList","getTotalNotice","setCurrentPage"])),{},{add:function(){this.$router.push({name:"noticeAdd"})},getDetail:function(t){this.$router.push({name:"noticeDetail",params:{articleno:t}})}}),created:function(){this.$store.state.pageClear&&(this.$store.dispatch("pageClear",!1),this.setCurrentPage(1)),this.noticeCurrentPage=this.noticePage,console.log("dispatch before create list"),this.getNoticeList(this.qnaPage),0!=this.$store.state.user&&(console.log("dispatch before create total"),this.getTotalNotice())},computed:(0,n.Z)((0,n.Z)({},(0,o.rn)("noticeStore",["noticeList","noticeTotal","noticePage"])),{},{boards:function(){return this.noticeList},rows:function(){return this.noticeTotal},admin:function(){return this.$store.state.admin}}),watch:{noticeCurrentPage:function(){console.log("dispatch before watch"),this.setCurrentPage(this.noticeCurrentPage),this.getNoticeList(this.noticeCurrentPage)}}},c=s,l=a(1001),u=(0,l.Z)(c,i,r,!1,null,"a98e504e",null),d=u.exports}}]);
//# sourceMappingURL=247-legacy.8215c0bd.js.map