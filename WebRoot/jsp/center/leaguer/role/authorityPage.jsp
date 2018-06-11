<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript" src="jsp/center/leaguer/role/authorityPage.js">
</script>
</head>
<body style="background:#f2f2f2;">
<form action="role.do?method=authoritySave" method="post" id="form" name="form" onsubmit="return false">
<div class="wrapper">
  <div class="tcy">
    <div class="tcy_c"> 
	  <input type="hidden" id="ID" name="ID" value="${requestMap.ID }"/>
      <div class="jurisdiction">
        <div class="jin_cnt">
          <div class="qxqx"> 
            <div class="san_dx_cnt">
	          <input type="checkbox" id="allLimit" name="allLimit" onclick="allfull(this)" /><label for="allLimit" class="fonfe_yes">选择全部</label>
            </div>
            <h1>角色名称：<span>${requestMap.C_NAME }</span></h1>
          </div>
	      <c:forEach items="${authorityAllList}" var="class">
          <div class="san_dx">
            <div class="san_tit"> 
              <div class="san_dx_cnt">
                <input type="checkbox" id="classLimit_${class.ID }" name="classLimit" onclick="classfull(${class.ID },this)" />
                <label for="classLimit_${class.ID }">${class.C_NAME }</label>
              </div>
            </div>
            <div class="san_c">
			  <c:forEach items="${class.subList}" var="sub">
              <div class="san_dx_cnt">
                <input type="checkbox" id="winLimit_${sub.ID }" name="winLimit" <c:if test="${authorityMap[sub.ID] == sub.ID }">checked="checked"</c:if> onclick="if(this.checked){$('#SYS_PAGESUB_${sub.ID }').val(${sub.ID });}else{$('#SYS_PAGESUB_${sub.ID }').val(0);}" />
                <label for="winLimit_${sub.ID }">${sub.C_NAME }</label>
                <input id="SYS_PAGESUB_${sub.ID }" name="I_SYS_PAGESUB_ID" type="hidden" <c:if test="${authorityMap[sub.ID] == sub.ID }">value="${sub.ID }"</c:if> />
              </div>
              </c:forEach>
              <div class="c"></div>
            </div>
          </div>
          </c:forEach>
        </div>
      </div>
      <div class="option_btn01">
        <ul>
          <li class="op_btn_rw"><a href="javascript:void(0)" class="bc_obtn" onclick="saveBtn()">保存</a></li>
          <li><a href="javascript:void(0)" class="xg_obtn" onclick="Util.onClose()">关闭</a></li>
        </ul>
      </div>
    </div>
  </div>
</div>
</form>
</body>
</html>
