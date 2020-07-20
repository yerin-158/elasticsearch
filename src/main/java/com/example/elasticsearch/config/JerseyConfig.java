package com.example.elasticsearch.config;

import com.example.elasticsearch.apiConfroller.TestController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/test")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(TestController.class);
    }

}
