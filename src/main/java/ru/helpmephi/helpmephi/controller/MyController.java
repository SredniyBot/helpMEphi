package ru.helpmephi.helpmephi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpServletRequest;

@Controller
public class MyController {

    @GetMapping
    public String mainPage(){
        return "index";
    }

    @ExceptionHandler(Throwable.class)
    public String handleAnyException(Throwable ex, HttpServletRequest request) {
        System.out.println("ghghgh");
        return ClassUtils.getShortName(ex.getClass());
    }
}
