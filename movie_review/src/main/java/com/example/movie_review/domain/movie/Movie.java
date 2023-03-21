package com.example.movie_review.domain.movie;

import com.example.movie_review.domain.review.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document
@AllArgsConstructor
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



    public void addReview(final Review review){
        this.reviews.add(review);
    }

}


