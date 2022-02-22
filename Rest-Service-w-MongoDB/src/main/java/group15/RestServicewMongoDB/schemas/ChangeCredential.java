package group15.RestServicewMongoDB.schemas;

import java.util.ArrayList;

public class ChangeCredential {
    private String credential;
    private Message message;
    private ArrayList<String> acceptedFields;

    public ChangeCredential(){}

    public ChangeCredential(String credential, Message message){
        this.credential = credential;
        this.message = message;
    }

    public String getCredential() {
        return credential;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setAcceptedFields(ArrayList<String> acceptedFields) {
        this.acceptedFields = acceptedFields;
    }
}
