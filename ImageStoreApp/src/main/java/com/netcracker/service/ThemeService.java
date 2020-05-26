package com.netcracker.service;

import com.netcracker.entity.Theme;
import com.netcracker.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {
    @Autowired
    private ThemeRepository themeRepository;

    public void add(Theme theme){
        themeRepository.save(theme);
    }

    public void update(Theme theme){
        themeRepository.save(theme);
    }

    public void remove(int id) {
        themeRepository.deleteById(id);
    }

    public Theme find(int id){
        Optional<Theme> optional = themeRepository.findById(id);
        return optional.orElse(null);
    }

    public List<Theme> findAll(){
        return themeRepository.findAll();
    }
}
