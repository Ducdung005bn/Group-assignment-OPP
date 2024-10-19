import java.util.Scanner;

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

    public void loginProcess(LibraryManagementSystem libraryManagementSystem) {
        do {
            setIDandPword(libraryManagementSystem);
            if (loginSuccessful()) {
                System.out.println( "You have logged in successfully");
            } else {
                System.out.println( "You have logged in unsuccessfully");
            }
        } while (loginSuccessful());
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

    private void setIDandPword(LibraryManagementSystem libraryManagementSystem) {
        Scanner scanner = new Scanner(System.in);
        System.out.println( "Please enter your ID:");
        pendingUserID = scanner.nextInt();
        scanner.nextLine();
        System.out.println( "Please enter your password:");
        pendingUserPword = scanner.nextLine();
        checkIDandPword(libraryManagementSystem);
    }

    private boolean loginSuccessful() {
        return isBorrower || isLibrarian;
    }

    private void checkIDandPword(LibraryManagementSystem libraryManagementSystem) {
        User user = libraryManagementSystem.findUser(pendingUserID);
        if (user != null && user.userPassword == pendingUserPword) {
            if (user instanceof Borrower) {
                isBorrower = true;
            } else {
                isLibrarian = true;
            }
        }
    }
}
