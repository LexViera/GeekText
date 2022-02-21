package group15.RestServicewMongoDB.utility;

import java.util.List;

import group15.RestServicewMongoDB.models.Book;
import group15.RestServicewMongoDB.schemas.Message;

public class MessageHandler {
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
        final String addedBooks = "Successfully added book(s)";
        return new Message(addedBooks, SUCCESS);
    }

    public static Message notAdmin(){
        final String notAdmin = "Unauthorized";
        return new Message(notAdmin, ERROR);
    }

    public static Message failedToProvideCredentials(){
        final String failedToProvideCredentials =  "Failed to provide username and password credentials.";
        return new Message(failedToProvideCredentials, ERROR);
    }

    public static Message takenUser(){
        final String takenUser = "Entered username is taken.";
        return new Message(takenUser, ERROR);
    }

    public static Message createdAccount(){
        final String createdAccount = "Succesfully created account.";
        return new Message(createdAccount, SUCCESS);
    }

    public static Message missingUser(){
        final String missingUser = "Username provided does not exist.";
        return new Message(missingUser, ERROR);
    }

    public static Message passwordMismatch(){
        final String passwordMismatch = "Invalid login credentials provided.";
        return new Message(passwordMismatch, ERROR);
    }

    public static Message successfullySignedIn(){
        final String successfullySignedIn = "Successfully signed in.";
        return new Message(successfullySignedIn, SUCCESS);
    }

    public static Message notSignedIn(){
        final String notSignedIn = "You are not signed in.";
        return new Message(notSignedIn, ERROR);
    }

    public static Message addedCreditCard(){
        final String addedCreditCard = "Succesfully added credit card.";
        return new Message(addedCreditCard, SUCCESS);
    }

    public static Message maxAmountOfCards(){
        final String maxAmountOfCards = "You have hit the max credit card limit.";
        return new Message(maxAmountOfCards, ERROR);
    }

    public static Message updatedUser(String userField){
        final String updatedUser = String.format("Succesfully updated user field '%s'", userField);
        return new Message(updatedUser, SUCCESS);
    }

    public static Message missingChangePasswordCredentials(){
        final String missingChangePasswordCredentials = "Must supply 'oldPassword' and 'newPassword' fields to change user password.";
        return new Message(missingChangePasswordCredentials ,ERROR);
    }

    public static Message providedIncorrectOldPassword(){
        final String incorrectOldPassword = "Incorrect old password provided.";
        return new Message(incorrectOldPassword, ERROR);
    }

    public static Message numberOfCardsAdded(int numberOfCards){
        final String message = String.format("You have %s added.", numberOfCards);
        return new Message(message, SUCCESS);
    }   
}
