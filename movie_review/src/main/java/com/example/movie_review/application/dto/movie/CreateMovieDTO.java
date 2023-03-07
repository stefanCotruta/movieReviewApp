package com.example.movie_review.application.dto.movie;

import com.example.movie_review.domain.movie.Genre;
import com.example.movie_review.domain.review.Review;
import lombok.Getter;

import java.io.File;
import java.util.List;

@Getter
public class CreateMovieDTO {
    private String title;
    private String description;
    private Genre genre;
    private File thumbnail;
    private Integer year;
    private List<String> actors;

}
