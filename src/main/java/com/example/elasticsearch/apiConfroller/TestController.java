package com.example.elasticsearch.apiConfroller;

import com.example.elasticsearch.domain.Person;
import com.example.elasticsearch.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
@Component
public class TestController {

    private ElasticsearchOperations elasticsearchOperations;
    private PersonService personService;

    public TestController(ElasticsearchOperations elasticsearchOperations, PersonService personService) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.personService = personService;
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
    public Person findById(@PathParam("id") String id) {
        Person person = elasticsearchOperations.get(id, Person.class);
        return person;
    }

    @GET
    @Path("/person")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByName(@QueryParam("name") String name, @QueryParam("age") int age) {
        if (age != 0) {
            return Response.ok(personService.findByNameAndAge(name, age)).build();
        }

        return Response.ok(personService.findPersonByName(name, PageRequest.of(0, 10))).build();
    }

}
