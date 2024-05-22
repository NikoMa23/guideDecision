import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex); //vue的插件机制

//Vuex.Store 构造器选项
const store = new Vuex.Store({
	//全局数据
	state: {
		//当前用户token
		token: "",
		//当前用户的个人信息
		userInfo: {},
		//用户是否登录
		isAuth: false,
		//用户点击的帖子信息
		postInfo: {}
	},
	//全局的计算属性
	getters: {

	},
	//修改数据的方法
	mutations: {
		//保存登陆后的用户状态
		saveLoginStatus(state, info) {
			state.isAuth = true
			state.token = info.token
			//存储到uniapp提供的持久化存储仓库中
			uni.setStorageSync("token", info.token)
			uni.setStorage("isAuth", true)
		},
		//保存用户信息
		saveUserinfo(state, user) {
			state.userInfo = user
			uni.setStorage({
				key: "userInfo",
				data: user
			})
		},
		//保存用户点击的帖子的信息
		savePostInfo(state, post) {
			state.postInfo = post
			uni.setStorage({
				key: "postInfo",
				data: post
			})
		}
	},
	//全局操作的方法
	actions: {

	}
})
export default store

// //状态集中管理
// //数据实现响应式
// //使用场景: 权限管理 路径 对用户进行权限限制

// import { reactive } from "vue"

// //ref-->字符串,数字 reactive-->对象中存储
// const store={
//   //定义状态
//   state:reactive({
//     nickname: "未登录",
//     headimgurl: "../../static/login/WeChat.png"
//   }),
//   toLoginByWx(){
//    console.log("进入微信登录");
//    	 // 获取微信服务商权限
// 	 // 在App平台，可用的服务商，是打包环境中配置的服务商，与手机端是否安装了该服务商的App没有关系。
//    	 uni.getProvider({
//    	 	service: 'oauth',
//    	 	success: function (res) {
//    	 		console.log(res.provider)
//    	 		if (~res.provider.indexOf('weixin')) {
//    				//如果支持微信登录 进行登录
//    	 			uni.login({
//    	 				"provider": "weixin",
//    	 				"onlyAuthorize": true, // 微信登录仅请求授权认证
//    	 				success: function(event){
//    	 					//客户端成功获取授权临时票据（code）,向业务服务器发起登录请求。
//    	 					uni.request({
//    	 					    url: 'http://8.130.121.208:8080', 
//    	 					    data: {
//    	 					        code: event.code
//    	 					    },
//    	 					    success: (res) => {
//    	 					        //获得用户信息保存并提示完成登录
//    								store.state.nickname=res.data.nickname;
// 								store.state.headimgurl=res.data.headimgurl;
//    								uni.setStorageSync("userInfo",res.data);
//    								uni.showToast({
//    								    title: "微信登录成功" ,
//    								    icon: 'none'
//    								});
//    	 					    }
//    	 					});
//    	 				},
//    	 				fail: function (err) {
//    	 			        // 登录授权失败
//    	 			        // err.code是错误码
//    						console.log(err);
//    						uni.showToast({
//    						    title: err.errMsg ,
//    						    icon: 'none'
//    						});
//    	 			    }
//    	 			})
//    	 		}
//    	 	},
//    		//请求失败
//    		fail: (err) => {
//    			console.log(err);
//    		    uni.showToast({
//    		        title: "手机不支持微信登录" ,
//    		        icon: 'none'
//    		    });
//    		},
//    	 });
//   },
//   logoutByWx(){
//   	 //1.清除本地缓存
// 	 uni.removeStorageSync('userInfo');
// 	 //2.state状态更替为默认
//      store.state.headimgurl="../../static/login/WeChat.png";
// 	 store.state.nickname="未登录";
//   }
// };
// //在App组件通过provide提供
// export default store