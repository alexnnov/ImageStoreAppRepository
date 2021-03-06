package com.imagestore.service;


import java.util.List;

import com.imagestore.domain.Image;

public interface ImageService {
	List<Image> findAll();
	
	Image findById(Long id);
	
	List<Image> findByCategory(String categoty);
	
	List<Image> blurrySearch(String name);

}
