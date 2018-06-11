/******************************************************************
 * 创建jqValidate
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
var createValidate = function(options) {
    //需要验证form表单对象名称
    this._formName = options.formName == null ? null : "#" + options.formName;
    //需要验证的元素
    this._elements = options.elements == null ? null : options.elements;
    
    this._validate = null;
    
    this._msg = { 
    	required: "【必填项】",
        remote: "【请修正该字段】",
        email: "【请输入正确格式的电子邮件】",
        url: "【请输入合法的网址】",
        date: "【请输入合法的日期】",
        dateISO: "【请输入合法的日期 (ISO).】",
        number: "【请输入合法的数字】",
        digits: "【只能输入整数】",
        creditcard: "【请输入合法的信用卡号】",
        equalTo: "【两个输入框内容不相同,请重新输入】",
        accept: "【请输入拥有合法后缀名的字符串】",
        maxlength: jQuery.validator.format("【请输入一个长度最多是{0}的字符串】"),
        minlength: jQuery.validator.format("【请输入一个长度最少是{0}的字符串】"),
        rangelength: jQuery.validator.format("【请输入一个长度介于{0}和{1}之间的字符串】"),
        range: jQuery.validator.format("【请输入一个介于{0}和{1}之间的值】"),
        max: jQuery.validator.format("【请输入一个最大为{0}的值】"),
        min: jQuery.validator.format("【请输入一个最小为{0}的值】")
    };
    
    this.getElementPosion= function(elementId){
        var element;
        if(typeof elementId == "string"){
	        element = document.getElementById(elementId);
        }else{
	    	element = elementId;
        }
        if(element != null) {
	        this.left = element.offsetLeft;
	        this.top = element.offsetTop;
	        if(element.style.position != "absolute"){
		        var tmpParent = element.offsetParent;
		        while(tmpParent != null && tmpParent.tagName != "BODY"){
			        this.left = this.left + tmpParent.offsetLeft - tmpParent.scrollLeft;
			        this.top = this.top + tmpParent.offsetTop - tmpParent.scrollTop;
			        tmpParent = tmpParent.offsetParent;
		        }	
	        }
	        this.rigth = this.left + element.clientWidth;	
	        this.bottom = this.top + element.clientHeight;	
        }
    };

    //创建错误消息框
    this.createErrorMsg = function(error, element){
        var divNamea = element.attr("name") + "errordiv";
        var divNameb = element.attr("name") + "errordiv" + element.attr("name") ;

        var mydiv = document.getElementById(divNamea);
        var divChild = document.getElementById(divNameb);
        var flag = false;

        if(mydiv == null){
            var mydiv = document.createElement("div");
            mydiv.setAttribute("id", divNamea);
            mydiv.style.position = "absolute";
            mydiv.style.zIndex = 999;
            var c = new this.getElementPosion(element.attr("id"));
            mydiv.style.left = c.left + "px";
            mydiv.style.top = c.bottom + 5 + "px";
            element.bind("focus" ,{a :element, b: this} ,this.visibleError);
            element.bind("blur",{a :element} ,this.hideError);
            flag = true;
        }
                
        error.html(error.html() + "<br/>");
        var mydivChild = document.createElement("div");
        mydivChild.setAttribute("id", divNameb);
        error.appendTo(mydivChild);

        if(flag){
            mydiv.appendChild(mydivChild);
            window.document.body.appendChild(mydiv);
            mydiv.style.visibility = "hidden";
        }else{
            mydiv.replaceChild(mydivChild, divChild);
        }
    };
    
    //验证通过后的操作
    this.successOp = function(element){
        var a = element[0].htmlFor + "errordiv";
        var b = document.getElementById(a);
        if (b != null) {
            window.document.body.removeChild(b);
        }
    };
    
    this.visibleError = function(event){
    	var objName = event.data.a.attr("name") + "errordiv";
    	var objDiv = null;
        objDiv = document.getElementById(objName);
        if(objDiv == null || objDiv.style.visibility == "visible"){
            return;
        } 
        
        //重新定位
    	var c = new event.data.b.getElementPosion(event.data.a.attr("id"));
    	objDiv.style.left = c.left + "px";
        objDiv.style.top = c.bottom + 5 + "px";	

        objDiv.style.visibility = "visible";
    };
    
    this.hideError = function(event){
        var objName = event.data.a.attr("name") + "errordiv";
        var objDiv =null;
        objDiv = document.getElementById(objName);
        if(objDiv == null || objDiv.style.visibility =="hidden"){
            return;
        } 
        objDiv.style.visibility = "hidden";
    };
    
    this.unicodeLength = function(strUnicode){
		var intLength = 0;
	    for (var i = 0;i < strUnicode.length; i++) {
	        if ((strUnicode.charCodeAt(i) < 0) || (strUnicode.charCodeAt(i) > 255)) {
	            intLength = intLength + 2;
	        } else {
	            intLength = intLength + 1;
	        }   
	    }
		return intLength;
	};
    
    //创建验证控件
    this.createValidate = function(){
        jQuery.extend(jQuery.validator.messages, this._msg);
        var _this = this;
        //返回字符串的实际长度（一个中文长度为2）
    	jQuery.validator.prototype.getLength = function(value, element){
				switch( element.nodeName.toLowerCase() ) {
				case 'select':
					return _this.unicodeLength($("option:selected", element).length);
				case 'input':
					if( this.checkable( element) )
						return _this.unicodeLength(this.findByName(element.name).filter(':checked').length);
				}
				return _this.unicodeLength(value);
		};
		
        if(this._formName == null) {
            return;
        }
        
        return jQuery(this._formName).validate({
            rules: this._elements,
            success: function(element){
                _this.successOp(element);
            },
            errorPlacement: function(error, element){
                _this.createErrorMsg(error, element);
            }
        });
    };
    
    //增加验证规则
    this.addValidate = function(){
        jQuery.validator.addMethod("stringCheck", function(value, element){
                return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
        }, "【只能包括中文字、英文字母、数字和下划线】");
        
         jQuery.validator.addMethod("cardCheck", function(value, element){
                return this.optional(element) || /^[A-Za-z0-9\-\_]+$/.test(value);
        }, "【只能包括英文字母和数字】");
        
        // 联系电话(手机/电话皆可)验证
        jQuery.validator.addMethod("isPhone", function(value, element){
                var length = value.length;
                //var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
                var mobile =/^1+\d{10}$/;
                var tel = /^(\d{3,4}-?)?\d{7,9}$/;
                return this.optional(element) || (tel.test(value) || mobile.test(value));
        }, "【请正确填写您的联系电话】");
        
        jQuery.validator.addMethod("decimal0", function(value, element){
                return this.optional(element) || /^(-)?((\d{1,12})|(\d{0,12}\.\d{1,4})){1}$/.test(value);
        }, "【请输入12位整数,4位小数的数字】");
        
        jQuery.validator.addMethod("decimal", function(value, element){
                return this.optional(element) || /^((\d{1,12})|(\d{0,12}\.\d{1,4})){1}$/.test(value);
        }, "【请输入12位整数,4位小数的数字】");
        
        jQuery.validator.addMethod("decimal2", function(value, element){
                return this.optional(element) || /^((\d{1,3})|(\d{0,3}\.\d{1,4})){1}$/.test(value);
        }, "【请输入3位整数,4位小数的数字】");
        
        jQuery.validator.addMethod("decimal3", function(value, element){
                return this.optional(element) || /^((\d{1,7})|(\d{0,7}\.\d{1,4})){1}$/.test(value);
        }, "【请输入7位整数,4位小数的数字】");
        
        jQuery.validator.addMethod("decimal4", function(value, element){
                return this.optional(element) || /^((\d{1,5})|(\d{0,5}\.\d{1,3})){1}$/.test(value);
        }, "【请输入5位整数,3位小数的数字】");
        
        jQuery.validator.addMethod("decimal5", function(value, element){
                return this.optional(element) || /^(-)?((\d{1,10})|(\d{0,10}\.\d{1,6})){1}$/.test(value);
        }, "【请输入10位整数,6位小数的数字】");
        
        jQuery.validator.addMethod("decimal6", function(value, element){
       			return this.optional(element) || /^((\d{1,10})|(\d{0,10}\.\d{1,6})){1}$/.test(value);
        }, "【请输入10位整数,6位小数的数字】");
        
		jQuery.validator.addMethod("compareDate", function(value, element, param){
         		return this.optional(element) || value >= $(param).val();
        }, "【起始日期不能大于终止日期】");
        
       	jQuery.validator.addMethod("compareNumber", function(value, element, param){
         		return this.optional(element) || parseFloat(value) < parseFloat($(param).val());
        }, "【最小数量不能大于等于最大数量】");
        
        jQuery.validator.addMethod("zero", function(value, element){
                return this.optional(element) || parseFloat(value) != 0;
        }, "【请输入不等于零的数字】");
        
        jQuery.validator.addMethod("lowOne", function(value, element){
                return this.optional(element) || parseFloat(value) < 1;
        }, "【请输入小于1的数字】");
    };
    
    this.checkFrm = function(){
    	return this._validate.form();
    };
    
    this._validate = this.createValidate();
    this.addValidate();
    
};
