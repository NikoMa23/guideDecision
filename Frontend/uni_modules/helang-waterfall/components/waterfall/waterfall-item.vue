<template>
	<view class="waterfall-item-container">
		<view class="waterfall-item" @tap="onTap">
			<image :src="params.imagesUrls[0]" mode="widthFix" @load="emitHeight" @error="emitHeight">
			</image>
			<!-- <image :src="getImgSrc(params.imagesUrls[0])" mode="widthFix" @load="emitHeight" @error="emitHeight">
			</image> -->
			<!-- <image src="https://picsum.photos/200/300" mode="widthFix" @load="emitHeight" @error="emitHeight"> -->
			</image>
			<view class="content">
				<view class="title">{{params.title}}</view>
				<view class="user-info">
					<image class="head-portrait" :src="params.userHeadPortraitUrl || '/static/userpage/defaultHeadPortrait.jpg'" @load="emitHeight"
						@error="handleError"></image>
					<!-- <image class="head-portrait" :src="getImgSrc(params.userHeadPortraitUrl)" @load="emitHeight"
						@error="emitHeight"></image> -->
					<text class="username">{{params.userNickname}}</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name: "helangWaterfallItem",
		options: {
			virtualHost: true
		},
		props: {
			params: {
				type: Object,
				default () {
					return {}
				}
			},
			tag: {
				type: String | Number,
				default: ''
			},
			index: {
				type: Number,
				default: -1
			}
		},
		data() {
			return {
				// 做图片缓存处理，初始化图片路径为空
				imgSrc: ''
			};
		},
		methods: {
			// 发出组件高度信息，在此处可以区分正确和错误的加载，给予错误的提示图片
			emitHeight(e) {
				const query = uni.createSelectorQuery().in(this);
				query.select('.waterfall-item-container').boundingClientRect(data => {
					let height = Math.floor(data.height);
					this.$emit("height", height, this.$props.tag);
				}).exec();
			},
			onTap() {
				this.$emit("click", this.$props.index, this.$props.tag);
			},
			handleError(e) {
				e.target.src = '/static/userpage/defaultHeadPortrait.jpg';
			}
		}
	}
</script>

<style lang="scss" scoped>
	.waterfall-item {
		padding: 16rpx;
		background-color: #fff;
		border-radius: 4px;
		font-size: 28rpx;
		color: #666;

		image {
			display: block;
			width: 100%;
			// 默认设置一个图片的大约值
			height: 350rpx;
		}

		.content {
			margin-top: 16rpx;
			display: flex;
			flex-direction: column;

			.title {}

			.user-info {
				margin-top: 3vw;
				display: flex;
				flex-direction: row;

				.head-portrait {
					border-radius: 50%;
					width: 6vw;
					height: 6vw;
				}

				.username {
					margin-top: 1.5vw;
					color: #8d8d8d;
					font-size: 3vw;
					font-family: 思源黑体;
					line-height: 2.5vw;

					align-self: flex-start;
					margin-left: 4.5vw;
				}
			}


		}
	}
</style>