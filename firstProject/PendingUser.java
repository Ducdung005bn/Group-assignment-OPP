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

    /**
     * Default constructor for PendingUser.
     * Initializes pendingUserID to 0, pendingUserPword to an empty string,
     * and sets isBorrower and isLibrarian to false.
     */
    public PendingUser() {
        pendingUserID = 0;
        pendingUserPword = "";
        isBorrower = false;
        isLibrarian = false;
    }

    /**
     * Starts the login process by prompting the user to enter their ID and password,
     * and checks if the login is successful. The process repeats until a successful login occurs.
     *
     * @param libraryManagementSystem The LibraryManagementSystem to validate user credentials.
     */
    public void loginProcess(LibraryManagementSystem libraryManagementSystem) {
        do {
            setIDandPword(libraryManagementSystem);
            if (loginSuccessful()) {
                System.out.println("You have logged in successfully");
            } else {
                System.out.println("You have logged in unsuccessfully");
            }
        } while (!loginSuccessful());
    }

    /**
     * Clears the current login information by resetting the user ID, password,
     * and status flags for borrower and librarian.
     */
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

    /**
     * Prompts the user to enter their ID and password, then check the user's login status.
     *
     * @param libraryManagementSystem The LibraryManagementSystem to validate user credentials.
     */
    private void setIDandPword(LibraryManagementSystem libraryManagementSystem) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your ID: ");
        pendingUserID = LibraryManagementSystem.setValidInteger();
        System.out.print("Please enter your password: ");
        pendingUserPword = scanner.nextLine();
        checkIDandPword(libraryManagementSystem);
    }

    private boolean loginSuccessful() {
        return isBorrower || isLibrarian;
    }

    /**
     * Validates the user ID and password by checking against the LibraryManagementSystem's
     * list of users. Sets the user as either a borrower or librarian upon successful validation.
     *
     * @param libraryManagementSystem The LibraryManagementSystem used to validate the credentials.
     */
    private void checkIDandPword(LibraryManagementSystem libraryManagementSystem) {
        User user = libraryManagementSystem.findUser(pendingUserID);
        if (user != null && user.getUserPassword().equals(pendingUserPword)) {
            if (user instanceof Borrower) {
                isBorrower = true;
            } else {
                isLibrarian = true;
            }
        }
    }
}
