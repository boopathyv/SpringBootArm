package com.aram.connect.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.aram.connect.AramConstants;
import com.aram.connect.dataObjects.EmailVO;
import com.aram.connect.persistence.dao.JoinUs;

public class EmailNotice {

	@SuppressWarnings("finally")
	public String sendTest(EmailVO emailVO) throws Throwable {

		String retVal = "";

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		File file;
		// Create properties, get Session
		Properties properties = new Properties();

		// use smtp.honeywell.com on local machine else use direct IP(10.238.252.20).

		properties.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		properties.put("mail.smtp.port", "587"); // TLS Port
		properties.put("mail.smtp.auth", "true"); // enable authentication
		properties.put("mail.smtp.starttls.enable", "true");

		final String fromEmail = AramConstants.ARAM_GMAIL; // requires valid gmail id
		final String password = AramConstants.ARAM_GMAIL_PWD; // correct password for gmail id
		// final String toEmail = "info@aramiasacademy.com"; // can be any email id

		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getInstance(properties, auth);
		try {

			Message message = new MimeMessage(session);
			BodyPart messageBodyPart = new MimeBodyPart();
			// Set message attributes
			message.setFrom(new InternetAddress("NoReply@aram.com"));
			// String mailList="info@aramiasacademy.com";
			String[] mailArr = emailVO.getToAddress();

			InternetAddress[] addressTo = new InternetAddress[mailArr.length];

			for (int i = 0; i < mailArr.length; i++) {
				addressTo[i] = new InternetAddress(mailArr[i]);
			}

			MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
			mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
			mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
			mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
			mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
			mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
			CommandMap.setDefaultCommandMap(mc);

			message.setRecipients(Message.RecipientType.TO, addressTo);

			message.setSubject(emailVO.getSubject());
			message.setSentDate(new Date());

			messageBodyPart.setContent(emailVO.getContent(), "text/html");
			MimeMultipart multipart = new MimeMultipart("related");
			multipart.addBodyPart(messageBodyPart);

			/*
			 * messageBodyPart = new MimeBodyPart(); DataSource fds1 = new
			 * FileDataSource("/Users/aravindhgovindaraj/Desktop/Screen.png"); //DataSource
			 * fds1 = new FileDataSource(
			 * "/Installer_Connect/apache-tomcat-7.0.70/webapps/QInstallerConnect/images/email_template_images/logoHIC.png"
			 * ); messageBodyPart.setDataHandler(new DataHandler(fds1));
			 * messageBodyPart.setHeader("Content-ID", "<logoHICImage>");
			 * multipart.addBodyPart(messageBodyPart);
			 */

			message.setContent(multipart);

			Transport.send(message);
			System.out.println("Mail Triggered Successfully");
			retVal = "Mail Triggered Successfully";

		} catch (Throwable e) {
			// TODO Auto-generated catch block

			retVal = "Mail Triggered failure";
			e.printStackTrace();
		} finally {
			baos.close();
			return retVal;
		}

	}

	public static void main(String[] args) throws Throwable {
		EmailNotice email = new EmailNotice();
		// email.sendTest("hiiiii test email y admin");
		// email.sendFeedback("aravindh.govindaraj@honeywell.com","strComments","strPN");
		// email.sendContactUSBuyer("aravindh.govindaraj@honeywell.com,apoorv.verma@honeywell.com",
		// "aravindh.govindaraj@honeywell.com", "9650899292", "Aravindh", "Report....");
		// email.sendContactUS("aravindh.govindaraj@honeywell.com",
		// "aravindh.govindaraj@honeywell.com", "9650899292", "Aravindh",
		// "Report....","","","");
	}

	private static void addAttachment(Multipart multipart, String filename) {
		DataSource source = new FileDataSource(filename);
		BodyPart messageBodyPart = new MimeBodyPart();
		try {
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("finally")
	public String sendEmail(String toAddress, String subject, String textMessage) throws Throwable {
		String retVal = "";

		Properties properties = new Properties();

		// use smtp.honeywell.com on local machine else use direct IP(10.238.252.20).

		properties.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		properties.put("mail.smtp.port", "587"); // TLS Port
		properties.put("mail.smtp.auth", "true"); // enable authentication
		properties.put("mail.smtp.starttls.enable", "true");

		final String fromEmail = AramConstants.ARAM_GMAIL; // requires valid gmail id
		final String password = AramConstants.ARAM_GMAIL_PWD; // correct password for gmail id
		// final String toEmail = "info@aramiasacademy.com"; // can be any email id

		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getInstance(properties, auth);

		System.out.println("Mail Triggered Successfully");
		retVal = "Mail Triggered Successfully";

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply@aramiasacademy.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
			message.setSubject(subject);
			message.setText(textMessage);

			// Send message
			Transport.send(message);
			System.out.println("message sent successfully....");
			retVal = "Email sent successfully";
			return retVal;

		} catch (Throwable e) {
			// TODO Auto-generated catch block

			retVal = "Mail Triggered failure";
			e.printStackTrace();
		} finally {
			return retVal;
		}
	}

}
