package group15.RestServicewMongoDB.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import group15.RestServicewMongoDB.models.Book;
import group15.RestServicewMongoDB.models.WishList;

@RestController
public class WishController {

    @GetMapping("/wish")
    public List<Book> showWish(WishList wish)
    {
        return wish.getWish();
    }
    
    @PostMapping("/add-to-Wish")
    public void addToWish(Book book, WishList wish)
    {
        wish.addBook(book);
    }

    @PostMapping("/remove-from-wish")
    public void removeFromWish(Book book, WishList wish)
    {
        wish.removeBook(book);
    }
}
