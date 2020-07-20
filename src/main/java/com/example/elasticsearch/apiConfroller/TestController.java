package com.example.elasticsearch.apiConfroller;

import com.example.elasticsearch.domain.Person;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
@Component
public class TestController {

    private ElasticsearchOperations elasticsearchOperations;

    public TestController(ElasticsearchOperations elasticsearchOperations){
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @POST
    @Path("/person")
    public String save(@FormParam("id") String id, @FormParam("name") String name, @FormParam("age") int age) {
        // TODO json 형식의 오브젝트로 가져오도록 바꾸기
        Person person = Person.builder().id(id).name(name).age(age).build();

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(id)
                .withObject(person)
                .build();

        String documentId = elasticsearchOperations.index(indexQuery, IndexCoordinates.of("marvel"));
        return documentId;
    }

    @GET
    @Path("/person/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person findById(@PathParam("id") String id){
        Person person = elasticsearchOperations.get(id, Person.class);
        return person;
    }

}
