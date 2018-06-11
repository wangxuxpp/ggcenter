package business.common.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 身份认证的javabean
 * 
 * @author 王丹
 * @version  1.0  2015-6-26
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
public class MailUser extends Authenticator {
	private String username = null;
	private String password = null;
	
	public MailUser(String username, String password) {
		this.username = username;
	    this.password = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}