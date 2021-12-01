package ru.helpmephi.helpmephi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.helpmephi.helpmephi.entity.doc.Content;
import ru.helpmephi.helpmephi.repos.ContentRepo;

@Service
public class ContentService {

    private final ContentRepo contentRepo;
    private final FileService fileService;

    public ContentService(ContentRepo contentRepo, FileService fileService) {
        this.contentRepo = contentRepo;
        this.fileService = fileService;
    }

    public Content create(MultipartFile multipartFile,String comment) throws Exception {
        String fileName=fileService.createFileAndGetName(multipartFile);
        return new Content(fileName, comment);
    }

    public void delete(Content content){
        contentRepo.delete(content);
        fileService.deleteFile(content.getFileName());
    }

    public void edit(Content content, String comment){
        content.setComment(comment);
        contentRepo.save(content);
    }

    public void save(Content content) {
        contentRepo.save(content);
    }


}
