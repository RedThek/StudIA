package cm.enspm.studia.service;

import cm.enspm.studia.model.*;
import cm.enspm.studia.model.examens.Evaluation;
import cm.enspm.studia.model.examens.Sequence;
import cm.enspm.studia.model.examens.Trimestre;
import cm.enspm.studia.model.personnes.Eleve;
import cm.enspm.studia.model.syllabus.AnneeScolaire;
import cm.enspm.studia.model.syllabus.Cycle;
import cm.enspm.studia.model.syllabus.Matiere;
import cm.enspm.studia.model.syllabus.NiveauEtude;
import cm.enspm.studia.model.syllabus.SystemeEducatif;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SchoolRepository {

    private final List<Eleve> eleves = new ArrayList<>();
    private final List<Matiere> matieres = new ArrayList<>();
    private final List<Sequence> sequences = new ArrayList<>();
    private final List<Evaluation> evaluations = new ArrayList<>();

    public SchoolRepository() {
        initializeSampleData();
    }

    private void initializeSampleData() {
        SystemeEducatif systeme = new SystemeEducatif(1, "Système éducatif bilingue camerounais", "Secondaire bilingue");
        Cycle cycleSecondaire = new Cycle(1, "Secondaire", "Cycle d'enseignement général");
        NiveauEtude seconde = new NiveauEtude(1, "Seconde", systeme, cycleSecondaire);
        AnneeScolaire annee2025 = new AnneeScolaire(1, "2024-2025", new Date(), new Date());

        matieres.add(new Matiere(1, "MAT", "Mathématiques"));
        matieres.add(new Matiere(2, "FR", "Français"));
        matieres.add(new Matiere(3, "EN", "Anglais"));
        matieres.add(new Matiere(4, "PHY", "Physique"));
        matieres.add(new Matiere(5, "HIS", "Histoire-Géographie"));

        Trimestre trimestre1 = new Trimestre(1, "1er Trimestre", annee2025);
        Trimestre trimestre2 = new Trimestre(2, "2ème Trimestre", annee2025);
        Trimestre trimestre3 = new Trimestre(3, "3ème Trimestre", annee2025);

        sequences.add(new Sequence(1, "Séquence 1", "Évaluation formative", trimestre1));
        sequences.add(new Sequence(2, "Séquence 2", "Évaluation sommative", trimestre2));
        sequences.add(new Sequence(3, "Séquence 3", "Évaluation finale", trimestre3));

        Eleve eleve1 = new Eleve("ELV2024-001", "NGOUNOU", "Marie", "12/05/2009", "Yaoundé", "F", "", "Camerounaise");
        Eleve eleve2 = new Eleve("ELV2024-002", "TCHOUNGANG", "Paul", "18/09/2008", "Douala", "M", "", "Camerounaise");
        eleves.add(eleve1);
        eleves.add(eleve2);

        evaluations.add(new Evaluation(eleve1, matieres.get(0), sequences.get(0), 16.5, "05/10/2024", "Très bon travail"));
        evaluations.add(new Evaluation(eleve1, matieres.get(1), sequences.get(0), 14.0, "07/10/2024", "Bonne compréhension"));
        evaluations.add(new Evaluation(eleve1, matieres.get(2), sequences.get(0), 12.5, "09/10/2024", "Doit participer davantage"));
        evaluations.add(new Evaluation(eleve1, matieres.get(3), sequences.get(0), 15.0, "11/10/2024", "Solide et régulier"));
        evaluations.add(new Evaluation(eleve1, matieres.get(4), sequences.get(0), 13.5, "13/10/2024", "Bon effort"));

        evaluations.add(new Evaluation(eleve2, matieres.get(0), sequences.get(0), 11.0, "05/10/2024", "Revoir les bases"));
        evaluations.add(new Evaluation(eleve2, matieres.get(1), sequences.get(0), 12.0, "07/10/2024", "Progression lente"));
        evaluations.add(new Evaluation(eleve2, matieres.get(2), sequences.get(0), 14.0, "09/10/2024", "Bon niveau oral"));
        evaluations.add(new Evaluation(eleve2, matieres.get(3), sequences.get(0), 10.5, "11/10/2024", "Travail irrégulier"));
        evaluations.add(new Evaluation(eleve2, matieres.get(4), sequences.get(0), 12.0, "13/10/2024", "Participation correcte"));
    }

    public List<Eleve> getEleves() {
        return new ArrayList<>(eleves);
    }

    public List<Matiere> getMatieres() {
        return new ArrayList<>(matieres);
    }

    public List<Sequence> getSequences() {
        return new ArrayList<>(sequences);
    }

    public List<Evaluation> getEvaluationsForStudent(Eleve eleve) {
        List<Evaluation> result = new ArrayList<>();
        for (Evaluation evaluation : evaluations) {
            if (Objects.equals(evaluation.getEleve().getMatricule(), eleve.getMatricule())) {
                result.add(evaluation);
            }
        }
        return result;
    }

    public Eleve findEleveByMatricule(String matricule) {
        if (matricule == null) {
            return null;
        }
        for (Eleve eleve : eleves) {
            if (matricule.equalsIgnoreCase(eleve.getMatricule())) {
                return eleve;
            }
        }
        return null;
    }

    public boolean addEleve(Eleve eleve) {
        if (findEleveByMatricule(eleve.getMatricule()) != null) {
            return false;
        }
        return eleves.add(eleve);
    }

    public boolean deleteEleve(Eleve eleve) {
        if (eleve == null) {
            return false;
        }
        evaluations.removeIf(evaluation -> Objects.equals(evaluation.getEleve().getMatricule(), eleve.getMatricule()));
        return eleves.remove(eleve);
    }
}
