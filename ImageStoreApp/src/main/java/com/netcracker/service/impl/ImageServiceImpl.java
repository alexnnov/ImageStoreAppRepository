package com.imagestore.service.impl;

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
		return (List<Image>) imageRepository.findAll();
	}
	
	public Image findById(Long id) {
		return imageRepository.findById(id).orElse(null);
	
	}

}
