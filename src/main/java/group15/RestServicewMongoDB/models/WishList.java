package group15.RestServicewMongoDB.models;
import java.util.LinkedList;
import java.util.List;
public class WishList {
    

private List<Book>wish = new LinkedList<>();

 //empty constructor
 public WishList(){}

 //add book to wishlist
 public void addBook(Book book)
 {
     wish.add(book);
 }

 //remove from wishlist
 public void removeBook(Book book)
 {
     for(Book x:wish)
     {
         if (book.getIsbn().equals(x.getIsbn()))
         {
             wish.remove(book);
         }
     }
 }

public List<Book> getWish() {
    return wish;//#endregion() {
 }
}