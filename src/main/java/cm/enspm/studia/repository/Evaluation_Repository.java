package cm.enspm.studia.repository;

import cm.enspm.studia.model.examens.Evaluation;
import cm.enspm.studia.model.personnes.Eleve;
import cm.enspm.studia.model.syllabus.Matiere;

import java.util.List;

public interface Evaluation_Repository {

    void ajouterEvaluation(Evaluation evaluation);

    List<Evaluation> listerEvaluations();

    List<Evaluation> getEvaluationsParEleve(Eleve eleve);

    List<Evaluation> getEvaluationsParMatiere(Matiere matiere);

    void modifierEvaluation(Evaluation ancienne, Evaluation nouvelle);

    void supprimerEvaluation(Evaluation evaluation);

    double calculerMoyenneEleve(Eleve eleve);

}