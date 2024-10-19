import java.util.Vector;

/**
 * The {@code LibraryManagementSystem} class represents a simple library management system
 * that manages different types of documents (Books, Theses, Magazines) and users (Borrowers, Librarians).
 * It provides methods to print document information, find documents by various criteria,
 * and find users by their ID.
 */
public class LibraryManagementSystem {
    public Vector<Book> bookList;
    public Vector<Thesis> thesisList;
    public Vector<Magazine> magazineList;
    public Vector<Borrower> borrowerList;
    public Vector<Librarian> librarianList;

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

    /**
     * Prints important information of all documents (Books, Theses, Magazines) in the library.
     * Each document's ISBN, title, and author are printed.
     */
    public void printLibraryDocument() {
        for (Book book : bookList) {
            System.out.println(book.getDocumentISBN());
            System.out.printf("The title of the book : %s. Author : %s%n%n",
                    book.getDocumentTitle(), book.getDocumentAuthor());
        }

        for (Thesis thesis : thesisList) {
            System.out.println(thesis.getDocumentISBN());
            System.out.printf("The title of thesis : %s. Author : %s%n%n",
                    thesis.getDocumentTitle(), thesis.getDocumentAuthor());
        }

        for (Magazine magazine : magazineList) {
            System.out.println(magazine.getDocumentISBN());
            System.out.printf("The title of magazine : %s. Author : %s%n%n",
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

    /**
     * Finds a user by their unique ID.
     *
     * @param UserID the ID of the user to find
     * @return the {@code User} found or {@code null} if no user matches the ID
     */
    public User findUser(int UserID) {
        for (User user : getAllUsers()) {
            if(user.getId() == UserID) {
                return user;
            }
        }
        return null;
    }
}
