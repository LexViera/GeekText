package group15.RestServicewMongoDB.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group15.RestServicewMongoDB.models.User;
import group15.RestServicewMongoDB.models.UserRepo;

/**
 * BookController
 */
@RestController
public class UserController {

    @Autowired
    private UserRepo userCollection;

    @PostMapping("/sign-up")
    public void addSingleBook(@RequestBody User userProfile){
        

        userCollection.save(userProfile);
    }
    
}