package com.imagestore.controller.shoppingcart;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imagestore.domain.CartItem;
import com.imagestore.domain.Image;
import com.imagestore.domain.ShoppingCart;
import com.imagestore.domain.User;
import com.imagestore.service.CartItemService;
import com.imagestore.service.ImageService;
import com.imagestore.service.ShoppingCartService;
import com.imagestore.service.UserService;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ImageService imageService;
	
	/**
	 * forms shoppingCart template processing GET-method request from the header template
	 * 
	 * @param model     the input model from the view
	 * @param principal current Spring Security user
	 * @return          view name shoppingCart to display
	 */
	@RequestMapping("/cart")
	public String shoppingCart(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		shoppingCartService.updateShoppingCart(shoppingCart);
		
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("shoppingCart", shoppingCart);
		
		return "shoppingCart";
	}
	
	/**
	 * processes request from imageDetail template basing on image and quantity information to add image to shopping cart
	 * 
	 * @param image     user-chosen image to add to shopping cart
	 * @param qty       number of images in stock
	 * @param model     the input model from the view 
	 * @param principal current Spring security user
	 * @return          view imagedetail for chosen image
	 */
	@RequestMapping("/addItem")
	public String addItem(
			@ModelAttribute("image") Image image,
			@ModelAttribute("qty") String qty,
			Model model, Principal principal
			) {
		User user = userService.findByUsername(principal.getName());
		image = imageService.findById(image.getId());
		
		if (Integer.parseInt(qty) > image.getInStockNumber()) {
			model.addAttribute("notEnoughStock", true);
			return "forward:/imageDetail?id="+image.getId();
		}
		
		CartItem cartItem = cartItemService.addImageToCartItem(image, user, Integer.parseInt(qty));
		model.addAttribute("addImageSuccess", true);
		
		return "forward:/imageDetail?id="+image.getId();
	}
	
	/**
	 * processes request from shoppingCart template basing on cart item and quantity information to update information
	 *  
	 * @param cartItemId user-chosen cart item Id to update
	 * @param qty        number of selected items in cart
	 * @return           view name shoppingCart for updated user's cart
	 */
	@RequestMapping("/updateCartItem")
	public String updateShoppingCart(
			@ModelAttribute("id") Long cartItemId,
			@ModelAttribute("qty") int qty
			) {
		CartItem cartItem = cartItemService.findById(cartItemId);
		cartItem.setQty(qty);
		cartItemService.updateCartItem(cartItem);
		
		return "forward:/shoppingCart/cart";
	}
	
	/**
	 * processes request from shoppingCart template to delete item in shopping cart
	 *  
	 * @param id  user-chosen cart item Id to delete
	 * @return    view name shoppingCart for updated user's cart
	 */
	@RequestMapping("/removeItem")
	public String removeItem(@RequestParam("id") Long id) {
		cartItemService.removeCartItem(cartItemService.findById(id));
		
		return "forward:/shoppingCart/cart";
	}
	


}
