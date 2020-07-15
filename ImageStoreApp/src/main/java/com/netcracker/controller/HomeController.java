package com.imagestore.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.imagestore.domain.CartItem;
import com.imagestore.domain.Image;
import com.imagestore.domain.Order;
import com.imagestore.domain.User;
import com.imagestore.domain.UserBilling;
import com.imagestore.domain.UserPayment;
import com.imagestore.domain.UserShipping;
import com.imagestore.domain.security.PasswordResetToken;
import com.imagestore.domain.security.Role;
import com.imagestore.domain.security.UserRole;
import com.imagestore.service.CartItemService;
import com.imagestore.service.ImageService;
import com.imagestore.service.OrderService;
import com.imagestore.service.UserPaymentService;
import com.imagestore.service.UserService;
import com.imagestore.service.UserShippingService;
import com.imagestore.service.impl.UserSecurityService;
import com.imagestore.utility.MailConstructor;
import com.imagestore.utility.SecurityUtility;
import com.imagestore.utility.USConstants;

@Controller
public class HomeController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private UserPaymentService userPaymentService;
	
	@Autowired
	private UserShippingService userShippingService;
	
	@Autowired
	private CartItemService cartItemService;
	
	

	@RequestMapping("/")
	public String index() {
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
		
		List<String> stateList = USConstants.listOfUSStatesCode;;
		Collections.sort(stateList);
		model.addAttribute("stateList", stateList);
		model.addAttribute("classActiveEdit", true);
		
		return "myProfile";
	}
	
	

	
	
	
	
	
	
	
	
	
}

