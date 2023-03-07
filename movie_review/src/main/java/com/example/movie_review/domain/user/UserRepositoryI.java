package com.example.movie_review.domain.user;

import java.util.List;

public interface UserRepositoryI {
    void saveUser(final User user);

    List<User> getUsers();

    User getById(final String id);

    void deleteUser(final String id);
}
