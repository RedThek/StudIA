package cm.enspm.studia.model.dto.personnes;

/**
 * Classe abstraite représentant une personne de l'école.
 * Toutes les personnes (élèves, enseignants, administration) héritent de cette classe.
 */
public abstract class Personne {

    // Attributs communs à toutes les personnes
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String lieuNaissance;
    private String sexe;
    private String nationalite;

    /**
     * Constructeur complet de la classe Personne.
     */
    public Personne(
        String nom,
        String prenom,
        String dateNaissance,
        String lieuNaissance,
        String sexe,
        String nationalite
    ){
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.sexe = sexe;
        this.nationalite = nationalite;
    }

    // ----------------------------- Getters -----------------------------

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public String getNationalite() {
        return nationalite;
    }

    // ----------------------------- Setters -----------------------------

    public void setNom(String nom){
        this.nom = nom;
    }
    public void setPrenom(String prenom)             { this.prenom = prenom; }
    public void setDateNaissance(String date)        { this.dateNaissance = date; }
    public void setLieuNaissance(String lieu)        { this.lieuNaissance = lieu; }
    public void setSexe(String sexe)                 { this.sexe = sexe; }
    public void setNationalite(String nationalite)   { this.nationalite = nationalite; }

    /**
     * Retourne le nom complet (prénom + nom).
     */
    public String getNomComplet() {
        return prenom + " " + nom;
    }

    /**
     * Méthode abstraite que chaque sous-classe doit implémenter
     * pour retourner le rôle de la personne dans l'école.
     */
    public abstract String getRole();
}
