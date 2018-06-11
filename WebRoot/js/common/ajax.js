/******************************************************************
 * ajax请求统一处理
 *
 * @author wx
 * @version 2014-3-19
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 * 
 * ajax请求统一处理,防止重复提交
 * @param
 * 	 element：请求地址或form对象,但不能为空(Sting或Object)
 * 	 options：请求选项
 * 	 options.error：请求失败时被调用的函数(可以不处理,有默认处理）
 * 	 options.success：请求成功后调用的回调函数
 * 	 options.data：发送到服务器的数据(Sting或Object)
 */
var closeFun_ = null;
var ajaxRequest = function(element, options) {
 	if(!element) {
		swin.showError("请求地址或form对象,但不能为空!");
		return;
	}
	var isSubmit = false; //判断是否正在提交?如正在提交为true,为了防止重复提交.
	var type = "GET"; //请求方式,默认GET
	var url = element; //请求地址
	
	if(options != undefined) { 
		var successCallBack = (options.success != null && typeof(options.success) == "function") ? options.success : null;
		var errorCallBack = (options.error != null && typeof(options.error) == "function") ? options.error : null;
		var data = options.data != null ? options.data : null;
		var async = options.async != null ? options.async : true; //默认异步
		var waitFun = options.fun != null ? true : false;
		closeFun_ = options.closeFun != null ? options.closeFun : null;
		//http 提交方式
		type = options.sendType != null ? options.sendType : "GET"; 
		
		var isShowMessageBox_  =  options.isShowMessageBox == null ? true :  options.isShowMessageBox;
	}
	
	if (!waitFun){
		Util.waitStart();
	}
	
	if(typeof element == "object") {
		if(element.tagName.toLowerCase() == "form") {
			if(isSubmit) {
				return; //为了防止重复提交.
			}
			isSubmit = true;
			type = "POST";
			url = element.action;
			data = jQuery("#"+element.id).serialize(); //序列化元素
		} else {
			swin.showError("传入的不是form对象");
		}
	}
    //为请求选项赋初始值
	options ={type: type, url: url, dataType: "json", cache: false, contentType: "application/x-www-form-urlencoded; charset=utf-8", data: data, async: async};
	
	jQuery.extend(options, { //ajax请求开始
		error: function(XMLHttpRequest) { //ajax请求失败
			var status = XMLHttpRequest.status;
			if(status == 404) {
				swin.showError("无法连接服务器!");
			} else {
				swin.showError("服务器出现异常!");
			}
			if(errorCallBack != null) {
				errorCallBack(XMLHttpRequest);
			}
			if (!waitFun) {
				Util.waitEnd();
			}
			
		},
		success: function(data) { //请求成功
			try{
				isSubmit = false;
				if(!processAjaxError(data, isShowMessageBox_)) { //处理错误信息
					if (!waitFun) {
						Util.waitEnd();
					}
					return;	
				}
				if(successCallBack != null) {
					successCallBack(data);
				}
				if(!waitFun){
					Util.waitEnd();
				}
			}catch(e){
				if (!waitFun) {
					Util.waitEnd();
				}
	 			swin.showError(e.message);
			}
		}
	});
	
	jQuery.ajax(options);
};
 
/**
 * 处理ajax请求返回的错误信息
 * 
 * @param data json数据
 */
var processAjaxError = function(data , showBox) {		 
	try {
		if(!data){
			return false;
		}
		var showBox_ = showBox == null ? true : showBox;
		
		var jsonType = data.jsonType;
		if(!jsonType){
			return false;
		}
		
		if(jsonType =="success"){
			if(data.jsonMessage != '' && data.jsonMessage != null && showBox_){
				swin.showSuccess(data.jsonMessage, closeFun_);
			}
			return true;	
		}					 
		
		if(showBox_){
			messageBox(data.jsonMessage);
			return false;
		}else{
			return true;
		}
	
	} catch(error) {
		messageBox(error.message);
		return false;
	}
};

function messageBox(message, question) {
 	if(!question && question == null){
 		swin.showError(message);
 	}else{
 		return confirm(message);
 	}
}
