package group15.RestServicewMongoDB.models;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document
public class Session {
    private long timeStamp; 

    public Session(){
        this.timeStamp = new Date().getTime();
    }
    
    public long getTimeStamp(){
        return timeStamp; 
    }
}
