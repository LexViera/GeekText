package group15.RestServicewMongoDB.utility;

import group15.RestServicewMongoDB.schemas.Message;

public class MessageHandler {

    private final static String ERROR  = "ERROR";
    private final static  String SUCCESS = "SUCCESS";

    public static Message failedToProvideCredentials(){
        String failedToProvideCredentials =  "Failed to provide username and password credentials.";
        return new Message(failedToProvideCredentials, ERROR);
    }

    public static Message takenUser(){
        String takenUser = "Entered username is taken.";
        return new Message(takenUser, ERROR);
    }

    public static Message createdAccount(){
        String createdAccount = "Succesfully created account.";
        return new Message(createdAccount, SUCCESS);
    }

    public static Message missingUser(){
        String missingUser = "Username provided does not exist.";
        return new Message(missingUser, ERROR);
    }

    public static Message passwordMismatch(){
        String passwordMismatch = "Invalid login credentials provided.";
        return new Message(passwordMismatch, ERROR);
    }

    public static Message successfullySignedIn(){
        String successfullySignedIn = "Successfully signed in.";
        return new Message(successfullySignedIn, SUCCESS);
    }

    public static Message notSignedIn(){
        String notSignedIn = "You are not signed in.";
        return new Message(notSignedIn, ERROR);
    }

    public static Message addedCreditCard(){
        String addedCreditCard = "Succesfully added credit card.";
        return new Message(addedCreditCard, SUCCESS);
    }

    public static Message maxAmountOfCards(){
        String maxAmountOfCards = "You have hit the max credit card limit.";
        return new Message(maxAmountOfCards, ERROR);
    }

    public static Message updatedUser(String userField){
        String updatedUser = String.format("Succesfully updated user field '%s'", userField);
        return new Message(updatedUser, SUCCESS);
    }

    public static Message missingChangePasswordCredentials(){
        String missingChangePasswordCredentials = "Must supply 'oldPassword' and 'newPassword' fields to change user password.";
        return new Message(missingChangePasswordCredentials ,ERROR);
    }

    public static Message providedIncorrectOldPassword(){
        String incorrectOldPassword = "Incorrect old password provided.";
        return new Message(incorrectOldPassword, ERROR);
    }
}
