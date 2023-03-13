package com.example.movie_review.application.dto.movie;


import com.example.movie_review.domain.movie.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class CreateMovieDTO {
    private String title;
    private String description;
    private Genre genre;
    private Integer year;
    private List<String> actors;
    private MultipartFile multipartFile;


    public CreateMovieDTO(final String title,
                          final String description,
                          final Genre genre,
                          final Integer year,
                          final List<String> actors
    ){
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.year = year;
        this.actors = actors;
    }
}
