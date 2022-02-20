package group15.RestServicewMongoDB.utility;

import group15.RestServicewMongoDB.schemas.Message;

public class MessageHandler {


    private static String failedToProvideCredentials =  "Failed to provide username and password credentials.";
    private static String takenUser = "Entered username is taken.";
    private static String createdAccount = "Succesfully created account.";
    private static String missingUser = "Username provided does not exist.";
    private static String passwordMismatch = "Invalid login credentials provided.";
    private static String successfullySignedIn = "Successfully signed in.";
    private static String notSignedIn = "You are not signed in.";
    private static String addedCreditCard = "Succesfully added credit card.";
    private static String maxAmountOfCards = "You have hit the max credit card limit.";

    private final static String ERROR  = "ERROR";
    private final static  String SUCCESS = "SUCCESS";

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
