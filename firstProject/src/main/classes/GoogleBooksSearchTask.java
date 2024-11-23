package main.classes;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Scanner;

import java.io.IOException;

public class GoogleBooksSearchTask implements Runnable {
    private String isbn;
    private Book result; // Store the resulting Book object

    public GoogleBooksSearchTask(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public void run() {
        try {
            // Fetch the book information from the API
            result = GoogleBooksAPI.getBookFromISBN(isbn);
        } catch (IOException e) {
            System.err.println("Error fetching book with ISBN " + isbn + ": " + e.getMessage());
        }
    }

    public Book getResult() {
        return result; // Return the fetched Book object
    }
}