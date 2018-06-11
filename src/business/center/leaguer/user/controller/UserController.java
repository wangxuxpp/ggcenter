package business.center.leaguer.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import base.baseinterface.BaseController;
import base.common.Constant;
import base.util.JSONResult;
import base.util.SessionUser;
import business.center.leaguer.user.service.UserService;

/**
 * 用户控制层
 * 
 * @author 王丹
 * @version  2014-3-21
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
@Controller
@RequestMapping("/user.do")
public class UserController  extends BaseController {

	private final String title = "用户信息";
	
	@Resource(name="userService")
	public UserService service;
	
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
	public String listPage(HttpServletRequest request, HttpServletResponse response, ModelMap map , SessionUser u){
		return "jsp/center/leaguer/user/listPage.jsp";
	}

	/**
	 * 显示选择页面
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=selectPage")
	public String selectPage(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		return "jsp/center/leaguer/user/selectPage.jsp";
	}
	
	/**
	 * 显示编辑页
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
		request.setAttribute("roleList", service.getRoleList(map));
		return "jsp/center/leaguer/user/savePage.jsp";
	}

	/**
	 * 显示密码修改页
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=passwordPage")
	public String passwordPage(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		map.put("ID", ((SessionUser)map.get(Constant.SYS_SESSION)).getUserId());
		request.setAttribute("requestMap", service.get(map));
		return "jsp/center/leaguer/user/passwordPage.jsp";
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
			result.setSaveSuccess(title);
		} catch (Exception e){
			log.error(e);
			result.setFailure(e.getMessage());
		}
		result.write(response);
	}

	/**
	 * 得到选择列表页
	 * 
	 * @param request
	 * @param response
	 * @param map void
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=selectList")
	public void selectList(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		JSONResult result = new JSONResult();
		try{
			result.setData(service.selectList(map));
			result.setSaveSuccess(title);
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
		result.write(response);
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

	/**
	 * 执行修改密码
	 * 
	 * @param request
	 * @param response
	 * @param map void
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=updatePassword")
	public void updatePassword(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		JSONResult result = new JSONResult();
		try{
			service.updatePassword(map);
			result.setSuccessType("密码修改成，下次登录系统请用新密码！");
		} catch (Exception e){
			log.error(e);
			result.setFailure(e.getMessage());
		}
		result.write(response);
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
	 * 执行恢复删除
	 * 
	 * @param request
	 * @param response
	 * @param map void
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=reverseDelete")
	public void reverseDelete(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		JSONResult result = new JSONResult();
		try{
			service.regain(map);
			result.setReverseSuccess(title);
		} catch (Exception e){
			log.error(e);
			result.setFailure(e.getMessage());
		}
		result.write(response);
	}

	/**
	 * 执行重置密码
	 * 
	 * @param request
	 * @param response
	 * @param map void
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=reversePassword")
	public void reversePassword(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		JSONResult result = new JSONResult();
		try{
			service.reversePassword(map);
			result.setSuccessType("密码重置成功，重置后的密码为：123456，下次登录系统请用户"+map.get("C_NAME").toString()+"使用重置后的密码！");
		} catch (Exception e){
			log.error(e);
			result.setFailure(e.getMessage());
		}
		result.write(response);
	}
	
}
