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
<script type="text/javascript" src="js/common/pinyin.js"></script>
<script type="text/javascript" src="js/common/pcSelest.js"></script>
<script type="text/javascript" src="js/common/util.js"></script>
<script type="text/javascript" src="js/jquery/jquery.selest.js"></script>
<script type="text/javascript" src="jsp/center/leaguer/factory/factory.js"></script>
</head>
<body>
<div class="wrapper">
  <jsp:include page="/top.jsp" />
  <jsp:include page="/jsp/center/leaguer/leaguer.jsp" />
  <div class="ht_cont"> 
  	<jsp:include page="/jsp/center/leaguer/account.jsp">
  		<jsp:param name="factory" value="true"/>
  	</jsp:include>
    <div class="r_br">
      <div class="rbr_tit">
        <h1>企业信息 &gt; 企业信息</h1>
      </div>
      <div class="rbr_cnt">
	  <form action="factory.do?method=update" method="post" id="form" name="form" onsubmit="return false">
		<input type="hidden" id="ID" name="ID" value="${requestMap.ID}">
		<input type="hidden" id="I_FACTORY_ID" name="I_FACTORY_ID" value="${requestMap.I_FACTORY_ID}">
		<input type="hidden" id="C_PRO" name="C_PRO" value="${requestMap.C_PRO}">
		<input type="hidden" id="C_CITY" name="C_CITY" value="${requestMap.C_CITY}">
        <div class="fg_tit">
          <div class="fg_cnt">
            <h1>企业信息</h1>
          </div>
        </div>
        <div class="tybd">
          <div class="tybd_c">
            <ul>
              <li>
                <h1 class="bdname">企业编号：</h1>
                <div class="bdint">
                  <h2>${requestMap.C_CODE}</h2>
                </div>
                <div class="c"></div>
              </li>
              <li>
                <h1 class="bdname"><span>*</span>企业名称：</h1>
                <div class="bdint">
                  <input class="int01" type="text" id="C_FACTORY_NAME" name="C_FACTORY_NAME" value="${requestMap.C_FACTORY_NAME}" onblur="factoryClick();">
                </div>
                <p id="factory_verify"></p>
                <div class="c"></div>
              </li>
              <li>
				<h1 class="bdname"><span>*</span>企业所在地：</h1>
				<div id="pcDiv" class="bdint">
				</div>
				<p id="pro_city" class="bdts"></p>
              </li>
              <li>
                <h1 class="bdname"><span>*</span>公司地址：</h1>
                <div class="bdint">
                  <input class="int01" type="text" id="C_ADDRESS" name="C_ADDRESS" value="${requestMap.C_ADDRESS}" onblur="addressClick();">
                </div>
                <p id="address_verify" class="bdts">请填写企业详细地址</p>
              </li>
              <li>
                <h1 class="bdname"><span>*</span>经营范围：</h1>
                <div class="bdint">
                  <input class="int01" type="text"  id="C_RUNAREA" name="C_RUNAREA" value="${requestMap.C_RUNAREA}" onblur="runareaClick();">
                </div>
                <p id="runarea_verify" class="bdts">请用”/”分开业务类型</p>
              </li>
              <li>
                <h1 class="bdname">手机：</h1>
                <div class="bdint">
                  <input class="int01" type="text" id="C_TEL" name="C_TEL" value="${requestMap.C_TEL}" onblur="telClick();">
                </div>
                <p id="tel_verify"></p>
              </li>
              <li>
                <h1 class="bdname">公司介绍：</h1>
                <div class="bdint">
                  <textarea class="txa01" id="C_INFO" name="C_INFO" >${requestMap.C_INFO}</textarea>
                </div>
                <p id="info_verify"></p>
              </li>
            </ul>
          </div>
        </div>
        <div class="c"></div>
        <div class="fg_tit">
          <div class="fg_cnt">
            <h1>个人信息</h1>
          </div>
        </div>
        <div class="tybd">
          <div class="tybd_c">
            <ul>
              <li>
                <h1 class="bdname">会员名：</h1>
                <div class="bdint">
                  <h2>${requestMap.C_LOGINNAME}</h2>
                </div>
                <div class="c"></div>
              </li>
              <li>
                <h1 class="bdname"><span>*</span>真实姓名：</h1>
                <div class="bdint">
                  <input class="int01" id="C_NAME" name="C_NAME" type="text" value="${requestMap.C_NAME}" onblur="nameClick();">
                </div>
                <p id="name_verify"></p>
              </li>
              <li>
                <h1 class="bdname"><span>*</span>邮箱：</h1>
                <div class="bdint">
                  <input class="int01" id="C_MAIL" name="C_MAIL" type="text" maxlength="100" value="${requestMap.C_MAIL}" onblur="mailClick();">
                </div>
                <p id="mail_verify"></p>
              </li>
              <!-- li>
                <h1 class="bdname">角色类型：</h1>
                <div class="bdint">
                  <input class="int01" id="C_SYS_ROLE_NAME" name="C_SYS_ROLE_NAME" type="text" readonly="readonly" >
                </div>
              </li -->
            </ul>
          </div>
        </div>
        <div class="c"></div>
        <div class="fg_tit">
          <div class="fg_cnt">
            <h1>头像设置</h1>
          </div>
        </div>
        <div class="txsz">
          <div class="txsz_c">
            <div class="txcj">编辑头像插件</div>
            <div class="txzs">
              <div class="txcnt"><img src="../css/user/images/userimg/u_photo.jpg"></div>
              <p><b>·</b>请上传您的品牌Logo或者个人真实头像<br/>
                <b>·</b>请勿在形象标示上留有任何联系方式的信息<br/>
                <b>·</b>支持JPG格式，图片大小不超过5MB<br/>
                <b>·</b>请保证图片质量，分辨率至少为200*200<br/>
                <b>·</b>请不要在头像上面留电话，QQ，网址，邮箱<br/>
                &nbsp;&nbsp;等联系方式信息，会导致您的头像无法通过审核</p>
            </div>
          </div>
          <div class="tybd_c">
            <ul>
              <li>
              	<c:if test="${requestMap.I_SYS_ROLE_ID ==0}"> 
                <div class="bdbtn">
                  <input class="tjbtn" type="button" value=" 确认提交" onclick="saveBtn()" >
                  <input class="qxbtn" type="button" value=" 取消" onclick="undoBtn()">
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
