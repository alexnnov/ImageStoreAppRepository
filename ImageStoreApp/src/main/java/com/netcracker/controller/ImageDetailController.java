package com.imagestore.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imagestore.domain.Image;
import com.imagestore.domain.User;
import com.imagestore.service.ImageService;
import com.imagestore.service.UserService;

@Controller
public class ImageDetailController {
	
	@Autowired
	private UserService userService;	
	@Autowired
	private ImageService imageService;
	
	
	@RequestMapping("/imageDetail")
	public String imageDetail(
			@PathParam("id") Long id, Model model, Principal principal
			) {
		if(principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		Image image = imageService.findById(id);
		
		model.addAttribute("image", image);
		
		List<Integer> qtyList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		model.addAttribute("qtyList", qtyList);
		model.addAttribute("qty", 1);
		
		return "imageDetail";
	}

}
