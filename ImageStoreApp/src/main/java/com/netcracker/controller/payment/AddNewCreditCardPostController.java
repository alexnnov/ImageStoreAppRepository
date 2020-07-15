package com.imagestore.controller.payment;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imagestore.domain.User;
import com.imagestore.domain.UserBilling;
import com.imagestore.domain.UserPayment;
import com.imagestore.service.UserService;

public class AddNewCreditCardPostController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * processes request from myProfile template for adding new credit card based on user payment and billing information
	 * 
	 * @param userPayment user-filled payment information from template
	 * @param userBilling current session user's billing 
	 * @param principal   current Spring Security user  
	 * @param model       the input model from the view
	 * @return            view name myProfile to display
	 */
	
	@RequestMapping(value="/addNewCreditCard", method=RequestMethod.POST)
	public String addNewCreditCard(
			@ModelAttribute("userPayment") UserPayment userPayment,
			@ModelAttribute("userBilling") UserBilling userBilling,
			Principal principal, Model model
			){
		User user = userService.findByUsername(principal.getName());
		userService.updateUserBilling(userBilling, userPayment, user);
		
		model.addAttribute("user", user);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userBillingList", user.getUserShippingList());
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveBilling", true);
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("orderList", user.getOrderList());
		
		return "myProfile";
	}

}
