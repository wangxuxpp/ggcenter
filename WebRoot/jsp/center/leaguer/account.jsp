<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="base.common.Constant"%>
<%@ page import="base.util.SessionUser"%>

<%
	SessionUser user = (SessionUser) session.getAttribute(Constant.SYS_SESSION);
	int roleId = 0;
	if(user != null){
		roleId = user.getUserRole();
	}
%>

	<div class="sidebar">
      <div class="sbr_tit">
        <h1>账户设置</h1>
      </div>
      <div class="sbr_cnt">
        <ul>
          <li><a href="factory.do?method=listPage" <c:if test="${not empty param.factory}"> class="sbron" </c:if> >企业信息</a></li>
          <li style="display: none;"><a href="#">头像设置</a></li>
          <li><a href="user.do?method=passwordPage" <c:if test="${not empty param.password}"> class="sbron" </c:if> >密码修改</a></li>
          <li style="display: none;"><a href="#">绑定手机</a></li>
          <li style="display: none;"><a href="#">绑定邮箱</a></li>
          <li><a href="permit.do?method=permit" <c:if test="${not empty param.permit}"> class="sbron" </c:if> >企业认证</a></li>
          <li><a href="role.do?method=listPage" <c:if test="${not empty param.role}"> class="sbron" </c:if> >角色设置</a></li>
          <li><a href="user.do?method=listPage" <c:if test="${not empty param.user}"> class="sbron" </c:if> >用户设置</a></li>
        </ul>
      </div>
    </div>