package base.baseinterface;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import base.common.Constant;
import base.util.Util;


/**
 * ERP与平台交互控制器
 * 
 * @author wx
 * @version  2014-6-20
 * 版权所有(C)卫德住工科技
 * 
 * Copyright(C)EasyPc.All Rights Reserved.
 * history:
 *
 */

@Controller
@SuppressWarnings({"unchecked"})
public abstract class CenterSwapController {
	
	/**
	 * Log4j对象,可在派生类中直接使用
	 */
	protected Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 向ModelMap中绑定请求属性
	 */
	
	@ModelAttribute
	protected ModelMap modelBinder(HttpServletRequest request, ModelMap model) {
		model.clear();
		model.addAllAttributes(Util.getParameterValues(request));
		return model;
	}
	
	@ModelAttribute
	protected String loginFactoryId(HttpServletRequest request) {
		return request.getParameter(Constant.erpConnectFactoryId).toString();
	}
	
}
