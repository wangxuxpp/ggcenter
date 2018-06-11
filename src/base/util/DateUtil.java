package base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import atomic.exception.SystemException;

/**
 * 日期、时间工具类
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
@SuppressWarnings({"rawtypes" , "unchecked"})
public class DateUtil {
	
	/**
	 * 格式化日期
	 * 
	 * @param date  要格式化的日期
	 * @param format 格式化形式
	 * @return 格式化的日期字符串
	 */
	public static String dateFormatString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 获取当前服务器时间的年份
	 * @return 年份
	 */
	public static String getYear() {
		return dateFormatString(new Date(), "yyyy");
	}

	/**
	 * 获取当前服务器时间的月份
	 * @return 月份
	 */
	public static String getMonth() {
		return dateFormatString(new Date(), "MM");
	}

	/**
	 * 获取当前服务器时间的日期
	 * @return 日期
	 */
	public static String getDay() {
		return dateFormatString(new Date(), "dd");
	}
	
	/**
	 * 获取当前服务器时间的小时
	 * @return 小时
	 */
	public static String getHour() {
		return dateFormatString(new Date(), "kk");
	}

	/**
	 * 获取当前服务器时间的分
	 * @return 分
	 */
	public static String getMinute() {
		return dateFormatString(new Date(), "mm");
	}

	/**
	 * 获取当前服务器时间的秒
	 * @return 秒
	 */ 
	public static String getSecond() {
		return dateFormatString(new Date(), "ss");
	}

	/**
	 * 获取当前服务器时间的年-月-日
	 * @return 年-月-日
	 */
	public static String getDate() {
		return dateFormatString(new Date(), "yyyy-MM-dd");
	}
	
	public static String getToDate() {
		return dateFormatString(new Date(), "yyyyMMdd");
	}

	/**
	 * 获取当前服务器时间的时:分:秒
	 * @return 时:分:秒
	 */
	public static String getTime() {
		return dateFormatString(new Date(), "kk:mm:ss");
	}
	
	/**
	 * 获取当前服务器时间的年-月-日 时:分:秒
	 * @return 年-月-日 时:分:秒
	 */
	public static String getDateTime() {
		return dateFormatString(new Date(), "yyyy-MM-dd kk:mm:ss");
	}
	
	/**
	 * 获取当前年份整型值
	 * @return 整型值
	 */
	public static int getIntYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);		
	}
	
	/**
	 * 获取当前月份整型值
	 * @return 整型值
	 */
	public static int getIntMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 获取当前星期几
	 * @return 字符串
	 */
	public static String getDayOfWeek() {
	    Calendar calendar = Calendar.getInstance();    
	    int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
	    switch (day_of_week) {
			case 0: {
				return "星期日";
			}
		    case 1: {
				return "星期一";
			}
			case 2: {
				return "星期二";
			}
			case 3: {
				return "星期三";
			}
			case 4: {
				return "星期四";
			}
			case 5: {
				return "星期五";
			}
			case 6: {
				return "星期六";
			}
			case 7: {
				return "星期日";
			}
			default:
				return "";
		} 
	}
	
	/**
	 * 日期比较
	 * 
	 * @param startDate
	 * @param endDate
	 * @return -1：小于、0：相等、1：大于
	 */
	public static int dateCompareTo(String startDate, String endDate) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Calendar start = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
			
			start.setTime(formatter.parse(startDate));
			end.setTime(formatter.parse(endDate));
			
			return start.compareTo(end);
		} catch(Exception e) { 
			throw new SystemException(e.getMessage()); 
		} 
	}
	
	/**
	 * 获取日期差,返回相差天数
	 * 
	 * @param startDate 
	 * @param endDate
	 * @return 相差天数
	 */
	public static long getDateDifference(String startDate, String endDate) { 	
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	     	Date start = formatter.parse(startDate);    
	     	Date end = formatter.parse(endDate);
	     	
	     	long diff  = end.getTime() - start.getTime();
	     	long days  = diff / (24 * 60 * 60 * 1000);
	     	
	     	return days ;
		} catch(Exception e) { 
			throw new SystemException(e.getMessage()); 
		} 
	}

	/**
	 * 获取日期差,返回相差分钟
	 * 
	 * @param startDate 
	 * @param endDate
	 * @return 相差分钟
	 */
	public static long getDateDifferenceM(String startDate, String endDate) { 	
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	     	Date start = formatter.parse(startDate);    
	     	Date end = formatter.parse(endDate);
	     	
	     	long diff  = end.getTime() - start.getTime();
	     	long min  = diff / (60 * 60 * 1000);
	     	
	     	return min ;
		} catch(Exception e) { 
			throw new SystemException(e.getMessage()); 
		} 
	}
	
	/**
	 * 获得日期
	 * 
	 * @param String YYYY-MM-DD
	 * @param 
	 * @return 
	 */
	public static String getAnyDayOfWeek(String Date){
	
		Calendar calendar = Calendar.getInstance();
		int year = Integer.parseInt(Date.substring(0,4));
		int month = Integer.parseInt(Date.substring(5,7))-1;
		int day = Integer.parseInt(Date.substring(8,10));
		calendar.set(year, month, day);
	    int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
	    switch (day_of_week) {
			case 0: {
				return "星期日";
			}
		    case 1: {
				return "星期一";
			}
			case 2: {
				return "星期二";
			}
			case 3: {
				return "星期三";
			}
			case 4: {
				return "星期四";
			}
			case 5: {
				return "星期五";
			}
			case 6: {
				return "星期六";
			}
			case 7: {
				return "星期日";
			}
			default:
				return "";
		} 
    }
	
	/**
	 * 相差时间
	 * 
	 * @param String YYYY-MM-DD HH:mm:ss
	 * @param String YYYY-MM-DD HH:mm:ss
	 * @return long
	 */
	public static long[] getDistanceTimes(String str1, String str2) {  
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    Date one;  
	    Date two;  
	    long day = 0;  
	    long hour = 0;  
	    long min = 0;  
	    long sec = 0;  
	    try {  
	        one = df.parse(str1);  
	        two = df.parse(str2);  
	        long time1 = one.getTime();  
	        long time2 = two.getTime();  
	        long diff ;  
	        if(time1<time2) {  
	            diff = time2 - time1;  
	        } else {  
	            diff = time1 - time2;  
	        }  
	        day = diff / (24 * 60 * 60 * 1000);  
	        hour = (diff / (60 * 60 * 1000) - day * 24);  
	        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);  
	        sec = (diff/1000-day*24*60*60-hour*60*60-min*60);  
	    } catch (SystemException e) {  
	        e.printStackTrace();  
	    } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    long[] times = {day, hour, min, sec};  
	    return times;  
	}
	
	/**
	 * 得到本月的第一天
	 * 返回格式：yyyy-MM-dd
	 * @return
	 */
	public static String getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));  
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(calendar.getTime());
	 }   
	   
	/**
	 * 得到本月的最后一天 
	 * 返回格式：yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(calendar.getTime());
	}
	
	/**
	 * 
	 * 返回格式：List对象日期格式yyyy-MM-dd
	 * 
	 * @return
	 */
	
	public static List getMonthBetweenDay(String startDay,String endDay){
		List list = new ArrayList();
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		int y = c1.get(Calendar.YEAR);
		c1.set(Util.strInt(startDay.substring(0, 4)), Util.strInt(startDay.substring(5, 7)), Util.strInt(startDay.substring(8,10)));
		c2.set(Util.strInt(endDay.substring(0, 4)), Util.strInt(endDay.substring(5, 7)), Util.strInt(endDay.substring(8,10)));
		String m ;
		String d;
	    try{
		    while(!c2.equals(c1)) {
				y = c1.get(Calendar.YEAR);
				m = c1.get(Calendar.MONTH) +"";
				if(m.length()<2) m = "0"+m;
				d = c1.get(Calendar.DAY_OF_MONTH) +"";
				if(d.length()<2) d = "0"+d;
				c1.add(Calendar.DAY_OF_YEAR, 1);
				list.add(y+"-"+m+"-"+d);
			}
		    list.add(endDay);
	    }catch(SystemException e){
	    	throw new SystemException("获得日期失败");
	    }
	    return list;
	}

	/**
	 * 对指定的字符串型日期加多天
	 * 如传入: 2007-01-01 调用次方法后,将返回2007-01-02
	 * 
	 * @param date 原始日期字符串
	 * @return String 对日期加1后的日期字符串
	 * @throws ProjectException 当传入的日期字符串不是有效的日期格式
	 */
	public static String getIncreaseDay(String date, int num){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			Date d = format.parse(date);
			calendar.setTime(d);
			calendar.add(Calendar.DAY_OF_MONTH, num);
		}catch (ParseException e) {
			throw new SystemException("格式化日期失败！");
		}
		return format.format(calendar.getTime());
	}

	/**
	 * 对指定的字符串型日期减多天
	 * 如传入: 2007-01-01 调用次方法后,将返回2006-12-31
	 * num 要减的天数
	 * @param date 原始日期字符串
	 * @return String 对日期减1后的日期字符串
	 */
	public static String getDecreaseDay(String date,int num){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			Date d = format.parse(date);
			calendar.setTime(d);
			calendar.add(Calendar.DAY_OF_MONTH, -num);
		} catch (ParseException e) {
			throw new SystemException("格式化日期失败！");
		}
		return format.format(calendar.getTime());
	}
}
