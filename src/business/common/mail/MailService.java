package business.common.mail;

import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 注册页
 * 
 * @author 王宇
 * @version  2015-6-23
 * 
 * history:
 *
 */

public class MailService {

    private final transient Properties props = System.getProperties();
    private MailUser mailUser;
    private Session session;
	private String toAddress = ""; // 收件人地址
	private String subject = ""; // 邮件标题
    
    /**
     * 初始化邮件发送器
     * 
     * @param smtpHostName
     * @param username
     * @param password 
     * 
     * history
     * 
     */
    public MailService(String username, String password, String address, String subject) {
    	String smtpHostName = "smtp." + username.split("@")[1];
    	init(username, password, smtpHostName);
    }

    /**
     * 发送邮件
     * 
     * @param recipient
     * @param mail
     * @throws AddressException
     * @throws MessagingException void
     * 
     * history
     * 
     */
    public void send(String context)throws AddressException, MessagingException {
    	send(toAddress, subject, context);
    }

    /**
     * 群发邮件
     * 
     * @param recipients
     * @param mail
     * @throws AddressException
     * @throws MessagingException void
     * 
     * history
     * 
     */
	public void send(List<String> recipients, String context) throws AddressException, MessagingException {
    	send(recipients, subject, context);
	}
	
    /**
     * 初始化
     * 
     * @param username
     * @param password
     * @param smtpHostName void
     * 
     * history
     * 
     */
    private void init(String username, String password, String smtpHostName) {
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.host", smtpHostName);
	    mailUser = new MailUser(username, password);
	    session = Session.getInstance(props, mailUser);
    }

    /**
     * 发送邮件
     * 
     * @param recipient
     * @param subject
     * @param content
     * @throws AddressException
     * @throws MessagingException void
     * 
     * history
     * 
     */
    private void send(String recipient, String subject, String content)throws AddressException, MessagingException {
	    MimeMessage message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(mailUser.getUsername()));
	    message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
	    message.setSubject(subject);
	    message.setContent(content, "text/html;charset=utf-8");
	    Transport.send(message);
    }

    /**
     * 群发邮件
     * 
     * @param recipients
     * @param subject
     * @param content
     * @throws AddressException
     * @throws MessagingException void
     * 
     * history
     * 
     */
    private void send(List<String> recipients, String subject, String content)throws AddressException, MessagingException {
	    MimeMessage message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(mailUser.getUsername()));
	    final int num = recipients.size();
	    InternetAddress[] addresses = new InternetAddress[num];
	    for (int i = 0; i < num; i++) {
	        addresses[i] = new InternetAddress(recipients.get(i));
	    }
	    message.setRecipients(RecipientType.TO, addresses);
	    message.setSubject(subject);
	    message.setContent(content, "text/html;charset=utf-8");
	    Transport.send(message);
    }

}
