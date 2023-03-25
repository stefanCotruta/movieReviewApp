package com.example.movie_review.domain.movie;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Thumbnail test suite")
class ThumbnailTest {

    @Test
    @DisplayName("Given 0 properties, When constructing a movie, It should be constructed with null values")
    void noArgsConstructorTest() {

//        When
        final Thumbnail thumbnail = new Thumbnail();

//        Then
        assertNull(thumbnail.getFilename());
        assertNull(thumbnail.getFileType());
        assertNull(thumbnail.getFileSize());
        assertNull(thumbnail.getFile());
    }

    @Test
    @DisplayName("Given a thumbnail and some values, When setting the fields, Then it should return them")
    void setterAndGetterTest() {

//        Given
        final Thumbnail thumbnail = new Thumbnail();
        byte[] byteArr = HexFormat.of().parseHex("e04fd020ea3a6910a2d808002b30309d");

//        When
        thumbnail.setFilename("fileName");
        thumbnail.setFileType("fileType");
        thumbnail.setFileSize("fileSize");
        thumbnail.setFile(byteArr);
//        Then

        assertEquals("fileName", thumbnail.getFilename());
        assertEquals("fileType", thumbnail.getFileType());
        assertEquals("fileSize", thumbnail.getFileSize());
        assertEquals(byteArr, thumbnail.getFile());
    }
}