import java.util.Vector;

/**
 * Represents a librarian in the library system. This class extends the User class
 * and provides functionalities for managing documents and storing librarian details.
 */
public class Librarian extends User {
    private int librarianSalary;
    private static Vector<Librarian> librarianList = new Vector<>();
    private LibraryManagementSystem librarySystem;

    /**
     * Constructs a Librarian with a specified salary and associated library system.
     *
     * @param salary the salary of the librarian.
     * @param librarySystem the library management system associated with the librarian.
     * @throws IllegalArgumentException if the salary is negative.
     */
    public Librarian(int salary, LibraryManagementSystem librarySystem) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.librarianSalary = salary;
        this.librarySystem = librarySystem;
    }

    /**
     * Prints the user information, including the librarian's salary.
     */
    @Override
    public void printUserInfo() {
        super.printUserInfo();
        System.out.println("Salary: " + librarianSalary);
    }

    /**
     * Gets the librarian's salary.
     *
     * @return the librarian's salary.
     */
    public int getLibrarianSalary() {
        return librarianSalary;
    }

    /**
     * Sets the librarian's salary.
     *
     * @param salary the salary to set.
     * @throws IllegalArgumentException if the salary is negative.
     */
    public void setLibrarianSalary(int salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.librarianSalary = salary;
    }

    /**
     * Adds a librarian to the librarian list.
     *
     * @param l the librarian to add.
     */
    public void addLibrarian(Librarian l) {
        librarianList.add(l);
    }

    /**
     * Gets the list of librarians.
     *
     * @return a vector containing all librarians.
     */
    public static Vector<Librarian> getLibrarians() {
        return librarianList;
    }

    /**
     * Adds a document to the library system.
     *
     * @param d the document to add.
     */
    public void addDocument(Document d) {
        if (d instanceof Book) {
            librarySystem.bookList.add((Book) d);
        } else if (d instanceof Thesis) {
            librarySystem.thesisList.add((Thesis) d);
        } else if (d instanceof Magazine) {
            librarySystem.magazineList.add((Magazine) d);
        }
    }

    /**
     * Removes a document from the library system.
     *
     * @param d the document to remove.
     */
    public void removeDocument(Document d) {
        if (d instanceof Book) {
            librarySystem.bookList.remove(d);
        } else if (d instanceof Thesis) {
            librarySystem.thesisList.remove(d);
        } else if (d instanceof Magazine) {
            librarySystem.magazineList.remove(d);
        }
    }

    /**
     * Updates a document in the library system.
     *
     * @param d the document to update.
     * @return true if the document was updated successfully, false otherwise.
     */
    public boolean updateDocument(Document d) {
        if (d instanceof Book) {
            for (int i = 0; i < librarySystem.bookList.size(); i++) {
                if (librarySystem.bookList.get(i).getDocumentISBN().equals(d.getDocumentISBN())) {
                    librarySystem.bookList.set(i, (Book) d);
                    return true;  // Document updated successfully
                }
            }
        } else if (d instanceof Thesis) {
            for (int i = 0; i < librarySystem.thesisList.size(); i++) {
                if (librarySystem.thesisList.get(i).getDocumentISBN().equals(d.getDocumentISBN())) {
                    librarySystem.thesisList.set(i, (Thesis) d);
                    return true;  // Document updated successfully
                }
            }
        } else if (d instanceof Magazine) {
            for (int i = 0; i < librarySystem.magazineList.size(); i++) {
                if (librarySystem.magazineList.get(i).getDocumentISBN().equals(d.getDocumentISBN())) {
                    librarySystem.magazineList.set(i, (Magazine) d);
                    return true;  // Document updated successfully
                }
            }
        }
        return false;  // Document not found for update
    }
}
