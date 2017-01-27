package com.workshop.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Created by mijo on 2017-01-04.
 */
@Configuration
public class SpringMongoConfig {

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception{
        return new SimpleMongoDbFactory(new MongoClient("localhost", 27017), "test");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception{
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }
}
