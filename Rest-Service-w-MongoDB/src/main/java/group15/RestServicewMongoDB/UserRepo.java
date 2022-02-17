package group15.RestServicewMongoDB;

import org.springframework.data.mongodb.repository.MongoRepository;

import group15.RestServicewMongoDB.models.User;

public interface UserRepo extends MongoRepository<User, Integer> {
    
}
