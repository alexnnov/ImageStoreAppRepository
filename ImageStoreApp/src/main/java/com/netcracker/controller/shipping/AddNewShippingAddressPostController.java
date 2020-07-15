package com.imagestore.controller.shipping;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imagestore.domain.User;
import com.imagestore.domain.UserShipping;
import com.imagestore.service.UserService;

@Controller
public class AddNewShippingAddressPostController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * processes request from the myProfile template to add client's new shipping address
	 *  	
	 * @param userShipping user-filled shipping information from template
	 * @param principal    current Spring Security user
	 * @param model        the input model from the view
	 * @return             view name myProfie to display
	 */
	@RequestMapping(value="/addNewShippingAddress", method=RequestMethod.POST)
	public String addNewShippingAddressPost(
			@ModelAttribute("userShipping") UserShipping userShipping,
			Principal principal, Model model
			){
		User user = userService.findByUsername(principal.getName());
		userService.updateUserShipping(userShipping, user);
		
		model.addAttribute("user", user);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("listOfShippingAddresses", true);
		model.addAttribute("classActiveShipping", true);
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("orderList", user.getOrderList());
		
		return "myProfile";
	}

}
