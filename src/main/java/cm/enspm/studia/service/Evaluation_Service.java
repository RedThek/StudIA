package cm.enspm.studia.service;

import cm.enspm.studia.model.examens.Evaluation;
import cm.enspm.studia.model.personnes.Eleve;
import cm.enspm.studia.model.syllabus.Matiere;
import cm.enspm.studia.repository.Evaluation_Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Evaluation_Service implements Evaluation_Repository {

    private final List<Evaluation> evaluations = new ArrayList<>();

    @Override
    public void ajouterEvaluation(Evaluation evaluation) {
        evaluations.add(evaluation);
    }

    @Override
    public List<Evaluation> listerEvaluations() {
        return new ArrayList<>(evaluations); // éviter l'exposition directe de la liste interne
    }

    @Override
    public List<Evaluation> getEvaluationsParEleve(Eleve eleve) {
        List<Evaluation> result = new ArrayList<>();
        for (Evaluation e : evaluations) {
            if (e.getUniqueEleve(eleve).equals(eleve)) {
                result.add(e);
            }
        }
        return result;
    }

    @Override
    public List<Evaluation> getEvaluationsParMatiere(Matiere matiere) {
        List<Evaluation> result = new ArrayList<>();
        for (Evaluation e : evaluations) {
            if (e.getUniqueMatiere(matiere).equals(matiere)) {
                result.add(e);
            }
        }
        return result;
    }

    @Override
    public void modifierEvaluation(Evaluation ancienne, Evaluation nouvelle) {
        int index = evaluations.indexOf(ancienne);
        if (index != -1) {
            evaluations.set(index, nouvelle);
        }
    }

    @Override
    public void supprimerEvaluation(Evaluation evaluation) {
        if (evaluation != null) {
            evaluations.remove(evaluation);
        }
    }

    @Override
    public double calculerMoyenneEleve(Eleve eleve, Set<Double> notes) {
        if (eleve == null) return 0;
        double somme = 0;
        int count = 0;

        for (Evaluation e : evaluations) {
            if (e.getUniqueEleve(eleve) != null && e.getUniqueEleve(eleve).equals(eleve)) {
                somme += 5; //e.getUniqueNote(eleve);
                count++;
            }
        }

        return (count == 0) ? 0 : somme / count;
    }
    
}