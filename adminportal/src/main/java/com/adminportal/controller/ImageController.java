package com.adminportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.Image;
import com.adminportal.service.ImageService;

@Controller
@RequestMapping("/image")
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addImage(Model model) {
		Image image = new Image();
		model.addAttribute("image", image);
		return "addImage";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addImagePost(@ModelAttribute("image") Image image, HttpServletRequest request) {
		imageService.save(image);

		MultipartFile imageImage = image.getImageImage();

		try {
			byte[] bytes = imageImage.getBytes();
			String name = image.getId() + ".png";
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/image/" + name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:imageList";
	}
	
	@RequestMapping("/imageList")
	public String imageList(Model model) {
		List<Image> imageList = imageService.findAll();
		model.addAttribute("imageList",imageList);
		
		
		return "imageList";
		
	}

}
