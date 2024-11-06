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

public class MyQr {
    public static final int QR_IMAGE_HEIGHT = 200;
    public static final int QR_IMAGE_WIDTH = 200;

    public static String fullFillInformationQR(Document document) {
        String fullFillInformation = "ISBN: " + document.getDocumentISBN();

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

    // Function to create the QR code
    public static void createQR(String data, String path,
            String charset, Map hashMap,
            int height, int width)
            throws WriterException, IOException {

        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(data.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, width, height);

        MatrixToImageWriter.writeToFile(
                matrix,
                path.substring(path.lastIndexOf('.') + 1),
                new File(path));
    }

    public static void main(String[] args)
            throws WriterException, IOException,
            NotFoundException {

        // The data that the QR code will contain
        String data = "www.geeksforgeeks.org";

        // The path where the image will get saved
        String path = "demo.png";

        // Encoding charset
        String charset = "UTF-8";

        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();

        hashMap.put(EncodeHintType.ERROR_CORRECTION,
                ErrorCorrectionLevel.L);

        // Create the QR code and save
        // in the specified folder
        // as a jpg file
        createQR(data, path, charset, hashMap, 200, 200);
        System.out.println("QR Code Generated!!! ");
    }
}
