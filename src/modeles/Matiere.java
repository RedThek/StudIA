package modeles;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant une matière enseignée dans l'école.
 */
public class Matiere {

    private String identifiant;         // Code unique de la matière (ex: "MAT001")
    private String nom;                 // Nom complet (ex: "Mathématiques")
    private String code;                // Code court (ex: "MATH")
    private String description;
    private int coefficientExamen;      // Coefficient pour les examens officiels
    private int heuresParSemaine;       // Nombre d'heures hebdomadaires
    private String enseignantId;        // Identifiant de l'enseignant responsable
    private List<String> classesIds;    // Classes où cette matière est enseignée

    /**
     * Constructeur de la classe Matiere.
     */
    public Matiere(String identifiant, String nom, String code, String description,
                   int coefficientExamen, int heuresParSemaine) {
        this.identifiant       = identifiant;
        this.nom               = nom;
        this.code              = code;
        this.description       = description;
        this.coefficientExamen = coefficientExamen;
        this.heuresParSemaine  = heuresParSemaine;
        this.enseignantId      = "";
        this.classesIds        = new ArrayList<>();
    }

    // ----------------------------- Getters / Setters -----------------------------

    public String getIdentifiant()                     { return identifiant; }
    public String getNom()                             { return nom; }
    public String getCode()                            { return code; }
    public String getDescription()                     { return description; }
    public int getCoefficientExamen()                  { return coefficientExamen; }
    public int getHeuresParSemaine()                   { return heuresParSemaine; }
    public String getEnseignantId()                    { return enseignantId; }
    public List<String> getClassesIds()                { return classesIds; }

    public void setNom(String nom)                             { this.nom = nom; }
    public void setDescription(String description)             { this.description = description; }
    public void setCoefficientExamen(int coeff)                { this.coefficientExamen = coeff; }
    public void setHeuresParSemaine(int heures)                { this.heuresParSemaine = heures; }
    public void setEnseignantId(String enseignantId)           { this.enseignantId = enseignantId; }

    /**
     * Ajoute une classe à la liste des classes enseignées.
     */
    public void ajouterClasse(String classeId) {
        if (!classesIds.contains(classeId)) {
            classesIds.add(classeId);
        }
    }

    /**
     * Retire une classe de la liste.
     */
    public void retirerClasse(String classeId) {
        classesIds.remove(classeId);
    }

    @Override
    public String toString() {
        return String.format("MATIÈRE | [%s] %s (%s) | Coeff: %d | %dh/semaine | Prof: %s",
                identifiant, nom, code, coefficientExamen, heuresParSemaine,
                (enseignantId.isEmpty() ? "Non assigné" : enseignantId));
    }
}
