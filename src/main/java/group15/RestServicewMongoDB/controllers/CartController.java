package group15.RestServicewMongoDB.controllers;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import group15.RestServicewMongoDB.models.Book;
import group15.RestServicewMongoDB.models.User;

@RestController
public class CartController {

    @GetMapping("/cart")
    public List<Book> showCart(User user)
    {
        return user.getCart().getCartContent();
    }
    
    @PostMapping("/add-to-cart")
    public void addToCart(Book book, User user)
    {
        user.getCart().addBook(book);
    }

    @PostMapping("/remove-from-cart")
    public void removeFromCart(Book book, User user)
    {
        user.getCart().removeBook(book);
    } 
}
