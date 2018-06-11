<!doctype html>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>卫德PC构件平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="No-Cache" />     
<meta http-equiv="Cache-Control" content="No-Cache" />     
<meta http-equiv="Expires" content="0" />  
<meta http-equiv="X-UA-Compatible" content="IE=10" />
<meta http-equiv="windows-Target" content="_top"/>
<meta name="keywords"    content="卫德数字化工厂系统，数字化工厂"/>
<meta name="description" content="卫德数字化工厂系统"/>
<meta name="Copyright"   content="本页版权归卫德住工科技所有。All Rights Reserved"/>
<jsp:include page="/jspFix/user/baseselect-css-js.jsp"></jsp:include>
<script type="text/javascript" src="js/common/respond.src.js"></script>
<script type="text/javascript" src="js/jquery/jquery.selest.js"></script>
<script type="text/javascript" src="index/jquery.superslide.2.1.1.js"></script>
<script type="text/javascript" src="index/index.js"></script>
</head>
<body>
<div class="wrapper"> 
  
  <!-- 顶部 start -->
  <jsp:include page="/top.jsp" >
  	<jsp:param name="index" value="true"/>
  </jsp:include>
  <!-- 顶部 end --> 
  
  <!-- 头部 start -->
  <div class="header">
    <div class="her_cnt"> 
      
      <!-- logo start -->
      <div class="logo"> <a href="index.jsp"><img src="images/logo.gif" title="logo" alt="logo"></a> </div>
      <!-- logo end --> 
      
      <!-- 搜索 start -->
      <div class="sosuo">
        <div class="ss_cnt"> 
          <!-- 类型 start -->
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
          <!-- 类型 end -->
          <div class="ss_int">
            <input class="inp_srh" name="keyboard" value="请输入关键字 " onFocus="if (value =='请输入关键字 '){value =''}" onBlur="if (value ==''){value='请输入关键字 '}" >
          </div>
          <div class="ss_btn"><a href="#">搜索</a></div>
          <div class="c"></div>
        </div>
        <p class="ss_gjz"> 热门搜索：<a href="#">钢筋</a><a href="#">洪凝土</a><a href="#">沙子</a><a href="#">洪凝土</a><a href="#">沙子</a></p>
      </div>
      <!-- 搜索 end --> 
      
      <!-- 二维码 start -->
      <div class="ewm">
        <div class="ewm_c"> <img src="images/erweima.gif">
          <h1>APP下载</h1>
        </div>
      </div>
      <!-- 二维码 end --> 
      
    </div>
  </div>
  <!-- 头部 end -->
  
  <div class="nav_ber"> 
    
    <!-- 导航 start -->
    <div class="menu"> 
      <!-- 小图标 start -->
      <div class="ico_new" style="left:685px; top:3px;"></div>
      <div class="ico_hot" style="left:585px; top:3px;"></div>
      <!-- 小图标 start -->
      <div class="all-sort">
        <h2><a href="#">全部商品分类</a></h2>
      </div>
      <div class="ad">
        <h1>服务热线：400-800-888</h1>
      </div>
      <div class="nav">
        <ul>
          <li><a href="#">首页</a></li>
          <li><a href="#">知名企业</a></li>
          <li><a href="#">行业资讯</a></li>
          <li><a href="#">数字工厂</a></li>
          <li><a href="#">施工系统</a></li>
        </ul>
      </div>
    </div>
    <!-- 导航 end -->
    
    <div class="wrap"> 
      
      <!-- 分类导航 start -->
      <div class="all-sort-list">
        <div class="item">
          <div class="item-tit">
            <h1><img src="images/ico_03.png">预制构件</h1>
            <h3><a href="#">阳台板</a><a href="#" class="hot_xz">阳台板</a><a href="#">女儿墙</a><a href="#">三明治墙</a><a href="#">楼梯板</a><a href="#">三明治墙</a></h3>
          </div>
          <div class="item-list">
            <div class="close">x</div>
            <div class="subitem">
              <dl>
                <dt><a href="#">商品房</a></dt>
                <dd><em><a href="#">阳台板</a></em><em><a href="#" class="hot_xz">女儿墙</a></em><em><a href="#">三明治墙</a></em><em><a href="#">三明治墙</a></em><em><a href="#">三明治墙</a></em><em><a href="#">三明治墙</a></em><em><a href="#">三明治墙</a></em></dd>
              </dl>
              <dl>
                <dt><a href="#">桥梁</a></dt>
                <dd><em><a href="#">阳台板</a></em><em><a href="#">女儿墙</a></em><em><a href="#">三明治墙</a></em><em><a href="#">三明治墙</a></em><em><a href="#">三明治墙</a></em><em><a href="#">三明治墙</a></em><em><a href="#">三明治墙</a></em></dd>
              </dl>
            </div>
            <div class="cat-right">
              <div class="car_ber"> </div>
            </div>
          </div>
        </div>
        <div class="item">
          <div class="item-tit">
            <h1><img src="images/ico_04.png">原料供应</h1>
            <h3><a href="#">沙子</a><a href="#">水泥</a><a href="#">钢筋</a><a href="#">混凝土</a><a href="#">磨具</a><a href="#">平车</a></h3>
          </div>
          <div class="item-list">
            <div class="close">x</div>
            <div class="subitem">
              <dl>
                <dt><a href="#">水泥</a></dt>
                <dd><em><a href="#">水泥</a></em><em><a href="#" class="hot_xz">水泥</a></em><em><a href="#">水泥</a></em><em><a href="#">水泥</a></em><em><a href="#">水泥</a></em></dd>
              </dl>
              <dl>
                <dt><a href="#">水泥</a></dt>
                <dd><em><a href="#">水泥</a></em><em><a href="#" class="hot_xz">水泥</a></em><em><a href="#">水泥</a></em><em><a href="#">水泥</a></em><em><a href="#">水泥</a></em></dd>
              </dl>
            </div>
            <div class="cat-right">
              <div class="car_ber"> </div>
            </div>
          </div>
        </div>
        <div class="item">
          <div class="item-tit">
            <h1><img src="images/ico_05.png">加工设备</h1>
            <h3><a href="#">沙子</a><a href="#">水泥</a><a href="#">钢筋</a><a href="#">混凝土</a><a href="#">磨具</a><a href="#">平车</a></h3>
          </div>
          <div class="item-list">
            <div class="close">x</div>
            <div class="subitem">
              <dl>
                <dt><a href="#">商品房</a></dt>
                <dd><em><a href="#">阳台板</a></em><em><a href="#" class="hot_xz">女儿墙</a></em><em><a href="#">三明治墙</a></em><em><a href="#">三明治墙</a></em></dd>
              </dl>
            </div>
            <div class="cat-right">
              <div class="car_ber"> </div>
            </div>
          </div>
        </div>
        <div class="item">
          <div class="item-tit">
            <h1><img src="images/ico_06.png">构件模具</h1>
            <h3><a href="#">沙子</a><a href="#">水泥</a><a href="#">钢筋</a><a href="#">混凝土</a><a href="#">磨具</a><a href="#">平车</a></h3>
          </div>
          <div class="item-list">
            <div class="close">x</div>
            <div class="subitem">
              <dl>
                <dt><a href="#">商品房</a></dt>
                <dd><em><a href="#">阳台板</a></em><em><a href="#" class="hot_xz">女儿墙</a></em><em><a href="#">三明治墙</a></em><em><a href="#">三明治墙</a></em></dd>
              </dl>
            </div>
            <div class="cat-right">
              <div class="car_ber"> </div>
            </div>
          </div>
        </div>
      </div>
      <script src="index/classnav.js"></script> 
      <!-- 分类导航 end -->
      
      <div class="jst_ber"> 
        
        <!-- 焦点图 start -->
        <div class="jst">
          <ul class="jst_cnt">
            <li><a href="#" target="_blank"><img src="DWF/0.jpg" /></a></li>
            <li><a href="#" target="_blank"><img src="DWF/1.jpg" /></a></li>
            <li><a href="#" target="_blank"><img src="DWF/2.jpg" /></a></li>
          </ul>
          <a class="prev" href="javascript:void(0)"></a> <a class="next" href="javascript:void(0)"></a>
          <div class="num">
            <ul>
            </ul>
          </div>
        </div>
        <script src="index/jdt.js"></script> 
        <!-- 焦点图 end --> 
        
        <!-- 广告 start -->
        <div class="h_ber">
          <div class="h_b01"><a href="#"><img src="DWF/f_b_01.jpg" alt="广告" title="广告"></a></div>
          <div class="h_b02"><a href="#"><img src="DWF/f_b_02.jpg" alt="广告" title="广告"></a></div>
        </div>
        <!-- 广告 end --> 
        
      </div>
      
      <!-- 公告 start -->
      <div class="ggzcdl">
        <div class="ggzcdl_t"> <a href="login.hm?method=showLogin" style="border-right:1px solid #fff;">登陆</a><a href="register.hm?method=showRegister">注册</a> </div>
        <div class="ggzcdl_c">
          <ul>
            <li><a href="#"><span>[公告]</span>平台2015-10-1正式上线正式上线正式上线正式上线</a></li>
            <li><a href="#"><span>[重要]</span>平台2015-10-1正式上线</a></li>
            <li><a href="#"><span>[公告]</span>平台2015-10-1正式上线</a></li>
            <li><a href="#"><span>[公告]</span>平台2015-10-1正式上线</a></li>
            <li><a href="#"><span>[资讯]</span>平台2015-10-1正式上线</a></li>
            <li><a href="#"><span>[公告]</span>平台2015-10-1正式上线</a></li>
            <li><a href="#"><span>[公告]</span>平台2015-10-1正式上线</a></li>
            <li><a href="#"><span>[公告]</span>平台2015-10-1正式上线</a></li>
            <li><a href="#"><span>[公告]</span>平台2015-10-1正式上线</a></li>
          </ul>
        </div>
      </div>
      <!-- 公告 end -->
      <div class="c"></div>
    </div>
  </div>
  
  <!-- 主要内容 start -->
  <div class="content"> 
    
    <!-- *************col01************* start -->
    <div class="colone">
      <div class="colone_tit">
        <h1><span>1F</span>预制构件</h1>
        <h2><a href="#">三明治墙</a><a href="#">三明治墙</a><a href="#">三明治墙</a><a href="#">三明治墙</a><a href="#">三明治墙</a></h2>
      </div>
      <div class="colone_cnt">
        <div class="c_c_ber">
          <h1 style="color:#ff822f; font-size:20px; text-align:center; padding-top:150px;">相关板块<br>
            banne</h1>
        </div>
        <div class="c_c_cp">
          <ul>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_01.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_01.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_01.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_01.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
          </ul>
          <div class="xian"></div>
          <ul class="cpj">
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_01.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_01.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_01.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_01.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
          </ul>
        </div>
        <div class="c_c_qy">
          <div class="c_c_qy_tit">
            <h1>推荐厂商</h1>
          </div>
          <ul>
            <li>
              <h1><a href="#">沈阳卫德住宅工业化科技有限公司</a></h1>
              <h2>辽宁省 - 沈阳市 - 沈北新区</h2>
              <h3>主营：工艺设计/建厂服务/深化设计</h3>
            </li>
            <li>
              <h1><a href="#">沈阳卫德住宅工业化科技有限公司</a></h1>
              <h2>辽宁省 - 沈阳市 - 沈北新区</h2>
              <h3>主营：工艺设计/建厂服务/深化设计</h3>
            </li>
            <li>
              <h1><a href="#">沈阳卫德住宅工业化科技有限公司</a></h1>
              <h2>辽宁省 - 沈阳市 - 沈北新区</h2>
              <h3>主营：工艺设计/建厂服务/深化设计</h3>
            </li>
            <li>
              <h1><a href="#">沈阳卫德住宅工业化科技有限公司</a></h1>
              <h2>辽宁省 - 沈阳市 - 沈北新区</h2>
              <h3>主营：工艺设计/建厂服务/深化设计</h3>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <!-- *************col01************* end --> 
    
    <!-- *************col01************* start -->
    <div class="colone">
      <div class="colone_tit">
        <h1><span>2F</span>原料供应</h1>
        <h2><a href="#">三明治墙</a><a href="#">三明治墙</a><a href="#">三明治墙</a><a href="#">三明治墙</a><a href="#">三明治墙</a></h2>
      </div>
      <div class="colone_cnt">
        <div class="c_c_ber">
          <h1 style="color:#ff822f; font-size:20px; text-align:center; padding-top:150px;">相关板块<br>
            banne</h1>
        </div>
        <div class="c_c_cp">
          <ul>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_02.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_02.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_02.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_02.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
          </ul>
          <div class="xian"></div>
          <ul class="cpj">
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_02.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_02.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_02.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_02.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
          </ul>
        </div>
        <div class="c_c_qy">
          <div class="c_c_qy_tit">
            <h1>推荐厂商</h1>
          </div>
          <ul>
            <li>
              <h1><a href="#">沈阳卫德住宅工业化科技有限公司</a></h1>
              <h2>辽宁省 - 沈阳市 - 沈北新区</h2>
              <h3>主营：工艺设计/建厂服务/深化设计</h3>
            </li>
            <li>
              <h1><a href="#">沈阳卫德住宅工业化科技有限公司</a></h1>
              <h2>辽宁省 - 沈阳市 - 沈北新区</h2>
              <h3>主营：工艺设计/建厂服务/深化设计</h3>
            </li>
            <li>
              <h1><a href="#">沈阳卫德住宅工业化科技有限公司</a></h1>
              <h2>辽宁省 - 沈阳市 - 沈北新区</h2>
              <h3>主营：工艺设计/建厂服务/深化设计</h3>
            </li>
            <li>
              <h1><a href="#">沈阳卫德住宅工业化科技有限公司</a></h1>
              <h2>辽宁省 - 沈阳市 - 沈北新区</h2>
              <h3>主营：工艺设计/建厂服务/深化设计</h3>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <!-- *************col01************* end --> 
    
    <!-- *************col01************* start -->
    <div class="colone">
      <div class="colone_tit">
        <h1><span>3F</span>构件施工</h1>
        <h2><a href="#">三明治墙</a><a href="#">三明治墙</a><a href="#">三明治墙</a><a href="#">三明治墙</a><a href="#">三明治墙</a></h2>
      </div>
      <div class="colone_cnt">
        <div class="c_c_ber">
          <h1 style="color:#ff822f; font-size:20px; text-align:center; padding-top:150px;">相关板块<br>
            banne</h1>
        </div>
        <div class="c_c_cp">
          <ul>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_03.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_03.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_03.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_03.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
          </ul>
          <div class="xian"></div>
          <ul class="cpj">
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_03.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_03.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_03.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_03.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
          </ul>
        </div>
        <div class="c_c_qy">
          <div class="c_c_qy_tit">
            <h1>推荐厂商</h1>
          </div>
          <ul>
            <li>
              <h1><a href="#">沈阳卫德住宅工业化科技有限公司</a></h1>
              <h2>辽宁省 - 沈阳市 - 沈北新区</h2>
              <h3>主营：工艺设计/建厂服务/深化设计</h3>
            </li>
            <li>
              <h1><a href="#">沈阳卫德住宅工业化科技有限公司</a></h1>
              <h2>辽宁省 - 沈阳市 - 沈北新区</h2>
              <h3>主营：工艺设计/建厂服务/深化设计</h3>
            </li>
            <li>
              <h1><a href="#">沈阳卫德住宅工业化科技有限公司</a></h1>
              <h2>辽宁省 - 沈阳市 - 沈北新区</h2>
              <h3>主营：工艺设计/建厂服务/深化设计</h3>
            </li>
            <li>
              <h1><a href="#">沈阳卫德住宅工业化科技有限公司</a></h1>
              <h2>辽宁省 - 沈阳市 - 沈北新区</h2>
              <h3>主营：工艺设计/建厂服务/深化设计</h3>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <!-- *************col01************* end --> 
    
    <!-- *************col01************* start -->
    <div class="colone">
      <div class="colone_tit">
        <h1><span>4F</span>构件运输</h1>
        <h2><a href="#">三明治墙</a><a href="#">三明治墙</a><a href="#">三明治墙</a><a href="#">三明治墙</a><a href="#">三明治墙</a></h2>
      </div>
      <div class="colone_cnt">
        <div class="c_c_ber">
          <h1 style="color:#ff822f; font-size:20px; text-align:center; padding-top:150px;">相关板块<br>
            banne</h1>
        </div>
        <div class="c_c_cp">
          <ul>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_04.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_04.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_04.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_04.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
          </ul>
          <div class="xian"></div>
          <ul class="cpj">
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_04.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_04.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_04.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
            <li>
              <div class="cp_img"><a href="#"><img src="DWF/photo_04.jpg" alt="产品" title="产品"></a></div>
              <h1><a href="#">预制楼梯板 T22-3F</a></h1>
              <h2>沈阳卫德住宅工业化科技</h2>
            </li>
          </ul>
        </div>
        <div class="c_c_qy">
          <div class="c_c_qy_tit">
            <h1>推荐厂商</h1>
          </div>
          <ul>
            <li>
              <h1><a href="#">沈阳卫德住宅工业化科技有限公司</a></h1>
              <h2>辽宁省 - 沈阳市 - 沈北新区</h2>
              <h3>主营：工艺设计/建厂服务/深化设计</h3>
            </li>
            <li>
              <h1><a href="#">沈阳卫德住宅工业化科技有限公司</a></h1>
              <h2>辽宁省 - 沈阳市 - 沈北新区</h2>
              <h3>主营：工艺设计/建厂服务/深化设计</h3>
            </li>
            <li>
              <h1><a href="#">沈阳卫德住宅工业化科技有限公司</a></h1>
              <h2>辽宁省 - 沈阳市 - 沈北新区</h2>
              <h3>主营：工艺设计/建厂服务/深化设计</h3>
            </li>
            <li>
              <h1><a href="#">沈阳卫德住宅工业化科技有限公司</a></h1>
              <h2>辽宁省 - 沈阳市 - 沈北新区</h2>
              <h3>主营：工艺设计/建厂服务/深化设计</h3>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <!-- *************col01************* end --> 
    
    <!-- 长条广告 start -->
    <div class="ctber01"> <img src="DWF/ber01.jpg"> </div>
    <!-- 长条广告 end -->
    
    <div class="c"></div>
    <!-- 资讯 start -->
    <div class="information"> 
      
      <!-- 行业动态 start -->
      <div class="infonr">
        <div class="infonr_tit">
          <h1>行业动态</h1>
          <a href="#">更多&gt;&gt;</a></div>
        <div class="infonr_cnt">
          <ul>
            <li>
              <div class="infonr_img"><a href="#"></a></div>
              <div class="infonr_txt">
                <h1><a href="#">巩固发展前沿阵地 吊顶招商要做到</a></h1>
                <p>企业与经销商的良好合作不仅关乎品牌渠道的维护、产品的销量，甚至还直接对企业的生存发展造成影响。因此，如何提升对经销商的服务，留住真正优质的经销商，实现共同进步和成长</p>
                <span>2015-5-23</span> </div>
            </li>
            <li>
              <div class="infonr_img"><a href="#"></a></div>
              <div class="infonr_txt">
                <h1><a href="#">巩固发展前沿阵地 吊顶招商要做到</a></h1>
                <p>企业与经销商的良好合作不仅关乎品牌渠道的维护、产品的销量，甚至还直接对企业的生存发展造成影响。因此，如何提升对经销商的服务，留住真正优质的经销商，实现共同进步和成长</p>
                <span>2015-5-23</span> </div>
            </li>
            <li>
              <div class="infonr_img"><a href="#"></a></div>
              <div class="infonr_txt">
                <h1><a href="#">巩固发展前沿阵地 吊顶招商要做到</a></h1>
                <p>企业与经销商的良好合作不仅关乎品牌渠道的维护、产品的销量，甚至还直接对企业的生存发展造成影响。因此，如何提升对经销商的服务，留住真正优质的经销商，实现共同进步和成长</p>
                <span>2015-5-23</span> </div>
            </li>
          </ul>
        </div>
      </div>
      <!-- 行业动态 end --> 
      
      <!-- 企业动态 start -->
      <div class="infonr wjw">
        <div class="infonr_tit">
          <h1>企业动态</h1>
          <a href="#">更多&gt;&gt;</a></div>
        <div class="infonr_cnt">
          <ul>
            <li>
              <div class="infonr_img"><a href="#"></a></div>
              <div class="infonr_txt">
                <h1><a href="#">巩固发展前沿阵地 吊顶招商要做到</a></h1>
                <p>企业与经销商的良好合作不仅关乎品牌渠道的维护、产品的销量，甚至还直接对企业的生存发展造成影响。因此，如何提升对经销商的服务，留住真正优质的经销商，实现共同进步和成长</p>
                <span>2015-5-23</span> </div>
            </li>
            <li>
              <div class="infonr_img"><a href="#"></a></div>
              <div class="infonr_txt">
                <h1><a href="#">巩固发展前沿阵地 吊顶招商要做到</a></h1>
                <p>企业与经销商的良好合作不仅关乎品牌渠道的维护、产品的销量，甚至还直接对企业的生存发展造成影响。因此，如何提升对经销商的服务，留住真正优质的经销商，实现共同进步和成长</p>
                <span>2015-5-23</span> </div>
            </li>
            <li>
              <div class="infonr_img"><a href="#"></a></div>
              <div class="infonr_txt">
                <h1><a href="#">巩固发展前沿阵地 吊顶招商要做到</a></h1>
                <p>企业与经销商的良好合作不仅关乎品牌渠道的维护、产品的销量，甚至还直接对企业的生存发展造成影响。因此，如何提升对经销商的服务，留住真正优质的经销商，实现共同进步和成长</p>
                <span>2015-5-23</span> </div>
            </li>
          </ul>
        </div>
      </div>
      <!-- 企业动态 end --> 
      
      <!-- 政策法规 start -->
      <div class="infonr wjr">
        <div class="infonr_tit">
          <h1>政策法规</h1>
          <a href="#">更多&gt;&gt;</a></div>
        <div class="infonr_cnt">
          <ul>
            <li>
              <div class="infonr_img"><a href="#"></a></div>
              <div class="infonr_txt">
                <h1><a href="#">巩固发展前沿阵地 吊顶招商要做到</a></h1>
                <p>企业与经销商的良好合作不仅关乎品牌渠道的维护、产品的销量，甚至还直接对企业的生存发展造成影响。因此，如何提升对经销商的服务，留住真正优质的经销商，实现共同进步和成长</p>
                <span>2015-5-23</span> </div>
            </li>
            <li>
              <div class="infonr_img"><a href="#"></a></div>
              <div class="infonr_txt">
                <h1><a href="#">巩固发展前沿阵地 吊顶招商要做到</a></h1>
                <p>企业与经销商的良好合作不仅关乎品牌渠道的维护、产品的销量，甚至还直接对企业的生存发展造成影响。因此，如何提升对经销商的服务，留住真正优质的经销商，实现共同进步和成长</p>
                <span>2015-5-23</span> </div>
            </li>
            <li>
              <div class="infonr_img"><a href="#"></a></div>
              <div class="infonr_txt">
                <h1><a href="#">巩固发展前沿阵地 吊顶招商要做到</a></h1>
                <p>企业与经销商的良好合作不仅关乎品牌渠道的维护、产品的销量，甚至还直接对企业的生存发展造成影响。因此，如何提升对经销商的服务，留住真正优质的经销商，实现共同进步和成长</p>
                <span>2015-5-23</span> </div>
            </li>
          </ul>
        </div>
      </div>
      <!-- 政策法规 end --> 
      
    </div>
    <!-- 资讯 end --> 
    
    <!-- 知名企业 start -->
	<jsp:include page="/index/advert.jsp" />
    <!-- 知名企业 end --> 
    
  </div>
  <!-- 主要内容 end --> 
  
  <!-- 结尾 start -->
  <jsp:include page="/bottom.jsp" />
  <!-- 结尾 end --> 
  
</div>
</body>
</html>
