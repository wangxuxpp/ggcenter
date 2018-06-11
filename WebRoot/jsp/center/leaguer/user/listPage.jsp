<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="base.common.Constant"%>
<%@ page import="base.util.SessionUser"%>
<%
	SessionUser user = (SessionUser) session.getAttribute(Constant.SYS_SESSION);
	int I_SYS_ROLE_ID = 0;
	if(user != null){
		I_SYS_ROLE_ID = user.getUserRole();
	}
%>
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
<jsp:include page="/jspFix/user/base-css-js.jsp"/>
<jsp:include page="/jspFix/user/ajax-css-js.jsp"/>
<jsp:include page="/jspFix/user/grid-css-js.jsp"/>
<script type="text/javascript" src="jsp/center/leaguer/user/listPage.js"></script>
</head>
<body>
<div class="wrapper">
  <jsp:include page="/top.jsp" />
  <jsp:include page="/jsp/center/leaguer/leaguer.jsp" />
  <div class="ht_cont">
  	<jsp:include page="/jsp/center/leaguer/account.jsp">
  		<jsp:param name="user" value="true"/>
  	</jsp:include>
    <div class="r_br">
      <div class="rbr_tit">
        <h1>企业信息 &gt; 用户设置</h1>
      </div>
      <div class="rbr_cnt">
        <div class="lbnr">
          <div class="lbnr_c">
            <% if(I_SYS_ROLE_ID == 0){ %>
            <div class="option_btn">
              <ul>
				<li id="selBtn"><a href="javascript:void(0)" class="obtn_01"><span></span>查询</a></li>
				<li id="insBtn"><a href="javascript:void(0)" class="obtn_02"><span></span>新增</a></li>
				<li id="updBtn"><a href="javascript:void(0)" class="obtn_03"><span></span>修改</a></li>
				<li id="delBtn"><a href="javascript:void(0)" class="obtn_04"><span></span>删除</a></li>
				<li id="conBtn"><a href="javascript:void(0)" class="obtn_05"><span></span>重置密码</a></li>
              </ul>
            </div>
            <% } %>
            <div class="tab_page">
				<table id="gridTable"></table>
            </div>
          </div>
        </div>
        <div class="c"></div>
      </div>
    </div>
    <div class="c"></div>
  </div>
  <jsp:include page="/bottom.jsp" />
</div>
</body>
</html>