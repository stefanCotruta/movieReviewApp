package com.example.movie_review.application.dto.review;

import lombok.Getter;

@Getter
public class EditReviewDTO {

    private String movieId;
    private String reviewId;
    private Integer rating;
    private String review;
}
