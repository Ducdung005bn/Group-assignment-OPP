import java.util.Vector;
import java.util.Scanner;
import java.util.Date;

public class Librarian extends User {
    private int librarianSalary = 200; //dollars per month

    /**
     * Prints the user information, including the librarian's salary.
     */
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

        System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
        String dateInput = scanner.nextLine();
        borrower.setUserDateOfBirth(java.sql.Date.valueOf(dateInput)); // Chuyển đổi từ String sang Date

        System.out.print("Be a student or not (YES/NO): ");
        String isStudentInput = scanner.nextLine();
        if (isStudentInput.equals("YES"))
            borrower.setIsStudent(true);
        else if (isStudentInput.equals("NO"))
            borrower.setIsStudent(false);

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

        System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
        String dateInput = scanner.nextLine();
        librarian.setUserDateOfBirth(java.sql.Date.valueOf(dateInput)); // Chuyển đổi từ String sang Date

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

        System.out.print("Is the document a book, a thesis or a magazine (book/thesis/magazine): ");
        String typeInput = scanner.nextLine();

        System.out.print("Enter Quantity: ");
        int quantityInput = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Title: ");
        String titleInput = scanner.nextLine();

        System.out.print("Enter Author: ");
        String authorInput = scanner.nextLine();

        System.out.print("Enter Description: ");
        String descriptionInput = scanner.nextLine();

        System.out.print("Enter Language: ");
        String languageInput = scanner.nextLine();

        System.out.print("Enter Number of Pages: ");
        int pageInput = scanner.nextInt();
        scanner.nextLine();

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
            magazine.setMagazineFrequency(scanner.nextInt());
            scanner.nextLine();

            System.out.print("Enter Issue Number: ");
            magazine.setMagazineIssueNumb(scanner.nextInt());
            scanner.nextLine();

            libraryManagementSystem.magazineList.add(magazine);
        }

    }

    public void removeDocument(LibraryManagementSystem libraryManagementSystem) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ISBN: ");
        String iSBNInput = scanner.nextLine();

        //TO DO 3

    }

    public void updateDocument(LibraryManagementSystem libraryManagementSystem) {

        //TO DO 4

    }

}
