<template>
	<view class="scenicspot-page">
		<view class="scenicspot">
			<view class="title">
				<view class="buju">
					<view class="goback" @click="goback()">
						<image class="back" src="/static/scenicspot/back.png" />
					</view>
					<view class="wenzikuangjia">
						<view class="wenzijieshao"><text class="text">景点详情</text></view>
					</view>
					<view class="shoucang">
						<image class="star" src="/static/scenicspot/star.png" />
					</view>
				</view>
			</view>
			<!-- <image class="picture" src="/static/scenicspot/scenery.png" /> -->
			<image class="picture" :src="getImgSrc(datalist.pictureUrl)" />
			<!-- <image class="picture" src="http://8.130.121.208:8080"></image> -->
			<!-- <view class="scenicname">
				<text class="name">南圣托里尼岛</text>
				<view class="money">
					<text class="price">¥4380</text>
					<text class="average">/人</text>
				</view>
				<view class="pingfen">
					<image class="star2" src="/static/scenicspot/star.png" />
					<text class="points">4.5</text>
					<text class="liulanliang">( 15K+ 浏览 )</text>
				</view>
			</view> -->
			<view class="description">
				<view class="dtitle">
					<text class="detitle">旅程描述</text>
					<image class="go" src="/static/scenicspot/go.png" />
				</view>
				<text class="concipt">
					<!-- 圣托里尼是在希腊大陆东南200公里的爱琴海上由一群火山组成的岛环，圣托里尼岛环上最大的一个岛也叫圣托里尼岛，别名锡拉岛。圣托里.. -->
					{{datalist.introduction}}
				</text>
			</view>
			<text class="photo">景点照片</text>
			<view class="show">
				<image class="picture1" src="/static/scenicspot/picture1.png" />
				<image class="picture1" src="/static/scenicspot/picture2.png" />
				<image class="picture1" src="/static/scenicspot/picture3.png" />

				<view class="etc">
					<text class="etc1">18+</text>
				</view>
			</view>
			<view class="reserve">
				<text class="yuding">到这儿去！</text>
			</view><br>
		</view>
	</view>
</template>

<script>
	export default {
		// onLoad: function(option) { //option为object类型，会序列化上个页面传递的参数
		// 	console.log(option.id); //打印出上个页面传递的参数。
		// 	console.log(option.name); //打印出上个页面传递的参数。
		// 	this.description = option.name;
		// 	console.log(this.description)
		// },

		data() {
			return {
				description: "",
				"datalist": [],
			}
		},

		onLoad: function(options) {
			this.datalist = JSON.parse(options.listData)
			console.log(this.datalist);
		},

		methods: {
			goback() {
				uni.switchTab({
					url: "/pages/mainpage/mainpage"
				})
			}
		},
	}
</script>

