import java.util.Scanner;
import java.util.Vector;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        PendingUser pendingUser = new PendingUser();
        Scanner scanner = new Scanner(System.in);

        //check; sua public static thanh private
        Librarian check_librarian = new Librarian();

        System.out.print("Enter Name: ");
        check_librarian.setUserName(scanner.nextLine());

        Librarian.setValidDate(scanner, check_librarian);

        System.out.print("Enter Phone Number: ");
        check_librarian.setUserPhoneNumb(scanner.nextLine());

        System.out.print("Enter Password: ");
        check_librarian.setUserPassword(scanner.nextLine());

        libraryManagementSystem.userNumb ++;
        check_librarian.setUserID(libraryManagementSystem.userNumb);
        libraryManagementSystem.librarianList.add(check_librarian);

        System.out.println("Librarian information has been entered successfully: ");
        check_librarian.printUserInfo();
        //check

        while (true) {
            pendingUser.loginProcess(libraryManagementSystem);

            if (pendingUser.getIsBorrower()) {
                Borrower borrower;
                borrower = (Borrower)libraryManagementSystem.findUser(pendingUser.getPendingUserID());

                while (true) {
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
                    } else {
                        System.out.println("Action is not supported.");
                    }
                }
            } else if (pendingUser.getIsLibrarian()) {
                Librarian librarian = new Librarian();
                librarian = (Librarian)libraryManagementSystem.findUser(pendingUser.getPendingUserID());

                while (true) {
                    System.out.println("Welcome to My Application!");
                    System.out.println("[0] Exit");
                    System.out.println("[1] Add Document");
                    System.out.println("[2] Remove Document");
                    System.out.println("[3] Update Document");
                    System.out.println("[4] Find Document");
                    System.out.println("[5] Display Document");
                    System.out.println("[6] Add Borrower");
                    System.out.println("[7] Add Librarian");
                    System.out.println("[8] Display User Info");

                    System.out.print("Enter your choice: ");
                    String menuInput = scanner.nextLine();
                    if (menuInput.equals("0")) {
                        break;
                    } else if (menuInput.equals("1")) {
                        librarian.addDocument(libraryManagementSystem);
                    } else if (menuInput.equals("2")) {
                        librarian.removeDocument(libraryManagementSystem);
                    } else if (menuInput.equals("3")) {
                        librarian.updateDocument(libraryManagementSystem);
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
                    } else {
                        System.out.println("Action is not supported.");
                    }
                }
            }
            pendingUser.deleteLoginInfo();
        }

    }
}