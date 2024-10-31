import java.util.Vector;
import java.util.Scanner;

/**
 * The {@code LibraryManagementSystem} class represents a simple library management system
 * that manages different types of documents (Books, Theses, Magazines) and users (Borrowers, Librarians).
 * It provides methods to print document information, find documents by various criteria,
 * and find users by their ID.
 */
public class LibraryManagementSystem {
    public static final int MAX_BORROW_LIMIT = 5;
    public static final int BORROW_DURATION_DAYS = 10;
    public int userNumb;
    public Vector<Book> bookList;
    public Vector<Thesis> thesisList;
    public Vector<Magazine> magazineList;
    public Vector<Borrower> borrowerList;
    public Vector<Librarian> librarianList;

    public LibraryManagementSystem() {
        userNumb = 0;
        bookList = new Vector<>();
        thesisList = new Vector<>();
        magazineList = new Vector<>();
        borrowerList = new Vector<>();
        librarianList = new Vector<>();
    }

    /**
     * Prints important information of all documents (Books, Theses, Magazines) in the library.
     * Each document's ISBN, title, and author are printed.
     */
    public void printLibraryDocument() {
        for (Book book : bookList) {
            System.out.printf(book.getDocumentISBN() + ". The title of the book : %s. Author : %s%n",
                    book.getDocumentTitle(), book.getDocumentAuthor());
        }

        for (Thesis thesis : thesisList) {
            System.out.printf(thesis.getDocumentISBN() + ". The title of the thesis : %s. Author : %s%n",
                    thesis.getDocumentTitle(), thesis.getDocumentAuthor());
        }

        for (Magazine magazine : magazineList) {
            System.out.printf(magazine.getDocumentISBN() + ". The title of the magazine : %s. Author : %s%n",
                    magazine.getDocumentTitle(), magazine.getDocumentAuthor());
        }
    }

    /**
     * Finds a document by its ISBN.
     *
     * @param documentISBN the ISBN of the document to find
     * @return the {@code Document} found or {@code null} if no document matches the ISBN
     */
    public Document findDocumentByISBN(String documentISBN) {
        for (Document document : getAllDocuments()) {
            if (document.getDocumentISBN().equals(documentISBN)) {
                return document;
            }
        }
        return null;
    }

    /**
     * Finds all documents with the given title.
     *
     * @param documentTitle the title of the document(s) to find
     * @return a {@code Vector} of documents that match the title, or {@code null} if no documents are found
     */
    public Vector<Document> findDocumentByTitle(String documentTitle) {
        Vector<Document> documentsOfTitle = new Vector<>();
        for (Document document : getAllDocuments()) {
            if (document.getDocumentTitle().equals(documentTitle)) {
                documentsOfTitle.add(document);
            }
        }
        return documentsOfTitle.isEmpty() ? null : documentsOfTitle;
    }

    /**
     * Finds all documents by the given author.
     *
     * @param documentAuthor the author of the document(s) to find
     * @return a {@code Vector} of documents that match the author, or {@code null} if no documents are found
     */
    public Vector<Document> findDocumentByAuthor(String documentAuthor) {
        Vector<Document> documentsOfAuthor = new Vector<>();
        for (Document document : getAllDocuments()) {
            if (document.getDocumentAuthor().equals(documentAuthor)) {
                documentsOfAuthor.add(document);
            }
        }
        return documentsOfAuthor.isEmpty() ? null : documentsOfAuthor;
    }

    public void findAndPrintDocuments() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Search by: ");
        System.out.println("[1] ISBN");
        System.out.println("[2] Title");
        System.out.println("[3] Author");
        System.out.print("Enter your choice: ");

        String searchInput = scanner.nextLine();

        switch (searchInput) {
            case "1":
                System.out.print("Enter ISBN: ");
                String iSBNSearch = scanner.nextLine();
                Document foundDocument = findDocumentByISBN(iSBNSearch);
                if (foundDocument != null) {
                    System.out.printf(foundDocument.getDocumentISBN() + ". The title of the book : %s. Author : %s%n",
                            foundDocument.getDocumentTitle(), foundDocument.getDocumentAuthor());
                } else {
                    System.out.println("No document found with ISBN: " + iSBNSearch);
                }
                break;

            case "2":
                System.out.print("Enter Title: ");
                String titleSearch = scanner.nextLine();
                Vector<Document> documentsByTitle = findDocumentByTitle(titleSearch);
                if (documentsByTitle != null) {
                    for (Document doc : documentsByTitle) {
                        System.out.printf(doc.getDocumentISBN() + ". The title of the book : %s. Author : %s%n",
                                doc.getDocumentTitle(), doc.getDocumentAuthor());
                    }
                } else {
                    System.out.println("No documents found with title: " + titleSearch);
                }
                break;

            case "3":
                System.out.print("Enter Author: ");
                String authorSearch = scanner.nextLine();
                Vector<Document> documentsByAuthor = findDocumentByAuthor(authorSearch);
                if (documentsByAuthor != null) {
                    for (Document doc : documentsByAuthor) {
                        System.out.printf(doc.getDocumentISBN() + ". The title of the book : %s. Author : %s%n",
                                doc.getDocumentTitle(), doc.getDocumentAuthor());
                    }
                } else {
                    System.out.println("No documents found by author: " + authorSearch);
                }
                break;

            default:
                System.out.println("Invalid search option.");
                break;
        }
    }

    /**
     * Finds a user by their unique ID.
     *
     * @param userID the ID of the user to find
     * @return the {@code User} found or {@code null} if no user matches the ID
     */
    public User findUser(int userID) {
        for (User user : getAllUsers()) {
            if(user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }

    public static int setValidInteger() {
        Scanner scanner = new Scanner(System.in);
        int value = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                value = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        return value;
    }

    /**
     * Combines all types of documents (Books, Theses, and Magazines) into one list for easier searching.
     *
     * @return a {@code Vector} of all {@code Document}s in the library
     */
    private Vector<Document> getAllDocuments() {
        Vector<Document> allDocuments = new Vector<>();
        allDocuments.addAll(bookList);
        allDocuments.addAll(thesisList);
        allDocuments.addAll(magazineList);
        return allDocuments;
    }

    /**
     * Combines all users (Borrowers and Librarians) into one list for easier searching.
     *
     * @return a {@code Vector} of all {@code User}s in the library
     */
    private Vector<User> getAllUsers() {
        Vector<User> allUsers = new Vector<>();
        allUsers.addAll(borrowerList);
        allUsers.addAll(librarianList);
        return allUsers;
    }
}
