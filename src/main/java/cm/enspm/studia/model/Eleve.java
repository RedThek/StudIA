package cm.enspm.studia.model;

/**
 * Représente un élève inscrit dans l'établissement.
 */
public class Eleve extends Personne {

    private String matricule;
    private String photo;
    private String statut;   // Ex: "Nouveau", "Redoublant", "Transféré"
    private Classe classe;   // Classe dans laquelle l'élève est inscrit (1)

    public Eleve(String matricule, String nom, String prenom,
                 String dateNaissance, String lieuNaissance,
                 String sexe, String photo, String nationalite) {
        super(nom, prenom, dateNaissance, lieuNaissance, sexe, nationalite);
        this.matricule = matricule;
        this.photo     = photo;
        this.statut    = "Nouveau";
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public String getMatricule() { return matricule; }
    public String getPhoto()     { return photo; }
    public String getStatut()    { return statut; }
    public Classe getClasse()    { return classe; }

    // ── Setters ──────────────────────────────────────────────────────────────

    public void setMatricule(String matricule) { this.matricule = matricule; }
    public void setPhoto(String photo)         { this.photo = photo; }
    public void setStatut(String statut)       { this.statut = statut; }

    /**
     * Affecte l'élève à une classe et met à jour la liste d'élèves de la classe.
     */
    public void setClasse(Classe classe) {
        if (this.classe != null) this.classe.removeEleve(this);
        this.classe = classe;
        if (classe != null) classe.addEleve(this);
    }

    @Override
    public String getRole() { return "Élève"; }

    @Override
    public String toString() {
        return matricule + " - " + getNomComplet();
    }
}
