package com.example.movie_review.domain.movie;

import com.example.movie_review.domain.review.Review;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.File;
import java.util.List;

@Getter
@Setter
@Document
public class Movie {
    @Id
    private String id;
    private String title;
    private String description;
    private Genre genre;

//    private File thumbnail;

    private Integer year;

    private List<String> actors;

    private List<Review> reviews;


    public Movie (final String id, final String title, final String description,
                  final Genre genre, final File thumbnail, final Integer year, final List<String> actors){
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
//        this.thumbnail = thumbnail;
        this.year = year;
        this.actors = actors;
    }

    public void addReview(final Review review){
        this.reviews.add(review);
    }
}


