package ru.helpmephi.helpmephi.entity.doc;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;


@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CollectionTable(name = "content_type",joinColumns = @JoinColumn(name = "content_id"))
    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @Transient
    private MultipartFile file;

    private String comment;

    private String fileName;

    @ManyToOne
    private Document document;

    public Content(){

    }

    public Content(String fileName,String comment){
        this.fileName=fileName;
        contentType=ContentType.getTypeByFilename(fileName);
        this.comment=comment;
    }

    public String getImagePath(){
        if(isImage())   return getFileName();
        return getContentType().getPrototypeImagePath();
    }

    public boolean isImage(){
        return contentType==ContentType.IMAGE;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
