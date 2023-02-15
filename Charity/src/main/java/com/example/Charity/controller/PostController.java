package com.example.Charity.controller;

import com.example.Charity.dao.PostRepository;
import com.example.Charity.entity.Post;
import com.example.Charity.enums.PostStatus;
import com.example.Charity.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String guestPage(Model model) {
        List<Post>posts = postService.findAllActivePosts();
        model.addAttribute("posts",posts);
        return "guest";
    }
    @RequestMapping(value = "/main", method = RequestMethod.GET )
    public String mainPage(Model model){
        List<Post>posts = postService.findAllActivePosts();
        model.addAttribute("posts",posts);
        return "main";
    }

    @GetMapping(value = "/create-post")
    public String createPost(){
        return "createNewPost";
    }

    @PostMapping(value = "/create-new-post")
    public String createNewPost(@ModelAttribute Post post){
    this.postService.createNewPost(post);
    return "main";
    }

    @GetMapping(value = "/all-new-posts")
    public String allNewPosts(Model model ){
        List<Post> posts = postService.findAllNewPosts();
        model.addAttribute("posts",posts);
        return "getAllPosts";
    }
    @GetMapping(value = "/all-active-posts")
    public String allActivePosts(Model model ){
        List<Post> posts = postService.findAllActivePosts();
        model.addAttribute("posts",posts);
        return "getAllPosts";
    }
    @GetMapping(value = "/all-completed-posts")
    public String allCompletedPosts(Model model ){
        List<Post> posts = postService.findAllCompletedPosts();
        model.addAttribute("posts",posts);
        return "getAllPosts";
    }

    @GetMapping(value = "/post/{id}")
    public String postDetail(@PathVariable (value = "id")long id, Model model ){
        Optional<Post>post=postService.findById(id);
        ArrayList<Post>res= new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "postDetails";
    }

    @PostMapping(value = "/post/{id}/approve")
    public String postApprove(@PathVariable (value = "id")long id,Model model){
        Post post = postService.findById(id).orElseThrow();
        postService.approve(post,id);
        return "redirect:/all-new-posts";
    }
    @PostMapping(value = "/post/{id}/denied")
    public String postDeny(@PathVariable (value = "id")long id,Model model){
        Post post = postService.findById(id).orElseThrow();
        postService.deny(post,id);
        return "redirect:/all-new-posts";
    }

}
