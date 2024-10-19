import java.util.ArrayList;
import java.util.Date;

public class Borrower extends User {
    private static final int MAX_BORROW_LIMIT = 5;
    private boolean isStudent;
    private ArrayList<BorrowData> borrowedHistory = new ArrayList<>();
    private int borrowedBookCount;
    private ArrayList<BorrowData> borrowingHistory = new ArrayList<>();
    private int borrowingBookCount;
    private int overdueCount;

    @Override
    public void printUserInfo() {
        super.printUserInfo();
        System.out.println("isStudent: " + isStudent);
        System.out.println("borrowedBookCount: " + borrowedBookCount);
        System.out.println("borrowingBookCount: " + borrowingBookCount);
        System.out.println("overdueCount: " + overdueCount);
    }

    public void printBorrowerHistory() {
        for (BorrowData data : borrowedHistory) {
            data.printBorrowData();
        }
    }

    public boolean getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    public ArrayList<BorrowData> getBorrowedHistory() {
        return borrowedHistory;
    }

    public void setBorrowedHistory(ArrayList<BorrowData> borrowedHistory) {
        this.borrowedHistory = borrowedHistory;
        this.borrowedBookCount = borrowedHistory.size();
    }

    public int getBorrowedBookCount() {
        return borrowedBookCount;
    }

    public void setBorrowedBookCount(int borrowedBookCount) {
        this.borrowedBookCount = borrowedBookCount;
    }

    public ArrayList<BorrowData> getBorrowingHistory() {
        return borrowingHistory;
    }

    public void setBorrowingHistory(ArrayList<BorrowData> borrowingHistory) {
        this.borrowingHistory = borrowingHistory;
        this.borrowingBookCount = borrowingHistory.size();
    }

    public int getBorrowingBookCount() {
        return borrowingBookCount;
    }

    public void setBorrowingBookCount(int borrowingBookCount) {
        this.borrowingBookCount = borrowingBookCount;
    }

    public int getOverdueCount() {
        return overdueCount;
    }

    public void setOverdueCount(int overdueCount) {
        this.overdueCount = overdueCount;
    }

    public void borrowBook(BorrowData borrowData) {
        if (isAbleToBorrow()) {
            borrowingHistory.add(borrowData);
            borrowingBookCount++;
        } else {
            System.out.println("Cannot borrow more books.");
        }
    }

    public boolean isAbleToBorrow() {
        // Assuming a limit of 5 books to borrow at a time
        return borrowingBookCount < MAX_BORROW_LIMIT;
    }

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
