package group15.RestServicewMongoDB.utility;

import java.util.List;

import group15.RestServicewMongoDB.models.Book;
import group15.RestServicewMongoDB.schemas.Message;

public class MessageHandler {


    private final static String failedToProvideCredentials =  "Failed to provide username and password credentials.";
    private final static String takenUser = "Entered username is taken.";
    private final static String createdAccount = "Succesfully created account.";
    private final static String missingUser = "Username provided does not exist.";
    private final static String passwordMismatch = "Invalid login credentials provided.";
    private final static String successfullySignedIn = "Successfully signed in.";
    private final static String notSignedIn = "You are not signed in.";
    private final static String addedCreditCard = "Succesfully added credit card.";
    private final static String maxAmountOfCards = "You have hit the max credit card limit.";
    private final static String notAdmin = "Unauthorized";
    private final static String addedBooks = "Successfully added book(s)";
    

    private final static String ERROR  = "ERROR";
    private final static  String SUCCESS = "SUCCESS";

    public static Message customSuccessMesssage(String customMessage){
        return new Message(customMessage, SUCCESS);
    }

    public static Message customErrorMesssage(String customMessage){
        return new Message(customMessage, ERROR);
    }

    public static Message returnBooks(List<Book> booksList){ 
        return new Message(booksList.toString(), SUCCESS);
    }

    public static Message addedBooks(){
        return new Message(addedBooks, SUCCESS);
    }

    public static Message notAdmin(){
        return new Message(notAdmin, ERROR);
    }

    public static Message failedToProvideCredentials(){
        return new Message(failedToProvideCredentials, ERROR);
    }

    public static Message takenUser(){
        return new Message(takenUser, ERROR);
    }

    public static Message createdAccount(){
        return new Message(createdAccount, SUCCESS);
    }

    public static Message missingUser(){
        return new Message(missingUser, ERROR);
    }

    public static Message passwordMismatch(){
        return new Message(passwordMismatch, ERROR);
    }

    public static Message successfullySignedIn(){
        return new Message(successfullySignedIn, SUCCESS);
    }

    public static Message notSignedIn(){
        return new Message(notSignedIn, ERROR);
    }

    public static Message addedCreditCard(){
        return new Message(addedCreditCard, SUCCESS);
    }

    public static Message maxAmountOfCards(){
        return new Message(maxAmountOfCards, ERROR);
    }
}
