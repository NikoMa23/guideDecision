<template>
	<view class="container">
		<view class="top-area">
			<view class="top-area-left">
				<image class="back" src="/static/post/back.png" @click="back"/>

				<view class="user-info">
					<image class="head-portrait" :src="dataList.userHeadPortraitUrl" />
					<text class="username">{{dataList.userNickname}}</text>
				</view>

				<image class="more" src="/static/post/more.png" />
			</view>
		</view>

		<swiper class="swiper" :current="currentSwiper" @change="swiperChange">
			<swiper-item v-for="(item, index) in imgUrls" :key="index">
				<image :src="item" class="travel-image" mode="aspectFill"></image>
			</swiper-item>
		</swiper>

		<view class="dots">
			<text v-for="(item, index) in imgUrls" :key="index" class="dot"
				:class="{active: currentSwiper === index}"></text>
		</view>

		<view class="main-area">
			<view class="context">
				<text class="attraction-name">{{dataList.title}}</text><br />
				<!-- <image src="http://8.130.121.208:8080/user/headPortrait/$27$$20240220_035149$Java____.png"></image> -->
				<text class="attraction-introduction">{{dataList.contentText}}</text>
			</view>

			<view class="bottom">
				<view class="input-area">
					<image class="input-icon" src="/static/post/input-icon.png"></image>
					<input type="text" placeholder="说点什么吧!" class="input" />
				</view>

				<view class="interaction">
					<image class="collect" src="/static/post/collect.png"></image>
					<image class="like" src="/static/post/like.png"></image>
					<image class="share" src="/static/post/share.png"></image>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import store from '@/store/index.js'
	export default {
		data() {
			return {
				currentSwiper: 0,
				imgUrls: [],
				dataList: []
			}
		},
		onLoad() {
			this.imgUrls = store.state.postInfo.imagesUrls;
			this.dataList = store.state.postInfo;
		},
		methods: {
			back() {
				uni.switchTab({
					url: "/pages/community/community"
				})
			},
			swiperChange(e) {
				this.currentSwiper = e.detail.current;
			}
		}
	}
</script>

<style>
	.container {
		padding-top: 100rpx;
		background-color: #ffffff;
		width: 100%;
		overflow-y: auto;
		overflow-x: hidden;
		height: 100%;

		display: flex;
		flex-direction: column;
	}

	.top-area {
		padding-left: 3vw;
		padding-right: 6vw;
		align-items: center;
		justify-content: space-between;
		display: flex;
		flex-direction: row;
	}

	.top-area-left {
		align-items: center;
		display: flex;
		flex-direction: row;
	}

	.back {
		margin-left: 2vw;
		width: 9vw;
		height: 10vw;
	}

	.user-info {
		display: flex;
		flex-direction: row;
		margin-left: 6vw;
	}

	.head-portrait {
		border-radius: 50%;
		width: 10vw;
		height: 10vw;
		flex-shrink: 0;
	}

	.username {
		margin-top: 2.3vw;
		color: #8d8d8d;
		font-size: 4.267vw;
		font-family: 思源黑体;
		line-height: 3.925vw;

		align-self: flex-start;
		margin-left: 4vw;
	}

	.more {
		width: 6vw;
		height: 6vw;
		margin-left: 53vw;
	}

	.swiper {
		margin-top: 5vw;
		width: 100%;
		height: 300px;
	}

	.travel-image {
		width: 100%;
		height: 100%;
		align-self: center;
	}

	.dots {
		display: flex;
		justify-content: center;
		margin: 3.5vw 0;
	}

	.dot {
		width: 8px;
		height: 8px;
		border-radius: 50%;
		background: #FA947D;
		margin: 0 5px;
	}

	.dot.active {
		background: #FF2F00;
	}

	.main-area {
		margin-top: 6.4vw;
		padding-left: 6.4vw;
		padding-right: 6.133vw;
		align-self: stretch;
		display: flex;
		flex-direction: column;
	}

	.context {
		display: flex;
		flex-direction: column;
	}

	.attraction-name {
		color: #121212;
		font-size: 5.6vw;
		font-family: 思源黑体;
		line-height: 5.152vw;
		align-self: flex-start;
	}

	.attraction-introduction {
		color: #12121299;
		font-size: 3.733vw;
		font-family: 思源黑体;
		line-height: 6.4vw;
		align-self: stretch;
	}

	.bottom {
		position: fixed;
		bottom: 20rpx;
		align-items: center;
		display: flex;
		flex-direction: row;
	}

	.input-area {
		width: 55vw;
		padding: 0 0 0.5vw 0;
		border-radius: 6.933vw;
		height: 10.667vw;
		border: 0.7vw dotted #000000;
		flex: 1 1 0;
		justify-content: center;
		display: flex;
		flex-direction: row;
	}

	.input-icon {
		width: 4vw;
		height: 4vw;
		align-self: center;
		margin-left: 12vw;
	}

	.input {
		color: #000000;
		font-size: 3.2vw;
		font-family: 思源黑体;
		font-weight: 300;
		line-height: 2.907vw;
		align-self: flex-start;
		margin-left: 4vw;
		margin-top: 3.5vw;
	}

	.interaction {
		flex-shrink: 0;
		display: flex;
		flex-direction: row;
		margin-left: 3vw;
	}

	.collect {
		width: 6.933vw;
		height: 6.667vw;
	}

	.like {
		width: 6.4vw;
		height: 6.133vw;
		margin-top: 0.3vw;
		margin-left: 5vw;
	}

	.share {
		width: 6.933vw;
		height: 6.667vw;
		margin-left: 5vw;
	}
</style>