public class Document {
    private int documentQuantity;
    private String documentTitle;
    private String documentAuthor;
    private String documentDescription;
    private String documentLanguage;
    private int documentPage;
    private String documentISBN;

    Document(int documentQuantity, String documentTitle, String documentAuthor, String documentDescription,
             String documentLanguage, int documentPage) {
        this.documentQuantity = documentQuantity;
        this.documentTitle = documentTitle;
        this.documentAuthor = documentAuthor;
        this.documentDescription = documentDescription;
        this.documentLanguage = documentLanguage;
        this.documentPage = documentPage;
    }

    public void printDocInfo() {
        System.out.println("Document Quantity : " + this.documentQuantity);
        System.out.println("Document Title : " + this.documentTitle);
        System.out.println("Document Author : " + this.documentAuthor);
        System.out.println("Document Description : " + this.documentDescription);
        System.out.println("Document Language : " + this.documentLanguage);
        System.out.println("Document Page : " + this.documentPage);
        System.out.println("Document ISBN : " + this.documentISBN);
    }

    public void setDocumentQuantity(int documentQuantity) {
        this.documentQuantity = documentQuantity;
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

    public int getDocumentQuantity() {
        return documentQuantity;
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
