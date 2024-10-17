import java.sql.SQLOutput;

public class Thesis extends Document{
    private String thesisSubject;
    private String thesisDegree;
    private String thesisUniversity;

    Thesis(int documentQuantity, String documentTitle, String documentAuthor, String documentDescription,
           String documentLanguage, int documentPage, String thesisSubject,
           String thesisDegree, String thesisUniversity) {
        super(documentQuantity, documentTitle, documentAuthor, documentDescription, documentLanguage, documentPage);
        this.thesisDegree = thesisDegree;
        this.thesisSubject = thesisSubject;
        this.thesisUniversity = thesisUniversity;
    }

    @Override
    public void printDocInfo() {
        super.printDocInfo();
        System.out.println(this.thesisSubject);
        System.out.println(this.thesisDegree);
        System.out.println(this.thesisUniversity);
    }

    public void setThesisSubject(String thesisSubject) {
        this.thesisSubject = thesisSubject;
    }

    public void setThesisDegree(String thesisDegree) {
        this.thesisDegree = thesisDegree;
    }

    public void setThesisUniversity(String thesisUniversity) {
        this.thesisUniversity = thesisUniversity;
    }

    public String getThesisSubject() {
        return this.thesisSubject;
    }

    public String getThesisDegree() {
        return this.thesisDegree;
    }

    public String getThesisUniversity() {
        return this.thesisUniversity;
    }
}
