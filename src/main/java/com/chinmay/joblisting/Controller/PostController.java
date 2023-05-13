package com.chinmay.joblisting.Controller;

import com.chinmay.joblisting.repository.PostRepository;
import com.chinmay.joblisting.model.Post;
import com.chinmay.joblisting.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    @Autowired
    PostRepository repo;

    @Autowired
    SearchRepository srepo;
    @CrossOrigin
    @GetMapping("/allPosts")

    public List<Post> getAllPosts()
    {
        return repo.findAll();
    }

    @CrossOrigin
    @GetMapping("/posts/{text}")
    public List<Post> search(@PathVariable String text)
    {
        return srepo.findByText(text);
    }

    @CrossOrigin
    @PostMapping("/post")
    public Post addPost(@RequestBody Post post)
    {
        return repo.save(post);
    }
}
