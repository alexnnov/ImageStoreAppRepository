package com.imagestore.utility;

import java.util.Locale;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.imagestore.domain.Order;
import com.imagestore.domain.User;

@Component
public class MailConstructor {
	@Autowired
	private Environment env;
	
	@Autowired 
	private TemplateEngine templateEngine;
	
	private static final String DEFAULT_SUBJECT = "Imagestore - New User";
	
	
	public SimpleMailMessage constructResetTokenEmail(
			String contextPath, Locale locale, String token, User user, String password
			) {
		String supportEmail = env.getProperty("support.email");
		
		String emailPayload = buildEmailPayload(contextPath, token, password);
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject(DEFAULT_SUBJECT);
		email.setText(emailPayload);
		email.setFrom(supportEmail);
		return email;
		
	}
	
	private String buildEmailPayload(String contextPath, String token, String password) {
		StringBuilder emailPayloadBuilder = new StringBuilder();
		emailPayloadBuilder.append(contextPath);
		emailPayloadBuilder.append("/newuser?token=");
		emailPayloadBuilder.append(token);
		emailPayloadBuilder.append("\nPlease click here to verify your email and edit your personal information. Your password is: \n ");
		emailPayloadBuilder.append(password);
		return emailPayloadBuilder.toString();
	}
	
	
	public MimeMessagePreparator constructOrderConfirmationEmail (User user, Order order, Locale locale) {
		Context context = new Context();
		context.setVariable("order", order);
		context.setVariable("user", user);
		context.setVariable("cartItemList", order.getCartItemList());
		String text = templateEngine.process("orderConfirmationEmailTemplate", context);
		
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(user.getEmail());
				email.setSubject("Order Confirmation - "+order.getId());
				email.setText(text, true);
				email.setFrom(new InternetAddress("alexeygudin@gmail.com"));
				
			}
		};
		
		return messagePreparator;
	}
	
	
	
	
}
