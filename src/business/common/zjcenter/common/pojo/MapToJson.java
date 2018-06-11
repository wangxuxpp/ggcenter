package business.common.zjcenter.common.pojo;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@SuppressWarnings({"rawtypes"})
public class MapToJson {

	
	public static String toJsonStr(Map m)
	{
		JSONObject json = new JSONObject();
		json.putAll(m);
		return json.toString();
	}
	
	
	public static String toJsonStr(List l)
	{
		JSONArray json = new JSONArray();
		json.addAll(l);
		return json.toString();
	}
}
