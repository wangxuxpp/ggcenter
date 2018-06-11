<!doctype html>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html>
<head>
<title>卫德PC构件平台--会员中心--企业信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="No-Cache" />     
<meta http-equiv="Cache-Control" content="No-Cache" />     
<meta http-equiv="Expires" content="0" />  
<meta http-equiv="X-UA-Compatible" content="IE=10" />
<meta http-equiv="windows-Target" content="_top"/>
<meta name="keywords"    content="卫德数字化工厂系统，数字化工厂"/>
<meta name="description" content="卫德数字化工厂系统"/>
<meta name="Copyright"   content="本页版权归卫德住工科技所有。All Rights Reserved"/>
<jsp:include page="/jspFix/user/base-css-js.jsp"/>
<jsp:include page="/jspFix/user/ajax-css-js.jsp"/>
<link rel="stylesheet" type="text/css" href="css/user/u_main.css">
<script type="text/javascript" src="js/jquery/jquery.selest.js"></script>
</head>
<body>
<div class="wrapper">
	<jsp:include page="/top.jsp" />
	<jsp:include page="/jsp/center/leaguer/leaguer.jsp" />
	<div class="ht_cont">
		<jsp:include page="/jsp/center/leaguer/account.jsp">
			<jsp:param name="permit" value="true" />
		</jsp:include>
		<div class="r_br">
			<div class="rbr_tit">
				<h1>企业信息 &gt; 企业认证</h1>
			</div>
			<div class="rbr_cnt">
				<div class="step">
					<div class="step_cnt bz01">
						<h1>1.认证需知</h1>
						<h2>2.资料提交</h2>
						<h3>3.提交完成</h3>
					</div>
				</div>
				<div class="tybd">
					<div class="tybd_c">
						<ul>
							<li>
								<div class="rzxz">
									<p>
										本协议由您与平台共同缔结，本协议具有合同效力。
										<br />
										<br />
										一、定义
										<br />
										用户，包含注册用户和非注册用户。注册用户是指通过www.平台.com完成全部注册程序后，使用平台服务的用户。非注册用户是指未进行注册、直接登录www.平台com或通过其他网站进入www.平台com使用平台服务的用户。
										协议方，本协议中协议双方合称协议方。北京五八信息技术有限公司及其关联公司、信息发布平台在协议中统称为"平台"，通过平台（www.平台.com）为您提供服务。
										<br />
										二、协议的效力
										<br />
										1、需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容需知内容。
									</p>
								</div>
							</li>
							<li>
								<div class="bdbtn">
									<input class="tjbtn" type="button" value=" 下一步" onclick="javascrtpt:window.location.href='permit.do?method=enterpriseData'">
									<input class="qxbtn" type="button" value=" 返回" onclick="javascrtpt:window.location.href='permit.do?method=permit'">
								</div>
							</li>
						</ul>
					</div>
				</div>
				<div class="c"></div>
			</div>
		</div>
	</div>
	<div class="c"></div>
	<jsp:include page="/bottom.jsp" />
</div>
</body>
</html>
