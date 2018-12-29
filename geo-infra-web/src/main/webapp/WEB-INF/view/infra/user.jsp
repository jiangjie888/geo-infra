<%@ include file="../main/base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="${path}/static/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function getList(){
	$.ajax({
	    type: "POST",
	    dataType: "json",
	    url: '${path}/user/getlist',
	    data: JSON.stringify({ rkey:null, username:'test', password:'1', name:'测试' }),
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
function save(){
	$.ajax({
	    type: "POST",
	    contentType: "application/json",
	    dataType: "json",
	    url: '${path}/user/save',
	    data: JSON.stringify({ rkey:null, username:'test', password:'1', name:'测试',roles:[] }),
	    //data: { rkey:null, username:'test', password:'1', name:'测试',roles:[{code:'role001'},{code:'role002'}] },
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
function remove(){
	$.ajax({
	    type: "POST",
	    dataType: "json",
	    url: '${path}/user/delete',
	    data: JSON.stringify({ rkey:null }),
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
function dubbo(){
	$.ajax({
	    type: "POST",
	    contentType: "application/json",
	    dataType: "json",
	    url: '${path}/user/dubbocust',
	    data: JSON.stringify({ rkey:null, username:'test', password:'1', name:'测试',roles:[] }),
	    success: function (response) {
	    	var re = response;
			alert(response.msg)
	    },
	    error: function(data) {
	        alert("网络错误");
	    }
	});
}
</script>
</head>
<body>
<input type="button" value="List" onclick="getList()" />
<input type="button" value="Save" onclick="save()" />
<input type="button" value="Remove" onclick="remove()" />
<input type="button" value="Dubbo" onclick="dubbo()" />
</body>
</html>