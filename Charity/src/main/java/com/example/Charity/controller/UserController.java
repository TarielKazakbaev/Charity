package com.example.Charity.controller;
import com.example.Charity.entity.User;
import com.example.Charity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {
        return "main";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error",required = false)String error,
            @RequestParam(value = "logout",required = false)String logout) {
        ModelAndView model= new ModelAndView();
        if (error != null) {
            model.addObject("error", "Почта или пароль неверны");
            model.setViewName("/login");
        }
        if (logout != null) {
            model.addObject("logout", "Logged out successfully.");
            model.setViewName("/login");
        }
        return model;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET )
    public String hello(){
        return "main";
    }

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }

    @GetMapping("/adminPage")
    public String adminPage(){
        return "admin";
    }

    @GetMapping("/moderPage")
    public String moderPage(){
        return "moder";
    }

    @GetMapping("/userPage")
    public String userPage(){
        return "user";
    }

    @GetMapping("/recipientPage")
    public String recipientPage(){
        return "recipient";

    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute User user){
        this.userService.save(user);
        return "user";
    }


    @GetMapping("/delete-users")
    public String delete(){
        return "delete";
    }


    @PostMapping(value = "/delete-users-by-email")
    public String deleteByEmail(@ModelAttribute User user){
        this.userService.delete(user.getEmail());
        return "login";
    }

    @GetMapping("/ban-users")
    public String ban(){
        return "ban";
    }

    @PostMapping(value = "/ban-users-by-email")
    public String banByEmail(@ModelAttribute User user){
        this.userService.ban(user.getEmail());
        return "login";
    }

    @GetMapping("/active-users")
    public String active(){
        return "active";
    }

    @PostMapping(value = "/active-users-by-email")
    public String activeByEmail(@ModelAttribute User user){
        this.userService.active(user.getEmail());
        return "login";
    }

    @GetMapping("/change-to-moder")
    public String moder(){
        return "getModer";
    }

    @PostMapping(value = "/change-to-moder-by-email")
    public String changeToModer(@ModelAttribute User user){
        this.userService.moder(user.getEmail());
        return "login";
    }

    @GetMapping("/change-to-user")
    public String user(){
        return "getUser";
    }

    @PostMapping(value = "/change-to-user-by-email")
    public String changeToUser(@ModelAttribute User user){
        this.userService.user(user.getEmail());
        return "login";
    }

    @GetMapping("/change-to-recipient")
    public String recipient(){
        return "getRecipient";
    }

    @PostMapping(value = "/change-to-recipient-by-email")
    public String changeToRecipient(@ModelAttribute User user){
        this.userService.recipient(user.getEmail());
        return "login";
    }

    @GetMapping(value = "/find-all-users")
    public String postMain(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "getAllUsers";
    }

}
