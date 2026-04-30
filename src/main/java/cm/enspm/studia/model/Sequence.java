package cm.enspm.studia.model;

public class Sequence {
    private int idSequence;
    private String libelle;
    private String designation;
    private Trimestre trimestre;
    
    
    public Sequence(int idSequence, String libelle, String designation, Trimestre trimestre) {
        this.idSequence = idSequence;
        this.libelle = libelle;
        this.designation = designation;
        this.trimestre = trimestre;
    }


    public int getIdSequence() {
        return idSequence;
    }


    public String getLibelle() {
        return libelle;
    }


    public String getDesignation() {
        return designation;
    }


    public Trimestre getTrimestre() {
        return trimestre;
    }


    public void setIdSequence(int idSequence) {
        this.idSequence = idSequence;
    }


    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


    public void setDesignation(String designation) {
        this.designation = designation;
    }


    public void setTrimestre(Trimestre trimestre) {
        this.trimestre = trimestre;
    }

    
}
