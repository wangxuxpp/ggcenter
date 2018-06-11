<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

  <div class="ht_her">
    <div class="ht_her_cnt">
      <div class="htlogo">
        <h1>会员中心</h1>
      </div>
      <div class="nav">
        <ul>
          <li class="up"><a href="factory.do?method=listPage">首页</a> </li>
          <li><a href="#" class="hni">账户设置 </a>
            <table>
              <tr>
                <td>
                  <ul>
			          <li><a href="factory.do?method=listPage">企业信息</a></li>
			          <li style="display: none;"><a href="#">头像设置</a></li>
			          <li><a href="user.do?method=passwordPage">密码修改</a></li>
			          <li style="display: none;"><a href="#">绑定手机</a></li>
			          <li style="display: none;"><a href="#">绑定邮箱</a></li>
			          <li><a href="permit.do?method=permit">企业认证</a></li>
			          <li><a href="role.do?method=listPage">角色设置</a></li>
			          <li><a href="user.do?method=listPage">用户设置</a></li>
                  </ul>
                </td>
              </tr>
            </table>
          </li>
          <c:forEach items="${pageList}" var="page">
          <li><a href="${page.C_URL }" class="hni">${page.C_NAME }</a>
            <table>
              <tr>
                <td>
                  <ul>
                  	<c:forEach items="${page.subList}" var="pageSub">
                    <li><a href="${pageSub.C_URL }">${pageSub.C_NAME }</a></li>
                    </c:forEach>
                  </ul>
                </td>
              </tr>
            </table>
          </li>
          </c:forEach>
        </ul>
      </div>
      <div class="htss">
        <div class="htss_cnt"> 
          <div class="ss_lb">
            <div class="searchMenu">
              <div class="searchSelected" id="searchSelected">产品</div>
              <div style="display:none;" class="searchTab" id="searchTab">
                <ul>
                  <li>产品</li>
                  <li>企业</li>
                  <li>咨询</li>
                </ul>
              </div>
            </div>
          </div>
          <div class="ss_int">
            <input class="inp_srh" name="keyboard" value="预制构件 " onFocus="if (value =='预制构件 '){value =''}" onBlur="if (value ==''){value='预制构件 '}" >
          </div>
          <div class="ss_btn"><a href="#">搜索</a></div>
          <div class="c"></div>
        </div>
      </div>
      <div class="c"></div>
    </div>
  </div>