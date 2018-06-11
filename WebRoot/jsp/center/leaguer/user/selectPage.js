var grid;

/******************************************************************
 * 初始化
 */
$(document).ready(function(){
	var height = $(window).height()-144;
	$("#selBtn").bind("click", searchFunc);
	$("#seaBtn").bind("click", selectFunc);
	
	var obj = {
		height: height,
		grid: "gridTable",
		pager: "pagerDiv",
		requestUrl : "user.do?method=selectList",
		colModel:[
			{ name: "ID", label: "编号", index: "ID", hidden: true},
			{ name: "C_SYS_ROLE_NAME", label: "角色名称", index: "C_SYS_ROLE_NAME", width: "150"},
			{ name: "C_LOGINNAME", label: "用户名", index: "C_LOGINNAME", width: "150"},
			{ name: "C_NAME", label: "昵称", index: "C_NAME", width: "150"},
			{ name: "C_MAIL", label: "邮箱", index: "C_MAIL", width: "350"},
			{ name: "C_MEMO", label: "备注", index: "C_MEMO", width: "350"}
		],
		sortname: "ID"
	};
	grid = new jqGrid(obj);	
	grid.refresh();
});

/******************************************************************
 * 查询函数
 */
function searchFunc(){
	grid.searchGrid();
};

/******************************************************************
 * 选中
 */
function selectFunc(){
	if(grid.getGridCurrentRowId() == null){
 		swin.showError("请选择操作员")
        return;
 	}
 	if(grid.getGridCurrentRowData().I_DEL == "删除"){
 		swin.showError("选中操作员已删除，请重新选择！");
        return;
 	}
 	var obj = new Object();
 	obj.ID = grid.getGridCurrentRowData().ID;
	obj.C_NAME = grid.getGridCurrentRowData().C_NAME;
	obj.C_LOGINNAME = grid.getGridCurrentRowData().C_LOGINNAME;
 	Util.onClose(obj);
}
