package ru.helpmephi.helpmephi.entity.doc;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import ru.helpmephi.helpmephi.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//    TODO add lesson add type (lab,lect...) add teachers

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Это поле не может быть пустым")
    @Length(min = 3,max = 40)
    private String name;

    @NotBlank(message = "Это поле не может быть пустым")
    @Length(min =3,max = 255,message = "Комментарий слишком длинный, максимальная длинна 255 символов")
    private String comment;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @CreationTimestamp
    private LocalDateTime date;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "content_id")
    private List<Content> content;

    public Document() {
        initContent();
    }

    public Document(String name, String comment,User user){
        initContent();
        this.name=name;
        this.comment=comment;
        this.author =user;
    }

    public void addContent(Content content){
        this.content.add(content);
    }

    public void removeContent(Content content){
        this.content.removeIf(cont->cont.getId().equals(content.getId()));
    }

    private void initContent(){
        if(content==null)content=new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User autor) {
        this.author = autor;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

}
