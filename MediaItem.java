
public abstract class MediaItem {

	// fields that will be used across media types
	private String itemID;
	private String title;
	private String authorOrDirector;
	private int publicationYear;
	private boolean isAvailable;

	// constructor
	public MediaItem(String itemID, String title, String authorOrDirector, int publicationYear) {
		this.itemID = itemID;
		this.title = title;
		this.authorOrDirector = authorOrDirector;
		this.publicationYear = publicationYear;
		this.isAvailable = true; // all new items start as available
	}

	// abstract method that subclasses will use
	public abstract String getItemDetails();

	// controls whether the media selected us avaialable
	public boolean checkOut() {
		if (isAvailable) {
			isAvailable = false;
			return true;
		}
		return false;
	}

	public boolean returnItem() {
		if (!isAvailable) {
			isAvailable = true;
			return true;
		}
		return false;
	}

	// getter methods for subclasses
	public String getItemID() {
		return itemID;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthorOrDirector() {
		return authorOrDirector;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public boolean isAvailable() {
		return isAvailable;
	}
}
