package com.imagestore.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imagestore.domain.BillingAddress;
import com.imagestore.domain.Image;
import com.imagestore.domain.CartItem;
import com.imagestore.domain.Order;
import com.imagestore.domain.Payment;
import com.imagestore.domain.ShippingAddress;
import com.imagestore.domain.ShoppingCart;
import com.imagestore.domain.User;
import com.imagestore.repository.OrderRepository;
import com.imagestore.service.CartItemService;
import com.imagestore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Override
	public synchronized Order createOrder(ShoppingCart shoppingCart,
			ShippingAddress shippingAddress,
			BillingAddress billingAddress,
			Payment payment,
			String shippingMethod,
			User user) {
		Order order = new Order();
		order.setBillingAddress(billingAddress);
		order.setOrderStatus("created");
		order.setPayment(payment);
		order.setShippingAddress(shippingAddress);
		order.setShippingMethod(shippingMethod);
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for(CartItem cartItem : cartItemList) {
			Image image = cartItem.getImage();
			cartItem.setOrder(order);
			image.setInStockNumber(image.getInStockNumber() - cartItem.getQty());
		}
		
		order.setCartItemList(cartItemList);
		order.setOrderDate(Calendar.getInstance().getTime());
		order.setOrderTotal(shoppingCart.getGrandTotal());
		shippingAddress.setOrder(order);
		billingAddress.setOrder(order);
		payment.setOrder(order);
		order.setUser(user);
		order = orderRepository.save(order);
		
		return order;
	}
	
	@Override
	public Order findOne(Long id) {
		return orderRepository.findById(id).orElse(null);
	}

}
