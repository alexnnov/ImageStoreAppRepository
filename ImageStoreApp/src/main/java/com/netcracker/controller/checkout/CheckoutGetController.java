package com.imagestore.controller.checkout;

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
import com.imagestore.exceptions.CartNotFoundException;
import com.imagestore.service.BillingAddressService;
import com.imagestore.service.CartItemService;
import com.imagestore.service.PaymentService;
import com.imagestore.service.ShippingAddressService;
import com.imagestore.service.UserService;
import com.imagestore.utility.USConstants;

@Controller
public class CheckoutGetController {

	private final ShippingAddress shippingAddress = new ShippingAddress();
	private final BillingAddress billingAddress = new BillingAddress();
	private final Payment payment = new Payment();

	@Autowired
	private UserService userService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private ShippingAddressService shippingAddressService;

	@Autowired
	private BillingAddressService billingAddressService;

	@Autowired
	private PaymentService paymentService;

	/**
	 * forms checkout template basing on session's cart, address and billing
	 * information
	 * 
	 * @param cartId               current session user's cart ID
	 * @param missingRequiredField flag showing that some of form's mandatory
	 *                             attributes are empty
	 * @param model                the input model from the view
	 * @param principal            current Spring Security user
	 * @return view name checkout to display
	 */
	@RequestMapping("/checkout")
	public String checkout(@RequestParam("id") Long cartId,
			@RequestParam(value = "missingRequiredField", required = false) boolean missingRequiredField, Model model,
			Principal principal) throws CartNotFoundException {
		User user = userService.findByUsername(principal.getName());

		Long shoppingCartId = user.getShoppingCart().getId();

		if (cartId != null && shoppingCartId != null && !cartId.equals(shoppingCartId)) {
			throw new CartNotFoundException();
		}

		List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

		if (cartItemList.isEmpty()) {
			model.addAttribute("emptyCart", true);
			return "forward:/shoppintCart/cart";
		}

		for (CartItem cartItem : cartItemList) {
			if (cartItem.getImage().getInStockNumber() < cartItem.getQty()) {
				model.addAttribute("notEnoughStock", true);
				return "forward:/shoppingCart/cart";
			}
		}

		List<UserShipping> userShippingList = user.getUserShippingList();
		List<UserPayment> userPaymentList = user.getUserPaymentList();

		model.addAttribute("userShippingList", userShippingList);
		model.addAttribute("userPaymentList", userPaymentList);

		if (userPaymentList.isEmpty()) {
			model.addAttribute("emptyPaymentList", true);
		} else {
			model.addAttribute("emptyPaymentList", false);
		}

		if (userShippingList.size() == 0) {
			model.addAttribute("emptyShippingList", true);
		} else {
			model.addAttribute("emptyShippingList", false);
		}

		for (UserShipping userShipping : userShippingList) {
			if (userShipping.isUserShippingDefault()) {
				shippingAddressService.setByUserShipping(userShipping, shippingAddress);
			}
		}

		for (UserPayment userPayment : userPaymentList) {
			if (userPayment.isDefaultPayment()) {
				paymentService.setByUserPayment(userPayment, payment);
				billingAddressService.setByUserBilling(userPayment.getUserBilling(), billingAddress);
			}
		}

		model.addAttribute("shippingAddress", shippingAddress);
		model.addAttribute("payment", payment);
		model.addAttribute("billingAddress", billingAddress);
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("shoppingCart", user.getShoppingCart());

		final List<String> stateList = USConstants.LIST_OF_US_STATES_CODE;
		Collections.sort(stateList);
		model.addAttribute("stateList", stateList);

		model.addAttribute("classActiveShipping", true);

		if (missingRequiredField) {
			model.addAttribute("missingRequiredField", true);
		}

		return "checkout";
	}
}
