package com.imagestore;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.imagestore.domain.User;
import com.imagestore.domain.security.Role;
import com.imagestore.domain.security.UserRole;
import com.imagestore.service.UserService;
import com.imagestore.utility.SecurityUtility;

@SpringBootApplication
public class ImagestoreApplication implements CommandLineRunner{
	private static final Logger logger = LoggerFactory.getLogger(ImagestoreApplication.class);
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) throws InterruptedException {
		logger.info("Launching ImageStore Application...");
		
		SpringApplication.run(ImagestoreApplication.class, args);
	}
	
	@Override
	public void run(String ... args) throws Exception{
		String encodedPassword = SecurityUtility.passwordEncoder().encode("I");
		
		User user1 = new User();
		user1.setFirstName("Ivan");
		user1.setLastName("Ivanov");
		user1.setUsername("I");
		user1.setPassword(encodedPassword);
		user1.setEmail("IIvanov@gmail.com");
		
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user1, Role.REGULAR_USER_ROLE));
		
		userService.createUser(user1, userRoles);
	}

}
