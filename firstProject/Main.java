public class Main {
    public static void main(String[] args) {
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        PendingUser pendingUser = new PendingUser();

        while (true) {
            pendingUser.loginProcess(LibraryManagementSystem);
            //To do
        }
    }
}