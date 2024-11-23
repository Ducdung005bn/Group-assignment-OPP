package main.classes;

import java.util.Date;
import java.io.Serializable;

/**
 * Represents a user with an ID, password, name, date of birth, and phone number.
 */
public class User implements Serializable {
    private int userID;
    private String userPassword;
    private String userName;
    private Date userDateOfBirth;
    private String userPhoneNumb;

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
}
