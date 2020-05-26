package com.netcracker.repository;

import com.netcracker.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme,Integer> {
}
