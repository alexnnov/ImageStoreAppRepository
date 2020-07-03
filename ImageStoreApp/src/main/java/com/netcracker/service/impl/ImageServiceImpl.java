package com.imagestore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imagestore.domain.Image;
import com.imagestore.repository.ImageRepository;
import com.imagestore.service.ImageService;

@Service
public class ImageServiceImpl  implements ImageService{
	@Autowired
	private ImageRepository imageRepository;
	
	public List<Image> findAll(){
		List<Image> imageList = (List<Image>) imageRepository.findAll();
		List<Image> activeImageList = new ArrayList<>();
		
		for (Image image: imageList) {
			if(image.isActive()) {
				activeImageList.add(image);
			}
		}
		
		return activeImageList;
	}
	
	public Image findById(Long id) {
		return imageRepository.findById(id).orElse(null);
	
	}
	
	public List<Image> findByCategory(String category){
		List<Image> imageList = imageRepository.findByCategory(category);
		
		List<Image> activeImageList = new ArrayList<>();
		
		for (Image image: imageList) {
			if(image.isActive()) {
				activeImageList.add(image);
			}
		}
		
		return activeImageList;
	}
	
	public List<Image> blurrySearch(String name) {
		List<Image> imageList = imageRepository.findByNameContaining(name);
		List<Image> activeImageList = new ArrayList<>();
		
		for (Image image: imageList) {
			if(image.isActive()) {
				activeImageList.add(image);
			}
		}
		
		return activeImageList;
	}

}
