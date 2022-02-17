package group15.RestServicewMongoDB.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group15.RestServicewMongoDB.models.Book;
import group15.RestServicewMongoDB.models.BookRepo;

/**
 * BookController
 */
@RestController
public class BookController {

    @Autowired
    private BookRepo bookCollection;

    private int countr = 100;

    @PostMapping("/books")
    public void addBooks(@RequestBody List<Book> books){
        bookCollection.saveAll(books);
    }

    @PostMapping("/books/addbook")
    public void addSingleBook(@RequestBody Book book){
        bookCollection.save(book);
    }

    @GetMapping("/books/hi")
    public String print(){
        ++countr;
        return "Hello World Bitches "+countr;
    }

    @GetMapping("/books")
    public List<Book> getBooks(){
        return bookCollection.findAll();
    }

    @GetMapping("/books/{bookId}")
    public Book findBook(@PathVariable final String bookId){
        return bookCollection.findById(bookId).orElseGet(Book::new);    
    }
    
}