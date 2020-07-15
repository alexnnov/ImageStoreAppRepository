package com.imagestore.controller.shipping;

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
import com.imagestore.domain.UserPayment;
import com.imagestore.domain.UserShipping;
import com.imagestore.service.CartItemService;
import com.imagestore.service.ShippingAddressService;
import com.imagestore.service.UserService;
import com.imagestore.service.UserShippingService;
import com.imagestore.utility.USConstants;

@Controller
public class SetShippingAddressController {
	
	private ShippingAddress shippingAddress = new ShippingAddress();
	private BillingAddress billingAddress = new BillingAddress();
	private Payment payment = new Payment();
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserShippingService userShippingService;
		
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShippingAddressService shippingAddressService;
	/**
	 * processes GET-method request from checkout template.
	 * Establishes shipping address for current session user.
	 * 
	 * @param userShippingId shipping Id for current user
	 * @param principal      Spring Security user
	 * @param model          the input model from the view
	 * @return               view name checkout to display
	 */
	@RequestMapping("/setShippingAddress")
	public String setShippingAddress(
			@RequestParam("userShippingId") Long userShippingId,
			Principal principal, Model model
			) {
		User user = userService.findByUsername(principal.getName());
		UserShipping userShipping = userShippingService.findById(userShippingId);
		
		if(userShipping.getUser().getId() != user.getId()) {
			return "badRequestPage";
		} else {
			shippingAddressService.setByUserShipping(userShipping, shippingAddress);
			
			List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
			
			
			
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
			
			model.addAttribute("classActiveShipping", true);
			
			if (userPaymentList.size() == 0) {
				model.addAttribute("emptyPaymentList", true);
			} else {
				model.addAttribute("emptyPaymentList", false);
			}
			
			
			model.addAttribute("emptyShippingList", false);
			
			
			return "checkout";
		}
	}
	

}
