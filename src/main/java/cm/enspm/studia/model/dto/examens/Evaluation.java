package cm.enspm.studia.model.dto.examens;

/**
 * Enregistrement représentant l'evaluation d'un élève dans une matière dans la base de données.
 */
public record Evaluation (
    int idEleve,
    int idMatiere,
    int idSequence,
    double note,
    String date,
    String commentaire
){

}
