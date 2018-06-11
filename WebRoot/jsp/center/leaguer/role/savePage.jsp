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
<script type="text/javascript" src="jsp/center/leaguer/role/savePage.js"></script>
</head>

<body style="background:#f2f2f2;">
<div class="wrapper">
  <div class="tcy">
    <div class="tcy_c"> 
      <div class="addbill">
          <div class="ts_text">
		    <p>带<span>*</span>为必填项</p>
		  </div>
		  <form action="page.do?method=save" method="post" id="form" name="form" onsubmit="return false">
		  <input type="hidden" id="ID" name="ID" value="${requestMap.ID }"/>
		  <table class="addTab" border="0" cellspacing="0" cellpadding="0" align="center">
			  <tr>
			    <td align="right" width="80px"><h1 class="tit_text">角色名称：</h1><span class="redtext">*</span></td>
			    <td align="left" width="260px"><input type="text" id="C_NAME" name="C_NAME" class="ftab_int" value="${requestMap.C_NAME }"/></td>
			  </tr>
			  <tr>
			    <td align="right" width="80px"><h1 class="tit_text">备&nbsp;&nbsp;&nbsp;&nbsp;注：</h1></td>
			    <td align="left" width="260px" height="90px"><textarea name="C_MEMO" class="ftab_txt" id="C_MEMO" style="width:255px;">${requestMap.C_MEMO }</textarea></td>
			  </tr>
		  </table>
		  </form>
		  <div class="option_btn01">
	        <ul>
	          <li class="op_btn_rw"><a href="javascript:void(0)" class="bc_obtn" onclick="saveBtn()">保存</a></li>
	          <li><a href="javascript:void(0)" class="xg_obtn" onclick="Util.onClose()">关闭</a></li>
	        </ul>
		  </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
