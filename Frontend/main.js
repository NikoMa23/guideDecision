import App from './App'
import store from './store'

// #ifndef VUE3
import Vue from 'vue'
// import './uni.promisify.adaptor'

//导入封装的请求
import api from './api/index.js'

//将请求对象设置为全局属性
Vue.prototype.$api = api

Vue.prototype.getImgSrc = function(url){
	// console.log("http://8.130.121.208:8080" + url)
	// return "http://e.hiphotos.baidu.com/image/pic/item" + url;
	return "http://39.101.67.214:8080" + url;
}

Vue.prototype.$store = store
Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
	store,
  ...App
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif