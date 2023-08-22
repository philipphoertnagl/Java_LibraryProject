package org.LibraryProject;

import java.time.LocalDateTime;

public class Book {
    private BookTitle bookTitle;
    private int bookID = 0;
    private LocalDateTime dueTime;
    private boolean isBorrowed;
    private Client client;

    private static int bookIDCounter = 1;
    public Book(BookTitle bookTitle) {
        this.bookTitle = bookTitle;
        this.bookID = bookIDCounter;
        bookIDCounter++;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public Client getClient() {
        return client;
    }

    public BookTitle getBookTitle() {
        return bookTitle;
    }

    protected void checkOutBook(Client client) {
        if (this.isBorrowed) {
            System.out.println("Book at the moment not available");
            return;
        }
        else {

        this.isBorrowed = true;
        this.dueTime = LocalDateTime.now().plusDays(3); //sets the due time to + 3 days
        this.client = client;

        }
    }

    protected void returnBook(Client client) {
        if(this.client != client) {
            System.out.println("This client has not borrowed this book");
            return;
        }

        this.isBorrowed = false;
        this.dueTime = null;
        this.client = null;


    }
}
