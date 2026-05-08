package cm.enspm.studia.model;

/**
 * Représente une matière enseignée dans l'établissement.
 */
public class Matiere {

    private int identifiantMatiere;
    private String code;           // Ex: "MAT", "FR"
    private String designation;    // Ex: "Mathématiques", "Français"
    private int coefficient;       // Coefficient de la matière

    public Matiere(int identifiantMatiere, String code, String designation, int coefficient) {
        this.identifiantMatiere = identifiantMatiere;
        this.code               = code;
        this.designation        = designation;
        this.coefficient        = coefficient;
    }

    /** Constructeur sans coefficient (coefficient = 1 par défaut). */
    public Matiere(int identifiantMatiere, String code, String designation) {
        this(identifiantMatiere, code, designation, 1);
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public int getIdentifiantMatiere()  { return identifiantMatiere; }
    public String getCode()             { return code; }
    public String getDesignation()      { return designation; }
    public int getCoefficient()         { return coefficient; }

    /** Alias pour compatibilité avec l'ancien code. */
    public String getCodeMatiere()      { return code; }
    /** Alias pour compatibilité avec l'ancien code. */
    public String getLibelle()          { return designation; }

    // ── Setters ──────────────────────────────────────────────────────────────

    public void setIdentifiantMatiere(int id)     { this.identifiantMatiere = id; }
    public void setCode(String code)              { this.code = code; }
    public void setDesignation(String designation){ this.designation = designation; }
    public void setCoefficient(int coefficient)   { this.coefficient = coefficient; }

    /** Alias pour compatibilité avec l'ancien code. */
    public void setCodeMatiere(String code)       { this.code = code; }
    /** Alias pour compatibilité avec l'ancien code. */
    public void setLibelle(String designation)    { this.designation = designation; }

    @Override
    public String toString() {
        return "[" + code + "] " + designation + " (coeff. " + coefficient + ")";
    }
}
