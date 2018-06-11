package business.common.zjcenter.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.ModelMap;

import base.common.Constant;
import base.exception.CenterException;
import business.common.zjcenter.common.parameter.CenterConnectParameter;
import business.common.zjcenter.common.parameter.ConnectParameter;
import business.common.zjcenter.common.pojo.JsonToMap;
import business.common.zjcenter.common.pojo.WebHttpConnect;


@SuppressWarnings({"rawtypes" , "unchecked"})
public class HttpSend {

	
	public static Map httpSend(String requestAddr , Map param , String factoryId)
	{
		Map m = null;
		ConnectParameter centerParam = CenterConnectParameter.getObj().get(factoryId);
		if (centerParam == null)
		{
			return null;
		}
		
		StringBuffer url = new StringBuffer();
		url.append(WebHttpConnect.httpFix).append("://")
			.append(centerParam.server).append(":").append(centerParam.port)
			.append("/").append(centerParam.projectName)
			.append("/").append(requestAddr);
		
		param.put(Constant.ZJcenterConnectType, "ZJCenterUser");
		param.put(Constant.ZJCenterConnectFactoryId, centerParam.factoryId);
		param.put(Constant.ZJCenterConnectUser, centerParam.centerToErpUserName);
		param.put(Constant.ZJCenterConnectPassword, centerParam.centerToErpPassWord);
		String r = WebHttpConnect.httpInvoke(WebHttpConnect.mimeTypeJson, url.toString(), param);
		if (r.substring(0 , 19).equals(Constant.ZJCenterConnectError))
		{
			throw new CenterException("与ERP通讯错误，"+r.substring(19));
		}
		m = JsonToMap.toMap(r);
		return m;
	}
	
	
	public static Map httpSend(String requestAddr , ModelMap map , String factoryId)
	{
		Map m = new HashMap();
		m.putAll(map);
		return httpSend(requestAddr , m , factoryId);
	}
}
