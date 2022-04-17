package group15.RestServicewMongoDB.models;
import java.util.ArrayList;
import java.util.List;
public class WishList {
    

    private List<Book>wish = new ArrayList<>();
    private String name;

    //empty constructor
    public WishList(){}

    public WishList(String name){
        this.name = name;
    }

    //add book to wishlist
    public void addBook(Book book)
    {
        this.wish.add(book);
    }

    //remove from wishlist
    public void removeBook(Book book)
    {
        for(Book x:wish)
        {
            if (book.getIsbn().equals(x.getIsbn()))
            {
                this.wish.remove(x);
            }
        }
    }

    public List<Book> getWishContent() {
        return wish;//#endregion() {
    }

    public boolean isInWishList(Book book){

        for(Book x:wish)
        {
            if (book.getIsbn().equals(x.getIsbn()))
            {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

 
}