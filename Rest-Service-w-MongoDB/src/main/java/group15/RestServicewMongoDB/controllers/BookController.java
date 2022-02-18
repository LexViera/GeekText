package group15.RestServicewMongoDB.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group15.RestServicewMongoDB.collections.BookRepo;
import group15.RestServicewMongoDB.models.Book;

/**
 * BookController
 */
@RestController
public class BookController {

    @Autowired
    private BookRepo bookCollection;

    //Variable and method are here to test if web server is responding
    private int countr = 1;
    @GetMapping("/test")
    public String print(){
        ++countr;
        return "Hello World -> endpoint GET call counter: "+countr;
    }

    @PostMapping("/books")
    public void addBooks(@RequestBody List<Book> books){
        bookCollection.saveAll(books);
    }

    @PostMapping("/books/add")
    public void addBook(@RequestBody Book book){
        bookCollection.save(book);
    }

    
    @GetMapping("/books")
    public List<Book> getBooks(){
        return bookCollection.findAll();
    }

    //Find books by ISBN
    @GetMapping("/books/{bookId}")
    public Book findBook(@PathVariable final String bookId){
        return bookCollection.findById(bookId).orElseGet(Book::new);    
    }
    
}