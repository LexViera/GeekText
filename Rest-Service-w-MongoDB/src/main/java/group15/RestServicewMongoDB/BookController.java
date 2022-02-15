package group15.RestServicewMongoDB;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * BookController
 */
@RestController
public class BookController {

    @Autowired
    private BookRepo bookRepo;

    private int counter = 0;

    @PostMapping("/books")
    public void addBooks(@RequestBody List<Book> books){
        bookRepo.saveAll(books);
    }

    @PostMapping("/books/singlebook")
    public void addSingleBook(@RequestBody Book book){
        bookRepo.save(book);
    }

    @GetMapping("/books/hi")
    public String print(){
        ++counter;
        return "Hello World Bitches "+counter;
    }

    @GetMapping("/books")
    public List<Book> getBooks(){
        return bookRepo.findAll();
    }

    @GetMapping("/books/{bookId}")
    public Book findBook(@PathVariable final String bookId){
        return bookRepo.findById(bookId).orElseGet(Book::new);
        
        
    }
    
}