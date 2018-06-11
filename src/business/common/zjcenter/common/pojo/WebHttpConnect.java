package business.common.zjcenter.common.pojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import base.exception.CenterConnectException;


@SuppressWarnings({"rawtypes"})
public class WebHttpConnect {
	
	public final static int socketTimeOut = 10000;
	public final static int connectTimeOut = 10000;
	
	public final static String httpFix = "http";
	
	public final static String charSet = "ISO-8859-1";
	
	public final static String mimeTypeText = "text/plain";
	
	public final static String mimeTypeJson = "application/x-json";

	
	
	public static List<NameValuePair> getParameter(Map m)
	{
		List<NameValuePair> r = new ArrayList<NameValuePair>();
		Iterator a = m.entrySet().iterator();
		
		while( a.hasNext())
		{
			Map.Entry entry = (Map.Entry) a.next(); 
			Object value = entry.getValue();
			String key = entry.getKey().toString();
			if (value instanceof List )
			{
				NameValuePair p = new BasicNameValuePair(key , MapToJson.toJsonStr(((List)value)));
				r.add(p);
				continue;
			}
			if(value instanceof Map)
			{
				NameValuePair p = new BasicNameValuePair(key , MapToJson.toJsonStr(((Map)value)));
				r.add(p);
				continue;
			}
			NameValuePair p = new BasicNameValuePair(key , value.toString());
			r.add(p);
		}
		return r;
	}
	
	private static BufferedReader getEntityReader(CloseableHttpResponse response)
	{
		try
		{
			StatusLine statusLine = response.getStatusLine();
	        HttpEntity entity = response.getEntity();
			if (statusLine.getStatusCode() >= 300)
			{
				throw new CenterConnectException(statusLine.getReasonPhrase()+","+statusLine.getStatusCode());
			}
			if (entity == null) {
	            throw new CenterConnectException("Response 没有获得返回信息。");
	        }
	        ContentType contentType = ContentType.getOrDefault(entity);
	        Charset charset = contentType.getCharset();
	        return new BufferedReader(new InputStreamReader(entity.getContent(), charset));
		}catch(Exception er)
		{
			throw new CenterConnectException(er.getMessage());
		}
	}
	
	private static String getResult(BufferedReader reader)
	{

		try
		{
	        StringBuffer r = new StringBuffer();
	        String value = null;
	        while(true)
	        {
	        	value = reader.readLine();
	        	if(value == null)
	        	{
	        		break;
	        	}
	        	r.append(value);
	        }       
			return r.toString();
		}catch(Exception er)
		{
			throw new CenterConnectException(er.getMessage());
		}
	}
	
	
	public static String httpInvoke( String mimeType ,String url, Map p )
	{
		CloseableHttpResponse response = null;
		CloseableHttpClient client = null;
		try
		{
			client = HttpClients.createDefault();
			RequestConfig cfg = RequestConfig.custom()
											.setSocketTimeout(socketTimeOut)
											.setConnectTimeout(connectTimeOut)
											.build();
			HttpPost post = new HttpPost(url);
			post.setConfig(cfg);
		
			UrlEncodedFormEntity myEntity = new UrlEncodedFormEntity(getParameter(p) ,charSet );
			myEntity.setContentEncoding("UTF-8");     
			post.setEntity(myEntity);
			
			response = client.execute(post);
			BufferedReader rd = getEntityReader(response);
			String rStr = getResult(rd);
			return rStr;		
		}
		catch(Exception er)
		{
			throw new CenterConnectException(er.getMessage());
		}
		finally
		{
			try {
				response.close();
				client.close();
			} catch (IOException e) {
				throw new CenterConnectException(e.getMessage());
			}
		}
	}
	
	/*public static String httpInvoke( String mimeType ,String serverName ,int port, String projectName , String methodName, String param )
	{
		CloseableHttpResponse response = null;
		CloseableHttpClient client = null;
		try
		{
			client = HttpClients.createDefault();
			URI uri = new URIBuilder().setScheme(httpFix)
									  .setHost(serverName)
									  .setPath(projectName)
									  .setPort(port)
									  .setParameter("method", methodName)
									  .build();
			RequestConfig cfg = RequestConfig.custom()
											.setSocketTimeout(socketTimeOut)
											.setConnectTimeout(connectTimeOut)
											.build();
			HttpPost post = new HttpPost(uri);
			post.setConfig(cfg);
		
			StringEntity myEntity = new StringEntity(param, ContentType.create(mimeType, charSet));
			post.setEntity(myEntity);
			response = client.execute(post);
			BufferedReader rd = getEntityReader(response);
			String rStr = getResult(rd);
			return rStr;
			
		}
		catch(Exception er)
		{
			throw new CenterConnectException(er.getMessage());
		}
		finally
		{
			try {
				response.close();
				client.close();
			} catch (IOException e) {
				throw new CenterConnectException(e.getMessage());
			}
		}
	}*/
}
