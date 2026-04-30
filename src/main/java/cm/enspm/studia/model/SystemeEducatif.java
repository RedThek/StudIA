package cm.enspm.studia.model;

public class SystemeEducatif {

    private int identifiantSysteme;
    private String libelle;
    private String designation;

    public SystemeEducatif(int identifiantSysteme, String libelle, String designation) {
        this.identifiantSysteme = identifiantSysteme;
        this.libelle = libelle;
        this.designation = designation;
    }

    public int getIdentifiantSysteme() {
        return identifiantSysteme;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getDesignation() {
        return designation;
    }

    public void setIdentifiantSysteme(int identifiantSysteme) {
        this.identifiantSysteme = identifiantSysteme;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
