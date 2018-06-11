var verify = true ;
$(document).ready(function(){
	$("#D_DATE").datepicker();
});

/******************************************************************
 * 法定代表人校验
 */
var legalPersonClick = function(){
	var factory = /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9_]){2,25}$/; 
	if($("#C_LEGALPERSON").val() == ""){
		document.getElementById('legalPerson_verify').className='bdts tscw';
		$("#legalPerson_verify").html('请填写法定代表人！');
		verify = false;
	}else if(!factory.test($("#C_LEGALPERSON").val())){
		document.getElementById('legalPerson_verify').className='bdts tscw';
		$("#legalPerson_verify").html('法定代表人长度只能在2-25位字符之间！');
		verify = false;
	}else{
		document.getElementById('legalPerson_verify').className='bdts';
		$("#legalPerson_verify").html('');
	}
}

/******************************************************************
 * 法人身份证
 */
var numberClick = function(){
	var factory = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
	if($("#C_NUMBER").val() == ""){
		document.getElementById('number_verify').className='bdts tscw';
		$("#number_verify").html('请填写法人身份证！');
		verify = false;
	}else if(!factory.test($("#C_NUMBER").val())){
		document.getElementById('number_verify').className='bdts tscw';
		$("#number_verify").html('身份证输入不合法！');
		verify = false;
	}else{
		document.getElementById('number_verify').className='bdts';
		$("#number_verify").html('');
	}
}

/******************************************************************
 * 成立日期
 */
var dateClick = function(){
	if($("#D_DATE").val() == ""){
		document.getElementById('date_verify').className='bdts tscw';
		$("#date_verify").html('请填写成立日期！');
		verify = false;
	}else{
		document.getElementById('date_verify').className='bdts';
		$("#date_verify").html('');
	}
}

/******************************************************************
 * 经营范围
 */
var runareaClick = function(){
	var factory = /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9_]|(?=[\x21-\x7e]+)[^A-Za-z0-9]){4,500}$/; 
	if($("#C_RUNAREA").val() == ""){
		document.getElementById('runarea_verify').className='bdts tscw';
		$("#runarea_verify").html('请填写经营范围！');
		verify = false;
	}else if(!factory.test($("#C_RUNAREA").val())){
		document.getElementById('runarea_verify').className='bdts tscw';
		$("#runarea_verify").html('经营范围长度只能在4-500位字符之间！');
		verify = false;
	}else{
		document.getElementById('runarea_verify').className='bdts';
		$("#runarea_verify").html('');
	}
}

/******************************************************************
 * 注册资金
 */
var capitalClick = function(){
	var factory = /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9_]){4,25}$/; 
	if($("#C_CAPITAL").val() == ""){
		document.getElementById('capital_verify').className='bdts tscw';
		$("#capital_verify").html('请填写注册资金！');
		verify = false;
	}else if(!factory.test($("#C_CAPITAL").val())){
		document.getElementById('capital_verify').className='bdts tscw';
		$("#capital_verify").html('注册资金长度只能在4-25位字符之间！');
		verify = false;
	}else{
		document.getElementById('capital_verify').className='bdts';
		$("#capital_verify").html('');
	}
}

/******************************************************************
 * 保存
 */
function saveBtn(){
	legalPersonClick(verify);
	numberClick(verify);
	dateClick(verify);
	runareaClick(verify);
	capitalClick(verify);
	
	if(verify == false){
		verify = true
		return;
	}
	
	ajaxRequest(document.getElementById("form"), {
		success: function(result){
			window.location.href = "permit.do?method=finish";
		}
	});
}
