package com.example.movie_review.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    @DisplayName("Given some properties, When constructing a movie, It should be constructed with the provided values")
    void allArgsConstructorTest(){

//        Given
        final String id = "id";
        final String name = "name";
        final String email = "email";
        final String password = "password";

//        When
        final User user = new User(
                id,
                name,
                email,
                password
        );

//        Then
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }


    @Test
    @DisplayName("Given a user, When getting the fields, Then it should return them")
    void getterTest() {

    //        Given
        final User user = new User(
                "id",
                "name",
                "email",
                "password"
        );

//        When
        final String id = user.getId();
        final String name = user.getName();
        final String email = user.getEmail();
        final String password = user.getPassword();

//        Then
        assertEquals("id", id);
        assertEquals("name", name);
        assertEquals("email", email);
        assertEquals("password", password);

    }

    @Test
    @DisplayName("Given a user, When editing it, Then the name and password should be updated")
    void edit() {

//        Given
        final User user = new User(
                "id",
                "name",
                "email",
                "password"
        );

//        When
        user.edit("nameEdit", "passwordEdit");

//        Then
        assertEquals("nameEdit", user.getName());
        assertEquals("passwordEdit", user.getPassword());
    }
}