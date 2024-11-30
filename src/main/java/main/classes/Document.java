package main.classes;
import java.io.Serializable;


/**
 * The {@code Document} class represents a document with various properties
 * such as title, author, description, language, page count and ISBN. It also
 * provides methods to set and get these properties.
 * <p>
 * This class is used to model a document that can be managed in a document
 * management system.
 * </p>
 */
public class Document implements Serializable {
    public int documentQuantity;
    private int documentQuantityAll;
    private String documentTitle;
    private String documentAuthor;
    private String documentDescription;
    private String documentLanguage;
    private int documentPage;
    private String documentISBN;
    private String documentGoogleLink;
    private String documentImageUrl;

    public Document() {
        documentQuantity = 0;
        documentQuantityAll = 0;
        documentTitle = "";
        documentAuthor = "";
        documentDescription = "";
        documentLanguage = "";
        documentPage = 0;
        documentISBN = "";
        documentGoogleLink = "";
        documentImageUrl = "";
    }

    public int getDocumentQuantityAll() {
        return documentQuantityAll;
    }

    public void setDocumentQuantityAll(int documentQuantityAll) {
        this.documentQuantityAll = documentQuantityAll;
        this.documentQuantity = documentQuantityAll;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public String getDocumentAuthor() {
        return documentAuthor;
    }

    public void setDocumentAuthor(String documentAuthor) {
        this.documentAuthor = documentAuthor;
    }

    public String getDocumentDescription() {
        return documentDescription;
    }

    public void setDocumentDescription(String documentDescription) {
        this.documentDescription = documentDescription;
    }

    public String getDocumentLanguage() {
        return documentLanguage;
    }

    public void setDocumentLanguage(String documentLanguage) {
        this.documentLanguage = documentLanguage;
    }

    public int getDocumentPage() {
        return documentPage;
    }

    public void setDocumentPage(int documentPage) {
        this.documentPage = documentPage;
    }

    public String getDocumentISBN() {
        return documentISBN;
    }

    public void setDocumentISBN(String documentISBN) {
        this.documentISBN = documentISBN;
    }

    public String getDocumentImageUrl() {
        return documentImageUrl;
    }

    public void setDocumentImageUrl(String documentImageUrl) {
        this.documentImageUrl = documentImageUrl;
    }

    public String getDocumentGoogleLink() {
        return documentGoogleLink;
    }

    public void setDocumentGoogleLink(String documentGoogleLink) {
        this.documentGoogleLink = documentGoogleLink;
    }
}
