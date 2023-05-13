package com.chinmay.joblisting.repository;

import com.chinmay.joblisting.model.Post;

import java.util.List;

public interface SearchRepository {
    List<Post> findByText(String text);
}
