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

    public PendingUser() {
        pendingUserID = 0;
        pendingUserPword = "";
        isBorrower = false;
        isLibrarian = false;
    }

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

    /**
     * Validates a user's ID and password against the library management system's records.
     * Determines if the user is a borrower or a librarian based on the validation result.
     *
     * @param libraryManagementSystem the library management system instance containing user data.
     */
    public void checkIDandPword(LibraryManagementSystem libraryManagementSystem) {
        User user = libraryManagementSystem.findUserByID(pendingUserID);

        if (user != null && user.getUserPassword().equals(pendingUserPword)) {
            if (user instanceof Borrower) {
                isBorrower = true;
            } else {
                isLibrarian = true;
            }
        }
    }

}
