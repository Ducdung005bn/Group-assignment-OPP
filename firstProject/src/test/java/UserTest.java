import main.classes.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        // Initialize User object before each test
        user = new User();
    }

    @AfterEach
    void tearDown() {
        // Clean up after each test if necessary
        user = null;
    }

    @Test
    void getUserID() {
        user.setUserID(123);
        assertEquals(123, user.getUserID(), "Expected userID to be 123");
    }

    @Test
    void setUserID() {
        user.setUserID(456);
        assertEquals(456, user.getUserID(), "Expected userID to be 456");
    }

    @Test
    void getUserPassword() {
        user.setUserPassword("password123");
        assertEquals("password123", user.getUserPassword(), "Expected password to match");
    }

    @Test
    void setUserPassword() {
        user.setUserPassword("newPassword456");
        assertEquals("newPassword456", user.getUserPassword(), "Expected password to match");
    }

    @Test
    void getUserName() {
        user.setUserName("John Doe");
        assertEquals("John Doe", user.getUserName(), "Expected userName to match");
    }

    @Test
    void setUserName() {
        user.setUserName("Jane Doe");
        assertEquals("Jane Doe", user.getUserName(), "Expected userName to match");
    }

    @Test
    void getUserDateOfBirth() {
        Date dob = new Date(90, 1, 1); // Sample date of birth
        user.setUserDateOfBirth(dob);
        assertEquals(dob, user.getUserDateOfBirth(), "Expected date of birth to match");
    }

    @Test
    void setUserDateOfBirth() {
        Date dob = new Date(85, 5, 25); // Sample date of birth
        user.setUserDateOfBirth(dob);
        assertEquals(dob, user.getUserDateOfBirth(), "Expected date of birth to match");
    }

    @Test
    void getUserPhoneNumb() {
        user.setUserPhoneNumb("123-456-7890");
        assertEquals("123-456-7890", user.getUserPhoneNumb(), "Expected phone number to match");
    }

    @Test
    void setUserPhoneNumb() {
        user.setUserPhoneNumb("098-765-4321");
        assertEquals("098-765-4321", user.getUserPhoneNumb(), "Expected phone number to match");
    }

    // Edge case tests
    @Test
    void setUserPassword_emptyString() {
        user.setUserPassword("");
        assertEquals("", user.getUserPassword(), "Expected password to be empty string");
    }

    @Test
    void setUserPhoneNumb_invalidFormat() {
        user.setUserPhoneNumb("invalid-phone-number");
        assertEquals("invalid-phone-number", user.getUserPhoneNumb(), "Expected phone number to be set even if invalid");
    }

    @Test
    void setUserDateOfBirth_futureDate() {
        Date futureDate = new Date(System.currentTimeMillis() + 1000000000L); // Future date
        user.setUserDateOfBirth(futureDate);
        assertEquals(futureDate, user.getUserDateOfBirth(), "Expected date of birth to be set to future date");
    }
}
