package com.imagestore.controller.shipping;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imagestore.domain.User;
import com.imagestore.domain.UserShipping;
import com.imagestore.service.ImageService;
import com.imagestore.service.OrderService;
import com.imagestore.service.UserPaymentService;
import com.imagestore.service.UserService;
import com.imagestore.service.UserShippingService;
import com.imagestore.service.impl.UserSecurityService;
import com.imagestore.utility.USConstants;

@Controller
public class UpdateUserShippingController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserShippingService userShippingService;
	
	/**
	 * forms template processing GET-method request from myProfile template for updating user-chosen shipping address
	 * 
	 * @param creditCardId user-chosen shipping address id for updating
	 * @param principal    current Spring Security user  
	 * @param model        the input model from the view
	 * @return             view name myProfile to display
	 */
	@RequestMapping("/updateUserShipping")
	public String updateUserShipping(
			@ModelAttribute("id") Long shippingAddressId, Principal principal, Model model
			) {
		User user = userService.findByUsername(principal.getName());
		UserShipping userShipping = userShippingService.findById(shippingAddressId);
		
		if(user.getId() != userShipping.getUser().getId()) {
			return "badRequestPage";
		} else {
			model.addAttribute("user", user);
			
			model.addAttribute("userShipping", userShipping);
			
			List<String> stateList = USConstants.listOfUSStatesCode;;
			Collections.sort(stateList);
			model.addAttribute("stateList", stateList);
			
			model.addAttribute("addNewShippingAddress", true);
			model.addAttribute("classActiveShipping", true);
			model.addAttribute("listOfCreditCards", true);
			
			model.addAttribute("userPaymentList", user.getUserPaymentList());
			model.addAttribute("userShippingList", user.getUserShippingList());
			model.addAttribute("orderList", user.getOrderList());
			
			return "myProfile";
		}
	}

}
