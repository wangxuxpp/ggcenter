
/******************************************************************
 * 弹出对话框
 *
 * @author 王丹
 * @version 2014-4-1
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
var swinReturnValue = null;
(function($){
	s_load = function(options){
		options = jQuery.extend({
				appendDiv:document.body,
				id:"swin_div",
				title:"",
				type:'edit',
				width:"0",
				height:"0",
				url:"",
				message:"",
				onclose: function(){},
				onreturn: function(){},
				update: true
		}, options);
		
		var $_swin = $('<DIV id="'+options.id+'"></DIV>');
		$(options.appendDiv).append($_swin);
		
		if(options.type == 'edit') {
			$("<iframe>",{
				id:options.id+'_iframe',
				width:"100%",
				height:"100%",
				frameBorder:"0",
				"scrolling":"no"
			}).appendTo("#"+options.id);
		} else {
			$("#"+options.id).html("<p class='ts_info_text'>"+options.message+"</p>");
		}
		
		var _onclose = function(){
			$("#"+options.id+'_iframe').remove();
			$_swin.remove();
			if(options.onclose != null){
				options.onclose(swinReturnValue);
			}
			swinReturnValue = null;
		}
		
		var _onreturn = function(){
			$("#"+options.id+'_iframe').remove();
			$_swin.remove();
			if(options.onreturn != null){
				options.onreturn(swinReturnValue);
			}
			swinReturnValue = null;
		}

		//把层显示出来
		if (options.type == "edit"){
			$_swin.dialog({
				autoOpen: false,
				resizable: false,
				modal: true,
				title: '<img src="css/user/images/load/s_ico05.png" class="ui_span_title_img"/>'+options.title,
				width: options.width,
				height: options.height,
				close: _onclose
			});
			$_swin.dialog( "open" );
			document.getElementById(options.id+"_iframe").src = options.url+"&ifmId="+options.id;
			
		} else if (options.type == "success"){
			$_swin.dialog({
				autoOpen: false,
				resizable: false,
				modal: true,
				title:'<img src="css/user/images/load/s_ico06.png" class="ui_span_title_img"/>'+"成功提示窗口",
				buttons: {
					"确定": function() {
						_onclose();
						return true;
					}
				}
			});
			$_swin.dialog( "open" );
			
		} else if (options.type == "confirm"){
			$_swin.dialog({
				autoOpen: false,
				resizable: false,
				modal: true,
				title:'<img src="css/user/images/load/s_ico06.png" class="ui_span_title_img"/>'+"确认提示窗口",
				buttons: {
					"确定": function() {
						_onclose();
						return true;
					},
					"取消": function() {
						_onreturn();
						return false;
					}
				}
			});
			$_swin.dialog( "open" );
			
		} else {
			$_swin.dialog({
				autoOpen: false,
				resizable: false,
				modal: true,
				title:'<img src="images/load/s_ico06.png" class="ui_span_title_img"/>'+"失败提示窗口",
				buttons: {
					"确定": function() {
						_onclose();
						return true;
					}
				}
			});
			$_swin.dialog( "open" );
			
		}
	}
})(jQuery);

/******************************************************************
 * 显示层窗口
 */
var swin = new _showLoading();
function _showLoading(){
	var k = 0;
	
	/******************************************************************
	 * 弹出编辑对话框
	 */
	this.openWinDialog = function(ifmId, url, title, width, height, onClose, topdiv){
		var s_window = window;
		if(topdiv){
			while(s_window.cloose == null){
				s_window = s_window.parent;
			}
		}
		new s_window.s_load({id:ifmId,url:url,title:title,type:"edit",width:width,height:height,onclose:onClose});
	};
	
	/******************************************************************
	 * 弹出编辑对话框
	 */
	this.openMaxWin = function(ifmId, url, title, onClose){
		var s_window = window;
		while(s_window.cloose == null){
			s_window = s_window.parent;
		}
		new s_window.s_load({id:ifmId,url:url,title:title,type:"edit",width:($(s_window).width()-30),height:($(s_window).height()-30),onclose:onClose});
	};
	
	/******************************************************************
	 * 弹出成功框
	 */
	this.showSuccess = function(message, onClose) {
		swinReturnValue = message;
		while(document.getElementById("showMessage"+k)){
			k++;
		}
		new s_load({id:"showMessage"+k,message:message,type:"success",onclose:onClose});
		k++;
	}
	
	/******************************************************************
	 * 弹出确认框
	 */
	this.showConfirm = function(message, onClose, onReturn) {
		while(document.getElementById("showConfirm"+k)){
			k++;
		}
		new s_load({id:"showConfirm"+k,message:message,type:"confirm",onclose:onClose,onreturn:onReturn});
		k++;
	}
	
	/******************************************************************
	 * 弹出错误框
	 */
	this.showError = function(message, onClose) {
		while(document.getElementById("showError"+k)){
			k++;
		}
		new s_load({id:"showError"+k,message:message,type:"error",onclose:onClose});
		k++;
	}
	
}
