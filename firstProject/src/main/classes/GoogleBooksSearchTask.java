import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Scanner;

public class GoogleBooksSearchTask implements Runnable {
    private String isbn;

    public GoogleBooksSearchTask(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread: " + Thread.currentThread().getName() + " is fetching book with ISBN: " + isbn);
            GoogleBooksAPI.printBookInfo(isbn); // Gọi phương thức printBookInfo từ lớp GoogleBooksAPI
        } catch (IOException e) {
            System.err.println("Error fetching book with ISBN " + isbn + ": " + e.getMessage());
        }
        ;
    }
}