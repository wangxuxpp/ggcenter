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
	     	"C_NAME": {
	       		required: true,
	         	maxlength: 200
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
			form.action = "role.do?method=update";
			ajaxRequest(form, {
				success:function(result){},
				closeFun:Util.onClose
			});
		} else {
			form.action = "role.do?method=save";
			ajaxRequest(form, {
				success:function(result){
					form.reset();
				}
			});
		}
	}
}
