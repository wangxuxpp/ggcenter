/******************************************************************
 * 初始化
 */
$(document).ready(function(){
	saveCheck();
});

/******************************************************************
 * 保存验证
 */
function saveCheck(){
	var options = {
		formName: "form",
	    elements: {
	     	"C_OLD_PASSWORD": {
	     		required: true,
	        	maxlength: 50
	     	},
	     	"C_NEW_PASSWORD": {
	     		required: true,
	        	maxlength: 50,
	        	minlength:6
	     	},
	     	"C_BOU_PASSWORD": {
	     		required: true,
	        	maxlength: 50,
	        	minlength:6
	     	}
		}
	};
	validate = new createValidate(options);
}

/******************************************************************
 * 保存方法
 */
function saveBtn(){
	
	if($("#C_NEW_PASSWORD").val() != $("#C_BOU_PASSWORD").val()){
		swin.showError("新密码与确认密码不相同，新重新填写!");
		$("#C_BOU_PASSWORD").position();
        return;
	}
	if(validate.checkFrm() == true){
		var form = document.getElementById("form");
		form.action = "user.do?method=updatePassword";
		ajaxRequest(form, {
			success:function(result){},
			closeFun:Util.onClose
		});
	}
}
