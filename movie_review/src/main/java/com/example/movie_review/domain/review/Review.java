package com.example.movie_review.domain.review;

import com.example.movie_review.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Review {

    private String userId;
    private Integer rating;
    private String review;

}
