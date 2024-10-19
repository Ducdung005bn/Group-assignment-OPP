/**
 * The {@code Magazine} class is a subclass of {@code Document} that represents a magazine
 * with additional properties such as subject, frequency of publication, and issue number.
 * <p>
 * It inherits all the properties of the {@code Document} class, and provides additional methods
 * to set and retrieve the subject, publication frequency, and issue number of the magazine.
 * </p>
 */
public class Magazine extends Document {
    private String magazineSubject;
    private int magazineFrequency;
    private int magazineIssueNumb;


    /**
     * Overrides the {@code printDocInfo} method from the {@code Document} class
     * to print additional information specific to a magazine, such as its subject,
     * frequency, and issue number.
     */
    @Override
    public void printDocInfo() {
        super.printDocInfo();
        System.out.println("Magazine Subject: " + this.magazineSubject);
        System.out.println("Magazine Frequency: " + this.magazineFrequency);
        System.out.println("Magazine Issue Number: " + this.magazineIssueNumb);
    }

    /**
     * Sets the subject of the magazine.
     *
     * @param magazineSubject the subject to be set
     */
    public void setMagazineSubject(String magazineSubject) {
        this.magazineSubject = magazineSubject;
    }

    /**
     * Sets the frequency of magazine publication.
     *
     * @param magazineFrequency the frequency to be set (e.g., 12 for monthly, 52 for weekly)
     */
    public void setMagazineFrequency(int magazineFrequency) {
        this.magazineFrequency = magazineFrequency;
    }

    /**
     * Sets the issue number of the magazine.
     *
     * @param magazineIssueNumb the issue number to be set
     */
    public void setMagazineIssueNumb(int magazineIssueNumb) {
        this.magazineIssueNumb = magazineIssueNumb;
    }

    /**
     * Gets the subject of the magazine.
     *
     * @return the subject of the magazine
     */
    public String getMagazineSubject() {
        return this.magazineSubject;
    }

    /**
     * Gets the frequency of magazine publication.
     *
     * @return the frequency of publication (e.g., 12 for monthly)
     */
    public int getMagazineFrequency() {
        return this.magazineFrequency;
    }

    /**
     * Gets the issue number of the magazine.
     *
     * @return the issue number of the magazine
     */
    public int getMagazineIssueNumb() {
        return this.magazineIssueNumb;
    }
}
