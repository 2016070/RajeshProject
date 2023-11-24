package com.lms.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibraryManagementSystem {
    private Map<String, Book> books = new HashMap<>();
    private Map<String, Patron> patrons = new HashMap<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        LibraryManagementSystem librarySystem = new LibraryManagementSystem();
        librarySystem.run();
    }

    private void run() {
        while (true) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Add Patron");
            System.out.println("4. View Patrons");
            System.out.println("5. Check Out Book");
            System.out.println("6. Return Book");
            System.out.println("7. View Transactions");
            System.out.println("8. Exit");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    addPatron();
                    break;
                case 4:
                    viewPatrons();
                    break;
                case 5:
                    checkOutBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    viewTransactions();
                    break;
                case 8:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private int getUserChoice() {
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            System.out.print("Enter your choice: ");
            scanner.next(); // Consume the invalid input
        }
        return scanner.nextInt();
    }

    private void addBook() {
        System.out.println("\nAdding a new book:");
        System.out.print("Enter title: ");
        String title = scanner.next();
        System.out.print("Enter author: ");
        String author = scanner.next();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.next();

        Book newBook = new Book(title, author, isbn);
        books.put(isbn, newBook);

        System.out.println("Book added successfully!");
    }

    private void viewBooks() {
        System.out.println("\nBooks in the library:");
        for (Book book : books.values()) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " (ISBN: " + book.getIsbn() + ")");
        }
    }

    private void addPatron() {
        System.out.println("\nAdding a new patron:");
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter contact information: ");
        String contactInfo = scanner.next();

        Patron newPatron = new Patron(name, contactInfo);
        patrons.put(name, newPatron);

        System.out.println("Patron added successfully!");
    }

    private void viewPatrons() {
        System.out.println("\nPatrons in the library:");
        for (Patron patron : patrons.values()) {
            System.out.println(patron.getName() + " - Contact: " + patron.getContactInfo());
        }
    }

    private void checkOutBook() {
        System.out.println("\nChecking out a book:");
        System.out.print("Enter patron name: ");
        String patronName = scanner.next();
        System.out.print("Enter ISBN of the book: ");
        String isbn = scanner.next();

        if (patrons.containsKey(patronName) && books.containsKey(isbn)) {
            Patron patron = patrons.get(patronName);
            Book book = books.get(isbn);
            String date = "2023-07-01"; // You can use a date library for more accurate dates

            Transaction transaction = new Transaction(book, patron, date);
            transactions.add(transaction);

            System.out.println("Book checked out successfully!");
        } else {
            System.out.println("Invalid patron name or ISBN. Please check your input.");
        }
    }

    private void returnBook() {
        System.out.println("\nReturning a book:");
        System.out.print("Enter ISBN of the book: ");
        String isbn = scanner.next();

        for (Transaction transaction : transactions) {
            if (transaction.getBook().getIsbn().equals(isbn)) {
                System.out.println("Book returned successfully!");
                transactions.remove(transaction);
                return;
            }
        }

        System.out.println("Book with ISBN " + isbn + " not found in transactions.");
    }

    private void viewTransactions() {
        System.out.println("\nTransaction history:");
        for (Transaction transaction : transactions) {
            System.out.println("Date: " + transaction.getDate() +
                    ", Book: " + transaction.getBook().getTitle() +
                    ", Patron: " + transaction.getPatron().getName());
        }
    }
}

