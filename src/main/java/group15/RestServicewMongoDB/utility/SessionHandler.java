package group15.RestServicewMongoDB.utility;

import group15.RestServicewMongoDB.collections.SessionRepo;
import group15.RestServicewMongoDB.collections.UserRepo;
import group15.RestServicewMongoDB.models.Session;
import group15.RestServicewMongoDB.models.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class SessionHandler {
    
    public final static String sessionIdentifierKey = "session-id";
    
    public static User fetchRequestUser(HttpServletRequest request, SessionRepo sessionCollection, UserRepo userCollection){
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        String sessionIdentifier = null;
        for (Cookie cookie : cookies){
            if (cookie.getName().equals(sessionIdentifierKey)){
                sessionIdentifier = cookie.getValue();
            }
        }
        if (sessionIdentifier == null) return null;
        if (sessionCollection == null || userCollection == null) return null;
        Session existingSession = sessionCollection.findById(sessionIdentifier).orElseGet(Session::new);
        if (existingSession.getSessionIdentifier() == null){
            return null;
        }
        User user = userCollection.findById(existingSession.getUsername()).orElseGet(User::new);
        return user;
    }
}


