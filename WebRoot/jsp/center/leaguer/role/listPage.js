var grid = null;

/******************************************************************
 * 初始化
 */
$(document).ready(function(){
	$("#selBtn").bind("click", searchFunc);
	$("#insBtn").bind("click", insertFunc);
	$("#updBtn").bind("click", updateFunc);
	$("#delBtn").bind("click", deleteFunc);
	$("#roleBtn").bind("click", roleFunc);
	
	var obj = {
		grid: "gridTable",
		requestUrl : "role.do?method=list",
		paging: false,
		gridObj: "grid",
		colModel: [
			{ name: "ID", label: "编号", index: "ID", hidden: true},
			{ name: "C_NAME", label: "名称", index: "C_NAME", width: "150"},
			{ name: "C_MEMO", label: "备注", index: "C_MEMO", width: "150"}
		]
	};
	grid = new Grid(obj);
});

/******************************************************************
 * 查询函数
 */
function searchFunc(){
	grid.searchGrid();
}
 
/******************************************************************
 * 新增
 */
var insertFunc = function(){
	swin.openWinDialog("roleAdd", "role.do?method=savePage", "角色新增", 400, 280, returnClose);
}

/******************************************************************
 * 修改
 */
var updateFunc = function(){
	if(grid.getGridCurrentRowId() == null){
 		swin.showError("请选择要修改的角色")
        return;
 	}
 	var id = grid.getGridCurrentRowData().ID;
	swin.openWinDialog("roleMod", "role.do?method=savePage&ID="+id, "角色修改", 400, 280, returnClose);
}

/******************************************************************
 * 删除
 */
var deleteFunc = function(){
	if(grid.getGridCurrentRowId() == null){
 		swin.showError("请选择要删除的角色")
        return;
 	}
	swin.showConfirm("确认要删除选中角色吗？", 
	function(){
	 	var id = grid.getGridCurrentRowData().ID;
	 	ajaxRequest("role.do?method=delete", {
			success:function(result){
				grid.refresh();
			}, data:{ID:id}
			
		});
	});
}

/******************************************************************
 * 权限
 */
var roleFunc = function(){
	if(grid.getGridCurrentRowId() == null){
 		swin.showError("请选择角色")
        return;
 	}
 	var id = grid.getGridCurrentRowData().ID;
	swin.openWinDialog("roleAdd", "role.do?method=authorityPage&ID="+id, "角色权限",  600, 500);
}

/******************************************************************
 * 弹出页关闭后回调方法
 */
var returnClose = function(returnValue){
	grid.refresh();
}
