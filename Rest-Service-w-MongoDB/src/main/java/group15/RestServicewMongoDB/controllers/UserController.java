package group15.RestServicewMongoDB.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group15.RestServicewMongoDB.collections.UserRepo;
import group15.RestServicewMongoDB.models.User;

/**
 * BookController
 */
@RestController
public class UserController {

    @Autowired
    private UserRepo userCollection;
    

    @PostMapping("/adduser")
    public void addSingleBook(@RequestBody User userProfile){
        userCollection.save(userProfile);
    }

    
}