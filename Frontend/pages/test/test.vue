<template>
	<view class="community">
		<view class="top-area">
			<view class="user-info">
				<image class="portrait" src="/static/community/portrait.png" />

				<view class="top-text-area">
					<view class="text-welcome">
						<text class="welcome">欢 迎 使 用 星 途 指 南！</text>
						<image class="star" src="/static/community/star.png" />
					</view>

					<text class="username">哇哈哈</text>
				</view>
			</view>
			
			<image class="message" src="/static/community/message.png" />
		</view>

		<view class="main-area">
			<view class="main-context">
				<view class="search-box">
					<uni-forms ref="searchRef" :rules="rules" :model="searchForm">
						<uni-forms-item class="input-spotPrefer" name="search">
							<uni-easyinput type="text" primaryColor="#f86140cc" prefixIcon="search"
								placeholderStyle="font-size: 4vw;font-family: 阿里妈妈刀隶体;color: #73848c;"
								v-model="searchForm.search" placeholder="搜索你感兴趣的景点" />
						</uni-forms-item>
					</uni-forms>
				</view>

				<view class="post-show">
					<view class="sort">
						<view class="sort-text" v-for="(item, index) in sort" :key="index" @click="choose(item, index)"
							:class="{ 'selected': index === selectedIndex }">
							{{ item.title }}
						</view>
					</view>

					<view class="posts">
						<view class="main-posts">
							<view class="left-posts">
								<image class="left1" src="/static/community/left1.png" />
								<image class="left2" src="/static/community/left2.png" />
								<image class="left3" src="/static/community/left3.png" />
							</view>

							<view class="right-posts">
								<image class="right1" src="/static/community/right1.png" />
								<image class="right2" src="/static/community/right2.png" />
								<image class="right3" src="/static/community/right3.png" />
							</view>
						</view>
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
				searchForm : {
					search: ''
				},
				sort: [{
						title: '山地'
					},
					{
						title: '露营'
					},
					{
						title: '沙滩'
					},
					{
						title: '森林'
					}
				],
				selectedIndex: 0
			}
		},
		mounted() {
			// 在 mounted 生命周期中设置默认选中的项
			this.choose(this.sort[0], 0);
		},
		methods: {
			choose(item, index) {
				this.selectedIndex = index;
			},
		},
	}
</script>

