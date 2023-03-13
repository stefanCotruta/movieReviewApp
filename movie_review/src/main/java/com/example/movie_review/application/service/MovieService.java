package com.example.movie_review.application.service;

import com.example.movie_review.application.dto.movie.CreateMovieDTO;
import com.example.movie_review.application.dto.review.CreateEditReviewDTO;
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
import java.time.LocalDate;

import java.util.Objects;
import java.util.UUID;




@Service
@AllArgsConstructor
public class MovieService {

    private GridFsTemplate template;
    @Autowired
    private GridFsOperations operations;
    private MovieRepositoryI movieRepository;

    public void createMovie(final CreateMovieDTO dto) throws IOException {

        validateData(dto);

        final Movie movie = new Movie(
                UUID.randomUUID().toString(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getGenre(),
                dto.getYear(),
                dto.getActors(),
                saveThumbnail(dto.getMultipartFile())
                );

        this.movieRepository.saveMovie(movie);
    }

    private String saveThumbnail(final MultipartFile multipartFile) throws IOException {
            final DBObject metadata = new BasicDBObject();
            metadata.put("fileSize", multipartFile.getSize());

            final Object fileID = template.store(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), multipartFile.getContentType(), metadata);

            return fileID.toString();
    }

    private void validateData(final CreateMovieDTO dto){
        if(dto.getTitle().isEmpty() || dto.getTitle().length()>30){
            throw new RuntimeException("Choose another title");
        }

        if (dto.getDescription().isEmpty() || dto.getDescription().length() > 1000){
            throw new RuntimeException("Chose another description");
        }

        if (dto.getActors().size() > 10){
            throw new RuntimeException("Too many actors");
        }

        if (dto.getYear()< 1900 || dto.getYear()> LocalDate.now().getYear()){
            throw new RuntimeException("Choose another year");
        }

        if (!Objects.requireNonNull(dto.getMultipartFile().getContentType()).contains("image")) {
            throw new RuntimeException("File is not an Image");
        }
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





}
