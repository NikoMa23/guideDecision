<template>
	<view class="container">
		<image class="bg-image"></image>
		<image class="back" src="/static/login/back.png" @tap="goBack()"></image>
		<image class="characters-image" src="/static/login/background1.png"></image>

		<view class="register-box">
			<image class="register-image" src='/static/login/register_block.png'></image>
			<text class="register-text">注册</text>
		</view>

		<view class="login-box">
			<image class="login-image" src="/static/login/login_block.png"></image>
			<text class="login-text">登录</text>
			<button class="register-button" plain="true" @tap="goregister()"></button>
			<!-- 登陆的表单 -->
			<uni-forms ref="loginRef" :rules="rules" :model="loginForm">
				<uni-forms-item class="input-field-username" name="username">
					<uni-easyinput type="text" primaryColor="#f86140cc" prefixIcon="person"
						placeholderStyle="font-size: 4vw;font-family: 阿里妈妈刀隶体;color: #73848c;"
						v-model="loginForm.username" placeholder="请输入用户名" />
				</uni-forms-item>
				<uni-forms-item class="input-field-psw" name="password">
					<uni-easyinput type="password" primaryColor="#f86140cc" prefixIcon="locked"
						placeholderStyle="font-size: 4vw;font-family: 阿里妈妈刀隶体;color: #73848c;"
						v-model="loginForm.password" placeholder="请输入密码" />
				</uni-forms-item>
			</uni-forms>

			<button class="login-button" @click="clickLogin()">登录</button>
			<text class="forget-psw">忘记密码？</text>
		</view>

		<text class="third-party-login-text">第三方登录</text>
		
		<view class="third-party-login">
			<view class="phone-block">
				<image class="phone-icon" src="/static/login/phone.png"></image>
			</view>
			<view class="QQ-block">
				<image class="QQ-icon" src="/static/login/QQ.png"></image>
			</view>
			<view class="WeChat-block">
				<image class="WeChat-icon" @click="WxLogin()" src="/static/login/WeChat.png"></image>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		mapMutations
	} from 'vuex'
	
	export default {
		data() {
			return {
				//登陆输入的表单
				loginForm: {
					//用户输入的内容
					username: "",
					password: ""
				},
				//登陆表单的验证规则
				rules: {
					username: {
						rules: [{
							required: true,
							errorMessage: '用户名不能为空'
						}]
					},
					password: {
						rules: [{
								pattern: '^[0-9a-zA-Z]{2,16}$',
								errorMessage: "密码应为2-16位"
							},
							{
								required: true,
								errorMessage: '密码不能为空'
							}
						]
					}
				}
			}
		},
		methods: {
			...mapMutations(['saveLoginStatus', 'saveUserinfo']),
			//关闭当前页面
			goBack() {
				uni.switchTab({
					url: "/pages/mainpage/mainpage"
				})
			},
			goregister() {
				uni.navigateTo({
					url: "/pages/register/register"
				});
			},
			//点击登录对登录表单输入的数据进行校验
			clickLogin() {
				//校验请求参数，发送请求
				this.$refs.loginRef.validate().then(res => {
					//调用登陆的方法
					this.submitLogin()
				}).catch(err => {
					console.log('表单错误信息：', err)
				})
			},
			async submitLogin() {
				//发送登陆的接口请求
				const response = await this.$api.user.login(this.loginForm)
				if (response.data.code === 101) {
					//弹出登录成功提示
					uni.showToast({
						title: '登陆成功！',
						icon: 'none'
					})
					//保存用户的登陆状态
					this.saveLoginStatus(response.data.data)
					//获取用户的信息
					this.getUserInfo()
					//页面跳转
					const timer = setTimeout(() => {
						// 执行页面跳转
						this.goBack()
						// 在执行完页面跳转后取消计时器
						clearTimeout(timer)
					}, 1500);
				}
			},
			async getUserInfo() {
				const response = await this.$api.user.getUserInfo()
				if (response.data.code === 103) {
					//保存用户信息到vuex中
					this.saveUserinfo(response.data.data)
				}
			}
		}
	}
</script>

