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
<jsp:include page="/jspFix/user/ajax-css-js.jsp"/>
<link rel="stylesheet" type="text/css" href="css/user/zclg.css">
<script type="text/javascript" src="js/common/pcSelest.js"></script>
<script type="text/javascript" src="js/common/pinyin.js"></script>
<script type="text/javascript" src="jsp/center/register/zc.js"></script>
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
	<form action="register.hm?method=doRegister" method="post" id="form" name="form">
		<input type="hidden" id="C_PRO" name="C_PRO">
		<input type="hidden" id="C_CITY" name="C_CITY">
		<input type="hidden" id="C_PRO_ABBR" name="C_PRO_ABBR">
		<input type="hidden" id="C_CITY_ABBR" name="C_CITY_ABBR">
		<div class="bdcnt">
			<div class="bdcnt_c">
				<ul>
					<li>
						<h1 class="bdname">
							<span>*</span>企业所在地：
						</h1>
						<div id="pcDiv" class="bdint"></div>
						<p id="pro_city" class="bdts"></p>
					</li>

					<li>
						<h1 class="bdname">
							<span>*</span>企业名称：
						</h1>
						<div class="bdint">
							<input id="C_FACTORY_NAME" name="C_FACTORY_NAME" class="int01" type="text" onblur="factoryClick();">
						</div>
						<p id="factory_verify" class="bdts">
						</p>
					</li>
					<li>
			          <h1 class="bdname"><span>*</span>电子邮箱：</h1>
			          <div class="bdint">
			            <input id="C_MAIL" name="C_MAIL" class="int01" type="text" maxlength="100" onblur="mailClick();">
			          </div>
			          <p id="mail_verify" class="bdts">邮箱是找回密码的重要条件，请谨慎填写</p>
			        </li>
					<li>
						<h1 class="bdname">
							<span>*</span>昵称：
						</h1>
						<div class="bdint">
							<input id="C_NAME"  name="C_NAME" class="int01" type="text" onblur="nameClick();">
						</div>
						<p id="name_verify">
						</p>
					</li>
					<li>
						<h1 class="bdname">
							<span>*</span>用户名：
						</h1>
						<div class="bdint">
							<input id="C_LOGINNAME" name="C_LOGINNAME" class="int01" type="text" onblur="loginNmaeClick();">
						</div>
						<p id="loginname_verify" class="bdts">
							用于登陆使用，请注意保管好您的用户名
						</p>
					</li>
					<li>
						<h1 class="bdname">
							<span>*</span>登陆密码：
						</h1>
						<div class="bdint">
							<input id="C_PASSWORD" name="C_PASSWORD" class="int01" type="password" onblur="passwordClick();">
						</div>
						<p id="loginpwd_verify" class="bdts">
							6-20位字符，建议由字母数字组合
						</p>
					</li>
					<li>
						<h1 class="bdname">
							<span>*</span>确认密码：
						</h1>
						<div class="bdint">
							<input id="C_PASSWORD_TO" name="C_PASSWORD_TO" class="int01" type="password" onblur="toPasswordClick();">
						</div>
						<p id="pwd_verify" class="bdts">
							请再次输入密码
						</p>
					</li>
					<li>
						<h1 class="bdname">
							<span>*</span>验证码：
						</h1>
						<div class="bdint">
							<input id="CAPTCHA" name="CAPTCHA" class="int02" type="text" maxlength="4" onblur="captchaClick();" >
							<div class="bsyzm">
								<img src="#" id="captchaCode" name="captchaCode" alt="读取中" title="换一张" onclick="showCaptcha()"/>
							</div>
							<input type="hidden" id="captcha_date" value="">
						</div>
						<p id="captcha_verify"></p>
					</li>
					<li>
						<label for="san_01" class="bddx">
							<input type="checkbox" id="san_01" name="name" onclick="check()" checked>
							我已阅读并同意
							<a href="#">《预制构件平台网服务条款》</a>和
							<a href="#">《用户注册协议协议》</a>
						</label>
					</li>
					<li>
						<input class="bdbtn" id="submit_save" type="button" onclick="saveBtn()" value="立即注册">
					</li>
				</ul>
      			<div class="c"></div>
			</div>
		</div>
	</form>
	<jsp:include page="/bottom.jsp" />
</div>
</body>
</html>
