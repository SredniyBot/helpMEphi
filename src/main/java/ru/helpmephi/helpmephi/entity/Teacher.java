package ru.helpmephi.helpmephi.entity;

import javax.persistence.*;
import java.util.List;

//@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String photoUrl;
    private String name;
    private String surname;
    private String patronymic;
//    private List<Review> review;
//    private List<Lesson> lessons;
//    private List<Document> documents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
