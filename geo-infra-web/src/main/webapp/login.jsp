<%@ include file="WEB-INF/view/main/base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="${path}/static/css/login.css">
</head>
<body>
<div class="htmleaf-container">
	<div class="wrapper">
		<div class="container">
			<h1>Welcome</h1>
			
			<form class="form">
				<input type="text" placeholder="Username">
				<input type="password" placeholder="Password">
				<button type="submit" id="login-button">Login</button>
			</form>
		</div>
		
		<ul class="bg-bubbles">
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>
	</div>
</div>

<script src="${path}/static/js/jquery-3.2.1.min.js"></script>
<script>
$('#login-button').click(function (event) {
	event.preventDefault();
	$('form').fadeOut(500);
	
	$.ajax({
	    type: "POST",
	    contentType: "application/json",
	    dataType: "json",
	    url: '${path}/main/logincheck',
	    data: JSON.stringify({ rkey:null, username:'test', password:'1', name:'测试',roles:[] }),
	    success: function (response) {
	    	alert(response.success);
	    	window.location.href = '${path}/main/index';
	    	
	    	//$('.wrapper').addClass('form-success');
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
	
});
</script>

<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';color:#000000">
<h1>数据管理系统</h1>
</div>
</body>
</html>
