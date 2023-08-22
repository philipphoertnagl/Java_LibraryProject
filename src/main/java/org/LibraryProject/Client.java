package org.LibraryProject;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private String email;
    private int clientID;
    private List<Book> borrowedBooks;

    private static int clientIDCounter = 1;
    public Client(String name, String email) {
        if (name == null) this.name = "not defined";
        else this.name = name;
        if (email == null) this.email = "not defined";
        else this.email = email;

        this.clientID = clientIDCounter;
        clientIDCounter++;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // Add a borrowed book to the list
    public void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }

    // Remove a returned book from the list
    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    // List all borrowed books by this client
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

}
