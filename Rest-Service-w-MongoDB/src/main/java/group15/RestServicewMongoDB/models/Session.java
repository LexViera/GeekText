package group15.RestServicewMongoDB.models;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document
public class Session {
    private long timeStamp;
    private String username; 

    public Session(String username){
        this.timeStamp = new Date().getTime();
        this.username = username;
    }
    
    public long getTimeStamp(){
        return timeStamp; 
    }
}
