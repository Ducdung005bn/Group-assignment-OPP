public class Magazine extends Document{
    private String magazineSubject;
    private int magazineFrequency;
    private int magazineIssueNumb;

    Magazine(int documentQuantity, String documentTitle, String documentAuthor, String documentDescription,
             String documentLanguage, int documentPage, String magazineSubject,
             int magazineFrequency, int magazineIssueNumb) {
        super(documentQuantity, documentTitle, documentAuthor, documentDescription, documentLanguage, documentPage);
        this.magazineSubject = magazineSubject;
        this.magazineFrequency = magazineFrequency;
        this.magazineIssueNumb = magazineIssueNumb;
    }

    @Override
    public void printDocInfo() {
        super.printDocInfo();
        System.out.println(this.magazineSubject);
        System.out.println(this.magazineFrequency);
        System.out.println(this.magazineIssueNumb);
    }

    public void setMagazineSubject(String magazineSubject) {
        this.magazineSubject = magazineSubject;
    }

    public void setMagazineFrequency(int magazineFrequency) {
        this.magazineFrequency = magazineFrequency;
    }

    public void setMagazineIssueNumb(int magazineIssueNumb) {
        this.magazineIssueNumb = magazineIssueNumb;
    }

    public String getMagazineSubject() {
        return this.magazineSubject;
    }

    public int getMagazineFrequency() {
        return this.magazineFrequency;
    }

    public int getMagazineIssueNumb() {
        return this.magazineIssueNumb;
    }
}
