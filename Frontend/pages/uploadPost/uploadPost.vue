<template>
	<view class="content">
		<view class="topArea">
			<!-- <image src="/static/uploadPost/title_decoration.png"></image> -->
			<view class="page-title">记录一下</view>
		</view>

		<view class="container">
			<!-- 帖子主题的部分 -->
			<view class="titleArea-txt">帖子主题</view>
			<!-- 帖子主题输入框 -->
			<textarea placeholder="在这里输入帖子的主题~~" placeholder-style="color: #77888f;" @blur="inputBlur_title"
				:focus="inputFocus_title" :auto-height="true" maxlength="100" v-model="form.title"
				class="post-title"></textarea>
			<view class="post-line"></view>

			<!-- 帖子类别的部分 -->
			<view class="typeArea-txt">帖子类别</view>
			<!-- 帖子类别的下拉框 -->
			<uni-data-select class="data_select" v-model="form.type" :localdata="range" :clear="false"
				@change="change"></uni-data-select>

			<!-- 帖子内容的部分 -->
			<view class="contentArea-txt">帖子内容</view>
			<!-- 帖子内容输入框 -->
			<textarea placeholder="在这里丰富帖子的内容叭~~" placeholder-style="color: #77888f;" @blur="inputBlur_content"
				:focus="inputFocus_content" :auto-height="true" maxlength="1500" v-model="form.contentText"
				class="post-txt"></textarea>

			<!-- 上传图片or视频 -->
			<view class="img-list">
				<view v-for="(item,index) in form.imageList" :key='index' class="img-list-box">
					<image v-if="!form.video" :src="item" class="img-item" @tap="imgTypeSelect(item)"></image>
					<video v-else :src="item" @longpress="videoTypeSelect(item)"></video>
				</view>
				<view v-if="form.imageList.length < 3 && !form.video" class="icon-camera" @tap="selectType">
					<uni-icons type="camera-filled" size="27" color=#D3D4D6></uni-icons>
				</view>
			</view>
		</view>
		<!-- 选择板块 -->

		<!-- 底部确定按钮 -->
		<view @click="clickCreate" class="yue-base-button">
			<view>确定发布~</view>
		</view>
	</view>
</template>

