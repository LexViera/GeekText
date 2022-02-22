package group15.RestServicewMongoDB.schemas;

import java.util.ArrayList;

public class ChangeCredential {
    private String field;
    private String credential;
    private Message message;
    private ArrayList<String> acceptedFields;

    public ChangeCredential(){}

    public ChangeCredential(String field, Message message){
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public Message getMessage() {
        return message;
    }

    public ArrayList<String> getAcceptedFields() {
        return acceptedFields;
    }

    public String getCredential() {
        return credential;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setAcceptedFields(ArrayList<String> acceptedFields) {
        this.acceptedFields = acceptedFields;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }
}
