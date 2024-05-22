const http = {
	//baseurl地址
	baseUrl: "http://39.101.67.214:8080",
	//人工智能接口
	baseUrlAI: "http://47.109.133.25:8860",
	//一般请求方法
	request(config) {
		//config请求配置对象，具体的值参照uniapp文档
		config = beforeRequest(config)
		config.url = this.baseUrl + config.url
		//创建一个promise对象，在里面发送请求
		return new Promise((resolve, reject) => {
			uni.request(config)
				.then(res => {
					let [error, resp] = res
					const response = beforeResponse(resp)
					resolve(response)
				})
				.catch(err => {
					errorHandle()
					reject(err)
				})
		})
	},
	//人工智能请求方法
	requestAI(config) {
		//config请求配置对象，具体的值参照uniapp文档
		config = beforeRequest(config)
		config.url = this.baseUrlAI + config.url

		//创建一个promise对象，在里面发送请求
		return new Promise((resolve, reject) => {
			uni.request(config)
				.then(res => {
					let [error, resp] = res
					const response = beforeResponse(resp)
					resolve(response)
				})
				.catch(err => {
					errorHandle()
					reject(err)
				})
		})
	},
	get(url, data, auth) {
		/*
		url：接口地址
		data：查询参数
		auth：请求是否需要携带token进行认证(true or false)
		*/
		return this.request({
			url: url,
			data: data,
			auth: auth,
			method: "GET"
		})
	},
	post(url, data, auth) {
		/*
		url：接口地址
		data：请求体参数
		auth：请求是否需要携带token进行认证(true or false)
		*/
		return this.request({
			url: url,
			data: data,
			auth: auth,
			method: "POST"
		})
	},
	delete(url, auth) {
		/*
		url：接口地址
		auth：请求是否需要携带token进行认证(true or false)
		*/
		return this.request({
			url: url,
			auth: auth,
			method: "DELETE"
		})
	},
	put(url, data, auth) {
		/*
		url：接口地址
		data：请求体参数
		auth：请求是否需要携带token进行认证(true or false)
		*/
		return this.request({
			url: url,
			data: data,
			auth: auth,
			method: "PUT"
		})
	},
	postAI(url, data, auth) {
		return this.requestAI({
			url: url,
			data: data,
			auth: auth,
			method: "POST",
		})
	}
}

//请求拦截器
const beforeRequest = (config) => {
	//请求之前做的操作
	console.log('请求拦截器：', config)
	config.header = {}
	//判断接口是否需要携带token
	if (config.auth) {
		if (uni.getStorageSync('token')) {
			//在请求头中添加token
			config.header['token'] = uni.getStorageSync('token')
		} else {
			//未登录，无访问权限，重定向至登陆页面
			uni.navigateTo({
				url: '/pages/login/login'
			})
		}
	}
	return config
}

//响应拦截器
const beforeResponse = (response) => {
	//判断请求返回的状态码是否为操作成功情况
	if (response.data.code !== 101 && response.data.code !== 102 && response.data.code !== 103 && response.data
		.code !== 201) {
		//显示对应错误信息
		uni.showToast({
			title: response.data.message,
			icon: 'none',
			duration: 1000
		})
	}
	console.log('响应拦截器：', response)
	return response
}

//异常处理器
const errorHandle = (err) => {
	console.log('网络异常，请求失败！', err)
}

export default http