/******************************************************************
 * Util公共类
 */
var Util = new _util();
function _util(){
	
	/***************************************************************
	* 关闭层方法
	*
	*/
	this.onClose = function(value){
		var ifmId = $("#ifmId").val();
		window.parent.swinReturnValue = value;
		parent.$("#"+ifmId).dialog( "close" );
	}
	
	/******************************************************************
	 * 开启等待层
	 */
	this.waitStart = function(){
		var height = $(window).height();
		var width = $(window).width();
		var loadStyle = "position:absolute; left:0; top:0; background:#aaa; filter:alpha(opacity=40);/*支持 IE 浏览器*/ -moz-opacity:0.40;/*支持 FireFox 浏览器*/ opacity:0.40;/*支持 Chrome, Opera, Safari 等浏览器*/ z-index: 9999999px;";
		var outerStyle = "position:absolute; width:221px; height:42px; z-index:9999988px; background: url(css/images/index/logo.png) no-repeat;";
		if(!document.getElementById("x_wait_loading")){
			$("<div>",{id:"x_wait_loading", style:"width:"+width+"px;height:"+height+"px;"+loadStyle}).appendTo($(document.body));
			var left = (width - 221)/2;
			var top = (height - 42)/2;
			$("<div>",{id:"x_wait_outer", style:"left:"+left+"px;top:"+top+"px;"+outerStyle}).appendTo($(document.body));
		}
	}
	
	/******************************************************************
	 * 关闭等待层
	 */
	this.waitEnd = function(){
		if(document.getElementById("x_wait_loading")){
			$("#x_wait_loading").remove();
			$("#x_wait_outer").remove();
		}
		return true;
	}
	
	/******************************************************************
	 * 得到当前日期
	 */
	this.getDate = function(time){
		var date = new Date();
		var meizzTheYear=date.getFullYear(); //定义年的变量的初始值
		var meizzTheMonth=date.getMonth()+1; //定义月的变量的初始值
		meizzTheMonth = (meizzTheMonth < 10 ? "0"+meizzTheMonth : meizzTheMonth);
		var meizzTheDate=date.getDate();    //定义日的变量的初始值   
		meizzTheDate = (meizzTheDate < 10 ? "0"+meizzTheDate : meizzTheDate); 
		var meizzTheHour=date.getHours();    //定义小时变量的初始值
		var meizzTheMinute=date.getMinutes();//定义分钟变量的初始值
		var meizzTheSecond=date.getSeconds();//定义秒变量的初始值
		if(time == 'time'){
			return meizzTheYear+"-"+meizzTheMonth+"-"+meizzTheDate+" "+meizzTheHour+":"+meizzTheMinute+":"+meizzTheSecond;
		}else if(time == 'long'){
			meizzTheHour = (meizzTheHour < 10 ? "0"+meizzTheHour : meizzTheHour); 
			meizzTheMinute = (meizzTheMinute < 10 ? "0"+meizzTheMinute : meizzTheMinute);
			meizzTheSecond = (meizzTheSecond < 10 ? "0"+meizzTheSecond : meizzTheSecond);
			return meizzTheYear+meizzTheMonth+meizzTheDate+meizzTheHour+meizzTheMinute+meizzTheSecond;
		}else{
			return meizzTheYear+"-"+meizzTheMonth+"-"+meizzTheDate;
		}
	}
	
	/******************************************************************
	 * 得到字段串长度
	 */
	this.getStrLen = function(val){
		var len = 0;
		for (var i = 0; i < val.length; i++){
			if(val[i].match(/[^x00-xff]/ig) != null)
				len += 2; 
			else
				len += 1; 
		}
		return len; 
	}
	
	/******************************************************************
	 * form的回车事件
	 */
	this.setKeydown = function(elementId, fun){
		if(elementId == null){
			alert('elementId 不能为空!');
			return;
		}
		$("#"+elementId).keydown(function(evt){
			evt = (evt) ? evt : ((window.event) ? window.event : (arguments.callee.caller.arguments[0]?arguments.callee.caller.arguments[0]:""));
			var keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which :evt.charCode);
			if(keyCode == '13'){
				if(fun != null && typeof fun == 'function'){
					fun();
				}
			}
		});
	}
	
}
