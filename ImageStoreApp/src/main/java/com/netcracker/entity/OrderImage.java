package com.netcracker.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="order_image")
public class OrderImage {
    @Id
    private int order_image_id;
    private int order;
    private int image;

}
