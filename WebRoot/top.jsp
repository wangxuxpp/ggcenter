<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="base.common.Constant"%>
<%@ page import="base.util.SessionUser"%>
<%
	SessionUser user = (SessionUser) session.getAttribute(Constant.SYS_SESSION);
	String name = null;
	if(user != null){
		name = user.getUserName();
	}
%>

  <div class="her_top">
    <div class="top_cnt">
      <div class="top_l">
        <p class="hy">您好，欢迎访问预制构件平台！</p>
        <p id="unLoginInfo" class="dl_zb" <% if(name != null){%>style="display:none;"<%} %>><a href="login.hm?method=showLogin">[<span>登录</span>]</a><a href="register.hm?method=showRegister">[<span>免费注册</span>]</a></p>
        <p id="loginInfo" class="dl_cg" <% if(name == null){%>style="display:none;"<%} %>><span><%=name %></span><a href="login.hm?method=doClose">退出</a></p>
      </div>
      <div class="top_r">
        <ul>
          <c:if test="${empty param.index}">
          <li><a href="index.jsp">首页</a></li>
          </c:if>
          <li class="ts_info"><a href="#">消息(<span>10</span>)</a></li>
          <li><a href="factory.do?method=listPage">会员中心</a></li>
          <li><a href="#">关于我们</a></li>
          <li><a href="#">客服中心</a></li>
          <li><a href="#">网站导航</a></li>
        </ul>
      </div>
      <div class="c"></div>
    </div>
  </div>