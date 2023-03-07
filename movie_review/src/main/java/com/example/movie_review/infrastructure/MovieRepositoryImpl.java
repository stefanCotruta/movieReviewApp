package com.example.movie_review.infrastructure;

import com.example.movie_review.domain.movie.Movie;
import com.example.movie_review.domain.movie.MovieRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class MovieRepositoryImpl implements MovieRepositoryI {

    private MongoTemplate mongoTemplate;

    @Override
    public void saveMovie(final Movie movie) {
        this.mongoTemplate.save(movie);
    }

    @Override
    public List<Movie> getMovies() {
        return this.mongoTemplate.findAll(Movie.class);
    }

    @Override
    public Movie getById(final String id) {
        return this.mongoTemplate.findById(id, Movie.class);
    }

    @Override
    public void deleteMovie(final String id) {
        final Query query = new Query(Criteria.where("_id").is(id));
        this.mongoTemplate.remove(query);
    }
}
