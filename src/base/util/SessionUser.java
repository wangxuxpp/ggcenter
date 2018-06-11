package base.util;

import atomic.shareMem.ShareMemoryPojo;



/** 
 * HttpSession中的用户信息
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
@SuppressWarnings("serial")
public class SessionUser extends ShareMemoryPojo {
	
	private int userId =0 ;//登录用户ID
	private int userRole =0 ; //用户角色
	
	private int factoryId =0;
	private String factoryCode = ""; //企业编号
	private String factoryName = ""; //企业编号
	private String pro = "" ;//省
	private String city ="" ;//市
	private String factoryStatus = "" ;//企业是否生效
	
	private String loginName ="";//登录名称
	private String userName ="";
	private String dateTime = "";
    private String fMemo ;
    private String fPassword ;
	
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFMemo() {
		return fMemo;
	}
	public void setFMemo(String memo) {
		fMemo = memo;
	}
	public String getFPassword() {
		return fPassword;
	}
	public void setFPassword(String password) {
		fPassword = password;
	}
	public int getFactoryId() {
		return factoryId;
	}
	public void setFactoryId(int factoryId) {
		this.factoryId = factoryId;
	}
	public String getFactoryCode() {
		return factoryCode;
	}
	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	/**
	 * @return the pro
	 */
	public String getPro() {
		return pro;
	}
	/**
	 * @param pro the pro to set
	 */
	public void setPro(String pro) {
		this.pro = pro;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the factoryStatus
	 */
	public String getFactoryStatus() {
		return factoryStatus;
	}
	/**
	 * @param factoryStatus the factoryStatus to set
	 */
	public void setFactoryStatus(String factoryStatus) {
		this.factoryStatus = factoryStatus;
	}
	

   
	
}
