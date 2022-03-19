package group15.RestServicewMongoDB.schemas;

public class BookRating {
    
    private int rating;
    private String comment;

    public BookRating(int rating, String comment){
        this.rating = ((rating>=1&&rating<=5) ? rating : null);
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    
}
