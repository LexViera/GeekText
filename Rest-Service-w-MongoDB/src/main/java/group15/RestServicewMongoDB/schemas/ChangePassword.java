package group15.RestServicewMongoDB.schemas;

public class ChangePassword {
    private final String oldPassword, newPassword; 

    public ChangePassword(String oldPassword, String newPassword){
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword(){
        return oldPassword;
    }
    
    public String getNewPassword(){
        return newPassword;
    }
}
