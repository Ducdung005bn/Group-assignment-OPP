package main.classes.main.opponents;

import java.io.*;
import java.util.Vector;

/**
 * The {@code LibraryManagementSystem} class represents a simple library management system
 * that manages different types of documents (Books, Theses, Magazines) and users (Borrowers, Librarians).
 * It provides methods to print document information, find documents by various criteria,
 * and find users by their ID.
 */
public class LibraryManagementSystem implements Serializable {
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
     * Loads library data from a file named "libraryData.dat".
     * If the file does not exist, it creates a new file with default data.
     * This method reads and deserializes the saved state of the LibraryManagementSystem
     * object from the file and updates the current instance.
     */
    public void loadData() {
        File dataFile = new File("libraryData.dat");

        // Check if the file doesn't exist. If so, create it with default data.
        if (!dataFile.exists()) {
            saveData(); // Save default data to the file.
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("libraryData.dat"))) {
            // Read the serialized LibraryManagementSystem object from the file.
            LibraryManagementSystem loadedData = (LibraryManagementSystem) ois.readObject();

            // Update the current instance's attributes with the loaded data.
            this.userNumb = loadedData.userNumb;
            this.bookList = (Vector<Book>) loadedData.bookList;
            this.thesisList = (Vector<Thesis>) loadedData.thesisList;
            this.magazineList = (Vector<Magazine>) loadedData.magazineList;
            this.borrowerList = (Vector<Borrower>) loadedData.borrowerList;
            this.librarianList = (Vector<Librarian>) loadedData.librarianList;

        } catch (FileNotFoundException e) {
            // Handle the case where the file is not found (should not occur due to the check above).
            System.out.println("File doesn't exist.");
        } catch (IOException | ClassNotFoundException e) {
            // Handle other exceptions, such as deserialization errors or file read errors.
            e.printStackTrace();
        }
    }

    /**
     * Saves the current state of the library management system to a file named "libraryData.dat".
     * This method serializes the LibraryManagementSystem object and writes it to the file.
     */
    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("libraryData.dat"))) {
            // Serialize the current instance and write it to the file.
            oos.writeObject(this);
        } catch (IOException e) {
            // Handle exceptions that may occur during file writing.
            e.printStackTrace();
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
     * @param userID the ID of the user to find
     * @return the {@code User} found or {@code null} if no user matches the ID
     */
    public User findUserByID(int userID) {
        for (User user : getAllUsers()) {
            if(user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }

    /**
     * Finds a user by their unique email address.
     *
     * @param userEmailAddress the email address of the user to find
     * @return the {@code User} found or {@code null} if no user matches the ID
     */
    public User findUserByEmail(String userEmailAddress) {
        for (User user : getAllUsers()) {
            if(user.getUserEmailAddress().equals(userEmailAddress)) {
                return user;
            }
        }
        return null;
    }

    public String findUserPasswordByEmail(String email) {
        User user = findUserByEmail(email);
        if (user == null) {
            return null;
        } else {
            return user.getUserPassword();
        }
    }

    public int findUserIDByEmail(String email) {
        User user = findUserByEmail(email);
        if (user == null) {
            return 0;
        } else {
            return user.getUserID();
        }
    }

    /**
     * Combines all types of documents (Books, Theses, and Magazines) into one list for easier searching.
     *
     * @return a {@code Vector} of all {@code Document}s in the library
     */
    public Vector<Document> getAllDocuments() {
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
