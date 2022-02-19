package group15.RestServicewMongoDB.schemas;

import java.util.ArrayList;

public class Message {
    private final String message, status; 
    private ArrayList<CreditCard> creditCards;

    public Message(String message, String status){
        this.message = message;
        this.status = status;
        this.creditCards = new ArrayList<CreditCard>();
    }

    public String getMessage(){
        return message;
    }

    public String getStatus(){
        return status;
    }

    public ArrayList<CreditCard> getCreditCards(){
        return creditCards;
    }

    public void setCreditCards(ArrayList<CreditCard> creditCards){
        this.creditCards = creditCards;
    }
}
