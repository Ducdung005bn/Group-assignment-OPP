/**
 * The {@code Document} class represents a document with various properties
 * such as title, author, description, language, page count and ISBN. It also
 * provides methods to set and get these properties.
 * <p>
 * This class is used to model a document that can be managed in a document
 * management system.
 * </p>
 */
public class Document {
    public int documentQuantity;
    private String documentTitle;
    private String documentAuthor;
    private String documentDescription;
    private String documentLanguage;
    private int documentPage;
    private String documentISBN;

    /**
     * Prints the document's information to the console.
     */
    public void printDocInfo() {
        System.out.println("Document Quantity : " + documentQuantity);
        System.out.println("Document Title : " + documentTitle);
        System.out.println("Document Author : " + documentAuthor);
        System.out.println("Document Description : " + documentDescription);
        System.out.println("Document Language : " + documentLanguage);
        System.out.println("Document Page : " + documentPage);
        System.out.println("Document ISBN : " + documentISBN);
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public void setDocumentAuthor(String documentAuthor) {
        this.documentAuthor = documentAuthor;
    }

    public void setDocumentDescription(String documentDescription) {
        this.documentDescription = documentDescription;
    }

    public void setDocumentLanguage(String documentLanguage) {
        this.documentLanguage = documentLanguage;
    }

    public void setDocumentPage(int documentPage) {
        this.documentPage = documentPage;
    }

    public void setDocumentISBN(String documentISBN) {
        this.documentISBN = documentISBN;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public String getDocumentAuthor() {
        return documentAuthor;
    }

    public String getDocumentDescription() {
        return documentDescription;
    }

    public String getDocumentLanguage() {
        return documentLanguage;
    }

    public int getDocumentPage() {
        return documentPage;
    }

    public String getDocumentISBN() {
        return documentISBN;
    }
}
