package group15.RestServicewMongoDB.controllers;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import group15.RestServicewMongoDB.collections.SessionRepo;
import group15.RestServicewMongoDB.collections.UserRepo;
import group15.RestServicewMongoDB.models.Book;
import group15.RestServicewMongoDB.utility.SessionHandler;

@RestController
public class CartController {

    @Autowired
    private SessionRepo sessionCollection;
    @Autowired
    private UserRepo userCollection;
    
   @GetMapping("/cart")
    public List<Book> showCart(HttpServletRequest request)
    {
        return SessionHandler.fetchRequestUser(request, sessionCollection, userCollection).getCart().getCartContent();
    }
    
    @PostMapping("/add-to-cart")
    public void addToCart(Book book, HttpServletRequest request)
    {
        SessionHandler.fetchRequestUser(request, sessionCollection, userCollection).getCart().addBook(book);
    } 

    @PostMapping("/remove-from-cart")
    public void removeFromCart(Book book, HttpServletRequest request)
    {
        SessionHandler.fetchRequestUser(request, sessionCollection, userCollection).getCart().removeBook(book);
    } 
}
