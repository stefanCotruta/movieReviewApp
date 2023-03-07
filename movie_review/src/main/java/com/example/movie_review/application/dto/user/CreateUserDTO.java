package com.example.movie_review.application.dto.user;

import lombok.Getter;

@Getter
public class CreateUserDTO {
    private String name;
    private String email;
    private String password;
}