<style scoped>
	.container {
		background-color: #ffffff;
		background-image: url('/static/login/background.png');
		background-size: 100% 100%;
		background-repeat: no-repeat;
		width: 100%;
		overflow-y: auto;
		overflow-x: hidden;
		height: 100%;
		position: relative;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.bg-image {
		background-image: url('/static/login/background.png');
		background-size: 100% 100%;
		background-repeat: no-repeat;
		width: 100vw;
		height: 100vh;
	}

	.back {
		position: absolute;
		left: 5vw;
		top: 10vw;
		width: 10vw;
		height: 10vw;
	}

	.characters-image {
		position: absolute;
		right: 8.056vw;
		top: 4.722vw;
		width: 42.778vw;
		height: 50vw;
	}

	.register-box {
		position: absolute;
		left: 50%;
		top: 58.333vw;
		transform: translateX(-50%);

		opacity: 0.95;
		width: 85.556vw;

		align-items: center;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.register-image {
		width: 85.556vw;
		height: 89.167vw;
	}

	.register-text {
		position: absolute;
		top: 6.5vw;
		right: 14vw;

		color: #ffffff;
		line-height: 5.194vw;
		font-size: 6.667vw;
		font-family: 阿里妈妈刀隶体;

	}

	.login-box {
		position: absolute;
		left: 50%;
		top: 58.333vw;
		transform: translateX(-50%);

		opacity: 0.95;
		width: 85.556vw;

		align-items: center;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.login-image {
		filter: drop-shadow(0vw 0vw 2.778vw #00000040);
		width: 85.556vw;
		height: 89.167vw;
	}

	.login-text {
		position: absolute;
		left: 16vw;
		top: 6.5vw;

		color: #f86140;
		line-height: 5.194vw;

		font-size: 6.667vw;
		font-family: 阿里妈妈刀隶体;
	}

	.register-button {
		position: absolute;
		top: 3vw;
		right: 7vw;
		width: 200rpx;
		height: 100rpx;

		color: #ffffff;
		line-height: 5.194vw;
		font-size: 6.667vw;
		font-family: 阿里妈妈刀隶体;

		background-color: transparent;
		border: 0;
	}

	.input-field-username {
		position: absolute;
		left: 38%;
		top: 25vw;
		transform: translateX(-50%);
		width: 73vw;
		margin-left: 10.833vw;
	}

	.input-field-psw {
		position: absolute;
		left: 38%;
		top: 43vw;
		transform: translateX(-50%);
		width: 73vw;
		margin-left: 10.833vw;
	}

	.login-button {
		position: absolute;
		left: 51%;
		top: 71.389vw;
		transform: translateX(-50%);

		padding: 3.278vw 0 4.111vw 4vw;
		background-color: #f86140cc;
		border-radius: 3.611vw;
		width: 73.156vw;

		align-items: center;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;

		color: #ffffff;
		font-size: 5.1vw;
		font-family: 阿里妈妈刀隶体;
		line-height: 5.328vw;
		letter-spacing: 2.833vw;
	}

	.forget-psw {
		position: absolute;
		right: 3.611vw;
		bottom: 23.922vw;

		color: #f86140;
		font-size: 4vw;
		font-family: 阿里妈妈刀隶体;
		line-height: 3.8vw;
	}

	.third-party-login-text {
		position: absolute;
		left: 41vw;
		bottom: 42vw;

		color: #ffffff;
		font-size: 4.444vw;
		font-family: 思源黑体;
		font-weight: 900;
		line-height: 4.322vw;
	}

	.third-party-login {
		width: 64.444vw;
		position: absolute;
		right: 16.111vw;
		top: 185vw;

		justify-content: space-between;
		display: flex;
		flex-direction: row;
	}

	.phone-block {
		padding: 3vw 1vw 0vw 1vw;
		background-color: #ffffff;
		border-radius: 4.444vw;
		width: 12vw;
		height: 12vw;
		align-items: center;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.phone-icon {
		width: 5.5vw;
		height: 7.5vw;
	}

	.QQ-block {
		padding: 3vw 1vw 0vw 1vw;
		background-color: #ffffff;
		border-radius: 4.444vw;
		width: 12vw;
		height: 12vw;
		align-items: center;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.QQ-icon {
		width: 6.5vw;
		height: 8vw;
	}

	.WeChat-block {
		padding: 3vw 0.5vw 2vw 1vw;
		background-color: #ffffff;
		border-radius: 4.444vw;
		width: 12vw;
		height: 10vw;
		align-items: center;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.WeChat-icon {
		width: 8vw;
		height: 8.5vw;
	}
</style>