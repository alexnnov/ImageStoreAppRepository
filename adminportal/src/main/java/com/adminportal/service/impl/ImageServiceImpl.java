package com.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Image;
import com.adminportal.repository.ImageRepository;
import com.adminportal.service.ImageService;


@Service
public class ImageServiceImpl implements ImageService{
	
	@Autowired
	private ImageRepository imageRepository;
	
	public Image save(Image image) {
		return imageRepository.save(image);
	}
	
	public List<Image> findAll() {
		return (List<Image>) imageRepository.findAll();
	}

}
