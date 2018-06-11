var grid = null;

/******************************************************************
 * 初始化
 */
$(document).ready(function(){
	$("#selBtn").bind("click", searchFunc);
	$("#insBtn").bind("click", insertFunc);
	$("#updBtn").bind("click", updateFunc);
	$("#delBtn").bind("click", deleteFunc);
	$("#conBtn").bind("click", reverseFunc);
	
	var obj = {
		grid: "gridTable",
		requestUrl: "user.do?method=list",
		paging: false,
		gridObj: "grid",
		colModel: [
			{ name: "ID", label: "编号", index: "ID", hidden: true},
			{ name: "C_SYS_ROLE_NAME", label: "角色名称", index: "C_SYS_ROLE_NAME", width: "150"},
			{ name: "C_LOGINNAME", label: "用户名", index: "C_LOGINNAME", width: "150"},
			{ name: "C_NAME", label: "昵称", index: "C_NAME", width: "150"},
			{ name: "C_MAIL", label: "邮箱", index: "C_MAIL", width: "350"},
			{ name: "C_MEMO", label: "备注", index: "C_MEMO", width: "350"}
		]
	};
	grid = new Grid(obj);
});

/******************************************************************
 * 查询函数
 */
function searchFunc(){
	grid.searchGrid();
};
 
/******************************************************************
 * 新增
 */
function insertFunc(){
	swin.openWinDialog("userAdd", "user.do?method=savePage", "用户新增", 400, 440, returnClose);
}

/******************************************************************
 * 修改
 */
function updateFunc(){
	if(grid.getGridCurrentRowId() == null){
 		swin.showError("请选择要修改的用户")
        return;
 	}
 	var id = grid.getGridCurrentRowData().ID;
	swin.openWinDialog("userMod", "user.do?method=savePage&ID="+id, "用户修改", 400, 440, returnClose);
}

/******************************************************************
 * 删除
 */
function deleteFunc(){
	if(grid.getGridCurrentRowId() == null){
 		swin.showError("请选择要删除的用户")
        return;
 	}
 	if(grid.getGridCurrentRowData().I_DEL == "删除"){
 		swin.showError("选中用户已删除，请重新选择！");
        return;
 	}
 	swin.showConfirm("确认要删除选中用户吗？", function(){
 		var id = grid.getGridCurrentRowData().ID;
	 	ajaxRequest("user.do?method=delete", {
			success:function(result){
				grid.refresh();
			}, data:{ID:id}
			
		});
 	});
}

/******************************************************************
 * 重置密码
 */
function reverseFunc(){
	if(grid.getGridCurrentRowId() == null){
 		swin.showError("请选择要重置密码的用户")
        return;
 	}
 	if(grid.getGridCurrentRowData().I_DEL == "删除"){
 		swin.showError("选中用户已删除，不用重置密码，请重新选择！");
        return;
 	}
 	swin.showConfirm("确认要把选中用户重置密码？", function(){
 		var id = grid.getGridCurrentRowData().ID;
		ajaxRequest("user.do?method=reversePassword", {
			success:function(result){}, data:{ID:id}
			
		});
 	});
}

/******************************************************************
 * 弹出页关闭后回调方法
 */
function returnClose(returnValue){
	grid.refresh();
}
