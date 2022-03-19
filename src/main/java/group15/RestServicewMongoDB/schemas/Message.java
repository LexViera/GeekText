package group15.RestServicewMongoDB.schemas;

public class Message {
    private final String message, status; 

    public Message(String message, String status){
        this.message = message;
        this.status = status;
    }

    public String getMessage(){
        return message;
    }

    public String getStatus(){
        return status;
    }
}
