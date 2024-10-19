import java.util.ArrayList;
import java.util.Date;

/**
 * Represents a borrower in the library system. This class extends the User class
 * and contains information about borrowed books and their borrowing history.
 */
public class Borrower extends User {
    private static final int MAX_BORROW_LIMIT = 5;
    private boolean isStudent;
    private ArrayList<BorrowData> borrowedHistory = new ArrayList<>();
    private int borrowedBookCount;
    private ArrayList<BorrowData> borrowingHistory = new ArrayList<>();
    private int borrowingBookCount;
    private int overdueCount;

    /**
     * Prints the user information, including student status, borrowed book count,
     * borrowing book count, and overdue count.
     */
    @Override
    public void printUserInfo() {
        super.printUserInfo();
        System.out.println("isStudent: " + isStudent);
        System.out.println("borrowedBookCount: " + borrowedBookCount);
        System.out.println("borrowingBookCount: " + borrowingBookCount);
        System.out.println("overdueCount: " + overdueCount);
    }

    /**
     * Prints the history of borrowed books.
     */
    public void printBorrowerHistory() {
        for (BorrowData data : borrowedHistory) {
            data.printBorrowData();
        }
    }

    /**
     * Gets the student status of the borrower.
     *
     * @return true if the borrower is a student, false otherwise.
     */
    public boolean getIsStudent() {
        return isStudent;
    }

    /**
     * Sets the student status of the borrower.
     *
     * @param isStudent the student status to set.
     */
    public void setIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    /**
     * Gets the borrowed history of the borrower.
     *
     * @return a list of BorrowData representing the borrowed history.
     */
    public ArrayList<BorrowData> getBorrowedHistory() {
        return borrowedHistory;
    }

    /**
     * Sets the borrowed history of the borrower and updates the borrowed book count.
     *
     * @param borrowedHistory the borrowed history to set.
     */
    public void setBorrowedHistory(ArrayList<BorrowData> borrowedHistory) {
        this.borrowedHistory = borrowedHistory;
        this.borrowedBookCount = borrowedHistory.size();
    }

    /**
     * Gets the count of borrowed books.
     *
     * @return the count of borrowed books.
     */
    public int getBorrowedBookCount() {
        return borrowedBookCount;
    }

    /**
     * Sets the count of borrowed books.
     *
     * @param borrowedBookCount the count of borrowed books to set.
     */
    public void setBorrowedBookCount(int borrowedBookCount) {
        this.borrowedBookCount = borrowedBookCount;
    }

    /**
     * Gets the borrowing history of the borrower.
     *
     * @return a list of BorrowData representing the borrowing history.
     */
    public ArrayList<BorrowData> getBorrowingHistory() {
        return borrowingHistory;
    }

    /**
     * Sets the borrowing history of the borrower and updates the borrowing book count.
     *
     * @param borrowingHistory the borrowing history to set.
     */
    public void setBorrowingHistory(ArrayList<BorrowData> borrowingHistory) {
        this.borrowingHistory = borrowingHistory;
        this.borrowingBookCount = borrowingHistory.size();
    }

    /**
     * Gets the count of borrowing books.
     *
     * @return the count of borrowing books.
     */
    public int getBorrowingBookCount() {
        return borrowingBookCount;
    }

    /**
     * Sets the count of borrowing books.
     *
     * @param borrowingBookCount the count of borrowing books to set.
     */
    public void setBorrowingBookCount(int borrowingBookCount) {
        this.borrowingBookCount = borrowingBookCount;
    }

    /**
     * Gets the count of overdue books.
     *
     * @return the count of overdue books.
     */
    public int getOverdueCount() {
        return overdueCount;
    }

    /**
     * Sets the count of overdue books.
     *
     * @param overdueCount the count of overdue books to set.
     */
    public void setOverdueCount(int overdueCount) {
        this.overdueCount = overdueCount;
    }

    /**
     * Allows the borrower to borrow a book if the limit has not been reached.
     *
     * @param borrowData the BorrowData representing the book to borrow.
     */
    public void borrowBook(BorrowData borrowData) {
        if (isAbleToBorrow()) {
            borrowingHistory.add(borrowData);
            borrowingBookCount++;
        } else {
            System.out.println("Cannot borrow more books.");
        }
    }

    /**
     * Checks if the borrower is able to borrow more books based on the limit.
     *
     * @return true if the borrower can borrow more books, false otherwise.
     */
    public boolean isAbleToBorrow() {
        return borrowingBookCount < MAX_BORROW_LIMIT;
    }

    /**
     * Returns a borrowed book and updates the history and overdue count if necessary.
     *
     * @param borrowing the BorrowData representing the book to return.
     */
    public void returnBook(BorrowData borrowing) {
        Date borrowedDate = borrowing.getBorrowedDate();
        Date plannedReturnDate = borrowing.getPlannedReturnDate();
        if (borrowedDate != null && plannedReturnDate != null && borrowedDate.after(plannedReturnDate)) {
            overdueCount++;
        }
        borrowingHistory.remove(borrowing);
        borrowedHistory.add(borrowing);
        borrowingBookCount--;
    }
}
