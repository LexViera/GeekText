package group15.RestServicewMongoDB.collections;

import org.springframework.data.mongodb.repository.MongoRepository;
import group15.RestServicewMongoDB.models.Session;

public interface SessionRepo extends MongoRepository<Session, String> {
    public Session findByUsername(String username);
}