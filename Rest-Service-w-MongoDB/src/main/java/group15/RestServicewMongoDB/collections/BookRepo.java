package group15.RestServicewMongoDB.collections;

import org.springframework.data.mongodb.repository.MongoRepository;

import group15.RestServicewMongoDB.models.Book;

public interface BookRepo extends MongoRepository<Book, String> {
    
}
