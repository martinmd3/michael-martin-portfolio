# Library Management System

A command-line library catalog written in Java. It loads books, magazines, and DVDs from a text file and lets you search, sort, check out, return, add, and delete items from a menu.

Built to practice object-oriented design: an abstract base class, an interface, and subclasses that share behavior but format their own details.

## Features

- Load inventory from `inventory.txt` at startup
- Display all items with type-specific details (ISBN for books, issue/month for magazines, runtime/rating for DVDs)
- Search by title or by item ID
- Sort by title (A-Z), publication year (oldest first), or availability
- Check out and return items, with checks for "already checked out" / "already available"
- Add and delete items, with input validation for numeric fields

## OOP concepts demonstrated

| Concept | Where |
|---|---|
| Abstract class | `MediaItem` holds shared fields (ID, title, author/director, year, availability) and an abstract `getItemDetails()` |
| Inheritance | `Book`, `DVD`, and `Magazine` extend `MediaItem` |
| Interface | `Loanable` (`markAsLoaned`, `returnLoaned`, `calculateLateFee`) implemented by all three item types |
| Polymorphism | One `ArrayList<MediaItem>` holds all item types; each prints itself via its own `getItemDetails()` |
| File I/O | `BufferedReader` parses `inventory.txt`; unknown types and malformed numbers are handled with try/catch |
| Sorting | `Collections.sort` with custom `Comparator`s |

## Run it

Requires JDK 8 or newer. From the project folder:

```
javac *.java
java LibraryManagementSystem
```

Run from the folder that contains `inventory.txt`, since the file is loaded by relative path.

You'll see:

```
===== Library Menu =====
1. Display all items
2. Search items
3. Sort items
4. Update item (Check out / Return)
5. Add item
6. Delete item
0. Exit
```

## Inventory file format

One item per line, comma-separated:

```
Book, ID, Title, Author, Year, Genre, ISBN
Magazine, ID, Title, Publisher, Year, IssueNumber, Month
DVD, ID, Title, Director, Year, RuntimeMinutes, Rating
```

## Known limitations

- Changes (checkouts, additions, deletions) live in memory only and are not written back to `inventory.txt`
- Title search is an exact, case-sensitive match
- `calculateLateFee` is a stub that returns 0.0; there's no due-date tracking yet
- Titles containing commas would break the file parser

## Possible next steps

- Save changes back to the file
- Case-insensitive, partial-match search
- Due dates and a real late-fee calculation
- Unit tests for the item classes
