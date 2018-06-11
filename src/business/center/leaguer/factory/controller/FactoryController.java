package business.center.leaguer.factory.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import base.baseinterface.BaseController;
import base.util.JSONResult;
import business.center.leaguer.factory.service.FactoryService;

/**
 * 企业信息
 * 
 * @author 王宇
 * @version  2015-7-01
 * 
 * history:
 *
 */
@Controller 

@RequestMapping("/factory.do")
public class FactoryController extends BaseController{
	
	@Resource(name="factoryService")
	public FactoryService service;
	
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
	@RequestMapping(params="method=listPage")
	public String listPage(HttpServletRequest request, HttpServletResponse response, ModelMap map ){
		request.setAttribute("requestMap", service.get(map));
		return "jsp/center/leaguer/factory/factory.jsp";
	}
	
	/**
	 * 执行修改
	 * 
	 * @param request
	 * @param response
	 * @param map void
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=update")
	public void update(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		JSONResult result = new JSONResult();
		try{
			service.update(map);
			result.setSuccessType("");	
		}catch(Exception e){
			log.error(e);
			result.setFailure(e.getMessage());
		}
		result.write(response);
	}
	
}

