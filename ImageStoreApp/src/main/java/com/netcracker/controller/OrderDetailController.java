package com.imagestore.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imagestore.domain.CartItem;
import com.imagestore.domain.Order;
import com.imagestore.domain.User;
import com.imagestore.domain.UserShipping;
import com.imagestore.service.CartItemService;
import com.imagestore.service.OrderService;
import com.imagestore.service.UserService;
import com.imagestore.service.UserShippingService;
import com.imagestore.utility.USConstants;



@Controller
public class OrderDetailController {
	@Autowired
	private UserService userService;
	@Autowired
	private CartItemService cartItemService;
	
	
	
	@Autowired
	private OrderService orderService;
	@RequestMapping("/orderDetail")
	public String orderDetail(
			@RequestParam("id") Long orderId,
			Principal principal, Model model
			){
		User user = userService.findByUsername(principal.getName());
		Order order = orderService.findOne(orderId);
		
		if(order.getUser().getId()!=user.getId()) {
			return "badRequestPage";
		} else {
			List<CartItem> cartItemList = cartItemService.findByOrder(order);
			model.addAttribute("cartItemList", cartItemList);
			model.addAttribute("user", user);
			model.addAttribute("order", order);
			
			model.addAttribute("userPaymentList", user.getUserPaymentList());
			model.addAttribute("userShippingList", user.getUserShippingList());
			model.addAttribute("orderList", user.getOrderList());
			
			UserShipping userShipping = new UserShipping();
			model.addAttribute("userShipping", userShipping);
			
			List<String> stateList = USConstants.listOfUSStatesCode;
			Collections.sort(stateList);
			model.addAttribute("stateList", stateList);
			
			model.addAttribute("listOfShippingAddresses", true);
			model.addAttribute("classActiveOrders", true);
			model.addAttribute("listOfCreditCards", true);
			model.addAttribute("displayOrderDetail", true);
			
			return "myProfile";
		}
	}

}
