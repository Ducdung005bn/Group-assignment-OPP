package main.classes.other.opponents;

import main.classes.main.opponents.Book;
import okhttp3.OkHttpClient; // Manages HTTP connections and performs HTTP requests efficiently.
import okhttp3.Request; // Constructs an HTTP request with a URL and necessary parameters.
import okhttp3.Response; // Handles the server’s response, including status code and JSON content.
import java.io.IOException; // Exception for handling I/O errors.
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * GoogleBooksAPI is a utility class that uses Google Books API to search and retrieve book information
 * based on ISBN.
 */
public class GoogleBooksAPI {
    private static final String API_URL = "https://www.googleapis.com/books/v1/volumes?q=isbn:"; // Base URL for Google Books API.
    private static final String API_KEY = "AIzaSyB3ImOPrIkN0n1oWtMWE_KBErkoUELFRYY"; // API key for authentication.

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

    /**
     * Retrieves book information from Google Books API and maps it to a Book object.
     *
     * @param isbn The ISBN of the book to retrieve information for.
     * @return A Book object with the retrieved information.
     * @throws IOException If there is an error with network or server response.
     */
    private static Book getBookFromISBN(String isbn) throws IOException {
        String jsonResponse = searchByISBN(isbn);
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        if (!jsonObject.has("items")) {
            System.out.println("Book not found with ISBN: " + isbn);
            return null;
        }

        JsonObject bookJson = jsonObject.getAsJsonArray("items").get(0).getAsJsonObject();
        JsonObject volumeInfo = bookJson.getAsJsonObject("volumeInfo");

        Book book = new Book();
        book.setDocumentISBN(isbn);

        // Set title
        if (volumeInfo.has("title")) {
            book.setDocumentTitle(volumeInfo.get("title").getAsString());
        }

        // Set authors
        if (volumeInfo.has("authors")) {
            JsonArray authorsArray = volumeInfo.getAsJsonArray("authors");
            StringBuilder authors = new StringBuilder();
            for (JsonElement author : authorsArray) {
                authors.append(author.getAsString()).append(", ");
            }
            book.setDocumentAuthor(authors.toString().replaceAll(", $", ""));
        }

        // Set publisher
        if (volumeInfo.has("publisher")) {
            book.setBookPublisher(volumeInfo.get("publisher").getAsString());
        }

        // Set published date
        if (volumeInfo.has("publishedDate")) {
            book.setDocumentDescription("Published Date: " + volumeInfo.get("publishedDate").getAsString());
        }

        // Set description
        if (volumeInfo.has("description")) {
            book.setDocumentDescription(volumeInfo.get("description").getAsString());
        }

        // Set page count
        if (volumeInfo.has("pageCount")) {
            book.setDocumentPage(volumeInfo.get("pageCount").getAsInt());
        }

        // Set categories
        if (volumeInfo.has("categories")) {
            JsonArray categoriesArray = volumeInfo.getAsJsonArray("categories");
            StringBuilder categories = new StringBuilder();
            for (JsonElement category : categoriesArray) {
                categories.append(category.getAsString()).append(", ");
            }
            book.setBookGenre(categories.toString().replaceAll(", $", ""));
        }

        // Set language
        if (volumeInfo.has("language")) {
            book.setDocumentLanguage(volumeInfo.get("language").getAsString());
        }

        // Set image URL (if available)
        if (volumeInfo.has("imageLinks")) {
            JsonObject imageLinks = volumeInfo.getAsJsonObject("imageLinks");
            if (imageLinks.has("thumbnail")) {
                book.setDocumentImageUrl(imageLinks.get("thumbnail").getAsString());
            }
        }

        // Set link to Google Books page (infoLink or canonicalVolumeLink)
        if (volumeInfo.has("infoLink")) {
            book.setDocumentGoogleLink(volumeInfo.get("infoLink").getAsString());
        } else if (volumeInfo.has("canonicalVolumeLink")) {
            book.setDocumentGoogleLink(volumeInfo.get("canonicalVolumeLink").getAsString());
        }

        return book;
    }

    /**
     * Searches for a book using its ISBN through Google Books API.
     *
     * @param isbn The ISBN of the book to search for.
     * @return JSON response as a String containing book information.
     * @throws IOException If there is an error with network or server response.
     */
    private static String searchByISBN(String isbn) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Build URL with ISBN and API key
        String url = API_URL + isbn + "&key=" + API_KEY;

        // Create HTTP GET request
        Request request = new Request.Builder()
                .url(url)
                .build();

        // Execute request and receive response
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // Check if response body is not null
            if (response.body() != null) {
                return response.body().string(); // Return JSON string from the response
            } else {
                throw new IOException("Response body is null");
            }
        }
    }
}