package com.netcracker.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;
import java.sql.Date;

@Entity
@Data
@Table(name="orders")
public class Order {
    @Id
    private int order_id;
    private Date date;
    private Time time;
}
