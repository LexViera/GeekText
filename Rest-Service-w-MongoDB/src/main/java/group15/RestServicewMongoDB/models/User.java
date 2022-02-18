package group15.RestServicewMongoDB.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

    @Id
    private String username;

    private String password;
    private String name;
    private String emailAddress;
    private String homeAddress;
    private String userRole;

    public User(String username, String password, String name, String emailAddress, String homeAddress, String userRole){
        this.username = username;
        this.password = password;
        this.name = name;
        this.emailAddress = emailAddress;
        this.homeAddress = homeAddress;
        this.userRole = userRole;
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

    public String getUserRole() {
        return userRole;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
