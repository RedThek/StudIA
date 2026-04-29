package modeles;

/**
 * Classe abstraite représentant une personne de l'école.
 * Toutes les personnes (élèves, enseignants, administration) héritent de cette classe.
 */
public abstract class Personne {

    // Attributs communs à toutes les personnes
    private String identifiant;
    private String nom;
    private String prenom;
    private String dateNaissance;   // Format : JJ/MM/AAAA
    private String adresse;
    private String telephone;
    private String email;

    /**
     * Constructeur complet de la classe Personne.
     */
    public Personne(String identifiant, String nom, String prenom,
                    String dateNaissance, String adresse, String telephone, String email) {
        this.identifiant   = identifiant;
        this.nom           = nom;
        this.prenom        = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse       = adresse;
        this.telephone     = telephone;
        this.email         = email;
    }

    // ----------------------------- Getters -----------------------------

    public String getIdentifiant()   { return identifiant; }
    public String getNom()           { return nom; }
    public String getPrenom()        { return prenom; }
    public String getDateNaissance() { return dateNaissance; }
    public String getAdresse()       { return adresse; }
    public String getTelephone()     { return telephone; }
    public String getEmail()         { return email; }

    // ----------------------------- Setters -----------------------------

    public void setNom(String nom)                   { this.nom = nom; }
    public void setPrenom(String prenom)             { this.prenom = prenom; }
    public void setDateNaissance(String date)        { this.dateNaissance = date; }
    public void setAdresse(String adresse)           { this.adresse = adresse; }
    public void setTelephone(String telephone)       { this.telephone = telephone; }
    public void setEmail(String email)               { this.email = email; }

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

    @Override
    public String toString() {
        return String.format("[%s] %s %s | Né(e) le %s | Tél: %s | Email: %s",
                identifiant, prenom, nom, dateNaissance, telephone, email);
    }
}
