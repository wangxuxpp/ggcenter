package base.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import atomic.exception.SystemException;

/**
 * JSON操作对象，本系统中前台与后台进行通讯使用的对象，注意当JAVA程序向页面
 * 响应时，及时无内容返回也要返回一个空的对象
 * 
 * 使用方法：
 * new JSONResult()
 *      .setData(map)
 *      .setData("data2", "abc")
 * 		.setErrorType("错误消息")
 * 		.setFailure("错误消息")
 *      .setField("错误/警告字段")
 *      .setSuccessType("保存成功")
 *      .setCaveatType("警告消息")
 *      .setOverTimeType()//session超时，弹出屏闭层，正常操作用不到址方法
 *      .setMessage("警告消息")
 *      .write();
 *      
 * @author 王丹
 * @version  2014-3-19
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
public class JSONResult {

	
	private String successMsg ="【%1$s】,保存成功！";
	private String delMeg ="【%1$s】,删除成功！";
	private String reverseMeg ="【%1$s】,恢复成功！";
	private String failureMesg ="操作失败,【%1$s】";
	
	/**
	 * 消息类型
	 * 1.success 成功
	 * 2.error   失败
	 */
	private String type = null;

	/**
	 * 消息
	 */
	private String message = null;
	/**
	 * 消息内容
	 */
	private String content = null;
	
	/**
	 * 消息字段，如页面上某个控件名，或BEAN中的某个属性字段
	 */
	private String field = null;
	
	/**
	 * 任意类型的Object，JAVA与前台页面进行数据传递的主要方式，具体内容根据各
	 * 控制器中约定决定
	 */
	private Map<String, Object> data = new HashMap<String, Object>();
	
	/**
	 * @param request
	 */
	public JSONResult(){
	}

	/**
	 * 将JSON对象写到HttpResponse中
	 * 
	 * @param response
	 * @return ModelAndView
	 * 
	 * history:
	 * 
	 */
	public void write(HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			json.put("jsonType", type);
			json.put("jsonMessage", message);
			json.put("jsonField", field);
			json.put("jsonContent", content);
			json.putAll(data);
			response.setContentType("application/x-json; charset=utf-8");
			json.write(response.getWriter());			
		}catch(Exception e){
			throw new SystemException(e.getMessage());
		}		
	}
	public void writeHtmlType(HttpServletResponse response) {
		try {
			//设置JSON输出字符集
			response.setContentType("text/html; charset=utf-8");
			JSONObject json = new JSONObject();
			json.put("jsonType", type);
			json.put("jsonMessage", message);
			json.put("jsonField", field);
			json.put("jsonContent", content);
			json.putAll(data);
			json.write(response.getWriter());			
		} catch(Exception e) {
			throw new SystemException(e.getMessage());
		}		
	}

	/**
	 * 设置数据对象
	 * 
	 * @param data
	 * @return JSONResult
	 * 
	 * history:
	 * 
	 */
	public JSONResult setData(Map<String, Object> data) {
		this.data.putAll(data);
		return this;
	}
	
	/**
	 * 设置数据值，可以多次操作
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JSONResult setData(String key, Object value){
		this.data.put(key, value);
		return this;
	}
	
	/**
	 * 给错误信息赋值
	 * 
	 * @return JSONResult
	 * 
	 * history:
	 * 
	 */
	public JSONResult setErrorType(String errorName){
		this.type = "error";
		this.message = errorName;
		return this;
	}

	/**
	 * 给错误信息赋值
	 * 
	 * @return JSONResult
	 * 
	 * history:
	 * 
	 */
	public JSONResult setErrorType(String errorName, String errorContent, Object...argsContent){
		this.type = "error";
		this.message = errorName;
		this.content = String.format(errorContent, argsContent);
		return this;
	}
	
	/**
	 * 给成功信息赋值
	 * 
	 * @param message
	 * @return JSONResult
	 * 
	 * history:
	 * 
	 */
	public JSONResult setSuccessType(String message){
		this.type = "success";
		this.message = message;
		return this;
	}
	
	/**
	 * 给字段赋值
	 * 
	 * @param field
	 * @return JSONResult
	 * 
	 * history:
	 * 
	 */
	public JSONResult setField(String field) {
		this.field = field;
		return this;
	}

	/**
	 * 给更多内容赋值
	 * 
	 * @param message
	 * @return JSONResult
	 * 
	 * history:
	 * 
	 */
	public JSONResult setMessage(String message) {
		if( message == null || "".equals(message)){
			message = "对不起，操作错误";
		}
		this.message =  message;
		return this;
	}
	
	/**
	 * 保存成功
	 * 
	 * @param message
	 * @return
	 */
	public JSONResult setSaveSuccess(String message)
	{
		this.type = "success";
		this.message =  String.format(successMsg, message);
		return this;
	}
	
	/**
	 * 删除成功
	 * 
	 * @param message
	 * @return
	 */
	public JSONResult setDelSuccess(String message)
	{
		this.type = "success";
		this.message =  String.format(this.delMeg, message);
		return this;
	}
	
	/**
	 * 恢复删除成功
	 * 
	 * @param message
	 * @return
	 */
	public JSONResult setReverseSuccess(String message)
	{
		this.type = "success";
		this.message =  String.format(this.reverseMeg, message);
		return this;
	}
	
	/**
	 * 操作失败
	 * 
	 * @param message
	 * @return
	 */
	public JSONResult setFailure(String message)
	{
		this.type = "error";
		this.message =  String.format(this.failureMesg, message);
		return this;
	}



}
