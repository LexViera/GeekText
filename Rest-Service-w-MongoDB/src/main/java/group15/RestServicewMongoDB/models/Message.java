package group15.RestServicewMongoDB.models;

public class Message {
    private String message;
    private int status;

    public Message(String message, int status){
        this.message = message;
        this.status = status;
    }
}
