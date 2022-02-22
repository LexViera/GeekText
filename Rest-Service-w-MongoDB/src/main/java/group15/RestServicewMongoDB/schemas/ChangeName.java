package group15.RestServicewMongoDB.schemas;

public class ChangeName {
    private String name;

    public ChangeName(){}

    public ChangeName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
