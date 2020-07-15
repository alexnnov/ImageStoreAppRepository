package com.imagestore.controller.payment;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imagestore.domain.User;
import com.imagestore.service.UserService;

@Controller
public class ListOfCreditCardsController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * processes GET-method request from myProfile template for displaying the list of current session user's credit cards
	 * 
	 * @param model     the input model from the view
	 * @param principal current Spring Security user  
	 * @param request   HTTP-servlet request
	 * @return          view name to display
	 */
	@RequestMapping("/listOfCreditCards")
	public String listOfCreditCards(
			Model model, Principal principal, HttpServletRequest request
			) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());
		
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveBilling", true);
		model.addAttribute("listOfShippingAddresses", true);
		
		return "myProfile";
	}
}
