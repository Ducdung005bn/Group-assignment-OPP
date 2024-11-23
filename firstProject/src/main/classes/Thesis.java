package main.classes;

import java.io.Serializable;

/**
 * The {@code Thesis} class is a subclass of {@code Document} that represents an academic thesis
 * with additional properties such as subject, degree level, and university.
 * <p>
 * It inherits all the properties of the {@code Document} class, and provides additional methods
 * to set and get the subject, degree, and university of the thesis.
 * </p>
 */
public class Thesis extends Document implements Serializable {
    private String thesisSubject;
    private String thesisDegree;
    private String thesisUniversity;

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
        return thesisSubject;
    }

    public String getThesisDegree() {
        return thesisDegree;
    }

    public String getThesisUniversity() {
        return thesisUniversity;
    }
}
