package com.example.Charity.dao;

import com.example.Charity.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Modifying
    @Query("from posts WHERE postStatus='NEW'")
    List<Post>findAllNewPosts();

    @Modifying
    @Query("from posts WHERE postStatus='ACTIVE'")
    List<Post>findAllActivePosts();

    @Modifying
    @Query("from posts WHERE postStatus='COMPLETED'")
    List<Post>findAllCompletedPosts();

    @Modifying
    @Query("from posts WHERE postStatus='DELETED'")
    List<Post>findAllDeletedPosts();

}
