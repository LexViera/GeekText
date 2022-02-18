package group15.RestServicewMongoDB.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

    @Id
    private int id;

    private String userName;

    private String password;

    private String userRole;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
  
    public int getId() {
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    

}
