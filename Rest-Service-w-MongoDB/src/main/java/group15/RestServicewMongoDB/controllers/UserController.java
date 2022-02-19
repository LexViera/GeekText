package group15.RestServicewMongoDB.controllers;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group15.RestServicewMongoDB.collections.SessionRepo;
import group15.RestServicewMongoDB.collections.UserRepo;
import group15.RestServicewMongoDB.models.User;
import group15.RestServicewMongoDB.models.Session;
import group15.RestServicewMongoDB.schemas.Message;
import group15.RestServicewMongoDB.utility.MessageHandler;
import group15.RestServicewMongoDB.utility.SessionHandler;
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

    private boolean isMissingUserOrPassword(String username, String password){
        return (username == null || password == null) ? true : false;
    }

    @PostMapping("/sign-up")
    public Message signupUser(@RequestBody User userCredentials){
        if (isMissingUserOrPassword(userCredentials.getUsername(), userCredentials.getPassword())){
            return MessageHandler.failedToProvideCredentials(); 
        } 
        String username = userCredentials.getUsername();
        boolean isExistingUser = userCollection.existsById(username); 
        if (!isExistingUser){
            userCredentials.setCreditCards(new ArrayList<CreditCard>());
            userCollection.save(userCredentials);
            return MessageHandler.createdAccount(); 
        }else{
            return MessageHandler.takenUser(); 
        }
    }

    @PostMapping("/login")
    public Message loginUser(@RequestBody Login loginCredentials, HttpServletResponse response){  
        final String givenUsername, givenPassword;
        givenUsername = loginCredentials.getUsername();
        givenPassword = loginCredentials.getPassword();
        if (isMissingUserOrPassword(givenUsername, givenPassword)){
            return MessageHandler.failedToProvideCredentials(); 
        } 
        User matchingUser = userCollection.findById(givenUsername).orElseGet(User::new);
        final String matchingUserPassword = matchingUser.getPassword();
        if (matchingUserPassword == null) return MessageHandler.missingUser(); 
        if (!matchingUserPassword.equals(givenPassword)) return MessageHandler.passwordMismatch(); 
 
        ArrayList<Session> existingSession = sessionCollection.findByUsername(givenUsername);
        for (Session session : existingSession){
            sessionCollection.deleteById(session.getSessionIdentifier());
        }
        Session newSession = new Session(givenUsername);
        sessionCollection.save(newSession);
        Cookie cookie = new Cookie(sessionIdentifierKey, newSession.getSessionIdentifier());
        response.addCookie(cookie);
        return MessageHandler.successfullySignedIn(); 
    }

    @PostMapping("/add-credit-card")
    public Message addCreditCard(@RequestBody CreditCard creditCardCredentials, HttpServletRequest request){
        User user = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
        if (user == null) return MessageHandler.notSignedIn(); 
        ArrayList<CreditCard> userCreditCards = user.getCreditCards();
        if (userCreditCards.size() >= maxCreditCards){
            return MessageHandler.maxAmountOfCards(); 
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

        if (!missingCredentials.isEmpty()){
            StringBuilder missingCredentialsMessage = new StringBuilder();
            missingCredentialsMessage.append("Missing the following credentials: ");
            while (!missingCredentials.isEmpty()){
                String missingCredential = missingCredentials.remove(missingCredentials.size() - 1); 
                missingCredentialsMessage.append(missingCredential);
                if (!missingCredentials.isEmpty()) missingCredentialsMessage.append(",");
            }
            return new Message(missingCredentialsMessage.toString(), "Error");
        }
        return MessageHandler.addedCreditCard(); 
    }

    @PostMapping("/view-credit-cards")
    public Message viewCreditCards(HttpServletRequest request){
        User user = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
        if (user == null) return MessageHandler.notSignedIn(); 

        ArrayList<CreditCard> userCreditCards = user.getCreditCards();
        int numberOfCards = userCreditCards.size();
        String message = String.format("You have %s added.", numberOfCards);
        Message response = new Message(message, "Success");
        response.setCreditCards(userCreditCards);

        return response;
    }

    // Pending
    // @PostMapping("/change-username/{username}")
    // public Message changeUsername(@PathVariable final String username){
    // }
}