import library.management.system.*;
import main.classes.other.opponents.*;
import main.classes.main.opponents.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DateValidatorTest extends AddInformationControllerTest {
    private AddInformationController addInformationController = new AddInformationController();

    @Test
    public void testValidDates() {
        assertTrue(addInformationController.isValidDate("2024-01-01")); // Ngày hợp lệ
        assertTrue(addInformationController.isValidDate("2023-02-28")); // Ngày hợp lệ (không phải năm nhuận)
        assertTrue(addInformationController.isValidDate("2020-02-29")); // Ngày hợp lệ (năm nhuận)
        assertTrue(addInformationController.isValidDate("2023-12-31")); // Ngày hợp lệ
        assertTrue(addInformationController.isValidDate("2000-02-29")); // Ngày hợp lệ (năm nhuận)
    }

    @Test
    public void testInvalidDates() {
        assertFalse(addInformationController.isValidDate("2023-02-30")); // Ngày không hợp lệ
        assertFalse(addInformationController.isValidDate("2023-04-31")); // Ngày không hợp lệ
        assertFalse(addInformationController.isValidDate("2023-13-01")); // Tháng không hợp lệ
        assertFalse(addInformationController.isValidDate("2023-00-01")); // Tháng không hợp lệ
        assertFalse(addInformationController.isValidDate("2023-01-32")); // Ngày không hợp lệ
        assertFalse(addInformationController.isValidDate("2023-04-31")); // Ngày không hợp lệ
    }

    @Test
    public void testEdgeCases() {
        assertFalse(addInformationController.isValidDate("2023-02-29")); // Không hợp lệ (năm không nhuận)
        assertTrue(addInformationController.isValidDate("2024-02-29")); // Hợp lệ (năm nhuận)
        assertFalse(addInformationController.isValidDate("2023-04-31")); // Ngày không hợp lệ
        assertTrue(addInformationController.isValidDate("2023-04-30")); // Ngày hợp lệ
    }

    @Test
    public void testInvalidFormat() {
        assertFalse(addInformationController.isValidDate("01-01-2024")); // Định dạng không hợp lệ
        assertFalse(addInformationController.isValidDate("2024/01/01")); // Định dạng không hợp lệ
        assertFalse(addInformationController.isValidDate("2024.01.01")); // Định dạng không hợp lệ
        assertFalse(addInformationController.isValidDate("2024-1-1")); // Định dạng không hợp lệ
        assertFalse(addInformationController.isValidDate("2024-01-")); // Định dạng không hợp lệ
        assertFalse(addInformationController.isValidDate("2024--01-01")); // Định dạng không hợp lệ
    }

    @Test
    public void testNullAndEmpty() {
        assertFalse(addInformationController.isValidDate("")); // Không hợp lệ
        assertFalse(addInformationController.isValidDate("   ")); // Không hợp lệ
    }
}
