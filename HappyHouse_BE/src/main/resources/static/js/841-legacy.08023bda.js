"use strict";(self["webpackChunkvue_board_bootstrap"]=self["webpackChunkvue_board_bootstrap"]||[]).push([[841],{841:function(t,e,r){r.r(e),r.d(e,{default:function(){return f}});var n=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{attrs:{id:"create"}},[t.show?r("b-form",{on:{submit:function(e){return e.preventDefault(),t.onSubmit.apply(null,arguments)},reset:t.onReset}},[r("b-form-group",{attrs:{id:"input-group",label:"제목:","label-for":"input-1"}}),r("b-form-input",{attrs:{id:"input-2",placeholder:"Enter subject",required:""},model:{value:t.form.subject,callback:function(e){t.$set(t.form,"subject",e)},expression:"form.subject"}}),r("b-form-group",{attrs:{id:"input-group",label:"내용:","label-for":"input-3"}},[r("b-form-textarea",{attrs:{id:"textarea",placeholder:"Enter something...",rows:"3","max-rows":"6"},model:{value:t.form.content,callback:function(e){t.$set(t.form,"content",e)},expression:"form.content"}})],1),r("b-button",{attrs:{type:"submit",variant:"primary"}},[t._v("게시글 생성")]),r("b-button",{attrs:{type:"reset",variant:"danger"}},[t._v("초기화")])],1):t._e()],1)},o=[],s=r(3019),a=r(4665),u={data:function(){return{form:{subject:"",content:""},show:!0}},methods:(0,s.Z)((0,s.Z)({},(0,a.nv)("qnaStore",["qnaCreate","setCurrentPage"])),{},{onSubmit:function(){console.log(this.form),this.qnaCreate(this.form),this.setCurrentPage(1),this.$router.push({name:"qnaList"})},onReset:function(t){var e=this;t.preventDefault(),this.form.subject="",this.form.content="",this.show=!1,this.$nextTick((function(){e.show=!0}))}})},i=u,l=r(1001),c=(0,l.Z)(i,n,o,!1,null,"2b355e03",null),f=c.exports}}]);
//# sourceMappingURL=841-legacy.08023bda.js.map