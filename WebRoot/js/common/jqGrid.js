/******************************************************************
 * 封装jqGrid
 * 
 * 
 * @author wx
 * @version $20140319$
 * 
 * 版权所有(C)【卫德·住工科技】
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history
 * 
 */
var jqGrid = function(options) {
	//grid高度
	this._height = options.height != null ? options.height : 150;
	this._width = options.width != null ? options.width : null;
	this._autoWidth = options.autoWidth != null ? options.autoWidth : true;
	this._row = options.row != null ? options.row : 20;
    //创建grid所使用的<table>标签ID
    this._grid = options.grid != null ? "#" + options.grid : null;
    this.__grid = options.grid != null ? options.grid : null;
    
    //控制GRID宽度，如果页面上有两个GRID 该值为另一GRID的ID,并且另一个GRID的_containDiv设为false
    this._slaveGrid = options.slaveGrid != null ? "#"+options.slaveGrid : null;
    //创建pager所使用的<div>标签ID
    this._pager = options.pager != null ? "#" + options.pager : null;
    //设置表格列的属性
    this._colModel = options.colModel != null ? options.colModel : null;
    //设置表格所使用的图标路径
    this._imgpath = options.imgpath != null ? options.imgpath : null;
    //pager上的add方法
    this._addfunc = options.addfunc != null ? options.addfunc : null;
    //pager上的edit方法
    this._editfunc = options.editfunc != null ? options.editfunc : null;
    //pager上的del方法
    this._delfunc = options.delfunc != null ? options.delfunc : null;
    //请求url地址
    this._requestUrl= options.requestUrl != null ? options.requestUrl : "";
    //默认排序列的名称
    this._sortname = options.sortname != null ? options.sortname : "";
    //是否有页尾空行
    this._footerrow = options.footerrow != null ? options.footerrow : false;
    this._userDataOnFooter = false;
	if(this._footerrow){
		this._userDataOnFooter = true;
	}
    //定义是否可以多选
    this._multiselect = options.multiselect != null ? options.multiselect : null;
    this._multiboxonly = false;
    //分页栏按钮
    this._addButtonId = "myGridAddButton_Id";
    this._editButtonId = "myGridEditButton_Id";
    this._delButtonId = "myGridDelButton_Id";
    
    //行双击事件
    this._dblclickRow = options.dblclickRow != null ? options.dblclickRow : null;
    //行选择事件
    this._selectRow = options.selectRow != null ? options.selectRow : null;
    //子表行双击提供三个参数（rowid , rowdata , 子表对象）
    this._subDblClickRow = options.subDblClickRow != null ? options.subDblClickRow : null;
    //子表行选提供三个参数（rowid , rowdata , 子表对象）
    this._subSelectRow = options.subSelectRow != null ? options.subSelectRow : null;
    
    //查询按钮
    this._searchFlag =  options.searchFlag != null ? options.searchFlag : true;
    //刷新按钮
    this._refreshFlag = options.refreshFlag  != null ? options.refreshFlag : true;
    //搜索工具栏
    this._filterToolbar = options.filterToolbar  != null ? options.filterToolbar : true;
    
    //鼠标离开Grid事件
    this._mouseleave = options.mouseleave != null ? options.mouseleave : null;
    
    /**Grid子表选择项*/
    //是否有子表
    this._subGrid = options.subGrid != null ? true : false;
    //设置子表格列的属性
    this._subGridModel = options.subGridModel != null ? options.subGridModel : null;
    //子表请求url地址
    this._subGridUrl = options.subGridUrl != null ? options.subGridUrl : null;
    
    this._containDiv = options.containDiv == false ? options.containDiv : true;
    
    this._loadComplete = options.loadComplete == null ? null : options.loadComplete;
    this._gridComplete = options.gridComplete == null ? null : options.gridComplete;
    
    this._sortorder =  options.sortorder == null ? "desc" : options.sortorder;
    
    this._treeGrid = options.treeGrid == null ? false : options.treeGrid;
    this._treeGridModel = options.treeGridModel == null ? null : options.treeGridModel;
    this._ExpandColClick = options.ExpandColClick == null ? false : options.ExpandColClick;
    this._ExpandColumn = options.ExpandColumn == null ? null : options.ExpandColumn;
    
    this._subGridRowClose = function () {
    	this.p.actionSubGrid = null;
    };
    
    this._subGridRowExpanded = function(subgrid_id, row_id) {
    	
        var sUrl = jQuery(this).jqGrid("getGridParam", "subGridUrl");
        var sCol = jQuery(this).jqGrid("getGridParam", "subGridModel");
        
        var subgrid_table_id = subgrid_id + "_t";  
        jQuery("#" + subgrid_id).html("<table id='" + subgrid_table_id + "'></table>"); 
        
        var rowData = jQuery(this).jqGrid("getRowData", row_id);

		function invokeBackFun(rowid , fun) {
			
			if (rowid == null) {
				return;
			}
			if (fun == null) {
				return;
			}
			var subRowData = subGrid.getRowData(rowid);
            
            fun(rowid , subRowData , subGrid);
            
		};
		
		var _this = this;
		var subGrid = jQuery("#" + subgrid_table_id).jqGrid({
            url: sUrl + "&subGridId=" + rowData.ID, //注意 rowData.ID主表列ID属性值
            datatype: "json",
            ajaxGridOptions: {contentType: "application/x-www-form-urlencoded; charset=utf-8"},
            mtype: "POST",
            colModel: sCol,
            altRows: true, //设置表格zebra-striped值
            altclass: "ui-priority-secondaryRowSec",
            jsonReader: {
               	root: "json_root",
				page: "json_page",
				total: "json_total",
				records: "json_records",
				repeatitems: false
            },
            prmNames: {
                page: "prm_page", 
                rows: "prm_rows",
                sort: "prm_sort",
                order: "prm_order" 
            },
            viewrecords: true,
            rowNum: 500,
			ondblClickRow: function (rowid, b){invokeBackFun(rowid, _this.p.onSubDoubleClickRow);},
			onSelectRow: function (rowid, b){
				if (_this != null) {
					_this.p.actionSubGrid = this.id;	
				}	
				invokeBackFun(rowid, _this.p.onSubSelectRow);
			}
        });
    };

    //构建表格
    this.createGrid = function() {
    	if(this._grid == null || this._pager == null || this._requestUrl == null) {
            return;
        }
        this.cGrid = jQuery(this._grid).jqGrid({
            url: this._requestUrl, 
            datatype: "json",
            ajaxGridOptions: {contentType: "application/x-www-form-urlencoded; charset=utf-8"},
            mtype: "POST",
            altRows: true, //设置表格zebra-striped值
            altclass: "ui-priority-secondaryRowSec",
            colModel: this._colModel,
            pager: jQuery(this._pager), //加载分页栏
            rowNum: this._row,
            rowList: [5, 10, 20, 30, 50, 100, 200, 500, 1000],
            loadtext: "加载中...",
            emptyrecords: "无记录",
            shrinkToFit: false, //每列宽度会根据width成比例缩放
            autowidth: this._autoWidth, //Grid的宽度会根据父容器的宽度自动重算
            width : this._width,
            sortname: this._sortname, //排序列的名称
            sortorder: this._sortorder, //排序顺序
            viewrecords: true, //显示总记录数
            imgpath: this._imgpath,
            multiselect: this._multiselect, //定义是否可以多选
            multiboxonly: this._multiboxonly, //当multiboxonly为ture时只有选择checkbox才会起作用
            height: this._height, //高度
            footerrow: this._footerrow, //是否有页尾空行
            treeGrid: this._treeGrid,     
			treeGridModel: this._treeGridModel, 
			ExpandColClick: this._ExpandColClick ,        
			ExpandColumn : this._ExpandColumn,      
			userDataOnFooter: this._userDataOnFooter,
            jsonReader: {
            	root: "json_root", //json中代表实际模型数据的入口
				page: "json_page", //json中代表当前页码的数据
				total: "json_total", //json中代表页码总数的数据
				records: "json_records", //json中代表数据行总数的数据
				repeatitems: false, // 如果设为false,则jqGrid在解析json时,
				                   // 会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）;
				                   // 而所使用的name是来自于colModel中的name设定。
				userdata: "userdata" //用户数据
            },
            prmNames: {
                page: "prm_page", //表示请求页码的参数名称  
                rows: "prm_rows", //表示请求行数的参数名称  
                sort: "prm_sort", //表示用于排序的列名的参数名称  
                order: "prm_order", //表示采用的排序方式的参数名称  
                search: "prm_search", //表示是否是搜索请求的参数名称  
                nd: "prm_nd", //表示已经发送请求的次数的参数名称  
                id: "prm_id", //表示当在编辑数据模块中发送数据时,使用的id的名称  
                oper: "prm_oper",    //operation参数名称（我暂时还没用到）  
                editoper: "prm_edit", //当在edit模式中提交数据时，操作的名称  
                addoper: "prm_add", //当在add模式中提交数据时,操作的名称  
                deloper: "prm_del", //当在delete模式中提交数据时，操作的名称  
                subgridid: "prm_id", //当点击以载入数据到子表时,传递的数据名称  
                npage: null,
                totalrows: "prm_totalrows" //表示需从Server得到总共多少行数据的参数名称,参见jqGrid选项中的rowTotal  
            },
            
            treeReader :{
   				level_field: "ALEVEL", //表示树的层级
   				parent_id_field: "APARENT", //表示树的父ID,为空表示根节点
   				leaf_field: "ISLEAF", //表示是否是叶子节点
   				expanded_field: "EXPANDED" //表示节点是否已经展开
			},

            ondblClickRow: this._dblclickRow,
            onSelectRow: this._selectRow,
            
            onSubDoubleClickRow : this._subDblClickRow,
            onSubSelectRow : this._subSelectRow,
            actionSubGrid : null,
			loadComplete :this._loadComplete, 
			gridComplete :this._gridComplete,
            
            /**Grid子表选择项*/
            subGrid: this._subGrid,
            subGridModel: this._subGridModel,
            subGridUrl: this._subGridUrl,
            subGridRowExpanded: this._subGridRowExpanded, //subGridRowExpanded: 当点击“+”展开子表格时，将触发此选项定义的事件方法;
            subGridRowColapsed: this._subGridRowClose         //subGridRowExpanded定义的事件方法函数将会得到两个参数:
														                                //subgrid_id: 子表格的id;当子表格展开的时候,在主表格中会创建一个div元素用来容纳子表格,subgrid_id就是这个div的id.
                                                                                        //row_id: 主表格中所要展开子表格的行的id.
        });
	};

    //创建分页栏
	this.createPager = function() {
        //控制分页栏上的增加,修改,删除,查询按钮
        if(this._grid != null && this._pager != null) {

            var addFlag = false;
            var editFlag = false;
            var delFlag = false;

            if(this._addfunc != null) {
                addFlag = true;
            }
            if(this._editfunc != null) {
                editFlag = true;
            }
            if(this._delfunc != null) {
                delFlag = true;
            }

            jQuery(this._grid).jqGrid("navGrid", this._pager, 
            {
                add: addFlag,
                edit: editFlag,
                del: delFlag,
                search: this._searchFlag,
                refresh: this._refreshFlag,

                addfunc: this._addfunc,
                editfunc: this._editfunc,
                delfunc: this._delfunc,
                
                addtitle: "增加",
				edittitle: "修改",
				deltitle: "删除",
				searchtitle: "查询",
				refreshtitle: "刷新",
                alertcap: "温馨提示",
                alerttext: "请选择记录"
            },
            {
            	id : this._editButtonId
            },
            {
            	id : this._addButtonId
            },
            {
            	id : this._delButtonId	
            },
            {
                //与查询相关的prmSearch参数  
                caption: "查询窗口",
                Find: "查询",
                closeAfterSearch: true,
                multipleSearch: true,
                stringResult: true,
                sFilter: "filters", //查询过滤条件
                sopt: ["eq", "ne", "lt", "le", "gt", "ge", "cn", "nc", "in", "ni"],
                groupOps: [{ op: "and", text: "全部And" }]//[{ op: "and", text: "全部And" }, { op: "or", text: "任何Or"}]
            },{}
            );
    	}
    };
    
    //创建搜索工具栏
    this.createFilterToolbar = function() {
    	if(this._grid != null) {
        	jQuery(this._grid).jqGrid("filterToolbar", {
                stringResult: true,
                sFilter: "filters",
                sopt: ["eq"],
                groupOps: [{ op: "and", text: "全部And"}]
            });
        }
    };

    this.createGrid(); //构建表格
    this.createPager(); //创建分页栏
    if(this._filterToolbar) {
    	this.createFilterToolbar(); //创建搜索工具栏
    }
    
    if (this._containDiv)
    {
    	var obj = document.getElementById(this.__grid).parentNode.parentNode.parentNode.parentNode.parentNode;
    	$(window).bind("resize",winResize); 
    	
    	jQuery(this._grid).setGridWidth(0);
    	if (this._slaveGrid!=null)
    	{
    		jQuery(this._slaveGrid).setGridWidth(0);
    	}
    	jQuery(this._grid).setGridWidth(obj.scrollWidth);
    	if (this._slaveGrid!=null)
    	{
    		jQuery(this._slaveGrid).setGridWidth(obj.scrollWidth);	
    	}
    	jQuery(window).trigger("resize");
    }
    
    function winResize()
    {
    	var obj = this;
    	if (obj.grid== null)
    	{
    		return;
    	}
    	if ((obj.grid._grid == null))
    	{
    		return;
    	}
    	var aDiv = document.getElementById(this.grid.__grid).parentNode.parentNode.parentNode.parentNode.parentNode;
    	jQuery(obj.grid._grid).setGridWidth(0);
    	if (obj.grid._slaveGrid!=null)
    	{
    		jQuery(obj.grid._slaveGrid).setGridWidth(0);
    	}
    	jQuery(obj.grid._grid).setGridWidth(aDiv.scrollWidth);  
    	if (obj.grid._slaveGrid!=null)
    	{
    		jQuery(obj.grid._slaveGrid).setGridWidth(aDiv.scrollWidth);	
    	}
    }
    
    //鼠标离开Grid事件
    this.createMouseleave = function() {
    	if(this._mouseleave != null){
    		var _this = this;
    		jQuery(this._grid).bind("mouseleave", function() {
    		 		_this._mouseleave();
    		 		return true;
    		 });
    	}
    };
    this.createMouseleave();
    
    //返回Grid当前的rowId
    this.getGridCurrentRowId = function() {
        return jQuery(this._grid).jqGrid("getGridParam", "selrow");
    };
    //返回子表当前的rowId
    this.getSubGridCurrentRowId = function() {
		var subGridId = jQuery(this._grid)[0].p.actionSubGrid;
		
		if (subGridId == null){
			return null;
		}
		return jQuery("#"+subGridId).jqGrid("getGridParam", "selrow");
    };
    
    //返回Grid当前的rowData
    this.getGridCurrentRowData = function() {
        var rowId = this.getGridCurrentRowId();
        return jQuery(this._grid).jqGrid("getRowData", rowId);
    };
    //返回子表当前的rowData
	this.getSubGridCurrentRowData = function() {
		var subGridId = jQuery(this._grid)[0].p.actionSubGrid;
		
		if(subGridId == null){
			return null;
		}
		var rowId = this.getSubGridCurrentRowId();
		
		if (rowId == null){
			return null;
		}
		return jQuery("#"+subGridId).jqGrid("getRowData", rowId);
    };
    
    //返回Grid当前的rowData
    this.getGridCurrentRowDataFormRowId = function(rowId) {
        return jQuery(this._grid).jqGrid("getRowData", rowId);
    };
    //返回Grid当前多选的rowId
    this.getGridCurrentMultiRowId = function() {
        return jQuery(this._grid).jqGrid("getGridParam", "selarrrow");
    };
    //返回Grid当前多选的rowData
    this.getGridCurrentMultiRowData = function() {
        var row = this.getGridCurrentMultiRowId();
        var rowData = new Array();
        for(var i = 0; i < row.length; i++) {
            var data = this.getGridCurrentRowDataFormRowId(row[i]);
            rowData.push(data);
        }
        return rowData;
    };
    //返回Grid当前多选的rowId(String类型)
    this.getGridCurrentMultiRowIdStr = function() {
        var rowData = this.getGridCurrentMultiRowData();
        var rowId = "";
        for(var i = 0; i < rowData.length; i++) {
            if(rowData[i].ID != null) {
                rowId += "'" + rowData[i].ID + "',";
            }
        }
        rowId = rowId.substr(0,rowId.lastIndexOf(","));
        return rowId;
    };
    //返回用户数据
    this.getUserData = function() {
    	return jQuery(this._grid).jqGrid("getGridParam", "userData");
    };
    //返回Grid当前url
    this.getGridCurrentUrl = function() {
        return jQuery(this._grid).jqGrid("getGridParam", "url");  
    };
    //设置Grid当前url
    this.setGridCurrentUrl = function(val) {
         jQuery(this._grid).jqGrid("setGridParam", {url: val});   
    };
    //查询Grid
	this.searchGrid = function() {
    	jQuery(this._grid).jqGrid("searchGrid",{
    		//与查询相关的prmSearch参数  
        	caption: "查询窗口",
        	Find: "查询",
        	closeAfterSearch: true,
         	multipleSearch: true,
         	stringResult: true,
         	sFilter: "filters", //查询过滤条件
        	sopt: ["eq", "ne", "lt", "le", "gt", "ge", "cn", "nc", "in", "ni"],
        	groupOps: [{ op: "and", text: "全部And" }, { op: "or", text: "任何Or"}]
        });
    };
    
    //返回GIRD记录数
    this.recordCount=function(){
    	 return jQuery(this._grid).jqGrid("getGridParam", "reccount");
    }
    //设置Grid当前选中行
    this.setGridSelectRow = function(val) {
         jQuery(this._grid).jqGrid.setSelection(val);   
    };
    //刷新
    this.refresh = function() {
        jQuery(this._grid).trigger("reloadGrid");
    };
    //选中行
    this.setSelection = function(rowId) {
    	//alert('');
        jQuery(this._grid).setSelection(rowId,true);
    };
    //释放
    this.release = function() {
    	jQuery(this._grid).remove();
    };
    
    this.showAddButton = function() {
   		if(jQuery("#" + this._addButtonId) == null) {
    		return;
    	}
    	jQuery("#" + this._addButtonId).show();
    };
    
    this.hideAddButton = function() {
    	if(jQuery("#" + this._addButtonId) == null) {
    		return;
    	}
    	jQuery("#" + this._addButtonId).hide();	
    };
    
    this.showEditButton = function() {
   		if (jQuery("#" + this._editButtonId) == null) {
   			return;
   		} 
   		jQuery("#" + this._editButtonId).show();
    };
    
    this.hideEditButton = function() {
   		if (jQuery("#"+this._editButtonId) == null) {
   			return;
   		} 
   		jQuery("#" + this._editButtonId).hide();
    };
    
    this.showDelButton = function() {
    	if (jQuery("#" + this._delButtonId) == null) {
    		return;
    	}
    	jQuery("#" + this._delButtonId).show();
    };
    
    this.hideDelButton = function() {
    	if(jQuery("#" + this._delButtonId) == null) {
    		return;
    	}
    	jQuery("#" + this._delButtonId).hide();
    };
    
    this.unbindDblClick = function() {
    	if(this._dblclickRow != null) {
    		jQuery(this._grid)[0].p.ondblClickRow = function(){return;};
    	}
    };
    
	this.bindDblClick = function() {
   		if(this._dblclickRow != null) {
    		jQuery(this._grid)[0].p.ondblClickRow = this._dblclickRow;
    	}
    };
    
    this.allRowData= function() {
    	return jQuery(this._grid).jqGrid("getRowData") ;	 	
    }
};
