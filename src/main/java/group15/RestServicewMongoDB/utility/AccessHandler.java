package group15.RestServicewMongoDB.utility;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import group15.RestServicewMongoDB.collections.SessionRepo;
import group15.RestServicewMongoDB.collections.UserRepo;
import group15.RestServicewMongoDB.models.User;
import group15.RestServicewMongoDB.schemas.Message;

public class AccessHandler extends SessionHandler{

    @Autowired
    static SessionRepo sessionCollection;
    @Autowired
    static UserRepo userCollection;
    
    public static Message enableAdminAccess (HttpServletRequest request){
        User user = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
        Message response = null;
        if(user == null) response = MessageHandler.notSignedIn();
        if(!user.isAdmin()) response = MessageHandler.notAdmin();
        return response;
    }

    public static Message enableUserAccess (HttpServletRequest request){
        User user = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
        if(user == null) return MessageHandler.notSignedIn();
        return null;
    }
    
}
