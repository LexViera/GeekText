package group15.RestServicewMongoDB.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import group15.RestServicewMongoDB.collections.BookRepo;
import group15.RestServicewMongoDB.collections.SessionRepo;
import group15.RestServicewMongoDB.collections.UserRepo;
import group15.RestServicewMongoDB.models.Author;
import group15.RestServicewMongoDB.models.Book;
import group15.RestServicewMongoDB.schemas.Message;
import group15.RestServicewMongoDB.utility.AccessHandler;
import group15.RestServicewMongoDB.utility.MessageHandler;

/**
 * BookController
 */
@RestController
public class BookController {

    @Autowired
    private BookRepo bookCollection;
    @Autowired
    private SessionRepo sessionCollection;
    @Autowired
    private UserRepo userCollection;

    //TESTING Server Response and Features
    @CrossOrigin(origins = "https://localhost:4200")
    @GetMapping("/test")
    public Message test1(){
        return MessageHandler.customSuccessMesssage("Hello World");
    }
    @CrossOrigin(origins = "https://localhost:4200", allowCredentials = "true")
    @GetMapping("/")
    public Message home(){
        return MessageHandler.customSuccessMesssage("Home");
    }


    @CrossOrigin(origins = "https://localhost:4200", allowCredentials = "true")
    @GetMapping("/test2")
    public Object test2(HttpServletRequest request){
        Message response = AccessHandler.enableUserAccess(request,sessionCollection,userCollection);
        //Success Message
        if(response == null) {
            //To-do
            return MessageHandler.customSuccessMesssage("Hello World , Logged in");
        }
        //Error Message
        return response;
    }

    //Accepts a POST call to populate the Book collection with an array of books
    @CrossOrigin(origins = "https://localhost:4200")
    @PostMapping("/books/add-book")
    public Message addBooks(@RequestBody List<Book> books, HttpServletRequest request){
        Message response = AccessHandler.enableAdminAccess(request,sessionCollection,userCollection);
        //Success Message
        if(response == null) {
            //To-do
            bookCollection.saveAll(books);
            return MessageHandler.addedBooks();
        }
        //Error Message
        return response; 
    }

    //Finds all books in the collection
    @CrossOrigin(origins = "https://localhost:4200")
    @GetMapping("/books")
    public List<Book> getBooks(HttpServletRequest request,HttpServletResponse httpResponse) throws IOException{
        Message response = AccessHandler.enableUserAccess(request,sessionCollection,userCollection);
        //Success Message
        if(response == null) {
            //To-do
            return bookCollection.findAll();
        }
        //Error Message added to header
        httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        return null;
    }
    
    // Find book by ISBN
    @CrossOrigin(origins = "https://localhost:4200")
    @GetMapping("/books/id={bookId}")
    public Book findBook(@PathVariable final String bookId, HttpServletRequest request,HttpServletResponse httpResponse)
            throws IOException {

        Message response = AccessHandler.enableUserAccess(request,sessionCollection,userCollection);
        // Success Message
        if (response == null) {
            // To-do
            return bookCollection.findById(bookId).orElseGet(Book::new);
        }
        // Error Message added to header
        httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        return null;
    }

    // Find books by author
    @CrossOrigin(origins = "https://localhost:4200")
    @GetMapping("/books/author={author}")
    public List<Book> findBooksByAuthor(@PathVariable Author author, HttpServletRequest request,HttpServletResponse httpResponse)
    throws IOException {

        Message response = AccessHandler.enableUserAccess(request,sessionCollection,userCollection);
        // Success Message
        if (response == null) {
            // To-do
            return bookCollection.findBooksByAuthor(author);
        }
        // Error Message added to header
        httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        return null;
                
    }

    // Find books by genre
    @CrossOrigin(origins = "https://localhost:4200")
    @GetMapping("/books/genre={genre}")
    public List<Book> findBooksByGenre(@PathVariable String genre, HttpServletRequest request,HttpServletResponse httpResponse)
    throws IOException {

        Message response = AccessHandler.enableUserAccess(request,sessionCollection,userCollection);
        // Success Message
        if (response == null) {
            // To-do
            return bookCollection.findBooksByGenre(genre);
        }
        // Error Message added to header
        httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        return null;

    }

    // Find books by Top Sellers(Top 10)
    @CrossOrigin(origins = "https://localhost:4200")
    @GetMapping("/books/top-sellers")
    public List<Book> findBooksByTopSeller(HttpServletRequest request, HttpServletResponse httpResponse)
            throws IOException {
        Message response = AccessHandler.enableUserAccess(request,sessionCollection,userCollection);
        // Success Message
        if (response == null) {
            // To-do
            List<Book> topSellers = new ArrayList<>(bookCollection.findAll());
            topSellers.sort((o1, o2) -> o1.getCopiesSold().compareTo(o2.getCopiesSold()));
            Collections.reverse(topSellers);
            // Return top 10 sellers
            return ((topSellers.size() >= 10) ? topSellers.subList(0, 10) : topSellers.subList(0, topSellers.size()));
        }
        // Error Message added to header
        httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        return null;

    }

    // Find books by specified rating (1-5)
    public List<Book> findBooksByRating(@PathVariable String rating) {

        // Logic

        // Waiting for Book Rating and Commenting system to be finished

        // Return books with minimum rating sellers
        return null;
    }

    // Find Book subset of size X from index 0 to X
    @CrossOrigin(origins = "https://localhost:4200")
    @GetMapping("/books/get-subset={index}")
    public List<Book> getBookSubset(@PathVariable int index, HttpServletRequest request,
            HttpServletResponse httpResponse)
            throws IOException {
        Message response = AccessHandler.enableUserAccess(request,sessionCollection,userCollection);
        // Success Message
        if (response == null) {
            // To-do
            List<Book> books = bookCollection.findAll();

            if (bookCollection.count() < index) {
                throw new IndexOutOfBoundsException();
            } else {
                return books.subList(0, index);
            }
        }
        // Error Message added to header
        httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        return null;
    }
}