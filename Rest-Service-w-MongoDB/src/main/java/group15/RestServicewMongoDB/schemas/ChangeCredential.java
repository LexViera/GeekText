package group15.RestServicewMongoDB.schemas;

public class ChangeCredential {
    private String credential;

    public ChangeCredential(){}

    public ChangeCredential(String credential){
        this.credential = credential;
    }

    public String getCredential() {
        return credential;
    }
}
