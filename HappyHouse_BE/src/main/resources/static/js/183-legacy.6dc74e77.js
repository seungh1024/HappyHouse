"use strict";(self["webpackChunkvue_board_bootstrap"]=self["webpackChunkvue_board_bootstrap"]||[]).push([[183],{9183:function(t,n,e){e.r(n),e.d(n,{default:function(){return b}});var a=function(){var t=this,n=t.$createElement,e=t._self._c||n;return e("div",{attrs:{id:"modify"}},[e("b-form",{on:{submit:function(n){return n.preventDefault(),t.onSubmit.apply(null,arguments)},reset:function(n){return n.preventDefault(),t.onReset.apply(null,arguments)}}},[e("b-form-group",{attrs:{id:"input-group-1",label:"제목:","label-for":"input-1"}}),e("b-form-input",{attrs:{id:"input-2",required:"",value:t.qna.subject},model:{value:t.qna.subject,callback:function(n){t.$set(t.qna,"subject",n)},expression:"qna.subject"}}),e("b-form-group",{attrs:{id:"input-group-3",label:"내용:","label-for":"input-3"}},[e("b-form-textarea",{attrs:{id:"textarea",rows:"3","max-rows":"6",value:t.qna.content},model:{value:t.qna.content,callback:function(n){t.$set(t.qna,"content",n)},expression:"qna.content"}})],1),e("b-button",{attrs:{type:"submit",variant:"primary"}},[t._v("수정하기")]),e("b-button",{attrs:{type:"reset",variant:"danger"}},[t._v("내용 초기화")])],1)],1)},r=[],o=e(3019),u=e(4665),i={data:function(){return{qnaData:{}}},created:function(){this.qnaData=this.qna},methods:(0,o.Z)((0,o.Z)({},(0,u.nv)("qnaStore",["qnaModify"])),{},{onSubmit:function(){this.qnaModify(this.qna),this.$router.push({name:"qnaList"})},onReset:function(){var t=this;this.qna.content="",this.show=!1,this.$nextTick((function(){t.show=!0}))}}),computed:(0,o.Z)({},(0,u.rn)("qnaStore",["qna"]))},s=i,l=e(1001),c=(0,l.Z)(s,a,r,!1,null,"5d31d029",null),b=c.exports}}]);
//# sourceMappingURL=183-legacy.6dc74e77.js.map