/**
 * The {@code Document} class represents a document with various properties
 * such as title, author, description, language, and page count. It also
 * provides methods to set and retrieve these properties, along with an ISBN number.
 * <p>
 * This class is used to model a physical or digital document that can be
 * managed in a document management system.
 * </p>
 */
public class Document {
    // Instance variables
    private int documentQuantity;
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
        System.out.println("Document Quantity : " + this.documentQuantity);
        System.out.println("Document Title : " + this.documentTitle);
        System.out.println("Document Author : " + this.documentAuthor);
        System.out.println("Document Description : " + this.documentDescription);
        System.out.println("Document Language : " + this.documentLanguage);
        System.out.println("Document Page : " + this.documentPage);
        System.out.println("Document ISBN : " + this.documentISBN);
    }

    /**
     * Sets the quantity of documents available.
     *
     * @param documentQuantity the quantity to be set
     */
    public void setDocumentQuantity(int documentQuantity) {
        this.documentQuantity = documentQuantity;
    }

    /**
     * Sets the document's title.
     *
     * @param documentTitle the title to be set
     */
    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    /**
     * Sets the document's author.
     *
     * @param documentAuthor the author to be set
     */
    public void setDocumentAuthor(String documentAuthor) {
        this.documentAuthor = documentAuthor;
    }

    /**
     * Sets the document's description.
     *
     * @param documentDescription the description to be set
     */
    public void setDocumentDescription(String documentDescription) {
        this.documentDescription = documentDescription;
    }

    /**
     * Sets the language in which the document is written.
     *
     * @param documentLanguage the language to be set
     */
    public void setDocumentLanguage(String documentLanguage) {
        this.documentLanguage = documentLanguage;
    }

    /**
     * Sets the total number of pages in the document.
     *
     * @param documentPage the number of pages to be set
     */
    public void setDocumentPage(int documentPage) {
        this.documentPage = documentPage;
    }

    /**
     * Sets the document's ISBN number.
     *
     * @param documentISBN the ISBN number to be set
     */
    public void setDocumentISBN(String documentISBN) {
        this.documentISBN = documentISBN;
    }

    /**
     * Gets the quantity of documents available.
     *
     * @return the quantity of documents
     */
    public int getDocumentQuantity() {
        return documentQuantity;
    }

    /**
     * Gets the document's title.
     *
     * @return the title of the document
     */
    public String getDocumentTitle() {
        return documentTitle;
    }

    /**
     * Gets the document's author.
     *
     * @return the author of the document
     */
    public String getDocumentAuthor() {
        return documentAuthor;
    }

    /**
     * Gets the document's description.
     *
     * @return the description of the document
     */
    public String getDocumentDescription() {
        return documentDescription;
    }

    /**
     * Gets the language in which the document is written.
     *
     * @return the document's language
     */
    public String getDocumentLanguage() {
        return documentLanguage;
    }

    /**
     * Gets the total number of pages in the document.
     *
     * @return the total number of pages
     */
    public int getDocumentPage() {
        return documentPage;
    }

    /**
     * Gets the document's ISBN number.
     *
     * @return the document's ISBN number
     */
    public String getDocumentISBN() {
        return documentISBN;
    }
}
