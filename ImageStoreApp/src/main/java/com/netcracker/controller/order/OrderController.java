package com.imagestore.controller.order;

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
import com.imagestore.exceptions.UserNotFoundException;
import com.imagestore.service.CartItemService;
import com.imagestore.service.OrderService;
import com.imagestore.service.UserService;
import com.imagestore.utility.USConstants;


@Controller
public class OrderController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CartItemService cartItemService;
	
	/**
	 * processes request from myProfile template to display order details
	 * 
	 * @param orderId   user-chosen order's Id
	 * @param principal current Spring Security user
	 * @param model     the input model from the view
	 * @return          view name myProfile to display
	 */
	@RequestMapping("/orderDetail")
	public String orderDetail(@RequestParam("id") Long orderId, Principal principal, Model model) throws UserNotFoundException{
		User user = userService.findByUsername(principal.getName());
		Order order = orderService.findOne(orderId);

		User orderUser = order.getUser();
		
		if (orderUser == null) {
			throw new UserNotFoundException();
		}
		
		if (user == null) {
			throw new UserNotFoundException();
		}
		
		Long orderUserId = orderUser.getId();
		Long userId = user.getId();
		
		if (orderUserId != null && userId != null && orderUserId.equals(userId)) {
			List<CartItem> cartItemList = cartItemService.findByOrder(order);
			model.addAttribute("cartItemList", cartItemList);
			model.addAttribute("user", user);
			model.addAttribute("order", order);

			model.addAttribute("userPaymentList", user.getUserPaymentList());
			model.addAttribute("userShippingList", user.getUserShippingList());
			model.addAttribute("orderList", user.getOrderList());

			UserShipping userShipping = new UserShipping();
			model.addAttribute("userShipping", userShipping);

			List<String> stateList = USConstants.LIST_OF_US_STATES_CODE;
			Collections.sort(stateList);
			model.addAttribute("stateList", stateList);

			model.addAttribute("listOfShippingAddresses", true);
			model.addAttribute("classActiveOrders", true);
			model.addAttribute("listOfCreditCards", true);
			model.addAttribute("displayOrderDetail", true);

			return "myProfile";
		} else {
			throw new UserNotFoundException();
		}
	}

}
