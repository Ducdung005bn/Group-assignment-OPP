
import java.util.Date;

/**
 * Represents a user with an ID, password, name, date of birth, and phone number.
 */
public class User {
    private int id;
    private String userPassword;
    private String userName;
    private Date userDateOfBirth;
    private String userPhoneNumb;

    /**
     * Prints the user's information to the console.
     */
    public void printUserInfo() {
        System.out.println("ID: " + id);
        System.out.println("User Name: " + userName);
        System.out.println("User Password: " + userPassword);
        System.out.println("User Date of Birth: " + userDateOfBirth);
        System.out.println("User Phone Numb: " + userPhoneNumb);
    }

    /**
     * Gets the user's ID.
     *
     * @return the user's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the user's ID.
     *
     * @param id the user's ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the user's password.
     *
     * @return the user's password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets the user's password.
     *
     * @param userPassword the user's password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Gets the user's name.
     *
     * @return the user's name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user's name.
     *
     * @param userName the user's name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the user's date of birth.
     *
     * @return the user's date of birth
     */
    public Date getUserDateOfBirth() {
        return userDateOfBirth;
    }

    /**
     * Sets the user's date of birth.
     *
     * @param userDateOfBirth the user's date of birth
     */
    public void setUserDateOfBirth(Date userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
    }

    /**
     * Gets the user's phone number.
     *
     * @return the user's phone number
     */
    public String getUserPhoneNumb() {
        return userPhoneNumb;
    }

    /**
     * Sets the user's phone number.
     *
     * @param userPhoneNumb the user's phone number
     */
    public void setUserPhoneNumb(String userPhoneNumb) {
        this.userPhoneNumb = userPhoneNumb;
    }

    /**
     * Changes the user's password to the given new password.
     *
     * @param newPassword the new password for the user
     */
    public void changePassword(String newPassword) {
        userPassword = newPassword;
    }
}
