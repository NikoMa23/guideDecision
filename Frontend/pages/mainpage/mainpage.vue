<template>
	<view class="mainpage">
		<view class="on">
			<view class="searchsort">
				<view class="searchpage">
					<view class="search">
						<view class="usertitle">
							<view class="userinfo">
								<image class="touxiang" src="/static/mainpage/touxiang.png" />
								<view class="description">
									<view class="welcome">
										<text class="welcome1">欢 迎 使 用 星 途 指 南！</text>
										<image class="star" src="/static/mainpage/star.png" />
									</view>
									<text class="username">Uthman Saleh</text>
									<!-- <text class="username">{{store.state.userInfo.userNickname}}</text> -->
								</view>
							</view>
							<image class="message" src="/static/mainpage/message.png" />
						</view>

						<view class="searchbox">
							<image class="bird" src="/static/mainpage/bird.png" />
							<view class="searchbox1">
								<view class="searchbox2">
									<image class="leaf" src="/static/mainpage/leaf.png" />
								</view>

								<image class="human" src="/static/mainpage/human.png" />
								<image class="leaf1" src="/static/mainpage/leaf1.png" />

								<view class="searchbox3">
									<!-- <input class="city" type="text" placeholder-style="height:60rpx;font-size:50rpx" placeholder="城市" /> -->
									<uni-forms ref="cityRef" :rules="rules" :model="cityForm">
										<uni-forms-item class="input-field-cityName" name="cityName">
											<uni-easyinput type="text" primaryColor="#f86140cc" prefixIcon=""
												placeholderStyle="font-size: 3vw;line-height:5vw;font-family: 阿里妈妈刀隶体;color: #73848c;"
												v-model="cityForm.cityName" placeholder="城市名" />
										</uni-forms-item>
									</uni-forms>
									<!-- <input class="number" type="text" placeholder-style="height:60rpx;font-size:50rpx" placeholder="数量" /> -->
									<uni-forms-item class="input-field-number" name="numer">
										<uni-easyinput type="text" primaryColor="#f86140cc" prefixIcon=""
											placeholderStyle="font-size: 3vw;line-height:5vw;font-family: 阿里妈妈刀隶体;color: #73848c;"
											v-model="cityForm.num" placeholder="景点数量" />
									</uni-forms-item>
									<image class="searchpicture" @click="clickSearch()"
										src="/static/mainpage/searchpicture.png" />
									<view class="searchpicture-shadow"></view>
								</view>
							</view>
						</view>
					</view>

					<view class="sorttitle">
						<text class="sorttitle1">热门分类</text>
						<view class="preference">
							<image class="preference1" src="/static/mainpage/preference.png" />
							<text class="preference2">点击此处选择您的偏好！</text>
						</view>
					</view>
				</view>

				<view class="hotsort">
					<view class="hotsort1">
						<image class="hotpicture1" src="/static/mainpage/hotpicture1.png" />
						<text class="hottext1">赏景</text>
					</view>

					<view class="hotsort2">
						<image class="hotpicture2" src="/static/mainpage/hotpicture2.png" />
						<text class="hottext2">探险</text>
					</view>

					<view class="hotsort3">
						<image class="hotpicture3" src="/static/mainpage/hotpicture3.png" />
						<text class="hottext3">美食</text>
					</view>

					<view class="hotsort4">
						<image class="hotpicture4" src="/static/mainpage/hotpicture4.png" />
						<text class="hottext4">文化</text>
					</view>
				</view>
			</view>
			<text class="recommendtitle">景点推荐</text>
		</view>
		<view class="down">
			<view class="recommend1">
				<view class="recommend1image">
					<image class="recommend1picture" src="/static/mainpage/recommendpicture1.png" />
				</view>

				<text class="recommend1name">Debtakhum</text>
				<text class="recommend1price">$2,090</text>
			</view>

			<view class="recommend2">
				<view class="recommend2image">
					<image class="recommend2picture" src="/static/mainpage/recommendpicture2.png" />
				</view>

				<text class="recommend2name">Chimbuk Hill</text>

				<view class="recommend2-presentation">
					<image class="star-samll" src="/static/mainpage/star-small.png" />
					<text class="recommend2-score">(5.0)</text>
				</view>
			</view>

			<view class="recommend3">
				<view class="recommend3frame"></view>
				<text class="recommend3name">Sundarban</text>
				<image class="recommend3picture" src="/static/mainpage/recommendpicture3.png" />
			</view>
		</view>
	</view>
