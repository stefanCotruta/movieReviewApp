package com.example.movie_review.interfaces;

import com.example.movie_review.application.dto.movie.CreateMovieDTO;
import com.example.movie_review.application.service.MovieService;
import com.example.movie_review.domain.movie.Genre;
import com.example.movie_review.domain.movie.Movie;
import com.example.movie_review.domain.movie.Thumbnail;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
public class MovieRestController {

    private MovieService movieService;

    @PostMapping(value = "/movie")
    public ResponseEntity<Void> createMovie(final @RequestPart("title") String title,
                                            final @RequestPart("description") String description,
                                            final @RequestPart("genre") String genre,
                                            final @RequestPart("year") String year,
                                            final @RequestPart("actors") String actors,
                                            final @RequestPart("file") MultipartFile file) throws IOException {

        final List<String> actorList = new ArrayList<String>(
                Arrays.asList(actors.split(",")))
                .stream().map(String::trim).toList();

        final CreateMovieDTO dto = new CreateMovieDTO(
                title,
                description,
                Genre.valueOf(genre),
                Integer.parseInt(year),
                actorList,
                file);

        this.movieService.createMovie(dto);


        return ResponseEntity.ok().build();
    }


    @GetMapping("/movie/{movieId}")
    public ResponseEntity<Movie> getMovie(final @PathVariable String movieId){
        return ResponseEntity.ok(this.movieService.getMovie(movieId));
    }

    @GetMapping("/thumbnail/{movieId}")
    public ResponseEntity<ByteArrayResource> getThumbnail(final @PathVariable String movieId) throws IOException {
        final Thumbnail loadFile = this.movieService.downloadFile(this.movieService.getMovie(movieId).getThumbnailId());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(loadFile.getFileType() ))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getFilename() + "\"")
                .body(new ByteArrayResource(loadFile.getFile()));
    }
}
