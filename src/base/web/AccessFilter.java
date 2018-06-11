package base.web;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.common.Constant;
import base.common.PageUrlConstant;
import business.common.zjcenter.common.parameter.CenterConnectParameter;

/**
 * 
 * 系统登录过滤器
 * 
 * @author wx
 * @version  2015-07-16
 *
 */
@WebFilter
(
		filterName="accessFilter",
		initParams = {@WebInitParam(name = "allowAccess", value = ""),@WebInitParam(name = "encoding", value = "UTF-8")} ,
		urlPatterns= {"*.jsp" , "*.action" , "*.do", "*.hm"}		
)
public class AccessFilter implements Filter {
    
	//允许访问的地址
	private Set<String> allowAccess = Collections.synchronizedSet(new HashSet<String>());
	
	private String  encoding = null;
	
	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");
		
		String[] access = config.getInitParameter("allowAccess").split(";");
		for(int i = 0; i < access.length; i++) {
			allowAccess.add(access[i]);
		}
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		httpRequest.setCharacterEncoding(encoding);
	
		String url = httpRequest.getServletPath().substring(httpRequest.getServletPath().lastIndexOf("/") + 1);
		String fixUrl = url.substring(url.lastIndexOf(".")+1);
		
		if((fixUrl.equals("do")||fixUrl.equals("jsp"))&&!url.equals("index.jsp")){
			if(httpRequest.getSession().getAttribute(Constant.SYS_SESSION) == null){
				httpResponse.sendRedirect(httpRequest.getContextPath() + PageUrlConstant.sysLoginPage);
        		return;
			}
		}else{
			if (fixUrl.equals("action")){
				Object connectType = httpRequest.getParameter(Constant.erpConnectType);
				Object id = httpRequest.getParameter(Constant.erpConnectFactoryId);
				Object user = httpRequest.getParameter(Constant.erpConnectUserName);
				Object password = httpRequest.getParameter(Constant.erpConnectPassword);
				response.setContentType("application/x-json; charset=utf-8");
				if (connectType== null|| id==null || user==null || password ==null){
					httpResponse.getWriter().println(Constant.ERPCONNECTERROR+"登录信息完整");
					return;
				}
				try{
					if(!CenterConnectParameter.getObj().erpCheck(connectType.toString() ,id.toString() , user.toString() , password.toString())){
						httpResponse.getWriter().println(Constant.ERPCONNECTERROR+"登录用户验证失败");
						return ;
					}
				}
				catch(Exception er){
					httpResponse.getWriter().println(er.getMessage());
					return;
				}
			}
		}
		httpResponse.setCharacterEncoding(encoding);
        chain.doFilter(httpRequest, httpResponse);
      
	}
    
	public void destroy() {}
	
}
