<!doctype html>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<title>卫德PC构件平台--免费注册</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="No-Cache" />     
<meta http-equiv="Cache-Control" content="No-Cache" />     
<meta http-equiv="Expires" content="0" />  
<meta http-equiv="X-UA-Compatible" content="IE=10" />
<meta http-equiv="windows-Target" content="_top"/>
<meta name="keywords"    content="卫德数字化工厂系统，数字化工厂"/>
<meta name="description" content="卫德数字化工厂系统"/>
<meta name="Copyright"   content="本页版权归卫德住工科技所有。All Rights Reserved"/>
<jsp:include page="/jspFix/user/baseselect-css-js.jsp"/>
<link rel="stylesheet" type="text/css" href="css/user/zclg.css">
</head>
<body>
<div class="wrapper">
	<jsp:include page="/top.jsp" />
	<div class="header">
		<div class="her_cnt">
			<div class="logo">
				<a href="index.jsp"><img src="images/logo.gif" title="logo" alt="logo">
				</a>
				<h1>
					账号注册
				</h1>
			</div>
			<div class="ts_xx">
				<a href="login.hm?method=showLogin">登录</a>
				<h1>
					我已经注册，现在就
				</h1>				
			</div>
		</div>
	</div>
	<div class="bdcnt">
		<div class="bdcnt_c">
			<div class="bdcnt_cg">
				<h1>恭喜您，已经注册成功！</h1>
				<h2>您的企业编号为：<span>${cCode } </span></h2>
				<h3>我们向您的注册邮箱发送了一封验证邮件，请登陆您的邮箱<span>${cMail } </span>查收。</h3>
				<input class="cgbtn" type="button" value="请完善详细信息" >
				<p>出于安全考虑，验证邮件的有效期为12小时，请您及时查收。<br/>
				    验证邮件会有1-10分钟的延迟，请稍后片刻。如长时间未收到邮件，请点击<a href="#">重新发送</a>。<br/>
				    如有疑问，欢迎咨询<a href="#">客服中心</a>。</p>
			</div>
		</div>
	</div>
	<jsp:include page="/bottom.jsp" />
</div>
</body>
</html>
