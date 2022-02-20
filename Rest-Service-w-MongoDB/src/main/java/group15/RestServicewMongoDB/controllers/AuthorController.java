package group15.RestServicewMongoDB.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group15.RestServicewMongoDB.collections.AuthorRepo;
import group15.RestServicewMongoDB.collections.SessionRepo;
import group15.RestServicewMongoDB.collections.UserRepo;

import group15.RestServicewMongoDB.models.Author;
import group15.RestServicewMongoDB.models.User;
import group15.RestServicewMongoDB.schemas.Message;
import group15.RestServicewMongoDB.utility.MessageHandler;

@RestController
public class AuthorController {

    private static final String SessionHandler = null;
    @Autowired
    AuthorRepo authorCollection;
    @Autowired
    SessionRepo sessionCollection;
    @Autowired
    UserRepo userCollection;

    @PostMapping("/add-authors")
    public Message addAuthors(@RequestBody List<Author> authors, HttpServletRequest request){
        
        User user = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
        if (user == null) return MessageHandler.notSignedIn(); 
        
        authorCollection.saveAll(authors);
        return MessageHandler.notSignedIn();
    }

    @PostMapping("/add-author")
    public void addAuthor(@RequestBody Author author){
        authorCollection.save(author);
    }
}
