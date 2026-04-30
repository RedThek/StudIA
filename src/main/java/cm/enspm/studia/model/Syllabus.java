package cm.enspm.studia.model;

public class Syllabus {
    
    private NiveauEtude niveauEtude;
    private Matiere matiere;
    private int coefficient;

    
    public Syllabus(NiveauEtude niveauEtude, Matiere matiere, int coefficient) {
        this.niveauEtude = niveauEtude;
        this.matiere = matiere;
        this.coefficient = coefficient;
    }


    public NiveauEtude getNiveauEtude() {
        return niveauEtude;
    }


    public Matiere getMatiere() {
        return matiere;
    }


    public int getCoefficient() {
        return coefficient;
    }


    public void setNiveauEtude(NiveauEtude niveauEtude) {
        this.niveauEtude = niveauEtude;
    }


    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }


    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    
}
