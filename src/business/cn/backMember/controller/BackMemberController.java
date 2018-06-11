package business.cn.backMember.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import base.util.JSONResult;
import business.cn.backMember.service.BackMemberService;

/**
 * 退货单
 * 
 * @author 王丹
 * @version  1.0  2015-6-19
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
@Controller 
@RequestMapping("/backMember.hm")
public class BackMemberController{
	
	//private final String title = "退货单构件信息";
	
	@Resource(name="backMemberService")
	public BackMemberService service;

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
	public String listPage(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		return "jsp/cn/backMember/listPage.jsp";
	}

	/**
	 * 得到列表数据
	 * 
	 * @param request
	 * @param response
	 * @param map void
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=list")
	public void list(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		JSONResult result = new JSONResult();
		try{
			service.getDateList(map);
			result.setSuccessType("同步成功！");
		} catch (Exception e){
			result.setFailure(e.getMessage());
		}
		result.write(response);
	}

}
