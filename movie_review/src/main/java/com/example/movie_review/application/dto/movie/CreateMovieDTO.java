package com.example.movie_review.application.dto.movie;


import com.example.movie_review.domain.movie.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateMovieDTO {
    private String title;
    private String description;
    private Genre genre;
    private Integer year;
    private List<String> actors;
    private MultipartFile multipartFile;

}
