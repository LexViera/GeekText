package group15.RestServicewMongoDB.models;
import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {

    //variables
    private List<Book>cart = new LinkedList<>();

    //empty constructor
    public ShoppingCart(){}

    //add book
    public void addBook(Book book)
    {
        cart.add(book);
    }

    //remove book
    public void removeBook(Book book)
    {
        for(Book x:cart)
        {
            if (book.getIsbn().equals(x.getIsbn()))
            {
                cart.remove(book);
            }
        }
    }

    //show cart
    public List<Book> getCartContent() 
    {
        return cart;
    }
}