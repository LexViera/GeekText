package group15.RestServicewMongoDB.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group15.RestServicewMongoDB.collections.UserRepo;
import group15.RestServicewMongoDB.models.User;
import group15.RestServicewMongoDB.schemas.Message;

/**
 * BookController
 */
@RestController
public class UserController {

    @Autowired
    private UserRepo userCollection;

    final String failedToProvideCredentials =  "Failed to provide username and password credentials on signup.";
    final String takenUser = "Entered username is taken.";
    final String createdAccount = "Succesfully created account";

    @PostMapping("/sign-up")
    public Message signupUser(@RequestBody User userCredentials){
        if (userCredentials.getUsername() == null || userCredentials.getPassword() == null){
            return new Message(failedToProvideCredentials, "Error");
        }
        String username = userCredentials.getUsername();
        boolean isExistingUser = userCollection.existsById(username);
        if (!isExistingUser){
            userCollection.save(userCredentials);
            return new Message(createdAccount, "Success");
        }else{
            return new Message(takenUser, "Error");
        }
    }

    @PostMapping("/login")
    public Message loginUser(@RequestBody User userCredentials){
        return new Message("", "");
    }
}