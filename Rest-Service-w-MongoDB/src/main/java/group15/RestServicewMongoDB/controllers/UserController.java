package group15.RestServicewMongoDB.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group15.RestServicewMongoDB.models.Message;
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
    public Message addSingleBook(@RequestBody User userCredentials){
        if (userCredentials.getUsername() == null || userCredentials.getPassword() == null){
            String message = "Failed to provide username and password credentials on signup.";
            return new Message(message, "Error");
        }
        String username = userCredentials.getUsername();
        boolean isExistingUser = userCollection.existsById(username);
        if (!isExistingUser){
            userCollection.save(userCredentials);
            String message = "Succesfully created account";
            return new Message(message, "Success");
        }else{
            String message = "Entered username is taken.";
            return new Message(message, "Error");
        }
    }
}