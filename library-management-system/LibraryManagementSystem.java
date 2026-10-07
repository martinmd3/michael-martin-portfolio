import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

//library system for managing media items
public class LibraryManagementSystem {

	// collection of media items
	private ArrayList<MediaItem> mediaCollection;

	// constructor
	public LibraryManagementSystem() {
		mediaCollection = new ArrayList<>();
	}

	// load items from inventory.txt
	public void loadInventory(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;

			while ((line = br.readLine()) != null) {
				if (line.trim().isEmpty())
					continue; // skip empty lines

				String[] parts = line.split(",");
				for (int i = 0; i < parts.length; i++)
					parts[i] = parts[i].trim(); // trim whitespace

				String type = parts[0];

				switch (type) {
				case "Book":
					mediaCollection.add(
							new Book(parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), parts[5], parts[6]));
					break;
				case "Magazine":
					mediaCollection.add(new Magazine(parts[1], parts[2], parts[3], Integer.parseInt(parts[4]),
							Integer.parseInt(parts[5]), parts[6]));
					break;
				case "DVD":
					mediaCollection.add(new DVD(parts[1], parts[2], parts[3], Integer.parseInt(parts[4]),
							Integer.parseInt(parts[5]), parts[6]));
					break;
				default:
					System.out.println("Unknown media type: " + type); // happens if no media type matches
				}
			}

