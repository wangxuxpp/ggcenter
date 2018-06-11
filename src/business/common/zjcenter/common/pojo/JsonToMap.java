package business.common.zjcenter.common.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@SuppressWarnings({"rawtypes" , "unchecked"})
public class JsonToMap{
	
	
	
	public static Map toMap(String jsonStr)
	{
		Map m = null;
		try {
			JSONObject json = JSONObject.fromObject(jsonStr);	
			m = getJsonMap(json);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
		
	}
	
	
	private  static List<Map<String ,Object>> getJsonList(JSONArray json) throws Exception
	{
		List l = new ArrayList();
		try
		{
			for (int i = 0  ; i<json.size() ; i++)
			{
				Object obj = json.get(i);
	
				if (obj instanceof JSONObject)
				{
					l.add(getJsonMap((JSONObject)obj));
				} 
				else {
					if (obj instanceof JSONArray)
					{
						l.add(getJsonList((JSONArray)obj));
					}
				}
			}
		}catch(Exception e)
		{
			throw e;
		}
		return l;
	}

	
	
	private static Map<String ,Object> getJsonMap(JSONObject json) throws Exception
	{
		Map m = new HashMap();
		try {
			Iterator<JSONObject> it =json.keys();
			while(it.hasNext())
			{
				Object key = it.next();
				Object value = json.get(key.toString());
				if (value instanceof JSONArray)
				{
					m.put(key.toString().toUpperCase(), getJsonList((JSONArray)value));
				} 
				else if(value instanceof JSONObject){
					m.put(key.toString().toUpperCase(), getJsonMap((JSONObject)value));
					
				}else {
					m.put(key.toString().toUpperCase(), value);
				}
			}
			
		} catch (Exception e) {
			throw e;
		}
		return m;
	}
	
	
}
