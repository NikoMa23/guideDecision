<template>
	<view class="AI-page">
		<image class="back" src="/static/AI/back.png" @click="back" />
		<text class="title-text">AI导游</text>
		<image class="long-img" src="/static/AI/long.png" />
		
		<uni-forms ref="cityRef" :model="cityForm">
			<uni-forms-item class="input-field-cityname" name="plot">
				<uni-easyinput type="text" primaryColor="#f86140cc"
					placeholderStyle="font-size: 4vw;font-family: 阿里妈妈刀隶体;color: #73848c;" v-model="cityForm.plot"
					placeholder="在这里输入要介绍的景点名称" />
			</uni-forms-item>
		</uni-forms>
		
		<view class="function">
			<!-- <image class="translation-img" src="/static/AI/translation.png" />
			<image class="bofang-img" src="/static/AI/bofang.png" />
			<image class="sound-img" src="/static/AI/sound.png" /> -->
			<view class="setting-function">
				<uni-icons name="gear" type="gear" size="15vw" color="#805b93"></uni-icons>
				<!-- <uni-icons name="gear-filled" type="gear-filled" size="15vw" color="#805b93"
					></uni-icons> -->
			</view>

			<view class="bofang-function">
				<uni-icons v-if="musicBofang" name="bofang" type="sound" size="15vw" color="#805b93"
					@click="bjMusicClick"></uni-icons>
				<uni-icons v-else name="bofang-filled" type="sound-filled" size="15vw" color="#805b93"
					@click="bjMusicClick"></uni-icons>
			</view>

			<view class="chat-function">
				<uni-icons v-if="chatPosition" name="chat" type="chat" size="15vw" color="#805b93"
					@click="clickSearch()"></uni-icons>
				<uni-icons v-else name="chat-filled" type="chat-filled" size="15vw" color="#805b93"
					@click="clickSearch()"></uni-icons>
			</view>
		</view>
		
		<view class="description">{{datalist.response}}</view>
	</view>
</template>

<script>
	// 定义变量
	const innerAudioContext = uni.createInnerAudioContext();
	innerAudioContext.loop = true; //循环播放
	// innerAudioContext.src = '/static/try.wav';

	export default {
		data() {
			return {
				datalist: "",
				cityForm: {
					plot: "",
					type: '四川话',
					speaker: ''
				},
				musicBofang: true,
				chatPosition: true,
			}
		},
		methods: {
			back() {
				uni.switchTab({
					url: "/pages/mainpage/mainpage"
				})
			},
			bjMusicClick: function() {
				console.log("成功进入函数");
				if (this.musicBofang) {
					console.log('播放')
					const timout = setTimeout(() => {
						clearTimeout(timout)
						innerAudioContext.play()
					}, 500)
				} else {
					console.log('暂停')
					innerAudioContext.pause();
				}
				this.musicBofang = !this.musicBofang;
			},
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
				const response = await this.$api.user.submitAI(this.cityForm);
				if (response.data.code == 200) {
					//弹出验证码发送成功提示
					uni.showToast({
						title: '获取文本描述成功！',
						icon: 'none'
					});
					//保存文本和音频文件路径
					// let data = JSON.parse(response.data.data)
					// console.log(response)
					this.datalist = response.data.data;
					this.$set(this.datalist, response, this.datalist.response);
					this.requestWav();
				};
			},
			async requestWav() {
				var wavUrl = 'http://47.109.133.25:8860/get_wav/' + this.datalist.file_id;
				console.log(wavUrl);
				var name = this.datalist.file_id;

				var dtask = plus.downloader.createDownload(wavUrl, {
					method: 'GET',
					filename: "_downloads/" + name //利用保存路径，实现下载文件的重命名
				}, function(d, status) {
					console.log(status)
					//d为下载的文件对象
					if (status == 200) {
						//下载成功,d.filename是文件在保存在本地的相对路径，使用下面的API可转为平台绝对路径
						var fileSaveUrl = plus.io.convertLocalFileSystemURL(d.filename);
						innerAudioContext.src = fileSaveUrl;
						console.log(fileSaveUrl);
						// plus.runtime.openFile(d.filename); //选择软件打开文件
					} else {
						//下载失败
						plus.downloader.clear(); //清除下载任务
						console.log('下载失败');
					}
				})
				dtask.start();
			}
		}
	}
</script>

<style>
	.AI-page {
		padding: 5vw 0vw 25vw;
		background-color: #fafafa;
		width: 100%;
		overflow-y: auto;
		overflow-x: hidden;
		height: 100%;
		display: flex;
		flex-direction: column;
	}

	.back {
		width: 10vw;
		height: 10vw;
		margin-top: 7vw;
		margin-left: 4vw;
		align-self: flex-start;
	}

	.title-text {
		color: #292f32;
		font-size: 8vw;
		font-family: Poppins;
		font-weight: 700;
		line-height: 10vw;
		align-self: center;
	}

	.long-img {
		margin-top: 5vw;
		width: 70vw;
		height: 70vw;
		align-self: center;
	}

	.function {
		align-self: stretch;
		margin: 10vw 9vw 0;
		justify-content: space-between;
		display: flex;
		flex-direction: row;
	}

	.translation-img {
		filter: drop-shadow(0vw 0vw 0vw #18b9dd, 0vw 4vw 5vw #236c7c);
		width: 20vw;
		height: 20vw;
	}

	.bofang-img {
		filter: drop-shadow(0vw 0vw 0vw #254435, 0vw 4vw 5vw #070d0a);
		width: 20vw;
		height: 20vw;
	}

	.sound-img {
		filter: drop-shadow(0vw 0vw 0vw #254435, 0vw 4vw 5vw #070d0a);
		width: 20vw;
		height: 20vw;
	}

	.description {
		margin: 10vw 10vw 0 12vw;
		letter-spacing: 3rpx;
		color: #12121299;
		font-size: 4vw;
		font-family: 思源黑体;
		line-height: 6vw;
		align-self: stretch;
		word-wrap: break-word;
		word-break: break-all;
		white-space: pre-line;

	}

	.input-field-cityname {
		position: absolute;
		left: 18vw;
		top: 10vw;
		transform: translateY(-50%);
		width: 65vw;
		height: 8vw;
		align-self: center;
	}

	.setting-function {
		margin-left: 2vw;
	}

	.bofang-function {
		margin-left: 15vw;
	}

	.chat-function {
		margin-left: 15vw;
	}

</style>