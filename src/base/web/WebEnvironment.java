package base.web;

import org.springframework.web.context.WebApplicationContext;

/**
 * 获得WEB应用的容器,使不在控制器中注入的对象也可以访问WEB资源
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
public class WebEnvironment {
	
	/**
	 * 声明一个WebApplicationContext"应用的容器"对象
	 */
	private static WebApplicationContext webApplicationContext = null;
	
	/**
	 * 获取当前WebApplicationContext上下文对象,在整个系统中只存在一个对象
	 * 
	 * @return WebApplicationContext
	 */
	public static WebApplicationContext getWebApplicationContext() {
		return webApplicationContext;
	}
	
	/**
	 * 设置当前WebApplicationContext上下文对象,在整个系统中只存在一个对象
	 * 
	 * @param applicationContext 
	 */
	public static void setApplicationContext(WebApplicationContext webApplicationContext) {
		WebEnvironment.webApplicationContext = webApplicationContext;
	}
 
	/**
	 * 通过SPRING WebApplicationContext上下文对象,获取BEAN
	 * 
	 * @param beanName @Service注入名称
	 * @return 得到一个@Service类
	 */
	public static Object getBean(String beanName) {
		return WebEnvironment.webApplicationContext.getBean(beanName);
	}	 
	
}
