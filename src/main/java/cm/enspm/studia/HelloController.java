package cm.enspm.studia;

import cm.enspm.studia.model.*;
import cm.enspm.studia.service.ReportCardGenerator;
import cm.enspm.studia.service.SchoolRepository;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class HelloController {

    // ELEVES
    @FXML private TextField matriculeField;
    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField dateNaissanceField;
    @FXML private TextField lieuNaissanceField;
    @FXML private TextField sexeField;
    @FXML private TextField nationaliteField;
    @FXML private TextField reportMatriculeField;
    @FXML private TableView<Eleve> studentsTable;
    @FXML private TableView<Evaluation> reportTable;
    @FXML private Label statusLabel;

    // MATIERES
    @FXML private TextField matiereCodeField;
    @FXML private TextField matiereLibelleField;
    @FXML private TableView<Matiere> matieresTable;
    @FXML private Label matiereStatusLabel;

    // CLASSES
    @FXML private TextField classeLibelleField;
    @FXML private ComboBox<Employe> classeProfCombo;
    @FXML private TableView<Classe> classesTable;
    @FXML private Label classeStatusLabel;

    // ENSEIGNANTS
    @FXML private TextField ensNomField;
    @FXML private TextField ensPrenomField;
    @FXML private TextField ensPosteField;
    @FXML private TextField ensTelField;
    @FXML private TextField ensEmailField;
    @FXML private TableView<Employe> enseignantsTable;
    @FXML private Label ensStatusLabel;

    // AFFECTATIONS
    @FXML private ComboBox<Employe> affEnseignantCombo;
    @FXML private ComboBox<Matiere> affMatiereCombo;
    @FXML private ComboBox<Classe>  affClasseCombo;
    @FXML private TableView<Affectation> affectationsTable;
    @FXML private Label affStatusLabel;

    // EMPLOI DU TEMPS
    @FXML private ComboBox<Classe>      edtClasseCombo;
    @FXML private ComboBox<Affectation> edtAffectationCombo;
    @FXML private ComboBox<String>      edtJourCombo;
    @FXML private ComboBox<String>      edtHeureDebutCombo;
    @FXML private ComboBox<String>      edtHeureFinCombo;
    @FXML private TableView<Programme>  edtTable;
    @FXML private Label edtStatusLabel;

    // DONNEES
    private final SchoolRepository repository = new SchoolRepository();
    private final ObservableList<Eleve>       studentsData     = FXCollections.observableArrayList();
    private final ObservableList<Evaluation>  reportData       = FXCollections.observableArrayList();
    private final ObservableList<Matiere>     matieresData     = FXCollections.observableArrayList();
    private final ObservableList<Classe>      classesData      = FXCollections.observableArrayList();
    private final ObservableList<Employe>     enseignantsData  = FXCollections.observableArrayList();
    private final ObservableList<Affectation> affectationsData = FXCollections.observableArrayList();
    private final ObservableList<Programme>   programmesData   = FXCollections.observableArrayList();

    private static final String[] JOURS  = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"};
    private static final String[] HEURES = {"07:30","09:30","11:30","13:30","15:30","17:30"};

    @FXML
    public void initialize() {
        studentsData.setAll(repository.getEleves());
        matieresData.setAll(repository.getMatieres());
        classesData.setAll(repository.getClasses());
        enseignantsData.setAll(repository.getEmployes());
        affectationsData.setAll(repository.getAffectations());
        programmesData.setAll(repository.getProgrammes());

        initElevesTab();
        initBulletinTab();
        initMatieresTab();
        initClassesTab();
        initEnseignantsTab();
        initAffectationsTab();
        initEdtTab();

        showStatus("Bienvenue dans StudIA !");
    }

    // ====== ELEVES ======

    private void initElevesTab() {
        studentsTable.setItems(studentsData);
        studentsTable.getColumns().setAll(
            col("Matricule", 130, d -> d.getValue().getMatricule()),
            col("Nom complet", 220, d -> d.getValue().getNomComplet()),
            col("Naissance", 110, d -> d.getValue().getDateNaissance()),
            col("Lieu", 120, d -> d.getValue().getLieuNaissance()),
            col("Sexe", 60, d -> d.getValue().getSexe()),
            col("Nationalite", 130, d -> d.getValue().getNationalite())
        );
        studentsTable.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                populateStudentForm(sel);
                reportMatriculeField.setText(sel.getMatricule());
                reportData.setAll(repository.getEvaluationsForStudent(sel));
            }
        });
    }

    @FXML protected void onCreateStudent() {
        if (isStudentFormInvalid()) { showStatus("Remplissez tous les champs."); return; }
        String matricule = matriculeField.getText().trim();
        if (repository.findEleveByMatricule(matricule) != null) { showStatus("Matricule deja utilise."); return; }
        Eleve eleve = new Eleve(matricule, nomField.getText().trim(), prenomField.getText().trim(),
                dateNaissanceField.getText().trim(), lieuNaissanceField.getText().trim(),
                sexeField.getText().trim(), "", nationaliteField.getText().trim());
        repository.addEleve(eleve);
        studentsData.setAll(repository.getEleves());
        studentsTable.getSelectionModel().select(eleve);
        showStatus("Eleve ajoute.");
    }

    @FXML protected void onUpdateStudent() {
        Eleve sel = studentsTable.getSelectionModel().getSelectedItem();
        if (sel == null) { showStatus("Selectionnez un eleve."); return; }
        if (isStudentFormInvalid()) { showStatus("Remplissez tous les champs."); return; }
        sel.setMatricule(matriculeField.getText().trim());
        sel.setNom(nomField.getText().trim()); sel.setPrenom(prenomField.getText().trim());
        sel.setDateNaissance(dateNaissanceField.getText().trim());
        sel.setLieuNaissance(lieuNaissanceField.getText().trim());
        sel.setSexe(sexeField.getText().trim()); sel.setNationalite(nationaliteField.getText().trim());
        studentsTable.refresh();
        showStatus("Eleve mis a jour.");
    }

    @FXML protected void onDeleteStudent() {
        Eleve sel = studentsTable.getSelectionModel().getSelectedItem();
        if (sel == null) { showStatus("Selectionnez un eleve."); return; }
        repository.deleteEleve(sel);
        studentsData.setAll(repository.getEleves());
        clearStudentForm(); reportData.clear();
        showStatus("Eleve supprime.");
    }

    @FXML protected void onClearForm() {
        clearStudentForm(); studentsTable.getSelectionModel().clearSelection();
        showStatus("Formulaire reinitialise.");
    }

    private boolean isStudentFormInvalid() {
        return matriculeField.getText().trim().isEmpty() || nomField.getText().trim().isEmpty()
                || prenomField.getText().trim().isEmpty() || dateNaissanceField.getText().trim().isEmpty()
                || lieuNaissanceField.getText().trim().isEmpty() || sexeField.getText().trim().isEmpty()
                || nationaliteField.getText().trim().isEmpty();
    }

    private void clearStudentForm() {
        matriculeField.clear(); nomField.clear(); prenomField.clear();
        dateNaissanceField.clear(); lieuNaissanceField.clear();
        sexeField.clear(); nationaliteField.clear();
    }

    private void populateStudentForm(Eleve e) {
        if (e == null) { clearStudentForm(); return; }
        matriculeField.setText(e.getMatricule()); nomField.setText(e.getNom());
        prenomField.setText(e.getPrenom()); dateNaissanceField.setText(e.getDateNaissance());
        lieuNaissanceField.setText(e.getLieuNaissance()); sexeField.setText(e.getSexe());
        nationaliteField.setText(e.getNationalite());
    }

    private void showStatus(String msg) { statusLabel.setText(msg); }

    // ====== BULLETIN ======

    private void initBulletinTab() {
        reportTable.setItems(reportData);
        reportTable.getColumns().setAll(
            col("Matiere", 200, d -> d.getValue().getMatiere().getLibelle()),
            col("Note", 80, d -> String.format("%.1f", d.getValue().getNote())),
            col("Sequence", 140, d -> d.getValue().getSequence().getLibelle()),
            col("Commentaire", 300, d -> d.getValue().getCommentaire())
        );
    }

    @FXML protected void onLoadReport() {
        String m = reportMatriculeField.getText().trim();
        if (m.isEmpty()) { showStatus("Indiquez un matricule."); return; }
        Eleve eleve = repository.findEleveByMatricule(m);
        if (eleve == null) { showStatus("Aucun eleve trouve."); reportData.clear(); return; }
        studentsTable.getSelectionModel().select(eleve);
        populateStudentForm(eleve);
        reportData.setAll(repository.getEvaluationsForStudent(eleve));
        showStatus("Bulletin charge pour " + eleve.getNomComplet() + ".");
    }

    @FXML protected void onGeneratePdf() {
        String m = reportMatriculeField.getText().trim();
        if (m.isEmpty()) { showStatus("Indiquez un matricule."); return; }
        Eleve eleve = repository.findEleveByMatricule(m);
        if (eleve == null) { showStatus("Aucun eleve trouve."); return; }
        List<Evaluation> evals = repository.getEvaluationsForStudent(eleve);
        Path out = Paths.get(System.getProperty("user.home"), "StudIAReports", "bulletin_" + eleve.getMatricule() + ".pdf");
        try {
            ReportCardGenerator.generateReportCard(eleve, evals, out.toFile());
            showStatus("PDF genere : " + out.toAbsolutePath());
        } catch (IOException ex) { showStatus("Erreur PDF : " + ex.getMessage()); }
    }

    // ====== MATIERES ======

    private void initMatieresTab() {
        matieresTable.setItems(matieresData);
        matieresTable.getColumns().setAll(
            col("Code", 100, d -> d.getValue().getCodeMatiere()),
            col("Matiere", 340, d -> d.getValue().getLibelle())
        );
        matieresTable.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) { matiereCodeField.setText(sel.getCodeMatiere()); matiereLibelleField.setText(sel.getLibelle()); }
        });
    }

    @FXML protected void onAddMatiere() {
        if (repository.addMatiere(matiereCodeField.getText(), matiereLibelleField.getText())) {
            matieresData.setAll(repository.getMatieres()); refreshCombos(); clearMatiereForm();
            matiereStatusLabel.setText("Matiere ajoutee.");
        } else { matiereStatusLabel.setText("Erreur : code ou libelle manquant."); }
    }

    @FXML protected void onUpdateMatiere() {
        Matiere sel = matieresTable.getSelectionModel().getSelectedItem();
        if (sel == null) { matiereStatusLabel.setText("Selectionnez une matiere."); return; }
        if (repository.updateMatiere(sel, matiereCodeField.getText(), matiereLibelleField.getText())) {
            matieresData.setAll(repository.getMatieres()); refreshCombos();
            matiereStatusLabel.setText("Matiere mise a jour.");
        } else { matiereStatusLabel.setText("Erreur : champs manquants."); }
    }

    @FXML protected void onDeleteMatiere() {
        Matiere sel = matieresTable.getSelectionModel().getSelectedItem();
        if (sel == null) { matiereStatusLabel.setText("Selectionnez une matiere."); return; }
        repository.deleteMatiere(sel);
        matieresData.setAll(repository.getMatieres());
        affectationsData.setAll(repository.getAffectations());
        refreshCombos(); clearMatiereForm();
        matiereStatusLabel.setText("Matiere supprimee.");
    }

    private void clearMatiereForm() {
        matiereCodeField.clear(); matiereLibelleField.clear();
        matieresTable.getSelectionModel().clearSelection();
    }

    // ====== CLASSES ======

    private void initClassesTab() {
        classesTable.setItems(classesData);
        classesTable.getColumns().setAll(
            col("Classe", 160, d -> d.getValue().getLibelle()),
            col("Prof. principal", 280, d -> { Employe p = d.getValue().getProfesseurPrincipal(); return p == null ? "-" : p.getNomComplet(); })
        );
        classeProfCombo.setItems(enseignantsData);
        classeProfCombo.setCellFactory(lv -> employeCell()); classeProfCombo.setButtonCell(employeCell());
        classesTable.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) { classeLibelleField.setText(sel.getLibelle()); classeProfCombo.setValue(sel.getProfesseurPrincipal()); }
        });
    }

    @FXML protected void onAddClasse() {
        if (repository.addClass(classeLibelleField.getText(), classeProfCombo.getValue())) {
            classesData.setAll(repository.getClasses()); refreshCombos(); clearClasseForm();
            classeStatusLabel.setText("Classe ajoutee.");
        } else { classeStatusLabel.setText("Erreur : libelle manquant."); }
    }

    @FXML protected void onUpdateClasse() {
        Classe sel = classesTable.getSelectionModel().getSelectedItem();
        if (sel == null) { classeStatusLabel.setText("Selectionnez une classe."); return; }
        if (repository.updateClasse(sel, classeLibelleField.getText(), classeProfCombo.getValue())) {
            classesData.setAll(repository.getClasses()); refreshCombos();
            classeStatusLabel.setText("Classe mise a jour.");
        } else { classeStatusLabel.setText("Erreur : libelle manquant."); }
    }

    @FXML protected void onDeleteClasse() {
        Classe sel = classesTable.getSelectionModel().getSelectedItem();
        if (sel == null) { classeStatusLabel.setText("Selectionnez une classe."); return; }
        repository.deleteClasse(sel);
        classesData.setAll(repository.getClasses());
        affectationsData.setAll(repository.getAffectations());
        refreshCombos(); clearClasseForm();
        classeStatusLabel.setText("Classe supprimee.");
    }

    private void clearClasseForm() {
        classeLibelleField.clear(); classeProfCombo.setValue(null);
        classesTable.getSelectionModel().clearSelection();
    }

    // ====== ENSEIGNANTS ======

    private void initEnseignantsTab() {
        enseignantsTable.setItems(enseignantsData);
        enseignantsTable.getColumns().setAll(
            col("ID", 80, d -> d.getValue().getIdentifiant()),
            col("Nom complet", 200, d -> d.getValue().getNomComplet()),
            col("Poste", 140, d -> d.getValue().getPoste()),
            col("Telephone", 150, d -> d.getValue().getTelephone()),
            col("Email", 200, d -> d.getValue().getEmail())
        );
        enseignantsTable.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                ensNomField.setText(sel.getNom()); ensPrenomField.setText(sel.getPrenom());
                ensPosteField.setText(sel.getPoste()); ensTelField.setText(sel.getTelephone());
                ensEmailField.setText(sel.getEmail());
            }
        });
    }

    @FXML protected void onAddEnseignant() {
        if (repository.addEmploye(ensNomField.getText(), ensPrenomField.getText(),
                ensPosteField.getText(), ensTelField.getText(), ensEmailField.getText())) {
            enseignantsData.setAll(repository.getEmployes()); refreshCombos(); clearEnsForm();
            ensStatusLabel.setText("Enseignant ajoute.");
        } else { ensStatusLabel.setText("Erreur : nom et prenom requis."); }
    }

    @FXML protected void onUpdateEnseignant() {
        Employe sel = enseignantsTable.getSelectionModel().getSelectedItem();
        if (sel == null) { ensStatusLabel.setText("Selectionnez un enseignant."); return; }
        if (repository.updateEmploye(sel, ensNomField.getText(), ensPrenomField.getText(),
                ensPosteField.getText(), ensTelField.getText(), ensEmailField.getText())) {
            enseignantsData.setAll(repository.getEmployes()); refreshCombos();
            ensStatusLabel.setText("Enseignant mis a jour.");
        } else { ensStatusLabel.setText("Erreur : nom requis."); }
    }

    @FXML protected void onDeleteEnseignant() {
        Employe sel = enseignantsTable.getSelectionModel().getSelectedItem();
        if (sel == null) { ensStatusLabel.setText("Selectionnez un enseignant."); return; }
        repository.deleteEmploye(sel);
        enseignantsData.setAll(repository.getEmployes());
        affectationsData.setAll(repository.getAffectations());
        refreshCombos(); clearEnsForm();
        ensStatusLabel.setText("Enseignant supprime.");
    }

    private void clearEnsForm() {
        ensNomField.clear(); ensPrenomField.clear(); ensPosteField.clear();
        ensTelField.clear(); ensEmailField.clear();
        enseignantsTable.getSelectionModel().clearSelection();
    }

    // ====== AFFECTATIONS ======

    private void initAffectationsTab() {
        affectationsTable.setItems(affectationsData);
        affectationsTable.getColumns().setAll(
            col("Enseignant", 200, d -> d.getValue().getEnseignant().getNomComplet()),
            col("Matiere", 180, d -> d.getValue().getMatiere().getLibelle()),
            col("Classe", 140, d -> d.getValue().getClasse().getLibelle())
        );
        affEnseignantCombo.setItems(enseignantsData);
        affEnseignantCombo.setCellFactory(lv -> employeCell()); affEnseignantCombo.setButtonCell(employeCell());
        affMatiereCombo.setItems(matieresData);
        affMatiereCombo.setCellFactory(lv -> matiereCell()); affMatiereCombo.setButtonCell(matiereCell());
        affClasseCombo.setItems(classesData);
        affClasseCombo.setCellFactory(lv -> classeCell()); affClasseCombo.setButtonCell(classeCell());
    }

    @FXML protected void onAddAffectation() {
        if (repository.addAffectation(affEnseignantCombo.getValue(), affMatiereCombo.getValue(), affClasseCombo.getValue())) {
            affectationsData.setAll(repository.getAffectations()); refreshEdtAffectationCombo();
            affStatusLabel.setText("Affectation ajoutee.");
        } else { affStatusLabel.setText("Erreur : selectionnez tous les champs ou affectation deja existante."); }
    }

    @FXML protected void onDeleteAffectation() {
        Affectation sel = affectationsTable.getSelectionModel().getSelectedItem();
        if (sel == null) { affStatusLabel.setText("Selectionnez une affectation."); return; }
        repository.deleteAffectation(sel);
        affectationsData.setAll(repository.getAffectations());
        programmesData.setAll(repository.getProgrammes());
        refreshEdtAffectationCombo();
        affStatusLabel.setText("Affectation supprimee.");
    }

    // ====== EMPLOI DU TEMPS ======

    private void initEdtTab() {
        edtTable.setItems(programmesData);
        edtTable.getColumns().setAll(
            col("Jour", 90, d -> d.getValue().getCrenauHoraire().getJour()),
            col("Horaire", 110, d -> d.getValue().getCrenauHoraire().getHeureDebut() + " - " + d.getValue().getCrenauHoraire().getHeureFin()),
            col("Matiere", 180, d -> d.getValue().getAffectation().getMatiere().getLibelle()),
            col("Enseignant", 200, d -> d.getValue().getAffectation().getEnseignant().getNomComplet()),
            col("Classe", 120, d -> d.getValue().getAffectation().getClasse().getLibelle()),
            col("Salle", 80, d -> d.getValue().getSalle() == null ? "-" : d.getValue().getSalle().getCode())
        );
        edtClasseCombo.setItems(classesData);
        edtClasseCombo.setCellFactory(lv -> classeCell()); edtClasseCombo.setButtonCell(classeCell());
        edtClasseCombo.setOnAction(e -> onEdtClasseSelected());
        edtAffectationCombo.setCellFactory(lv -> affectationCell()); edtAffectationCombo.setButtonCell(affectationCell());
        edtJourCombo.setItems(FXCollections.observableArrayList(JOURS));
        edtHeureDebutCombo.setItems(FXCollections.observableArrayList(HEURES));
        edtHeureFinCombo.setItems(FXCollections.observableArrayList(HEURES));
        edtJourCombo.getSelectionModel().selectFirst();
        edtHeureDebutCombo.getSelectionModel().selectFirst();
        edtHeureFinCombo.getSelectionModel().select(1);
    }

    private void onEdtClasseSelected() {
        Classe sel = edtClasseCombo.getValue();
        if (sel == null) return;
        ObservableList<Affectation> f = FXCollections.observableArrayList();
        for (Affectation a : repository.getAffectations())
            if (a.getClasse().getIdentifiantClasse() == sel.getIdentifiantClasse()) f.add(a);
        edtAffectationCombo.setItems(f);
        programmesData.setAll(repository.getProgrammesForClasse(sel));
        edtStatusLabel.setText("Emploi du temps de " + sel.getLibelle() + ".");
    }

    @FXML protected void onAddProgramme() {
        Affectation aff = edtAffectationCombo.getValue();
        String jour = edtJourCombo.getValue(), debut = edtHeureDebutCombo.getValue(), fin = edtHeureFinCombo.getValue();
        if (aff == null || jour == null || debut == null || fin == null) { edtStatusLabel.setText("Selectionnez tous les champs."); return; }
        if (debut.compareTo(fin) >= 0) { edtStatusLabel.setText("L'heure de fin doit etre apres l'heure de debut."); return; }
        if (repository.addProgramme(aff, jour, debut, fin)) {
            Classe cls = edtClasseCombo.getValue();
            programmesData.setAll(cls != null ? repository.getProgrammesForClasse(cls) : repository.getProgrammes());
            edtStatusLabel.setText("Creneau ajoute : " + jour + " " + debut + "-" + fin);
        } else { edtStatusLabel.setText("Conflit : ce creneau est deja occupe pour cette classe."); }
    }

    @FXML protected void onDeleteProgramme() {
        Programme sel = edtTable.getSelectionModel().getSelectedItem();
        if (sel == null) { edtStatusLabel.setText("Selectionnez un creneau."); return; }
        repository.deleteProgramme(sel);
        Classe cls = edtClasseCombo.getValue();
        programmesData.setAll(cls != null ? repository.getProgrammesForClasse(cls) : repository.getProgrammes());
        edtStatusLabel.setText("Creneau supprime.");
    }

    @FXML protected void onShowAllProgrammes() {
        edtClasseCombo.setValue(null);
        edtAffectationCombo.setItems(FXCollections.observableArrayList(repository.getAffectations()));
        programmesData.setAll(repository.getProgrammes());
        edtStatusLabel.setText("Affichage de tous les creneaux.");
    }

    // ====== UTILITAIRES ======

    private void refreshCombos() {
        classeProfCombo.setItems(null); classeProfCombo.setItems(enseignantsData);
        refreshEdtAffectationCombo();
    }

    private void refreshEdtAffectationCombo() {
        Classe cls = edtClasseCombo.getValue();
        if (cls != null) {
            ObservableList<Affectation> f = FXCollections.observableArrayList();
            for (Affectation a : repository.getAffectations())
                if (a.getClasse().getIdentifiantClasse() == cls.getIdentifiantClasse()) f.add(a);
            edtAffectationCombo.setItems(f);
        }
    }

    @FunctionalInterface
    private interface CellValueGetter<T> {
        String get(javafx.scene.control.TableColumn.CellDataFeatures<T, String> d);
    }

    private <T> TableColumn<T, String> col(String title, double width, CellValueGetter<T> getter) {
        TableColumn<T, String> c = new TableColumn<>(title);
        c.setCellValueFactory(d -> new ReadOnlyStringWrapper(getter.get(d)));
        c.setPrefWidth(width);
        return c;
    }

    private ListCell<Employe> employeCell() {
        return new ListCell<>() {
            @Override protected void updateItem(Employe e, boolean empty) {
                super.updateItem(e, empty); setText(empty || e == null ? "" : e.getNomComplet());
            }
        };
    }

    private ListCell<Matiere> matiereCell() {
        return new ListCell<>() {
            @Override protected void updateItem(Matiere m, boolean empty) {
                super.updateItem(m, empty); setText(empty || m == null ? "" : m.getLibelle());
            }
        };
    }

    private ListCell<Classe> classeCell() {
        return new ListCell<>() {
            @Override protected void updateItem(Classe c, boolean empty) {
                super.updateItem(c, empty); setText(empty || c == null ? "" : c.getLibelle());
            }
        };
    }

    private ListCell<Affectation> affectationCell() {
        return new ListCell<>() {
            @Override protected void updateItem(Affectation a, boolean empty) {
                super.updateItem(a, empty);
                setText(empty || a == null ? "" : a.getMatiere().getLibelle() + " - " + a.getEnseignant().getNomComplet());
            }
        };
    }
}
