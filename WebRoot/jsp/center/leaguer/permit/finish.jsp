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
<script type="text/javascript" src="js/common/pinyin.js"></script>
<script type="text/javascript" src="js/common/pcSelest.js"></script>
<script type="text/javascript" src="js/common/util.js"></script>
<script type="text/javascript" src="js/jquery/jquery.selest.js"></script>
<script type="text/javascript" src="jsp/center/leaguer/factory/factory.js"></script>
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
					<div class="step_cnt bz03">
						<h1>1.认证需知</h1>
						<h2>2.资料提交</h2>
						<h3>3.提交完成</h3>
					</div>
				</div>
				<div class="tybd">
					<div class="tybd_c">
						<ul>
							<li>
								<div class="rztjcg">
									<h1>尊敬的用户，您申请【PC构件厂企业认证】的资料已经提交成功。</h1>
									<h2>我们将在24小时内完成审核。</h2>
									<h3>请点击返回进行其他操作，如有疑问请咨询<a href="#">客服中心</a>。</h3>
								</div>
							</li>
							<li>
								<div class="bdbtn">
									<input class="tjbtn" type="button" value="返回" onclick="javascrtpt:window.location.href='permit.do?method=permit'">
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
