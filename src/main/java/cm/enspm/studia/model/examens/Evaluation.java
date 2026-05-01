package cm.enspm.studia.model.examens;

import cm.enspm.studia.model.personnes.Eleve;
import cm.enspm.studia.model.syllabus.Matiere;

/**
 * Classe représentant une note attribuée à un élève dans une matière.
 */
public class Evaluation {

    private Eleve eleve;
    private Matiere matiere;
    private Sequence sequence;
    private double note;
    private String date;
    private String commentaire;

    public Evaluation(Eleve eleve, Matiere matiere, Sequence sequence, double note, String date, String commentaire) {
        this.eleve = eleve;
        this.matiere = matiere;
        this.sequence = sequence;
        this.note = note;
        this.date = date;
        this.commentaire = commentaire;
    }

    public Eleve getEleve() { return eleve; }
    public Matiere getMatiere() { return matiere; }
    public Sequence getSequence() { return sequence; }
    public double getNote() { return note; }
    public String getDate() { return date; }
    public String getCommentaire() { return commentaire; }

    public void setEleve(Eleve eleve) { this.eleve = eleve; }
    public void setMatiere(Matiere matiere) { this.matiere = matiere; }
    public void setSequence(Sequence sequence) { this.sequence = sequence; }
    public void setNote(double note) { this.note = note; }
    public void setDate(String date) { this.date = date; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    @Override
    public String toString() {
        return "Evaluation{" +
                "eleve=" + eleve +
                ", matiere=" + matiere +
                ", sequence=" + sequence +
                ", note=" + note +
                ", date='" + date + '\'' +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}