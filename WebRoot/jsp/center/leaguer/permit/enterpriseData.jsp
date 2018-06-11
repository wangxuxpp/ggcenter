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
<jsp:include page="/jspFix/user/datepicker-css-js.jsp"/>
<script type="text/javascript" src="js/common/jquery.form.js"></script>
<script type="text/javascript" src="jsp/center/upload/upload.js"></script>
<script type="text/javascript" src="jsp/center/leaguer/permit/enterpriseData.js"></script>
</head>
<body>
<jsp:include page="/jsp/center/upload/upload.jsp"></jsp:include>
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
			<div class="step_cnt bz02">
			  <h1> 1.认证需知 </h1>
			  <h2> 2.资料提交 </h2>
			  <h3> 3.提交完成 </h3>
			</div>
		  </div>
		  <form action="permit.do?method=update" method="post" id="form" name="form" onsubmit="return false">
		    <input type="hidden" id="ID" name="ID" value="${requestMap.ID }" />
		    <input id="D_INSERT_DATE" name="D_INSERT_DATE" type="hidden" value="${requestMap.C_INSERT_DATE}">
		    <input id="D_ENABLE_DATE" name="D_ENABLE_DATE" type="hidden" value="${requestMap.C_ENABLE_DATE}">
		    <input id="D_REGISTER_DATE" name="D_REGISTER_DATE" type="hidden" value="${requestMap.C_REGISTER_DATE}">
			<div class="tybd">
			  <div class="tybd_c">
				<ul>
				<li>
				  <h1 class="bdname"><span>*</span>法定代表人：</h1>
				  <div class="bdint">
					<input id="C_LEGALPERSON" name="C_LEGALPERSON" class="int01" type="text" value="${requestMap.C_LEGALPERSON}" onblur="legalPersonClick();">
				  </div>
				  <p id="legalPerson_verify" class="bdts"></p>
				</li>
				<li>
				  <h1 class="bdname"><span>*</span>法人身份证：</h1>
				  <div class="bdint">
					<input id="C_NUMBER" name="C_NUMBER" class="int01" type="text" value="${requestMap.C_NUMBER}" onblur="numberClick();">
				  </div>
				  <p id="number_verify" class="bdts"></p>
				</li>
				<li>
				  <h1 class="bdname"><span>*</span>成立日期：</h1>
				  <div class="bdint">
					<input id="D_DATE" name="D_DATE" class="int01" type="text" value="${requestMap.C_DATE}" readonly="readonly">
				  </div>
				  <p id="date_verify" class="bdts"></p>
				</li>
				<li>
				  <h1 class="bdname"><span>*</span>经营范围：</h1>
				  <div class="bdint">
					<input id="C_RUNAREA" name="C_RUNAREA" class="int01" type="text" value="${requestMap.C_RUNAREA}" onblur="runareaClick();">
				  </div>
				  <p id="runarea_verify" class="bdts">请用”/”分开业务类型</p>
				</li>
				<li>
				  <h1 class="bdname"><span>*</span>注册资金：</h1>
				  <div class="bdint">
					<input id="C_CAPITAL" name="C_CAPITAL" class="int01" type="text" value="${requestMap.C_CAPITAL}" onblur="capitalClick();">
				  </div>
				  <p id="capital_verify" class="bdts"></p>
				</li>
				<li>
				  <h1 class="bdname"><span>*</span>上传营业执照：</h1>
				  <div class="bdint">
					<div class="upd">
					  <div class="upd_t">
					    <input type="button" class="scbtn" value="上传图片" onclick="showUpload('charter', 'yyzz_img', 'yyzz_preview')">
					    <a href="#">查看样板</a><p id="yyzz_preview" class="bdts">件格式 JPG 文件大小2MK以内</p>
					  </div>
					  <div id="yyzz_img"/><img src="file/factory/charter/${requestMap.ID}.jpg" style="max-height: 360px;"/></div>
					</div>
				  </div>
				</li>
				<li>
				  <h1 class="bdname"><span>*</span>法人二代身份证：</h1>
				  <div class="bdint">
					<div class="upd">
					  <div class="upd_t">
						<input type="button" class="scbtn" value="上传图片" onclick="showUpload('keyIdImg', 'sfz_img', 'sfz_preview')">
						<a href="#">查看样板</a><p id="sfz_preview" class="bdts">件格式 JPG 文件大小2MK以内</p>
					  </div>
					  <div id="sfz_img"/><img src="file/factory/keyIdImg/${requestMap.ID}.jpg" style="max-height: 360px;"/></div>
					</div>
				  </div>
				</li>
				<li>
				  <h1 class="bdname"><span>*</span>企业商标logo：</h1>
				  <div class="bdint">
				    <div class="upd">
					  <div class="upd_t">
						<input type="button" class="scbtn" value="上传图片" onclick="showUpload('LOGO', 'logo_img', 'logo_preview')">
						<a href="#">查看样板</a><p id="logo_preview" class="bdts">件格式 JPG 文件大小2MK以内</p>
					  </div>
					  <div id="logo_img"/><img src="file/factory/LOGO/${requestMap.ID}.jpg" style="max-height: 360px;"/></div>
					</div>
				  </div>
				</li>
				<li>
				  <div class="bdbtn">
					<input class="tjbtn" type="button" value="下一步" onclick="saveBtn()">
					<input class="qxbtn" type="button" value="上一步" onclick="javascrtpt:window.location.href='permit.do?method=certification'">
				  </div>
				</li>
				</ul>
			  </div>
			</div>
		  </form>
		  <div class="c"></div>
		</div>
	  </div>
	</div>
	<div class="c"></div>
	<jsp:include page="/bottom.jsp"/>
</div>
</body>
</html>
