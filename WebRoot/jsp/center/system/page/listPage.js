var grid = null;
var gridSub = null;

/******************************************************************
 * 初始化
 */
$(document).ready(function(){
	var height = ($(window).height()-278)/2;
	
	$("#insBtn").bind("click", insertFunc);
	$("#updBtn").bind("click", updateFunc);
	$("#delBtn").bind("click", deleteFunc);
 	var obj = {
		height: height,
		grid: "gridTable",
		pager: "pagerDiv",
		requestUrl: "page.hm?method=list",
		addfunc: insertFunc,
		editfunc: updateFunc,
		dblclickRow: updateFunc,
		delfunc: deleteFunc,
		selectRow: selectRow,
		colModel:[
			{ name: "ID", label: "编号", index: "ID", width: "150"},
			{ name: "C_NAME", label: "模块名称", index: "C_NAME", width: "150"},
			{ name: "C_URL", label: "地址", index: "C_URL", width: "150"},
			{ name: "C_IMAGENAME", label: "控件标识", index: "C_IMAGENAME", width: "150"},
			{ name: "C_MEMO", label: "备注", index: "C_MEMO", width: "350"}
		],
		sortname: "ID"
	};
	grid = new jqGrid(obj);	
	
	$("#insBtnSub").bind("click", insertFuncSub);
	$("#updBtnSub").bind("click", updateFuncSub);
	$("#delBtnSub").bind("click", deleteFuncSub);
 	var objsub = {
		height: height,
		grid: "gridTableSub",
		pager: "pagerDivSub",
		addfunc: insertFuncSub,
		editfunc: updateFuncSub,
		dblclickRow: updateFuncSub,
		delfunc: deleteFuncSub,
		colModel:[
			{ name: "ID", label: "编号", index: "ID",  width: "150"},
			{ name: "I_SYS_PAGE_ID", label: "I_SYS_PAGE_ID", index: "I_SYS_PAGE_ID",  width: "150"},
			{ name: "C_NAME", label: "权限名称", index: "C_NAME", width: "150"},
			{ name: "C_URL", label: "地址", index: "C_URL", width: "150"},
			{ name: "C_IMAGENAME", label: "控件标识", index: "C_IMAGENAME", width: "150"},
			{ name: "C_MEMO", label: "备注", index: "C_MEMO", width: "350"}
		],
		sortname: "ID"
	};
	gridSub = new jqGrid(objsub);
});

/******************************************************************
 * 刷新子列表信息
 */
function selectRow(rowId){
	var rowData = grid.getGridCurrentRowDataFormRowId(rowId);
 	var subUrl = "page.hm?method=listSub&I_SYS_PAGE_ID=" + rowData.ID;
 	gridSub.setGridCurrentUrl(subUrl);
 	gridSub.refresh();
}

/******************************************************************
 * 新增
 */
function insertFunc(){
	swin.openWinDialog("pageAdd", "page.hm?method=savePage", "模块新增", 330, 303, returnClose);
}

/******************************************************************
 * 修改
 */
function updateFunc(){
	if(grid.getGridCurrentRowId() == null){
 		swin.showError("请选择要修改的模块")
        return;
 	}
 	var id = grid.getGridCurrentRowData().ID;
	swin.openWinDialog("pageMod", "page.hm?method=savePage&ID="+id, "模块修改", 330, 313, returnClose);
}

/******************************************************************
 * 弹出页关闭后回调方法
 */
function returnClose(returnValue){
	grid.refresh();
	selectRow(0);
}

/******************************************************************
 * 删除
 */
function deleteFunc(){
	if(grid.getGridCurrentRowId() == null){
 		swin.showError("请选择要删除的模块")
        return;
 	}
 	if(grid.getGridCurrentRowData().I_DEL == "删除"){
 		swin.showError("选中模块已删除，请重新选择！");
        return;
 	}
	swin.showConfirm("确认要删除选中模块吗？", doDelete);
}
function doDelete(){
 	var id = grid.getGridCurrentRowData().ID;
 	ajaxRequest("page.hm?method=delete", {
		success:function(result){
			grid.refresh();
			selectRow(0);
		}, data:{ID:id}
		
	});
}

/******************************************************************
 * 新增
 */
function insertFuncSub(){
 	var id = grid.getGridCurrentRowData().ID;
	swin.openWinDialog("pageSubAdd", "page.hm?method=saveSubPage&mainId="+id, "模块权限新增", 330, 313, returnCloseSub);
}

/******************************************************************
 * 修改
 */
function updateFuncSub(){
	if(grid.getGridCurrentRowId() == null){
 		swin.showError("请选择要添加权限的模块")
        return;
 	}
	if(gridSub.getGridCurrentRowId() == null){
 		swin.showError("请选择要修改的模块权限")
        return;
 	}
 	var id = gridSub.getGridCurrentRowData().ID;
 	var mainId = grid.getGridCurrentRowData().ID;
	swin.openWinDialog("pageSubMod", "page.hm?method=saveSubPage&ID="+id+"&mainId="+mainId, "模块权限修改", 330, 313, returnCloseSub);
}

/******************************************************************
 * 弹出页关闭后回调方法
 */
function returnCloseSub(returnValue){
	gridSub.refresh();
}
 
/******************************************************************
 * 删除
 */
function deleteFuncSub(){
	if(gridSub.getGridCurrentRowId() == null){
 		swin.showError("请选择要删除的模块权限")
        return;
 	}
	if(gridSub.getGridCurrentRowData().I_DEL == "删除"){
 		swin.showError("选中模块权限已删除，请重新选择！");
        return;
 	}
 	swin.showConfirm("确认要删除选中模块权限吗？", doDeleteSub);
}
function doDeleteSub(){
 	var id = gridSub.getGridCurrentRowData().ID;
	ajaxRequest("page.hm?method=deleteSub", {
		success:function(result){
			gridSub.refresh();
		}, data:{ID:id}
		
	});
}
