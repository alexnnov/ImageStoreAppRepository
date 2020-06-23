package com.imagestore.repository;

import org.springframework.data.repository.CrudRepository;

import com.imagestore.domain.Image;

public interface ImageRepository extends CrudRepository<Image,Long>{

}
