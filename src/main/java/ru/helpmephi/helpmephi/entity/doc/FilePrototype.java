package ru.helpmephi.helpmephi.entity.doc;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class FilePrototype {

    private MultipartFile multipartFile;

    private String comment;

    public FilePrototype(MultipartFile multipartFile, String comment) {
        this.multipartFile = multipartFile;
        this.comment = comment;
    }

    public boolean isValid(){
        if(multipartFile==null)return false;
        if(multipartFile.isEmpty())return false;
        return true;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment=comment;
    }
}
