public class Book extends Document{
    private String bookGenre;
    private String bookPublisher;

    public Book(int documentQuantity, String documentTitle, String documentAuthor, String documentDescription,
         String documentLanguage, int documentPage, String bookGenre, String bookPublisher){
         super(documentQuantity, documentTitle, documentAuthor, documentDescription, documentLanguage, documentPage);
         this.bookGenre = bookGenre;
         this.bookPublisher = bookPublisher;
    }

    @Override
    public void printDocInfo() {
        super.printDocInfo();
        System.out.println("Book Genre: " + bookGenre);
        System.out.println("Book Publisher: " + bookPublisher);
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

}
