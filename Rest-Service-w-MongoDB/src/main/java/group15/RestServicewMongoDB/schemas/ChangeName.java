package group15.RestServicewMongoDB.schemas;

public class ChangeName {
    private String firstName, lastName;

    public ChangeName(){}

    public ChangeName(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
