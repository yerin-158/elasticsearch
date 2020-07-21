package com.example.elasticsearch.service;

import com.example.elasticsearch.domain.Person;
import com.example.elasticsearch.domain.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Page<Person> findPersonByName(String name, Pageable pageable) {
        return personRepository.findByName(name, pageable);
    }

    public List<Person> findByNameAndAge(String name, int age) {
        return personRepository.findByNameAndAge(name, age);
    }

}
