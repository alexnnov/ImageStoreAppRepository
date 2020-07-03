package com.imagestore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imagestore.domain.Image;
import com.imagestore.domain.User;
import com.imagestore.service.ImageService;
import com.imagestore.service.UserService;


@Controller
public class ImageBoardController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ImageService imageService;
	
	
	@RequestMapping("/imageboard")
	public String imageBoard(Model model, Principal principal) {
		if(principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		List<Image> imageList = imageService.findAll();
		model.addAttribute("imageList", imageList);
		model.addAttribute("activeAll",true);
		
		return "imageboard";
	}

}
