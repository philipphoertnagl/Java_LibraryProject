package org.LibraryProject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Library {
    private List<Book> books;
    private List<Client> clients;


    public Library() {
        this.books = new ArrayList<>();
        this.clients = new ArrayList<>();
    }
    public void addBookCopy(BookTitle bookTitle, int amount) { //needed only for 1 single copy adding to list
        for(int i = 0; i < amount; i++) {
            Book newCopy = bookTitle.addCopy();
            this.books.add(newCopy);
        }
    }

    public void removeOldestBookCopy(BookTitle bookTitle, int amount) {
        for(int i = 0; i < amount; i++) {
            Book notBorrowedCopy = null;
            for (Book book : bookTitle.getCopies()) {
                if (!book.isBorrowed()) {
                    notBorrowedCopy = book;
                    break;
                }
            }
            if (notBorrowedCopy == null) {
                System.out.println("All copies of " + bookTitle.getTitle() + " are currently borrowed.");
                return;
            }
            bookTitle.removeCopy(notBorrowedCopy);
            this.books.remove(notBorrowedCopy);
        }
    }

    public void addBookTitle(BookTitle bookTitle) {
        this.books.addAll(bookTitle.getCopies());
    }

    public Book borrowProcess(BookTitle bookTitle, Client client) {
        // Find the first available copy of the book
        Book availableCopy = null;
        for (Book book : bookTitle.getCopies()) {
            if (!book.isBorrowed()) {
                availableCopy = book;
                break;
            }
        }
        // If no copy is available, print a message and exit the method
        if (availableCopy == null) {
            System.out.println("No copies of " + bookTitle.getTitle() + " are available.");
            return null;
        }
        // Otherwise, proceed with the borrowing process
        availableCopy.checkOutBook(client);
        client.addBorrowedBook(availableCopy);
        System.out.println("\"" + client.getName() +  "\" has borrowed a copy of \"" + bookTitle.getTitle() + "\".");
        return availableCopy;
    }



    public void returnProcess(BookTitle bookTitle, Client client) {
        // Find the first copy of the book that's borrowed by the client
        Book borrowedCopy = null;
        for (Book book : bookTitle.getCopies()) {
            if (book.isBorrowed() && book.getClient().equals(client)) {
                borrowedCopy = book;
                break;
            }
        }

        // If no borrowed copy by this client is found, print a message and exit
        if (borrowedCopy == null) {
            System.out.println(client.getName() + " did not borrow " + bookTitle.getTitle() + ".");
            return;
        }

        // Otherwise, proceed with the returning process
        borrowedCopy.returnBook(client);
        client.returnBook(borrowedCopy);
        System.out.println("\"" + client.getName() + "\" returned \"" + bookTitle.getTitle() + "\".");
    }



    public void listAvailableBooks() {
        System.out.println("These books are currently available: ");
        for (Book book : books) {
           if(!book.isBorrowed()) {
               System.out.println(book.getBookTitle().getTitle());
           }
        }
        System.out.println("\n");
    }

    public void listBorrowedBooks() {
        System.out.println("These books are currently borrowed: ");
        for (Book book : books) {
            if(book.isBorrowed()) {
                System.out.println("\"" + book.getBookTitle().getTitle() + "\" by Client \"" + book.getClient().getName() + "\"");
            }
        }
        System.out.println("\n");
    }

    public BookTitle searchTitle(String searchedTerm) {
        for (Book book : books) {
            if(book.getBookTitle().getTitle().toLowerCase().contains(searchedTerm.toLowerCase())) {
                System.out.println(String.format("Of the Title \"%s\" are %d copies available.", book.getBookTitle().getTitle(), book.getBookTitle().getCopies().size()));
                return book.getBookTitle();
                }
            }
        return null; //if nothing found
        }
}

// I/O .csv STUFF:

/*
    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // For each book in your library
            for (Book book : books) {
                // Save the book's data in CSV format
                writer.println(bookToCSV(book));
            }
            // Optionally, you can save client data too.
            for (Client client : clients) {
                // Save the client's data in CSV format
                writer.println(clientToCSV(client));
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

*/
   /*
    //OLD DESIGN

    public void borrowProcess(Book book, Client client) {
        if (!books.contains(book)) {
            System.out.println("This book is not part of this library.");
            return;
        }
        if (book.isBorrowed()) {
            System.out.println("This book is currently borrowed by someone else.");
            return;
        }
        book.checkOutBook(client);
        client.clientBorrowBook(book);
        System.out.println(client.getName() + " borrowed " + book.getBookTitle().getTitle());
    }

    // DIFFICULT NEW SYNTAX

    public void borrowProcess(BookTitle bookTitle, Client client) {
    // Find the first available copy of the book
    Optional<Book> availableCopy = bookTitle.getCopies().stream().filter(book -> !book.isBorrowed()).findFirst();

    if (!availableCopy.isPresent()) {
        System.out.println("All copies of this book are currently borrowed.");
        return;
    }

    Book bookToBorrow = availableCopy.get();
    bookToBorrow.checkOutBook(client);
    client.clientBorrowBook(bookToBorrow);
    System.out.println(client.getName() + " borrowed " + bookToBorrow.getBookTitle().getTitle());
}
     */