<style>
	.community {
		padding-top: 110rpx;
		background-color: #fafcfb;
		width: 100%;
		overflow-y: auto;
		overflow-x: hidden;
		height: 100%;
		display: flex;
		flex-direction: column;
	}

	.top-area {
		padding-left: 46rpx;
		align-items: center;
		justify-content: space-between;
		display: flex;
		flex-direction: row;
	}

	.user-info {
		display: flex;
		flex-direction: row;
	}

	.portrait {
		border-radius: 15rpx;
		width: 92rpx;
		height: 92rpx;
	}

	.top-text-area {
		margin-left: 25rpx;
		align-self: flex-start;
		display: flex;
		flex-direction: column;
	}

	.text-welcome {
		align-items: center;
		display: flex;
		flex-direction: row;
	}

	.welcome {
		align-items: center;
		display: flex;
		flex-direction: row;
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
		font-size: 46rpx;
		font-family: 字魂天龙行楷;
		line-height: 37rpx;
		margin-top: 20rpx;
		letter-spacing: 5rpx;
	}

	.message {
		border-radius: 15rpx;
		margin-right: 15rpx;
		width: 77rpx;
		height: 77rpx;
	}

	.main-area {
		height: 1413rpx;
		display: flex;
		flex-direction: column;
		margin-top: 31rpx;
	}

	.main-context {
		padding: 0 60rpx;
		display: flex;
		flex-direction: column;
	}

	.search-box {
		/* padding: 23rpx 27rpx; */
		background-color: #ffffff;
		border-radius: 21rpx;
		border-left: dotted 2rpx #e7eaf0;
		border-right: dotted 2rpx #e7eaf0;
		border-top: dotted 2rpx #e7eaf0;
		border-bottom: dotted 2rpx #e7eaf0;
		align-items: center;
		display: flex;
		flex-direction: row;
	}

	.post-show {
		display: flex;
		flex-direction: column;
		margin-top: 42rpx;
	}

	.sort {
		justify-content: space-between;
		display: flex;
		flex-direction: row;
	}

	/*.sort1-chosen {
		padding: 15rpx 0 33rpx;
		background-color: #fa6d4f;
		border-radius: 192rpx;
		width: 163rpx;
		align-items: center;
		justify-content: flex-start;
		flex-shrink: 0;
		display: flex;
		flex-direction: column;
		color: #ffffff;
	}

	.sort2-text {
		margin-left: 50rpx;
		margin-top: 25rpx;
		line-height: 28rpx;
		font-size: 31rpx;
		font-family: 思源黑体;
		line-height: 28rpx;
		color: #aaaaaa;
		align-self: flex-start;
	}

	.sort3-text {
		margin-left: 102rpx;
		margin-top: 25rpx;
		font-size: 31rpx;
		font-family: 思源黑体;
		line-height: 28rpx;
		color: #aaaaaa;
		align-self: flex-start;
	}

	.sort4-text {
		margin-left: 100rpx;
		margin-top: 25rpx;
		line-height: 28rpx;
		font-size: 31rpx;
		font-family: 思源黑体;
		line-height: 28rpx;
		color: #aaaaaa;
		align-self: flex-start;
	} */

	.sort {
		display: flex;
		background-color: #f0f0f0;
		border-radius: 5vw;
	}

	.selected {
		/* 选中时的底色 */
		background-color: #f7693e;
		/* 选中时的文字颜色 */
		color: #ffffff;
		font-weight: 600;
	}

	.sort-text {
		padding: 20rpx 30rpx 20rpx 30rpx;
		cursor: pointer;
		transition: background-color 0.3s;
		font-family: 思源黑体;
		border-radius: 5vw;
	}

	.posts {
		position: relative;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
		margin-top: 35rpx;
	}

	.main-posts {
		display: flex;
		flex-direction: row;
	}

	.left-posts {
		flex: 1 1 300rpx;
		align-items: center;
		display: flex;
		flex-direction: column;
	}

	.left1 {
		width: 300rpx;
		height: 385rpx;
	}

	.left2 {
		border-radius: 31rpx;
		width: 300rpx;
		height: 288rpx;
		margin-top: 31rpx;
	}

	.left3 {
		border-radius: 31rpx;
		width: 300rpx;
		height: 385rpx;
		margin-top: 31rpx;
	}

	.right-posts {
		flex: 1 1 300rpx;
		align-self: flex-start;
		align-items: center;
		display: flex;
		flex-direction: column;
		margin-left: 31rpx;
	}

	.right1 {
		width: 300rpx;
		height: 288rpx;
	}

	.right2 {
		border-radius: 31rpx;
		width: 300rpx;
		height: 385rpx;
		margin-top: 17rpx;
	}

	.right3 {
		border-radius: 31rpx;
		width: 300rpx;
		height: 385rpx;
		margin-top: 33rpx;
	}

	/* 	.magic {
		position: absolute;
		left: 50%;
		bottom: 95rpx;
		transform: translateX(-50%);
		border-radius: 138rpx;
		width: 92rpx;
		height: 92rpx;
	}

	.magic-shadow {
		position: absolute;
		left: 345rpx;
		right: 269rpx;
		bottom: 75rpx;
		width: 70rpx;
		background-color: #ff93467a;
		border-radius: 0rpx 0rpx 23rpx 23rpx;
		filter: blur(5rpx);
		height: 56rpx;
	}

	.navigation {
		margin-top: -92rpx;
		padding: 0 94rpx 5rpx;
		border-radius: 31rpx;
		background-image: url('/static/community/navigation-block.png');
		background-size: 100% 100%;
		background-repeat: no-repeat;
		position: relative;
		align-self: stretch;
		align-items: flex-end;
		justify-content: space-between;
		display: flex;
		flex-direction: row;
	}

	.home-journey {
		margin-left: 5rpx;
		margin-top: 40rpx;
		width: 200rpx;
		align-self: flex-start;
		justify-content: flex-start;
		display: flex;
		flex-direction: row;
	}

	.home {
		margin-bottom: 5rpx;
		align-items: center;
		width: 70rpx;
		display: flex;
		flex-direction: column;
	}

	.home-image {
		width: 54rpx;
		height: 54rpx;
	}

	.home-text {
		line-height: 22rpx;
		font-size: 23rpx;
		font-family: 字魂天龙行楷;
		color: #77888f;
		margin-top: 7rpx;
	}

	.journey {
		margin-top: 5rpx;
		align-items: center;
		width: 70rpx;
		display: flex;
		flex-direction: column;
		margin-left: 77rpx;
	}

	.journey-image {
		width: 42rpx;
		height: 44rpx;
	}

	.journey-text {
		margin-top: 10rpx;
		font-size: 23rpx;
		font-family: 字魂天龙行楷;
		line-height: 22rpx;
		color: #73848c;
		margin-top: 13rpx;
	}

	.community-chosen {
		width: 70rpx;
		margin-left: 165rpx;
		margin-top: 25rpx;
		align-self: flex-start;
		align-items: flex-start;
		display: flex;
		flex-direction: column;
	}

	.community-image {
		width: 50rpx;
		height: 70rpx;
	}

	.community-text {
		color: #fa6d4f;
		font-size: 23rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		line-height: 22rpx;
		margin-top: 7rpx;
		margin-left: 2rpx;
	}

	.user {
		width: 70rpx;
		margin-left: 77rpx;
		margin-top: 40rpx;
		align-self: center;
		align-items: center;
		display: flex;
		flex-direction: column;
	}

	.user-image {
		width: 46rpx;
		height: 46rpx;
	}

	.user-text {
		font-size: 23rpx;
		font-family: 字魂天龙行楷;
		line-height: 22rpx;
		color: #73848c;
		margin-top: 14rpx;
	} */
</style>