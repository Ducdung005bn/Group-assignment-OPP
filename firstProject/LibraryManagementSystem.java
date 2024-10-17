import java.util.Vector;

public class LibraryManagementSystem {
    public Vector<Book> bookList;
    public Vector<Thesis> thesisList;
    public Vector<Magazine> magazineList;


    /**
     * method print important information of Library.
     */
    public void printLibraryDocument() {

        for (Book book : bookList) {
            System.out.println(book.getDocumentISBN());
            System.out.printf("The title of the book : %s" + ". Author : %s%n%n",
                    book.getDocumentTitle(), book.getDocumentAuthor());
        }

        for (Thesis thesis : thesisList) {
            System.out.println(thesis.getDocumentISBN());
            System.out.printf("The title of thesis : %s" + ". Author : %s%n%n",
                    thesis.getDocumentTitle(), thesis.getDocumentAuthor());
        }

         for (Magazine magazine : magazineList) {
             System.out.println(magazine.getDocumentISBN());
             System.out.printf("The title of magazine : %s" + ". Author : %s%n%n",
                     magazine.getDocumentTitle(), magazine.getDocumentAuthor());
         }
    }




}
