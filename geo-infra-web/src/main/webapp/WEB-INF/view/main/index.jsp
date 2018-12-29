<%@ include file="base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台管理平台</title>
    <link rel="stylesheet" href="${path}/static/plugins/layui/css/layui.css" media="all" type="text/css" >
    <%-- <link rel="stylesheet" href="${path}/static/plugins/layui/font/iconfont/css" type="text/css" > --%>
    <link rel="stylesheet" href="${path}/static/plugins/layui/css/font-awesome.min.css" type="text/css" >
    <!-- <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css"> -->
    <link rel="stylesheet" href="${path}/static/plugins/BeginnerAdmin/build/css/app.css" media="all" type="text/css" >
</head>

<body>
    <div class="layui-layout layui-layout-admin kit-layout-admin">
        <div class="layui-header">
            <div class="layui-logo">后台管理平台</div>
            <div class="layui-logo kit-logo-mobile">K</div>
            <ul class="layui-nav layui-layout-left kit-nav">
                <li class="layui-nav-item"><a href="javascript:;">控制台</a></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">其它系统</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">邮件管理</a></dd>
                        <dd><a href="javascript:;">消息管理</a></dd>
                        <dd><a href="javascript:;">授权管理</a></dd>
                    </dl>
                </li>
            </ul>
            <ul class="layui-nav layui-layout-right kit-nav">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <img src="http://m.zhengjinfan.cn/images/0.jpg" class="layui-nav-img"> Van
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">基本资料</a></dd>
                        <dd><a href="javascript:;">安全设置</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="${path}/main/loginout"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a></li>
            </ul>
        </div>

        <div class="layui-side layui-bg-black kit-side">
            <div class="layui-side-scroll">
                <div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
                <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
                <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>
                    <li class="layui-nav-item">
                        <a class="" href="javascript:;"><i class="fa fa-plug" aria-hidden="true"></i><span> 系统管理</span></a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" kit-target data-options="{url:'${path}/user/index',icon:'&#xe6c6;',title:'用户管理',id:'1'}">
                                    <i class="layui-icon">&#xe6c6;</i><span> 用户管理</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" data-url="role/index" data-icon="fa-user" data-title="角色管理" kit-target data-id='2'><i class="fa fa-user" aria-hidden="true"></i><span> 角色管理</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" data-url="nav.html" data-icon="&#xe628;" data-title="导航栏" kit-target data-id='3'><i class="layui-icon">&#xe628;</i><span> 导航栏</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" data-url="list4.html" data-icon="&#xe614;" data-title="列表四" kit-target data-id='4'><i class="layui-icon">&#xe614;</i><span> 列表四</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" kit-target data-options="{url:'https://www.baidu.com',icon:'&#xe658;',title:'百度一下',id:'5'}"><i class="layui-icon">&#xe658;</i><span> 百度一下</span></a>
                            </dd>
                        </dl>
                    </li>

                </ul>
            </div>
        </div>
         <div class="layui-body" id="container">
            内容主体区域
            <div style="padding: 15px;">主体内容加载中,请稍等...</div>
        </div> 

       <!--  <div class="layui-footer">
            底部固定区域
            2017 &copy;
            <a href="http://kit.zhengjinfan.cn/">kit.zhengjinfan.cn/</a> MIT license

        </div> -->
    </div>
    <script src="${path}/static/plugins/layui/layui.js" charset="utf-8"></script>
    <script>
        var message;
        layui.config({
            base: '${path}/static/plugins/BeginnerAdmin/build/js/'
        }).use(['app', 'message'], function() {
            var app = layui.app,
                $ = layui.jquery,
                layer = layui.layer;
            //将message设置为全局以便子页面调用
            message = layui.message;
            //主入口
            app.set({
                type: 'iframe'
            }).init();
            $('#pay').on('click', function() {
                layer.open({
                    title: false,
                    type: 1,
                    content: '<img src="/static/plugins/BeginnerAdmin/build/images/pay.png" />',
                    area: ['500px', '250px'],
                    shadeClose: true
                });
            });
        });
        
        
        /* $('#logout').click(function (event) {
        	event.preventDefault();
        	$.ajax({
        	    type: "POST",
        	    contentType: "application/json",
        	    dataType: "json",
        	    url: '${path}/main/loginout',
        	    //data: JSON.stringify({ rkey:null, username:'test', password:'1', name:'测试',roles:[] }),
        	    success: function (response) {
        	    	
        	    },
        	    error: function(data) {
        	        alert("网络错误");
        	    }
        	});
        }); */
    </script>
</body>

</html>