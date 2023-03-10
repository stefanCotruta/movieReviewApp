package com.example.movie_review.domain.movie;

import com.example.movie_review.domain.review.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.File;
import java.util.List;

@Getter
@Setter
@Document
@NoArgsConstructor
public class Movie {
    @Id
    private String id;
    private String title;
    private String description;
    private Genre genre;
    private Integer year;
    private List<String> actors;
    private String thumbnailId;
    private List<Review> reviews;



    public Movie (final String id,
                  final String title,
                  final String description,
                  final Genre genre,
                  final Integer year,
                  final List<String> actors,
                  final String thumbnailId){
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.thumbnailId = thumbnailId;
        this.year = year;
        this.actors = actors;
    }

    public void addReview(final Review review){
        this.reviews.add(review);
    }

}


