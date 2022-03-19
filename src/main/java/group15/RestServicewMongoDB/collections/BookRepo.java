package group15.RestServicewMongoDB.collections;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import group15.RestServicewMongoDB.models.Author;
import group15.RestServicewMongoDB.models.Book;

public interface BookRepo extends MongoRepository<Book, String> {

    //Query to pull all boooks associated with an author
    public List<Book> findBooksByAuthor(Author author);

    //Find books by genre
    public List<Book> findBooksByGenre(String genre);
     
    //Find books by specified rating (1-5)
    
    //Retrieve List of X Books at a time where X is an integer from a given position in the overall recordset.

    //Find Book subset of size X from index 0 to X
}
