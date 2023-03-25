package com.example.movie_review.domain.review;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Review test suite")
class ReviewTest {

    @Test
    @DisplayName("Given 0 properties, When constructing a review, It should be constructed with null values")
    void noArgsConstructorTest() {

//       When
        final Review review = new Review();

//        Then
        assertNull(review.getReviewId());
        assertNull(review.getUserId());
        assertNull(review.getRating());
        assertNull(review.getReview());
    }

    @Test
    @DisplayName("Given some properties, When constructing a review, It should be constructed with the correct values")
    void allArgsConstructorTest() {
//        Given
        final String reviewId = "reviewId";
        final String userId = "userId";
        final Integer rating = 5;
        final String reviewText = "review";


//       When
        final Review review = new Review(
                reviewId,
                userId,
                rating,
                reviewText
        );

//        Then
        assertEquals("reviewId", review.getReviewId());
        assertEquals("userId", review.getUserId());
        assertEquals(5, review.getRating());
        assertEquals("review", review.getReview());
    }

    @Test
    @DisplayName("Given a review, When getting the properties, It should return them correctly")
    void getterTest() {

//       Given
        final Review review = new Review(
                "reviewId",
                "userId",
                5,
                "review"
        );

//        When
        final String reviewId = review.getReviewId();
        final String userId = review.getUserId();
        final Integer rating = review.getRating();
        final String reviewText = review.getReview();

//        Then
        assertEquals("reviewId", reviewId);
        assertEquals("userId", userId);
        assertEquals(5, rating);
        assertEquals("review", reviewText);
    }

    @Test
    @DisplayName("Given a review, When editing the review, Then rating and review should be updated")
    void editReview() {

    //       Given
        final Review review = new Review(
                "reviewId",
                "userId",
                5,
                "review"
        );

//        When
        review.editReview(10, "editReviewTest");

//        Then
        assertEquals(10, review.getRating());
        assertEquals("editReviewTest", review.getReview());

    }


}