package base.util.pojo;

import base.util.FactoryStatic;


public class ProjectPatch {
	
	private String projectPatch = "";
	private Integer servicePort = -99;
	
	public ProjectPatch(String fixPatch , Integer port)
	{
		projectPatch = fixPatch;
		servicePort = port;
	}
	
	public Integer getServicePort()
	{
		return servicePort;
	}
	public String getPath()
	{
		if (projectPatch.equals(""))
		{
			projectPatch = FactoryStatic.class.getResource("/").getPath();
			projectPatch = projectPatch.substring(1 , projectPatch.length()).toUpperCase();
			projectPatch = projectPatch.substring( 0 , projectPatch.lastIndexOf("WEB-INF")-1);
		}
		return projectPatch;
	}
}
