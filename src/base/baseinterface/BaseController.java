package base.baseinterface;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import base.common.Constant;
import base.util.SessionUser;
import base.util.Util;


/**
 * 系统中基础的控制器类,为其他控制器类提供基础方法
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
public abstract class BaseController {
	
	/**
	 * Log4j对象,可在派生类中直接使用
	 */
	protected Logger log = Logger.getLogger(this.getClass());

	protected String winUrl = "";
	
	/**
	 * 向ModelMap中绑定请求属性
	 */
	
	@ModelAttribute
	protected ModelMap modelBinder(HttpServletRequest request, ModelMap model) {
		model.clear();
		model.addAllAttributes(Util.getParameterValues(request));
		model.put(Constant.SYS_SESSION,(SessionUser)request.getSession().getAttribute(Constant.SYS_SESSION));
		return model;
	}
	
	@ModelAttribute
	protected SessionUser sessionUserBinder(HttpServletRequest request) {
		return (SessionUser)request.getSession().getAttribute(Constant.SYS_SESSION);
	}
	
}
