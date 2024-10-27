import java.util.Vector;
import java.util.Scanner;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
    public class DateValidator {
        private DateTimeFormatter dateFormatter;
        private Scanner sc;

        // Constructor
        public DateValidator(DateTimeFormatter dateFormatter) {
            this.dateFormatter = dateFormatter;
            this.sc = new Scanner(System.in);
        }

        // Phương thức kiểm tra tính hợp lệ của ngày
        public boolean isValid(String dateStr) {
            try {
                LocalDate.parse(dateStr, this.dateFormatter);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }

        // Phương thức để nhận đầu vào ngày hợp lệ
        public String dateValidInput() {
            String date;
            do {
                System.out.print("Enter a valid date (" + dateFormatter.toString() + "): ");
                date = sc.nextLine();
                if (!isValid(date)) {
                    System.out.println("Invalid date. Try again!");
                }
            } while (!isValid(date));
            return date; // Trả về ngày hợp lệ
        }
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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ISBN of the document to update: ");
        String iSBNInput = scanner.nextLine();
        Document documentToUpdate = findDocumentByISBN(iSBNInput);

        if (documentToUpdate instanceof Book) {
            System.out.println("Updating book information. Choose the attribute you want to update:");
            boolean continueUpdate = true;

            while (continueUpdate) {
                System.out.println("[1] Quantity");
                System.out.println("[2] Title");
                System.out.println("[3] Author");
                System.out.println("[4] Description");
                System.out.println("[5] Language");
                System.out.println("[6] Number of Pages");
                System.out.println("[7] Genre");
                System.out.println("[8] Publisher");
                System.out.println("[0] Finish updating");

                System.out.print("Enter the number corresponding to the attribute to update: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter new Quantity: ");
                        bookToUpdate.documentQuantity = scanner.nextInt();
                        scanner.nextLine();
                    }
                    case 2 -> {
                        System.out.print("Enter new Title: ");
                        bookToUpdate.setDocumentTitle(scanner.nextLine());
                    }
                    case 3 -> {
                        System.out.print("Enter new Author: ");
                        bookToUpdate.setDocumentAuthor(scanner.nextLine());
                    }
                    case 4 -> {
                        System.out.print("Enter new Description: ");
                        bookToUpdate.setDocumentDescription(scanner.nextLine());
                    }
                    case 5 -> {
                        System.out.print("Enter new Language: ");
                        bookToUpdate.setDocumentLanguage(scanner.nextLine());
                    }
                    case 6 -> {
                        System.out.print("Enter new Number of Pages: ");
                        bookToUpdate.setDocumentPage(scanner.nextInt());
                        scanner.nextLine();
                    }
                    case 7 -> {
                        System.out.print("Enter new Genre: ");
                        bookToUpdate.setBookGenre(scanner.nextLine());
                    }
                    case 8 -> {
                        System.out.print("Enter new Publisher: ");
                        bookToUpdate.setBookPublisher(scanner.nextLine());
                    }
                    case 0 -> {
                        continueUpdate = false;
                        System.out.println("Finished updating book information.");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        } else if (documentToUpdate instanceof Thesis) {
            System.out.println("Updating thesis information. Choose the attribute you want to update:");
            boolean continueUpdate = true;

            while (continueUpdate) {
                System.out.println("[1] Quantity");
                System.out.println("[2] Title");
                System.out.println("[3] Author");
                System.out.println("[4] Description");
                System.out.println("[5] Language");
                System.out.println("[6] Number of Pages");
                System.out.println("[7] Subject");
                System.out.println("[8] Degree");
                System.out.println("[9] University");
                System.out.println("[0] Finish updating");

                System.out.print("Enter the number corresponding to the attribute to update: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter new Quantity: ");
                        thesisToUpdate.documentQuantity = scanner.nextInt();
                        scanner.nextLine();
                    }
                    case 2 -> {
                        System.out.print("Enter new Title: ");
                        thesisToUpdate.setDocumentTitle(scanner.nextLine());
                    }
                    case 3 -> {
                        System.out.print("Enter new Author: ");
                        thesisToUpdate.setDocumentAuthor(scanner.nextLine());
                    }
                    case 4 -> {
                        System.out.print("Enter new Description: ");
                        thesisToUpdate.setDocumentDescription(scanner.nextLine());
                    }
                    case 5 -> {
                        System.out.print("Enter new Language: ");
                        thesisToUpdate.setDocumentLanguage(scanner.nextLine());
                    }
                    case 6 -> {
                        System.out.print("Enter new Number of Pages: ");
                        thesisToUpdate.setDocumentPage(scanner.nextInt());
                        scanner.nextLine();
                    }
                    case 7 -> {
                        System.out.print("Enter new Subject: ");
                        thesisToUpdate.setThesisSubject(scanner.nextLine());
                    }
                    case 8 -> {
                        System.out.print("Enter new Degree: ");
                        thesisToUpdate.setThesisDegree(scanner.nextLine());
                    }
                    case 9 -> {
                        System.out.print("Enter new University: ");
                        thesisToUpdate.setThesisUniversity(scanner.nextLine());
                    }
                    case 0 -> {
                        continueUpdate = false;
                        System.out.println("Finished updating thesis information.");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        } else if (documentToUpdate instanceof Magazine) {
            System.out.println("Updating magazine information. Choose the attribute you want to update:");
            boolean continueUpdate = true;

            while (continueUpdate) {
                System.out.println("[1] Quantity");
                System.out.println("[2] Title");
                System.out.println("[3] Author");
                System.out.println("[4] Description");
                System.out.println("[5] Language");
                System.out.println("[6] Number of Pages");
                System.out.println("[7] Subject");
                System.out.println("[8] Frequency");
                System.out.println("[9] Issue Number");
                System.out.println("[0] Finish updating");

                System.out.print("Enter the number corresponding to the attribute to update: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter new Quantity: ");
                        magazineToUpdate.documentQuantity = scanner.nextInt();
                        scanner.nextLine();
                    }
                    case 2 -> {
                        System.out.print("Enter new Title: ");
                        magazineToUpdate.setDocumentTitle(scanner.nextLine());
                    }
                    case 3 -> {
                        System.out.print("Enter new Author: ");
                        magazineToUpdate.setDocumentAuthor(scanner.nextLine());
                    }
                    case 4 -> {
                        System.out.print("Enter new Description: ");
                        magazineToUpdate.setDocumentDescription(scanner.nextLine());
                    }
                    case 5 -> {
                        System.out.print("Enter new Language: ");
                        magazineToUpdate.setDocumentLanguage(scanner.nextLine());
                    }
                    case 6 -> {
                        System.out.print("Enter new Number of Pages: ");
                        magazineToUpdate.setDocumentPage(scanner.nextInt());
                        scanner.nextLine();
                    }
                    case 7 -> {
                        System.out.print("Enter new Subject: ");
                        magazineToUpdate.setMagazineSubject(scanner.nextLine());
                    }
                    case 8 -> {
                        System.out.print("Enter new Frequency: ");
                        magazineToUpdate.setMagazineFrequency(scanner.nextInt());
                        scanner.nextLine();
                    }
                    case 9 -> {
                        System.out.print("Enter new Issue Number: ");
                        magazineToUpdate.setMagazineIssueNumb(scanner.nextInt());
                        scanner.nextLine();
                    }
                    case 0 -> {
                        continueUpdate = false;
                        System.out.println("Finished updating magazine information.");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Document not found.");
        }
    }

}
