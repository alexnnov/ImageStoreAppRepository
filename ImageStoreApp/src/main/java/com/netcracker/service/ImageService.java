package com.netcracker.service;

import com.netcracker.entity.Image;
import com.netcracker.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public void add(Image image){
        imageRepository.save(image);
    }

    public void update(Image image){
        imageRepository.save(image);
    }

    public void remove(int id) {
        imageRepository.deleteById(id);
    }

    public Image find(int id){
        Optional<Image> optional = imageRepository.findById(id);
        return optional.orElse(null);
    }

    public List<Image> findAll(){
        return imageRepository.findAll();
    }
}
