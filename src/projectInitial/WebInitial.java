package projectInitial;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.Log4jConfigListener;

import base.web.StartupListener;
import projectInitial.cfg.mvc.ConfiyMVC;

public class WebInitial implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext cn) throws ServletException {
		
		cn.setInitParameter("webAppRootKey", "ggcenter.root");
		cn.setInitParameter("log4jConfigLocation", "/WEB-INF/config/log4j.properties");
		cn.setInitParameter("log4jRefreshInterval", "60000");
		cn.addListener(new Log4jConfigListener());
		
		cn.setInitParameter("contextConfigLocation", "/WEB-INF/config/applicationContext.xml");
		cn.addListener(new StartupListener());

		//XmlWebApplicationContext rootSpringMvc = new XmlWebApplicationContext();
		//rootSpringMvc.setConfigLocation("/WEB-INF/config/dispatcher-servlet.xml");
		
		AnnotationConfigWebApplicationContext rootSpringMvc = new AnnotationConfigWebApplicationContext();
		rootSpringMvc.register(ConfiyMVC.class);
		ServletRegistration.Dynamic dispatcher =cn.addServlet("dispatcher", new DispatcherServlet(rootSpringMvc));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("*.do");
		dispatcher.addMapping("*.action");
		dispatcher.addMapping("*.hm");
	}

}
