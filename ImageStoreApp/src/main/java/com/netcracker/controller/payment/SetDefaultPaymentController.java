package com.imagestore.controller.payment;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imagestore.domain.User;
import com.imagestore.service.UserService;

@Controller
public class SetDefaultPaymentController {
	
	@Autowired
	private UserService userService;

	/**
	 * processes POST-method request from myProfile template for setting default
	 * credit card.
	 * 
	 * @param defaultPaymentId Id of credit card for setting default payment
	 * @param principal        current Spring Security user
	 * @param model            the input model from the view
	 * @return view name myProfile to display
	 */

	@RequestMapping(value = "/setDefaultPayment", method = RequestMethod.POST)
	public String setDefaultPayment(@ModelAttribute("defaultUserPaymentId") Long defaultPaymentId, Principal principal,
			Model model) {
		User user = userService.findByUsername(principal.getName());
		userService.setUserDefaultPayment(defaultPaymentId, user);

		model.addAttribute("user", user);
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveBilling", true);
		model.addAttribute("listOfShippingAddresses", true);

		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());

		return "myProfile";
	}
}
