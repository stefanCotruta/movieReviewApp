package com.example.movie_review.infrastructure;

import com.example.movie_review.domain.user.User;
import com.example.movie_review.domain.user.UserRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepositoryI {

    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(final User user) {
        this.mongoTemplate.save(user);
    }

    @Override
    public List<User> getUsers() {
        return this.mongoTemplate.findAll(User.class);
    }

    @Override
    public User getById(final String id) {
        return this.mongoTemplate.findById(id, User.class);
    }

    @Override
    public void deleteUser(final String id) {
        final Query query = new Query(Criteria.where("_id").is(id));
        this.mongoTemplate.remove(query);
    }
}
