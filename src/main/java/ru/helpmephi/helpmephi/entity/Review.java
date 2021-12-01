package ru.helpmephi.helpmephi.entity;

import javax.persistence.*;
import java.sql.Date;

//@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String message;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
