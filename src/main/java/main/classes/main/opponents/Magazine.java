package main.classes.main.opponents;

import java.io.Serializable;

/**
 * The {@code Magazine} class is a subclass of {@code Document} that represents a magazine
 * with additional properties such as subject, frequency of publication, and issue number.
 * <p>
 * It inherits all the properties of the {@code Document} class, and provides additional methods
 * to set and get the subject, publication frequency, and issue number of the magazine.
 * </p>
 */
public class Magazine extends Document implements Serializable {
    private String magazineSubject;
    private int magazineFrequency;
    private int magazineIssueNumb;

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
        return magazineSubject;
    }

    public int getMagazineFrequency() {
        return magazineFrequency;
    }

    public int getMagazineIssueNumb() {
        return magazineIssueNumb;
    }
}
