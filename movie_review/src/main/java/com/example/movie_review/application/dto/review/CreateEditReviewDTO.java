package com.example.movie_review.application.dto.review;

import lombok.Getter;

@Getter
public class CreateEditReviewDTO {
    private String userId;
    private Integer rating;
    private String review;
}
