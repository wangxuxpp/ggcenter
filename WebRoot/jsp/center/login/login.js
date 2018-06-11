var indexNum = 1;

/******************************************************************
 * 初始化
 */
$(document).ready(function(){
	showCaptcha();
	Util.setKeydown("password", doLogin);
});

/******************************************************************
 * 登录
 */
function doLogin(){
	$("#showMessage").html("");
	if($("#factoryName").val()==""){
		$("#showMessage").html("<h1>请输入【登录类别】！</h1>");
		$("#factoryName").focus();
		return;
	}
	if($("#loginName").val()==""){
		$("#showMessage").html("<h1>请输入【登录用户】！</h1>");
		$("#loginName").focus();
		return;
	}
	if($("#password").val()==""){
		$("#showMessage").html("<h1>请输入【登录密码】！</h1>");
		$("#password").focus();
		return;
	}
	var value = $("#password").val()
	if(!(/^[A-Za-z0-9\-\_]+$/.test(value))){
		$("#showMessage").html("<h1>【密码只能包括英文字母和数字】！</h1>");
		return;
	}
	
	if(indexNum > 3){
		var cDate = $("#captcha_date").val();
		var date = Util.getDate("long");
		if(parseInt(date, 10) - parseInt(cDate, 10) > 100){
			$("#showMessage").html("<h1>验证码超时，请刷新一下！</h1>");
			return;
		}
	}
	
	ajaxRequest(document.getElementById("form"), {
		isShowMessageBox : false,
		success:function(result){
			if(result.jsonType == 'success'){
				window.location.href = "index.jsp";
			}else{
				$("#showMessage").html("<h1>"+result.jsonMessage+"</h1>");
				if(indexNum > 3){
					$("#captcha_li").show();
					showCaptcha();
				}
				indexNum = indexNum + 1;
			}
		}
	});
}

/******************************************************************
 * 获取验证码
 */
function showCaptcha(){
	var date = Util.getDate("long");
	$("#captcha_date").val(date);
	document.getElementById('captchaCode').src = "captcha.hm?method=getCaptcha&radomDate="+date;
}
