package group15.RestServicewMongoDB;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, Integer> {
    
}