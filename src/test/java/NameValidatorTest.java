import library.management.system.*;
import main.classes.other.opponents.*;
import main.classes.main.opponents.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NameValidatorTest{
    private AddInformationController addInformationController = new AddInformationController();

    @Test
    public void testValidName() {
        assertTrue(addInformationController.isValidName("John Doe"));
        assertTrue(addInformationController.isValidName("Jane D."));
        assertTrue(addInformationController.isValidName("A B C"));
        assertTrue(addInformationController.isValidName("Alice"));
        assertTrue(addInformationController.isValidName("Alice B."));
    }

    @Test
    public void testEmptyName() {
//        assertFalse(addInformationController.isValidName(""));
        assertFalse(addInformationController.isValidName("   ")); // Chỉ có khoảng trắng
    }

    @Test
    public void testNameWithSpecialCharacters() {
        assertFalse(addInformationController.isValidName("John@Doe"));
        assertFalse(addInformationController.isValidName("Jane#D."));
        assertFalse(addInformationController.isValidName("John$Doe"));
    }

    @Test
    public void testNameWithMultipleSpaces() {
        assertFalse(addInformationController.isValidName("John  Doe")); // Hai khoảng trắng
        assertFalse(addInformationController.isValidName("  John Doe")); // Bắt đầu bằng khoảng trắng
        assertFalse(addInformationController.isValidName("John Doe  ")); // Kết thúc bằng khoảng trắng
    }

    @Test
    public void testNameWithDotAtEnd() {
        assertTrue(addInformationController.isValidName("John D.")); // Hợp lệ
        assertFalse(addInformationController.isValidName("John D..")); // Không hợp lệ
    }

    @Test
    public void testNameWithNumbers() {
        assertFalse(addInformationController.isValidName("John123"));
        assertFalse(addInformationController.isValidName("Jane D3"));
    }

}
