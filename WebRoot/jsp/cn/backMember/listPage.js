/******************************************************************
 * 初始化
 */
$(document).ready(function(){
	swin.showSuccess("1dsfasdf");
});

/******************************************************************
 * 查询函数
 */
function searchFunc(){
	ajaxRequest("backMember.do?method=list", {
		success:function(result){
			alert("成功");
		}
	});
};
