package cm.enspm.studia.model;

/**
 * Classe représentant une classe (groupe d'élèves) de l'école secondaire.
 * Ex : "3ème A", "Terminale S", "2nde B"
 */
public class Classe {

    private int identifiantClasse;           // Code unique (ex: "CL001")
    private String libelle;                   // Nom de la classe (ex: "3ème A")
    private NiveauEtude niveauEtude;                // Niveau scolaire (ex: "3ème", "Terminale")
    private AnneeScolaire anneeScolare;             // Année scolaire (ex: 2024)
    private Employe professeurPrincipal; // Identifiant de l'enseignant principal
    

    /**
    * Constructeur de la classe Classe.
    */
    public Classe(int identifiantClasse, String libelle, NiveauEtude niveauEtude, AnneeScolaire anneeScolare,
            Employe professeurPrincipal) {
        this.identifiantClasse = identifiantClasse;
        this.libelle = libelle;
        this.niveauEtude = niveauEtude;
        this.anneeScolare = anneeScolare;
        this.professeurPrincipal = professeurPrincipal;
    }


    public int getIdentifiantClasse() {
        return identifiantClasse;
    }


    public String getLibelle() {
        return libelle;
    }


    public NiveauEtude getNiveauEtude() {
        return niveauEtude;
    }


    public AnneeScolaire getAnneeScolare() {
        return anneeScolare;
    }


    public Employe getProfesseurPrincipal() {
        return professeurPrincipal;
    }


    public void setIdentifiantClasse(int identifiantClasse) {
        this.identifiantClasse = identifiantClasse;
    }


    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


    public void setNiveauEtude(NiveauEtude niveauEtude) {
        this.niveauEtude = niveauEtude;
    }


    public void setAnneeScolare(AnneeScolaire anneeScolare) {
        this.anneeScolare = anneeScolare;
    }


    public void setProfesseurPrincipal(Employe professeurPrincipal) {
        this.professeurPrincipal = professeurPrincipal;
    }
}
