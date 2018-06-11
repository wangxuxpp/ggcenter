/******************************************************************
 * grid列表
 * 
 * 例：new grid({
 *   grid:放置列表的控件ID,
 *   pager:放置翻页信息的控件ID,
 *   height:列表高度,
 *   requestUtl:访问路径,
 *   colModel:[{name:列表ID, lable:列表显示名, index:获取结果集中key值, width:列宽, hidden:是否隐藏(true,false), align:在列中的位置(left,center,right), formatter:调用方法}],
 *   data:查询条件,
 *   seccuss:返回成功后处发,
 *   error:返回失败后处发,
 *   loadComplete:列表部署完后处发,
 *   paging:是否分页
 * })
 *
 * @author 王丹
 * @version 2015-7-3
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
var Grid = function(options){
	if(options == null){
		return;
	}
	//得到画列表的位置、列表头、高度及分页相关内容
	var gridObj = options.gridObj;
	var grid = options.grid;
	var colModel = options.colModel;
	var height = options.height == null ? "auto" : options.height;
	var high = options.high == null ? "30px" : options.high;
	var isOrderBy = options.paging == false ? false : true;
    var altclass = "bmx";
    var selectCss = "djxz";
    var movableCss = "ui-priority-secondaryRowSec-yellow";
	var limit = options.limit == null ? 5 : options.limit;
	var type = options.gridType == true ? "grb01" : "";
	var isSubmit = false;
	
	//得到查询列表的路径及查询条件
	var requestUrl = options.requestUrl;
	var params = new Object();
	this.data = null;
	
	//处发事件
	var errorCallBack = (options.error != null && typeof(options.error) == "function") ? options.error : null;
	var successCallBack = (options.success != null && typeof(options.success) == "function") ? options.success : null;
	var completeCallBack = (options.loadComplete != null && typeof(options.loadComplete) == "function") ? options.loadComplete : null;
	
	//选中行数据 ID
	var selectRowDate = null;
	var selectRowDateId = null;
	 
	//初始化
	var init = function(){
		var rows = colModel.length;
		var newRow = new Array();
		$("#"+grid).addClass("gridTab");
		$("#"+grid).addClass(type);
		$("#"+grid).attr("border", "0");
		$("#"+grid).attr("cellspacing", "0");
		$("#"+grid).attr("cellpadding", "0");
		$("#"+grid).attr("align", "center");
		newRow.push("<thead>");
		newRow.push("<tr>");
		for(var j = 0 ; j < rows ; j = j + 1){
			var colObj = colModel[j];
			newRow.push("<th width='");
			newRow.push(colObj.width);
			if(colObj.hidden){
				newRow.push("' style='display:none;");
			}
			newRow.push("'>");
			newRow.push(colObj.label);
			newRow.push("</th>");
		}
		newRow.push("</tr>");
		newRow.push("</thead>");
		newRow.push("<tbody>");
		newRow.push("</tbody>");
		newRow.push("<tfoot>");
		newRow.push("</tfoot>");
		$("#"+grid+" ").append(newRow.join(''));
		setPager();
		getDateList();
	};
	
	//设置分页信息
	var setPager = function(){
		if(isOrderBy == true){
			var html = new Array();
			var rows = colModel.length;
			html.push('<tr>');
			html.push('<td colspan="'+rows+'">');
			html.push('<h1>总共<span id="totalNum">0</span>条记录&nbsp;&nbsp;&nbsp;&nbsp;当前第');
			html.push('<input type="hidden" id="setPageOn" name="setPageOn" value="1">');
			html.push('<select id="pageOn" name="pageOn" onclick="setPage()">');
			html.push('<option value="0">0</option>');
			html.push('</select>页/共<span id="totalPage">0</span>页</h1>');
			html.push('<h2>');
			html.push('<a href="#" onclick="'+gridObj+'.firstPage()">首页</a>');
			html.push('<a href="#" onclick="'+gridObj+'.previous()">上一页</a>');
			html.push('<a href="#" onclick="'+gridObj+'.nextPage()">下一页</a>');
			html.push('<a href="#" onclick="'+gridObj+'.lastPage()">末页</a>');
			html.push('</h2>');
			html.push('</td>');
			html.push('</tr>');
			$("#"+grid+" tfoot ").append(html.join(''));
		}
	}
	
	//通过ajax访问后台程序得到查询列表结果集
	var getDateList = function(){
		if(requestUrl == null)return;
		isSubmit = true;
		Util.waitStart();
		if(isOrderBy == true){
			params.limit = limit;
		}
		jQuery.extend(params, this.data);
		jQuery.extend(options, {
			url:requestUrl, data: params, 
			error: function(XMLHttpRequest){
				Util.waitEnd();
				swin.showError(XMLHttpRequest);
			},
			success: function(data){
				Util.waitEnd();
				try{
					isSubmit = false;
					if(data.jsonType == "success"){
						if(isOrderBy == true){
							var totalPage = parseInt(data.totalPage, 10);
							$("#totalPage").html(totalPage);
							$("#totalNum").html(data.totalNum);
							setPageNum(totalPage);
						}
						loadComplete(data.dataList)
						if(successCallBack != null){
							successCallBack(data);
						}
					}
				}catch(e){
		 			swin.showError(e.message);
				}
			},
			async: true, cache:false, type:"POST", dataType:"json",contentType:"application/x-www-form-urlencoded; charset=utf-8"
		});
		jQuery.ajax(options);
	}
	
	//部署列表行列信息
	var loadComplete = function(dataList){
		$("#"+grid+" tbody tr").remove();
		var len = dataList.length;
		var rows = colModel.length;
		var newRow = new Array();
		for(var i = 0 ; i < len ; i = i + 1){
			var rowObj = dataList[i];
			newRow.push("<tr ");
			for(var j = 0 ; j < rows ; j = j + 1){
				var colObj = colModel[j];
				var mapping = colObj.name;
				var val = rowObj[colObj.index];
				if(mapping == "ID"){
					newRow.push(" id='");
					newRow.push(val);
					newRow.push("'");
				}
			}
			newRow.push(">");
			for(var j = 0 ; j < rows ; j = j + 1){
				var colObj = colModel[j];
				var mapping = colObj.name;
				var val = rowObj[colObj.index];
				if(colObj.formatter != null && typeof(colObj.formatter) == "function"){
					val = colObj.formatter(val, i, rowObj);
				}
				var align = colObj.align == null ? "left" : colObj.align;
				newRow.push("<td id='");
				newRow.push(mapping);
				if(colObj.width != null){
					var width = colObj.width;
					newRow.push("' width='");
					newRow.push(width);
				}
				newRow.push("' align='");
				newRow.push(align);
				newRow.push("' height='");
				newRow.push(high)
				if(colObj.hidden){
					newRow.push("' style='display:none;");
				}
				newRow.push("' >");
				newRow.push(val);
				newRow.push("</td>");
			}
			newRow.push("</tr>");
		}
		$("#"+grid+" tbody ").append(newRow.join(''));
		setRowInfo();
	}
	
	//给当前页赋
	var setPageNum = function(totalPage){
		$("#pageOn").empty();
		for(var k = 1 ; k <= totalPage ; k = k + 1){
			$("#pageOn").append("<option value='"+k+"'>"+k+"</option>");
		}
		$("#pageOn").val($("#setPageOn").val());
	}
	
	//给行加颜色、鼠标经过、行的单击和双击事件
	var setRowInfo = function(){
		$("#"+grid+" tbody tr:odd").addClass(altclass);
		$("#"+grid+" tbody tr").click(function(){
			var obj = this;
			var $obj = $(this);
			$("#"+grid+" tbody tr").removeClass(selectCss);
			$obj.addClass(selectCss);
			selectRowDateId = obj.id;
			var trSeq = $obj.parent().find("tr").index($obj);
			selectRowDate = new Object();
			$("#"+grid+" tbody tr:eq("+trSeq+") td").each(function(){
				var obj = this;
				var $obj = $(this);
				selectRowDate[obj.id] = $obj.text();
			});
		});
	}
	
	//设置页面开始记录、一页行数
	this.setPage = function(){
		var pageOn = $("#pageOn").val();
		var start = (parseInt(pageOn, 10) - 1) * limit;
		params.start = start;
		$("#setPageOn").val(pageOn);
		getDateList();
	}
	
	//首页
	this.firstPage = function(){
		$("#pageOn").val(1)
		var start = 0;
		params.start = start;
		$("#setPageOn").val(1);
		getDateList();
	}
	
	//上一页
	this.previous = function(){
		var pageOn = $("#pageOn").val();
		var start = 0;
		if(pageOn == 1){
			return;
		}else{
			start = (parseInt(pageOn, 10) - 2) * limit;
			params.start = start;
		}
		pageOn = parseInt(pageOn, 10) - 1;
		$("#setPageOn").val(pageOn);
		getDateList();
	}
	
	//下一页
	this.nextPage = function(){
		var pageOn = $("#pageOn").val();
		var start = 0;
		if(pageOn == $("#totalPage").html()){
			return;
		}else{
			start = parseInt(pageOn, 10) * limit;
			params.start = start;
		}
		pageOn = parseInt(pageOn, 10) + 1;
		$("#setPageOn").val(pageOn);
		getDateList();
	}
	
	//最后一页
	this.lastPage = function(){
		var pageOn = $("#totalPage").html();
		var start = (parseInt(pageOn, 10) - 1) * limit;
		params.start = start;
		$("#setPageOn").val(pageOn);
		getDateList();
	}
	
	//刷新
	this.refresh = function(){
		var start = 0;
		params.start = start;
		getDateList();
	}
	
	//设置访问后台路径
	this.setRequestUrl = function(url){
		requestUrl = url;
	}
	
	//给查询结果集加条件
	this.setParam = function(key, value){
		params[key] = value;
	}
	
	//给一个单元格设置样式
	this.setCell = function(i, j, style){
		$("#"+grid+" tbody tr:eq("+i+") td:eq("+j+")").css(style);
	}
	
	//得到一共有多少行
	this.getAllRows = function(){
		return $("#"+grid+" tbody tr").length - 1;
	}
	
	//得到选中行数据
	this.getGridCurrentRowData = function(){
		return selectRowDate;
	}
	
	//得到选中行的id
	this.getGridCurrentRowId = function(){
		return selectRowDateId;
	}
	init();
}
