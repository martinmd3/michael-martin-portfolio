


//extends abstract class and adds interface
public class Book extends MediaItem implements Loanable {

    // book-specific attributes
    private String genre;
    private String ISBN;

    // constructor
    public Book(String itemID, String title, String author, int publicationYear,
                String genre, String ISBN) {
    	//takes the itemID, title, author, and publication year from super class
        super(itemID, title, author, publicationYear);
        //adds the specifics
        this.genre = genre;
        this.ISBN = ISBN;
    }

    // new getters
    public String getGenre() {
        return genre;
    }

    public String getISBN() {
        return ISBN;
    }

    // implemnts abstract method
    @Override
    public String getItemDetails() {
        return "Book, ID: " + getItemID() +
               ", Title: " + getTitle() +
               ", Author: " + getAuthorOrDirector() +
               ", Year: " + getPublicationYear() +
               ", Genre: " + genre +
               ", ISBN: " + ISBN +
               ", Available: " + (isAvailable() ? "Yes" : "No");
    }

    // loanable interface implementations
    
    //marked as loaned will call the abstract class to change availability (where applicable)
    @Override
    public boolean markAsLoaned() {
        return checkOut();
    }

    @Override
    public boolean returnLoaned() {
        return returnItem();
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return 0.0; //would put math here if actually were to do the math
    }
}
