package business.common.zjcenter.readData.cnUnit.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import base.baseinterface.CenterSwapController;
import base.util.JSONResult;
import business.common.zjcenter.readData.cnUnit.service.ConstructUnitService;

/**
 * 施工单位
 * 
 * @author 王丹
 * @version  1.0  2015-6-17
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
@Controller 
@RequestMapping("/constructUnit.action")
public class ConstructUnitController extends CenterSwapController {

	@Resource(name="constructUnitService")
	protected ConstructUnitService service;
	
	/**
	 * 同步方法
	 * 
	 * @param request
	 * @param response
	 * @param map void
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=syncUnit")
	public void syncUnit(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		JSONResult json = new JSONResult();
		try{
			json.setData("DATALIST", service.syncUnit(map));
			json.setSuccessType("");
		}catch (Exception e) {
			json.setErrorType(e.getMessage());
		}
		json.write(response);
	}
}
