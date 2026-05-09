package cm.enspm.studia.model.personnes;

import cm.enspm.studia.model.administration.Classe;

/**
 * Classe représentant un élève de l'école secondaire.
 * Un élève a un matricule unique et peut être associé à une classe.
 */

public class Eleve extends Personne {

    /**
     * Code d'identification unique de l'élève dans l'établissement.
     */
    private String matricule;

    /**
     * Chemin ou identifiant de la photo de profil de l'élève.
     */
    private String photo;
    
    /**
     * Statut administratif de l'élève (par exemple Actif, Inactif).
     */
    private String statut;

    /**
     * Nom de la classe ou groupe auquel l'élève est affecté.
     */
    private String classe;

    /**
     * Constructeur principal de la classe Eleve.
     * @param matricule numéro matricule unique de l'élève
     * @param nom nom de l'élève
     * @param prenom prénom de l'élève
     * @param dateNaissance date de naissance au format texte
     * @param lieuNaissance lieu de naissance
     * @param sexe sexe de l'élève
     * @param photo chemin de la photo de l'élève
     * @param nationalite nationalité de l'élève
     * @param classe classe ou groupe de l'élève
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

    /**
     * Retourne le matricule de l'élève.
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * Retourne le chemin ou l'URL de la photo de l'élève.
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Retourne le statut administratif de l'élève.
     */
    public String getStatut() {
        return statut;
    }

    /**
     * Retourne le nom de la classe de l'élève.
     */
    public String getClasse() {
        return classe;
    }

    // ----------------------------- Setters -----------------------------

    /**
     * Met à jour le matricule de l'élève.
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * Met à jour le chemin ou l'identifiant de la photo de l'élève.
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Met à jour le statut administratif de l'élève.
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * Met à jour la classe associée à l'élève.
     */
    public void setClasse(String classe) {
        this.classe = classe;
    }

    /**
     * Retourne le rôle métier de cet objet.
     */
    @Override
    public String getRole() {
        return "Élève";
    }
}
