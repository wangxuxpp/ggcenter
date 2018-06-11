package business.center.system.erpFactory.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import base.baseinterface.BaseController;
import base.util.JSONResult;
import business.center.system.erpFactory.service.ErpFactoryService;

/**
 * 平台用户连接
 * 
 * @author 王丹
 * @version  1.0  2015-6-5
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
@Controller 
@RequestMapping("/erpFactory.do")
public class ErpFactoryController  extends BaseController {
	private final String title = "平台用户连接";

	@Resource(name="erpFactoryService")
	public ErpFactoryService service;

	/**
	 * 显示编辑查看页
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=savePage")
	public String savePage(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		request.setAttribute("requestMap", service.get(map));
		return "jsp/system/erpFactory/savePage.jsp";
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
			result.setSaveSuccess(title);
		} catch (Exception e){
			log.error(e);
			result.setFailure(e.getMessage());
		}
		result.write(response);
	}
	
}
