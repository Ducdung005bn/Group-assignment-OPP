package main.classes.main.opponents;

import java.io.Serializable;
import java.util.Vector;

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

}
