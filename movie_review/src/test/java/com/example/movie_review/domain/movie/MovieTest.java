package com.example.movie_review.domain.movie;

import com.example.movie_review.domain.review.Review;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Movie test suite")
class MovieTest {

    @Test
    @DisplayName("Given 0 properties, When constructing a movie, It should be constructed with null values")
    void noArgsConstructorTest(){
//        When
        final Movie noArgsMovie = new Movie();

//        Then
        assertNull(noArgsMovie.getId());
        assertNull(noArgsMovie.getTitle());
        assertNull(noArgsMovie.getDescription());
        assertNull(noArgsMovie.getGenre());
        assertNull(noArgsMovie.getYear());
        assertNull(noArgsMovie.getActors());
        assertNull(noArgsMovie.getThumbnailId());
        assertNull(noArgsMovie.getReviews());
    }

    @Test
    @DisplayName("Given some properties, When constructing a movie, It should be constructed with the provided values")
    void allArgsConstructorTest(){

//        Given
        final String movieId = "movieId";
        final String title = "title";
        final String description = "description";
        final Genre genre = Genre.ACTION;
        final Integer year = 2011;
        final String thumbnailId = "thumbnailId";
        final List<String> actorList = new ArrayList<>();
        final List<Review> reviewList = new ArrayList<>();

        actorList.add("Leonardo DiCaprio");
        actorList.add("Robert DeNiro");


//        When
        final Movie allArgsMovie = new Movie(
                movieId,
                title,
                description,
                genre,
                year,
                actorList,
                thumbnailId,
                reviewList
        );

//        Then
        assertEquals("movieId", allArgsMovie.getId());
        assertEquals("title", allArgsMovie.getTitle());
        assertEquals("description", allArgsMovie.getDescription());
        assertEquals(Genre.ACTION, allArgsMovie.getGenre());
        assertEquals(2011, allArgsMovie.getYear());
        assertEquals(actorList, allArgsMovie.getActors());
        assertEquals("thumbnailId", allArgsMovie.getThumbnailId());
        assertEquals(reviewList, allArgsMovie.getReviews());
    }

    @Test
    @DisplayName("Given a movie, When getting the fields, Then it should return them")
    void getterTest() {

//        Given
        final List<String> actorList = new ArrayList<>();
        actorList.add("Leonardo DiCaprio");
        actorList.add("Robert DeNiro");

        final List<Review> reviewList = new ArrayList<>();

//        When
        final Movie movie = new Movie(
                "movieId",
                "title",
                "description",
                Genre.ACTION,
                2011,
                actorList,
                "thumbnailId",
                reviewList);

//        Then
        assertEquals("movieId", movie.getId());
        assertEquals("title", movie.getTitle());
        assertEquals("description", movie.getDescription());
        assertEquals(Genre.ACTION, movie.getGenre());
        assertEquals(2011, movie.getYear());
        assertEquals(actorList, movie.getActors());
        assertEquals("thumbnailId", movie.getThumbnailId());
        assertEquals(reviewList, movie.getReviews());
    }

    @Test
    @DisplayName("Given a movie, When setting the fields, Then it should set them correctly")
    void setterTest() {

//        Given
        final List<String> actorList = new ArrayList<>();
        actorList.add("Leonardo DiCaprio");
        actorList.add("Robert DeNiro");

        final List<Review> reviewList = new ArrayList<>();

        final Movie movie = new Movie();

//        When
        movie.setId("movieId");
        movie.setTitle("title");
        movie.setDescription("description");
        movie.setGenre(Genre.ACTION);
        movie.setYear(2011);
        movie.setActors(actorList);
        movie.setThumbnailId("thumbnailId");
        movie.setReviews(reviewList);

//        Then
        assertEquals("movieId", movie.getId());
        assertEquals("title", movie.getTitle());
        assertEquals("description", movie.getDescription());
        assertEquals(Genre.ACTION, movie.getGenre());
        assertEquals(2011, movie.getYear());
        assertEquals(actorList, movie.getActors());
        assertEquals("thumbnailId", movie.getThumbnailId());
        assertEquals(reviewList, movie.getReviews());
    }

    @Test
    @DisplayName("Given a review, When adding review to movie, Then it should add it to the list")
    void addReviewTest() {

        // Given
        final List<String> actorList = new ArrayList<>();
        actorList.add("Leonardo DiCaprio");
        actorList.add("Robert DeNiro");

        final List<Review> reviewList = new ArrayList<>();

        final Review review = new Review(
                "reviewId",
                "userId",
                5,
                "review description test");

        final Movie movie = new Movie(
                "movieId",
                "title",
                "description",
                Genre.ACTION,
                2011,
                actorList,
                "thumbnailId",
                reviewList);

        // When
        movie.addReview(review);

        // Then
        assertEquals(review, reviewList.get(0));
    }

}