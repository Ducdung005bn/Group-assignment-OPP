import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        PendingUser pendingUser = new PendingUser();

        //Check
        Librarian check_librarian = new Librarian();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Name: ");
        check_librarian.setUserName(scanner.nextLine());

        System.out.println("Enter Date of Birth (yyyy-MM-dd): ");
        String dateInput = scanner.nextLine();
        check_librarian.setUserDateOfBirth(java.sql.Date.valueOf(dateInput)); // Chuyển đổi từ String sang Date

        System.out.println("Enter Phone Number: ");
        check_librarian.setUserPhoneNumb(scanner.nextLine());

        System.out.println("Enter Password: ");
        check_librarian.setUserPassword(scanner.nextLine());

        libraryManagementSystem.userNumb ++;
        check_librarian.setUserID(libraryManagementSystem.userNumb);
        libraryManagementSystem.librarianList.add(check_librarian);

        System.out.println("Librarian information has been entered successfully: ");
        check_librarian.printUserInfo();
        //

        while (true) {
            pendingUser.loginProcess(libraryManagementSystem);

            if (pendingUser.getIsBorrower()) {
                Borrower borrower;
                borrower = (Borrower)libraryManagementSystem.findUser(pendingUser.getPendingUserID());

                while (true) {
                    System.out.println("\n");
                    System.out.println("Welcome to My Application!");
                    System.out.println("[0] Exit");
                    System.out.println("[1] Find Document");
                    System.out.println("[2] Display Document");
                    System.out.println("[3] Borrow Document");
                    System.out.println("[4] Return Document");
                    System.out.println("[5] Display User Info");

                    System.out.print("Enter your choice: ");
                    String menuInput = scanner.nextLine();

                    if (menuInput.equals("0")) {
                        break;
                    } else if (menuInput.equals("1")) {
                        libraryManagementSystem.findAndPrintDocuments();
                    } else if (menuInput.equals("2")) {
                        libraryManagementSystem.printLibraryDocument();
                    } else if (menuInput.equals("3")) {
                        System.out.print("Enter ISBN: ");
                        borrower.borrowBook(scanner.nextLine(), libraryManagementSystem);
                    } else if (menuInput.equals("4")) {
                        System.out.print("Enter ISBN: ");
                        borrower.returnBook(scanner.nextLine(), libraryManagementSystem);
                    } else if (menuInput.equals("5")) {
                        borrower.printUserInfo();
                    }
                }
            } else if (pendingUser.getIsLibrarian()) {
                Librarian librarian = new Librarian();
                librarian = (Librarian)libraryManagementSystem.findUser(pendingUser.getPendingUserID());

                while (true) {
                    System.out.println("\n");
                    System.out.println("Welcome to My Application!");
                    System.out.println("[0] Exit");
                    System.out.println("[1] Add Document");
                    System.out.println("[4] Find Document");
                    System.out.println("[5] Display Document");
                    System.out.println("[6] Add User");
                    System.out.println("[7] Add Librarian");
                    System.out.println("[8] Display User Info");

                    System.out.print("Enter your choice: ");
                    String menuInput = scanner.nextLine();
                    if (menuInput.equals("0")) {
                        break;
                    } else if (menuInput.equals("1")) {
                        librarian.addDocument(libraryManagementSystem);
                    } else if (menuInput.equals("4")) {
                        libraryManagementSystem.findAndPrintDocuments();
                    } else if (menuInput.equals("5")) {
                        libraryManagementSystem.printLibraryDocument();
                    } else if (menuInput.equals("6")) {
                        librarian.addBorrower(libraryManagementSystem);
                    } else if (menuInput.equals("7")) {
                        librarian.addLibrarian(libraryManagementSystem);
                    } else if (menuInput.equals("8")) {
                        librarian.printUserInfo();
                    }
                }
            }
            pendingUser.deleteLoginInfo();
        }
    }
}