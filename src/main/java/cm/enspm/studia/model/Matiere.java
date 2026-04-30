package cm.enspm.studia.model;

public class Matiere {

    private int identifiantMatiere;      // Code unique (ex: "001")
    private String codeMatiere;           // Code de la matière (ex: "MAT222", "PHY")
    private String libelle;                   // Nom de la matière (ex: "Mathématiques")
    
    public Matiere(int identifiantMatiere, String codeMatiere, String libelle) {
        this.identifiantMatiere = identifiantMatiere;
        this.codeMatiere = codeMatiere;
        this.libelle = libelle;
    }

    public int getIdentifiantMatiere() {
        return identifiantMatiere;
    }

    public String getCodeMatiere() {
        return codeMatiere;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setIdentifiantMatiere(int identifiantMatiere) {
        this.identifiantMatiere = identifiantMatiere;
    }

    public void setCodeMatiere(String codeMatiere) {
        this.codeMatiere = codeMatiere;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
