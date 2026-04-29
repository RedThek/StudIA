package modeles;

/**
 * Classe représentant un membre de l'administration de l'école
 * (directeur, secrétaire, comptable, etc.).
 */
public class Administrateur extends Personne {

    private String numeroEmploye;
    private String poste;           // Ex: "Directeur", "Secrétaire", "Comptable"
    private String service;         // Ex: "Direction", "Scolarité", "Finance"
    private double salaire;
    private String dateEmbauche;
    private boolean estDirecteur;   // Indique si cette personne est le directeur de l'école

    /**
     * Constructeur de la classe Administrateur.
     */
    public Administrateur(String identifiant, String nom, String prenom, String dateNaissance,
                          String adresse, String telephone, String email,
                          String numeroEmploye, String poste, String service,
                          double salaire, String dateEmbauche, boolean estDirecteur) {
        super(identifiant, nom, prenom, dateNaissance, adresse, telephone, email);
        this.numeroEmploye = numeroEmploye;
        this.poste         = poste;
        this.service       = service;
        this.salaire       = salaire;
        this.dateEmbauche  = dateEmbauche;
        this.estDirecteur  = estDirecteur;
    }

    // ----------------------------- Getters / Setters -----------------------------

    public String getNumeroEmploye()                   { return numeroEmploye; }
    public String getPoste()                           { return poste; }
    public String getService()                         { return service; }
    public double getSalaire()                         { return salaire; }
    public String getDateEmbauche()                    { return dateEmbauche; }
    public boolean isEstDirecteur()                    { return estDirecteur; }

    public void setPoste(String poste)                 { this.poste = poste; }
    public void setService(String service)             { this.service = service; }
    public void setSalaire(double salaire)             { this.salaire = salaire; }
    public void setEstDirecteur(boolean estDirecteur)  { this.estDirecteur = estDirecteur; }

    @Override
    public String getRole() {
        return estDirecteur ? "Directeur" : "Administration (" + poste + ")";
    }

    @Override
    public String toString() {
        return String.format("ADMIN | Employé n°%s | %s | Poste: %s | Service: %s | Embauché le: %s | Salaire: %.2f€%s",
                numeroEmploye, getNomComplet(), poste, service, dateEmbauche, salaire,
                estDirecteur ? " | ★ DIRECTEUR" : "");
    }
}
