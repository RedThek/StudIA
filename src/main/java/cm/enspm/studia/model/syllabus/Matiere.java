package cm.enspm.studia.model.syllabus;

public class Matiere {

    /**
     * identifiant de la matière : Un entier unique pour identifier la matière (ex: 1, 2, 3)
     */
    private int identifiantMatiere;

    /**
     * code de la matière : Un code alphanumérique pour représenter la matière (ex: "MATHS", "PHY")
     */
    private String codeMatiere;

    /**
     * libellé de la matière : Le nom complet de la matière (ex: "Mathématiques", "Physique")
     */
    private String libelle;
    
    /**
     * Constructeur de la classe Matiere
     * @param identifiantMatiere
     * @param codeMatiere
     * @param libelle
     */
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
