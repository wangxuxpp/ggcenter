package business.center.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import base.baseinterface.BaseController;
import base.common.Constant;
import base.util.JSONResult;
import business.center.login.service.LoginService;

/**
 * 登录控制层
 * 
 * @author 王丹
 * @version  2014-3-19
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
@Controller 
@RequestMapping("/login.hm")
public class LoginController  extends BaseController {

	@Resource(name="loginService")
	public LoginService service;
	
	/**
	 * 显示系统主页面
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=showLogin")
	public String showLogin(HttpServletRequest request, HttpServletResponse response){
		return "jsp/center/login/login.jsp";
	}

	/**
	 * 登录系统
	 * 
	 * @param request
	 * @param response void
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=doLogin")
	public void doLogin(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		JSONResult result = new JSONResult();
		try{
			if(!service.validateLogin(map, request)){
				result.setErrorType("用户类别或用户名或密码错误");
			}else{
				result.setSuccessType("");
			}
		} catch (Exception e){
			log.error(e);
			result.setErrorType(e.getMessage());
		}
		result.write(response);
	}

	/**
	 * 登出系统
	 * 
	 * @param request
	 * @param response void
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=doClose")
	public String doClose(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		request.getSession().setAttribute(Constant.SYS_SESSION, null);
		return "index.jsp";
	}

}
