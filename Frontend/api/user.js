//用户模块接口封装
import http from './request.js'

export default {
	//登录接口
	login(params) {
		return http.post('/user/login', params)
	},
	//获取用户信息
	getUserInfo() {
		return http.post('/user/details', {}, true)
	},
	//尝试注册接口
	register(params) {
		return http.post('/user/attemptRegister', params)
	},
	//输入验证码接口
	code(params) {
		return http.post('/user/CodeRegister', params)
	},
	//刷新token

	//校验token

	//提交旅游城市
	submitXC(params, auth) {
		return http.post('/travel/getRoute', params, auth)
	},
	//获取AI生成的文本和语音文件id数据
	submitAI(params) {
		return http.postAI('/glm/intro/text/all', params)
	}
	//获取AI生成的语音文件
	// getWav(params) {
	// 	return http.postAI('/get_wav/', params)
	// }
}