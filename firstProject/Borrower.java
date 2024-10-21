import java.util.Objects;
import java.util.Vector;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Represents a borrower in the library system. This class extends the User class
 * and contains information about borrowed books and their borrowing history.
 */
public class Borrower extends User {
    private boolean isStudent;
    private Vector<BorrowData> borrowedHistory = new Vector<>();
    private int borrowedBookCount;
    private Vector<BorrowData> borrowingHistory = new Vector<>();
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

    /**
     * Attempts to borrow a book from the library based on the provided ISBN.
     * <p>
     * If the book is available and the borrower has not reached the borrowing limit,
     * the method will create a new {@code BorrowData} entry and update the borrowing history.
     * </p>
     *
     * @param documentISBN the ISBN of the book to be borrowed
     * @param libraryManagementSystem the system managing library operations, which is used to verify the document's availability
     */
    public void borrowBook(String documentISBN, LibraryManagementSystem libraryManagementSystem) {
        String reason = isAbleToBorrow(documentISBN, libraryManagementSystem);

        if (reason == null) {  //is able to borrow the book
            BorrowData borrowData = new BorrowData();
            borrowData.setBorrowerID(getUserID());
            borrowData.setBorrowedBookISBN(documentISBN);
            borrowData.setBorrowDate(new Date());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_YEAR, LibraryManagementSystem.BORROW_DURATION_DAYS);

            borrowData.setPlannedReturnDate(calendar.getTime());
            borrowData.setBorrowStatus("Not Returned");

            borrowingHistory.add(borrowData);
            borrowingBookCount++;

            Document document = libraryManagementSystem.findDocumentByISBN(documentISBN);
            document.documentQuantity--;

            System.out.println("You have successfully borrowed the book.");
        } else {
            System.out.println(reason);
        }
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
    private String isAbleToBorrow(String documentISBN, LibraryManagementSystem libraryManagementSystem) {
        Document document = libraryManagementSystem.findDocumentByISBN(documentISBN);

        if (document == null) {
            return "The book with ISBN " + documentISBN + " was not found.";
        } else if (document.documentQuantity == 0) {
            return "The book with ISBN " + documentISBN + " is out of stock.";
        } else if (borrowingBookCount >= LibraryManagementSystem.MAX_BORROW_LIMIT) {
            return "You have reached the maximum borrow limit of " + LibraryManagementSystem.MAX_BORROW_LIMIT + " books.";
        } else {
            return null;  //is able to borrow the book
        }
    }

    /**
     * Processes the return of a borrowed book by updating the borrowing history
     * and the document quantity in the library management system.
     *
     * <p>This method searches for the specified book by its ISBN in the user's
     * borrowing history. If the book is found, it updates the status of the
     * borrow record, checks if the book was returned on time or late,
     * and adjusts the quantity of the book in the library accordingly.</p>
     *
     * @param documentISBN the ISBN of the book being returned
     * @param libraryManagementSystem the library management system containing
     *                                 the document information
     */
    public void returnBook(String documentISBN, LibraryManagementSystem libraryManagementSystem) {
        BorrowData borrowData = null;
        for (BorrowData data : borrowingHistory) {
            if (data.getBorrowedBookISBN().equals(documentISBN)) {
                borrowData = data;
                break;
            }
        }
        if (borrowData == null) {
            System.out.println("The book with ISBN " + documentISBN + " was not found in your borrowing history.");
        } else {
            borrowingHistory.remove(borrowData);
            Date actualReturnDate = new Date();
            if (actualReturnDate.after(borrowData.getPlannedReturnDate())) {
                System.out.println("You have returned the book after the due date.");
                borrowData.setBorrowStatus("Not Returned On Time");
                overdueCount++;
            } else {
                System.out.println("You have returned the book before the due date.");
                borrowData.setBorrowStatus("Returned On Time");
            }
            borrowedHistory.add(borrowData);
            borrowingBookCount--;

            Document document = libraryManagementSystem.findDocumentByISBN(documentISBN);
            if (document != null)
                document.documentQuantity++;
        }
    }
}
