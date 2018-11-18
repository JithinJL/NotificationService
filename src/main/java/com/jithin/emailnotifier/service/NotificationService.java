package com.jithin.emailnotifier.service;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class NotificationService {

	private JavaMailSender mailSender;
	
	private TemplateEngine templateEngine;
	
	
	@Autowired
	public NotificationService(JavaMailSender mailSender, TemplateEngine templateEngine) {
		this.mailSender = mailSender;
		this.templateEngine = templateEngine;
	}

	/*@Autowired
	public NotificationService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Autowired
	public NotificationService(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}*/

	public void sendNotification() throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("");
		mail.setFrom("");
		mail.setSubject("Test");
		mail.setText("Hiiii");
		mailSender.send(mail);
	}

	public void sendNotificationTemplate() throws MailException, MessagingException {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		Context context = new Context();
		context.setVariable("signature", "");
		context.setVariable("location", "CA");
		context.setVariable("orderId", "#123456");
		
		//context.setVariables(mail.getModel());
		String html = templateEngine.process("email-template", context);
		helper.setTo("");
		helper.setText(html, true);
		helper.setSubject("order updation -no reply");
		helper.setFrom("");
		mailSender.send(message);
	}

}
