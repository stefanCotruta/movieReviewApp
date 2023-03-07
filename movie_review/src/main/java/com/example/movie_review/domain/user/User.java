package com.example.movie_review.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Document
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;


    public void edit(final String name, final String password){
        this.name = name;
        this.password = password;
    }
}
