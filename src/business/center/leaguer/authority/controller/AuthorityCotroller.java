package business.center.leaguer.authority.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import base.baseinterface.BaseController;
import base.util.SessionUser;
import business.center.leaguer.authority.service.AuthorityService;

/**
 * 得到权限列表
 * 
 * @author 王丹
 * @version  1.0  2015-6-29
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
@Controller 
@RequestMapping("/authority.do")

public class AuthorityCotroller extends BaseController {

	@Resource(name="authorityService")
	public AuthorityService service;

	/**
	 * 得到用户的页面信息
	 * 
	 * @param request
	 * @param response void
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=getPage")
	public String getPage(HttpServletRequest request, HttpServletResponse response, ModelMap map, SessionUser u){
		map.put("pageList", service.getPage(u.getFactoryId(), u.getUserRole()));
		return "jsp/center/leaguer/leaguer.jsp";
	}
	
}
