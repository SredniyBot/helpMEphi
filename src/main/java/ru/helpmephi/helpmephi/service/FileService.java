package ru.helpmephi.helpmephi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.UUID;

@Service
public class FileService {
    @Value("${upload.path}")
    private String resPath;

    private File destinationRootFolder;

    @PostConstruct
    private void init(){
        destinationRootFolder =new File(resPath);
        if(!destinationRootFolder.exists()) destinationRootFolder.mkdir();
    }
    public String createFileAndGetName(MultipartFile file) throws Exception {
        if(file!=null&&!file.getOriginalFilename().isEmpty()){
            String resultFileName = UUID.randomUUID() +"."+file.getOriginalFilename();
            file.transferTo(new File(resPath+"/"+resultFileName));
            return resultFileName;
        }else{
            throw new NullPointerException("Файл для сохранения пуст или не существует");
        }
    }

    public void deleteFile(String fileName){
        File f = new File(resPath+"/"+fileName);
        f.delete();
    }

    public String getResPath() {
        return resPath;
    }
}
