package org.LibraryProject;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("My Library Project: \n");

        Library myLibrary = new Library();

        BookTitle harryPotter = new BookTitle("Harry Potter und der Stein der Weisen", "J.K. Rowling", "12345678901", 20);
        BookTitle verwandlung = new BookTitle("Die Verwandlung", "Franz Kafka", "12345679802", 4);

        Client phil = new Client("Phil", "phliwas@gmail.com");
        Client dina = new Client("Dina", "dina@gmail.com");

        //add the book copies to the library system list (before theyre just floating around in memory
        myLibrary.addBookTitle(harryPotter);
        myLibrary.addBookTitle(verwandlung);

        System.out.println("First status:\n");
        myLibrary.listAvailableBooks();
        myLibrary.listBorrowedBooks();

        myLibrary.borrowProcess(verwandlung,phil);
        myLibrary.borrowProcess(verwandlung, dina);
        myLibrary.borrowProcess(harryPotter,phil);
        myLibrary.borrowProcess(verwandlung,phil);

        System.out.println("Second status:\n");
        myLibrary.listAvailableBooks();
        myLibrary.listBorrowedBooks();


        myLibrary.returnProcess(verwandlung,phil);
        myLibrary.returnProcess(verwandlung,phil);



        System.out.println("Third status:\n");
        myLibrary.listAvailableBooks();
        myLibrary.listBorrowedBooks();


        myLibrary.addBookCopy(verwandlung, 10);


        System.out.println("Fourth status:\n");
        myLibrary.listAvailableBooks();
        myLibrary.listBorrowedBooks();



        myLibrary.removeOldestBookCopy(verwandlung,10);


        System.out.println("Fifth status:\n");
        myLibrary.listAvailableBooks();
        myLibrary.listBorrowedBooks();

        myLibrary.removeOldestBookCopy(harryPotter,15);
        myLibrary.searchTitle("Die Verwandlung");
        myLibrary.searchTitle("Harry Potter");

        JFrame frame = new JFrame();
        JButton button = new JButton("click me");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(button);
        frame.setSize(300,300);
        frame.setVisible(true);
    }
}