package com.example.movie_review.application.dto.review;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("CreateReviewDTO test suite")
class CreateReviewDTOTest {

    @Test
    @DisplayName("Given a dto, when getting the properties, Then they should be returned")
    void getterTest(){

//        Given
        final CreateReviewDTO dto = new CreateReviewDTO();

//        When
        final String movieId = dto.getMovieId();
        final String userId = dto.getUserId();
        final Integer rating = dto.getRating();
        final String review = dto.getReview();

//        Then
        assertNull(movieId);
        assertNull(userId);
        assertNull(rating);
        assertNull(review);
    }

}