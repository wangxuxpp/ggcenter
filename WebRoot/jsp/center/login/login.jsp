<!doctype html>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<title>卫德PC构件平台--登陆</title>
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
<jsp:include page="/jspFix/user/ajax-css-js.jsp"/>
<link rel="stylesheet" type="text/css" href="css/user/zclg.css">
<script type="text/javascript" src="jsp/center/login/int_txt.js"></script>
<script type="text/javascript" src="jsp/center/login/login.js"></script>
</head>
<body>
<div class="wrapper">
	<jsp:include page="/top.jsp" />
	<div class="header">
		<div class="her_cnt">
			<div class="logo">
				<a href="index.jsp"><img src="images/logo.gif" title="logo" alt="logo"> </a>
				<h1>
					会员登陆
				</h1>
			</div>
			<div class="ts_xx">
				<a href="register.hm?method=showRegister">注册</a>
				<h1>
					我已经注册，现在就
				</h1>				
			</div>
		</div>
	</div>
	<div class="login">
		<div class="login_c">
			<div class="login_l"></div>
			<div class="login_r">
				<div class="dlinfo" id="showMessage"></div>
				<form action="login.hm?method=doLogin" id="form" name="form" method="post" onsubmit="return false">
					<ul>
						<li>
							<div class="dlico">
								<img src="images/ico_09.gif" alt="">
							</div>
							<input type="text" id="factoryName" name="factoryName" class="dlint" value="" />
							<label for="factoryName" id="for-p02">
								企业编号/企业名称
							</label>
						</li>
						<li>
							<div class="dlico">
								<img src="images/ico_10.gif" alt="">
							</div>
							<input type="text" id="loginName" name="loginName" class="dlint" value="" />
							<label for="loginName" id="for-p01">
								用户名
							</label>
						</li>
						<li>
							<div class="dlico">
								<img src="images/ico_11.gif" alt="">
							</div>
							<input type="password" id="password" name="password" class="dlint" value=""/>
							<label for="password" id="for-p">
								密&nbsp;&nbsp;码
							</label>
						</li>
						<li id="captcha_li" style="display: none;">
							<div class="dlico">
								<img src="images/ico_13.gif" alt="">
							</div>
							<input type="text" id="captcha" name="captcha" class="dlint" value="" style="width: 100px;" />
							<img src="#" id="captchaCode" name="captchaCode" alt="读取中" title="换一张" onclick="showCaptcha()"/>
							<label for="captcha" id="for-c">
								验&nbsp;证&nbsp;码
							</label>
							<input type="hidden" id="captcha_date" value="">
						</li>
						<li class="dldx">
							<label for="san_01">
								<input type="checkbox" id="san_01" name="name">
								两周内记住密码
							</label>
							<a href="#" onclick="alert('请等待')">忘记密码？</a>
						</li>
						<li class="dlbtn">
							<input type="button" value="登陆" onclick="doLogin()">
						</li>
					</ul>
				</form>
				<script type="text/javascript">
				search('password', 'for-p');
				search('loginName', 'for-p01');
				search('factoryName', 'for-p02');
				search('captcha', 'for-c');
				</script>
			</div>
			<div class="c"></div>
		</div>
	</div>
	<jsp:include page="/bottom.jsp" />
</div>
</body>
</html>