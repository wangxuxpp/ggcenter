var verify = true ;

/******************************************************************
 * 初始化
 */
$(document).ready(function(){
	new ProCityValue("pcDiv", {proId:"C_PRO", cityId:"C_CITY", proBtn:pinPro, cityBtn:pinCity});
	showCaptcha();
});

/******************************************************************
 * 获取验证码
 */
function showCaptcha(){
	var date = Util.getDate("long");
	$("#captcha_date").val(date);
	document.getElementById('captchaCode').src = "captcha.hm?method=getCaptcha&radomDate="+date;
}

/******************************************************************
 * 得到省份汉字的每一个字母
 */
function pinPro(value){
	$("#C_PRO_ABBR").val(CC2PY(value));
}

/******************************************************************
 * 得到城市汉字的每一个字母
 */
function pinCity(value){
	$("#C_CITY_ABBR").val(CC2PY(value));
	document.getElementById('pro_city').className='bdts tszq';
	$("#pro_city").html('');
}

/******************************************************************
 * 企业名校验
 */
var factoryClick = function(){
	var factory = /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9_]){4,100}$/; 
	if($("#C_FACTORY_NAME").val() == ""){
		document.getElementById('factory_verify').className='bdts tscw';
		$("#factory_verify").html('请填写企业名称！');
		verify = false;
	}else if(!factory.test($("#C_FACTORY_NAME").val())){
		document.getElementById('factory_verify').className='bdts tscw';
		$("#factory_verify").html('企业名称长度只能在4-100位字符之间！');
		verify = false;
	}else{
		document.getElementById('factory_verify').className='bdts tszq';
		$("#factory_verify").html('');
	}
}

/******************************************************************
 * 邮箱校验
 */
var mailClick = function(){
	var mail_verify = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/; 
	var mail = $("#C_MAIL").val();
	if( mail== ""){
		document.getElementById('mail_verify').className='bdts tscw';
		$("#mail_verify").html('请填写电子邮箱！');
		verify = false;
	}else if(!mail_verify.test(mail)){
		document.getElementById('mail_verify').className='bdts tscw';
		$("#mail_verify").html('电子邮箱格式不正确，请重新填写！');
		verify = false;
	}else{
		document.getElementById('mail_verify').className='bdts tszq';
		$("#mail_verify").html('');
	}
}

/******************************************************************
 * 昵称校验
 */
var nameClick = function(){
	var c_name = /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9_]|(?=[\x21-\x7e]+)[^A-Za-z0-9]){4,20}$/; 
	if($("#C_NAME").val() == ""){
		document.getElementById('name_verify').className='bdts tscw';
		$("#name_verify").html('请填写昵称！');
		verify = false;
	}else if(!c_name.test($("#C_NAME").val())){
		document.getElementById('name_verify').className='bdts tscw';
		$("#name_verify").html('昵称长度只能在4-25位字符之间！');
		verify = false;
	}else{
		document.getElementById('name_verify').className='bdts tszq';
		$("#name_verify").html('');
	}
}

/******************************************************************
 * 用户名校验
 */
var loginNmaeClick = function(){
	var loginName = /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9_]){4,100}$/; 
	if($("#C_LOGINNAME").val() == ""){
		document.getElementById('loginname_verify').className='bdts tscw';
		$("#loginname_verify").html('请填写用户名！');
		verify = false;
	}else if(!loginName.test($("#C_LOGINNAME").val())){
		document.getElementById('loginname_verify').className='bdts tscw';
		$("#loginname_verify").html('用户名长度只能在4-100位字符之间！');
		verify = false;
	}else{
		document.getElementById('loginname_verify').className='bdts tszq';
		$("#loginname_verify").html('');
	}
}

/******************************************************************
 * 密码校验
 */
var passwordClick = function(){
	var pwd = $("#C_PASSWORD").val();
	var verify = /^[\w]{6,12}$/;
	if(pwd == ""){
		document.getElementById('loginpwd_verify').className='bdts tscw';
		$("#loginpwd_verify").html('请填写登陆密码！');
		verify = false;
	}else if(!verify.test(pwd)){
		document.getElementById('loginpwd_verify').className='bdts tscw';
		$("#loginpwd_verify").html('登陆密码长度为6-20位字符！');
		verify = false;
	}else{
		document.getElementById('loginpwd_verify').className='bdts tszq';
		$("#loginpwd_verify").html('');
	}
}

/******************************************************************
 * 确认密码校验
 */
var toPasswordClick = function(){
	if($("#C_PASSWORD_TO").val() == ""){
		document.getElementById('pwd_verify').className='bdts tscw';
		$("#pwd_verify").html('请再次输入密码！');
		verify = false;
	}else if($("#C_PASSWORD").val()!=$("#C_PASSWORD_TO").val()){
		document.getElementById('pwd_verify').className='bdts tscw';
		$("#pwd_verify").html('两次输入的登陆密码不一致！');
		verify = false;
	}else{
		document.getElementById('pwd_verify').className='bdts tszq';
		$("#pwd_verify").html('');
	}
}

/******************************************************************
 * 验证码校验
 */
var captchaClick = function(){
	
	var captcha = Util.getDate("long");
	var original = $("#captcha_date").val();
	if($("#CAPTCHA").val() == ""){
		document.getElementById('captcha_verify').className='bdts tscw';
		$("#captcha_verify").html('请填写验证码！');
		verify = false;
	}else if(original.substr(0,12)!=captcha.substr(0,12)){
		if(original.substr(original.length-2)<captcha.substr(captcha.length-2)){
			document.getElementById('captcha_verify').className='bdts tscw';
			$("#captcha_verify").html('验证码超时！');
			showCaptcha();
			verify = false;
		}
	}else{
		document.getElementById('captcha_verify').className='bdts';
		$("#captcha_verify").html('');
	}
}

/******************************************************************
 * 协议校验
 */
var check= function(){
 var checkbox = document.getElementById('san_01');
  if(checkbox.checked){ 
  	document.getElementById("submit_save").disabled="";
  	document.getElementById("submit_save").style.backgroundColor=""; 
  }else{ 
     document.getElementById("submit_save").disabled="disabled";
     document.getElementById("submit_save").style.backgroundColor="#d2d2d2"; 
  }
}

/******************************************************************
 * 注册
 */
function saveBtn(){
	if($("#C_PRO").val()==""||$("#C_CITY").val()==""){
		document.getElementById('pro_city').className='bdts tscw';
		$("#pro_city").html('请选择企业所在地！');
		verify = false;
	}
	
	factoryClick(verify);
	mailClick(verify);
	nameClick(verify);
	loginNmaeClick(verify);
	passwordClick(verify);
	toPasswordClick(verify);
	captchaClick(verify);
	
	if(verify == false){
		verify = true
		return;
	}
	
	ajaxRequest(document.getElementById("form"), {
		isShowMessageBox : false,
		success:function(result){
			if(result.jsonType == 'success'){
					window.location.href = "register.hm?method=showVerify&cCode="+result.C_CODE+"&cMail="+result.C_MAIL;
			}else{
				if(result.jsonMessage.indexOf('C_FACTORY_NAME') != -1){
					document.getElementById('factory_verify').className='bdts tscw';
					$("#factory_verify").html('企业名称已存在！');
				}
				if(result.jsonMessage.indexOf('CAPTCHA') != -1){
					document.getElementById('captcha_verify').className='bdts tscw';
					$("#captcha_verify").html('验证码错误！');
				}
				return;
			}
		}
	});
}
