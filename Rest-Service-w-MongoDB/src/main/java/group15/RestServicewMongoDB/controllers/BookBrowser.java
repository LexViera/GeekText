package group15.RestServicewMongoDB.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import group15.RestServicewMongoDB.collections.BookRepo;
import group15.RestServicewMongoDB.models.Author;
import group15.RestServicewMongoDB.models.Book;

/**
 * BookController
 */
@RestController
public class BookBrowser {

    @Autowired
    BookRepo bookCollection;

        //Find book by ISBN
        @GetMapping("/books/{bookId}")
        public Book findBook(@PathVariable final String bookId){
            return bookCollection.findById(bookId).orElseGet(Book::new);    
        }
    
        //Find books by author
        @GetMapping("/books/{author}")
        public List<Book> findBooksByAuthor(@PathVariable Author author){
            return bookCollection.findBooksByAuthor(author);
        }

        //Find books by genre
        @GetMapping("/books/{genre}")
        public List<Book> findBooksByGenre(@PathVariable String genre){
            return bookCollection.findBooksByGenre(genre);

        }
        //Find books by Top Sellers(Top 10)
        //@GetMapping("/books/top-sellers")
        //public List<Book> findBooksByTopSeller(){
            //List<Book> topSellers = new LinkedList<>();
            //topSellers.

            //Return top 10 sellers
            //return topSellers;
        //}

        //Find books by specified rating (1-5)
        public List<Book> findBooksByRating(@PathVariable String rating){
            
            //Logic

            //Waiting for Book Rating and Commenting system to be finished

            //Return books with minimum rating sellers
            return null;
        }
        
        //Find Book subset of size X from index 0 to X
        @GetMapping("/books/get-subset?={index}")
        public List<Book> getBookSubset(@PathVariable int index) throws IndexOutOfBoundsException{

            List<Book> books = bookCollection.findAll();
            
            try{
                if(bookCollection.count() < index){
                    throw new IndexOutOfBoundsException();
                }
                else{
                    return books.subList(0, index);
                }
            }
            catch(Exception e){
                //do something with - Requested Subset is larger than Book Collection
            }
            return null;
        }

}
