package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Image;


public interface ImageService {
	
	Image save(Image image);
	
	List<Image> findAll();
	
	Image findById(Long id);
	

}
