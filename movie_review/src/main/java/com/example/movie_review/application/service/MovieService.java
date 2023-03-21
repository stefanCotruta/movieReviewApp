package com.example.movie_review.application.service;

import com.example.movie_review.application.dto.movie.CreateMovieDTO;
import com.example.movie_review.application.dto.review.CreateReviewDTO;
import com.example.movie_review.application.dto.review.EditReviewDTO;
import com.example.movie_review.domain.ValidationHelper;
import com.example.movie_review.domain.movie.Movie;
import com.example.movie_review.domain.movie.MovieRepositoryI;
import com.example.movie_review.domain.movie.Thumbnail;
import com.example.movie_review.domain.review.Review;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class MovieService {

    private GridFsTemplate template;
    @Autowired
    private GridFsOperations operations;
    private MovieRepositoryI movieRepository;
    private ValidationHelper validationHelper;

    public void createMovie(final CreateMovieDTO dto) throws IOException {

        this.validationHelper.validateMovie(dto);

        List<Review> reviews = new ArrayList<>();

        final Movie movie = new Movie(
                UUID.randomUUID().toString(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getGenre(),
                dto.getYear(),
                dto.getActors(),
                saveThumbnail(dto.getMultipartFile()),
                reviews
                );

        this.movieRepository.saveMovie(movie);
    }

    private String saveThumbnail(final MultipartFile multipartFile) throws IOException {
            final DBObject metadata = new BasicDBObject();
            metadata.put("fileSize", multipartFile.getSize());

            final Object fileID = template.store(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), multipartFile.getContentType(), metadata);

            return fileID.toString();
    }



    public Movie getMovie(final String id){
        return this.movieRepository.getById(id);
    }

    public Thumbnail downloadFile(final String id) throws IOException {
        final GridFSFile gridFSFile = template.findOne( new Query(Criteria.where("_id").is(id)) );
        final Thumbnail loadFile = new Thumbnail();

        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            loadFile.setFilename( gridFSFile.getFilename() );

            loadFile.setFileType( gridFSFile.getMetadata().get("_contentType").toString() );

            loadFile.setFileSize( gridFSFile.getMetadata().get("fileSize").toString() );

            loadFile.setFile( IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()) );
        }

        return loadFile;
    }


    public void addReview(final String movieId, final CreateReviewDTO dto){
        this.validationHelper.validateReview(dto);

        final Movie movie = this.movieRepository.getById(movieId);
        final Review review = new Review(
                UUID.randomUUID().toString(),
                dto.getUserId(),
                dto.getRating(),
                dto.getReview()
        );
        movie.addReview(review);

        this.movieRepository.saveMovie(movie);
    }


    public void editReview(final EditReviewDTO dto){
        this.validationHelper.validateReview(dto);

        final Review review = getReviewById(dto.getMovieId(), dto.getReviewId());
        final Movie movie = this.movieRepository.getById(dto.getMovieId());

        movie.getReviews().removeIf(rv -> rv.getReviewId().equals(dto.getReviewId()));

        review.editReview(dto.getRating(), dto.getReview());

        movie.getReviews().add(review);

        this.movieRepository.saveMovie(movie);
    }


    private Review getReviewById(final String movieId, final String reviewId){
        final Movie movie = this.movieRepository.getById(movieId);

        for(Review rv : movie.getReviews()){
            if (rv.getReviewId().equals(reviewId)){
                return rv;
            }
        }

        throw new RuntimeException("Review not found");
    }

    public void deleteReview(final String movieId, final String reviewId){
        final Movie movie = this.movieRepository.getById(movieId);

        movie.getReviews().removeIf(rv -> rv.getReviewId().equals(reviewId));

        this.movieRepository.saveMovie(movie);
    }
}
