package com.imagestore.controller.shipping;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imagestore.domain.User;
import com.imagestore.domain.UserShipping;
import com.imagestore.service.UserService;
import com.imagestore.utility.USConstants;

@Controller
public class AddNewShippingAddressGetController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * forms template processing GET-method request from myProfile template for adding new shipping address
	 * 
	 * @param model     the input model from the view
	 * @param principal current Spring Security user  
	 * @return          view name myProfile to display
	 */
	@RequestMapping("/addNewShippingAddress")
	public String addNewShippingAddress(
			Model model, Principal principal
			){
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		
		model.addAttribute("addNewShippingAddress", true);
		model.addAttribute("classActiveShipping", true);
		model.addAttribute("listOfCreditcards", true);
		
		UserShipping userShipping = new UserShipping();
		
		model.addAttribute("userShipping", userShipping);
		
		List<String> stateList = USConstants.listOfUSStatesCode;;
		Collections.sort(stateList);
		model.addAttribute("stateList", stateList);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());
		
		return "myProfile";
	}
}
