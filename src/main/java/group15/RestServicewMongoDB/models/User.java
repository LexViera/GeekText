package group15.RestServicewMongoDB.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import group15.RestServicewMongoDB.schemas.CreditCard;
import java.util.ArrayList;
import java.util.List;

@Document
public class User {
    @Id
    private String username;
    private String password;
    private String name;
    private String emailAddress;
    private String homeAddress;
    private Boolean isAdmin = false;
    private ArrayList<CreditCard> creditCards; 
    private ShoppingCart cart;
    private WishList wish;

    public User(){
        this.creditCards = new ArrayList<CreditCard>();
        this.cart = new ShoppingCart();
        this.wish = new WishList();
    }

    public User(String username, String password, String name, String emailAddress, String homeAddress, Boolean isAdmin){
        this.username = username;
        this.password = password;
        this.name = name;
        this.emailAddress = emailAddress;
        this.homeAddress = homeAddress;
        this.isAdmin = (isAdmin) ? true : false;
        this.creditCards = new ArrayList<CreditCard>();
        this.cart = new ShoppingCart();
        this.wish = new WishList();
    }
  
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getName(){
        return name;
    }

    public String getEmailAddress(){
        return emailAddress;
    };

    public String getHomeAddress(){
        return homeAddress;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    public ArrayList<CreditCard> getCreditCards(){
        return creditCards;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setCreditCards(ArrayList<CreditCard> creditCards){
        this.creditCards = creditCards;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public WishList getWish() {
        return wish;
    }

    public void setWish(WishList wish) {
        this.wish = wish;
    }  
}
