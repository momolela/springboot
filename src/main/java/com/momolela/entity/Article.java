package com.momolela.entity;

import io.searchbox.annotations.JestId;
import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;

@Data
public class Article {
    @JestId
//    @Id
    private Integer id;
    private String author;
    private String title;
    private String content;

}
