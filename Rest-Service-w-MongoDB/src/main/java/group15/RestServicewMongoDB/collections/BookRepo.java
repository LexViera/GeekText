package group15.RestServicewMongoDB.collections;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import group15.RestServicewMongoDB.models.Author;
import group15.RestServicewMongoDB.models.Book;

public interface BookRepo extends MongoRepository<Book, String> {

    //Query to pull all boooks associated with an author
    public List<Book> findBooksByAuthor(Author author);
}
