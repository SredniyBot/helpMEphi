package ru.helpmephi.helpmephi.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class ControllerUtils {
    protected static Map<String,String> getErrors(BindingResult bindingResult){
        Map<String,String> map = new HashMap();
        for(FieldError fieldError:bindingResult.getFieldErrors()){
            map.put(fieldError.getField()+"Error",fieldError.getDefaultMessage());
        }
        return  map;
    }
}
