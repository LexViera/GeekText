package group15.RestServicewMongoDB.controllers;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import group15.RestServicewMongoDB.models.Book;
import group15.RestServicewMongoDB.models.ShoppingCart;

@RestController
public class CartController {

    @GetMapping("/cart")
    public List<Book> showCart(ShoppingCart cart)
    {
        return cart.getCart();
    }
    
    @PostMapping("/add-to-cart")
    public void addToCart(Book book, ShoppingCart cart)
    {
        cart.addBook(book);
    }

    @PostMapping("/remove-from-cart")
    public void removeFromCart(Book book, ShoppingCart cart)
    {
        cart.removeBook(book);
    } 
}