<script>
	import store from '@/store/index.js'

	export default {
		data() {
			return {
				range: [{
						value: 0,
						text: "风景"
					},
					{
						value: 1,
						text: "美食"
					},
					{
						value: 2,
						text: "玩乐"
					},
					{
						value: 3,
						text: "文化"
					}
				],
				// 默认输入框获得焦点
				inputFocus_title: false,
				inputFocus_content: false,
				form: {
					title: '',
					contentText: '',
					type: '',
					imageList: [],
					video: ''
				}
			}
		},

		methods: {
			change(e) {
				this.form.type = this.range[e].text;
				console.log("e:", this.form.type);
			},
			// 选择媒体类型
			selectType() {
				let that = this
				let itemL = that.form.imageList.length != 0 ? !that.form.imageList.video ? ['拍照', '从相册选择照片'] : '' : ['拍照',
					'从相册选择照片', '从相册选择视频'
				]
				uni.showActionSheet({
					itemList: itemL,
					success: function(res) {
						if (res.tapIndex == 0) {
							uni.chooseImage({
								sourceType: ['camera'], // 拍照选择
								success: function(res) {
									that.form.imageList.push(res.tempFilePaths);
								}
							});
						}
						if (res.tapIndex == 1) {
							uni.chooseImage({
								count: 3 - that.form.imageList.length,
								sourceType: ['album'], //从相册选择
								success: function(res) {
									res.tempFilePaths.forEach(url => {
										that.form.imageList.push(url);
										// 发给oss 待写
									})
								}
							});
						}
						if (res.tapIndex == 2) {
							uni.chooseVideo({
								sourceType: ['album'], // 从相册选择视频
								success: function(res) {
									if (res.size > 20971520) {
										uni.showToast({
											title: "视频文件过大",
											duration: 2000
										});
										return;
									}
									that.form.video = true;
									that.form.imageList.push(res.tempFilePaths);
								}
							});
						}
					}
				});
			},

			// 图片状态选择
			imgTypeSelect(item) {
				let that = this
				uni.showActionSheet({
					itemList: ['预览', '删除'],
					success: function(res) {
						if (res.tapIndex == 0) {
							uni.previewImage({
								current: item,
								urls: that.form.imageList
							})
						}
						if (res.tapIndex == 1) {
							let index = that.form.imageList.findIndex(url => url === item);
							if (index !== -1) {
								that.form.imageList.splice(index, 1);
							}
						}
					}
				});
			},

			// 视频状态选择
			videoTypeSelect() {
				uni.showActionSheet({
					itemList: ['删除'],
					success: function(res) {
						if (res.tapIndex == 0) {
							let index = that.form.imageList.findIndex(url => url === item);
							if (index !== -1) {
								that.form.imageList.splice(index, 1);
							}
						}
					}
				});
			},

			// 文字内容-标题
			inputBlur_title(event) {
				this.inputCursor_title = event.detail.cursor;
				this.inputFocus_title = false;
			},

			// 文字内容-内容
			inputBlur_content(event) {
				this.inputCursor_content = event.detail.cursor;
				this.inputFocus_content = false;
			},
			
			async uploadFile(filePath) {
			    return new Promise((resolve, reject) => {
			        uni.uploadFile({
			            url: 'http://39.101.67.214:8080/post/uploadImages',
			            filePath: filePath,
			            name: 'file',
			            header: {
			                'Auth': store.state.isAuth,
			                'token': store.state.token
			            },
			            success: (uploadFileRes) => {
			                console.log(uploadFileRes.data);
			                resolve(uploadFileRes); // resolve 上传成功
			            },
			            fail: (error) => {
			                reject(error); // reject 上传失败
			            }
			        });
			    });
			},
			
			async uploadAllFiles() {
			    for (var i = 0; i < this.form.imageList.length; i++) {
			        await this.uploadFile(this.form.imageList[i]);
			    }
			},
			
			async publishPost() {
			    try {
			        await this.uploadAllFiles(); // 等待所有文件上传完成
			
			        const response = await this.$api.post.upLoadPost(this.form, store.state.isAuth);
			        if (response.statusCode === 200) {
			            uni.showToast({
			                title: '发布成功',
			                duration: 1500,
			                mask: true
			            });
			            setTimeout(() => {
			                uni.switchTab({
			                    url: '/pages/mainpage/mainpage'
			                });
			            }, 1500);
			        }
			    } catch (error) {
			        console.error('文件上传或发布失败', error);
			        // 处理上传失败或发布失败的情况
			    }
			},
			
			// 发布
			async clickCreate() {
			    if (!this.form.title) {
			    	uni.showToast({
			    		title: "请输入帖子的主题噢",
			    		icon: "none"
			    	})
			    	return;
			    }
			    if (!this.form.type) {
			    	uni.showToast({
			    		title: "请选择帖子的类别噢",
			    		icon: "none"
			    	})
			    	return;
			    }
			    if (!this.form.contentText) {
			    	uni.showToast({
			    		title: "请输入帖子的内容噢",
			    		icon: "none"
			    	})
			    	return;
			    }
			
			    await this.publishPost();
			}

			// // 发布
			// async clickCreate() {
			// 	if (!this.form.title) {
			// 		uni.showToast({
			// 			title: "请输入帖子的主题噢",
			// 			icon: "none"
			// 		})
			// 		return;
			// 	}
			// 	if (!this.form.type) {
			// 		uni.showToast({
			// 			title: "请选择帖子的类别噢",
			// 			icon: "none"
			// 		})
			// 		return;
			// 	}
			// 	if (!this.form.contentText) {
			// 		uni.showToast({
			// 			title: "请输入帖子的内容噢",
			// 			icon: "none"
			// 		})
			// 		return;
			// 	}

			// 	let successCtn = 0;
			// 	// var response = null;
			// 	// for (var i = 0; i < this.form.imageList.length; i++) {
			// 	// 	response = await this.$api.post.uploadImages(this.form.imageList[i], store.state.isAuth);
			// 	// 	if (response.statusCode === 200) {
			// 	// 		console.log(`第${i+1}张图片上传成功！`);
			// 	// 	}
			// 	// }
			// 	for (var i = 0; i < this.form.imageList.length; i++) {
			// 		uni.uploadFile({
			// 			url: 'http://39.101.67.214:8080/post/uploadImages',
			// 			filePath: this.form.imageList[i],
			// 			name: 'file',
			// 			header: {
			// 				'Auth': store.state.isAuth,
			// 				'token': store.state.token
			// 			},
			// 			success: (uploadFileRes) => {
			// 				console.log(uploadFileRes.data);
			// 				successCtn++;
			// 				console.log(successCtn)
			// 			}
			// 		});
			// 	}

			// 	// while (true) {
			// 	// 	if (successCtn == this.form.imageList.length) {
			// 			const response = await this.$api.post.upLoadPost(this.form, store.state.isAuth);
			// 			if (response.statusCode === 200) {
			// 				uni.showToast({
			// 					title: '发布成功',
			// 					duration: 1500,
			// 					mask: true
			// 				});
			// 				setTimeout(() => {
			// 					uni.switchTab({
			// 						url: '/pages/mainpage/mainpage'
			// 					});
			// 				}, 1500);
			// 				successCtn = 0;
			// 			}
			// 	}
			// };
			// }
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		height: 100%;
		background-color: #FFFFFF;
		border-radius: 30rpx 30rpx 0rpx 0rpx;
	}

	.topArea {
		margin-top: 10vw;
		margin-left: 10vw;
		width: 600rpx;
		height: 200rpx;

		background-image: url('/static/uploadPost/title_decoration.png');
		position: relative;
		background-size: 600rpx;
		/* 图片等比例缩放，保持图片完全覆盖容器 */
		background-position: center;
		/* 图片居中显示 */
		background-repeat: no-repeat;
		/* 不重复平铺背景图片 */


		.page-title {
			position: absolute;
			margin-left: 32vw;
			margin-top: 5vw;
			color: #d12806;
			font-size: 50rpx;
			font-weight: 600;
		}
	}

	.container {
		padding: 40rpx 40rpx;
		overflow: hidden;
		// border: 5rpx dotted #f7693e;
	}

	.titleArea-txt {
		margin-top: 2vw;
		color: #d14710;
		font-size: 35rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		letter-spacing: 2rpx;
	}

	.typeArea-txt {
		margin-top: 4vw;
		color: #d14710;
		font-size: 35rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		letter-spacing: 2rpx;
	}

	.data_select {
		margin-top: 5vw;
	}

	.contentArea-txt {
		margin-top: 6vw;
		color: #d14710;
		font-size: 35rpx;
		font-weight: 600;
		font-family: 字魂天龙行楷;
		letter-spacing: 2rpx;
	}

	.post-title {
		margin-top: 5vw;
		width: 100%;
		min-height: 60rpx;
		line-height: 35rpx;
	}

	.post-line {
		margin-top: 2vw;
		width: 89vw;
		height: 1rpx;
		flex-shrink: 0;
		background-color: #c4c4c480;
		align-items: flex-start;
		justify-content: flex-start;
		display: flex;
		flex-direction: column;
	}

	.post-txt {
		margin-top: 5vw;
		width: 100%;
		min-height: 170rpx;
		line-height: 35rpx;
	}

	/* 选择位置 */
	.choose-location {
		display: inline-flex;
		align-items: center;
		background-color: #eee;
		font-size: 28rpx;
		border-radius: 100rpx;
		padding: 10rpx 20rpx;
		margin-left: 5rpx;
		line-height: 1;
		color: #616161;

		.txt {
			margin-left: 10rpx;
		}
	}

	.yue-base-button {
		position: fixed;
		display: flex;
		align-items: center;
		justify-content: center;
		bottom: 50rpx;
		width: 100%;
		padding: 40rpx 0;
		z-index: 3;
	}

	.yue-base-button view {
		width: 560rpx;
		text-align: center;
		height: 96rpx;
		line-height: 96rpx;
		border-radius: 96rpx;
		font-size: 32rpx;
		font-weight: 400;
		color: #FFFFFF;
		background: #f7693e;
	}

	// 相机icon
	.icon-camera {
		display: flex;
		justify-content: center;
		align-items: center;
		width: 210rpx;
		height: 210rpx;
		border-radius: 6rpx;
		margin: 5rpx 0rpx 0rpx 5rpx;
		background-color: #f4f5f7;
	}

	// 媒体列表
	.img-list {
		display: flex;
		flex-wrap: wrap;
		margin-top: 60rpx;
	}

	.img-list-box {
		display: flex;
		justify-content: center;
		align-items: center;
		position: relative;
	}

	.img-item {
		width: 210rpx;
		height: 210rpx;
		margin: 5rpx;
		border-radius: 6rpx
	}
</style>