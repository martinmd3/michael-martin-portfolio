

//extends abstract class and adds interface
public class Magazine extends MediaItem implements Loanable {

	// magazine-specific attributes
    private int issueNumber;
    private String month;

    // constructor
    public Magazine(String itemID, String title, String editor, int publicationYear,
                    int issueNumber, String month) {
        super(itemID, title, editor, publicationYear);
        this.issueNumber = issueNumber;
        this.month = month;
    }

    // new getters
    public int getIssueNumber() {
        return issueNumber;
    }

    public String getMonth() {
        return month;
    }

    // implemnts abstract method
    @Override
    public String getItemDetails() {
        return "Magazine, ID: " + getItemID() +
               ", Title: " + getTitle() +
               ", Editor: " + getAuthorOrDirector() +
               ", Year: " + getPublicationYear() +
               ", Issue: " + issueNumber +
               ", Month: " + month +
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
