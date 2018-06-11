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
	     	"I_SYS_ROLE_ID": {
	     		required: true,
	        	maxlength: 500
	     	},
	     	"C_LOGINNAME": {
	     		required: true,
	        	maxlength: 500,
	        	minlength:4,
	        	maxlength:100
	     	},
	     	"C_NAME": {
	       		required: true,
	         	maxlength: 50,
	        	minlength:4,
	        	maxlength:25
	     	},
	     	"C_PASSWORD": {
	     		required: true,
	        	maxlength: 50,
	        	minlength:6,
	        	maxlength:20
	     	},
	     	"C_MAIL": {
	     		required: true,
	        	maxlength: 500,
	        	email:true
	     	},
	     	"C_MEMO": {
	        	maxlength: 500
	     	}
		}
	};
	validate = new createValidate(options);
}

/******************************************************************
 * 保存方法
 */
function saveBtn(){
	if(validate.checkFrm() == true){
		var id = $("#ID").val();
		var form = document.getElementById("form");
		if(id != null && id != "") {
			form.action = "user.do?method=update";
			ajaxRequest(form, {
				success:function(result){},
				closeFun:Util.onClose
			});
		} else {
			form.action = "user.do?method=save";
			ajaxRequest(form, {
				success:function(result){
					form.reset();
				}
			});
		}
	}
}
