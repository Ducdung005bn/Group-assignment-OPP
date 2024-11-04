package main.classes;

import main.classes.User;
import main.classes.LibraryManagementSystem;
import main.classes.Borrower;
import java.util.Scanner;

/**
 * The PendingUser class represents a user who has not yet successfully logged in.
 * It holds an entered ID, an entered password, and login status as either a borrower or a librarian.
 */
public class PendingUser {
    private int pendingUserID;
    private String pendingUserPword;
    private boolean isBorrower;
    private boolean isLibrarian;

    /**
     * Default constructor for PendingUser.
     * Initializes pendingUserID to 0, pendingUserPword to an empty string,
     * and sets isBorrower and isLibrarian to false.
     */
    public PendingUser() {
        pendingUserID = 0;
        pendingUserPword = "";
        isBorrower = false;
        isLibrarian = false;
    }

    /**
     * Clears the current login information by resetting the user ID, password,
     * and status flags for borrower and librarian.
     */
    public void deleteLoginInfo() {
        pendingUserID = 0;
        pendingUserPword = "";
        isBorrower = false;
        isLibrarian = false;
    }

    public int getPendingUserID() {
        return pendingUserID;
    }

    public String getPendingUserPword() {
        return pendingUserPword;
    }

    public boolean getIsBorrower() {
        return isBorrower;
    }

    public boolean getIsLibrarian() {
        return isLibrarian;
    }
    
    public void setPendingUserID(int pendingUserID) {
        this.pendingUserID = pendingUserID;
    }
    
    public void setPendingUserPword(String pendingUserPword) {
        this.pendingUserPword = pendingUserPword;
    }

    public void checkIDandPword(LibraryManagementSystem libraryManagementSystem) {
        User user = libraryManagementSystem.findUser(pendingUserID);
        if (user != null && user.getUserPassword().equals(pendingUserPword)) {
            if (user instanceof Borrower) {
                isBorrower = true;
            } else {
                isLibrarian = true;
            }
        }
    }
}
