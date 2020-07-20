package com.imagestore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.imagestore.domain.Image;

public interface ImageRepository extends CrudRepository<Image,Long>{
	
	List<Image> findByCategory(String category);
	
	List<Image> findByNameContaining(String name);

}
