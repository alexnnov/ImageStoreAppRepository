package com.netcracker.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="image_theme")
public class ImageTheme {
    @Id
    private int image_theme_id;
    private int image;
    private int theme;
}
