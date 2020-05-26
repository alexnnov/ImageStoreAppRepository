package com.netcracker.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="themes")
public class Theme {
    @Id
    private int theme_id;
    private String theme;
}
