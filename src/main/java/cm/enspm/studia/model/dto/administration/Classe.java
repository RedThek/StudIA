package cm.enspm.studia.model.dto.administration;

import cm.enspm.studia.model.personnes.Employe;
import cm.enspm.studia.model.syllabus.AnneeScolaire;
import cm.enspm.studia.model.syllabus.NiveauEtude;

/**
 * Classe représentant une classe (groupe d'élèves) de l'école secondaire pour une année scolaire donnée.
 * Ex : "3ème A 2022-2023", "Terminale S 2019-2020", "2nde B 2020-2021"
 */
public class Classe {

    /**
     * Identifiant unique de la classe (ex: "001")
     */
    private int identifiantClasse;

    /**
     * Libellé de la classe (ex: "3ème A4", "Terminale D1", "2nde B2")
     */
    private String libelle;

    /**
     * Niveau d'étude de la classe (ex: "3ème", "Terminale D", "2nde B")
     */
    private NiveauEtude niveauEtude;

    /**
     * Année scolaire de la classe (ex: "2022-2023", "2019-2020", "2020-2021")
     */
    private AnneeScolaire anneeScolare;

    /**
     * l'enseignant principal de la classe
     */
    private Employe professeurPrincipal;
    
    /**
     * Constructeur de la classe Classe
     * @param identifiantClasse
     * @param libelle
     * @param niveauEtude
     * @param anneeScolare
     * @param professeurPrincipal
     */
    public Classe(int identifiantClasse, String libelle, NiveauEtude niveauEtude,
        AnneeScolaire anneeScolare, Employe professeurPrincipal) {
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
