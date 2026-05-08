package cm.enspm.studia.model.syllabus;

public class Matiere {

    /**
     * code de la matière : Un code alphanumérique pour représenter la matière (ex: "MATHS", "PHY")
     */
    private String codeMatiere;

    /**
     * 
     */
    private String designation;

    /**
     * 
     */
    private Integer coefficient;

    public Matiere(String codeMatiere, String designation, Integer coefficient) {
        this.codeMatiere = codeMatiere;
        this.designation = designation;
        this.coefficient = coefficient;
    }

    public String getCodeMatiere() {
        return codeMatiere;
    }

    public String getDesignation() {
        return designation;
    }

    public Integer getCoefficient() {
        return coefficient;
    }

    public void setCodeMatiere(String codeMatiere) {
        this.codeMatiere = codeMatiere;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setCoefficient(Integer coefficient) {
        this.coefficient = coefficient;
    }

    
}
