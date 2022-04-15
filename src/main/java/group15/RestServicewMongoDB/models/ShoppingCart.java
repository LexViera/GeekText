package group15.RestServicewMongoDB.models;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    //variables
    private List<Book>cart;

    //empty constructor
    public ShoppingCart(){
        this.cart = new ArrayList<>();
    }

    //add book
    public void addBook(Book book)
    {
        this.cart.add(book);
        
    }

    //remove book
    public void removeBook(Book book)
    {   
        System.out.println("CART BEFORE REMOVAL in ShoppingCart: "+this.toString());
        for(Book x:cart)
        {
            if (book.getIsbn().equals(x.getIsbn()))
            {
                cart.remove(x);
                break;
            }
        }
        System.out.println("CART AFTER REMOVAL in ShoppingCart: "+this.toString());
    }
    
    //show cart
    public List<Book> getCartContent() 
    {
        return this.cart;
    }

    //clear cart
    public void clearCart() 
    {
        this.cart.clear();
    }

    public boolean isInCart(Book book){

        for(Book x:cart)
        {
            if (book.getIsbn().equals(x.getIsbn()))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        String string = "";
        for(Book x:cart){
            string += x.getIsbn();
            string += " | ";
        }
        return string;
    }
}