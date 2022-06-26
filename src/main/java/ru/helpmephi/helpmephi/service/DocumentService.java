package ru.helpmephi.helpmephi.service;

import org.springframework.stereotype.Service;
import ru.helpmephi.helpmephi.entity.User;
import ru.helpmephi.helpmephi.entity.doc.Content;
import ru.helpmephi.helpmephi.entity.doc.Document;
import ru.helpmephi.helpmephi.entity.doc.FilePrototype;
import ru.helpmephi.helpmephi.repos.DocumentRepo;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final DocumentRepo documentRepo;
    private final ContentService contentService;

    public DocumentService(DocumentRepo documentRepo, ContentService contentService) {
        this.documentRepo = documentRepo;
        this.contentService = contentService;
    }

    public List<Document> findAll() {
        return (List<Document>) documentRepo.findAll();
    }

    public void save(User user, List<Content> contents, Document document) {
        document.setAuthor(user);
        addContent(document,contents);
        documentRepo.save(document);
    }


    public void saveWithFilePrototypes(User user, List<FilePrototype> files, Document document) {
        document.setAuthor(user);
        addPrototypes(document,files);
        documentRepo.save(document);
    }

    public void edit(Document document, List<Content> files) {
        if(documentRepo.existsById(document.getId())){
            addContent(document,files);
            documentRepo.save(document);
        }
    }

    public void setDeletedUser(User user){
        List<Document> documents = ( findAll().stream().filter(document -> document.getAuthor().equals(user)).collect(Collectors.toList()));
        for(Document doc:documents){
            doc.setAuthor(null);
            documentRepo.save(doc);
        }
    }

    public void delete(Document document) {
        if (documentRepo.existsById(document.getId())){
            List<Content> contents=document.getContent();
            for(Content content:contents)
                contentService.delete(content);
            documentRepo.delete(document);
        }
    }


    private void addContent(Document document,List<Content> files){
        for(Content content:files){
            try {
                contentService.save(content);
                document.addContent(content);
            } catch (Exception e) {
                //File can't be added if it is null
            }
        }
    }

    private void addPrototypes(Document document,List<FilePrototype> files){
        for(FilePrototype file:files){
            try {
                Content content=contentService.create(file.getMultipartFile(),file.getComment());
                document.addContent(content);
            } catch (Exception e) {
                //File can't be added if it is null
            }
        }
    }
}
