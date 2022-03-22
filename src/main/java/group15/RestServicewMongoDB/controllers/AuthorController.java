package group15.RestServicewMongoDB.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import group15.RestServicewMongoDB.utility.AccessHandler;
import group15.RestServicewMongoDB.utility.MessageHandler;
import group15.RestServicewMongoDB.utility.SessionHandler;

@RestController
public class AuthorController {

    
    @Autowired
    AuthorRepo authorCollection;
    @Autowired
    SessionRepo sessionCollection;
    @Autowired
    UserRepo userCollection;

    @PostMapping("/authors/add-author")
    public Message addAuthors(@RequestBody List<Author> authors, HttpServletRequest request, HttpServletResponse httpResponse){

        Message response = AccessHandler.enableUserAccess(request,sessionCollection,userCollection);
            //Success Message
            if(response == null) {
                //To-do
                User user = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
            if(user == null) return MessageHandler.notSignedIn();
            if (!user.isAdmin()) return MessageHandler.notAdmin(); 
            authorCollection.saveAll(authors);
            return MessageHandler.customSuccessMesssage("Successfully added all authors.");
            }
            //Error Message added to header
            return response;
    }
}
