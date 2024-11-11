import okhttp3.OkHttpClient; // Manages HTTP connections and performs HTTP requests efficiently.
import okhttp3.Request; // Constructs an HTTP request with a URL and necessary parameters.
import okhttp3.Response; // Handles the server’s response, including status code and JSON content.
import java.io.IOException; // Exception for handling I/O errors.
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
     * Searches for a book using its ISBN through Google Books API.
     *
     * @param isbn The ISBN of the book to search for.
     * @return JSON response as a String containing book information.
     * @throws IOException If there is an error with network or server response.
     */
    public static String searchByISBN(String isbn) throws IOException {
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

    /**
     * Retrieves and prints book information based on ISBN.
     *
     * @param isbn The ISBN of the book to retrieve and print information for.
     * @throws IOException If there is an error with network or server response.
     */
    public static void printBookInfo(String isbn) throws IOException {
        // Call searchByISBN to get JSON data from the API
        String jsonResponse = searchByISBN(isbn);
        System.out.println("JSON Response: " + jsonResponse);

        // Parse JSON to extract book information
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        if (jsonObject.has("items")) {
            JsonObject book = jsonObject.getAsJsonArray("items").get(0).getAsJsonObject();
            JsonObject volumeInfo = book.getAsJsonObject("volumeInfo");

            // Extract and print title
            String title = volumeInfo.has("title") ? volumeInfo.get("title").getAsString() : "N/A";
            System.out.println("Title: " + title);

            // Extract and print authors
            if (volumeInfo.has("authors")) {
                JsonArray authorsArray = volumeInfo.getAsJsonArray("authors");
                System.out.print("Authors: ");
                for (JsonElement author : authorsArray) {
                    System.out.print(author.getAsString() + " ");
                }
                System.out.println();
            } else {
                System.out.println("Authors: N/A");
            }

            // Extract and print publisher
            String publisher = volumeInfo.has("publisher") ? volumeInfo.get("publisher").getAsString() : "N/A";
            System.out.println("Publisher: " + publisher);

            // Extract and print published date
            String publishedDate = volumeInfo.has("publishedDate") ? volumeInfo.get("publishedDate").getAsString() : "N/A";
            System.out.println("Published Date: " + publishedDate);

            // Extract and print description
            String description = volumeInfo.has("description") ? volumeInfo.get("description").getAsString() : "N/A";
            System.out.println("Description: " + description);

            // Extract and print page count
            int pageCount = volumeInfo.has("pageCount") ? volumeInfo.get("pageCount").getAsInt() : 0;
            System.out.println("Page Count: " + pageCount);

            // Extract and print categories
            if (volumeInfo.has("categories")) {
                JsonArray categoriesArray = volumeInfo.getAsJsonArray("categories");
                System.out.print("Categories: ");
                for (JsonElement category : categoriesArray) {
                    System.out.print(category.getAsString() + " ");
                }
                System.out.println();
            } else {
                System.out.println("Categories: N/A");
            }

            // Extract and print average rating
            double averageRating = volumeInfo.has("averageRating") ? volumeInfo.get("averageRating").getAsDouble() : 0.0;
            System.out.println("Average Rating: " + averageRating);

            // Extract and print ratings count
            int ratingsCount = volumeInfo.has("ratingsCount") ? volumeInfo.get("ratingsCount").getAsInt() : 0;
            System.out.println("Ratings Count: " + ratingsCount);

            // Extract and print ISBNs (if available)
            if (volumeInfo.has("industryIdentifiers")) {
                JsonArray identifiers = volumeInfo.getAsJsonArray("industryIdentifiers");
                for (JsonElement identifier : identifiers) {
                    JsonObject idObj = identifier.getAsJsonObject();
                    String type = idObj.has("type") ? idObj.get("type").getAsString() : "N/A";
                    String id = idObj.has("identifier") ? idObj.get("identifier").getAsString() : "N/A";
                    System.out.println(type + ": " + id);
                }
            }

            // Extract and print language
            String language = volumeInfo.has("language") ? volumeInfo.get("language").getAsString() : "N/A";
            System.out.println("Language: " + language);

        } else {
            System.out.println("Book not found with ISBN: " + isbn);
        }
    }
}
