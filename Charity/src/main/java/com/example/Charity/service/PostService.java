package com.example.Charity.service;

import com.example.Charity.dao.PostRepository;
import com.example.Charity.entity.Post;
import com.example.Charity.enums.PostStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public void createNewPost(Post post){
        post.setCreated_date_post(new Date());
        post.setPostStatus(PostStatus.NEW);
        this.postRepository.save(post);
    }


    public List<Post>findAllNewPosts(){
        return this.postRepository.findAllNewPosts();
    }

    public List<Post>findAllActivePosts(){
        return this.postRepository.findAllActivePosts();
    }

    public List<Post>findAllCompletedPosts(){
        return this.postRepository.findAllCompletedPosts();
    }

    public List<Post>findAllDeletedPosts(){
        return this.postRepository.findAllDeletedPosts();
    }
    public Optional<Post> findById(long id){
        return this.postRepository.findById(id);
    }
    public void approve(Post post,long id){
        postRepository.findById(id);
        post.setPostStatus(PostStatus.ACTIVE);
        this.postRepository.save(post);
    }
    public void deny(Post post,long id){
        postRepository.findById(id);
        this.postRepository.delete(post);
    }

}
