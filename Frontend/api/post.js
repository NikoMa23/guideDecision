//帖子模块接口封装
import http from './request.js'

export default {
	//分页查询获取多个帖子
	getPostByPage(params, auth){
		return http.post('/post/getPostByPage', params, auth)
	},
	//上传帖子图片
	uploadImages(params, auth) {
		return http.post('/post/uploadedImages', params, auth)
	},
	//发帖
	upLoadPost(params, auth){
		return http.post('/post/uploadPost', params, auth)
	}

}