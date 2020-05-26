package com.netcracker.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Data
@Table(name = "clients")
public class Client {
    @Id
    private int client_id;
    private String name;
    private String lastname;
    private String email;
}
