package com.imagestore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imagestore.domain.Image;
import com.imagestore.domain.User;
import com.imagestore.service.ImageService;
import com.imagestore.service.UserService;

@Controller
public class SearchController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ImageService imageService;

	@RequestMapping("/searchByCategory")
	public String searchByCategory(
			@RequestParam("category") String category,
			Model model, Principal principal
			){
		if(principal!=null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		String classActiveCategory = "active"+category;
		classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
		classActiveCategory = classActiveCategory.replaceAll("&", "");
		model.addAttribute(classActiveCategory, true);
		
		List<Image> imageList = imageService.findByCategory(category);
		
		if (imageList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "imageboard";
		}
		
		model.addAttribute("imageList", imageList);
		
		return "imageboard";
	}
	
	@RequestMapping("/searchImage")
	public String searchImage(
			@ModelAttribute("keyword") String keyword,
			Principal principal, Model model
			) {
		if(principal!=null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		List<Image> imageList = imageService.blurrySearch(keyword);
		
		if (imageList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "imageboard";
		}
		
		model.addAttribute("imageList", imageList);
		
		return "imageboard";
	}
}
