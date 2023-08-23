package org.LibraryProject;

import org.LibraryProject.Book;
import org.LibraryProject.BookTitle;
import org.LibraryProject.Client;

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
        for (int i = 0; i < amount; i++) {
            Book newCopy = bookTitle.addCopy();
            this.books.add(newCopy);
        }
    }

    public void removeOldestBookCopy(BookTitle bookTitle, int amount) {
        for (int i = 0; i < amount; i++) {
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
        addClient(client);
        System.out.println("\"" + client.getName() + "\" has borrowed a copy of \"" + bookTitle.getTitle() + "\".");
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
        addClient(client);
        System.out.println("\"" + client.getName() + "\" returned \"" + bookTitle.getTitle() + "\".");
    }


    public void listAvailableBooks() {
        System.out.println("These books are currently available: ");
        for (Book book : books) {
            if (!book.isBorrowed()) {
                System.out.println(book.getBookTitle().getTitle());
            }
        }
        System.out.println("\n");
    }

    public void listBorrowedBooks() {
        System.out.println("These books are currently borrowed: ");
        for (Book book : books) {
            if (book.isBorrowed()) {
                System.out.println("\"" + book.getBookTitle().getTitle() + "\" by Client \"" + book.getClient().getName() + "\"");
            }
        }
        System.out.println("\n");
    }

    public BookTitle searchTitle(String searchedTerm) {
        for (Book book : books) {
            if (book.getBookTitle().getTitle().toLowerCase().contains(searchedTerm.toLowerCase())) {
                System.out.println(String.format("Of the Title \"%s\" are %d copies available.", book.getBookTitle().getTitle(), book.getBookTitle().getCopies().size()));
                return book.getBookTitle();
            }
        }
        return null; //if nothing found
    }

    public void addClient(Client client) {
        if (!clients.contains(client)) {
            clients.add(client);
        }
    }




    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Client client : clients) {
                String escapedName = client.getName().replace(",", "\\,");
                writer.write("Client," + escapedName + "," + client.getEmail() + "," + client.getClientID());
                writer.newLine();
            }

            for (Book book : books) {
                String escapedTitle = book.getBookTitle().getTitle().replace(",", "\\,");
                writer.write("Book," + escapedTitle + "," +
                        book.getBookTitle().getAuthor() + "," +
                        book.getBookTitle().getISBN() + "," +
                        book.getBookID());
                writer.newLine();
            }
            // Extend this for other data as necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("Client")) {
                    String originalName = parts[1].replace("\\,", ",");
                    Client client = new Client(originalName, parts[2]);
                    this.clients.add(client);
                } else if (parts[0].equals("Book")) {
                    String originalTitle = parts[1].replace("\\,", ",");
                    BookTitle bookTitle = new BookTitle(originalTitle, parts[2], parts[3], 1); // assuming 1 copy for simplicity
                    this.addBookTitle(bookTitle); // This adds the book to the library
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}