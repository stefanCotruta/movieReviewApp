package com.example.movie_review.domain;

import com.example.movie_review.application.dto.movie.CreateMovieDTO;
import com.example.movie_review.application.dto.review.CreateReviewDTO;
import com.example.movie_review.application.dto.review.EditReviewDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;
@Component
public class ValidationHelper {

    public void validateMovie(final CreateMovieDTO dto){
        if(dto.getTitle().isEmpty() || dto.getTitle().length()>30){
            throw new RuntimeException("Choose another title");
        }

        if (dto.getDescription().isEmpty() || dto.getDescription().length() > 1000){
            throw new RuntimeException("Chose another description");
        }

        if (dto.getActors().size() > 10){
            throw new RuntimeException("Too many actors");
        }

        if (dto.getYear() < 1900 || dto.getYear()> LocalDate.now().getYear()){
            throw new RuntimeException("Choose another year");
        }

        if (!Objects.requireNonNull(dto.getMultipartFile().getContentType()).contains("image")) {
            throw new RuntimeException("File is not an Image");
        }
    }


    public void validateReview(final CreateReviewDTO dto){
        validateRatingAndReview(dto.getRating(), dto.getReview());
    }

    public void validateReview(final EditReviewDTO dto){
        validateRatingAndReview(dto.getRating(), dto.getReview());
    }

    private void validateRatingAndReview(final Integer rating, final String review) {
        if (rating == null || rating < 1 || rating > 10){
            throw new RuntimeException("Choose another rating");
        }

        if (review.isEmpty() || review.length() > 500){
            throw new RuntimeException("Choose another review");
        }
    }


    public void validateName(final String name){
        if (name.length() > 30){
            throw new RuntimeException("Name is too long");
        }
    }

    public void validatePassword(final String password){
        if (password.length() < 8 || password.length() > 30){
            throw new RuntimeException("Incorrect length of password");
        }

        if (!password.matches(".*\\d+.*")){
            throw new RuntimeException("Password needs to contain at least 1 number");
        }
    }

    public void validateEmail(final String email){
        if (email.length() < 3 || email.length() > 50 || !email.contains("@")){
            throw new RuntimeException("Email form not suitable");
        }
    }

}
