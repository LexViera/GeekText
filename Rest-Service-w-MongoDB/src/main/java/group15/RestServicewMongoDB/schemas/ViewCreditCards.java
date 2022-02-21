package group15.RestServicewMongoDB.schemas;

import java.util.ArrayList;

public class ViewCreditCards {
    private ArrayList<CreditCard> creditCards;
    private Message message;

    public ViewCreditCards(ArrayList<CreditCard> creditCards, Message message){
        this.creditCards = creditCards;
        this.message = message;
    }

    public void setCreditCards(ArrayList<CreditCard> creditCards){
        this.creditCards = creditCards;
    }

    public ArrayList<CreditCard> getCreditCards(){
        return creditCards; 
    }
}
