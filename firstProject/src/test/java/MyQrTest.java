import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.classes.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import static org.junit.jupiter.api.Assertions.*;

class MyQrTest {
    private MyQr myQr;
    private Book book;
    private Thesis thesis;
    private Magazine magazine;

    @BeforeEach
    void setUp() {
        myQr = new MyQr();

        // Create a Book
        book = new Book();
        book.setDocumentISBN("123456789");
        book.documentQuantity = 10;
        book.setDocumentTitle("Sample Book");
        book.setDocumentAuthor("John Doe");
        book.setDocumentDescription("A sample book for testing.");
        book.setDocumentLanguage("English");
        book.setDocumentPage(200);
        book.setBookGenre("Fiction");
        book.setBookPublisher("Penguin Books");

        // Create a Thesis
        thesis = new Thesis();
        thesis.setDocumentISBN("987654321");
        thesis.documentQuantity = 1;
        thesis.setDocumentTitle("Thesis Title");
        thesis.setDocumentAuthor("Jane Doe");
        thesis.setDocumentDescription("A thesis on computer science.");
        thesis.setDocumentLanguage("English");
        thesis.setDocumentPage(150);
        thesis.setThesisSubject("Computer Science");
        thesis.setThesisDegree("Master");
        thesis.setThesisUniversity("Harvard University");

        // Create a Magazine
        magazine = new Magazine();
        magazine.setDocumentISBN("456789123");
        magazine.documentQuantity = 5;
        magazine.setDocumentTitle("Tech Magazine");
        magazine.setDocumentAuthor("Tech Author");
        magazine.setDocumentDescription("A magazine about technology.");
        magazine.setDocumentLanguage("English");
        magazine.setDocumentPage(50);
        magazine.setMagazineSubject("Technology");
        magazine.setMagazineFrequency(1);
        magazine.setMagazineIssueNumb(5);
    }

    @AfterEach
    void tearDown() {
        myQr = null;
        book = null;
        thesis = null;
        magazine = null;
    }

    @Test
    void fullFillInformationQR() {
        String expectedBookInfo = "ISBN: 123456789\nQuantity: 10\nTitle: Sample Book\nAuthor: John Doe\nDescription: A sample book for testing.\nLanguage: English\nPage: 200\nGenre: Fiction\nPublisher: Penguin Books";
        assertEquals(expectedBookInfo, MyQr.fullFillInformationQR(book));

        String expectedThesisInfo = "ISBN: 987654321\nQuantity: 1\nTitle: Thesis Title\nAuthor: Jane Doe\nDescription: A thesis on computer science.\nLanguage: English\nPage: 150\nSubject: Computer Science\nDegree: Master\nUniversity: Harvard University";
        assertEquals(expectedThesisInfo, MyQr.fullFillInformationQR(thesis));

        String expectedMagazineInfo = "ISBN: 456789123\nQuantity: 5\nTitle: Tech Magazine\nAuthor: Tech Author\nDescription: A magazine about technology.\nLanguage: English\nPage: 50\nSubject: Technology\nFrequency: 1\nIssue Number: 5";
        assertEquals(expectedMagazineInfo, MyQr.fullFillInformationQR(magazine));
    }

    @Test
    void generateQRDocument() {
        assertDoesNotThrow(() -> myQr.generateQRDocument(book));
        assertDoesNotThrow(() -> myQr.generateQRDocument(thesis));
        assertDoesNotThrow(() -> myQr.generateQRDocument(magazine));
    }

    @Test
    void createQR() {
        String data = "ISBN: 123456789\nGenre: Fiction\nPublisher: Penguin Books";
        String path = "test_qr.png";
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        assertDoesNotThrow(() -> MyQr.createQR(data, path, charset, hashMap, MyQr.QR_IMAGE_HEIGHT, MyQr.QR_IMAGE_WIDTH));

        File qrFile = new File(path);
        assertTrue(qrFile.exists());

        qrFile.delete();
    }
}