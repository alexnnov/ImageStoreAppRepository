package com.imagestore.controller.payment;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imagestore.domain.BillingAddress;
import com.imagestore.domain.CartItem;
import com.imagestore.domain.Payment;
import com.imagestore.domain.ShippingAddress;
import com.imagestore.domain.User;
import com.imagestore.domain.UserBilling;
import com.imagestore.domain.UserPayment;
import com.imagestore.domain.UserShipping;
import com.imagestore.service.BillingAddressService;
import com.imagestore.service.CartItemService;
import com.imagestore.service.PaymentService;
import com.imagestore.service.UserPaymentService;
import com.imagestore.service.UserService;
import com.imagestore.utility.USConstants;

@Controller
public class SetPaymentMethodController {
	
	private ShippingAddress shippingAddress = new ShippingAddress();
	private BillingAddress billingAddress = new BillingAddress();
	private Payment payment = new Payment();
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private UserPaymentService userPaymentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private BillingAddressService billingAddressService;
	
	
	/**
	 * processes GET-method request from the checkout template.
	 * Establishes payment method for current session user.
	 * 
	 * @param userPaymentId payment Id for current user
	 * @param principal     Spring Security user
	 * @param model         the input model from the view
	 * @return              view name checkout to display
	 */
	@RequestMapping("/setPaymentMethod")
	public String setPaymentMethod(
			@RequestParam("userPaymentId") Long userPaymentId,
			Principal principal, Model model
			) {
		User user = userService.findByUsername(principal.getName());
		UserPayment userPayment = userPaymentService.findById(userPaymentId);
		UserBilling userBilling = userPayment.getUserBilling();
		
		if(userPayment.getUser().getId() != user.getId()){
			return "badRequestPage";
		} else {
			paymentService.setByUserPayment(userPayment, payment);
			
			List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
			
			billingAddressService.setByUserBilling(userBilling, billingAddress);
			
			model.addAttribute("shippingAddress", shippingAddress);
			model.addAttribute("payment", payment);
			model.addAttribute("billingAddress", billingAddress);
			model.addAttribute("cartItemList", cartItemList);
			model.addAttribute("shoppingCart", user.getShoppingCart());
			
			List<String> stateList = USConstants.listOfUSStatesCode;
			Collections.sort(stateList);
			model.addAttribute("stateList", stateList);
			
			List<UserShipping> userShippingList = user.getUserShippingList();
			List<UserPayment> userPaymentList = user.getUserPaymentList();
			
			model.addAttribute("userShippingList", userShippingList);
			model.addAttribute("userPaymentList", userPaymentList);
			
			model.addAttribute("shippingAddress", shippingAddress);
			
			model.addAttribute("classActivePayment", true);
			
			
			model.addAttribute("emptyPaymentList", false);
			
			
			if (userShippingList.size() == 0) {
				model.addAttribute("emptyShippingList", true);
			} else {
				model.addAttribute("emptyShippingList", false);
			}
			
			return "checkout";
		}
	}

}
