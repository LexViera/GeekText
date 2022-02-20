package group15.RestServicewMongoDB.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group15.RestServicewMongoDB.collections.BookRepo;
import group15.RestServicewMongoDB.collections.SessionRepo;
import group15.RestServicewMongoDB.collections.UserRepo;
import group15.RestServicewMongoDB.models.Book;
import group15.RestServicewMongoDB.models.User;
import group15.RestServicewMongoDB.schemas.Message;
import group15.RestServicewMongoDB.utility.MessageHandler;
import group15.RestServicewMongoDB.utility.SessionHandler;

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

    //Variable and method are here to test if web server is responding
    private int countr = 0;
    @GetMapping("/test")
    public Message print(HttpServletRequest request){
        ++countr;
        User user = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
        if(user == null) return MessageHandler.notSignedIn();
        //if (!user.isAdmin()) return MessageHandler.notAdmin(); 
        return MessageHandler.customSuccessMesssage("Hello World - Request Counter:"+countr);
    }

    //Accepts a POST call to populate the Book collection with an array of books
    @PostMapping("/books")
    public Message addBooks(@RequestBody List<Book> books, HttpServletRequest request){
        User user = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
        if(user == null) return MessageHandler.notSignedIn();
        if(!user.isAdmin()) return MessageHandler.notAdmin(); 
        bookCollection.saveAll(books);
        return MessageHandler.customSuccessMesssage("Successfully added books.");
    }

    //Accepts a POST call to add a single book object/JSON
    @PostMapping("/books/add")
    public void addBook(@RequestBody Book book){
        bookCollection.save(book);
    }
    
    //Finds all books in the collection
    @GetMapping("/books")
    public List<Book> getBooks(){
        return bookCollection.findAll();
    }
    
}