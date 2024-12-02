package library.management.system;

import main.classes.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ReturnDocumentControllerTest {
    private JPanel jpnView;
    private Borrower borrower;
    private LibraryManagementSystem libraryManagementSystem;
    private final String[] COLUMNS = {"Borrower ID", "Book ISBN", "Borrow Date", "Planned Return Date", "Status", "Return"};
    private DefaultTableModel model;
    private ReturnDocumentController controller;

    @BeforeEach
    void setUp() {
        // Khởi tạo hệ thống quản lý thư viện
        libraryManagementSystem = TestDataInitializer.initializeLibraryData();

        // Chọn một borrower để test
        borrower = libraryManagementSystem.borrowerList.get(0);

        // Tạo JPanel và model
        jpnView = new JPanel();
        model = new DefaultTableModel(COLUMNS, 0);
        JTable table = new JTable(model);
        jpnView.add(table);

        // Tạo controller
        controller = new ReturnDocumentController(jpnView, borrower, libraryManagementSystem);
    }

    @AfterEach
    void tearDown() {
        // Làm sạch dữ liệu sau mỗi test
        libraryManagementSystem = null;
        borrower = null;
        controller = null;
    }

    @Test
    void testReturnDocument() {
        // Tạo một Document để mượn
        Document testDocument = libraryManagementSystem.bookList.get(0);

        // Tạo ngày mượn và ngày trả dự kiến
        Date currentDate = new Date();
        Date borrowDate = new Date(currentDate.getTime() - 7 * 24 * 60 * 60 * 1000); // 7 ngày trước
        Date plannedReturnDate = new Date(currentDate.getTime() + 7 * 24 * 60 * 60 * 1000); // 7 ngày sau

        // Tạo BorrowData
        BorrowData borrowData = new BorrowData();
        borrowData.setBorrowerID(borrower.getUserID());
        borrowData.setBorrowedBookISBN(testDocument.getDocumentISBN());
        borrowData.setBorrowDate(borrowDate);
        borrowData.setPlannedReturnDate(plannedReturnDate);
        borrowData.setBorrowStatus("Not Returned");

        // Thêm BorrowData vào danh sách đang mượn của borrower
        borrower.borrowingHistory.add(borrowData);

        // Thực hiện trả sách
        controller.returnDocument(borrowData);

        // Kiểm tra các điều kiện sau khi trả sách
        // 1. Kiểm tra borrowingHistory
        assertFalse(borrower.borrowingHistory.contains(borrowData),
                "Sách phải được loại khỏi danh sách đang mượn");

        // 2. Kiểm tra borrowedHistory
        assertTrue(borrower.borrowedHistory.contains(borrowData),
                "Sách phải được thêm vào lịch sử đã trả");

        // 3. Kiểm tra trạng thái của BorrowData
        assertEquals("Returned", borrowData.getBorrowStatus(),
                "Trạng thái của BorrowData phải là 'Returned'");

        // 4. Kiểm tra số lượng sách trong thư viện
        Document returnedDocument = libraryManagementSystem.findDocumentByISBN(testDocument.getDocumentISBN());
        assertNotNull(returnedDocument, "Sách phải được tìm thấy trong thư viện");

        // 5. Kiểm tra số lượng sách có sẵn đã tăng
        int expectedAvailableQuantity = returnedDocument.getDocumentQuantityAll() + 1;
        assertEquals(expectedAvailableQuantity, returnedDocument.getDocumentQuantityAll(),
                "Số lượng sách có sẵn phải tăng sau khi trả");
    }

    @Test
    void testReturnOverdueDocument() {
        // Tạo một Document để mượn
        Document testDocument = libraryManagementSystem.bookList.get(0);

        // Tạo ngày mượn và ngày trả dự kiến (quá hạn)
        Date currentDate = new Date();
        Date borrowDate = new Date(currentDate.getTime() - 14 * 24 * 60 * 60 * 1000); // 14 ngày trước
        Date plannedReturnDate = new Date(currentDate.getTime() - 7 * 24 * 60 * 60 * 1000); // 7 ngày trước

        // Tạo BorrowData
        BorrowData overdueData = new BorrowData();
        overdueData.setBorrowerID(borrower.getUserID());
        overdueData.setBorrowedBookISBN(testDocument.getDocumentISBN());
        overdueData.setBorrowDate(borrowDate);
        overdueData.setPlannedReturnDate(plannedReturnDate);
        overdueData.setBorrowStatus("Overdue");

        // Thêm BorrowData vào danh sách đang mượn của borrower
        borrower.borrowingHistory.add(overdueData);

        // Thực hiện trả sách
        controller.returnDocument(overdueData);

        // Kiểm tra các điều kiện cho sách quá hạn
        assertEquals("Returned (Overdue)", overdueData.getBorrowStatus(),
                "Trạng thái của sách quá hạn phải là 'Returned (Overdue)'");
    }
    @Test
    void testDuplicateBorrowDocument() {
        // Tạo một Document để mượn
        Document testDocument = libraryManagementSystem.bookList.get(0);

        // Tạo ngày mượn và ngày trả dự kiến
        Date currentDate = new Date();
        Date borrowDate1 = new Date(currentDate.getTime() - 7 * 24 * 60 * 60 * 1000); // 7 ngày trước
        Date plannedReturnDate1 = new Date(currentDate.getTime() + 7 * 24 * 60 * 60 * 1000); // 7 ngày sau

        // Tạo BorrowData đầu tiên
        BorrowData borrowData1 = new BorrowData();
        borrowData1.setBorrowerID(borrower.getUserID());
        borrowData1.setBorrowedBookISBN(testDocument.getDocumentISBN());
        borrowData1.setBorrowDate(borrowDate1);
        borrowData1.setPlannedReturnDate(plannedReturnDate1);
        borrowData1.setBorrowStatus("Not Returned");

        // Thêm BorrowData đầu tiên vào danh sách đang mượn của borrower
        borrower.borrowingHistory.add(borrowData1);

        // Tạo BorrowData thứ hai (trùng lặp)
        BorrowData borrowData2 = new BorrowData();
        borrowData2.setBorrowerID(borrower.getUserID());
        borrowData2.setBorrowedBookISBN(testDocument.getDocumentISBN());
        borrowData2.setBorrowDate(borrowDate1);
        borrowData2.setPlannedReturnDate(plannedReturnDate1);
        borrowData2.setBorrowStatus("Not Returned");

        // Kiểm tra việc thêm sách trùng lặp
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            // Giả sử có một phương thức kiểm tra và ngăn chặn việc mượn sách trùng lặp
            controller.returnDocument(borrowData2);
        });

        // Kiểm tra thông báo lỗi
        assertEquals("This book is already borrowed by the user", exception.getMessage());

        // Kiểm tra số lượng sách trong danh sách đang mượn
        assertEquals(1, borrower.borrowingHistory.stream()
                .filter(data -> data.getBorrowedBookISBN().equals(testDocument.getDocumentISBN()))
                .count(), "Chỉ được phép mượn một quyển sách duy nhất");
    }
}
