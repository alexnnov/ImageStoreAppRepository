package com.imagestore.controller.payment;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imagestore.domain.User;
import com.imagestore.domain.UserPayment;
import com.imagestore.exceptions.UserNotFoundException;
import com.imagestore.service.UserPaymentService;
import com.imagestore.service.UserService;

@Controller
public class RemoveCreditCardController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserPaymentService userPaymentService;
	
	/**
	 * processes GET-method request from myProfile template for deleting chosen by
	 * user credit card
	 * 
	 * @param creditCardId user-chosen credit card Id for deleting
	 * @param principal    current Spring Security user
	 * @param model        the input model from the view
	 * @return view name myProfile to display
	 */
	@RequestMapping("/removeCreditCard")
	public String removeCreditCard(@ModelAttribute("id") Long creditCardId, Principal principal, Model model) throws UserNotFoundException {
		User user = userService.findByUsername(principal.getName());
		UserPayment userPayment = userPaymentService.findById(creditCardId);
			if (user.getId().equals( userPayment.getUser().getId())) {
			model.addAttribute("user", user);
			userPaymentService.removeById(creditCardId);

			model.addAttribute("listOfCreditCards", true);
			model.addAttribute("classActiveBilling", true);
			model.addAttribute("listOfShippingAddresses", true);

			model.addAttribute("userPaymentList", user.getUserPaymentList());
			model.addAttribute("userShippingList", user.getUserShippingList());
			model.addAttribute("orderList", user.getOrderList());

			return "myProfile";
		} else {
			throw new UserNotFoundException();
		}
	}

}
