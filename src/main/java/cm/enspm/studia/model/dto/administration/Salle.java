package cm.enspm.studia.model.dto.administration;

/**
 * Classe représentant une salle de l'école secondaire.
 * Une salle peut être une salle de classe, un laboratoire, une salle informatique, etc.
 */
public class Salle {

    // Constantes pour les types de salles
    public static final String TYPE_CLASSE       = "Salle de classe";
    public static final String TYPE_LABO_SCIENCE = "Laboratoire de sciences";
    public static final String TYPE_INFORMATIQUE = "Salle informatique";
    public static final String TYPE_SPORT        = "Salle de sport";
    public static final String TYPE_BIBLIOTHEQUE = "Bibliothèque";
    public static final String TYPE_ART          = "Salle d'art";

    private String code;         // Code de la salle (ex: "A101")
    private String nom;                 // Nom descriptif (ex: "Salle Pasteur")
    private String typeSalle;           // Type de salle (voir constantes ci-dessus)
    private int capaciteMax;            // Nombre maximum d'élèves
    private int numeroBatiment;         // Numéro du bâtiment
    private int numeroEtage;            // Étage (0 = rez-de-chaussée)
    private boolean estDisponible;      // Disponibilité actuelle
    private boolean aProjecteur;        // Équipement : vidéoprojecteur
    private boolean aTableauxBlancs;    // Équipement : tableaux blancs interactifs

    /**
     * Constructeur de la classe Salle.
     */
    public Salle(String code, String nom, String typeSalle,
                 int capaciteMax, int numeroBatiment, int numeroEtage) {
        this.code      = code;
        this.nom              = nom;
        this.typeSalle        = typeSalle;
        this.capaciteMax      = capaciteMax;
        this.numeroBatiment   = numeroBatiment;
        this.numeroEtage      = numeroEtage;
        this.estDisponible    = true;   // Disponible par défaut
        this.aProjecteur      = false;
        this.aTableauxBlancs  = false;
    }

    // ----------------------------- Getters / Setters -----------------------------

    public String getCode()                    { return code; }
    public String getNom()                     { return nom; }
    public String getTypeSalle()               { return typeSalle; }
    public int getCapaciteMax()                { return capaciteMax; }
    public int getNumeroBatiment()             { return numeroBatiment; }
    public int getNumeroEtage()                { return numeroEtage; }
    public boolean isEstDisponible()           { return estDisponible; }
    public boolean isAProjecteur()             { return aProjecteur; }
    public boolean isATableauxBlancs()         { return aTableauxBlancs; }

    public void setNom(String nom)                         { this.nom = nom; }
    public void setTypeSalle(String typeSalle)             { this.typeSalle = typeSalle; }
    public void setCapaciteMax(int capaciteMax)            { this.capaciteMax = capaciteMax; }
    public void setEstDisponible(boolean estDisponible)    { this.estDisponible = estDisponible; }
    public void setAProjecteur(boolean aProjecteur)        { this.aProjecteur = aProjecteur; }
    public void setATableauxBlancs(boolean aTBI)          { this.aTableauxBlancs = aTBI; }

    /**
     * Retourne une description courte de l'emplacement.
     */
    public String getEmplacement() {
        String etageStr = (numeroEtage == 0) ? "Rez-de-chaussée" : "Étage " + numeroEtage;
        return "Bâtiment " + numeroBatiment + " – " + etageStr;
    }

    @Override
    public String toString() {
        return String.format("SALLE | [%s] %s | %s | Capacité: %d élèves | %s | %s | Proj: %s | TBI: %s",
                code, nom, typeSalle, capaciteMax, getEmplacement(),
                estDisponible ? "DISPONIBLE" : "OCCUPÉE",
                aProjecteur ? "Oui" : "Non",
                aTableauxBlancs ? "Oui" : "Non");
    }
}
