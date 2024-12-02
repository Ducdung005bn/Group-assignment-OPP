import library.management.system.*;
import main.classes.other.opponents.*;
import main.classes.main.opponents.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class DisplayUserInfoControllerTest {
    private JPanel jpnUserInfo;
    private JPanel jpnHistory;
    private Borrower borrower;
    private Librarian librarian;
    private LibraryManagementSystem libraryManagementSystem;

    @BeforeEach
    void setUp() {
        // Khởi tạo các đối tượng cần thiết
        jpnUserInfo = new JPanel();
        jpnHistory = new JPanel();
        // Giả sử TestDataInitializer là một lớp bạn đã tạo để khởi tạo dữ liệu thử nghiệm
        TestDataInitializer TestDataInitializer = null;
        libraryManagementSystem = TestDataInitializer.initializeLibraryData();
        borrower = libraryManagementSystem.borrowerList.get(0);
        librarian = libraryManagementSystem.librarianList.get(2);

        // Khởi tạo controller với người mượn

    }

    @AfterEach
    void tearDown() {
        // Dọn dẹp sau mỗi bài kiểm tra
        jpnUserInfo.removeAll();
        jpnHistory.removeAll();
    }

    @Test
    void testDisplayUserInfoForBorrower() {
        new DisplayUserInfoController(jpnUserInfo, jpnHistory, borrower, libraryManagementSystem);
        // Kiểm tra thông tin hiển thị cho người mượn
        assertEquals( borrower.getUserID() +"", ((JLabel) jpnUserInfo.getComponent(1)).getText());
        assertEquals(borrower.getUserName(), ((JLabel) jpnUserInfo.getComponent(3)).getText());
        assertEquals(borrower.getUserDateOfBirth().toString(), ((JLabel) jpnUserInfo.getComponent(5)).getText());
        assertEquals(borrower.getUserEmailAddress(), ((JLabel) jpnUserInfo.getComponent(7)).getText());
        assertEquals((borrower.getIsStudent() ? "Yes" : "No"), ((JLabel) jpnUserInfo.getComponent(9)).getText());
        assertEquals(borrower.getOverdueCount() +"", ((JLabel) jpnUserInfo.getComponent(11)).getText());
//        assertEquals("Password: " + "*".repeat(borrower.getUserPassword().length()), ((JLabel) jpnUserInfo.getComponent(13)).getText());
    }

    @Test
    void testDisplayBorrowerHistory() {
//        // Giả sử bạn đã thêm một số lịch sử mượn vào borrower
////        borrower.addBorrowData("123456789", "2024-01-01", "2024-01-15", "Borrowed", 5);
//        BorrowData borrowData = new BorrowData();
//        borrowData.setBorrowedBookISBN("123456789");
////        borrowData.setBorrowDate("2024-01-01");
////        borrowData.setPlannedReturnDate("2024-01-15");
//        borrowData.setBorrowStatus("Borrowed");
//        borrower.borrowedHistory.add(borrowData);
        new DisplayUserInfoController(jpnUserInfo, jpnHistory, borrower, libraryManagementSystem);

        // Kiểm tra bảng lịch sử mượn
        JTable historyTable = (JTable) ((JScrollPane) jpnHistory.getComponent(0)).getViewport().getView();
        assertEquals("123456789", historyTable.getValueAt(0, 0));
        assertEquals("Borrowed", historyTable.getValueAt(0, 3));
        assertEquals(5, historyTable.getValueAt(0, 4));
    }

    @Test
    void testDisplayUserInfoForLibrarian() {
        // Khởi tạo controller với thủ thư
        new DisplayUserInfoController(jpnUserInfo, jpnHistory, librarian, libraryManagementSystem);

        // Kiểm tra thông tin hiển thị cho thủ thư
        assertEquals( librarian.getUserID() + "", ((JLabel) jpnUserInfo.getComponent(1)).getText());
        assertEquals( librarian.getUserName() +"", ((JLabel) jpnUserInfo.getComponent(3)).getText());
        assertEquals( librarian.getUserDateOfBirth().toString(), ((JLabel) jpnUserInfo.getComponent(5)).getText());
        assertEquals( librarian.getUserEmailAddress(), ((JLabel) jpnUserInfo.getComponent(7)).getText());
        assertEquals( librarian.getLibrarianSalary() + " dollars per month", ((JLabel) jpnUserInfo.getComponent(9)).getText());
    }
}
