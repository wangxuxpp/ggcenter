package business.center.system.page.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import base.baseinterface.BaseController;
import base.util.JSONResult;
import business.center.system.page.service.PageService;

/**
 * 模块、页面管理
 * 
 * @author 王丹
 * @version  1.0  2015-6-27
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
@Controller 
@RequestMapping("/page.hm")
@SuppressWarnings({"rawtypes" , "unchecked"})
public class PageController  extends BaseController {
	
	private String title = "系统页面";
	
	@Resource(name="pageService")
	public PageService service;
	
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
	@RequestMapping(params="method=listPage")
	public String listPage(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		return "jsp/center/system/page/listPage.jsp";
	}

	/**
	 * 显示编辑页面
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
		if(map.containsKey("ID")){
			request.setAttribute("requestMap", service.get(map));
		}
		return "jsp/center/system/page/savePage.jsp";
	}
	
	/**
	 * 得到列表页
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
			result.setData(service.list(map));
			result.setSuccessType("");
		} catch (Exception e){
			log.error(e);
			result.setErrorType(e.getMessage());
		}
		result.write(response);
	}

	/**
	 * 新增
	 * 
	 * @param request
	 * @param response
	 * @param map void
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=save")
	public void save(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		JSONResult result = new JSONResult();
		try{
			service.save(map);
			result.setSaveSuccess(title);
		} catch (Exception e){
			log.error(e);
			result.setErrorType(e.getMessage());
		}
		result.write(response);
	}

	/**
	 * 修改
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
			result.setErrorType(e.getMessage());
		}
		result.write(response);
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @param map void
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		JSONResult result = new JSONResult();
		try{
			service.delete(map);
			result.setDelSuccess(title);
		} catch (Exception e){
			log.error(e);
			result.setErrorType(e.getMessage());
		}
		result.write(response);
	}
	
	/**
	 * 子表显示编辑页面
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=saveSubPage")
	public String saveSubPage(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		if(map.containsKey("ID")){
			request.setAttribute("requestMap", service.getSub(map));
		}else{
			Map requestMap = new HashMap();
			requestMap.put("I_SYS_PAGE_ID", map.get("mainId"));
			request.setAttribute("requestMap",requestMap);
		}
		return "jsp/center/system/page/saveSubPage.jsp";
	}
	
	/**
	 * 子表得到列表页
	 * 
	 * @param request
	 * @param response
	 * @param map void
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=listSub")
	public void listSub(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		JSONResult result = new JSONResult();
		try{
			result.setData(service.listSub(map));
			result.setSuccessType("");
		} catch (Exception e){
			log.error(e);
			result.setErrorType(e.getMessage());
		}
		result.write(response);
	}

	/**
	 * 子表新增
	 * 
	 * @param request
	 * @param response
	 * @param map void
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=saveSub")
	public void saveSub(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		JSONResult result = new JSONResult();
		try{
			service.saveSub(map);
			result.setSaveSuccess(title);
		} catch (Exception e){
			log.error(e);
			result.setErrorType(e.getMessage());
		}
		result.write(response);
	}

	/**
	 * 子表修改
	 * 
	 * @param request
	 * @param response
	 * @param map void
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=updateSub")
	public void updateSub(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		JSONResult result = new JSONResult();
		try{
			service.updateSub(map);
			result.setSaveSuccess(title);
		} catch (Exception e){
			log.error(e);
			result.setErrorType(e.getMessage());
		}
		result.write(response);
	}

	/**
	 * 子表删除
	 * 
	 * @param request
	 * @param response
	 * @param map void
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=deleteSub")
	public void deleteSub(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		JSONResult result = new JSONResult();
		try{
			service.deleteSub(map);
			result.setDelSuccess(title);
		} catch (Exception e){
			log.error(e);
			result.setErrorType(e.getMessage());
		}
		result.write(response);
	}

}
