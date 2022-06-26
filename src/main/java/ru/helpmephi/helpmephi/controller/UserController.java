package ru.helpmephi.helpmephi.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.helpmephi.helpmephi.entity.Role;
import ru.helpmephi.helpmephi.entity.User;
import ru.helpmephi.helpmephi.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PreAuthorize("hasAuthority('ANGEL')")
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users",userService.findAll());
        return "user_list";
    }


    @PreAuthorize("hasAuthority('ANGEL')")
    @GetMapping("{user}/edit")
    public String editUser(@AuthenticationPrincipal User author,@PathVariable User user,Model model){
        model.addAttribute("user",user);
        model.addAttribute("author",author);
        model.addAttribute("roles", Role.values());
        return "user_edit";
    }


    @PreAuthorize("hasAuthority('ANGEL')")
    @PostMapping
    public String userSave(
            @RequestParam("id") User user,
            @RequestParam(name = "username")String name,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "new_role") String role
    ){
        userService.save(user,name,email,role);
        return "redirect:/user";
    }



    @PreAuthorize("hasAuthority('ANGEL')")
    @PostMapping("/del")
    public String userDel(@RequestParam("id") User user){
        userService.delete(user);
        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(
            @AuthenticationPrincipal User user,
            Model model
    ){
        model.addAttribute("user",user);
        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                                @RequestParam String password,
                                @RequestParam int course){
        userService.updateProfile(user,password,course);
        return "redirect:/user/profile";
    }
}
