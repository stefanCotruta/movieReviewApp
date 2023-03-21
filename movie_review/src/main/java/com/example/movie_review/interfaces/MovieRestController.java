package com.example.movie_review.interfaces;

import com.example.movie_review.application.dto.movie.CreateMovieDTO;
import com.example.movie_review.application.dto.review.CreateReviewDTO;
import com.example.movie_review.application.dto.review.EditReviewDTO;
import com.example.movie_review.application.service.MovieService;
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

@RestController
@AllArgsConstructor
public class MovieRestController {

    private MovieService movieService;

    @PostMapping(value = "/movie")
    public ResponseEntity<Void> createMovie(final @RequestPart CreateMovieDTO dto,
                                            final @RequestPart("file") MultipartFile file) throws IOException {

        dto.setMultipartFile(file);

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


    @PostMapping("/review/{movieId}")
    public ResponseEntity<Void> addReview(final @PathVariable String movieId, final @RequestBody CreateReviewDTO dto){
        this.movieService.addReview(movieId, dto);
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/review/update")
    public ResponseEntity<Void> addReview(final @RequestBody EditReviewDTO dto){
        this.movieService.editReview(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/review/{movieId}/{reviewId}")
    public ResponseEntity<Void> deleteReview(final @PathVariable String movieId, final @PathVariable String reviewId){
        this.movieService.deleteReview(movieId, reviewId);
        return ResponseEntity.ok().build();
    }



}
