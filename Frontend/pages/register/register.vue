<template>
	<view class="container">
		<image class="bg-image"></image>
		<image class="back" src="/static/login/back.png" @tap="goBack()"></image>
		<image class="characters-image" src="/static/login/background1.png"></image>

		<view class="login-box">
			<image class="login-image" src="/static/register/login-block.png"></image>
			<text class="login-text">登录</text>
		</view>

		<view class="register-box">
			<image class="register-image" src='/static/register/register-block.png'></image>
			<text class="register-text">注册</text>
			<button class="login-button1" plain="true" @tap="gologin()"></button>

			<uni-forms ref="loginRef" :rules="rules" :model="loginForm">
				<uni-forms-item class="input-field-username" name="username">
					<uni-easyinput type="text" primaryColor="#f86140cc" prefixIcon="person"
						placeholderStyle="font-size: 4vw;font-family: 阿里妈妈刀隶体;color: #73848c;"
						v-model="loginForm.username" placeholder="请输入注册用户名" />
				</uni-forms-item>
				<uni-forms-item class="input-field-psw" name="password">
					<uni-easyinput type="password" primaryColor="#f86140cc" prefixIcon="locked"
						placeholderStyle="font-size: 4vw;font-family: 阿里妈妈刀隶体;color: #73848c;"
						v-model="loginForm.password" placeholder="请输入注册密码" />
				</uni-forms-item>
				<uni-forms-item class="input-field-conpsw" name="confirmpassword">
					<uni-easyinput type="password" primaryColor="#f86140cc" prefixIcon="hand-up"
						placeholderStyle="font-size: 4vw;font-family: 阿里妈妈刀隶体;color: #73848c;"
						v-model="loginForm.confirmpassword" placeholder="请再次输入注册密码" />
				</uni-forms-item>
				<uni-forms-item class="input-field-code" name="Code">
					<uni-easyinput type="text" primaryColor="#f86140cc" prefixIcon="auth"
						placeholderStyle="font-size: 4vw;font-family: 阿里妈妈刀隶体;color: #73848c;"
						v-model="loginForm.authCode" placeholder="输入验证码" />
				</uni-forms-item>
			</uni-forms>
			<button class="code-button" @click="clickattemptRegister()">获取验证码</button>
			<button class="register-button" @click="clickCodeRegister()">注册</button>
			<!-- 			<text class="info">快来一探究竟吧！</text> -->
		</view>


		<text class="third-party-login-text">第三方注册</text>

		<view class="third-party-login">
			<view class="QQ-block">
				<image class="QQ-icon" src="/static/login/QQ.png"></image>
			</view>
			<view class="WeChat-block">
				<image class="WeChat-icon" src="/static/login/WeChat.png"></image>
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
					password: "",
					authCode: "",
					confirmpassword: ""
				},
				//登陆表单的验证规则
				rules: {
					username: {
						rules: [{
							required: true,
							errorMessage: '请输入用户名',
							validateTrigger: 'submit'
						}]
					},
					password: {
						rules: [{
								required: true,
								errorMessage: "请输入密码",
								validateTrigger: 'submit'
							},
							{
								required: true,
								errorMessage: '密码不能为空'
							}
						]
					},
					confirmpassword: {
						rules: [{
							required: true,
							errorMessage: "请再次输入密码"
						}]
					}
				}
			}
		},
		methods: {
			...mapMutations(['saveLoginInfo']),
			//关闭当前页面
			goBack() {
				uni.switchTab({
					url: "/pages/mainpage/mainpage"
				})
			},
			gologin() {
				uni.navigateTo({
					url: "/pages/login/login"
				});
			},
			//点击注册对登录表单输入的数据进行校验
			clickattemptRegister() {
				//判断密码是否一致
				if (this.loginForm.password !== this.loginForm.confirmpassword) {
					uni.showToast({
						icon: "none",
						title: "两次密码不一致，请重新输入"
					})
					return
				}
				//校验请求参数，发送请求
				this.$refs.loginRef.validate().then(res => {
					//调用注册的方法
					this.submitRegister()
				}).catch(err => {
					console.log('表单错误信息：', err);
				})
			},
			async submitRegister() {
				//发送注册的接口请求
				const response = await this.$api.user.register(this.loginForm)
				if (response.data.code === 201) {
					//弹出验证码发送成功提示
					uni.showToast({
						title: '验证码已发送！',
						icon: 'none'
					})
					//保存用户信息和token
					// this.saveLoginInfo(response.data)

					// uni.showToast({
					// 	title:'注册成功'
					// })					
					// //页面跳转
					// uni.navigateTo({
					// 	url:'/pages/login/login'
					// })
				}
			},

			//点击注册对登录表单输入的数据进行校验
			clickCodeRegister() {
				// //判断密码是否一致
				// if(this.loginForm.password!=this.confirm.password){
				// 	uni.showToast({
				// 		icon:"none",
				// 		title:"两次密码不一致，请重新输入"
				// 	})
				// 	return
				// }
				//校验请求参数，发送请求
				this.$refs.loginRef.validate().then(res => {
					//调用注册的方法
					this.submitCode()
				}).catch(err => {
					console.log('表单错误信息：', err);
				})
			},
			async submitCode() {
				//发送注册的接口请求
				const response = await this.$api.user.code(this.loginForm)
				if (response.data.code === 102) {
					//弹出注册成功提示
					uni.showToast({
						title: '注册成功！',
						icon: 'none'
					})
					const timer = setTimeout(() => {
						// 执行页面跳转
						this.gologin()
						// 在执行完页面跳转后取消计时器
						clearTimeout(timer)
					}, 1500);
					//保存用户信息和token
					// this.saveLoginInfo(response.data)

					// uni.showToast({
					// 	title:'注册成功'
					// })					
					// //页面跳转
					// uni.navigateTo({
					// 	url:'/pages/login/login'
					// })
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

	.back {
		position: absolute;
		left: 5vw;
		top: 10vw;
		width: 10vw;
		height: 10vw;
	}

	.bg-image {
		background-image: url('/static/login/background.png');
		background-size: 100% 100%;
		background-repeat: no-repeat;
		width: 100vw;
		height: 100vh;
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
		filter: drop-shadow(0vw 0vw 2.778vw #00000035);
		width: 85.556vw;
		height: 115.167vw;
	}

	.register-text {
		position: absolute;
		top: 6.5vw;
		right: 14vw;
		color: #f86140;
		line-height: 5.194vw;
		font-size: 6.667vw;
		font-family: 阿里妈妈刀隶体;
	}

	.login-button1 {
		position: absolute;
		top: 3vw;
		left: 7vw;
		width: 200rpx;
		height: 100rpx;

		color: #ffffff;
		line-height: 5.194vw;
		font-size: 6.667vw;
		font-family: 阿里妈妈刀隶体;

		background-color: transparent;
		border: 0;
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
		width: 85.556vw;
		height: 115.167vw;
	}

	.login-text {
		position: absolute;
		left: 16vw;
		top: 6.5vw;

		color: #ffffff;
		line-height: 5.194vw;

		font-size: 6.667vw;
		font-family: 阿里妈妈刀隶体;
	}

	.input-field-username {
		position: absolute;
		left: 38%;
		top: 25vw;
		transform: translateX(-50%);

		background-color: #ffffff;
		box-shadow: 0vw 0vw 0.556vw #00000040 inset;
		width: 73vw;

		justify-content: center;

		display: flex;
		flex-direction: row;

		margin-left: 10.833vw;
		line-height: 4.275vw;
		font-size: 7vw;
		font-family: 阿里妈妈刀隶体;
		line-height: 4.069vw;
		color: #73848c;
		align-self: flex-start;
	}

	.login-icon-username {
		position: absolute;
		left: 3.1vw;
		top: 50%;
		transform: translateY(-50%);

		width: 8.611vw;
		height: 8.611vw;

		align-self: center;
	}

	.input-field-psw {
		position: absolute;
		left: 38%;
		top: 43vw;
		transform: translateX(-50%);

		background-color: #ffffff;
		box-shadow: 0vw 0vw 0.556vw #00000040 inset;
		width: 73vw;

		justify-content: center;

		display: flex;
		flex-direction: row;

		margin-left: 10.833vw;
		line-height: 4.275vw;
		font-size: 5vw;
		font-family: 阿里妈妈刀隶体;
		line-height: 4.069vw;
		color: #73848c;
		align-self: flex-start;
	}

	.input-field-conpsw {
		position: absolute;
		left: 38%;
		top: 61vw;
		transform: translateX(-50%);

		background-color: #ffffff;
		box-shadow: 0vw 0vw 0.556vw #00000040 inset;
		width: 73vw;

		justify-content: center;

		display: flex;
		flex-direction: row;

		margin-left: 10.833vw;
		line-height: 4.275vw;
		font-size: 5vw;
		font-family: 阿里妈妈刀隶体;
		line-height: 4.069vw;
		color: #73848c;
		align-self: flex-start;
	}

	.input-field-code {
		position: absolute;
		left: 17%;
		top: 78vw;
		transform: translateX(-50%);

		background-color: #ffffff;
		box-shadow: 0vw 0vw 0.556vw #00000040 inset;
		width: 37vw;

		justify-content: center;

		display: flex;
		flex-direction: row;

		margin-left: 10.833vw;
		margin-top: 1vw;
		line-height: 4.275vw;
		font-size: 5vw;
		font-family: 阿里妈妈刀隶体;
		line-height: 4.069vw;
		color: #73848c;
		align-self: flex-start;
	}

	.login-icon-psw {
		position: absolute;
		left: 3.1vw;
		top: 50%;
		transform: translateY(-50%);

		width: 8.611vw;
		height: 8.611vw;

		align-self: center;
	}

	.code-button {
		position: absolute;
		left: 75%;
		top: 79vw;
		transform: translateX(-50%);

		padding: 3.278vw 0 4.111vw 2vw;
		background-color: #f86140cc;
		border-radius: 3.611vw;
		width: 33.156vw;

		align-items: center;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;

		color: #ffffff;
		font-size: 3.5vw;
		font-family: 阿里妈妈刀隶体;
		font-weight: 600;
		line-height: 5.328vw;
		letter-spacing: 1.3vw;
	}

	.register-button {
		position: absolute;
		left: 51%;
		/* top: 91.389vw; */
		bottom: 7vw;
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

	.info {
		position: absolute;
		right: 24vw;
		bottom: 5.922vw;

		color: #f86140;
		font-size: 4vw;
		font-family: 阿里妈妈刀隶体;
		line-height: 3.8vw;
	}

	.third-party-login-text {
		position: absolute;
		left: 40vw;
		bottom: 32vw;

		color: #ffffff;
		font-size: 4.444vw;
		font-family: 思源黑体;
		font-weight: 900;
		line-height: 4.322vw;
	}

	.third-party-login {
		width: 40vw;
		position: absolute;
		right: 29vw;
		margin-top: 195vw;

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