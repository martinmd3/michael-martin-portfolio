


public class DVD extends MediaItem implements Loanable {

	// dvd-specific attributes
    private int duration; // in minutes
    private String rating;

    // constructor
    public DVD(String itemID, String title, String director, int publicationYear,
               int duration, String rating) {
        super(itemID, title, director, publicationYear);
        this.duration = duration;
        this.rating = rating;
    }

    // new getters
    public int getDuration() {
        return duration;
    }

    public String getRating() {
        return rating;
    }

    // implemnts abstract method
    
    //get item details will display the specs of any called item
    @Override
    public String getItemDetails() {
        return "DVD, ID: " + getItemID() +
               ", Title: " + getTitle() +
               ", Director: " + getAuthorOrDirector() +
               ", Year: " + getPublicationYear() +
               ", Duration: " + duration + " mins" +
               ", Rating: " + rating +
               ", Available: " + (isAvailable() ? "Yes" : "No");//conditional availabiltiy check
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
