package com.example.elasticsearch.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@Data
@Document(indexName = "marvel")
public class Person {

    @Id
    private String id;
    private String name;
    private int age;

    @Builder
    public Person (String id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
