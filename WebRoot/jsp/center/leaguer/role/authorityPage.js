/******************************************************************
 * 初始化
 */
$(document).ready(function(){
	var height = $(window).height()- 60;
	$("#limitDivList").css({"height":height});
});

/******************************************************************
 * 保存方法
 */
function saveBtn(){
	ajaxRequest(document.getElementById("form"), {
		success:function(result){},
		closeFun:Util.onClose
	});
}

/******************************************************************
 * 全选
 */
function allfull(obj){
	var checked = obj.checked;
	var element = document.getElementById("limitDivList");
	var inputElements = element.getElementsByTagName('input');
	var n = inputElements.length;
	for(var i = 0 ; i < n ; i = i + 1){
		var elem = inputElements[i];
		if(elem.type == 'checkbox'){
			elem.checked = checked;
			var ids = (elem.id).split("_");
			if(checked){
		 		$("#SYS_PAGESUB_"+ids[1]).val(ids[1]);
			}else{
		 		$("#SYS_PAGESUB_"+ids[1]).val(0);
			}
		}
	}
}

/******************************************************************
 * 一级模块全选
 */
function classfull(id, obj){
	var checked = obj.checked;
	var element = document.getElementById("class_"+id);
	var inputElements = element.getElementsByTagName('input');
	var n = inputElements.length;
	for(var i = 0 ; i < n ; i = i + 1){
		var elem = inputElements[i];
		if(elem.type == 'checkbox'){
			elem.checked = checked;
			var ids = (elem.id).split("_");
			if(checked){
		 		$("#SYS_PAGESUB_"+ids[1]).val(ids[1]);
			}else{
		 		$("#SYS_PAGESUB_"+ids[1]).val(0);
			}
		}
	}
}
