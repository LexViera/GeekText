package group15.RestServicewMongoDB.schemas;

public class ChangePassword {
    private String oldPassword, newPassword; 

    public ChangePassword(){}

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
