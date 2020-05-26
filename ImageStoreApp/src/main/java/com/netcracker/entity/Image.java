package com.netcracker.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="images")
public class Image {
    @Id
    private int image_id;
    private String path;
    private int price;
}