			System.out.println("Inventory loaded successfully. Total items: " + mediaCollection.size());

		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found: " + filename); // file isn't found in directory
		} catch (IOException ioe) {
			System.out.println("Error reading file: " + filename); // erro in file reading
		} catch (NumberFormatException nfe) {
			System.out.println("Error parsing number from file."); // typo in file
		}
	}

	// getter for main menu stuff
	public ArrayList<MediaItem> getMediaCollection() {
		return mediaCollection;
	}

	// display everything, if empty tell user
	public void displayAllItems(ArrayList<MediaItem> library) {
		if (library.isEmpty()) {
			System.out.println("No items in the library."); // sad day
		} else {
			for (MediaItem item : library) {
				System.out.println(item.getItemDetails()); // just print each
			}
		}
	}

	// search title using linear search
	public void searchByTitle(ArrayList<MediaItem> library, String title) {
		boolean found = false;
		for (MediaItem item : library) {
			if (item.getTitle().equals(title)) {
				System.out.println(item.getItemDetails());
				found = true;
			}
		}
		if (!found)
			System.out.println("No items found with title: " + title);
	}

	// search by ID
	public void searchByID(ArrayList<MediaItem> library, String id) {
		boolean found = false;
		for (MediaItem item : library) {
			if (item.getItemID().equals(id)) {
				System.out.println(item.getItemDetails());
				found = true;
			}
		}
		if (!found)
			System.out.println("No items found with ID: " + id);
	}

	// sort helper
	public void sortItems(ArrayList<MediaItem> library, int criteria) {
		switch (criteria) {
		case 1: // az alphebhetical title
			Collections.sort(library, new Comparator<MediaItem>() {
				@Override
				public int compare(MediaItem m1, MediaItem m2) {
					return m1.getTitle().compareTo(m2.getTitle());
				}
			});
			break;
		case 2: // sorts by year, oldest first
			Collections.sort(library, new Comparator<MediaItem>() {
				@Override
				public int compare(MediaItem m1, MediaItem m2) {
					return Integer.compare(m1.getPublicationYear(), m2.getPublicationYear());
				}
			});
			break;
		case 3: // sorts by availability
			Collections.sort(library, new Comparator<MediaItem>() {
				@Override
				public int compare(MediaItem m1, MediaItem m2) {
					return Boolean.compare(m2.isAvailable(), m1.isAvailable()); // makes the available display first
				}
			});
			break;
		default:
			System.out.println("Invalid sort criteria."); // user didn't answer a usable list option
		}

		System.out.println("\n--- Sorted Items ---");
		displayAllItems(library); // display the newly sorted list
	}

	// add new item, loops for type and year, other fields unnecessary since they
	// aren't integers
	public void addNewItem(ArrayList<MediaItem> library, Scanner scanner) {
		System.out.println("\n--- Add New Item ---"); // header

		// type input loop
		String type = "";
		while (true) {
			System.out.print("Enter type (Book/Magazine/DVD): ");
			type = scanner.nextLine().trim();
			// checks for whether the entry is valid
			if (type.equalsIgnoreCase("Book") || type.equalsIgnoreCase("Magazine") || type.equalsIgnoreCase("DVD"))
				break;
			System.out.println("Invalid type. Please enter Book, Magazine, or DVD."); // try again
		}

		System.out.print("Enter ID: ");
		String id = scanner.nextLine();
		System.out.print("Enter title: ");
		String title = scanner.nextLine();
		System.out.print("Enter author/director: ");
		String author = scanner.nextLine();

		// year input loop
		int year = 0;
		while (true) {
			System.out.print("Enter publication year: ");
			String input = scanner.nextLine().trim();
			try {
				year = Integer.parseInt(input);
				break;
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid year. Please enter a number."); // keep asking
			}
		}

		// adding books
		if (type.equalsIgnoreCase("Book")) {
			System.out.print("Enter genre: ");
			String genre = scanner.nextLine();
			System.out.print("Enter ISBN: ");
			String isbn = scanner.nextLine();
			library.add(new Book(id, title, author, year, genre, isbn));
		}

		// adding magazines
		else if (type.equalsIgnoreCase("Magazine")) {
			int issue = 0;
			while (true) {
				System.out.print("Enter issue number: ");
				try {
					issue = Integer.parseInt(scanner.nextLine());
				} catch (NumberFormatException nfe) {
					System.out.println("Issue must be an integer");
					continue;
				}
				break;
			}
			System.out.print("Enter month: ");
			String month = scanner.nextLine();
			library.add(new Magazine(id, title, author, year, issue, month));
		}

		// adding dvds
		else {
			int duration = 0;
			while (true) {
				System.out.print("Enter duration (minutes): ");
				try {
					duration = Integer.parseInt(scanner.nextLine());
				} catch (NumberFormatException nfe) {
					System.out.println("Duration must be an integer");
					continue;
				}
				break;
			}
			System.out.print("Enter rating: ");
			String rating = scanner.nextLine();
			library.add(new DVD(id, title, author, year, duration, rating));
		}

		System.out.println("Item added!"); // hooray
	}

	// remove item by ID
	public void removeItem(ArrayList<MediaItem> library, Scanner scanner) {
		System.out.println("\n--- Remove Item ---");
		System.out.print("Enter the Item ID to remove: ");
		String itemID = scanner.nextLine().trim();

		MediaItem selectedItem = null;
		for (MediaItem item : library) {
			if (item.getItemID().equals(itemID)) {
				selectedItem = item;
				break;
			}
		}

		if (selectedItem != null) {
			library.remove(selectedItem);
			System.out.println("Item removed successfully."); // successful removal
		} else {
			System.out.println("Item with ID " + itemID + " not found."); // shows the user they entered an invalid id
		}
	}

	// main method
	public static void main(String[] args) {
		LibraryManagementSystem system = new LibraryManagementSystem(); // creates system object
		system.loadInventory("inventory.txt"); // make sure inventory.txt is in the project folder
		Scanner scanner = new Scanner(System.in);// import scanner
		boolean running = true;
		// user ui loop
		while (running) {
			// display
			System.out.println("\n===== Library Menu =====");
			System.out.println("1. Display all items");
			System.out.println("2. Search items");
			System.out.println("3. Sort items");
			System.out.println("4. Update item (Check out / Return)");
			System.out.println("5. Add item");
			System.out.println("6. Delete item");
			System.out.println("0. Exit");
			System.out.print("Enter your choice: ");

			// stores user choice for switch cases
			String choice = scanner.nextLine().trim();

			// choice menu
			switch (choice) {
			// option 1: displays all items in the library
			case "1":
				System.out.println("\n--- All Media Items ---");
				system.displayAllItems(system.getMediaCollection());
				break;

			// option 2: Search items, either by title or ID
			case "2":
				System.out.println("\n--- Search Items ---");
				System.out.println("1. By Title");
				System.out.println("2. By ID");
				System.out.print("Enter your choice: ");
				String searchChoice = scanner.nextLine().trim();

				System.out.print("Enter your search term: ");
				String searchTerm = scanner.nextLine().trim();
				// inner menu to choice which way to search
				switch (searchChoice) {
				case "1": // search by title
					system.searchByTitle(system.getMediaCollection(), searchTerm);
					break;

				case "2": // search by ID
					system.searchByID(system.getMediaCollection(), searchTerm);
					break;

				default: // neither option was chosen / invalid input
					System.out.println("Invalid search option.");
				}
				break;

			// option 3: sorts the list of media items based off user preference
			case "3":
				// inner menu for sorting
				System.out.println("\n--- Sort Items ---");

				int criteria = 0; // container
				while (true) {
					System.out.println("1. Title");
					System.out.println("2. Publication Year (oldest first)");
					System.out.println("3. Availability");
					System.out.print("Enter your choice: ");

					int input;
					try {
						input = Integer.parseInt(scanner.nextLine().trim());
					} catch (NumberFormatException nfe) {
						System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
						continue; // ask again instead of crashing
					}

					if (input == 1 || input == 2 || input == 3) {
						criteria = input;
						break;
					} else {
						System.out.println("Invalid choice. Please enter 1, 2, or 3.");
					}
				}

				system.sortItems(system.getMediaCollection(), criteria);
				break;
			// ask user whether they are wanting to check something out or return something
			// before asking for the ID of the media they're requesting
			case "4":
				System.out.println("\n--- Update Item ---");
				int updateChoice = 0; // container
				while (true) {
					System.out.println("1. Check Out Item");
					System.out.println("2. Return Item");
					System.out.print("Enter your choice: ");

					// asks whether to check out or return
					if (scanner.hasNextInt()) {
						updateChoice = scanner.nextInt();
						scanner.nextLine(); // consume leftover newline
						if (updateChoice == 1 || updateChoice == 2) {
							break; // valid choice
						} else {
							System.out.println("Invalid choice. Please enter 1 or 2.");
						}
					} else {
						System.out.println("Invalid input. Please enter a number (1 or 2).");
						scanner.nextLine(); // consume invalid input
					}
				}

				System.out.print("Enter the Item ID: ");
				String itemID = scanner.nextLine().trim();

				MediaItem selectedItem = null; // container
				for (MediaItem item : system.getMediaCollection()) { // linear search for item in library
					if (item.getItemID().equals(itemID)) {
						selectedItem = item;
						break;
					}
				}
				// if item not found, report to user
				if (selectedItem == null) {
					System.out.println("Item with ID " + itemID + " not found.");
					break;
				}

				// once item found, attempt checkout/return
				switch (updateChoice) {
				case 1: // check out
					if (selectedItem.isAvailable()) {
						selectedItem.checkOut();
						System.out.println("Item checked out successfully.");
					} else {
						System.out.println("Item is already checked out.");
					}
					break;

				case 2: // return
					if (!selectedItem.isAvailable()) {
						selectedItem.returnItem();
						System.out.println("Item returned successfully.");
					} else {
						System.out.println("Item is already available.");
					}
					break;
				}
				break;

			// option 5: add item to library
			case "5":
				system.addNewItem(system.getMediaCollection(), scanner);
				break;
			// option 6: delete item from library
			case "6":
				system.removeItem(system.getMediaCollection(), scanner);
				break;
			// option 7/0: exit program
			case "0":
				System.out.println("Exiting program. Goodbye!");
				running = false;
				break;

			// user prompts outside of menu
			default:
				System.out.println("Invalid choice. Please enter a number from 0-6.");

			}

		}
		scanner.close(); // close scanner
	}
}
