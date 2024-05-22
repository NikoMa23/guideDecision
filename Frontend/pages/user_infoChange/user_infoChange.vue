<template>
	<view class="personalinfo">
		<view class="personalinfogroup">
			<br><text class="title">修改个人信息</text><br><br>
			<text class="font knowntext">让更多朋友了解你</text>

			<view class="portrait">
				<image class="camera" src="/static/user_infoChange/camera.png" />
			</view>

			<view class="gender-selection">
				<radio-group>
					<label>
						<radio class="male" value="男生" />男生
					</label>
					<label>
						<radio class="female" value="女生" />女生
					</label>
				</radio-group>
			</view>

			<view class="name">
				<text class="username">姓名</text>
				<input class="username-input" type="text" placeholder="请输入姓名" />
			</view>
			<view class="country">
				<text class="usercountry">国家</text>
				<input class="country-input" type="text" placeholder="请输入国家" />
			</view>
			<view class="city">
				<text class="usercity">城市</text>
				<input class="city-input" type="text" placeholder="请输入城市" />
			</view>
			<view class="birthday">
				<text class="userbirthday">出生日期</text>
				<input class="birthday-input"type="text" placeholder="请输入出生日期" />
			</view>
			
			<button class="userconfirm">确认修改</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {

			}
		},
		methods: {

		}
	}
</script>

<style>
	.personalinfogroup {
		padding: 32rpx 50rpx 172rpx;
		overflow-y: auto;

	}

	.title {
		margin-top: 100rpx;
		;
		color: #505050;
		font-size: 52rpx;
		font-family: 思源黑体;
		font-weight: 700;
		line-height: 49.3rpx;
	}

	.portrait {
		margin-top: 64rpx;
		padding: 64rpx 0;
		background-color: #f27f45;
		border-radius: 50%;
		width: 172rpx;

		display: flex;
		align-items: center;
		justify-content: center;
	}

	.camera {
		width: 48rpx;
		height: 42rpx;
	}

	.gender-selection {
		width: 48.896vw;
		align-self: flex-start;
		margin-top: 7.467vw;
		display: flex;
		flex-direction: row;
	}

	.male {
		margin-right: 2.5vw;
		font-size: 3.467vw;
		font-family: 思源黑体;
		line-height: 3.291vw;
		font-weight: 700;
		color: #000000;
		align-self: flex-start;
	}

	.female {
		margin-left: 15vw;
		margin-right: 2.5vw;
		font-size: 3.467vw;
		font-family: 思源黑体;
		line-height: 3.291vw;
		font-weight: 700;
		color: #000000;
		align-self: flex-start;
	}

	.knowntext {
		color: #9e9e9e;
		line-height: 29.38rpx;
	}

	.name {
		margin-top: 44rpx;
		padding-bottom: 32rpx;
		border-bottom: solid 2rpx #dadada;

		margin-top: 52rpx;
	}

	.username {
		font-size: 26rpx;
		font-family: 思源黑体;
		line-height: 24.68rpx;
		font-weight: 700;
		color: #5b5b5b;
		line-height: 24.54rpx;
	}
	
	.username-input {
		margin-top: 4.267vw;
		margin-bottom: -2vw;
	}

	.country {
		margin-top: 44rpx;
		padding-bottom: 32rpx;
		border-bottom: solid 2rpx #dadada;

		margin-top: 52rpx;
	}

	.usercountry {
		font-size: 26rpx;
		font-family: 思源黑体;
		line-height: 24.68rpx;
		font-weight: 700;
		color: #5b5b5b;
		line-height: 24.54rpx;
	}
	
	.country-input {
		margin-top: 4.267vw;
		margin-bottom: -2vw;
	}

	.city {
		margin-top: 44rpx;
		padding-bottom: 32rpx;
		border-bottom: solid 2rpx #dadada;

		margin-top: 52rpx;
	}

	.usercity {
		font-size: 26rpx;
		font-family: 思源黑体;
		line-height: 24.68rpx;
		font-weight: 700;
		color: #5b5b5b;
		line-height: 24.54rpx;
	}
	
	.city-input {
		margin-top: 4.267vw;
		margin-bottom: -2vw;
	}

	.birthday {
		margin-top: 44rpx;
		padding-bottom: 32rpx;
		border-bottom: solid 2rpx #dadada;

		margin-top: 52rpx;
	}

	.userbirthday {
		font-size: 26rpx;
		font-family: 思源黑体;
		line-height: 24.68rpx;
		font-weight: 700;
		color: #5b5b5b;
		line-height: 24.54rpx;
	}
	
	.birthday-input {
		margin-top: 4.267vw;
		margin-bottom: -2vw;
	}

	.confirm {
		align-self: stretch;
		margin-top: 80rpx;

		padding: 20rpx 0 48rpx;
		background-color: #f2a347;
		border-radius: 48rpx;
		text-align: center;
	}

	.userconfirm {
		align-self: stretch;
		margin-top: 80rpx;
		
		padding: 29rpx 1rpx 44rpx;
		background-color: #f27c41;
		border-radius: 48rpx;
		text-align: center;
		
		font-size: 32rpx;
		font-family: 思源黑体;
		line-height: 29.24rpx;

		color: #ffffff;
		font-weight: 900;
		line-height: 29.08rpx;
		letter-spacing: 3rpx;
	}

	/* radio 边框颜色 */
	/deep/ uni-radio .uni-radio-input {
		width: 30rpx;
		height: 30rpx;
		border: 2rpx solid rgba(241, 241, 224, 0.5) !important;
		/* 防止IOS出现一侧边框缺失的问题 */
		transform: rotateZ(360deg);
		background-color: rgba(230, 230, 230, 0.5) !important;
		margin-right: 0rpx !important;
	}

	/* radio 选中后的边框颜色 */
	/deep/ uni-radio .uni-radio-input-checked {
		border: 2rpx solid #ebf1e9 !important;
		transform: rotateZ(360deg);
		background-color: #eaeaeb !important;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	/* radio 选中后的图标样式*/
	/deep/ uni-radio .uni-radio-input.uni-radio-input-checked::before {
		display: block;
		content: "" !important;
		width: 2.8vw !important;
		height: 2.8vw !important;
		left: 1.95vw;
		top: 1.95vw;
		background: #ff9f73 !important;
		border-radius: 20rpx !important;
	}
</style>
