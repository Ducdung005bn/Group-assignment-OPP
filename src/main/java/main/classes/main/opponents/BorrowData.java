package main.classes.main.opponents;

import java.util.Date;
import java.io.Serializable;

/**
 * Represents the borrowing data of a book, including the borrower's ID, book ISBN,
 * borrowed date, planned return date, and the current status of the borrow.
 */
public class BorrowData implements Serializable {
    private int borrowerID;
    private String borrowedBookISBN;
    private Date borrowDate;
    private Date plannedReturnDate;
    private String borrowStatus;
    private int userRating;

    public BorrowData() {
        borrowerID = 0;
        borrowedBookISBN = "";
        borrowDate = null;
        plannedReturnDate = null;
        borrowStatus = "";
        userRating = 0;
    }

    public int getBorrowerID() {
        return borrowerID;
    }

    public void setBorrowerID(int borrowerID) {
        this.borrowerID = borrowerID;
    }

    public String getBorrowedBookISBN() {
        return borrowedBookISBN;
    }

    public void setBorrowedBookISBN(String borrowedBookISBN) {
        this.borrowedBookISBN = borrowedBookISBN;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getPlannedReturnDate() {
        return plannedReturnDate;
    }

    public void setPlannedReturnDate(Date plannedReturnDate) {
        this.plannedReturnDate = plannedReturnDate;
    }

    public String getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(String borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }
}
