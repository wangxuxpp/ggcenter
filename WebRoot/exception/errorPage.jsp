<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" language="java" isErrorPage="true"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="base.util.Util"%>
<html>
<head>
<title>卫德住工科技数字化工厂系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="No-Cache" />     
<meta http-equiv="Cache-Control" content="No-Cache" />     
<meta http-equiv="Expires" content="0" />  
<meta http-equiv="X-UA-Compatible" content="IE=10" />
<meta http-equiv="windows-Target" content="_top"/>
<meta name="keywords"    content="卫德数字化工厂系统，数字化工厂"/>
<meta name="description" content="卫德数字化工厂系统"/>
<meta name="Copyright"   content="本页版权归卫德住工科技所有。All Rights Reserved"/>
<link rel="shortcut icon" href="css/images/index/favicon.ico"/>
<link rel="bookmark"      href="css/images/index/favicon.ico"/>
<link rel="stylesheet" href="css/reset.css" type="text/css" />
<link rel="stylesheet" href="css/base.css" type="text/css" />
</head>
  
<body style="padding:0; margin:0;">
<div class="wrapper"> 
  <div class="error404">
    <div class="error404_cnt">
      <div class="error404_cnt_c">
		<img src="css/images/otherImg/Error.gif" alt="出错了" title="出错了" />
		<h1 class="errorcnt">对不起，没有找到相关信息！</h1>
		<h3 class="tsinfo"><%=Util.throwableStr(exception)%></h3>
      </div>
    </div>
  </div>
</div>
</body>
</html>
