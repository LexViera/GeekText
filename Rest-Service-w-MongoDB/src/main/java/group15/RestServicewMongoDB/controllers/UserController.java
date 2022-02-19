package group15.RestServicewMongoDB.controllers;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group15.RestServicewMongoDB.collections.SessionRepo;
import group15.RestServicewMongoDB.collections.UserRepo;
import group15.RestServicewMongoDB.models.User;
import group15.RestServicewMongoDB.models.Session;
import group15.RestServicewMongoDB.schemas.Message;
import group15.RestServicewMongoDB.schemas.CreditCard;
import group15.RestServicewMongoDB.schemas.Login;

@RestController
public class UserController {

    @Autowired
    private UserRepo userCollection;
    @Autowired
    private SessionRepo sessionCollection;

    final String sessionIdentifierKey = "session-id";
    final int maxCreditCards = 10;

    final String failedToProvideCredentials =  "Failed to provide username and password credentials.";
    final String takenUser = "Entered username is taken.";
    final String createdAccount = "Succesfully created account";
    final String missingUser = "Username provided does not exist";
    final String passwordMismatch = "Invalid login credentials provided";
    final String successfullySignedIn = "Successfully signed in";
    final String notSignedIn = "You are not signed in";
    final String addedCreditCard = "Succesfully added credit card";
    final String maxAmountOfCards = String.format("You have hit the max credit card limit of %d", maxCreditCards);

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
            userCredentials.setCreditCards(new ArrayList<CreditCard>());
            userCollection.save(userCredentials);
            return new Message(createdAccount, "Success");
        }else{
            return new Message(takenUser, "Error");
        }
    }

    @PostMapping("/login")
    public Message loginUser(@RequestBody Login loginCredentials, HttpServletResponse response){  
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

        ArrayList<Session> existingSession = sessionCollection.findByUsername(givenUsername);
        for (Session session : existingSession){
            sessionCollection.deleteById(session.getSessionIdentifier());
        }
        Session newSession = new Session(givenUsername);
        sessionCollection.save(newSession);
        Cookie cookie = new Cookie(sessionIdentifierKey, newSession.getSessionIdentifier());
        response.addCookie(cookie);
        return new Message(successfullySignedIn, "Success");
    }

    @PostMapping("/add-credit-card")
    public Message addCreditCard(@RequestBody CreditCard creditCardCredentials, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String sessionIdentifier = null;
        for (Cookie cookie : cookies){
            if (cookie.getName().equals(sessionIdentifierKey)){
                sessionIdentifier = cookie.getValue();
            }
        }
        if (sessionIdentifier == null) return new Message(notSignedIn, "Error");
        Session existingSession = sessionCollection.findById(sessionIdentifier).orElseGet(Session::new);
        if (existingSession.getSessionIdentifier() == null){
            return new Message(notSignedIn, "Error");
        }
        User user = userCollection.findById(existingSession.getUsername()).orElseGet(User::new);
        ArrayList<CreditCard> userCreditCards = user.getCreditCards();
        if (userCreditCards.size() >= maxCreditCards){
            return new Message(maxAmountOfCards, "Error");
        }

        userCreditCards.add(creditCardCredentials);
        user.setCreditCards(userCreditCards);
        userCollection.save(user);

        String firstName = creditCardCredentials.getFirstName();
        String lastName = creditCardCredentials.getLastName();
        String creditCardNumber = creditCardCredentials.getCreditCardNumber();

        ArrayList<String> missingCredentials = new ArrayList<>();
        if (firstName == null) missingCredentials.add("firstName"); 
        if (lastName == null) missingCredentials.add("lastName");
        if (creditCardNumber == null) missingCredentials.add("creditCardNumber");

        if (missingCredentials.size() > 0){
            StringBuilder missingCredentialsMessage = new StringBuilder();
            missingCredentialsMessage.append("Missing the following credentials: ");
            while (missingCredentials.size() > 0){
                String missingCredential = missingCredentials.remove(missingCredentials.size() - 1); 
                missingCredentialsMessage.append(missingCredential);
                if (missingCredentials.size() > 0) missingCredentialsMessage.append(",");
            }
            return new Message(missingCredentialsMessage.toString(), "Error");
        }
        return new Message(addedCreditCard, "Success");
    }
}