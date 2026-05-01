package cm.enspm.studia.model.dto.syllabus;

/**
 * Classe représentant un cycle d'enseignement secondaire
 * Un cycle d'enseignement secondaire est composé de plusieurs classes et correspond
 * à un niveau d'enseignement ex : 1er cycle de l'enseignement secondaire
 */
public class Cycle {
    
    /**
     * Identifiant du cycle d'enseignement
     */
    private int identifiantCycle;

    /**
     * Libellé du cycle d'enseignement ex : cycle1 ou cycle2
     */
    private String libelle;

    /**
     * Nom du cycle d'enseignement ex : 1er cycle de l'enseignement secondaire
    */
    private String nom;

    /**
     * Constructeur de la classe Cycle
     * @param identifiantCycle
     * @param libelle
     * @param nom
     */
    public Cycle(int identifiantCycle, String libelle, String nom) {
        this.identifiantCycle = identifiantCycle;
        this.libelle = libelle;
        this.nom = nom;
    }

    public int getIdentifiantCycle() {
        return identifiantCycle;
    }

    public String getLibelleCycle() {
        return libelle;
    }

    public String getNomCycle() {
        return nom;
    }

    public void setNomCycle(String nom) {
        this.nom = nom;
    }
}
