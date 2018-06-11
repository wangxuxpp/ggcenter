package base.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import org.springframework.ui.ModelMap;

import atomic.dao.IDAO;
import atomic.exception.SystemException;

/**
 * 系统工具类
 * 
 * @author 王丹
 * @version  Mar 15, 2014
 * 
 * history:
 *
 */
@SuppressWarnings({"rawtypes"})
public class Util {
		
	/**
	 * 判断一个字符串是否为空字符串,当String == null 或 String.equals("")都为空
	 * 
	 * 注:当字符串中包含空格时,也不是空字符串,即方法不会截断字符串中的空格
	 * 
	 * @param str 待判断的字符串
	 * @return boolean true:传入的字符串为空,false:字符串不为空
	 */
	public static boolean strIsEmpty(CharSequence str) {
		return (str == null) || (str.toString().trim().equals(""));
	}
 
    /**
     * 字符串转换成布尔型
     * 
     * @param str
     * @return boolean
     */
	public static boolean strBoolean(CharSequence str) {
		try {
			return Boolean.parseBoolean(str.toString());
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * 字符串转换成整型
	 * 
	 * @param str
	 * @return Integer
	 */
	public static int strInt(CharSequence str) {
		try {
			return Integer.parseInt(str.toString());
		} catch (Exception e) {
			return 0;
		}		
	}
	
	/**
	 * 字符串转换成长整型
	 * 
	 * @param str
	 * @return long
	 */
	public static long strLong(CharSequence str) {
		try {
			return Long.parseLong(str.toString());
		} catch (Exception e) {
			return 0L;
		}
	}
	
	/**
	 * 字符串转换成浮点型
	 * 
	 * @param str
	 * @return float
	 */
	public static float strFloat(CharSequence str) {
		try {
			return Float.parseFloat(str.toString());
		} catch (Exception e) {
			return 0F;
		}
	}
	
	/**
	 * 字符串转换成双精度型
	 * 
	 * @param str
	 * @return double
	 */
	public static double strDouble(CharSequence str) {
		try {
			return Double.parseDouble(str.toString());
		} catch (Exception e) {
			return 0D;
		}
	}
	
	/**
	 * 布尔型转换成字符串
	 * 
	 * @param val
	 * @return String
	 */
    public static String booleanStr(boolean val) {
    	try {
    		return String.valueOf(val);
    	} catch (Exception e) {
    		return "";
    	}
    }
    
    /**
     * 整型转换成字符串
     * 
     * @param val
     * @return String
     */
    public static String intStr(int val) {
    	try {
    		return String.valueOf(val);
    	} catch (Exception e) {
    		return "";
    	}
    }
    
    /**
     * 长整型转换成字符串
     * 
     * @param val
     * @return String
     */
    public static String longStr(long val) {
    	try {
    		return String.valueOf(val);
    	} catch (Exception e) {
    		return "";
    	}
    }
    
    /**
     * 浮点型转换成字符串
     * 
     * @param val
     * @return String
     */
    public static String floatStr(float val) {
    	try {
    		return String.valueOf(val);
    	} catch (Exception e) {
    		return "";
    	}
    }
    
    /**
     * 双精度型转换成字符串
     * 
     * @param val
     * @return String
     */
    public static String doubleStr(double val) {
    	try {
    		return String.valueOf(val);
    	} catch (Exception e) {
    		return "";
    	}
    }
    
	/**
	 * 对象转换成布尔型
	 * 
	 * @param obj
	 * @return boolean
	 */
	public static boolean objBoolean(Object obj) {
		try {
			if(obj == null) {
				return false;
			}
			return Boolean.parseBoolean(obj.toString());
		} catch (Exception e) {
			return false;
		}
	}
    
    /**
	 * 对象转换成整型
	 * 
	 * @param obj
	 * @return Integer
	 */
	public static int objInt(Object obj) {
		try {
			if(obj == null) {
				return 0;
			}
			return Integer.parseInt(obj.toString());
		} catch (Exception e) {
			return 0;
		}	
	}
	
	/**
     * 对象转换成长整型
     * 
     * @param obj
     * @return long
     */
    public static long objLong(Object obj) {
    	try {
    		if(obj == null) {
        		return 0L;
    		}
    		return Long.parseLong(obj.toString());
    	} catch(Exception e) {
    		return 0L;
    	}    	
    }
    
	/**
     * 对象转换成浮点型
     * 
     * @param obj
     * @return float
     */
    public static float objFloat(Object obj) {
    	try {
    		if(obj == null) {
        		return 0F;
    		}
    		return Float.parseFloat(obj.toString());
		} catch (Exception e) {
			return 0F;
		}
    }
	
    /**
     * 对象转换成双精度型
     * 
     * @param obj
     * @return double
     */
    public static double objDouble(Object obj) {
    	try {
    		if(obj == null) {
        		return 0D;
    		}
    		return Double.parseDouble(obj.toString());
    	} catch(Exception e) {
    		return 0D;
    	}
    }
    
    /**
     * 对象转换成字符串
     * 
     * @param obj
     * @return String
     */
    public static String objStr(Object obj) {
    	try {
    		if(obj == null) {
    			return "";
    		}
    		return obj.toString();
    	} catch (Exception e) {
    		return "";
    	}
    }
    
    /**
     * 对象转换成日期
     * 
     * @param obj
     * @return Date
     */
    public static Date objDate(Object obj) {
    	try {
    		if(obj == null) {
    			return new Date();
    		}
    		return (Date)obj;
    	} catch (Exception e) {
    		return new Date();
    	}
    }
    
    /**
     * 错误或异常对象转换成字符串
     * 
     * @param e 错误或异常对象
     * @return String
     */
    public static String throwableStr(Throwable e) {
    	StringWriter sw = new StringWriter();
    	PrintWriter pw = new PrintWriter(sw);
    	e.printStackTrace(pw);
    	return sw.toString();
    }
    
    /**
     * 对字符串进行HTML格式编码
     * 转换：
     * 	 回车 => <br/>
     * 	 空格 => &nbsp;
     * 	 <   => &lt;
     * 	 >   => &gt;   
     * 
     * @param str 普通格式的字符串
     * @return String
     */
    public static String htmlCoding(String str) {    	
    	String html = str.replaceAll("<", "&lt;")
    	                 .replaceAll(">", "&gt;")
    	                 .replaceAll("\r\n", "<br>")
    	                 .replaceAll(" ", "&nbsp;");
    	return html;
    }
    
    /**
     * 对HTML格式的字符串解码
     * 
     * 转换：
     *   <br/>  => 回车
     *   &nbsp; => 空格
     *   &lt;   => <
     *   &gt;   => >
     * 
     * @param html HTML格式的字符串
     * @return String
     */
    public static String htmlDecode(String html) {
    	String str = html.replaceAll("&lt;", "<")
        				 .replaceAll("&gt;", ">")
        				 .replaceAll("<br>", "\r\n")
        				 .replaceAll("&nbsp;", " ");
    	return str;	
    }
    
    /**
     * 保证指定的值在转换成字符串后,具有指定的长度,如果不足指定的长度,前面将补"0"
     * 
     * @param val 原始字符串值
     * @param len 最终的字符串长度
     * @return String
     */
    public static String padStrZero(int val, int len) {
    	String result = intStr(val);
    	return padStrZero(result, len);
    }
    
    /**
     * 保证指定的值在转换成字符串后,具有指定的长度,如果不足指定的长度,前面将补"0"
     * 
     * @param val 原始字符串值
     * @param len 最终的字符串长度
     * @return String
     */
    public static String padStrZero(long val, int len) {
    	String result = longStr(val);
    	return padStrZero(result, len);
    }
    
    /**
     * 保证指定的值在转换成字符串后,具有指定的长度,如果不足指定的长度,前面将补"0"
     * 
     * @param val 原始字符串值
     * @param len 最终的字符串长度
     * @return String
     */
    public static String padStrZero(String val, int len) {
    	String result = val;
    	while(result.length() < len) {
    		result = "0" + result;
    	}
    	return result;
    }
    
    /**
	 * 带格式解码,编码转换 
	 * 
	 * @param str 要解码的的字符串
	 * @param srcEncoding 原始字符串的编码
	 * @param destEncoding 目标字符串的编码
	 * @return
	 */
	public static String encoding(String str, String srcEncoding, String destEncoding) {
		try {
			byte[] src = URLDecoder.decode(str, destEncoding).getBytes(srcEncoding);
			String dest = new String(src, destEncoding);
			return dest;
		} catch(Exception e) {
			return "";
		}
	}
	
    /**
	 * 不带格式解码,编码转换 
	 * 
	 * @param str 要解码的字符串
	 * @param srcEncoding 原始字符串的编码
	 * @param destEncoding 目标字符串的编码
	 * @return
	 */
	public static String encodingNoDecode(String str, String srcEncoding, String destEncoding) {
		try {
			byte[] src = str.getBytes(srcEncoding);
			String dest = new String(src, destEncoding);
			return dest;
		} catch(Exception e) {
			return "";
		}
	}

	
	public static Double dRound(double val, int scale) {
		if(scale < 0) {
			throw new SystemException("无效的scale参数,参数scale必须是大于或等于零的整数");
        } else {
			//下面的舍入模式是ROUND_HALF_UP
			BigDecimal bd = new BigDecimal(val);
			return bd.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
	}
	
    /**
	 * 四舍五入
	 * 
	 * @param val 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return String 四舍五入后的结果
	 */
	public static String round(double val, int scale) {
		return objStr(dRound(val , scale));
	}
	
	/**
	 * double类型输出格式处理
	 * 
	 * @param val 需要输出格式处理的数字
	 * @param scale 小数点后保留几位
	 * @return String 输出格式处理后的结果
	 */
	public static String doubleFormat(double val, int scale) {
		if(scale < 0) {
			throw new SystemException("无效的scale参数,参数scale必须是大于或等于零的整数");
        } else {
        	String valStr = doubleStr(val);
        	String num = valStr.substring(0, valStr.indexOf("."));
        	String dec = valStr.substring(valStr.indexOf(".") + 1, valStr.length());
        	//下面处理输出格式
        	String format = "";
        	if(dec.length() > scale) {
        		dec = dec.substring(0, scale);
        	} else {
	        	while(dec.length() < scale) {
	        		dec = dec + "0";
	        	}
        	}
        	//是否带小数点处理
        	if(scale > 0) {
        		format = num + "." + dec;
        	} else {
        		format = num;
        	}
        	return format;
        }
	}
      
	
	/**
     * 从HttpServletRequest获取全部参数,以Map形式返回
     * 
     * @param request HttpServletRequest对象
     * @return Map
     */
    
	public static Map getParameterValues(HttpServletRequest request) {
    	Map<String, String> parameter = new HashMap<String, String>();
    	Enumeration enume = request.getParameterNames();
    	while(enume.hasMoreElements()) {
    		String key = objStr(enume.nextElement());
    		String value = request.getParameter(key).trim();
    		parameter.put(key, value);    		
    	}
    	return parameter;
    }
    
    /**
     * 从HttpServletRequest获取全部参数,以Map形式返回
     * 
     * @param request HttpServletRequest对象
     * @param srcEncoding 原始字符串的编码
	 * @param destEncoding 目标字符串的编码
     * @return Map
     */
    
	public static Map getParameterValues(HttpServletRequest request, String srcEncoding, String destEncoding) {
    	Map<String, String> parameter = new HashMap<String, String>();
    	Enumeration enume = request.getParameterNames();
    	while(enume.hasMoreElements()) {
    		String key = objStr(enume.nextElement());
    		String value = encoding(request.getParameter(key).trim(), srcEncoding, destEncoding);
    		parameter.put(key, value);   		
    	}
    	return parameter;
    }
    /**
	 * 拼接字符串
	 *	
	 * @param str 传入的需要拼接的字符数组
	 * @return
	 */
	public static String strSplicing(String... str){
		StringBuffer s = new StringBuffer(200);
		for(int i=0;i<str.length;i++){
			s.append(str[i]);
		}
		return s.toString();
	}
	
	/**
	 * 汉字转换为汉语拼音首字母,英文字符不变 
	 * 
	 * @param hanye 汉语拼音
	 * @return String 转换为汉语拼音首字母
	 */
    public static String convertToFirstSpell(String hanye) {
    	StringBuffer hanyePinyin = new StringBuffer();
        char[] hanyeChar = hanye.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for(int i = 0; i < hanyeChar.length; i++) {
        	if(hanyeChar[i] > 128) {    
            	try {    
            		hanyePinyin.append(PinyinHelper.toHanyuPinyinStringArray(hanyeChar[i], defaultFormat)[0].charAt(0));
            	} catch(Exception e) {    
            		hanyePinyin.append(hanyeChar[i]);
            		//throw new SystemException("汉字转换汉语拼音时报错,请转换为半角输入法后重新输入");    
            	}
        	} else {    
        		hanyePinyin.append(hanyeChar[i]);    
        	}    
        }    
        return hanyePinyin.toString();    
     }
    
    /**
     * 进行密码加密
     * 
     * @param dao
     * @param value
     * @return String
     * 
     * history
     * 
     */
    public static String getPassword(IDAO dao, String value){
    	String str ="select fun_password('"+value+"') CPD from dual";
    	return dao.queryString(str, null);
    }
    
    /**
     * 进行密码解密
     * 
     * @param dao
     * @param value
     * @return String
     * 
     * history
     * 
     */
    public static String getReversePassword(IDAO dao, String value){
    	String str ="select fun_reversepassword('"+value+"') CPD from dual";
    	return dao.queryString(str, null);
    }
    
	/**
	 * 得到编号
	 * 
	 * @param dao
	 * @param tableName
	 * @return String
	 * 
	 * history
	 * 
	 */
	public synchronized static String getBusinessCode(IDAO dao, String tableName){
    	String str ="select trim(to_char(sysdate, 'yymmdd') ||"
            		+" trim(to_char(nvl((select c_numCode"
            		+" from (select to_number(nvl(substr(c_code, 7, 5), 0)) c_numCode,"
            		+" substr(c_code, 1, 6) c_dateCode,"
            		+" dense_rank() over(order by to_number(nvl(substr(c_code, 7, 5), 0)) desc) c_order"
            		+" from "+tableName
            		+" where substr(c_code, 1, 6) ="
            		+" to_char(sysdate, 'yymmdd'))"
            		+" where c_order = 1),"
            		+" 0)+1,"
            		+" '00000'))) C_CODE"
    				+" from dual";
    	
    	List l = dao.executeQuery(str, null);
    	return ((Map)l.get(0)).get("C_CODE").toString();
    }

	/**
	 * 得到序号信息
	 * 
	 * @param dao
	 * @param tableName
	 * @param fieldName
	 * @param whereSql
	 * @return String
	 * 
	 * history
	 * 
	 */
	public synchronized static String getfactoryCode(IDAO dao, String tableName, String fieldName, String whereSql){
    	String str ="select max("+ fieldName +") from "+ tableName +" where 1 = 1 "+whereSql;
    	String cCode = dao.queryString(str, null);
    	int codeNum = 1;
    	if(!"".equals(cCode)){
	    	int lenght = cCode.length();
	    	codeNum = Util.objInt(cCode.substring(lenght-5));
	    	codeNum = codeNum + 1;
    	}
    	return ensureStringLen(codeNum+"", 5);
    }
    
    /**
	 * 输入的拼音为大写时，返回正确的json查询对象
	 * 
	 * @param ModelMap
	 * @return ModelMap
	 * 
	 * history
	 * 
	 */
    public static ModelMap nameToLower(ModelMap requestModel){
		String json = requestModel.get("filters").toString();
		JSONObject jsonObj = JSONObject.fromObject(json);
		String rules = jsonObj.getString("rules");
		JSONArray ja = JSONArray.fromObject(rules);
		for(int i = 0; i<ja.size(); i++){
			JSONObject jo = ja.getJSONObject(i);
			if(jo.containsValue("C_NAME")){
				if(!isHanyuSpell(jo.get("data").toString())){
					String data = jo.get("data").toString().toLowerCase();
					jo.remove("data");
					jo.put("data", data);
					ja.remove(i);
					ja.add(i,jo);
				}
			}
		}
		jsonObj.remove("rules");
		jsonObj.put("rules", ja);
		requestModel.remove("filters");
		requestModel.put("filters", jsonObj);
    	return requestModel;
    }
    
    /**
	 * 汉语拼音
	 * 
	 * @param String
	 * @return Boolean
	 * 
	 * history
	 * 
	 */
    public static boolean isHanyuSpell(String str){
    	boolean flag = false;
    	char[] hanyeChar = str.toCharArray(); 
    	for(int i = 0; i < hanyeChar.length; i++) { 
			if(hanyeChar[i] > 128) {    
		    	return flag = true;
			}
    	}
		return flag;
    }
    
    /**070
     * 保证指定的值在转换成字符串后，具有指定的长度，如果不足指定的长度，前面将补0
     * 
     * @param value 原始字符串值
     * @param len   最终的字符串长度
     * @return 符合指定长度的字符串，可能前面已经补0
     */
    public static String ensureStringLen(String value, int len){
    	String result = value;
    	while(result.length() < len)
    		result = "0" + result;
    	return result;
    }
    
    /**
     * 通过日期与序号得到编号
     * 
     * @param date
     * @param order
     * @return String
     * 
     * history
     * 
     */
    public static String getCode(String date, String order){
    	order = ensureStringLen(order, 5);
    	String[] str = date.split("-");
    	String code = str[0].substring(2)+str[1]+str[2]+order;
    	return code;
    }
    
    /**
     * 得到不用登录操作员信息
     * 
     * @return SessionUser
     * 
     * history
     * 
     */
    public static SessionUser getUser(){
		SessionUser user = new SessionUser();
		user.setUserId(0);
		user.setUserName("游客");
		return user;
    }
    
}
