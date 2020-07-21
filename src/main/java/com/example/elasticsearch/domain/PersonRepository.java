package com.example.elasticsearch.domain;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface PersonRepository extends Repository<Person, String> {
    List<Person> findByNameAndAge(String name, int age);

    @Query("{\"match\": {\"name\": {\"query\": \"?0\"}}}")
    Page<Person> findByName(String name, Pageable pageable);

}

