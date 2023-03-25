package com.example.movie_review.domain.movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Thumbnail {
    private String filename;
    private String fileType;
    private String fileSize;
    private byte[] file;



}
