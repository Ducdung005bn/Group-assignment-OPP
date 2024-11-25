import main.classes.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import okhttp3.OkHttpClient; // Manages HTTP connections and performs HTTP requests efficiently.
import okhttp3.Request; // Constructs an HTTP request with a URL and necessary parameters.
import okhttp3.Response; // Handles the server’s response, including status code and JSON content.
import java.io.IOException; // Exception for handling I/O errors.
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import static org.junit.jupiter.api.Assertions.*;

class GoogleBooksSearchTaskTest {

    private GoogleBooksSearchTask task;

    @BeforeEach
    void setUp() {
        // Initialize a sample ISBN for testing
        task = new GoogleBooksSearchTask("9780596004651"); // Example ISBN
    }

    @AfterEach
    void tearDown() {
        // Clean up after each test if necessary
        task = null;
    }

    @Test
    void run() {
        // Run the task in a separate thread
        Thread thread = new Thread(task);
        thread.start();

        // Wait for the task to complete
        try {
            thread.join();
        } catch (InterruptedException e) {
            fail("Thread was interrupted: " + e.getMessage());
        }

        // Verify that the result is not null
        assertNotNull(task.getResult(), "Expected a valid Book object, but got null");
    }

    @Test
    void getResult() {
        // Run the task to retrieve the result
        Thread thread = new Thread(task);
        thread.start();

        // Wait for the task to complete
        try {
            thread.join();
        } catch (InterruptedException e) {
            fail("Thread was interrupted: " + e.getMessage());
        }

        // Verify that the result contains the expected information
        Book result = task.getResult();
        assertNotNull(result, "Expected a valid Book object, but got null");
        assertEquals("9780596004651", result.getDocumentISBN(), "ISBN does not match");
        // Add additional checks for other attributes of Book if necessary
    }
}
