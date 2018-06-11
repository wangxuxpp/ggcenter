package base.common;

/**
 * 系统常量类
 * 
 * @author wx
 * @version  2015-05-19
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
public class Constant {
	
	/**
	 * HttpSession中的属性名称,用于绑定用户信息
	 */	
	public static final String SYS_SESSION = "SYS_SESSION_USER";
	public final static String CAPCHA_SESSION_KEY = "CAPCHA_SESSION_KEY";


	/**
	 * 状态-0:正常
	 */
	public static final int STATUS_START = 0;
	/**
	 * 状态-1:删除
	 */
	public static final int STATUS_STOP = 1;
	
	public static final String GRIDMAPKEY = "jsonMap";
	public static final String MODELRESULT = "model";
	
	/**
	 * erp链接平台错误头
	 */
	public static final String ERPCONNECTERROR = "IllegalEepUser";
	
	public static final String erpConnectType = "connectType";
	public static final String erpConnectFactoryId = "centerErpId";
	public static final String erpConnectUserName = "erpusername";
	public static final String erpConnectPassword = "erpuserpassword";
	
	public static final String ZJCenterConnectError ="IllegalZJCenterUser";
	public static final String ZJcenterConnectType = "ZJCENTERUSER";
	public static final String ZJCenterConnectFactoryId = "CENTERERPID";
	public static final String ZJCenterConnectUser = "ZJCENTERUSRNAME";
	public static final String ZJCenterConnectPassword = "ZJCENTERUSRPASSWORD";
	
		
}
