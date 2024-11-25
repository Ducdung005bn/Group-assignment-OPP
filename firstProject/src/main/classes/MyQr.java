package main.classes;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * Utility class for generating QR codes for library documents.
 * Supports creating QR codes with detailed information about books, theses, or magazines.
 */
public class MyQr {

    // Constants for QR code dimensions.
    public static final int QR_IMAGE_HEIGHT = 200;
    public static final int QR_IMAGE_WIDTH = 200;

    /**
     * Generates a string containing detailed information about a document,
     * which will be encoded in the QR code.
     *
     * @param document the document whose information will be used.
     * @return a formatted string containing all relevant details of the document.
     */
    public static String fullFillInformationQR(Document document) {
        // Initialize the string with basic document information.
        String fullFillInformation = "ISBN: " + document.getDocumentISBN();
        fullFillInformation += "\nQuantity: " + document.documentQuantity;
        fullFillInformation += "\nTitle: " + document.getDocumentTitle();
        fullFillInformation += "\nAuthor: " + document.getDocumentAuthor();
        fullFillInformation += "\nDescription: " + document.getDocumentDescription();
        fullFillInformation += "\nLanguage: " + document.getDocumentLanguage();
        fullFillInformation += "\nPage: " + document.getDocumentPage();

        // Add specific information based on document type.
        if (document instanceof Book) {
            Book book = (Book) document;
            fullFillInformation += "\nGenre: " + book.getBookGenre();
            fullFillInformation += "\nPublisher: " + book.getBookPublisher();
        } else if (document instanceof Thesis) {
            Thesis thesis = (Thesis) document;
            fullFillInformation += "\nSubject: " + thesis.getThesisSubject();
            fullFillInformation += "\nDegree: " + thesis.getThesisDegree();
            fullFillInformation += "\nUniversity: " + thesis.getThesisUniversity();
        } else if (document instanceof Magazine) {
            Magazine magazine = (Magazine) document;
            fullFillInformation += "\nSubject: " + magazine.getMagazineSubject();
            fullFillInformation += "\nFrequency: " + magazine.getMagazineFrequency();
            fullFillInformation += "\nIssue Number: " + magazine.getMagazineIssueNumb();
        }

        return fullFillInformation;
    }

    /**
     * Generates a QR code image for a given document and saves it as a file.
     *
     * @param documentQR the document for which the QR code is generated.
     */
    public void generateQRDocument(Document documentQR) {
        // Get the detailed information of the document as a string.
        String data = MyQr.fullFillInformationQR(documentQR);

        // Define the file path for the QR code image.
        String path = "qr_" + documentQR.getDocumentISBN() + ".png";

        // Define the character set and error correction level.
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        try {
            // Generate and save the QR code.
            MyQr.createQR(data, path, charset, hashMap, MyQr.QR_IMAGE_HEIGHT, MyQr.QR_IMAGE_WIDTH);
            System.out.println("QR Code Generated for document ISBN: " + documentQR.getDocumentISBN());
        } catch (Exception e) {
            // Handle errors during QR code generation.
            System.err.println("Failed to generate QR Code: " + e.getMessage());
        }
    }

    /**
     * Creates a QR code image based on the provided data and saves it to the specified file path.
     *
     * @param data    the content to be encoded in the QR code.
     * @param path    the file path where the QR code image will be saved.
     * @param charset the character set used to encode the data.
     * @param hashMap the QR code settings, including error correction level.
     * @param height  the height of the QR code image.
     * @param width   the width of the QR code image.
     * @throws WriterException if an error occurs during the QR code writing process.
     * @throws IOException     if an error occurs during file writing.
     */
    public static void createQR(String data, String path,
                                String charset, Map<EncodeHintType, ErrorCorrectionLevel> hashMap,
                                int height, int width) throws WriterException, IOException {

        // Encode the data into a QR code bit matrix.
        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(data.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, width, height);

        // Save the QR code image to the specified file path.
        MatrixToImageWriter.writeToFile(
                matrix,
                path.substring(path.lastIndexOf('.') + 1),
                new File(path));
    }
}
