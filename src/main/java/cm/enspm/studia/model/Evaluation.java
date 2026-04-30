package cm.enspm.studia.model;

/**
 * Classe représentant une note attribuée à un élève dans une matière.
 */
public class Evaluation {

    private Eleve eleve;           // Référence à l'élève
    private Matiere matiere;       // Référence à la matière
    private Sequence sequence;          // Identifiant de la séquence
    private double note;              // Note sur 20
    private String date;                // Date de l'évaluation (JJ/MM/AAAA)
    private String commentaire;         // Remarque facultative de l'enseignant

    public Evaluation(Eleve eleve, Matiere matiere, Sequence sequence, double note, String date, String commentaire) {
        this.eleve = eleve;
        this.matiere = matiere;
        this.sequence = sequence;
        this.note = note;
        this.date = date;
        this.commentaire = commentaire;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public Sequence getSequence() {
        return sequence;
    }

    public double getNote() {
        return note;
    }

    public String getDate() {
        return date;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
