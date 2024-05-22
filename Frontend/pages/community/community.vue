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
					<text class="username">Uthman Saleh</text>
				</view>
			</view>
			<image class="message" src="/static/community/message.png" />
		</view>
		<view class="main-area">
			<view class="main-context">
				<view class="search-box">
					<uni-forms ref="searchRef" :model="searchForm">
						<uni-forms-item class="input-spotPrefer" name="search">
							<uni-easyinput type="text" primaryColor="#f86140cc" prefixIcon="search"
								placeholderStyle="font-size: 4vw;font-family: 阿里妈妈刀隶体;color: #73848c;"
								v-model="searchForm.search" placeholder="搜索你感兴趣的类别" />
						</uni-forms-item>
					</uni-forms>
				</view>
				<view class="post-show">
					<!-- 帖子分类导航 -->
					<scroll-view class="sort" scroll-x="true" scroll-with-animation>
						<view class="sort-text" v-for="(item, sortIndex) in sort" :key="sortIndex"
							@click="choose(sortIndex)" :class="{ 'selected': sortIndex === selectedIndex }">
							{{ item.name }}
						</view>
					</scroll-view>
					<!-- 各类别下的帖子列表 -->
					<view class="posts" :style="'height:'+clientHeight+'px;'">
						<!-- <view class="main-posts"> -->
						<scroll-view scroll-y="true" :style="'height:'+clientHeight+'px;'" @scrolltolower="loadMore()">
							<helang-waterfall-list :status="waterfall.status" :list="waterfall.list"
								:reset="waterfall.reset" @click="onClick" @done="onDone">
								<template>
									<view v-if="waterfall.status == 'await'">
										<view class="load-txt">上拉加载更多</view>
									</view>
									<view v-else-if="waterfall.status == 'loading'">
										<view class="load-txt">加载中</view>
									</view>
									<view v-else-if="waterfall.status == 'success'">
										<view class="load-txt">加载中</view>
									</view>
									<view v-else-if="waterfall.status == 'finish'">
										<view class="load-txt">没有更多了</view>
									</view>
									<view v-else-if="waterfall.status == 'fail'">
										<image class="load-icon" src="../../static/waterfall/fail.png"></image>
										<view class="load-txt">出错了，请刷新重试</view>
									</view>
									<view v-else-if="waterfall.status == 'empty'">
										<image class="load-icon" src="../../static/waterfall/empty.png"></image>
										<view class="load-txt">暂无数据</view>
									</view>
									<view v-else><!-- 别问我为什么要写一个 v-else 的空 view,不写H5平台就会有CSS生效的离谱BUG --></view>
								</template>
							</helang-waterfall-list>
						</scroll-view>
						<!-- </view> -->
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import store from '@/store/index.js'
	import helangWaterfallList from "@/uni_modules/helang-waterfall/components/waterfall/waterfall-list"
	import {
		mapMutations
	} from 'vuex'

	export default {
		components: {
			"helang-waterfall-list": helangWaterfallList
		},
		data() {
			return {
				//搜索感兴趣类别的数据
				searchForm: {
					search: ''
				},
				//可视区域高度
				clientHeight: 0,
				//帖子分类导航对应数据
				sort: [{
						name: '风景'
					},
					{
						name: '美食'
					},
					{
						name: '玩乐'
					},
					{
						name: '文化'
					}
				],
				//选中的索引
				selectedIndex: 0,
				// 异步请求相关
				ajax: {
					//当前部分帖子类别
					type: '',
					// 是否可以加载
					load: true,
					// 每页的请求条件
					size: 6,
					// 页码
					current: 0,
					// 数据列表
					dataList: []
				},
				// 瀑布流组件相关
				waterfall: {
					status: "",
					reset: false,
					list: []
				}
			}
		},
		mounted() {
			// 在 mounted 生命周期中设置默认选中的项
			this.choose(0);
		},
		onReady() {
			// this.getPost();
			//进行可视化区域高度矫正
			uni.getSystemInfo({
				success: (res) => {
					this.clientHeight = res.windowHeight - this.getClientHeight() - 170;
				}
			});
		},
		// 触底触发
		// onReachBottom() {
		// 	this.ajax.current++;
		// 	this.getPost();
		// 	console.log("6666666");
		// },
		// 下拉刷新
		onPullDownRefresh() {
			// 正常情况下接口返回应该很会很快。故意延迟调用，让用户有在刷新的体验感
			setTimeout(() => {
				this.ajax.current = 0;
				this.ajax.load = true;
				this.getPost();
			}, 800);
		},
		methods: {
			...mapMutations(['savePostInfo']),
			choose(index) {
				if (this.sortIndex === index) {
					return;
				}
				this.selectedIndex = index;
				this.ajax.type = this.sort[this.selectedIndex].name;
				this.ajax.current = 0;
				this.ajax.load = true;
				this.getPost();
			},
			// 瀑布流组件点击事件
			onClick(data, index, tag) {
				this.savePostInfo(data);
				console.log(data);
				uni.navigateTo({
					url: '/pages/post/post'
				})
				// let direction = {
				// 	"left": '左',
				// 	"right": '右'
				// }
				// uni.showToast({
				// 	title: `${direction[tag]}侧列表第${index+1}个被点击`,
				// 	icon: 'none'
				// })
			},
			// 瀑布流组件渲染完成
			onDone() {
				// 设置组件为 非重置，这行代码保留不删即可
				this.waterfall.reset = false;

				// 恢复 getList 方法的调用
				this.ajax.load = true;

				// 设置组件状态为 等待加载
				this.waterfall.status = 'await';
			},
			// 获取数据
			async getPost() {
				if (!this.ajax.load) {
					return;
				}
				this.ajax.load = false;

				// 设置状态为加载中
				this.waterfall.status = 'loading';

				// 请求数据，调用 getPost 方法
				try {
					const response = await this.$api.post.getPostByPage(this.ajax, store.state.isAuth);
					// 处理返回的数据
					if (response.data.code === 103) {
						// 弹出获取成功提示
						console.log('数据获取成功！')
						// 第一页数据执行以下代码
						if (this.ajax.current == 0) {
							// 关闭下拉
							uni.stopPullDownRefresh();

							// 设置组件状态为 重置，可供下拉刷新这类需要重置列表功能时使用
							this.waterfall.reset = true;
						}

						// 数据无效时处理
						if (!response.data.data || response.data.data.length < 1) {
							// 设置组件为 加载结束 状态
							this.waterfall.status = 'finish';
							return;
						}
						// // 下载并存储头像图片
						// for (let post of response.data.data) {
						// 	if (post.userHeadPortraitUrl) {
						// 		let url = "http://8.130.121.208:8080" + post.userHeadPortraitUrl;
						// 		let filename = 'portrait_' + post.postId + '.jpg';
						// 		// let filepath = `${uni.getSystemInfoSync().userDataPath}/${filename}`;  // 获取本地文件的路径
						// 		let filepath = `@/userData/${filename}`; // 获取本地文件的路径

						// 		//检查本地是否已经有这张图片
						// 		uni.getFileInfo({
						// 			filePath: filepath,
						// 			success: (info) => {
						// 				// 如果本地已经有这张图片，直接使用本地地址
						// 				post.userHeadPortraitUrl = filepath;
						// 				console.log('333');
						// 			},
						// 			fail: (err) => {
						// 				// 如果本地没有这张图片，从服务器下载
						// 				console.log('222');
						// 				uni.downloadFile({
						// 					url: url,
						// 					success: (res) => {
						// 						if (res.statusCode === 200) {
						// 							// 下载成功，使用本地地址
						// 							uni.saveFile({
						// 								tempFilePath: res.tempFilePath,
						// 								filePath: filepath, // 自定义路径
						// 								success: function(saveRes) {
						// 									console.log('图片保存成功，地址为' + saveRes.savedFilePath);
						// 									post.userHeadPortraitUrl = saveRes.savedFilePath;;
						// 								}
						// 							});

						// 						}
						// 					}
						// 				});
						// 			}
						// 		});
						// 	}

						// 	//下载并存储封面图片
						// 	if (post.imagesUrls && post.imagesUrls.length > 0) {
						// 		let url = "http://8.130.121.208:8080" + post.imagesUrls[0];
						// 		let filename = 'cover_' + post.postId + '.jpg';
						// 		let filepath = `${uni.env.USER_DATA_PATH}/${filename}`; // 获取本地文件的路径

						// 		// 检查本地是否已经有这张图片
						// 		uni.getFileInfo({
						// 			filePath: filepath,
						// 			success: (info) => {
						// 				// 如果本地已经有这张图片，直接使用本地地址
						// 				post.imagesUrls[0] = filepath;
						// 			},
						// 			fail: (err) => {
						// 				// 如果本地没有这张图片，从服务器下载
						// 				uni.downloadFile({
						// 					url: url,
						// 					filePath: filepath,
						// 					success: (res) => {
						// 						if (res.statusCode === 200) {
						// 							// 下载成功，使用本地地址
						// 							post.imagesUrls[0] = res.filePath;
						// 						}
						// 					}
						// 				});
						// 			}
						// 		});
						// 	}
						// }

						// 将数据赋值给瀑布流 list 属性
						this.waterfall.list = response.data.data;
						// console.log(this.waterfall.list);
						// 设置组件为 加载成功 状态，此时瀑布流组件开始计算当前数据的布局
						this.waterfall.status = 'success';

						// 缓存当前数据给其他需要该数据的功能使用
						if (this.ajax.current == 0) {
							this.ajax.dataList = response.data.data;
						} else {
							this.ajax.dataList = [...this.ajax.dataList, ...response.data.data];
						}
						// 记录本次数据长度，意义请看 done 事件的回调
						this.ajax.dataCount = response.data.data.length || 0;
					}
				} catch (error) {
					console.error("Error fetching data: ", error);
					// 处理请求失败的情况
					// 设置组件状态为 加载失败 状态
					this.waterfall.status = 'fail';
				}
			},
			//兼容可视区域高度获取
			getClientHeight() {
				const res = uni.getSystemInfoSync();
				const system = res.platform;
				if (system === 'ios') {
					return 44 + res.statusBarHeight;
				} else if (system === 'android') {
					return 48 + res.statusBarHeight;
				} else {
					return 0;
				}
			},
			//上拉加载更多
			loadMore() {
				this.ajax.current++;
				this.getPost();
				console.log("6666666");
			}
		}
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
		align-items: center;
		display: flex;
		flex-direction: row;
	}

	.input-spotPrefer {
		width: 83.5vw;
	}

	.post-show {
		display: flex;
		flex-direction: column;
		margin-top: 2rpx;
	}

	.sort {
		margin-left: 3vw;
		width: 78vw;
		white-space: nowrap;
		justify-content: space-between;
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
		display: inline-block;
		padding: 20rpx 40rpx 20rpx 40rpx;
		cursor: pointer;
		transition: background-color 0.5s;
		font-family: 思源黑体;
		border-radius: 5vw;
	}

	.posts {
		height: 100vw;
		position: relative;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
		margin-top: 35rpx;
	}

	.main-posts {
		width: 100%;
		padding-bottom: 20rpx;
		margin-bottom: 30rpx;
		border-radius: $borderRadius;
		box-shadow: 0rpx 3rpx 6rpx rgba(0, 46, 37, 0.08);
	}

	.post-image {
		width: 100%;
		border-radius: $borderRadius $borderRadius 0 0;
	}

	.left-posts {
		flex: 1 1 300rpx;
		align-items: center;
		display: flex;
		flex-direction: column;
	}

	.right-posts {
		flex: 1 1 300rpx;
		align-self: flex-start;
		align-items: center;
		display: flex;
		flex-direction: column;
		margin-left: 31rpx;
	}

	.load-txt {
		padding: 0 0 20rpx 0;
		text-align: center;
		color: #999;
		font-size: 24rpx;
	}

	.load-icon {
		width: 300rpx;
		height: 300rpx;
		margin: 0 auto 20rpx auto;
		display: block;
	}

	.status-change {
		position: fixed;
		right: 10rpx;
		top: 60%;
		width: 80rpx;
		height: 80rpx;
		z-index: 100;
		font-size: 24rpx;
		border-radius: 50%;
		background-color: #0089ff;
		color: #fff;
		line-height: 1;
		opacity: .33;

		display: flex;
		flex-direction: row;
		flex-wrap: nowrap;
		justify-content: center;
		align-items: center;
		align-content: center;
	}
</style>