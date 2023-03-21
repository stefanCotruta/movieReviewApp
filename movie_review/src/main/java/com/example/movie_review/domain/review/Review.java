package com.example.movie_review.domain.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private String reviewId;
    private String userId;
    private Integer rating;
    private String review;


    public void editReview(final Integer rating, final String review){
        this.rating = rating;
        this.review = review;
    }

}
