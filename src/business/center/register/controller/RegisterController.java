package business.center.register.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import base.baseinterface.BaseController;
import base.util.JSONResult;
import business.center.register.service.RegisterService;


/**
 * 注册页
 * 
 * @author 王宇
 * @version  2015-6-23
 * 
 * history:
 *
 */
@Controller 

@RequestMapping("/register.hm")
public class RegisterController extends BaseController{
	
	@Resource(name="registerService")
	public RegisterService service;
	
	/**
	 * 显示页面
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=showRegister")
	public String showRegister(HttpServletRequest request, HttpServletResponse response, ModelMap map ){
		return "jsp/center/register/zc.jsp";
	}

	/**
	 * 显示页面
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=showVerify")
	public String showVerify(HttpServletRequest request, HttpServletResponse response, ModelMap map ){
		return "jsp/center/register/zc_cg.jsp";
	}
	
	/**
	 * 执行新增
	 * 
	 * @param request
	 * @param response
	 * @param map void
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=doRegister")
	public void doRegister(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		JSONResult result = new JSONResult();
		try{
			result.setData(service.doRegister(map, request));
			result.setSuccessType("");	
		}catch (Exception e){
			log.error(e);
			result.setFailure(e.getMessage());
		}
		result.write(response);
	}
}
