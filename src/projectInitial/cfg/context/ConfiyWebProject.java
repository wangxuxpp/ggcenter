package projectInitial.cfg.context;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import base.util.pojo.ProjectPatch;
import redis.ShareMemoryParameter;
import atomic.shareMem.ShareMem;



@Order(2)
@Configuration
@PropertySource(value = "WEB-INF/config/mem.properties", ignoreResourceNotFound = true)  
//@ComponentScan(basePackages = {"atomic.dao,atomic.baseinterface"})
public class ConfiyWebProject {

	@Autowired  
	Environment env;  
	
	@Bean(name="shareMem")
	public ShareMem shareMem()
	{
		ShareMemoryParameter smp = new ShareMemoryParameter();
		smp.setIp(env.getProperty("mem.ip"));
		smp.setPort(Integer.parseInt(env.getProperty("mem.port")));
		smp.setMaxObject(Integer.parseInt(env.getProperty("mem.maxObject").trim()));
		smp.setMaxIdle(Integer.parseInt(env.getProperty("mem.maxIdle").trim()));
		smp.setMaxWait(Integer.parseInt(env.getProperty("mem.maxWait").trim()));
		smp.setTestOnBorrow(Boolean.parseBoolean(env.getProperty("mem.testOnBorrow").trim()));
		smp.setTestOnReturn(Boolean.parseBoolean(env.getProperty("mem.testOnReturn").trim()));
		boolean enable = Boolean.parseBoolean(env.getProperty("shareMem.enable").trim());
		return new ShareMem(smp ,enable);
	}
	@Bean(name="projectPatch")
	public ProjectPatch filePatch()
	{
		String patch = env.getProperty("projectPatch.patch").toString();
		int port = Integer.parseInt(env.getProperty("projectPatch.filePort").trim());
		return new ProjectPatch(patch ,port);
	}
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver getMultipartResolver()
	{
		CommonsMultipartResolver r = new CommonsMultipartResolver();
		r.setMaxUploadSize(10240000000L);
		r.setMaxInMemorySize(8192);
		r.setDefaultEncoding("utf-8");
		return r;
	}
}
