package ru.helpmephi.helpmephi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.List;


//@Entity
//@Table(name="lessons")
public class Lesson {

    @Id
    @JoinColumn(name = "name")
    private String name;
//    private Lesson parentLesson;
//    private List<Teacher> teachers;
//    private List<Integer> curses;


}
