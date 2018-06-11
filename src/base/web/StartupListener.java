package base.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * 系统启动监听
 * 
 * @author wx
 * @version  2015-05-19
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
public class StartupListener extends ContextLoaderListener implements ServletContextListener {
	
	/**
	 * Log4j对象,可在派生类中直接使用
	 */
	protected Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 系统启动初始化
	 */
	public void contextInitialized(ServletContextEvent event) {
		try {
			super.contextInitialized(event);
			//获取Spring WebApplicationContext对象,存储在WebEnvironment中
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());		
			WebEnvironment.setApplicationContext(webApplicationContext);
		} catch(Exception e) {
			log.error(e.getMessage());
		}
	}
	/**
	 * 系统销毁事件
	 */
	public void contextDestroyed(ServletContextEvent event) {}

}
