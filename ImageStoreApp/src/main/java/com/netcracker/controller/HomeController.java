package com.imagestore.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imagestore.domain.User;
import com.imagestore.domain.UserShipping;
import com.imagestore.service.UserService;
import com.imagestore.utility.USConstants;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String index() {
		logger.debug("Calling index method with no arguments");
		return "index";
	}
	
	@RequestMapping("/coordinates")
	public String coordinates() {
		return "coordinates";
	}
	
	@RequestMapping("/faq")
	public String faq() {
		return "faq";
	}

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("classActiveLogin", true);
		return "myAccount";
	}
	
	/**
	 * fills model with user information stored in database
	 * 
	 * @param model     the input model from the view
	 * @param principal current Spring Security user  
	 * @return          view name myProfile to display
	 */
	@RequestMapping("/myProfile")
	    public String myProfile(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());
		
		UserShipping userShipping = new UserShipping();
		model.addAttribute("userShipping", userShipping);
		
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("listOfShippingAddresses", true);
		
		List<String> stateList = USConstants.LIST_OF_US_STATES_CODE;
		Collections.sort(stateList);
		model.addAttribute("stateList", stateList);
		model.addAttribute("classActiveEdit", true);
		
		return "myProfile";
	}
	
	

	
	
	
	
	
	
	
	
	
}

