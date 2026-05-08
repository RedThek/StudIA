package cm.enspm.studia.model.personnes;

import cm.enspm.studia.model.administration.Classe;

/**
 * Classe représentant un élève de l'école secondaire.
 * Un élève a un matricule unique et peut être associé à une classe.
 */

public class Eleve extends Personne {

    /**
     * 
     */
    private String matricule;
    /**
     * 
     */
    private String photo;
    
    /**
     * 
     */
    private String statut;

    /**
     * 
     */
    private String classe;

    /**
     * Constructeur de la classe Eleve.
     */
    public Eleve(
        String matricule, String nom, String prenom,
        String dateNaissance, String lieuNaissance, 
        String sexe, String photo, String nationalite, String classe) {
        super(nom, prenom, dateNaissance, lieuNaissance, sexe, nationalite);
        this.matricule  = matricule;
        this.photo   = photo;
        this.statut = "Actif";
        this.classe = classe;
    }

    // ----------------------------- Getters -----------------------------

    public String getMatricule() {
        return matricule;
    }

    public String getPhoto() {
        return photo;
    }

    public String getStatut() {
        return statut;
    }

    public String getClasse() {
        return classe;
    }

    // ----------------------------- Setters -----------------------------

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    @Override
    public String getRole() {
        return "Élève";
    }
}
