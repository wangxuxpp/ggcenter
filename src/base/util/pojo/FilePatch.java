package base.util.pojo;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import base.util.FactoryStatic;


public class FilePatch {

	public static String getProjectPatch() {
		return FactoryStatic.getProjectPatch().getPath();
	}

	public static String getWarePatch(){
		return getProjectPatch()+File.separator+"file"+File.separator+"ware"+File.separator;
	}
	public static String getMemberPatch(){
		return getProjectPatch()+File.separator+"file"+File.separator+"member"+File.separator;
	}
	public static String getNotifyPatch(){
		return getProjectPatch()+File.separator+"file"+File.separator+"notify"+File.separator;
	}
	public static String getFactoryPatch(){
		return getProjectPatch()+File.separator+"file"+File.separator+"factory"+File.separator;
	}
	
	public static String getApplicationURL(HttpServletRequest request)
	{
		
		Integer port = 0;
		if (FactoryStatic.getProjectPatch().getServicePort()== -99){
			port = request.getServerPort(); 
		} else {
			port = FactoryStatic.getProjectPatch().getServicePort();
		}
		return request.getScheme()+"://"+request.getServerName()+":"+port+request.getContextPath();
	}
}
