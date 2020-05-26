package com.netcracker.service;

import com.netcracker.entity.ImageTheme;
import com.netcracker.repository.ImageThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageThemeService {
    @Autowired
    private ImageThemeRepository imageThemeRepository;

    public void add(ImageTheme imageTheme){
        imageThemeRepository.save(imageTheme);
    }

    public void update(ImageTheme imageTheme){
        imageThemeRepository.save(imageTheme);
    }

    public void remove(int id) {
        imageThemeRepository.deleteById(id);
    }

    public ImageTheme find(int id){
        Optional<ImageTheme> optional = imageThemeRepository.findById(id);
        return optional.orElse(null);
    }

    public List<ImageTheme> findAll(){
        return imageThemeRepository.findAll();
    }
}
