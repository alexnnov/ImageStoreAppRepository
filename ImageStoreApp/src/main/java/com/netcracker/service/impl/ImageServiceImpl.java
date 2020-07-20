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
	
	@Override
	public List<Image> findAll(){
		return (List<Image>) imageRepository.findAll();
	}
	
	@Override
	public Image findById(Long id) {
		return imageRepository.findById(id).orElse(null);
	
	}
	
	@Override
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
	
	@Override
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
