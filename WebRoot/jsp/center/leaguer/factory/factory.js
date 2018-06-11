/******************************************************************
 * 初始化
 */
var verify = true ;
$(document).ready(function(){
	param = new Object();
	param.proId = 'C_PRO';
	param.cityId = 'C_CITY';
	param.proValue=$("#C_PRO").val();
	param.cityValue=$("#C_CITY").val();
	new ProCityValue("pcDiv",param);
});

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
		document.getElementById('factory_verify').className='bdts';
		$("#factory_verify").html('');
	}
}

/******************************************************************
 * 地址校验
 */
var addressClick = function(){
	var factory = /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9_]|(?=[\x21-\x7e]+)[^A-Za-z0-9]){2,200}$/; 
	if($("#C_ADDRESS").val() == ""){
		document.getElementById('address_verify').className='bdts tscw';
		$("#address_verify").html('请填写地址！');
		verify = false;
	}else if(!factory.test($("#C_ADDRESS").val())){
		document.getElementById('address_verify').className='bdts tscw';
		$("#address_verify").html('地址长度只能在2-200位字符之间！');
		verify = false;
	}else{
		document.getElementById('address_verify').className='bdts';
		$("#address_verify").html('');
	}
}

/******************************************************************
 * 经营范围
 */
var runareaClick = function(){
	var factory = /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9_]|(?=[\x21-\x7e]+)[^A-Za-z0-9]){2,500}$/; 
	if($("#C_RUNAREA").val() == ""){
		document.getElementById('runarea_verify').className='bdts tscw';
		$("#runarea_verify").html('请填写经营范围！');
		verify = false;
	}else if(!factory.test($("#C_RUNAREA").val())){
		document.getElementById('runarea_verify').className='bdts tscw';
		$("#runarea_verify").html('经营范围长度只能在2-500位字符之间！');
		verify = false;
	}else{
		document.getElementById('runarea_verify').className='bdts';
		$("#runarea_verify").html('');
	}
}

/******************************************************************
 * 手机号码
 */
var telClick = function(){
	var factory = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	if($("#C_TEL").val() == ""){
		document.getElementById('tel_verify').className='bdts tscw';
		$("#tel_verify").html('请填写手机号码！');
		verify = false;
	}else if(!factory.test($("#C_TEL").val())){
		document.getElementById('tel_verify').className='bdts tscw';
		$("#tel_verify").html('请输入有效的手机号码！');
		verify = false;
	}else{
		document.getElementById('tel_verify').className='bdts';
		$("#tel_verify").html('');
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
		document.getElementById('name_verify').className='bdts';
		$("#name_verify").html('');
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
		document.getElementById('mail_verify').className='bdts';
		$("#mail_verify").html('');
	}
}

/******************************************************************
 * 保存
 */
function saveBtn(){
	if($("#C_PRO").val()!=""||$("#C_CITY").val()!=""){
		document.getElementById('pro_city').className='bdts';
		$("#pro_city").html('');
	}
	
	var leng = Util.getStrLen($("#C_INFO").val());
	if(leng>1000){
		document.getElementById('info_verify').className='bdts tscw';
		$("#info_verify").html('公司介绍不能超过1000字符！');
		verify = false;
	}
	
	factoryClick(verify);
	addressClick(verify);
	runareaClick(verify);
	telClick(verify);
	nameClick(verify);
	mailClick(verify);
	if(verify == false){
		verify = true
		return;
	}
	
	ajaxRequest(document.getElementById("form"), {
		isShowMessageBox : false,
		success:function(result){
			if(result.jsonType == 'success'){
					swin.showSuccess("提交成功！")
			}else{
				if(result.jsonMessage.indexOf('C_FACTORY_NAME') != -1){
					document.getElementById('factory_verify').className='bdts tscw';
					$("#factory_verify").html('企业名称已存在！');
				}
				return;
			}
		}
	});
}

function undoBtn(){
	swin.showConfirm("确认要取消吗？", function(){
		location.reload();
	});
}
