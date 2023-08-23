package org.LibraryProject;


import java.util.ArrayList;
import java.util.List;

public class BookTitle {
    private String title;
    private String author;
    private String ISBN;
    private List<Book> copies = new ArrayList<>();


    public BookTitle(String title, String author, String ISBN, int totalAmount) {
        if (title == null) this.title = "not defined";
        else this.title = title;
        if (author == null) this.author = "not defined";
        else this.author = author;
        if (ISBN != null && ISBN.length() == 11) this.ISBN = ISBN;
        else throw new IllegalArgumentException("This is not a valid ISBN number!");

        for (int i = 0; i < totalAmount; i++) {
            copies.add(new Book(this));
        }
    }

    public List<Book> getCopies() {
        return copies;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getBorrowedCopies() {
        int count = 0;
        for (Book book : copies) {
            if (book.isBorrowed()) {
                count++;
            }
        }
        return count;
    }


    public Book addCopy() {
        Book newBook = new Book(this);
        copies.add(newBook);
        return newBook;
    }

    public void removeCopy(Book book) {
        copies.remove(book);
    }

    public int getAvailableCopies() {
        return copies.size() - getBorrowedCopies();
    }
}

