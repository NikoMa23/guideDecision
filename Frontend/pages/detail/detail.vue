<template>
	<view>
		<view class="goback" @click="goback()">
			<image class="back" src="/static/scenicspot/back.png" />
		</view>
		<uni-list class="travel-command" v-for="(item,index) in trylist" :key="index">
			<uni-list-item class="travel-command-item" :title="item.spotsName" :note="item.longitude" showArrow
				thumb="/static/xingcheng/jiantou.png" thumb-size="lg" right-text="详情" :clickable="true"
				@click="goscenicspot(item.id)">
			</uni-list-item>
		</uni-list>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				"trylist": []
			};
		},
		onLoad: function(options) {
			this.trylist = JSON.parse(options.listData)
			console.log(this.trylist)
		},
		methods: {
			goscenicspot(id) {
				uni.navigateTo({
					url: '/pages/scenicspot/scenicspot?listData=' + JSON.stringify(this.trylist[id - 1])
				})
			},
			goback() {
				uni.switchTab({
					url: "/pages/mainpage/mainpage"
				})
			}
		}
	}
</script>

<style>
	.goback {
		width: 20vw;
		position: absolute;
		left: 1vw;
		top: 1vw;


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

	.travel-command {
		top: 20vw;
	}
</style>