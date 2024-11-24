package main.classes;
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
        // Khởi tạo một ISBN mẫu cho việc kiểm thử
        task = new GoogleBooksSearchTask("9780596004651"); // ISBN ví dụ
    }

    @AfterEach
    void tearDown() {
        // Dọn dẹp sau mỗi kiểm thử nếu cần
        task = null;
    }

    @Test
    void run() {
        // Chạy tác vụ trong một luồng riêng
        Thread thread = new Thread(task);
        thread.start();

        // Chờ để tác vụ hoàn tất
        try {
            thread.join();
        } catch (InterruptedException e) {
            fail("Thread was interrupted: " + e.getMessage());
        }

        // Kiểm tra rằng kết quả không phải là null
        assertNotNull(task.getResult(), "Expected a valid Book object, but got null");
    }

    @Test
    void getResult() {
        // Chạy tác vụ để lấy kết quả
        Thread thread = new Thread(task);
        thread.start();

        // Chờ để tác vụ hoàn tất
        try {
            thread.join();
        } catch (InterruptedException e) {
            fail("Thread was interrupted: " + e.getMessage());
        }

        // Kiểm tra rằng kết quả có thông tin mong đợi
        Book result = task.getResult();
        assertNotNull(result, "Expected a valid Book object, but got null");
        assertEquals("9780596004651", result.getDocumentISBN(), "ISBN does not match");
        // Thêm các kiểm tra khác cho các thuộc tính của Book nếu cần
    }
}