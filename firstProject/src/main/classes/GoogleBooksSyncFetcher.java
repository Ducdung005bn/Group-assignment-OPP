package main.classes;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GoogleBooksSyncFetcher {
    /**
     * Fetches book details from Google Books API based on the given ISBN.
     *
     * @param isbn The ISBN of the book to fetch.
     * @return A Book object if the book exists; otherwise, returns a message "Book not found" in English.
     */
    public static Object fetchBook(String isbn) {
        // Create a single-threaded ExecutorService to handle the task
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Define a Callable task to fetch the book
        Callable<Object> task = () -> {
            try {
                // Use GoogleBooksAPI to fetch the book details
                Book book = GoogleBooksAPI.getBookFromISBN(isbn);
                if (book != null) {
                    return book; // Return the Book object if it exists
                } else {
                    return "Book not found"; // Return this message if the book is not found
                }
            } catch (IOException e) {
                // Return an error message if an exception occurs
                return "Error: " + e.getMessage();
            }
        };

        try {
            // Submit the task and get the result
            Object result = executorService.submit(task).get();
            return result; // Return the Book object or error message
        } catch (InterruptedException | ExecutionException e) {
            return "Error: " + e.getMessage(); // Handle exceptions during task execution
        } finally {
            // Shutdown the ExecutorService to release resources
            executorService.shutdown();
        }
    }
}