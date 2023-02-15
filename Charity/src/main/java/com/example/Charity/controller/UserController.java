package com.example.Charity.controller;
import com.example.Charity.entity.User;
import com.example.Charity.enums.RoleStatus;
import com.example.Charity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {



    @Autowired
    private UserService userService;



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

    @RequestMapping(value = "/guest", method = RequestMethod.GET )
    public String guest(){
        return "guest";
    }


    @GetMapping("/myProfile")
    public String getUser(Authentication authentication){
        String userName= authentication.getName();
        User user = userService.findByEmail(userName);
        if (user.getRoles().equals(RoleStatus.USER)){
            return "user";
        }
        else if (user.getRoles().equals(RoleStatus.ADMIN)){
            return "admin";
        }
        else if (user.getRoles().equals(RoleStatus.MODER)){
            return "moder";
        }
        else if(user.getRoles().equals(RoleStatus.RECIPIENT)){
            return "recipient";
        }
        return "redirect:main";
    }

    @GetMapping("/createAccount")
    public String registrationPage(){
        return "registration";
    }



    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute User user)  {
        this.userService.save(user);
        return "login";
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
    @GetMapping(value = "/find-all")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "getAllUsers";
    }

    @GetMapping(value = "/find-all-users")
    public String findUsers(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "getAllUsers";
    }


    @GetMapping(value = "/find-all-moders")
    public String findModers(Model model) {
        List<User> users = userService.findAllModers();
        model.addAttribute("users", users);
        return "getAllUsers";
    }

    @GetMapping(value = "/find-all-recipients")
    public String findRecipients(Model model) {
        List<User> users = userService.findAllRecipients();
        model.addAttribute("users", users);
        return "getAllUsers";
    }

    @GetMapping(value = "/find-all-admins")
    public String findAdmins(Model model) {
        List<User> users = userService.findAllAdmins();
        model.addAttribute("users", users);
        return "getAllUsers";
    }

    @GetMapping(value = "/find-all-banned")
    public String findBanned(Model model) {
        List<User> users = userService.findAllBanned();
        model.addAttribute("users", users);
        return "getAllUsers";
    }

    @GetMapping(value = "/find-all-deleted")
    public String findDeleted(Model model) {
        List<User> users = userService.findAllDeleted();
        model.addAttribute("users", users);
        return "getAllUsers";
    }


}
