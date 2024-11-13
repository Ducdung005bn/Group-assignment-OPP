package main.classes;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Represents a borrower in the library system. This class extends the User class
 * and contains information about borrowed books and their borrowing history.
 */
public class Borrower extends User implements Serializable {
    public Vector<BorrowData> borrowingHistory = new Vector<>();
    public int borrowingBookCount;
    public Vector<BorrowData> borrowedHistory = new Vector<>();
    private boolean isStudent;
    private int overdueCount;

    /**
     * Prints the user information, including student status, borrowed book count,
     * borrowing book count, and overdue count.
     */
    @Override
    public void printUserInfo() {
        super.printUserInfo();
        System.out.println("Is student: " + isStudent);
        System.out.println("The number of borrowed books: " + borrowedHistory.size());
        System.out.println("The number of borrowing books: " + borrowingBookCount);
        System.out.println("The number of overdue books: " + overdueCount);
    }

    /**
     * Prints the history of borrowed books.
     */
    public void printBorrowerHistory() {
        for (BorrowData data : borrowedHistory) {
            data.printBorrowData();
        }
        for (BorrowData data : borrowingHistory) {
            data.printBorrowData();
        }
    }

    public boolean getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }
    
    public void setOverdueCount(int overdueCount) {
        this.overdueCount = overdueCount;
    }
    
    public int getOverdueCount() {
        return overdueCount;
    }


    /**
     * Checks whether the user is eligible to borrow a book based on the provided ISBN.
     * <p>
     * The method verifies if the book exists, if there are enough copies available,
     * and if the borrower has not exceeded the maximum borrowing limit.
     * </p>
     *
     * @param documentISBN the ISBN of the book to be checked
     * @param libraryManagementSystem the system managing library operations, which is used to verify the document's status
     * @return {@code null} if the user can borrow the book, or a string describing the reason why borrowing is not allowed
     */
    public String isAbleToBorrow(String documentISBN, LibraryManagementSystem libraryManagementSystem) {
        Document document = libraryManagementSystem.findDocumentByISBN(documentISBN);

        boolean alreadyBorrow = false;
        for (BorrowData borrowData : borrowingHistory) {
            if (borrowData.getBorrowedBookISBN() == documentISBN) {
                alreadyBorrow = true;
                break;
            }
        }
        if (document == null) {
            return "The book with ISBN " + documentISBN + " was not found.";
        } else if (document.documentQuantity == 0) {
            return "The book with ISBN " + documentISBN + " is out of stock.";
        } else if (alreadyBorrow) {
            return "You have already borrowed this book.";
        } else if (borrowingBookCount >= LibraryManagementSystem.MAX_BORROW_LIMIT) {
            return "You have reached the maximum borrow limit of " + LibraryManagementSystem.MAX_BORROW_LIMIT + " books.";
        } else {
            return null;  //is able to borrow the book
        }
    }    
}
