package group15.RestServicewMongoDB.controllers.utility;

import group15.RestServicewMongoDB.schemas.Message;

public class MessageHandler {

    static String failedToProvideCredentials =  "Failed to provide username and password credentials.";
    static String takenUser = "Entered username is taken.";
    static String createdAccount = "Succesfully created account.";
    static String missingUser = "Username provided does not exist.";
    static String passwordMismatch = "Invalid login credentials provided.";
    static String successfullySignedIn = "Successfully signed in.";
    static String notSignedIn = "You are not signed in.";
    static String addedCreditCard = "Succesfully added credit card.";
    static String maxAmountOfCards = String.format("You have hit the max credit card limit.");

    private final static String ERROR  = "ERROR";
    private final static  String SUCCESS = "SUCCESS";

    static Message failedToProvideCredentials(){
        return new Message(failedToProvideCredentials, ERROR);
    }

    static Message takenUser(){
        return new Message(takenUser, ERROR);
    }

    static Message createdAccount(){
        return new Message(createdAccount, SUCCESS);
    }

    static Message missingUser(){
        return new Message(missingUser, ERROR);
    }

    static Message passwordMismatch(){
        return new Message(passwordMismatch, ERROR);
    }

    static Message successfullySignedIn(){
        return new Message(successfullySignedIn, SUCCESS);
    }

    static Message notSignedIn(){
        return new Message(notSignedIn, ERROR);
    }

    static Message addedCreditCard(){
        return new Message(addedCreditCard, SUCCESS);
    }

    static Message maxAmountOfCards(){
        return new Message(maxAmountOfCards, ERROR);
    }
}
