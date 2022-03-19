package group15.RestServicewMongoDB.schemas;

public class CreditCard {
    private final String firstName, lastName, creditCardNumber;

    public CreditCard(String firstName, String lastName, String creditCardNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.creditCardNumber = creditCardNumber;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getCreditCardNumber(){
        return creditCardNumber;
    }

}
