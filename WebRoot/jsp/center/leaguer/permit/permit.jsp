<!doctype html>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<div class="rzlist">
					<div class="rzcnt">
						<div class="rzcnt_to gjc_q">
							<div class="rzphot"></div>
							<h1>未认证</h1>
							<h2>已通过认证</h2>
						</div>
						<div class="rzcnt_txt">
							<h1>PC构件厂认证</h1>
							<p>您已经通过PC构件厂认证，已经可以使用平台功能。<br>如有其他问题，请咨询<a href="#">客服中心</a></p>
						</div>
						<div class="rzcnt_btn">
							<a href="#" class="tgbtn">敬请期待</a>
						</div>
					</div>
					<div class="rzcnt">
						<div class="rzcnt_to gys_q">
							<div class="rzphot"></div>
							<h1>未认证</h1>
							<h2>已通过认证</h2>
						</div>
						<div class="rzcnt_txt">
							<h1>供应商认证</h1>
							<p>您尚未完成供应商认证，还不能进行其它操作。<br>关于企业认证问题，请咨询<a href="#">客服中心</a></p>
						</div>
						<div class="rzcnt_btn">
							<a href="#" class="tgbtn">敬请期待</a>
						</div>
					</div>
					<div class="rzcnt">
						<c:choose>
							<c:when test="${constructionPermit.C_STATUS == '有效'}">
								<div class="rzcnt_to sgs_q rz_on">
							</c:when>
							<c:otherwise>
								<div class="rzcnt_to sgs_q ">
							</c:otherwise>
						</c:choose>
						<div class="rzphot"></div>
						<h1>未认证</h1><h2>已通过认证</h2>
					</div>
					<div class="rzcnt_txt">
						<h1>施工商认证</h1>
						<p>您尚未完施工商认证，还不能进行其它操作。<br>关于企业认证问题，请咨询<a href="#">客服中心</a></p>
					</div>
					<div class="rzcnt_btn">
						<c:choose>
							<c:when test="${constructionPermit.C_STATUS == '无效'}">
								<a href="#" class="rzzbtn">认证中</a>
							</c:when>
							<c:when test="${constructionPermit.C_STATUS == '有效'}">
								<a href="#" class="tgbtn">认证成功</a>
							</c:when>
							<c:otherwise>
								<a href="permit.do?method=certification" class="sqtn">申请认证</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="rzcnt">
					<div class="rzcnt_to yss_q">
						<div class="rzphot"></div>
						<h1>未认证</h1>
						<h2>已通过认证</h2>
					</div>
					<div class="rzcnt_txt">
						<h1>运输商认证</h1>
						<p>您尚未完运输商认证，还不能进行其它操作。<br>关于企业认证问题，请咨询<a href="#">客服中心</a></p>
					</div>
					<div class="rzcnt_btn"><a href="#" class="tgbtn">敬请期待</a></div>
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
