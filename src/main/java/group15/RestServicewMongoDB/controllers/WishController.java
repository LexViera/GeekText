package group15.RestServicewMongoDB.controllers;
import java.util.ArrayList;
import java.util.List;
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
import group15.RestServicewMongoDB.models.User;
import group15.RestServicewMongoDB.models.WishList;
import group15.RestServicewMongoDB.schemas.Message;
import group15.RestServicewMongoDB.utility.MessageHandler;
import group15.RestServicewMongoDB.utility.SessionHandler;


@RestController
public class WishController {

    @Autowired
    private SessionRepo sessionCollection;
    @Autowired
    private UserRepo userCollection;
    // @Autowired
    // private BookRepo bookCollection;

    @GetMapping("/wishlist={name}")
    public List<Book> showWish(@PathVariable String name, HttpServletRequest request)
    {
        User activeUser = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
        ArrayList<WishList> wishLists = activeUser.getWish();
        for(WishList x: wishLists){
            if(x.getName()==name){
                return x.getWishContent();
            }

        }
        return null;
    }

    @PostMapping("/create-wishlist={name}")
    public Message createWishlist(@PathVariable String name, HttpServletRequest request){
        User activeUser = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
        
        if(activeUser.getWish().size()>=3){
            return MessageHandler.customErrorMesssage("Maximum Number of Wishlist Reached");
        }
        for(WishList x: activeUser.getWish()){
            if(x.getName()==name){
                return MessageHandler.customErrorMesssage("Wishlist Already Exists");
            }

        }
        activeUser.getWish().add(new WishList(name));
        userCollection.save(activeUser);
        return MessageHandler.customSuccessMesssage("Wishlist Created Successfully");
    } 
    
    // @PostMapping("/add-to-wishlist={bookId}={name}")
    // public Message addToWish(@PathVariable String bookId,@PathVariable String name, HttpServletRequest request){
    //     User activeUser = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
    //     Book book = bookCollection.findById(bookId).orElseGet(Book::new);

    //     if(activeUser.getCart().isInCart(book)){
    //         activeUser.getCart().removeBook(book);
    //         for(WishList x: activeUser.getWish()){
    //             if(x.getName()==name){
    //                 x.addBook(book);
    //                 userCollection.save(activeUser);
    //                 return MessageHandler.addedBooks();
    //             }
    //         }
    //     }
    //     return MessageHandler.customErrorMesssage("Invalid BookID or Wishlist name");
    // } 

    // @PostMapping("/remove-from-wishlist={bookId}={name}")
    // public Message removeFromWish(@PathVariable String bookId,@PathVariable String name, HttpServletRequest request){
    //     User activeUser = SessionHandler.fetchRequestUser(request, sessionCollection, userCollection);
    //     Book book = bookCollection.findById(bookId).orElseGet(Book::new);

    //     for(WishList x: activeUser.getWish()){
    //         if(x.getName()==name){
    //             x.removeBook(book);
    //             userCollection.save(activeUser);
    //             return MessageHandler.customSuccessMesssage("Book Removed");
    //         }
    //     }
    //     return MessageHandler.customErrorMesssage("Invalid BookID or Wishlist name");
    // }

}
