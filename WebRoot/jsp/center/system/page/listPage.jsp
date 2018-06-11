<!doctype html>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<title>卫德PC构件平台--后台管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="No-Cache" />     
<meta http-equiv="Cache-Control" content="No-Cache" />     
<meta http-equiv="Expires" content="0" />  
<meta http-equiv="X-UA-Compatible" content="IE=10" />
<meta http-equiv="windows-Target" content="_top"/>
<meta name="keywords"    content="卫德数字化工厂系统，数字化工厂"/>
<meta name="description" content="卫德数字化工厂系统"/>
<meta name="Copyright"   content="本页版权归卫德住工科技所有。All Rights Reserved"/>
<link rel="shortcut icon" href="images/favicon.ico"/>
<link rel="bookmark"      href="images/favicon.ico"/>
<jsp:include page="/jspFix/sysUser/base-css-js.jsp"/>
<jsp:include page="/jspFix/sysUser/jqgrid-css-js.jsp"/>
<script type="text/javascript" src="jsp/center/system/page/listPage.js"></script>
</head>
<body>
<div>
	<div class="option_btn">
		<ul>
			<li id="insBtn"><a href="javascript:void(0)" class="obtn_02"><span></span>新增</a></li>
			<li id="updBtn"><a href="javascript:void(0)" class="obtn_03"><span></span>修改</a></li>
			<li id="delBtn"><a href="javascript:void(0)" class="obtn_04"><span></span>删除</a></li>
		</ul>
	</div>
	<div class="tab_page"> 
		<table id="gridTable"></table>
		<div id="pagerDiv"></div>
	</div>
	<div class="option_btn">
		<ul>
			<li id="insBtnSub"><a href="javascript:void(0)" class="obtn_02"><span></span>新增</a></li>
			<li id="updBtnSub"><a href="javascript:void(0)" class="obtn_03"><span></span>修改</a></li>
			<li id="delBtnSub"><a href="javascript:void(0)" class="obtn_04"><span></span>删除</a></li>
		</ul>
	</div>
	<div class="tab_page"> 
		<table id="gridTableSub"></table>
		<div id="pagerDivSub"></div>
	</div>
</div>
</body>
</html>
