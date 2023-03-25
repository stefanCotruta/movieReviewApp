package com.example.movie_review.application.dto.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CreateUserDTO test suite")
class EditUserDTOTest {

    @Test
    @DisplayName("Given a dto, when getting the properties, Then they should be returned")
    void getterTest(){

//        Given
        final EditUserDTO dto = new EditUserDTO();

//        When
        final String name = dto.getName();
        final String password = dto.getPassword();

//        Then
        assertNull(name);
        assertNull(password);
    }


}