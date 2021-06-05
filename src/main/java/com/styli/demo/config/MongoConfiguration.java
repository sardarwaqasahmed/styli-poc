///**
// * 
// */
//package com.styli.demo.config;
//
//import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
//import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
//
//import org.bson.codecs.configuration.CodecRegistry;
//import org.bson.codecs.pojo.PojoCodecProvider;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
///**
// * @author Waqas Ahmed
// * @Date Jun 5, 2021
// */
//@Configuration
//public class MongoConfiguration {
//
//    @Value("${spring.data.mongodb.uri}")
//    private String connectionString;
//
//    @Bean
//    public MongoClient mongoClient() {
//        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
//        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
//        return MongoClients.create(MongoClientSettings.builder()
//                                                      .applyConnectionString(new ConnectionString(connectionString))
//                                                      .codecRegistry(codecRegistry)
//                                                      .build());
//    }
//
//}