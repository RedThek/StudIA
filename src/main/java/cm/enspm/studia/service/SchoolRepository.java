package cm.enspm.studia.service;

//import cm.enspm.studia.model.*;
import cm.enspm.studia.mapper.EleveMapper;
import cm.enspm.studia.model.administration.Classe;
import cm.enspm.studia.model.examens.Evaluation;
import cm.enspm.studia.model.examens.Sequence;
import cm.enspm.studia.model.examens.Trimestre;
import cm.enspm.studia.model.personnes.Eleve;
import cm.enspm.studia.model.syllabus.AnneeScolaire;
import cm.enspm.studia.model.syllabus.Cycle;
import cm.enspm.studia.model.syllabus.Matiere;
import cm.enspm.studia.model.syllabus.NiveauEtude;
import cm.enspm.studia.model.syllabus.SystemeEducatif;
import cm.enspm.studia.repository.EleveRepository;

import java.time.LocalDate;
import java.util.*;

public class SchoolRepository {

    private final EleveRepository eleveRepository;
    private final List<Eleve> eleves = new ArrayList<>();
    private final List<Matiere> matieres = new ArrayList<>();
    private final List<Sequence> sequences = new ArrayList<>();
    private final List<Evaluation> evaluations = new ArrayList<>();
    private final List<Classe> classes = new ArrayList<>();
    private final List<Trimestre> trimestres = new ArrayList<>();

    public SchoolRepository(EleveRepository eleveRepository) {
        this.eleveRepository = eleveRepository;
        initializeSampleData();
    }

    public SchoolRepository() {
        this.eleveRepository = null;
        initializeSampleData();
    }

    private void initializeSampleData() {
        SystemeEducatif systeme = new SystemeEducatif(1, "Système éducatif bilingue camerounais", "Secondaire bilingue");
        Cycle cycleSecondaire = new Cycle(1, "Secondaire", "Cycle d'enseignement général");
        
        // Initialize matieres first
        List<Matiere> matseconde = new ArrayList<>();
        matseconde.add(new Matiere("MAT", "Mathématiques", 4));
        matseconde.add(new Matiere("FR", "Français", 3));
        matseconde.add(new Matiere("EN", "Anglais", 3));
        matseconde.add(new Matiere("PHY", "Physique", 3));
        matseconde.add(new Matiere("HIS", "Histoire-Géographie", 2));
        
        List<Integer> heures = new ArrayList<>(Arrays.asList(4, 3, 3, 3, 2));
        NiveauEtude seconde = new NiveauEtude("Seconde", matseconde, heures);
        
        matieres.addAll(matseconde);
        
        AnneeScolaire annee2025 = new AnneeScolaire(LocalDate.now(), LocalDate.now());

        Trimestre trimestre1 = new Trimestre("T1", "1er Trimestre", "T1", LocalDate.now(), LocalDate.now().plusDays(30), new ArrayList<>());
        Trimestre trimestre2 = new Trimestre("T2", "2ème Trimestre", "T2", LocalDate.now().plusDays(30), LocalDate.now().plusDays(60), new ArrayList<>());
        Trimestre trimestre3 = new Trimestre("T3", "3ème Trimestre", "T3", LocalDate.now().plusDays(60), LocalDate.now().plusDays(90), new ArrayList<>());
        
        trimestres.add(trimestre1);
        trimestres.add(trimestre2);
        trimestres.add(trimestre3);

        sequences.add(new Sequence(1, "Séquence 1", "Seq1", LocalDate.now(), LocalDate.now().plusDays(15)));
        sequences.add(new Sequence(2, "Séquence 2", "Seq2", LocalDate.now().plusDays(15), LocalDate.now().plusDays(30)));
        sequences.add(new Sequence(3, "Séquence 3", "Seq3", LocalDate.now().plusDays(30), LocalDate.now().plusDays(45)));

        Eleve eleve1 = new Eleve("ELV2024-001", "NGOUNOU", "Marie", "12/05/2009", "Yaoundé", "F", "", "Camerounaise", null);
        Eleve eleve2 = new Eleve("ELV2024-002", "_fire", "MKR", "11/11/1111", "Valhalla", "M", "", "Asgardien", null);
        eleves.add(eleve1);
        eleves.add(eleve2);

        Classe classe = new Classe(seconde, Arrays.asList(eleve1, eleve2), null, annee2025, null);
        classes.add(classe);

        // Create evaluations with the new Set-based structure
        evaluations.add(new Evaluation(
                new HashSet<>(trimestres),
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>(classes),
                new HashSet<>(Arrays.asList(16.5, 14.0, 12.5, 15.0, 13.5)),
                new HashSet<>(Arrays.asList("Très bon travail", "Bonne compréhension"))
        ));
        evaluations.add(new Evaluation(
                new HashSet<>(trimestres),
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>(classes),
                new HashSet<>(Arrays.asList(11.0, 12.0, 14.0, 10.5, 12.0)),
                new HashSet<>(Arrays.asList("Revoir les bases", "Progression lente"))
        ));
    }

    public List<Eleve> getEleves() {
        if (eleveRepository != null) {
            return EleveMapper.toDomainList(eleveRepository.getAllEleves());
        }
        return new ArrayList<>(eleves);
    }

    public List<Matiere> getMatieres() {
        return new ArrayList<>(matieres);
    }

    public List<Sequence> getSequences() {
        return new ArrayList<>(sequences);
    }

    public List<Evaluation> getEvaluationsForStudent(Eleve eleve) {
        return new ArrayList<>(evaluations);
    }

    public Classe findClasseForEleve(Eleve eleve) {
        for (Classe classe : classes) {
            if (classe.getEleves().contains(eleve)) {
                return classe;
            }
        }
        return classes.isEmpty() ? null : classes.get(0);
    }

    public Eleve findEleveByMatricule(String matricule) {
        if (matricule == null) {
            return null;
        }
        if (eleveRepository != null) {
            return eleveRepository.RechercherEleveParMatricule(matricule)
                    .map(EleveMapper::toDomain)
                    .orElse(null);
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
        return eleves.remove(eleve);
    }

    //By MKR_fire
    public boolean addEvaluation(Evaluation evaluation) {
        if (evaluation == null) {
            return false;
        }
        return evaluations.add(evaluation);
    }

    public boolean deleteEvaluation(Evaluation evaluation) {
        if (evaluation == null) {
            return false;
        }
        return evaluations.remove(evaluation);
    }

    public List<Evaluation> getEvaluations() {
        return new ArrayList<>(evaluations);
    }


}
