package library.management.system;
import main.classes.*;
import java.sql.Date;

public class TestDataInitializer {
    public static LibraryManagementSystem initializeLibraryData() {
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        Librarian librarian1 = new Librarian();
        librarian1.setUserName("Nguyen Duc Dung");
        librarian1.setUserDateOfBirth(java.sql.Date.valueOf("2005-11-27"));
        librarian1.setUserEmailAddress("taytran2k0@gmail.com");
        librarian1.setUserPassword("1");
        libraryManagementSystem.userNumb++;
        librarian1.setUserID(libraryManagementSystem.userNumb);
        libraryManagementSystem.librarianList.add(librarian1);

        Librarian librarian2 = new Librarian();
        librarian2.setUserName("Pham Minh Tu");
        librarian2.setUserDateOfBirth(java.sql.Date.valueOf("1998-02-15"));
        librarian2.setUserEmailAddress("minhtu@mail.com");
        librarian2.setUserPassword("minhtu");
        libraryManagementSystem.userNumb++;
        librarian2.setUserID(libraryManagementSystem.userNumb);
        libraryManagementSystem.librarianList.add(librarian2);

        Librarian librarian3 = new Librarian();
        librarian3.setUserName("Tran Thi Lan");
        librarian3.setUserDateOfBirth(java.sql.Date.valueOf("1993-06-08"));
        librarian3.setUserEmailAddress("lantran@mail.com");
        librarian3.setUserPassword("lantran");
        libraryManagementSystem.userNumb++;
        librarian3.setUserID(libraryManagementSystem.userNumb);
        libraryManagementSystem.librarianList.add(librarian3);


        Borrower borrower1 = new Borrower();
        borrower1.setUserName("Le Manh Hung");
        borrower1.setUserDateOfBirth(java.sql.Date.valueOf("2005-07-10"));
        borrower1.setIsStudent(true);
        borrower1.setUserEmailAddress("23020669@vnu.edu.vn");
        borrower1.setUserPassword("4");
        libraryManagementSystem.userNumb++;
        borrower1.setUserID(libraryManagementSystem.userNumb);
        libraryManagementSystem.borrowerList.add(borrower1);

        Borrower borrower2 = new Borrower();
        borrower2.setUserName("Nguyen Thi Lan");
        borrower2.setUserDateOfBirth(java.sql.Date.valueOf("2003-05-15"));
        borrower2.setIsStudent(false);
        borrower2.setUserEmailAddress("0987654321@gmail.com");
        borrower2.setUserPassword("5");
        libraryManagementSystem.userNumb++;
        borrower2.setUserID(libraryManagementSystem.userNumb);
        libraryManagementSystem.borrowerList.add(borrower2);

        Borrower borrower3 = new Borrower();
        borrower3.setUserName("Tran Van Minh");
        borrower3.setUserDateOfBirth(java.sql.Date.valueOf("2002-11-25"));
        borrower3.setIsStudent(true);
        borrower3.setUserEmailAddress("0345678901@gmail.com");
        borrower3.setUserPassword("6");
        libraryManagementSystem.userNumb++;
        borrower3.setUserID(libraryManagementSystem.userNumb);
        libraryManagementSystem.borrowerList.add(borrower3);

        Borrower borrower4 = new Borrower();
        borrower4.setUserName("Pham Minh Tu");
        borrower4.setUserDateOfBirth(java.sql.Date.valueOf("2001-07-10"));
        borrower4.setIsStudent(true);
        borrower4.setUserEmailAddress("minhtu.pham@mail.com");
        borrower4.setUserPassword("7");
        libraryManagementSystem.userNumb++;
        borrower4.setUserID(libraryManagementSystem.userNumb);
        libraryManagementSystem.borrowerList.add(borrower4);

        Borrower borrower5 = new Borrower();
        borrower5.setUserName("Bui Thi Lan Anh");
        borrower5.setUserDateOfBirth(java.sql.Date.valueOf("2003-02-20"));
        borrower5.setIsStudent(false);
        borrower5.setUserEmailAddress("lananh.bui@mail.com");
        borrower5.setUserPassword("8");
        libraryManagementSystem.userNumb++;
        borrower5.setUserID(libraryManagementSystem.userNumb);
        libraryManagementSystem.borrowerList.add(borrower5);

        Borrower borrower6 = new Borrower();
        borrower6.setUserName("Nguyen Thi Mai");
        borrower6.setUserDateOfBirth(java.sql.Date.valueOf("2000-04-15"));
        borrower6.setIsStudent(true);
        borrower6.setUserEmailAddress("mainguyen@mail.com");
        borrower6.setUserPassword("9");
        libraryManagementSystem.userNumb++;
        borrower6.setUserID(libraryManagementSystem.userNumb);

        Borrower borrower7 = new Borrower();
        borrower7.setUserName("Tran Thi Lan");
        borrower7.setUserDateOfBirth(java.sql.Date.valueOf("1998-08-05"));
        borrower7.setIsStudent(true);
        borrower7.setUserEmailAddress("lantran@mail.com");
        borrower7.setUserPassword("10");
        libraryManagementSystem.userNumb++;
        borrower7.setUserID(libraryManagementSystem.userNumb);
        libraryManagementSystem.borrowerList.add(borrower7);

        Borrower borrower8 = new Borrower();
        borrower8.setUserName("Pham Minh Hoang");
        borrower8.setUserDateOfBirth(java.sql.Date.valueOf("2001-03-10"));
        borrower8.setIsStudent(false);
        borrower8.setUserEmailAddress("hoangpham@mail.com");
        borrower8.setUserPassword("11");
        libraryManagementSystem.userNumb++;
        borrower8.setUserID(libraryManagementSystem.userNumb);
        libraryManagementSystem.borrowerList.add(borrower8);

        Borrower borrower9 = new Borrower();
        borrower9.setUserName("Le Thanh Son");
        borrower9.setUserDateOfBirth(java.sql.Date.valueOf("1999-11-25"));
        borrower9.setIsStudent(true);
        borrower9.setUserEmailAddress("sonle@mail.com");
        borrower9.setUserPassword("12");
        libraryManagementSystem.userNumb++;
        borrower9.setUserID(libraryManagementSystem.userNumb);
        libraryManagementSystem.borrowerList.add(borrower9);

        Borrower borrower10 = new Borrower();
        borrower10.setUserName("Nguyen Thi Lan");
        borrower10.setUserDateOfBirth(java.sql.Date.valueOf("2002-06-18"));
        borrower10.setIsStudent(true);
        borrower10.setUserEmailAddress("lannguyen@mail.com");
        borrower10.setUserPassword("13");
        libraryManagementSystem.userNumb++;
        borrower10.setUserID(libraryManagementSystem.userNumb);
        libraryManagementSystem.borrowerList.add(borrower10);

        Borrower borrower11 = new Borrower();
        borrower11.setUserName("Hoang Minh Thu");
        borrower11.setUserDateOfBirth(java.sql.Date.valueOf("2000-01-12"));
        borrower11.setIsStudent(false);
        borrower11.setUserEmailAddress("thuhm@mail.com");
        borrower11.setUserPassword("14");
        libraryManagementSystem.userNumb++;
        borrower11.setUserID(libraryManagementSystem.userNumb);
        libraryManagementSystem.borrowerList.add(borrower11);

        Borrower borrower12 = new Borrower();
        borrower12.setUserName("Pham Thanh Tu");
        borrower12.setUserDateOfBirth(java.sql.Date.valueOf("1997-07-23"));
        borrower12.setIsStudent(true);
        borrower12.setUserEmailAddress("tupham@mail.com");
        borrower12.setUserPassword("15");
        libraryManagementSystem.userNumb++;
        borrower12.setUserID(libraryManagementSystem.userNumb);
        libraryManagementSystem.borrowerList.add(borrower12);

        Book book1 = (Book) GoogleBooksAPI.fetchBook("9780061120084");
        book1.setDocumentQuantityAll(5);
        libraryManagementSystem.bookList.add(book1);

        Book book2 = (Book) GoogleBooksAPI.fetchBook("9781408845611");
        book2.setDocumentQuantityAll(8);
        libraryManagementSystem.bookList.add(book2);

        Book book3 = (Book) GoogleBooksAPI.fetchBook("9780143128540");
        book3.setDocumentQuantityAll(12);
        libraryManagementSystem.bookList.add(book3);

        Book book4 = (Book) GoogleBooksAPI.fetchBook("9781501196844");
        book4.setDocumentQuantityAll(7);
        libraryManagementSystem.bookList.add(book4);

        Book book5 = (Book) GoogleBooksAPI.fetchBook("9780062315007");
        book5.setDocumentQuantityAll(10);
        libraryManagementSystem.bookList.add(book5);

        Book book6 = (Book) GoogleBooksAPI.fetchBook("9780590353427");
        book6.setDocumentQuantityAll(6);
        libraryManagementSystem.bookList.add(book6);

        Book book7 = (Book) GoogleBooksAPI.fetchBook("9780735211292");
        book7.setDocumentQuantityAll(9);
        libraryManagementSystem.bookList.add(book7);

        Book book8 = (Book) GoogleBooksAPI.fetchBook("9780553380163");
        book8.setDocumentQuantityAll(15);
        libraryManagementSystem.bookList.add(book8);

        Book book9 = (Book) GoogleBooksAPI.fetchBook("9780061122415");
        book9.setDocumentQuantityAll(3);
        libraryManagementSystem.bookList.add(book9);

        Book book10 = (Book) GoogleBooksAPI.fetchBook("9781451673214");
        book10.setDocumentQuantityAll(4);
        libraryManagementSystem.bookList.add(book10);
        return libraryManagementSystem;
    }
}