</template>

<script>
	import store from '@/store/index.js'
	export default {
		data() {
			return {
				cityForm: {
					cityName: "",
					num: "",
				},

				"datalist": [],

				rules: {
					cityName: {
						rules: [{
							required: true,

						}]
					},
					num: {
						rules: [{
							required: true,
						}]
					}
				}
			}
		},
		methods: {
			clickSearch() {
				this.$refs.cityRef.validate().then(res => {
					//调用发送的方法
					this.submitSearch()
				}).catch(err => {
					console.log('表单错误信息：', err);
				})
			},
			async submitSearch() {
				//发送注册的接口请求
				const response = await this.$api.user.submitXC(this.cityForm, store.state.isAuth);
				this.datalist = response.data.data;
				if (response.statusCode == 200) {
					//弹出验证码发送成功提示
					uni.showToast({
						title: '获取路线成功！',
						icon: 'none',
					});
					uni.navigateTo({
						url: '/pages/detail/detail?listData=' + JSON.stringify(this.datalist)
					});
				};
			}
		}
	}
</script>

<style>
	.mainpage {
		padding-top: 110rpx;
		background-color: #fafcfb;
		width: 100%;
		overflow-y: auto;
		overflow-x: hidden;
		height: 100%;

		display: flex;
		flex-direction: column;
	}

	.on {
		display: flex;
		flex-direction: column;
	}

	.searchsort {
		align-self: stretch;
		display: flex;
		flex-direction: column;
	}

	.searchpage {
		padding-left: 46rpx;
		display: flex;
		flex-direction: column;
	}

	.search {
		height: 596rpx;
		display: flex;
		flex-direction: column;
	}

	.usertitle {
		align-items: center;
		justify-content: space-between;
		display: flex;
		flex-direction: row;
	}

	.userinfo {
		display: flex;
		flex-direction: row;
	}

	.touxiang {
		border-radius: 15rpx;
		width: 92rpx;
		height: 92rpx;
	}

	.description {
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

	.welcome1 {
		font-size: 23rpx;
		font-family: 字魂天龙行楷;
		line-height: 22rpx;
		color: #77888f;
	}

	.star {
		width: 21rpx;
		height: 23rpx;
		flex-shrink: 0;
		margin-left: 15rpx;
	}

	.username {
		color: #452933;
		line-height: 37rpx;
		font-size: 46rpx;
		font-family: 字魂天龙行楷;
		margin-top: 19rpx;
		letter-spacing: 5rpx;
	}

	.message {
		border-radius: 15rpx;
		width: 77rpx;
		height: 77rpx;
		margin-right: 25rpx;
	}

	.searchbox {
		margin-right: 46rpx;
		margin-top: -44rpx;
		padding: 88rpx 0 404rpx;
		border-radius: 46rpx;
		overflow: hidden;
		position: relative;
		align-items: flex-start;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.bird {
		margin-left: 85rpx;
		width: 84rpx;
		height: 55rpx;
		flex-shrink: 0;
	}

	.searchbox1 {
		position: absolute;
		left: 0;
		right: 0;
		top: 88rpx;
		padding-top: 127rpx;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.searchbox2 {
		padding: 33rpx 5rpx 98rpx;
		border-radius: 46rpx;
		background-image: url('/static/mainpage/search-background.png');
		background-size: 100% 100%;
		background-repeat: no-repeat;
		align-items: flex-start;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.leaf {
		margin-left: 108rpx;
		width: 196rpx;
		height: 202rpx;
	}

	.human {
		position: absolute;
		left: 212rpx;
		top: 0;
		width: 221rpx;
		height: 412rpx;
	}

	.leaf1 {
		position: absolute;
		right: 168rpx;
		bottom: 105.75rpx;
		width: 153.85rpx;
		height: 151.92rpx;
	}

	.searchbox3 {
		position: absolute;
		left: 50%;
		top: 262rpx;
		transform: translateX(-50%);
		padding: 38rpx 31rpx 38rpx 46rpx;
		background-color: #ffffff;
		border-radius: 31rpx;
		filter: drop-shadow(0rpx 15rpx 15rpx #322f230f);
		width: 460rpx;
		height: 80rpx;
		justify-content: space-between;
		display: flex;
		flex-direction: row;
	}

	.input-field-cityName {
		left: 38%;
		transform: translateX(-50%);
		width: 25vw;
		margin-left: 1vw;
	}

	.input-field-number {
		left: 10vw;
		transform: translateX(-50%);
		width: 23vw;
		margin-left: 1vw;
	}

	.city {
		margin-top: 8rpx;
		color: #73848c80;
		line-height: 48rpx;
		font-size: 46rpx;
		font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
		align-self: flex-start;
		width: 300rpx;
	}

	.number {
		margin-top: 8rpx;
		color: #73848c80;
		line-height: 48rpx;
		font-size: 46rpx;
		font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
		align-self: flex-start;
		width: 300rpx;
	}

	.searchpicture {
		border-radius: 15rpx;
		width: 75rpx;
		height: 75rpx;
		margin-top: 1.5vw;
		align-self: center;
	}

	.searchpicture-shadow {
		position: absolute;
		right: 36rpx;
		top: 115rpx;
		background-color: #ff93468f;
		filter: blur(15.38rpx);
		width: 60rpx;
		height: 19rpx;
	}

	.sorttitle {
		margin-top: 40rpx;
		justify-content: space-between;
		display: flex;
		flex-direction: row;
	}

	.sorttitle1 {
		font-size: 35rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		letter-spacing: 8rpx;
		line-height: 33rpx;
		color: #452933;
		align-self: flex-start;
	}

	.preference {
		margin-right: 20rpx;
		display: flex;
		flex-direction: row;
	}

	.preference1 {
		width: 46rpx;
		height: 46rpx;
		flex-shrink: 0;
	}

	.preference2 {
		color: #77888f;
		font-size: 15rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		line-height: 18rpx;
		align-self: flex-start;
		margin-left: 16rpx;
		margin-top: 15rpx;
	}

	.hotsort {
		margin-top: 10rpx;
		padding: 0 19rpx;
		display: flex;
		flex-direction: row;
	}

	.hotsort1 {
		padding: 8rpx 0;
		flex: 1 1 178rpx;
		align-items: center;
		display: flex;
		flex-direction: column;
	}

	.hotpicture1 {
		width: 128rpx;
		height: 128rpx;
	}

	.hottext1 {
		margin-top: 25rpx;
		margin-left: 10rpx;

		line-height: 21rpx;
		text-shadow: 0rpx 0rpx 8rpx #00000040;

		font-size: 25rpx;
		font-weight: 500;
		font-family: 字魂天龙行楷;
		letter-spacing: 6rpx;
		line-height: 22rpx;
		color: #77888f;
	}

	.hotsort2 {
		padding: 8rpx 0;
		flex: 1 1 178rpx;
		align-items: center;
		display: flex;
		flex-direction: column;
	}

	.hotpicture2 {
		width: 128rpx;
		height: 128rpx;
	}

	.hottext2 {
		margin-top: 25rpx;
		margin-left: 10rpx;

		line-height: 21rpx;
		text-shadow: 0rpx 0rpx 8rpx #00000040;

		font-size: 25rpx;
		font-weight: 500;
		font-family: 字魂天龙行楷;
		letter-spacing: 6rpx;
		line-height: 22rpx;
		color: #77888f;
	}

	.hotsort3 {
		padding: 8rpx 0;
		flex: 1 1 178rpx;
		align-items: center;
		display: flex;
		flex-direction: column;
	}

	.hotpicture3 {
		width: 128rpx;
		height: 128rpx;
	}

	.hottext3 {
		margin-top: 25rpx;
		margin-left: 10rpx;

		line-height: 21rpx;
		text-shadow: 0rpx 0rpx 8rpx #00000040;

		font-size: 25rpx;
		font-weight: 500;
		font-family: 字魂天龙行楷;
		letter-spacing: 6rpx;
		line-height: 22rpx;
		color: #77888f;
	}

	.hotsort4 {
		padding: 8rpx 0;
		flex: 1 1 178rpx;
		align-items: center;
		display: flex;
		flex-direction: column;
	}

	.hotpicture4 {
		width: 128rpx;
		height: 128rpx;
	}

	.hottext4 {
		margin-top: 25rpx;
		margin-left: 10rpx;

		line-height: 21rpx;
		text-shadow: 0rpx 0rpx 8rpx #00000040;

		font-size: 25rpx;
		font-weight: 500;
		font-family: 字魂天龙行楷;
		letter-spacing: 6rpx;
		line-height: 22rpx;
		color: #77888f;
	}

	.recommendtitle {
		margin-left: 46rpx;
		line-height: 33rpx;
		font-size: 35rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		letter-spacing: 8rpx;
		line-height: 33rpx;
		color: #452933;
		align-self: flex-start;
		margin-top: 50rpx;
	}

	.down {
		margin-top: 37rpx;
		padding: 46rpx 0 133rpx;
		position: relative;
		justify-content: space-between;
		display: flex;
		flex-direction: row;
	}

	.recommend1 {
		padding: 12rpx 0 35rpx;
		background-color: #ffffff;
		border-radius: 26rpx;
		overflow: hidden;
		width: 327rpx;
		height: 354rpx;
		align-items: flex-start;
		display: flex;
		flex-direction: column;
	}

	.recommend1image {
		margin-left: -162rpx;
		border-radius: 16rpx;
		overflow: hidden;
		width: 300rpx;
		height: 217rpx;
		align-items: flex-start;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.recommend1picture {
		margin-left: -19rpx;
		width: 354rpx;
		height: 221rpx;
		flex-shrink: 0;
	}

	.recommend1name {
		margin-left: -142rpx;
		margin-top: 31rpx;
		font-size: 33rpx;
		font-weight: 600;
		line-height: 26rpx;

		font-size: 35rpx;
		font-family: 字魂天龙行楷;
		line-height: 26rpx;
		color: #452933;
	}

	.recommend1price {
		margin-left: 60rpx;
		margin-top: 15rpx;
		color: #452933;
		font-size: 15rpx;
		font-family: 字魂天龙行楷;
		line-height: 14rpx;
	}

	.recommend2 {
		margin-right: -173rpx;
		padding: 12rpx 12rpx 31rpx;
		background-color: #ffffff;
		border-radius: 23rpx;
		overflow: hidden;
		width: 327rpx;
		height: 354rpx;
		display: flex;
		flex-direction: column;
	}

	.recommend2image {
		border-radius: 16rpx;
		overflow: hidden;
		height: 217rpx;
		align-self: stretch;
		align-items: flex-start;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.recommend2picture {
		margin-left: -15rpx;
		width: 331rpx;
		height: 237rpx;
		flex-shrink: 0;
	}

	.recommend2name {
		margin-left: 19rpx;
		font-size: 35rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		line-height: 26rpx;
		color: #452933;
		align-self: flex-start;
		margin-top: 27rpx;
	}

	.recommend2-presentation {
		margin-top: 21rpx;
		padding: 0 18rpx;
		align-self: stretch;
		align-items: center;
		display: flex;
		flex-direction: row;
	}

	.star-samll {
		width: 12rpx;
		height: 12rpx;
	}

	.recommend2-score {
		color: #005161;
		font-size: 11rpx;
		font-family: 字魂天龙行楷;
		line-height: 10rpx;
	}

	.recommend3 {
		position: absolute;
		left: 50%;
		top: 0;
		transform: translateX(-50%);

		padding: 15rpx 15rpx 73rpx;
		background-color: #ffffff;
		border-radius: 31rpx;
		overflow: hidden;
		width: 381rpx;

		display: flex;
		flex-direction: column;
	}

	.recommend3frame {
		border-radius: 19rpx;
		overflow: hidden;
		height: 306rpx;
		align-self: stretch;
		flex-shrink: 0;
	}

	.recommend3name {
		margin-left: 23rpx;
		margin-top: 23rpx;
		color: #452933;
		font-size: 40rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		line-height: 31rpx;
		align-self: flex-start;
	}

	.recommend3picture {
		position: absolute;
		left: 15rpx;
		right: 15rpx;
		top: 15rpx;

		border-radius: 19rpx;
		overflow: hidden;
		width: 350rpx;
		height: 306rpx;

		flex-shrink: 0;
	}
</style>