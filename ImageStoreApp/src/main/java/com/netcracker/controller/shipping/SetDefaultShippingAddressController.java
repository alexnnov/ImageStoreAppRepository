package com.imagestore.controller.shipping;

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
public class SetDefaultShippingAddressController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * processes request from myProfile template to mark saved shipping address as default
	 * 
	 * @param defaultShippingId Id of shipping address for setting default payment
	 * @param principal         current Spring Security user
	 * @param model             the input model from the view
	 * @return view name myProfile to display
	 */
	@RequestMapping(value = "/setDefaultShippingAddress", method = RequestMethod.POST)
	public String setDefaultShippingAddress(@ModelAttribute("defaultShippingAddressId") Long defaultShippingId,
			Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());
		userService.setUserDefaultShipping(defaultShippingId, user);

		model.addAttribute("user", user);
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveShipping", true);
		model.addAttribute("listOfShippingAddresses", true);

		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());

		return "myProfile";
	}

}
