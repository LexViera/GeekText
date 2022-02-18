package group15.RestServicewMongoDB.schemas;

public class NewCreditCard {
    private final String firstName, lastName, creditCardNumber;

    public NewCreditCard(String firstName, String lastName, String creditCardNumber){
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
