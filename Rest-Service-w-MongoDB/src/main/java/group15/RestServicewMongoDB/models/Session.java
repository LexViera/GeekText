package group15.RestServicewMongoDB.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

@Document
public class Session {
    @Id
    private String sessionIdentifier;

    private long timeStamp;
    private String username; 

    public Session(){}

    public Session(String username){
        UUID uuid = UUID.randomUUID();
        this.timeStamp = new Date().getTime();
        this.username = username;
        this.sessionIdentifier = uuid.toString();
    }

    public void setUsername(String username){
        this.username = username;
    }
    
    public long getTimeStamp(){
        return timeStamp; 
    }

    public String getUsername(){
        return username;
    }

    public String getSessionIdentifier(){
        return sessionIdentifier;
    }
}
