<template>
	<view class="userpage">
		<view class="user-info">
			<view class="top-area">
				<view class="datagram"><text class="datagram-text">数据报表展示</text></view>
				<view class="user-main-info">
					<view class="user-title">
						<view class="user-main-title">
							<image class="portrait" src="/static/userpage/portrait.png" />
							<view class="username">
								<view class="welcome">
									<text class="welcome-text">欢 迎 使 用 星 途 指 南！</text>
									<image class="star" src="/static/userpage/star.png" />
								</view>
								<text class="user-actual-name">Uthman Saleh</text>
							</view>
						</view>
						<image class="message" src="/static/userpage/message.png" />
					</view>

					<view class="user-introduction">
						<view class="praise-attention-fan">
							<view class="praise">
								<text class="praise-num">0</text>
								<text class="praise-text">获赞</text>
							</view>

							<view class="attention">
								<text class="attention-num">3</text>
								<text class="attention-text">关注</text>
							</view>

							<view class="fan">
								<text class="fan-num">1</text>
								<text class="fan-text">粉丝</text>
							</view>
						</view>
						<text class="person-introduction">个人简介</text>
						<text class="person-id">UID：0000000000 ~ IP属地：XX</text>
					</view>
				</view>
			</view>

			<view class="medium-area">
				<view class="collect">
					<image class="collect-image" src="/static/userpage/collect.png" />
					<text class="collect-text">我的收藏</text>
				</view>

				<view class="history">
					<image class="history-image" src="/static/userpage/history.png" />
					<text class="history-text">我的历史</text>
				</view>

				<view class="setting">
					<image class="setting-image" src="/static/userpage/setting.png" />
					<text class="setting-text">个人设置</text>
				</view>

				<view class="equipment">
					<image class="equipment-image" src="/static/userpage/equipment.png" />
					<text class="equipment-text">我的设备</text>
				</view>

				<view class="feedback">
					<image class="feedback-image" src="/static/userpage/feedback.png" />
					<text class="feedback-text">用户反馈</text>
				</view>
			</view>
		</view>

		<view class="post">
			<view class="post-area">
				<view class="post-title">
					<text class="post-title-text">帖子 0</text>
					<text class="answer-text">问答 0</text>
				</view>

				<view class="line-chosen"></view>
				<view class="post-line"></view>

				<view class="post-main">
					<text class="post-main-text">我的帖子></text>
					<view class="post-introduction">
						<text class="post-introduction-text">帖子推荐</text>
					</view>
				</view>
			</view>
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
	.userpage {
		background-color: #fafcfb;
		width: 100%;
		overflow-y: auto;
		overflow-x: hidden;
		height: 100%;
		display: flex;
		flex-direction: column;
	}

	.user-info {
		height: 1019rpx;
		display: flex;
		flex-direction: column;
	}

	.top-area {
		display: flex;
		flex-direction: column;
	}

	.datagram {
		padding: 221rpx 0 312rpx;
		filter: drop-shadow(0rpx 0rpx 2rpx #00000040);
		background-color: #fa6d4f;
		align-items: center;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.datagram-text {
		line-height: 33rpx;
		font-size: 35rpx;
		font-family: 字魂天龙行楷;
		line-height: 32rpx;
		color: #ffffff;
	}

	.user-main-info {
		margin-top: 40rpx;
		padding-left: 48rpx;
		padding-bottom: 104rpx;
		display: flex;
		flex-direction: column;
	}

	.user-title {
		align-items: center;
		justify-content: space-between;
		display: flex;
		flex-direction: row;
	}

	.user-main-title {
		display: flex;
		flex-direction: row;
	}

	.portrait {
		border-radius: 15rpx;
		width: 92rpx;
		height: 92rpx;
	}

	.username {
		margin-left: 25rpx;
		align-self: flex-start;
		display: flex;
		flex-direction: column;
	}

	.welcome {
		align-items: center;
		display: flex;
		flex-direction: row;
	}

	.welcome-text {
		font-size: 23rpx;
		font-family: 字魂天龙行楷;
		line-height: 22rpx;
		color: #73848c;
	}

	.star {
		width: 21rpx;
		height: 23rpx;
		flex-shrink: 0;
		margin-left: 15rpx;
	}

	.user-actual-name {
		color: #452933;
		font-size: 46rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		line-height: 37rpx;
		margin-top: 25rpx;
	}

	.message {
		margin-right: 10rpx;
		border-radius: 15rpx;
		width: 77rpx;
		height: 77rpx;
	}

	.user-introduction {
		display: flex;
		flex-direction: column;
		margin-top: 54rpx;
	}

	.praise-attention-fan {
		align-self: stretch;
		align-items: baseline;
		display: flex;
		flex-direction: row;
	}

	.praise {
		line-height: 20rpx;
		height: 20rpx;
	}

	.praise-num {
		color: #fa6d4f;
		font-size: 21rpx;
		font-family: 字魂天龙行楷;
		line-height: 18rpx;
		letter-spacing: 8rpx;
	}

	.praise-text {
		margin-left: 12rpx;
		line-height: 20rpx;
		font-size: 19rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		letter-spacing: 8rpx;
		color: #000000;
	}

	.attention {
		line-height: 18rpx;
		height: 18rpx;
		margin-left: 65rpx;
	}

	.attention-num {
		line-height: 18rpx;
		font-size: 19rpx;
		font-family: 字魂天龙行楷;
		letter-spacing: 8rpx;
		color: #fa6d4f;
	}

	.attention-text {
		margin-left: 12rpx;
		line-height: 17rpx;
		font-size: 19rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		letter-spacing: 8rpx;
		color: #000000;
	}

	.fan {
		line-height: 17rpx;
		height: 17rpx;
		margin-left: 65rpx;
	}

	.fan-num {
		line-height: 17rpx;
		font-size: 19rpx;
		font-family: 字魂天龙行楷;
		letter-spacing: 8rpx;
		color: #fa6d4f;
	}

	.fan-text {
		margin-left: 8rpx;
		line-height: 16rpx;
		font-size: 19rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		letter-spacing: 8rpx;
		color: #000000;
	}

	.person-introduction {
		margin-top: 33rpx;
		color: #000000;
		font-size: 19rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		line-height: 19rpx;
		align-self: flex-start;
	}

	.person-id {
		margin-top: 30rpx;
		color: #77888f;
		font-size: 19rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		line-height: 19rpx;

		align-self: flex-start;
	}

	.medium-area {
		position: relative;
		margin: -62rpx 4rpx 0;
		display: flex;
		flex-direction: row;
	}

	.collect {
		padding: 12rpx 0;
		flex: 1 1 148rpx;
		align-items: center;
		display: flex;
		flex-direction: column;
	}

	.collect-image {
		width: 48rpx;
		height: 48rpx;
	}

	.collect-text {
		margin-top: 13rpx;
		line-height: 21rpx;
		font-size: 23rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		line-height: 22rpx;
		color: #77888f;
	}

	.history {
		padding: 12rpx 0;
		flex: 1 1 148rpx;
		align-items: center;
		display: flex;
		flex-direction: column;
	}

	.history-image {
		width: 48rpx;
		height: 48rpx;
	}

	.history-text {
		line-height: 20rpx;
		font-size: 23rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		line-height: 22rpx;
		color: #77888f;
		margin-top: 15rpx;
	}

	.setting {
		padding: 12rpx 0;
		flex: 1 1 148rpx;
		align-items: center;
		display: flex;
		flex-direction: column;
	}

	.setting-image {
		width: 48rpx;
		height: 48rpx;
	}

	.setting-text {
		line-height: 20rpx;
		font-size: 23rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		line-height: 22rpx;
		color: #77888f;
		margin-top: 15rpx;
	}

	.equipment {
		padding: 12rpx 0;
		flex: 1 1 148rpx;
		align-items: center;
		display: flex;
		flex-direction: column;
	}

	.equipment-image {
		border-radius: 13rpx;
		width: 48rpx;
		height: 48rpx;
	}

	.equipment-text {
		margin-top: 13rpx;
		line-height: 20rpx;
		font-size: 23rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		line-height: 22rpx;
		color: #77888f;
	}

	.feedback {
		padding: 12rpx 0;
		flex: 1 1 148rpx;
		align-items: center;
		display: flex;
		flex-direction: column;
	}

	.feedback-image {
		width: 48rpx;
		height: 48rpx;
	}

	.feedback-text {
		line-height: 20rpx;
		font-size: 23rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		line-height: 22rpx;
		color: #77888f;
		margin-top: 15rpx;
	}

	.post {
		margin-top: 29rpx;
		display: flex;
		flex-direction: column;
	}

	.post-area {
		filter: drop-shadow(0rpx 0rpx 3rpx #00000040);
		background-color: #ffffff;
		display: flex;
		flex-direction: column;
	}

	.post-title {
		padding: 25rpx 187rpx 20rpx;
		justify-content: space-between;
		display: flex;
		flex-direction: row;
	}

	.post-title-text {
		color: #000000;
		line-height: 32rpx;
		font-size: 35rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		line-height: 32rpx;
	}

	.answer-text {
		color: #77888f;
		font-size: 35rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		line-height: 32rpx;
	}

	.post-line {
		width: 100vw;
		height: 1rpx;
		flex-shrink: 0;
		background-color: #c4c4c480;
		align-items: flex-start;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.line-chosen {
		margin-left: 183rpx;
		background-color: #fa6d4f;
		border-radius: 60rpx;
		width: 110rpx;
		height: 8rpx;
		flex-shrink: 0;
	}

	.post-main {
		padding: 19rpx 12rpx 37rpx 46rpx;
		display: flex;
		flex-direction: column;
	}

	.post-main-text {
		line-height: 22rpx;
		font-size: 23rpx;
		font-family: 字魂天龙行楷;
		line-height: 22rpx;
		color: #77888f;
		align-self: flex-end;
	}

	.post-introduction {
		margin-top: 33rpx;
		margin-right: 33rpx;
		padding: 54rpx 0 77rpx;
		background-color: #fa6d4f;
		align-self: stretch;
		align-items: center;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.post-introduction-text {
		line-height: 33rpx;
		font-size: 35rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		line-height: 32rpx;
		color: #ffffff;
	}

	/* .navigation {
		padding-top: 60rpx;
		position: relative;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.navigation-main {
		padding: 0 94rpx 4rpx;
		border-radius: 31rpx;
		background-image: url('/static/userpage/background.png');
		background-size: 100% 100%;
		background-repeat: no-repeat;
		align-items: flex-end;
		justify-content: space-between;
		display: flex;
		flex-direction: row;
	}

	.navigation-unselected {
		width: 423rpx;
		align-items: flex-end;
		display: flex;
		flex-direction: row;
	}

	.navigation-unselected-left {
		margin-top: -100rpx;
		width: 327rpx;
		align-items: flex-start;
		display: flex;
		flex-direction: row;
	}

	.magic-background {
		position: absolute;
		left: 333rpx;
		right: 269rpx;
		bottom: 70rpx;
		background-color: #ff93467a;
		border-radius: 0rpx 0rpx 23rpx 23rpx;
		filter: blur(15.38rpx);
		width: 90rpx;
		height: 56rpx;
	}

	.magic {
		position: absolute;
		left: 50%;
		top: 23rpx;
		transform: translateX(-50%);
		border-radius: 138rpx;
		width: 92rpx;
		height: 92rpx;
	}

	.zhuye {
		margin-left: 10rpx;
		margin-top: 23rpx;
		align-items: center;
		display: flex;
		flex-direction: column;
	}

	.zhuye-image {
		width: 54rpx;
		height: 54rpx;
	}

	.zhuye-text {
		line-height: 22rpx;
		font-size: 23rpx;
		font-family: 字魂天龙行楷;
		line-height: 22rpx;
		color: #73848c;
		margin-top: 7rpx;
	}

	.xingcheng {
		margin-left: 85rpx;
		margin-top: 30rpx;
		align-items: center;
		display: flex;
		flex-direction: column;
	}

	.xingcheng-image {
		width: 42rpx;
		height: 44rpx;
	}

	.xingcheng-text {
		margin-top: 10rpx;
		line-height: 21rpx;
		font-size: 23rpx;
		font-family: 字魂天龙行楷;
		line-height: 22rpx;
		color: #73848c;
	}

	.shequ {
		margin-top: 62rpx;
		align-items: center;
		display: flex;
		flex-direction: column;
		margin-left: 40rpx;
	}

	.shequ-image {
		width: 55rpx;
		height: 50rpx;
	}

	.shequ-text {
		font-size: 23rpx;
		font-family: 字魂天龙行楷;
		line-height: 22rpx;
		color: #73848c;
		margin-top: 7rpx;
	}

	.yonghu {
		margin-left: 30rpx;
		margin-top: 8rpx;
		align-items: center;
		display: flex;
		flex-direction: column;
	}

	.yonghu-image {
		width: 50rpx;
		height: 72rpx;
	}

	.yonghu-text {
		color: #fa6d4f;
		line-height: 22rpx;
		font-size: 23rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		line-height: 22rpx;
		margin-top: 4rpx;
	} */
</style>
