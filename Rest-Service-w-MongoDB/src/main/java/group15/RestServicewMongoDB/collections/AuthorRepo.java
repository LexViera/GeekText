package group15.RestServicewMongoDB.collections;

import org.springframework.data.mongodb.repository.MongoRepository;

import group15.RestServicewMongoDB.models.Author;

public interface AuthorRepo extends MongoRepository<Author, String>{
    
}
