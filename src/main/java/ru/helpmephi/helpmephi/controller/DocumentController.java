package ru.helpmephi.helpmephi.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.helpmephi.helpmephi.entity.doc.Content;
import ru.helpmephi.helpmephi.entity.doc.Document;
import ru.helpmephi.helpmephi.entity.User;
import ru.helpmephi.helpmephi.entity.doc.FilePrototype;
import ru.helpmephi.helpmephi.service.DocumentService;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/docs")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    public String documents(Model model){
        model.addAttribute("documents", documentService.findAll());
        return "docs";
    }

    @GetMapping("/doc/{document}")
    public String doc(@PathVariable Document document,Model model){
        model.addAttribute("document",document);
        return "doc";
    }


    @PreAuthorize("hasAnyAuthority('STRANGER','COMRADE','PEACEMAKER','HERO','ANGEL')")
    @GetMapping("/create")
    public String creation(){
        return "doc_add";
    }

    @PreAuthorize("hasAnyAuthority('STRANGER','COMRADE','PEACEMAKER','HERO','ANGEL')")
    @PostMapping("/create")
    public String create(
                @AuthenticationPrincipal User user,

            @RequestParam(name = "file1" ,required = false) MultipartFile file1,
            @RequestParam(name = "file2" ,required = false) MultipartFile file2,
            @RequestParam(name = "file3" ,required = false) MultipartFile file3,
            @RequestParam(name = "file4" ,required = false) MultipartFile file4,
            @RequestParam(name = "file5" ,required = false) MultipartFile file5,

            @RequestParam(name = "comment1" ,required = false) String comment1,
            @RequestParam(name = "comment2" ,required = false) String comment2,
            @RequestParam(name = "comment3" ,required = false) String comment3,
            @RequestParam(name = "comment4" ,required = false) String comment4,
            @RequestParam(name = "comment5" ,required = false) String comment5,

            @Valid Document document,
            BindingResult bindingResult,
            @Valid Model model
    ) {
        List<FilePrototype> files = new ArrayList<>();
        files.add(new FilePrototype(file1,comment1));
        files.add(new FilePrototype(file2,comment2));
        files.add(new FilePrototype(file3,comment3));
        files.add(new FilePrototype(file4,comment4));
        files.add(new FilePrototype(file5,comment5));


        Map<String,String> errors=getErrors(
                getCommentsValidation(comment1,comment2,comment3,comment4,comment5),
                getDocCommentValidation(document),
                ControllerUtils.getErrors(bindingResult)
        );


        if(!errors.isEmpty()){
            model.mergeAttributes(errors);
            model.addAttribute("prevDoc",document);
            model.addAttribute("files",files);

            System.out.println(document.getComment());

            return "doc_add";
        }else {
            documentService.saveWithFilePrototypes(user, files,document);                 //TODO list of files
        }
        return "redirect:/docs";
    }



    @GetMapping("{document}/edit")
    public String getDocument(@PathVariable Document document,Model model){
        model.addAttribute("document",document);
        return "doc_edit";
    }


    @PostMapping("/edit")
    public String editDocument(
            @Valid Document document,
            @ModelAttribute List<@Valid Content> contents   //TODO rewrite
    ) {
        ArrayList<MultipartFile> files = new ArrayList<>();
//        files.add(contents);
        documentService.edit(document,contents);
        return "redirect:/docs";
    }




    @PostMapping("/del")
    public String delDocument(@RequestParam("id") Document document){
        documentService.delete(document);
        return "redirect:/docs";
    }

    @SafeVarargs
    private Map<String,String> getErrors(Map<String,String> ... maps){
        Map<String,String> errors =new HashMap<>();
        for(Map<String,String> map :maps){
            errors.putAll(map);
        }
        return errors;
    }

    private Map<String,String> getCommentsValidation(String comment1,String comment2,String comment3,String comment4,String comment5){
        Map<String,String> map=new HashMap<>();

        ArrayList<String> comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(comment2);
        comments.add(comment3);
        comments.add(comment4);
        comments.add(comment5);
        int maxLength=255;
        for(int i=0;i<comments.size();i++){
            if(comments.get(i)==null||comments.get(i).isEmpty()) continue;
            if(comments.get(i).length()>maxLength) map.put("comment"+(i+1)+"Error","Максимальный размер поля: "+maxLength);
        }
        return map;
    }

    private Map<String,String> getDocCommentValidation(Document document){
        Map<String,String> map = new HashMap<>();
        String comment=document.getComment();
        if(comment.contains("&lt;")&&comment.contains("&gt;")){
            map.put("commentError",String.format("В комментарии содержится запрещенный тег: \"%s\"",
                    comment.substring(comment.indexOf("&lt;")+4,comment.indexOf("&gt;"))));
            document.setComment(comment.replaceAll("&lt;","\"").replaceAll("&gt;","\"").replaceAll("'","\""));
        }
        return map;
    }

}
