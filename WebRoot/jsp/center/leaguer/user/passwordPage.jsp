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
<jsp:include page="/jspFix/user/validate-css-js.jsp"/>
<script type="text/javascript" src="jsp/center/leaguer/user/passwordPage.js">
</script>
</head>

<body>
<div class="wrapper">
  <jsp:include page="/top.jsp" />
  <jsp:include page="/jsp/center/leaguer/leaguer.jsp" />
  <div class="ht_cont">
  	<jsp:include page="/jsp/center/leaguer/account.jsp">
  		<jsp:param name="password" value="true"/>
  	</jsp:include>
    <div class="r_br">
      <div class="rbr_tit">
        <h1>企业信息 &gt; 修改密码</h1>
      </div>
      <div class="rbr_cnt">
        <div class="tsinfo">
          <div class="tsinfo_cnt">
            <p>请您先完善企业认证信息 (完成企业认证才能够正常使用平台功能 )！</p>
            <a href="#" title="关闭">X</a>
          </div>
        </div>
		<form action="user.do?method=save" method="post" id="form" name="form" onsubmit="return false">
		<input type="hidden" id="ID" name="ID" value="${requestMap.ID }"/>
        <div class="tybd">
          <div class="tybd_c">
            <ul>
              <li>
                <h1 class="bdname"><span>*</span>原密码：</h1>
                <div class="bdint">
                  <input class="int01" type="password" id="C_OLD_PASSWORD" name="C_OLD_PASSWORD" >
                </div>
                <p id="factory_verify"></p>
              </li>
              <li>
                <h1 class="bdname"><span>*</span>新密码：</h1>
                <div class="bdint">
                  <input class="int01" type="password" id="C_NEW_PASSWORD" name="C_NEW_PASSWORD" >
                </div>
                <p id="address_verify"></p>
              </li>
              <li>
                <h1 class="bdname"><span>*</span>确认密码：</h1>
                <div class="bdint">
                  <input class="int01" type="password" id="C_BOU_PASSWORD" name="C_BOU_PASSWORD" >
                </div>
                <p id="address_verify"></p>
              </li>
              <li>
              	<c:if test="${requestMap.I_SYS_ROLE_ID == 0}"> 
	              <div class="bdbtn">
	                <input class="tjbtn" type="button" value=" 确认提交" onclick="saveBtn()" >
	              </div>
                </c:if>
              </li>
            </ul>
          </div>
        </div>
        <div class="c"></div>
		</form>
      </div>
    </div>
    <div class="c"></div>
  </div>
  <jsp:include page="/bottom.jsp" />
</div>
</body>
</html>
