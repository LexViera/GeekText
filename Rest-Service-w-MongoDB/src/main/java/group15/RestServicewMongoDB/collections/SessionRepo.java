package group15.RestServicewMongoDB.collections;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import group15.RestServicewMongoDB.models.Session;

public interface SessionRepo extends MongoRepository<Session, String> {
    public ArrayList<Session> findByUsername(String username); 
}