/**
 * 
 */
package com.jithin.emailnotifier.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jithin.emailnotifier.service.NotificationService;

/**
 * @author Jithin
 *
 */
@RestController
public class NotificationController {
	
	private Logger logger= LoggerFactory.getLogger(NotificationController.class);

	@Autowired
	NotificationService notificationService;

	@RequestMapping("/test")
	public String test() {
		return "Hey it works";

	}

	@RequestMapping("/send-mail")
	public String sendMail() {

		try {
			notificationService.sendNotificationTemplate();
		} catch (Exception ex) {
			logger.info(ex.getMessage());
		}

		return "mailed";
	}

}
