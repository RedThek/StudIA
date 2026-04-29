package modeles;

/**
 * Classe représentant une note attribuée à un élève dans une matière.
 */
public class Note {

    private String matiereId;           // Référence à la matière
    private double valeur;              // Note sur 20
    private String typeEvaluation;      // Ex: "Devoir", "Examen", "Interro", "TP"
    private String date;                // Date de l'évaluation (JJ/MM/AAAA)
    private String commentaire;         // Remarque facultative de l'enseignant

    /**
     * Constructeur de la note.
     */
    public Note(String matiereId, double valeur, String typeEvaluation,
                String date, String commentaire) {
        this.matiereId      = matiereId;
        this.valeur         = valeur;
        this.typeEvaluation = typeEvaluation;
        this.date           = date;
        this.commentaire    = commentaire;
    }

    // ----------------------------- Getters / Setters -----------------------------

    public String getMatiereId()           { return matiereId; }
    public double getValeur()              { return valeur; }
    public String getTypeEvaluation()      { return typeEvaluation; }
    public String getDate()                { return date; }
    public String getCommentaire()         { return commentaire; }

    public void setValeur(double valeur)           { this.valeur = valeur; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    @Override
    public String toString() {
        return String.format("Matière: %s | %s | Note: %.1f/20 | Date: %s | Remarque: %s",
                matiereId, typeEvaluation, valeur, date,
                (commentaire == null || commentaire.isEmpty() ? "Aucune" : commentaire));
    }
}
