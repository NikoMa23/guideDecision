# sliver_server
use for sliver_storm 

# 返回信息
## Code
WrongUserOrPassword = 301;
Unauthenticated = 302;
IllegalPhoneNum = 303;
IllegalPassword = 304;
WrongAuthCode = 305;
AuthCodeOutdated = 306;
IllegalAction = 307;
TokenOutdated = 308;
Error = 309;
IllegalNickname = 310;
WaitForAuthCode = 201;
LoginOk = 101;
RegisterOk = 102;
AccessOk = 103;

## 返回的结构
private int code;
private String message;
private T data;


用户名就是手机号,只能是手机号格式才能注册

# 接口:

## 登录接口
1. /user/login :Post

2. json请求格式
   1. username 用户名
   2. password 密码

3. 会返回一个token,访问其他资源接口时需要把token放在请求的HEAD中,命名为token

## 测试接口
1. /test :Get

2. 测试资源访问接口,需要携带token才会有返回值

## 尝试注册接口
1. /user/attemptRegister :Post

2. json请求格式
   1. username 用户名
   2. password 密码

3. 发送请求后会要求等待验证码

## 验证码码注册接口
1. /user/CodeRegister :Post

2. json请求格式:
   1. username 用户名
   2. password 密码
   3. authCode 验证码目前为111

3. 返回注册结果和token.

## 尝试注销接口
1. /user/attemptLogout :Post

2. json请求格式:
   1. username 用户名
   2. password 密码

3. 发送请求后会要求等待验证码

## 验证码注销接口
1. /user/codeLogout :Post

2. json请求格式:
   1. username 用户名
   2. password 密码
   3. authCode 验证码目前为222

3. 返回注销结果

## 用户反馈的接口
1. /user/feedback Post
2. json格式
   1. feedback 反馈的信息 目前只能是文本

## 用户修改密码接口
1. /user/changePassword Post
2. json格式
   1. username 用户名
   2. password 原来的密码
   3. changedPassword 改变后的密码

## 用户修改昵称的接口
1. /user/changeNickname Post
2. json格式
   1. username 用户名
   2. changedNickname 改变后的昵称

## 获取用户信息的接口
1. /user/details Post
2. json格式
   1. userId 需要查询的用户id 如果没有userId则返回本人的用户信息

## 用户上传头像的接口
1. /user/uploadHeadPortrait Post
2. 接收文件的代码:
   @PostMapping("/post/uploadImages")
   ResponseResult<Object> uploadPostImages(HttpServletRequest request, @RequestParam("file") MultipartFile file){
   if (file.isEmpty()) {
   return new ResponseResult<>(ResponseResult.Error, "上传图片为空", null);
   }
   //获取请求信息
   String token = request.getHeader("token");
   Claims claims;
   try {
   claims = JwtUtils.parseJwtToken(token);
   } catch (ExpiredJwtException e){
   return new ResponseResult<>(ResponseResult.TokenOutdated,"token已过期",null);
   }
   int userId = Integer.parseInt(claims.getId());
   return postService.uploadPostContentImages(userId,file);
   }
3. 上传参照格式:
~~~html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image Upload</title>
</head>
<body>
    <h2>Image Upload</h2>
    <form action="http://your-server-url/your-endpoint" method="post" enctype="multipart/form-data">
        <label for="image">Select Image:</label>
        <input type="file" name="file" id="image" accept="image/*">
        <br>
        <input type="submit" value="Upload">
    </form>
</body>
</html>
~~~

# 帖子相关接口

## 帖子的图片上传接口
1. /post/uploadedImages :Post

2. 一次只能上传一个图片

3. 上传参照格式:
~~~html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image Upload</title>
</head>
<body>
    <h2>Image Upload</h2>
    <form action="http://your-server-url/your-endpoint" method="post" enctype="multipart/form-data">
        <label for="image">Select Image:</label>
        <input type="file" name="file" id="image" accept="image/*">
        <br>
        <input type="submit" value="Upload">
    </form>
</body>
</html>
~~~
4. 接收文件的代码:
   @PostMapping("/post/uploadImages")
   ResponseResult<Object> uploadPostImages(HttpServletRequest request, @RequestParam("file") MultipartFile file){
   if (file.isEmpty()) {
   return new ResponseResult<>(ResponseResult.Error, "上传图片为空", null);
   }
   //获取请求信息
   String token = request.getHeader("token");
   Claims claims;
   try {
   claims = JwtUtils.parseJwtToken(token);
   } catch (ExpiredJwtException e){
   return new ResponseResult<>(ResponseResult.TokenOutdated,"token已过期",null);
   }
   int userId = Integer.parseInt(claims.getId());
   return postService.uploadPostContentImages(userId,file);
   }

5. 返回上传结果

## 上传帖子接口  {如果上传图片后没有上传帖子,会有问题,建议上传图片和帖子一次性上传}
1. /post/upload :Post
2. json上传格式:
title        帖子标题
contentText  帖子内容{文本}
3. 返回上传结果


## 获取某个用户的帖子,没有userId则返回自己的帖子
1. /post/getUserPost Post
2. json上传格式:
userId  需要查找的用户的id
3. 返回结果

## 对帖子进行评论接口
1. /post/uploadRemark  Post
2. json上传格式:
postId 帖子的id
contentText 评论的内容
3. 目前只支持文本评论,不加图片,懒

## 获取帖子的评论接口
1. /post/getRemark Post
2. json格式:
postId 帖子的id

## 对帖子进行点赞的接口
1. /post/like Post
2. json格式
postId

## 对帖子进行取消点赞的接口
1. /post/noLike Post
2. json格式
postId 帖子的id
 
## 对帖子进行收藏的接口
1. /post/userCollect Post
2. json格式
postId 帖子的id

## 查询用户的收藏的接口 
1. /post/userCollect Get

