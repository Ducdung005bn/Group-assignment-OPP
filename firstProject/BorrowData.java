import java.util.*;

/**
 * Represents the borrowing data of a book, including the borrower's ID, book ISBN,
 * borrowed date, planned return date, and the current status of the borrow.
 */
public class BorrowData {
    private String borrowerID;
    private String borrowedBookISBN;
    private Date borrowedDate;
    private Date plannedReturnDate;
    private String borrowStatus;

    /**
     * Prints the borrowing data information to the console.
     */
    public void printBorrowData() {
        System.out.println("Borrowed Book ISBN: " + borrowedBookISBN);
        System.out.println("Borrowed Date: " + borrowedDate);
        System.out.println("Borrowed Status: " + borrowStatus);
    }

    /**
     * Gets the ID of the borrower.
     *
     * @return the borrower's ID
     */
    public String getBorrowerID() {
        return borrowerID;
    }

    /**
     * Sets the ID of the borrower.
     *
     * @param borrowerID the borrower's ID
     */
    public void setBorrowerID(String borrowerID) {
        this.borrowerID = borrowerID;
    }

    /**
     * Gets the ISBN of the borrowed book.
     *
     * @return the ISBN of the borrowed book
     */
    public String getBorrowedBookISBN() {
        return borrowedBookISBN;
    }

    /**
     * Sets the ISBN of the borrowed book.
     *
     * @param borrowedBookISBN the ISBN of the borrowed book
     */
    public void setBorrowedBookISBN(String borrowedBookISBN) {
        this.borrowedBookISBN = borrowedBookISBN;
    }

    /**
     * Gets the date when the book was borrowed.
     *
     * @return the borrowed date
     */
    public Date getBorrowedDate() {
        return borrowedDate;
    }

    /**
     * Sets the date when the book was borrowed.
     *
     * @param borrowedDate the borrowed date
     */
    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    /**
     * Gets the planned return date of the book.
     *
     * @return the planned return date
     */
    public Date getPlannedReturnDate() {
        return plannedReturnDate;
    }

    /**
     * Sets the planned return date of the book.
     *
     * @param plannedReturnDate the planned return date
     */
    public void setPlannedReturnDate(Date plannedReturnDate) {
        this.plannedReturnDate = plannedReturnDate;
    }

    /**
     * Gets the current borrowing status.
     *
     * @return the borrowing status
     */
    public String getBorrowStatus() {
        return borrowStatus;
    }

    /**
     * Sets the current borrowing status.
     *
     * @param borrowStatus the borrowing status
     */
    public void setBorrowStatus(String borrowStatus) {
        this.borrowStatus = borrowStatus;
    }
}
