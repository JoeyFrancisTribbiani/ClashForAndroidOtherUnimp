!function(e){var t={};function n(r){if(t[r])return t[r].exports;var o=t[r]={i:r,l:!1,exports:{}};return e[r].call(o.exports,o,o.exports,n),o.l=!0,o.exports}n.m=e,n.c=t,n.d=function(e,t,r){n.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:r})},n.r=function(e){"undefined"!=typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},n.t=function(e,t){if(1&t&&(e=n(e)),8&t)return e;if(4&t&&"object"==typeof e&&e&&e.__esModule)return e;var r=Object.create(null);if(n.r(r),Object.defineProperty(r,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var o in e)n.d(r,o,function(t){return e[t]}.bind(null,o));return r},n.n=function(e){var t=e&&e.__esModule?function(){return e.default}:function(){return e};return n.d(t,"a",t),t},n.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},n.p="",n(n.s=111)}({0:function(e,t,n){"use strict";function r(e,t,n,r,o,i,a,s,u,c){var l,p="function"==typeof e?e.options:e;if(u){p.components||(p.components={});var f=Object.prototype.hasOwnProperty;for(var d in u)f.call(u,d)&&!f.call(p.components,d)&&(p.components[d]=u[d])}if(c&&((c.beforeCreate||(c.beforeCreate=[])).unshift((function(){this[c.__module]=this})),(p.mixins||(p.mixins=[])).push(c)),t&&(p.render=t,p.staticRenderFns=n,p._compiled=!0),r&&(p.functional=!0),i&&(p._scopeId="data-v-"+i),a?(l=function(e){(e=e||this.$vnode&&this.$vnode.ssrContext||this.parent&&this.parent.$vnode&&this.parent.$vnode.ssrContext)||"undefined"==typeof __VUE_SSR_CONTEXT__||(e=__VUE_SSR_CONTEXT__),o&&o.call(this,e),e&&e._registeredComponents&&e._registeredComponents.add(a)},p._ssrRegister=l):o&&(l=s?function(){o.call(this,this.$root.$options.shadowRoot)}:o),l)if(p.functional){p._injectStyles=l;var _=p.render;p.render=function(e,t){return l.call(t),_(e,t)}}else{var v=p.beforeCreate;p.beforeCreate=v?[].concat(v,l):[l]}return{exports:e,options:p}}n.d(t,"a",(function(){return r}))},1:function(e,t){e.exports={"@VERSION":2}},106:function(e,t,n){"use strict";n.r(t);var r=n(83),o=n.n(r);for(var i in r)"default"!==i&&function(e){n.d(t,e,(function(){return r[e]}))}(i);t.default=o.a},111:function(e,t,n){"use strict";n.r(t);n(51);var r=n(75);"undefined"==typeof Promise||Promise.prototype.finally||(Promise.prototype.finally=function(e){var t=this.constructor;return this.then((function(n){return t.resolve(e()).then((function(){return n}))}),(function(n){return t.resolve(e()).then((function(){throw n}))}))}),ArrayBuffer=uni.base64ToArrayBuffer("").constructor,r.default.mpType="page",r.default.route="pages/uni-agree/uni-agree",r.default.el="#root",new Vue(r.default)},112:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;t.default={h5:{url:"https://uni-starter.dcloud.net.cn"},mp:{weixin:{id:"gh_33446d7f7a26"}},router:{visitor:["/",{pattern:/^\/pages\/list.*/},{pattern:/^\/pages\/ucenter\/login-page.*/},"/pages/common/webview/webview","/pages/grid/grid","/pages/ucenter/ucenter","/pages/ucenter/about/about","/pages/ucenter/settings/settings"],login:["weixin","univerify","username","smsCode","apple"]},about:{appName:"uni-starter",logo:"/static/logo.png",company:"\u5317\u4eacxx\u7f51\u7edc\u6280\u672f\u6709\u9650\u516c\u53f8",slogan:"\u4e91\u7aef\u4e00\u4f53\u5e94\u7528\u5feb\u901f\u5f00\u53d1\u6a21\u7248",agreements:[{title:"\u7528\u6237\u670d\u52a1\u534f\u8bae",url:"\u8bf7\u586b\u5199\u7528\u6237\u670d\u52a1\u534f\u8bae\u94fe\u63a5"},{title:"\u9690\u79c1\u653f\u7b56",url:"\u8bf7\u586b\u5199\u9690\u79c1\u653f\u7b56\u94fe\u63a5"}],download:"",version:"1.0.0"},download:{ios:"https://itunes.apple.com/cn/app/hello-uni-app/id1417078253?mt=8",android:"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-97fca9f2-41f6-449f-a35e-3f135d4c3875/6d754387-a6c3-48ed-8ad2-e8f39b40fc01.apk"},marketId:{ios:"id1417078253",android:"123456"},i18n:{enable:!1}}},51:function(e,t,n){Vue.prototype.__$appStyle__={},Vue.prototype.__merge_style&&Vue.prototype.__merge_style(n(52).default,Vue.prototype.__$appStyle__)},52:function(e,t,n){"use strict";n.r(t);var r=n(1),o=n.n(r);for(var i in r)"default"!==i&&function(e){n.d(t,e,(function(){return r[e]}))}(i);t.default=o.a},75:function(e,t,n){"use strict";var r=n(92),o=n(81),i=n(0);var a=Object(i.a)(o.default,r.b,r.c,!1,null,null,"6d3a98a8",!1,r.a,void 0);(function(e){this.options.style||(this.options.style={}),Vue.prototype.__merge_style&&Vue.prototype.__$appStyle__&&Vue.prototype.__merge_style(Vue.prototype.__$appStyle__,this.options.style),Vue.prototype.__merge_style?Vue.prototype.__merge_style(n(106).default,this.options.style):Object.assign(this.options.style,n(106).default)}).call(a),t.default=a.exports},81:function(e,t,n){"use strict";var r=n(82),o=n.n(r);t.default=o.a},82:function(e,t,n){"use strict";var r;Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var o=((r=n(112))&&r.__esModule?r:{default:r}).default.about,i={data:function(){return{}},onLoad:function(){this._canBack=!1},onBackPress:function(){return!this._canBack},methods:{openprotocol:function(e){uni.navigateTo({url:"/pages/common/webview/webview?url="+o.agreements[0].url})},openPrivacyPolicy:function(e){uni.navigateTo({url:"/pages/common/webview/webview?url="+o.agreements[1].url})},agree:function(e){uni.setStorageSync("userprotocol",1);this._canBack=!0,setTimeout((function(){uni.navigateBack({animationDuration:0})}),100)},disagree:function(){plus.runtime.quit()}}};t.default=i},83:function(e,t){e.exports={".page":{"":{paddingTop:["80",0,0,0],paddingRight:["30",0,0,0],paddingBottom:["80",0,0,0],paddingLeft:["30",0,0,0]}},".title":{"":{fontSize:["18",0,0,1],lineHeight:["30",0,0,1],marginBottom:["20",0,0,1]}},".flex-r":{"":{flexDirection:["row",0,0,2],flexWrap:["wrap",0,0,2]}},".text-item":{"":{marginBottom:["5",0,0,3]}},".tl":{"":{fontSize:["14",0,0,4],lineHeight:["20",0,0,4]}},".hl":{"":{color:["#007AFF",0,0,5]}},".service":{"":{fontSize:["16",0,0,6],lineHeight:["20",0,0,6],marginTop:["20",0,0,6]}},".confirm":{"":{marginTop:["50",0,0,7],flexDirection:["row",0,0,7]}},".btn-privacy":{"":{flex:[1,0,0,8]}},".btn-disagree":{"":{marginLeft:["20",0,0,9]}},"@VERSION":2}},92:function(e,t,n){"use strict";n.d(t,"b",(function(){return r})),n.d(t,"c",(function(){return o})),n.d(t,"a",(function(){}));var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("scroll-view",{staticStyle:{flexDirection:"column"},attrs:{scrollY:!0,showScrollbar:!0,enableBackToTop:!0,bubble:"true"}},[n("view",{staticClass:["page"]},[n("view",[n("u-text",{staticClass:["title"],appendAsTree:!0,attrs:{append:"tree"}},[e._v("\u4e2a\u4eba\u4fe1\u606f\u4fdd\u62a4\u6307\u5f15")])]),n("view",{staticClass:["text-item"]},[n("u-text",{staticClass:["tl"],appendAsTree:!0,attrs:{append:"tree"}},[e._v("1.\u5728\u6d4f\u89c8\u4f7f\u7528\u65f6\uff0c\u6211\u4eec\u4f1a\u6536\u96c6\u3001\u4f7f\u7528\u8bbe\u5907\u6807\u8bc6\u4fe1\u606f\u7528\u4e8e\u63a8\u8350\u3002")])]),n("view",{staticClass:["text-item"]},[n("u-text",{staticClass:["tl"],appendAsTree:!0,attrs:{append:"tree"}},[e._v("2.\u6211\u4eec\u53ef\u80fd\u4f1a\u7533\u8bf7\u4f4d\u7f6e\u6743\u9650\uff0c\u7528\u4e8e\u6f14\u793a uni-app \u7684\u5730\u56fe\u3001\u5b9a\u4f4d\u80fd\u529b\u3002")])]),n("view",{staticClass:["text-item"]},[n("u-text",{staticClass:["tl"],appendAsTree:!0,attrs:{append:"tree"}},[e._v("3.\u4f60\u53ef\u4ee5\u67e5\u770b\u5b8c\u6574\u7248")])]),n("view",{staticClass:["text-item","flex-r"]},[n("u-text",{staticClass:["tl","hl"],appendAsTree:!0,attrs:{append:"tree"},on:{click:e.openprotocol}},[e._v("\u300a\u7528\u6237\u534f\u8bae\u300b")]),n("u-text",{staticClass:["tl"],appendAsTree:!0,attrs:{append:"tree"}},[e._v("\u548c")]),n("u-text",{staticClass:["tl","hl"],appendAsTree:!0,attrs:{append:"tree"},on:{click:e.openPrivacyPolicy}},[e._v("\u300a\u9690\u79c1\u653f\u7b56\u300b")])]),n("view",{staticClass:["text-item"]},[n("u-text",{staticClass:["tl"],appendAsTree:!0,attrs:{append:"tree"}},[e._v("\u4ee5\u4fbf\u4e86\u89e3\u6211\u4eec\u6536\u96c6\u3001\u4f7f\u7528\u3001\u5171\u4eab\u3001\u5b58\u50a8\u4fe1\u606f\u7684\u60c5\u51b5\uff0c\u4ee5\u53ca\u5bf9\u4fe1\u606f\u7684\u4fdd\u62a4\u63aa\u65bd\u3002")])]),n("view",{staticClass:["text-item"]},[n("u-text",{staticClass:["service"],appendAsTree:!0,attrs:{append:"tree"}},[e._v("\u5982\u679c\u4f60\u540c\u610f\u8bf7\u70b9\u51fb\u4e0b\u9762\u7684\u6309\u94ae\u4ee5\u63a5\u53d7\u6211\u4eec\u7684\u670d\u52a1")])]),n("view",{staticClass:["text-item","confirm"]},[n("button",{staticClass:["btn-privacy"],attrs:{type:"primary"},on:{click:e.agree}},[e._v("\u540c\u610f")]),n("button",{staticClass:["btn-privacy","btn-disagree"],on:{click:e.disagree}},[e._v("\u6682\u4e0d\u4f7f\u7528")])],1),n("view",{staticClass:["exit-area"]})])])},o=[]}});