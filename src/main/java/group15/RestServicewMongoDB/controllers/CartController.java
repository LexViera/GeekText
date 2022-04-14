package group15.RestServicewMongoDB.controllers;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import group15.RestServicewMongoDB.collections.BookRepo;
import group15.RestServicewMongoDB.collections.SessionRepo;
import group15.RestServicewMongoDB.collections.UserRepo;
import group15.RestServicewMongoDB.models.Book;
import group15.RestServicewMongoDB.models.ShoppingCart;
import group15.RestServicewMongoDB.models.User;
import group15.RestServicewMongoDB.schemas.Message;
import group15.RestServicewMongoDB.utility.MessageHandler;
import group15.RestServicewMongoDB.utility.SessionHandler;

@RestController
public class CartController {
    
    @Autowired
    private BookRepo bookCollection;
    @Autowired
    private SessionRepo sessionCollection;
    @Autowired
    private UserRepo userCollection;
    
   @GetMapping("/cart")
    public List<Book> showCart(HttpServletRequest request){
        
        return SessionHandler.fetchRequestUser(request, sessionCollection, userCollection).getCart().getCartContent();
    }
    
    @PostMapping("/add-to-cart={id}")
    public Message addToCart(@PathVariable String id, HttpServletRequest request){

        Book book = bookCollection.findById(id).orElseGet(Book::new);
        User activeUser = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
        
        if(book.getIsbn() != null){
            activeUser.getCart().addBook(book);
            userCollection.save(activeUser);
            return MessageHandler.addedBooks();
        }
        return MessageHandler.missingBook(); 
    } 

    @PostMapping("/remove-from-cart={id}")
    public Message removeFromCart(@PathVariable String id, HttpServletRequest request)
    {
        Book book = bookCollection.findById(id).orElseGet(Book::new);
        User activeUser = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
        
        System.out.println("CART BEFORE REMOVAL: "+activeUser.getCart().toString());
        if(book.getIsbn() != null ){
            activeUser.getCart().removeBook(book);
            System.out.println("CART AFTER REMOVAL: "+activeUser.getCart().toString());
            userCollection.save(activeUser);
            
            return MessageHandler.customSuccessMesssage("Book Removed");
        }
        return MessageHandler.missingBook();
    }
    
    @PostMapping("/clear-cart")
    public Message clearCart(HttpServletRequest request){

        User activeUser = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
        activeUser.getCart().clearCart();
        userCollection.save(activeUser);

        return MessageHandler.customSuccessMesssage("Cart Cleared"); 
    }
}