<style>
	.scenicspot-page {
		padding-top: 80rpx;
	}

	.scenicspot {
		background-color: #ffffff;
		width: 100%;
		overflow-y: auto;
		overflow-x: hidden;
		height: 100%;

		justify-content: flex-start;

		display: flex;
		flex-direction: column;
	}

	.title {
		padding: 30rpx 6.9rpx 40rpx;

		position: relative;
		align-self: stretch;
		align-items: center;
		justify-content: center;
		display: flex;
		flex-direction: row;
	}

	.goback {
		width: 146rpx;
		position: absolute;
		left: 14rpx;
		bottom: 52rpx;


		align-items: center;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.back {
		top: 4.5vw;
		width: 60rpx;
		height: 66rpx;
	}

	.wenzikuangjia {
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.wenzijieshao {
		color: #121212;
		font-size: 36rpx;
		font-family: 思源黑体;
		font-weight: 700;
		line-height: 33.8rpx;
	}

	.shoucang {
		position: absolute;
		right: 24rpx;
		bottom: 50rpx;

		width: 146rpx;
		align-items: center;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;

	}

	.star {
		top: 3.5vw;
		width: 48rpx;
		height: 46rpx;
	}

	.picture {
		margin-top: 15rpx;
		border-radius: 48rpx;
		width: 654rpx;
		height: 440rpx;

		align-self: center;

	}

	.scenicname {
		margin: 62rpx 48rpx 0;
		align-self: stretch;
		display: flex;
		flex-direction: column;

	}

	.name {
		color: #121212;
		font-size: 40rpx;
		font-family: 思源黑体;
		line-height: 37rpx;
		align-self: flex-start;

	}

	.money {
		margin-top: 22rpx;
		padding: 0 2rpx;
		align-self: stretch;
		align-items: flex-start;
		display: flex;
		flex-direction: row;
	}

	.price {
		color: #0069ef;
		line-height: 24rpx;

		font-size: 32rpx;
		font-family: 思源黑体;
		line-height: 30rpx;
		color: #121212;
	}

	.average {
		margin-left: 10rpx;
		margin-top: 16rpx;
		line-height: 24rpx;
		font-size: 24rpx;
		font-family: 思源黑体;
		line-height: 24rpx;
		color: #aaaaaa;

	}

	.pingfen {
		margin-top: 22rpx;
		padding: 0 4rpx;
		align-self: stretch;
		display: flex;
		flex-direction: row;
	}

	.star2 {
		width: 24rpx;
		height: 24rpx;
		align-self: center;
	}

	.points {
		margin-left: 14rpx;
		line-height: 18rpx;
		font-size: 24rpx;
		font-family: 思源黑体;
		line-height: 24rpx;
		color: #aaaaaa;
		align-self: flex-start;
	}

	.liulanliang {
		margin-left: 14rpx;
		line-height: 25rpx;
		font-size: 24rpx;
		font-family: 思源黑体;
		line-height: 24rpx;
		color: #aaaaaa;
		align-self: center;
	}

	.description {
		margin-top: 44rpx;
		width: 642rpx;
		align-self: center;
		display: flex;
		flex-direction: column;
	}

	.dtitle {
		align-items: center;
		justify-content: space-between;
		display: flex;
		flex-direction: row;
	}

	.detitle {
		line-height: 29rpx;
		font-size: 32rpx;
		font-family: 思源黑体;
		line-height: 30rpx;
		color: #121212;

	}

	.go {
		margin-right: 7rpx;
		width: 16rpx;
		height: 30rpx;
	}

	.concipt {
		margin-top: 14rpx;
		color: #12121299;
		line-height: 48rpx;
		font-size: 28rpx;
		font-family: 思源黑体;
	}

	.photo {
		margin-left: 48rpx;
		margin-top: 46rpx;
		font-size: 32rpx;
		font-family: 思源黑体;
		line-height: 30rpx;
		color: #121212;
		align-self: flex-start;
	}

	.show {
		align-self: stretch;
		margin: 44rpx 50rpx 0 48rpx;
		justify-content: space-between;
		display: flex;
		flex-direction: row;
	}

	.picture1 {
		border-radius: 24rpx;
		width: 136rpx;
		height: 136rpx;
	}

	.picture2 {
		border-radius: 24rpx;
		width: 136rpx;
		height: 136rpx;
	}

	.picture3 {
		border-radius: 24rpx;
		width: 136rpx;
		height: 136rpx;
	}

	.etc {
		padding: 46rpx 0 69rpx;
		background-color: #5c5c5c;
		border-radius: 24rpx;
		width: 136rpx;
		height: 26rpx;
		align-items: center;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.etc1 {
		color: #ffffff;
		line-height: 21rpx;
		font-size: 28rpx;
		font-family: 思源黑体;

	}

	.reserve {
		margin: 112rpx 48rpx 0;
		padding: 28rpx 10rpx 40rpx;
		background-color: #F67D62;
		border-radius: 28rpx;
		align-self: stretch;
		align-items: center;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.yuding {
		margin-left: 1.5vw;
		color: #ffffff;
		font-weight: 700;
		line-height: 30rpx;
		font-size: 34rpx;
		font-family: 思源黑体;
		line-height: 30rpx;
		color: #ffffff;
	}
</style>
