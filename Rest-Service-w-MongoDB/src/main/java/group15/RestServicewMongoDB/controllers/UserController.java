package group15.RestServicewMongoDB.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group15.RestServicewMongoDB.collections.SessionRepo;
import group15.RestServicewMongoDB.collections.UserRepo;
import group15.RestServicewMongoDB.models.User;
import group15.RestServicewMongoDB.models.Session;
import group15.RestServicewMongoDB.schemas.Message;
import group15.RestServicewMongoDB.schemas.Login;

@RestController
public class UserController {

    @Autowired
    private UserRepo userCollection;
    @Autowired
    private SessionRepo sessionCollection;

    final String failedToProvideCredentials =  "Failed to provide username and password credentials.";
    final String takenUser = "Entered username is taken.";
    final String createdAccount = "Succesfully created account";
    final String missingUser = "Username provided does not exist";
    final String passwordMismatch = "Invalid login credentials provided";
    final String successfullySignedIn = "Successfully signed in";

    private boolean isMissingUserOrPassword(String username, String password){
        return (username == null || password == null) ? true : false;
    }

    @PostMapping("/sign-up")
    public Message signupUser(@RequestBody User userCredentials){
        if (isMissingUserOrPassword(userCredentials.getUsername(), userCredentials.getPassword())){
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
    public Message loginUser(@RequestBody Login loginCredentials){  
        final String givenUsername, givenPassword;
        givenUsername = loginCredentials.getUsername();
        givenPassword = loginCredentials.getPassword();
        if (isMissingUserOrPassword(givenUsername, givenPassword)){
            return new Message(failedToProvideCredentials, "Error");
        } 
        User matchingUser = userCollection.findById(givenUsername).orElseGet(User::new);
        final String matchingUserPassword = matchingUser.getPassword();
        if (matchingUserPassword == null) return new Message(missingUser, "Error");
        if (!matchingUserPassword.equals(givenPassword)) return new Message(passwordMismatch, "Error"); 
        sessionCollection.save(new Session(givenUsername));
        return new Message(successfullySignedIn, "Success");
    }
}