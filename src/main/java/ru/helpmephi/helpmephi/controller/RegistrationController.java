package ru.helpmephi.helpmephi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.helpmephi.helpmephi.entity.User;
import ru.helpmephi.helpmephi.service.UserService;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.HashMap;
import java.util.Map;


@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }



    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model){
        Map<String,String> errors= addUserOrGetErrors(user,bindingResult);
        if(!errors.isEmpty()) {
            model.mergeAttributes(errors);
            model.addAttribute("prevUser",user);
            return "registration";
        }
        model.addAttribute("message","Письмо отправлено вам на почту");
        return "login";
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code,Model model){
        System.out.println(code);
        boolean isActivated= userService.activateUser(code);

        if(isActivated){
            model.addAttribute("message","Пользователь успешно активирован!" );
        }else{
            model.addAttribute("message","Код активации не найден :(" );
        }
        return "login";
    }

    @GetMapping("restore")
    public String restore(){
        return "pswd-restore";
    }

    @PostMapping("restore")
    public String restorePswd(@RequestParam String email,Model model){
        Map<String, String> errors = new HashMap<>(userService.restorePswdOrGetErrors(email));
        if(!errors.isEmpty()){
            model.mergeAttributes(errors);
            model.addAttribute("prevEmail",email);
            return "pswd-restore";
        }
        model.addAttribute("message","Письмо отправлено вам на почту");
        return "login";
    }



    public Map<String,String> addUserOrGetErrors(User user, BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>(ControllerUtils.getErrors(bindingResult));
        if (!user.getPassword().equals(user.getPassword2())) errors.put("password2Error","Пароли должны совпадать");
        if (errors.isEmpty()) {
            errors.putAll(userService.addUserOrGetErrors(user));
        }
        return errors;
    }



}
