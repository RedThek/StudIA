package modeles;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant une classe (groupe d'élèves) de l'école secondaire.
 * Ex : "3ème A", "Terminale S", "2nde B"
 */
public class Classe {

    private String identifiant;           // Code unique (ex: "CL001")
    private String nom;                   // Nom de la classe (ex: "3ème A")
    private String niveau;                // Niveau scolaire (ex: "3ème", "Terminale")
    private String section;              // Section/Filière (ex: "Scientifique", "Littéraire")
    private String salleId;              // Identifiant de la salle principale
    private String professeurPrincipalId; // Identifiant de l'enseignant principal
    private List<String> elevesIds;       // Identifiants des élèves de la classe
    private List<String> matieresIds;     // Identifiants des matières enseignées
    private int anneeScolare;             // Année scolaire (ex: 2024)

    /**
     * Constructeur de la classe Classe.
     */
    public Classe(String identifiant, String nom, String niveau,
                  String section, int anneeScolare) {
        this.identifiant              = identifiant;
        this.nom                      = nom;
        this.niveau                   = niveau;
        this.section                  = section;
        this.anneeScolare             = anneeScolare;
        this.salleId                  = "";
        this.professeurPrincipalId    = "";
        this.elevesIds                = new ArrayList<>();
        this.matieresIds              = new ArrayList<>();
    }

    // ----------------------------- Getters / Setters -----------------------------

    public String getIdentifiant()                         { return identifiant; }
    public String getNom()                                 { return nom; }
    public String getNiveau()                              { return niveau; }
    public String getSection()                             { return section; }
    public String getSalleId()                             { return salleId; }
    public String getProfesseurPrincipalId()               { return professeurPrincipalId; }
    public List<String> getElevesIds()                     { return elevesIds; }
    public List<String> getMatieresIds()                   { return matieresIds; }
    public int getAnneeScolare()                           { return anneeScolare; }

    public void setNom(String nom)                                 { this.nom = nom; }
    public void setSection(String section)                         { this.section = section; }
    public void setSalleId(String salleId)                         { this.salleId = salleId; }
    public void setProfesseurPrincipalId(String professeurId)      { this.professeurPrincipalId = professeurId; }

    // ----------------------------- Gestion des élèves et matières -----------------------------

    /**
     * Inscrit un élève dans cette classe.
     */
    public void inscrireEleve(String eleveId) {
        if (!elevesIds.contains(eleveId)) {
            elevesIds.add(eleveId);
        }
    }

    /**
     * Désinscrit un élève de cette classe.
     */
    public void desinscrireEleve(String eleveId) {
        elevesIds.remove(eleveId);
    }

    /**
     * Ajoute une matière à cette classe.
     */
    public void ajouterMatiere(String matiereId) {
        if (!matieresIds.contains(matiereId)) {
            matieresIds.add(matiereId);
        }
    }

    /**
     * Retire une matière de cette classe.
     */
    public void retirerMatiere(String matiereId) {
        matieresIds.remove(matiereId);
    }

    /**
     * Retourne le nombre d'élèves inscrits.
     */
    public int getNombreEleves() {
        return elevesIds.size();
    }

    @Override
    public String toString() {
        return String.format("CLASSE | [%s] %s | Niveau: %s | Section: %s | Année: %d | Effectif: %d élève(s)",
                identifiant, nom, niveau, section, anneeScolare, getNombreEleves());
    }
}
