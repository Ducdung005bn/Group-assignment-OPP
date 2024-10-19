/**
 * The {@code Thesis} class is a subclass of {@code Document} that represents an academic thesis
 * with additional properties such as subject, degree level, and university.
 * <p>
 * It inherits all the properties of the {@code Document} class, and provides additional methods
 * to set and retrieve the subject, degree, and university of the thesis.
 * </p>
 */
public class Thesis extends Document {
    private String thesisSubject;
    private String thesisDegree;
    private String thesisUniversity;


    /**
     * Overrides the {@code printDocInfo} method from the {@code Document} class
     * to print additional information specific to a thesis, such as its subject,
     * degree, and university.
     */
    @Override
    public void printDocInfo() {
        super.printDocInfo();
        System.out.println("Thesis Subject: " + this.thesisSubject);
        System.out.println("Thesis Degree: " + this.thesisDegree);
        System.out.println("Thesis University: " + this.thesisUniversity);
    }

    /**
     * Sets the subject of the thesis.
     *
     * @param thesisSubject the subject to be set
     */
    public void setThesisSubject(String thesisSubject) {
        this.thesisSubject = thesisSubject;
    }

    /**
     * Sets the academic degree level of the thesis.
     *
     * @param thesisDegree the degree level to be set (e.g., Bachelor's, Master's, PhD)
     */
    public void setThesisDegree(String thesisDegree) {
        this.thesisDegree = thesisDegree;
    }

    /**
     * Sets the university where the thesis was submitted.
     *
     * @param thesisUniversity the university to be set
     */
    public void setThesisUniversity(String thesisUniversity) {
        this.thesisUniversity = thesisUniversity;
    }

    /**
     * Gets the subject of the thesis.
     *
     * @return the subject of the thesis
     */
    public String getThesisSubject() {
        return this.thesisSubject;
    }

    /**
     * Gets the academic degree level of the thesis.
     *
     * @return the degree level of the thesis (e.g., Bachelor's, Master's, PhD)
     */
    public String getThesisDegree() {
        return this.thesisDegree;
    }

    /**
     * Gets the university where the thesis was submitted.
     *
     * @return the university where the thesis was submitted
     */
    public String getThesisUniversity() {
        return this.thesisUniversity;
    }
}
