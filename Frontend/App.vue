<script>
	import {
		mapMutations
	} from 'vuex'
	export default {
		methods: {
			...mapMutations(['saveLoginStatus', 'saveUserinfo'])
		},
		onLaunch: function() {
			console.log('App Launch')
			// tabbar中间按钮点击事件
			uni.onTabBarMidButtonTap(function(e) {
				uni.navigateTo({
					url: "/pages/AI/AI",
					fail(err) {
						console.log(err)
					}
				})
			})
		},
		onShow: function() {
			console.log('App Show')
			//读取 uniapp 的 storage 中存储的用户信息，保存到 vuex 中，用于解决页面刷新导致 vuex 中数据丢失的问题
			const uinfo = uni.getStorageSync('userInfo') || {}
			const loginStatus = {
				token: uni.getStorageSync('token')
			}
			//保存登录状态
			this.saveLoginStatus(loginStatus)
			//保存用户信息
			this.saveUserinfo(uinfo)
			// console.log(loginStatus)
			// console.log(uinfo)
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	/*每个页面公共css */
</style>