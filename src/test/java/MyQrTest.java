import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.L;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyQrTest {
    @TempDir
    Path tempDir;

    @Test
    void testGenerateQRDocument() throws IOException {
        // Create a sample Document object
        Document book = new Book("9780123456789", "Book Title", "Author", "Publisher");

        // Generate the QR code
        MyQr.generateQRDocument(book);

        // Check if the QR code file was created
        File qrFile = new File(tempDir.toFile(), "qr_9780123456789.png");
        assertTrue(qrFile.exists());

        // Verify the content of the QR code file
        byte[] qrBytes = Files.readAllBytes(qrFile.toPath());
        assertTrue(qrBytes.length > 0);
    }

    @Test
    void testCreateQR() throws IOException {
        String data = "Hello, World!";
        String path = tempDir.resolve("test_qr.png").toString();
        String charset = "UTF-8";
        Map<com.google.zxing.EncodeHintType, Object> hints = new HashMap<>();
        hints.put(com.google.zxing.EncodeHintType.ERROR_CORRECTION, L);

        MyQr.createQR(data, path, charset, hints, 200, 200);

        File qrFile = new File(path);
        assertTrue(qrFile.exists());

        byte[] qrBytes = Files.readAllBytes(qrFile.toPath());
        assertTrue(qrBytes.length > 0);
    }
}
