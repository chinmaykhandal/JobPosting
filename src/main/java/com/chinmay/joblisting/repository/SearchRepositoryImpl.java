package com.chinmay.joblisting.repository;

import com.chinmay.joblisting.model.Post;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.AggregateIterable;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchRepositoryImpl implements SearchRepository {


    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Post> findByText(String text) {

        final List<Post> posts = new ArrayList<>();
        MongoDatabase database = client.getDatabase("JobPost");
        MongoCollection<Document> collection = database.getCollection("JobPost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", new Document("text", new Document("query", text).append("path", Arrays.asList("techs", "desc", "profile")))), new Document("$sort", new Document("exp", 1L)), new Document("$limit", 5L)));
        result.forEach(doc -> posts.add(converter.read(Post.class,doc)));

        return posts;
    }
}
