<%@ include file="view/base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <title>登录</title>
  <link rel="stylesheet" href="${path}/static/layui/css/layui.css" media="all">
  <script src="${path}/static/jquery-3.2.1.min.js"></script>
  <script src="${path}/static/layui/layui.js" charset="utf-8"></script>
<body>
<div id="main" style="width:350px;height:100px;padding:10px;">
<form id="form1" class="layui-form" action="">
      <div class="layui-form-item">
        <label class="layui-form-label">账号:</label>
        <div class="layui-input-block">
          <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入账号" class="layui-input">
        </div>
      </div>
      <div class="layui-form-item">
        <label class="layui-form-label">密码:</label>
        <div class="layui-input-block">
          <input type="text" name="username" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
      </div>
</form>
</div>
<script type="text/javascript">
layui.use('layer', function(){ //独立版的layer无需执行这一句
	  var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
	  
	  var loginWin =  function(){
		      var that = this; 
		      //多窗口模式，层叠置顶
		      layer.open({
		        type: 1 //此处以iframe举例
		        ,title: '登录'
		        ,area: ['400px', '220px']
		        ,shade: 0
		        ,maxmin: true
		        ,offset:  '150px'
		        ,content: $('#main')
		        ,btn: ['登录', '重置'] //只是为了演示
		        ,yes: function(){
		        	$.ajax({
		                type: "POST",
		                dataType: "json",
		                url: '${path}/user/get',
		                data: Json.stringify({ username:'admin',password:'1' }),
		                success: function (response) {
		                	alert(response);
		                    /* if(response.re == 1){
		                        alert(response.msg)
		                    }
		                    else{
		                        alert(response.msg);
		                    } */
		                },
		                error: function(data) {
		                    alert("网络错误");
		                }
		            });
		        }
		        ,btn2: function(){
		        	$('#form1').reset();
		        }
		        ,zIndex: layer.zIndex
		      });
	  };
	  
	  
	  loginWin();
		  
});
</script>
</body>
</html>
