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
