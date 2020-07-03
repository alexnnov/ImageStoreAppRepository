package com.imagestore.service;

import com.imagestore.domain.BillingAddress;
import com.imagestore.domain.Order;
import com.imagestore.domain.Payment;
import com.imagestore.domain.ShippingAddress;
import com.imagestore.domain.ShoppingCart;
import com.imagestore.domain.User;

public interface OrderService {
	Order createOrder(ShoppingCart shoppingCart,
			ShippingAddress shippingAddress,
			BillingAddress billingAddress,
			Payment payment,
			String shippingMethod,
			User user);
	
	Order findOne(Long id);
}
