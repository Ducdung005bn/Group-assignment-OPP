import java.util.InputMismatchException;
import java.util.Vector;
import java.util.Scanner;
import java.util.Date;
import java.util.Arrays;

public class Librarian extends User {
    private int librarianSalary = 200; //dollars per month

    @Override
    public void printUserInfo() {
        super.printUserInfo();
        System.out.println("Salary: " + librarianSalary);
    }

    public int getLibrarianSalary() {
        return librarianSalary;
    }

    public void setLibrarianSalary(int librarianSalary) {
        this.librarianSalary = librarianSalary;
    }

    public void addBorrower(LibraryManagementSystem libraryManagementSystem) {
        Borrower borrower = new Borrower();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Name: ");
        borrower.setUserName(scanner.nextLine());

        setValidDate(scanner, borrower);

        String isStudentInput;

        while (true) {
            System.out.print("Be a student or not (yes/no): ");
            isStudentInput = scanner.nextLine().trim().toLowerCase();

            if (isStudentInput.equals("yes") || isStudentInput.equals("no")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        borrower.setIsStudent(isStudentInput.equals("yes"));

        System.out.print("Enter Phone Number: ");
        borrower.setUserPhoneNumb(scanner.nextLine());

        System.out.print("Enter Password: ");
        borrower.setUserPassword(scanner.nextLine());

        libraryManagementSystem.userNumb ++;
        borrower.setUserID(libraryManagementSystem.userNumb);
        libraryManagementSystem.borrowerList.add(borrower);

        System.out.println("Borrower information has been entered successfully:");
        borrower.printUserInfo();
    }

    public void addLibrarian(LibraryManagementSystem libraryManagementSystem) {
        Librarian librarian = new Librarian();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Name: ");
        librarian.setUserName(scanner.nextLine());

        setValidDate(scanner, librarian);

        System.out.print("Enter Phone Number: ");
        librarian.setUserPhoneNumb(scanner.nextLine());

        System.out.print("Enter Password: ");
        librarian.setUserPassword(scanner.nextLine());

        libraryManagementSystem.userNumb ++;
        librarian.setUserID(libraryManagementSystem.userNumb);
        libraryManagementSystem.librarianList.add(librarian);

        System.out.println("Librarian information has been entered successfully: ");
        librarian.printUserInfo();
    }

    public void addDocument(LibraryManagementSystem libraryManagementSystem) {
        Scanner scanner = new Scanner(System.in);

        String typeInput;
        String[] validTypes = {"book", "magazine", "thesis"};

        while (true) {
            System.out.print("Enter type (book, magazine, thesis): ");
            typeInput = scanner.nextLine().trim().toLowerCase();

            if (Arrays.asList(validTypes).contains(typeInput)) {
                break;
            } else {
                System.out.println("Invalid input. Please enter 'book', 'magazine', or 'thesis'.");
            }
        }

        System.out.print("Enter Quantity: ");
        int quantityInput = LibraryManagementSystem.setValidInteger();

        System.out.print("Enter Title: ");
        String titleInput = scanner.nextLine();

        System.out.print("Enter Author: ");
        String authorInput = scanner.nextLine();

        System.out.print("Enter Description: ");
        String descriptionInput = scanner.nextLine();

        System.out.print("Enter Language: ");
        String languageInput = scanner.nextLine();

        System.out.print("Enter Number of Pages: ");
        int pageInput = LibraryManagementSystem.setValidInteger();

        System.out.print("Enter ISBN: ");
        String iSBNInput = scanner.nextLine();

        if (typeInput.equals("book")) {
            Book book = new Book();

            book.documentQuantity = quantityInput;
            book.setDocumentTitle(titleInput);
            book.setDocumentAuthor(authorInput);
            book.setDocumentDescription(descriptionInput);
            book.setDocumentLanguage(languageInput);
            book.setDocumentPage(pageInput);
            book.setDocumentISBN(iSBNInput);

            System.out.print("Enter Genre: ");
            book.setBookGenre(scanner.nextLine());

            System.out.print("Enter Publisher: ");
            book.setBookPublisher(scanner.nextLine());

            libraryManagementSystem.bookList.add(book);
        } else if (typeInput.equals("thesis")) {
            Thesis thesis = new Thesis();

            thesis.documentQuantity = quantityInput;
            thesis.setDocumentTitle(titleInput);
            thesis.setDocumentAuthor(authorInput);
            thesis.setDocumentDescription(descriptionInput);
            thesis.setDocumentLanguage(languageInput);
            thesis.setDocumentPage(pageInput);
            thesis.setDocumentISBN(iSBNInput);

            System.out.print("Enter Subject: ");
            thesis.setThesisSubject(scanner.nextLine());

            System.out.print("Enter Degree: ");
            thesis.setThesisDegree(scanner.nextLine());

            System.out.print("Enter University: ");
            thesis.setThesisUniversity(scanner.nextLine());

            libraryManagementSystem.thesisList.add(thesis);
        } else if (typeInput.equals("magazine")) {
            Magazine magazine = new Magazine();

            magazine.documentQuantity = quantityInput;
            magazine.setDocumentTitle(titleInput);
            magazine.setDocumentAuthor(authorInput);
            magazine.setDocumentDescription(descriptionInput);
            magazine.setDocumentLanguage(languageInput);
            magazine.setDocumentPage(pageInput);
            magazine.setDocumentISBN(iSBNInput);

            System.out.print("Enter Subject: ");
            magazine.setMagazineSubject(scanner.nextLine());

            System.out.print("Enter Frequency: ");
            magazine.setMagazineFrequency(LibraryManagementSystem.setValidInteger());

            System.out.print("Enter Issue Number: ");
            magazine.setMagazineIssueNumb(LibraryManagementSystem.setValidInteger());

            libraryManagementSystem.magazineList.add(magazine);
        }

    }

    public void removeDocument(LibraryManagementSystem libraryManagementSystem) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ISBN: ");
        String iSBNInput = scanner.nextLine();
        Document removedDocument = libraryManagementSystem.findDocumentByISBN(iSBNInput);
        if (removedDocument == null) {
            System.out.println("Document not found");
        } else {
            if (removedDocument instanceof Book) {
                libraryManagementSystem.bookList.remove(removedDocument);
            } else if (removedDocument instanceof Thesis) {
                libraryManagementSystem.thesisList.remove(removedDocument);
            } else if (removedDocument instanceof Magazine) {
                libraryManagementSystem.magazineList.remove(removedDocument);
            }
        }
    }

    public void updateDocument(LibraryManagementSystem libraryManagementSystem) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ISBN: ");
        String iSBNInput = scanner.nextLine();
        Document documentToUpdate = libraryManagementSystem.findDocumentByISBN(iSBNInput);
        boolean continueUpdating = true;

        while (continueUpdating) {
            System.out.println("[0] Finish updating");
            System.out.println("[1] Quantity");
            System.out.println("[2] Title");
            System.out.println("[3] Author");
            System.out.println("[4] Description");
            System.out.println("[5] Language");
            System.out.println("[6] Number of Pages");

            if (documentToUpdate instanceof Book) {
                System.out.println("[7] Genre");
                System.out.println("[8] Publisher");
            } else if (documentToUpdate instanceof Thesis) {
                System.out.println("[7] Subject");
                System.out.println("[8] Degree");
                System.out.println("[9] University");
            } else if (documentToUpdate instanceof Magazine) {
                System.out.println("[7] Subject");
                System.out.println("[8] Frequency");
                System.out.println("[9] Issue Number");
            }

            System.out.print("Select the attribute you want to update: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter new Quantity: ");
                        documentToUpdate.documentQuantity = LibraryManagementSystem.setValidInteger();
                    }
                    case 2 -> {
                        System.out.print("Enter new Title: ");
                        documentToUpdate.setDocumentTitle(scanner.nextLine());
                    }
                    case 3 -> {
                        System.out.print("Enter new Author: ");
                        documentToUpdate.setDocumentAuthor(scanner.nextLine());
                    }
                    case 4 -> {
                        System.out.print("Enter new Description: ");
                        documentToUpdate.setDocumentDescription(scanner.nextLine());
                    }
                    case 5 -> {
                        System.out.print("Enter new Language: ");
                        documentToUpdate.setDocumentLanguage(scanner.nextLine());
                    }
                    case 6 -> {
                        System.out.print("Enter new Number of Pages: ");
                        documentToUpdate.setDocumentPage(LibraryManagementSystem.setValidInteger());
                    }
                    case 7 -> {
                        if (documentToUpdate instanceof Book) {
                            System.out.print("Enter new Genre: ");
                            ((Book) documentToUpdate).setBookGenre(scanner.nextLine());
                        } else if (documentToUpdate instanceof Thesis) {
                            System.out.print("Enter new Subject: ");
                            ((Thesis) documentToUpdate).setThesisSubject(scanner.nextLine());
                        } else if (documentToUpdate instanceof Magazine) {
                            System.out.print("Enter new Subject: ");
                            ((Magazine) documentToUpdate).setMagazineSubject(scanner.nextLine());
                        }
                    }
                    case 8 -> {
                        if (documentToUpdate instanceof Book) {
                            System.out.print("Enter new Publisher: ");
                            ((Book) documentToUpdate).setBookPublisher(scanner.nextLine());
                        } else if (documentToUpdate instanceof Thesis) {
                            System.out.print("Enter new Degree: ");
                            ((Thesis) documentToUpdate).setThesisDegree(scanner.nextLine());
                        } else if (documentToUpdate instanceof Magazine) {
                            System.out.print("Enter new Frequency: ");
                            ((Magazine) documentToUpdate).setMagazineFrequency(LibraryManagementSystem.setValidInteger());
                        }
                    }
                    case 9 -> {
                        if (documentToUpdate instanceof Book) {
                            System.out.println("Invalid choice. Please try again.");
                        } else if (documentToUpdate instanceof Thesis) {
                            System.out.print("Enter new University: ");
                            ((Thesis) documentToUpdate).setThesisUniversity(scanner.nextLine());
                        } else if (documentToUpdate instanceof Magazine) {
                            System.out.print("Enter new Issue Number: ");
                            ((Magazine) documentToUpdate).setMagazineIssueNumb(LibraryManagementSystem.setValidInteger());
                        }
                    }
                    case 0 -> {
                        continueUpdating = false;
                        System.out.println("Updating has finished.");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    public static void setValidDate(Scanner scanner, User user) {
        String dateInput;

        while (true) {
            System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
            dateInput = scanner.nextLine();

            if (isValidDate(dateInput)) {
                user.setUserDateOfBirth(java.sql.Date.valueOf(dateInput));
                break;
            } else {
                System.out.println("Invalid date format or value. Please enter a valid date (yyyy-MM-dd).");
            }
        }
    }

    public static boolean isValidDate(String dateInput) {
        String regex = "^\\d{4}-(\\d{1,2})-(\\d{1,2})$";

        if (!dateInput.matches(regex)) {
            return false;
        }

        String[] parts = dateInput.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        String normalizedDate = String.format("%04d-%02d-%02d", year, month, day);

        try {
            java.sql.Date.valueOf(normalizedDate);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
