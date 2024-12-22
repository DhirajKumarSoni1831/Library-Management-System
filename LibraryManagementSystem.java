import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String name;
    boolean isIssued;
    String issuedTo;

    public Book(String name) {
        this.name = name;
        this.isIssued = false;
        this.issuedTo = null;
    }
}

public class LibraryManagementSystem {
    private static final String USERNAME = "SRIST";
    private static final String PASSWORD = "SRIST";
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("===== Welcome to the Library Management System =====");

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();

            switch (choice) {
                case 1 -> login();
                case 2 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void login() {
        System.out.print("\nEnter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            System.out.println("Login successful!");
            libraryMenu();
        } else {
            System.out.println("Incorrect username or password. Returning to main menu.");
        }
    }

    private static void libraryMenu() {
        while (true) {
            System.out.println("\nLibrary Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. View Book List");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> deleteBook();
                case 3 -> viewBooks();
                case 4 -> issueBook();
                case 5 -> returnBook();
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter the name of the book: ");
        String bookName = scanner.nextLine();
        books.add(new Book(bookName));
        System.out.println("Book \"" + bookName + "\" added successfully.");
    }

    private static void deleteBook() {
        System.out.print("Enter the name of the book to delete: ");
        String bookName = scanner.nextLine();
        for (Book book : books) {
            if (book.name.equalsIgnoreCase(bookName)) {
                books.remove(book);
                System.out.println("Book \"" + bookName + "\" deleted successfully.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("\nList of Books:");
            for (Book book : books) {
                System.out.println("- " + book.name + (book.isIssued ? " (Issued to: " + book.issuedTo + ")" : ""));
            }
        }
    }

    private static void issueBook() {
        System.out.print("Enter the name of the book to issue: ");
        String bookName = scanner.nextLine();
        System.out.print("Enter the name of the student: ");
        String studentName = scanner.nextLine();

        for (Book book : books) {
            if (book.name.equalsIgnoreCase(bookName)) {
                if (book.isIssued) {
                    System.out.println("Book is already issued to " + book.issuedTo + ".");
                } else {
                    book.isIssued = true;
                    book.issuedTo = studentName;
                    System.out.println("Book \"" + bookName + "\" issued to " + studentName + ".");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private static void returnBook() {
        System.out.print("Enter the name of the book to return: ");
        String bookName = scanner.nextLine();

        for (Book book : books) {
            if (book.name.equalsIgnoreCase(bookName)) {
                if (book.isIssued) {
                    System.out.println("Book \"" + bookName + "\" returned by " + book.issuedTo + ".");
                    book.isIssued = false;
                    book.issuedTo = null;
                } else {
                    System.out.println("Book is not issued to anyone.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}
