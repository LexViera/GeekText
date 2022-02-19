package group15.RestServicewMongoDB.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group15.RestServicewMongoDB.collections.AuthorRepo;
import group15.RestServicewMongoDB.models.Author;

@RestController
public class AuthorController {

    @Autowired
    AuthorRepo authorCollection;
    
    @PostMapping("/authors")
    public void addAuthors(@RequestBody List<Author> authors){
        authorCollection.saveAll(authors);
    }
    @PostMapping("/authors/add")
    public void addAuthor(@RequestBody Author author){
        authorCollection.save(author);
    }
}
