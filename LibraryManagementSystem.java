import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private int id;
    private boolean isBorrowed;

    // Constructor
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    // Set book status
    public void borrowBook() {
        this.isBorrowed = true;
    }

    public void returnBook() {
        this.isBorrowed = false;
    }

    // Display book details
    public void display() {
        System.out.println("ID: " + id + ", Title: " + title + ", Author: " + author + ", Borrowed: " + (isBorrowed ? "Yes" : "No"));
    }
}

public class LibraryManagementSystem {

    private static ArrayList<Book> library = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    removeBook();
                    break;
                case 6:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Display menu
    private static void showMenu() {
        System.out.println("\n---- Library Management System ----");
        System.out.println("1. Add Book");
        System.out.println("2. View Books");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. Remove Book");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    // Add a new book
    private static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Book Author: ");
        String author = scanner.nextLine();

        library.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    // View all books
    private static void viewBooks() {
        if (library.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("\n---- List of Books ----");
            for (Book book : library) {
                book.display();
            }
        }
    }

    // Borrow a book
    private static void borrowBook() {
        System.out.print("Enter Book ID to borrow: ");
        int id = scanner.nextInt();

        Book bookToBorrow = null;
        for (Book book : library) {
            if (book.getId() == id && !book.isBorrowed()) {
                bookToBorrow = book;
                break;
            }
        }

        if (bookToBorrow != null) {
            bookToBorrow.borrowBook();
            System.out.println("You have successfully borrowed the book: " + bookToBorrow.getTitle());
        } else {
            System.out.println("Book not available or already borrowed.");
        }
    }

    // Return a borrowed book
    private static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = scanner.nextInt();

        Book bookToReturn = null;
        for (Book book : library) {
            if (book.getId() == id && book.isBorrowed()) {
                bookToReturn = book;
                break;
            }
        }

        if (bookToReturn != null) {
            bookToReturn.returnBook();
            System.out.println("You have successfully returned the book: " + bookToReturn.getTitle());
        } else {
            System.out.println("Book not found or not borrowed.");
        }
    }

    // Remove a book from the library
    private static void removeBook() {
        System.out.print("Enter Book ID to remove: ");
        int id = scanner.nextInt();

        Book bookToRemove = null;
        for (Book book : library) {
            if (book.getId() == id) {
                bookToRemove = book;
                break;
            }
        }

        if (bookToRemove != null) {
            library.remove(bookToRemove);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }
}
