package com.example.movie_review.application.dto.movie;

import com.example.movie_review.domain.movie.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CreateMovieDTO test suite")
class CreateMovieDTOTest {

    @Test
    @DisplayName("Given some properties, When constructing the dto, It should be constructed with the provided values")
    void allArgsConstructorTest(){

//        Given
        final String title = "title";
        final String description = "description";
        final Genre genre = Genre.ACTION;
        final Integer year = 2000;
        final List<String> actors = new ArrayList<>();
        actors.add("Leonardo DiCaprio");

//        When
        final CreateMovieDTO dto = new CreateMovieDTO(
                title,
                description,
                genre,
                year,
                actors
        );

//        Then
        assertEquals(title, dto.getTitle());
        assertEquals(description, dto.getDescription());
        assertEquals(genre, dto.getGenre());
        assertEquals(year, dto.getYear());
        assertEquals(actors, dto.getActors());
    }

    @Test
    @DisplayName("Given a dto, When setting the fields, Then it should return them")
    void setterAndGetterTest() {

//        Given
        final CreateMovieDTO dto = new CreateMovieDTO(
          null,
          null,
          null,
          null,
          null
        );

        final String title = "title";
        final String description = "description";
        final Genre genre = Genre.ACTION;
        final Integer year = 2000;
        final List<String> actors = new ArrayList<>();
        actors.add("Leonardo DiCaprio");

//        When
        dto.setTitle(title);
        dto.setDescription(description);
        dto.setGenre(genre);
        dto.setYear(year);
        dto.setActors(actors);

//        Then
        assertEquals(title, dto.getTitle());
        assertEquals(description, dto.getDescription());
        assertEquals(genre, dto.getGenre());
        assertEquals(year, dto.getYear());
        assertEquals(actors, dto.getActors());

    }
}