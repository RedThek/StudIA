package modeles;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un enseignant de l'école secondaire.
 * Un enseignant peut enseigner plusieurs matières dans plusieurs classes.
 */
public class Enseignant extends Personne {

    private String numeroEmploye;
    private String specialite;             // Domaine de spécialité principal
    private String diplome;                // Diplôme le plus élevé obtenu
    private double salaire;
    private String dateEmbauche;
    private List<String> matieresEnseignees;   // Identifiants des matières
    private List<String> classesAssignees;     // Noms des classes (ex: "3ème A")

    /**
     * Constructeur de la classe Enseignant.
     */
    public Enseignant(String identifiant, String nom, String prenom, String dateNaissance,
                      String adresse, String telephone, String email,
                      String numeroEmploye, String specialite, String diplome,
                      double salaire, String dateEmbauche) {
        super(identifiant, nom, prenom, dateNaissance, adresse, telephone, email);
        this.numeroEmploye      = numeroEmploye;
        this.specialite         = specialite;
        this.diplome            = diplome;
        this.salaire            = salaire;
        this.dateEmbauche       = dateEmbauche;
        this.matieresEnseignees = new ArrayList<>();
        this.classesAssignees   = new ArrayList<>();
    }

    // ----------------------------- Getters / Setters -----------------------------

    public String getNumeroEmploye()                   { return numeroEmploye; }
    public String getSpecialite()                      { return specialite; }
    public String getDiplome()                         { return diplome; }
    public double getSalaire()                         { return salaire; }
    public String getDateEmbauche()                    { return dateEmbauche; }
    public List<String> getMatieresEnseignees()        { return matieresEnseignees; }
    public List<String> getClassesAssignees()          { return classesAssignees; }

    public void setSpecialite(String specialite)       { this.specialite = specialite; }
    public void setDiplome(String diplome)             { this.diplome = diplome; }
    public void setSalaire(double salaire)             { this.salaire = salaire; }

    // ----------------------------- Gestion des matières / classes -----------------------------

    /**
     * Assigne une matière à cet enseignant si elle n'est pas déjà présente.
     */
    public void ajouterMatiere(String matiereId) {
        if (!matieresEnseignees.contains(matiereId)) {
            matieresEnseignees.add(matiereId);
        }
    }

    /**
     * Retire une matière de la liste de l'enseignant.
     */
    public void retirerMatiere(String matiereId) {
        matieresEnseignees.remove(matiereId);
    }

    /**
     * Assigne une classe à cet enseignant.
     */
    public void ajouterClasse(String nomClasse) {
        if (!classesAssignees.contains(nomClasse)) {
            classesAssignees.add(nomClasse);
        }
    }

    /**
     * Retire une classe de la liste de l'enseignant.
     */
    public void retirerClasse(String nomClasse) {
        classesAssignees.remove(nomClasse);
    }

    @Override
    public String getRole() {
        return "Enseignant";
    }

    @Override
    public String toString() {
        return String.format("ENSEIGNANT | Employé n°%s | %s | Spécialité: %s | Diplôme: %s | Embauché le: %s | Salaire: %.2f€",
                numeroEmploye, getNomComplet(), specialite, diplome, dateEmbauche, salaire);
    }
}
