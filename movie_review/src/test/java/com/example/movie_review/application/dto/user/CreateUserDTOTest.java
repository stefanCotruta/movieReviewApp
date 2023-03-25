package com.example.movie_review.application.dto.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CreateUserDTO test suite")
class CreateUserDTOTest {


    @Test
    @DisplayName("Given a dto, when getting the properties, Then they should be returned")
    void getterTest(){

//        Given
        final CreateUserDTO dto = new CreateUserDTO();

//        When
        final String name = dto.getName();
        final String email = dto.getEmail();
        final String password = dto.getPassword();

//        Then
        assertNull(name);
        assertNull(email);
        assertNull(password);
    }
}