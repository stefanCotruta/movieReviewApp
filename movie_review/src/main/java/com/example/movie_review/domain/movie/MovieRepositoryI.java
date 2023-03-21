package com.example.movie_review.domain.movie;

import com.example.movie_review.domain.review.Review;

import java.util.List;

public interface MovieRepositoryI {

    void saveMovie(final Movie movie);

    List<Movie> getMovies();

    Movie getById(final String id);

    void deleteMovie(final String id);

//    Review getReviewById(final String id);

}
