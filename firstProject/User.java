import java.util.Date;

/**
 * Represents a user with an ID, password, name, date of birth, and phone number.
 */
public class User {
    private int userID;
    private String userPassword;
    private String userName;
    private Date userDateOfBirth;
    private String userPhoneNumb;

    /**
     * Prints the user's information to the console.
     */
    public void printUserInfo() {
        System.out.println("User ID: " + userID);
        System.out.println("User Name: " + userName);
        System.out.println("User Date of Birth: " + userDateOfBirth);
        System.out.println("User Phone Numb: " + userPhoneNumb);
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getUserDateOfBirth() {
        return userDateOfBirth;
    }

    public void setUserDateOfBirth(Date userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
    }

    public String getUserPhoneNumb() {
        return userPhoneNumb;
    }

    public void setUserPhoneNumb(String userPhoneNumb) {
        this.userPhoneNumb = userPhoneNumb;
    }

    /**
     * Changes the user's password to the given new password.
     *
     * @param newUserPassword the new password for the user
     */
    public void changePassword(String newUserPassword) {
        userPassword = newUserPassword;
    }
}
