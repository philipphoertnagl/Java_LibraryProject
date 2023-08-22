import java.io.*;
import java.util.*;

public class Library {
    // ... other members ...

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

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse the line to reconstruct the book or client
                // Here's a simplistic example, you'll need to adapt:
                String[] parts = line.split(",");
                if (parts[0].equals("Book")) {
                    // Create a new book from the CSV parts and add to the library
                    books.add(csvToBook(parts));
                } else if (parts[0].equals("Client")) {
                    // Create a new client from the CSV parts and add to the library
                    clients.add(csvToClient(parts));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    private String bookToCSV(Book book) {
        // Convert a book to CSV format
        // This is a basic example, adjust as needed
        return "Book," + book.getBookTitle().getTitle() + "," + book.isBorrowed();
        // ... add more details as necessary
    }

    private Book csvToBook(String[] parts) {
        // Convert a CSV string to a Book object
        // This is a basic example, adjust as needed
        BookTitle title = new BookTitle(parts[1], /* ... other parameters ... */);
        Book book = new Book(title);
        // ... set other properties of the book from the CSV
        return book;
    }

    private String clientToCSV(Client client) {
        // Convert a client to CSV format
        // This is a basic example, adjust as needed
        return "Client," + client.getName() + "," + client.getEmail();
    }

    private Client csvToClient(String[] parts) {
        // Convert a CSV string to a Client object
        return new Client(parts[1], parts[2]);
    }
}
