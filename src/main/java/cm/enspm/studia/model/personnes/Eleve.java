package cm.enspm.studia.model.personnes;

/**
 * Classe représentant un élève de l'école.
 * Un élève a un matricule unique et peut être associé à une classe.
 */

public class Eleve extends Personne {

    private String matricule;
    private String photo;

    /**
     * Constructeur de la classe Eleve.
     */
    public Eleve(
        String matricule, String nom, String prenom,
        String dateNaissance, String lieuNaissance, 
        String sexe, String photo, String nationalite) {
        super(nom, prenom, dateNaissance, lieuNaissance, sexe, nationalite);
        this.matricule  = matricule;
        this.photo   = photo;
    }

    // ----------------------------- Getters -----------------------------

    public String getMatricule() {
        return matricule;
    }

    public String getPhoto() {
        return photo;
    }

    // ----------------------------- Setters -----------------------------

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String getRole() {
        return "Élève";
    }
}
