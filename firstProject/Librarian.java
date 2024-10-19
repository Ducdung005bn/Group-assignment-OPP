import java.util.Vector;

public class Librarian extends User {
    private int librarianSalary;
    private static Vector<Librarian> librarianList = new Vector<>();
    private LibraryManagementSystem librarySystem;

    public Librarian(int salary, LibraryManagementSystem librarySystem) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.librarianSalary = salary;
        this.librarySystem = librarySystem;
    }

    @Override
    public void printUserInfo() {
        super.printUserInfo();
        System.out.println("Salary: " + librarianSalary);
    }

    public int getLibrarianSalary() {
        return librarianSalary;
    }

    public void setLibrarianSalary(int salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.librarianSalary = salary;
    }

    public void addLibrarian(Librarian l) {
        librarianList.add(l);
    }

    public static Vector<Librarian> getLibrarians() {
        return librarianList;
    }

    public void addDocument(Document d) {
        if (d instanceof Book) {
            librarySystem.bookList.add((Book) d);
        } else if (d instanceof Thesis) {
            librarySystem.thesisList.add((Thesis) d);
        } else if (d instanceof Magazine) {
            librarySystem.magazineList.add((Magazine) d);
        }
    }

    public void removeDocument(Document d) {
        if (d instanceof Book) {
            librarySystem.bookList.remove(d);
        } else if (d instanceof Thesis) {
            librarySystem.thesisList.remove(d);
        } else if (d instanceof Magazine) {
            librarySystem.magazineList.remove(d);
        }
    }

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
