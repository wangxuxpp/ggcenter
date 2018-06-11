package business.center.leaguer.role.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import base.baseinterface.BaseController;
import base.util.JSONResult;
import base.util.SessionUser;
import base.util.Util;
import business.center.leaguer.role.service.RoleService;
import business.common.clearMem.ClearMem;

/**
 * 角色表
 * 
 * @author 王宇
 * @version  2015-6-29
 * 
 * history:
 *
 */
@Controller 

@RequestMapping("/role.do")
public class RoleController extends BaseController{
	
	private final String title = "角色信息";
	
	@Resource(name="roleService")
	public RoleService service;
	
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
		return "jsp/center/leaguer/role/listPage.jsp";
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
	public String savePage(HttpServletRequest request, HttpServletResponse response, ModelMap map ){
		if(map.containsKey("ID")){
			request.setAttribute("requestMap", service.get(map));
		}
		return "jsp/center/leaguer/role/savePage.jsp";
	}
	
	/**
	 * 显示角色权限信息
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return String
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=authorityPage")
	public String authorityPage(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		request.setAttribute("requestMap", service.get(map));
		request.setAttribute("authorityMap", service.getAuthorityMap(map));
		request.setAttribute("authorityAllList",service.getAuthorityAllList(map));
		return "jsp/center/leaguer/role/authorityPage.jsp";
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
			result.setData(service.list(map));
			result.setSuccessType("");
		} catch (Exception e){
			log.error(e);
			result.setFailure(e.getMessage());
		}
		result.write(response);
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
	@RequestMapping(params="method=save")
	public void save(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		JSONResult result = new JSONResult();
		try{
			service.save(map);
			result.setSaveSuccess(title);
		} catch (Exception e){
			log.error(e);
			result.setFailure(e.getMessage());
		}
		result.writeHtmlType(response);
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
		result.writeHtmlType(response);
	}
	
	/**
	 * 执行删除
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
			result.setFailure(e.getMessage());
		}
		result.write(response);
	}

	/**
	 * 执行保存wed权限
	 * 
	 * @param request
	 * @param response
	 * @param map void
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=authoritySave")
	public void wedLimit(HttpServletRequest request, HttpServletResponse response, ModelMap map, SessionUser user){
		JSONResult result = new JSONResult();
		String[] subIds = request.getParameterValues("I_SYS_PAGESUB_ID");
		map.put("ids", subIds.length);
		map.put("I_SYS_PAGESUB_ID", subIds);
		try{
			service.authoritySave(map);
			ClearMem.clearWebRole(user.getFactoryId(), Util.objInt(map.get("ID")));
			result.setSaveSuccess("系统模块角色权限");
		} catch (Exception e){
			log.error(e);
			result.setFailure(e.getMessage());
		}
		result.write(response);
	}
	
}

