package business.center.leaguer.permit.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import base.baseinterface.BaseController;
import base.util.JSONResult;
import business.center.leaguer.permit.service.PermitService;

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

@RequestMapping("/permit.do")
public class PermitController extends BaseController{
	
	@Resource(name="permitService")
	public PermitService service;
	
	/**
	 * 企业认证
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=permit")
	public String permit(HttpServletRequest request, HttpServletResponse response, ModelMap map ){
		request.setAttribute("constructionPermit", service.getStatus(map));
		return "jsp/center/leaguer/permit/permit.jsp";
	}
	
	/**
	 * 认证需知
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=certification")
	public String certification(HttpServletRequest request, HttpServletResponse response, ModelMap map ){
		return "jsp/center/leaguer/permit/certification.jsp";
	}
	
	/**
	 * 企业资料
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=enterpriseData")
	public String enterpriseData(HttpServletRequest request, HttpServletResponse response, ModelMap map ){
		request.setAttribute("requestMap", service.get(map));
		return "jsp/center/leaguer/permit/enterpriseData.jsp";
	}
	
	/**
	 * 提交完成
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=finish")
	public String finish(HttpServletRequest request, HttpServletResponse response, ModelMap map ){
		return "jsp/center/leaguer/permit/finish.jsp";
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
		}catch (Exception e){
			log.error(e);
			result.setFailure(e.getMessage());
		}
		result.write(response);
	}
}

