package com.imagestore;

import java.util.HashSet;
import java.util.Set;

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
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ImagestoreApplication.class, args);
	}
	
	@Override
	public void run(String ... args) throws Exception{
		User user1 = new User();
		user1.setFirstName("Ivan");
		user1.setLastName("Ivanov");
		user1.setUsername("I");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("I"));
		user1.setEmail("IIvanov@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1= new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
	}

}
