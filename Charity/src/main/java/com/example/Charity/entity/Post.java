package com.example.Charity.entity;

import com.example.Charity.enums.PostStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "posts")
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name = "Required_Amount_Money")
    private int required_amount;

    @Column(name = "Views")
    private int views;

    @Column(name = "Created_date_post")
    private Date created_date_post;

    @Column
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    @ManyToOne
    private User user;
}
