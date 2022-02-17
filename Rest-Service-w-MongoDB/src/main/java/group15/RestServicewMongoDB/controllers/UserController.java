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
    public void addSingleBook(@RequestBody User userCredentials){
        String username = userCredentials.getUsername();
        boolean isExistingUser = userCollection.existsById(username);
        System.out.println(isExistingUser);
        if (!isExistingUser){
            userCollection.save(userCredentials);
        }
    }
    
}