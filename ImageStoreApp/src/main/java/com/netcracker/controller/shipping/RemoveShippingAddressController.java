package com.imagestore.controller.shipping;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imagestore.domain.User;
import com.imagestore.domain.UserShipping;
import com.imagestore.exceptions.UserNotFoundException;
import com.imagestore.service.UserService;
import com.imagestore.service.UserShippingService;

@Service
public class RemoveShippingAddressController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserShippingService userShippingService;

	/**
	 * processes GET-method request from myProfile template for deleting user saved shipping address
	 * 
	 * @param creditCardId user-chosen shipping address Id for deleting
	 * @param principal    current Spring Security user
	 * @param model        the input model from the view
	 * @return view name myProfile to display
	 */
	@RequestMapping("/removeUserShipping")
	public String removeUserShipping(@ModelAttribute("id") Long userShippingId, Principal principal, Model model) throws UserNotFoundException{
		User user = userService.findByUsername(principal.getName());
		UserShipping userShipping = userShippingService.findById(userShippingId);

		if (user.getId().equals(userShipping.getUser().getId())) {
			model.addAttribute("user", user);

			userShippingService.removeById(userShippingId);

			model.addAttribute("listOfShippingAddresses", true);
			model.addAttribute("classActiveShipping", true);

			model.addAttribute("userPaymentList", user.getUserPaymentList());
			model.addAttribute("userShippingList", user.getUserShippingList());
			model.addAttribute("orderList", user.getOrderList());

			return "myProfile";
		} else {
			throw new UserNotFoundException();
		}
	}

}
