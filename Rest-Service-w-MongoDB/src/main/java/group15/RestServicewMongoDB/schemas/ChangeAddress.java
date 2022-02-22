package group15.RestServicewMongoDB.schemas;

public class ChangeAddress {
    private String address;

    ChangeAddress(){}

    ChangeAddress(String address){
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}

