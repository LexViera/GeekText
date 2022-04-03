package group15.RestServicewMongoDB.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group15.RestServicewMongoDB.collections.SessionRepo;
import group15.RestServicewMongoDB.collections.UserRepo;
import group15.RestServicewMongoDB.models.User;
import group15.RestServicewMongoDB.models.Session;
import group15.RestServicewMongoDB.schemas.Message;
import group15.RestServicewMongoDB.schemas.ViewCreditCards;
import group15.RestServicewMongoDB.utility.MessageHandler;
import group15.RestServicewMongoDB.utility.SessionHandler;
import group15.RestServicewMongoDB.schemas.ChangeCredential;
import group15.RestServicewMongoDB.schemas.ChangePassword;
import group15.RestServicewMongoDB.schemas.CreditCard;
import group15.RestServicewMongoDB.schemas.Login;

@RestController
public class UserController {

    @Autowired
    private UserRepo userCollection;
    @Autowired
    private SessionRepo sessionCollection;

    final int maxCreditCards = 10;

    private boolean isMissingUserOrPassword(String username, String password){
        return (username == null || password == null) ? true : false;
    }

    private Message signupUser(User userCredentials, boolean isAdmin){
        if (isMissingUserOrPassword(userCredentials.getUsername(), userCredentials.getPassword())){
            return MessageHandler.failedToProvideCredentials(); 
        } 
        String username = userCredentials.getUsername();
        boolean isExistingUser = userCollection.existsById(username); 
        if (!isExistingUser){
            userCredentials.setCreditCards(new ArrayList<CreditCard>());
            userCredentials.setIsAdmin(isAdmin);
            userCollection.save(userCredentials);
            return MessageHandler.createdAccount(); 
        }else{
            return MessageHandler.takenUser(); 
        }
    }

    @PostMapping("/sign-up")
    public Message userSignup(@RequestBody User userCredentials){
        return signupUser(userCredentials, false);
    }

    @PostMapping("/admin/sign-up")
    public Message adminUserSignup(@RequestBody User userCredentials, HttpServletRequest request){
        User user = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
        if (user == null) return MessageHandler.notSignedIn(); 

        boolean isAdmin = user.isAdmin();
        if (!isAdmin){
            return MessageHandler.notAdmin();
        }
        return signupUser(userCredentials, isAdmin);
    }
    @CrossOrigin(origins = "https://geek-text-front.herokuapp.com/")
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
        Cookie cookie = new Cookie(SessionHandler.sessionIdentifierKey, newSession.getSessionIdentifier());
        //response.addCookie(cookie);
        response.addHeader(
            "Set-Cookie", cookie.getName()+"="+cookie.getValue()+"; HttpOnly; SameSite=None; Secure=true; Access-Control-Allow-Credentials:true; Access-Control-Allow-Origin: *");
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
    public ViewCreditCards viewCreditCards(HttpServletRequest request){
        User user = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
        if (user == null) new ViewCreditCards(null, MessageHandler.notSignedIn()); 

        ArrayList<CreditCard> userCreditCards = user.getCreditCards();
        int amountOfCards = userCreditCards.size();
        if (amountOfCards == 0){
            return new ViewCreditCards(userCreditCards, MessageHandler.noCreditCards());
        }

        ViewCreditCards response = new ViewCreditCards(userCreditCards, MessageHandler.numberOfCardsAdded(amountOfCards));
        response.setCreditCards(userCreditCards);

        return response;
    }

    @PostMapping("/change-password")
    public Message changeUsername(@RequestBody final ChangePassword changePasswordCredentials, HttpServletRequest request){
        User user = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
        if (user == null) return MessageHandler.notSignedIn(); 
        
        String oldPassword = changePasswordCredentials.getOldPassword();
        String newPassword = changePasswordCredentials.getNewPassword();

        if (oldPassword == null || newPassword == null) return MessageHandler.missingChangePasswordCredentials();
        if (!user.getPassword().equals(oldPassword)) return MessageHandler.providedIncorrectOldPassword();

        user.setPassword(newPassword);
        userCollection.save(user);
        
        return MessageHandler.updatedUser("password");
    }

    @PostMapping("/change/{field}")
    public ChangeCredential changeField(@RequestBody final ChangeCredential changeCredential, @PathVariable final String field, HttpServletRequest request){
        User user = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
        if (user == null) return new ChangeCredential(field, MessageHandler.notSignedIn());   
        
        String credential = changeCredential.getCredential();
        if (credential == null) return new ChangeCredential(field, MessageHandler.missingCredential(credential));   

        switch (field) {
            case "name":
                user.setName(credential);
                break;
            case "address":
                user.setHomeAddress(credential);
                break;
            default:
                ChangeCredential credentialChange = new ChangeCredential(field, MessageHandler.invalidFieldProvided("/change/{field}"));
                ArrayList<String> acceptedFields = new ArrayList<>(Arrays.asList("name", "address"));
                credentialChange.setAcceptedFields(acceptedFields);
                return credentialChange;
        }

        userCollection.save(user);
        return new ChangeCredential(field, MessageHandler.updatedUser(field));
    }

    @GetMapping("/user-info")
    public User userInfo(HttpServletRequest request){
        User user = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
        if (user != null) user.setPassword("-");
        return user; 
    }

    @GetMapping("/admin/{username}/user-info")
    public User adminUserInfo(HttpServletRequest request, @PathVariable final String username){
        User user = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
        if (user != null && user.isAdmin()){
            User fetchedUser = userCollection.findById(username).orElseGet(User::new);
            if (fetchedUser.getUsername() == null) fetchedUser.setUsername("No such user found.");
            return fetchedUser;
        } else return null;
    }
}