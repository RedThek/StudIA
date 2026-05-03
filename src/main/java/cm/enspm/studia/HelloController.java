package cm.enspm.studia;

import cm.enspm.studia.model.examens.Evaluation;
import cm.enspm.studia.model.personnes.Eleve;
import cm.enspm.studia.model.syllabus.Matiere;
import cm.enspm.studia.service.ReportCardGenerator;
import cm.enspm.studia.service.DatabaseService;
import cm.enspm.studia.service.ServicesEleve;
import cm.enspm.studia.service.SchoolRepository;
import cm.enspm.studia.session.SessionUtilisateur;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

//By MKR_fire
import cm.enspm.studia.model.examens.Sequence;


public class HelloController {

    @FXML
    private TextField matriculeField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField dateNaissanceField;
    @FXML
    private TextField lieuNaissanceField;
    @FXML
    private TextField sexeField;
    @FXML
    private TextField nationaliteField;
    @FXML
    private TextField reportMatriculeField;
    @FXML
    private TableView<Eleve> studentsTable;
    @FXML
    private TableView<Evaluation> reportTable;
    @FXML
    private Label statusLabel;

    //By MKR_fire
    @FXML
    private TextField evaluationMatriculeField;
    @FXML
    private ComboBox<Matiere> matiereComboBox;
    @FXML
    private ComboBox<Sequence> sequenceComboBox;
    @FXML
    private TextField noteField;
    @FXML
    private TextField evaluationDateField;
    @FXML
    private TextField commentaireField;
    @FXML
    private TableView<Evaluation> evaluationsTable;

    private final ObservableList<Evaluation> evaluationsData = FXCollections.observableArrayList();
    private final SchoolRepository repository = new SchoolRepository();
    private final ServicesEleve eleveService;
    private final ObservableList<Eleve> studentsData = FXCollections.observableArrayList();
    private final ObservableList<Evaluation> reportData = FXCollections.observableArrayList();

    public HelloController() {
        try {
            this.eleveService = new ServicesEleve(
                    DatabaseService.getInstance().getEleveRepository(),
                    new SessionUtilisateur());
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion à la base de données", e);
        }
    }

    @FXML
    public void initialize() {
        studentsData.setAll(eleveService.listerEleves());
        studentsTable.setItems(studentsData);
        reportTable.setItems(reportData);

        TableColumn<Eleve, String> matriculeColumn = new TableColumn<>("Matricule");
        matriculeColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getMatricule()));
        matriculeColumn.setPrefWidth(140);

        TableColumn<Eleve, String> nameColumn = new TableColumn<>("Nom complet");
        nameColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getNom() + " " + data.getValue().getPrenom()));
        nameColumn.setPrefWidth(220);

        TableColumn<Eleve, String> naissanceColumn = new TableColumn<>("Naissance");
        naissanceColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getDateNaissance().toString()));
        naissanceColumn.setPrefWidth(120);

        TableColumn<Eleve, String> lieuColumn = new TableColumn<>("Lieu");
        lieuColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getLieuNaissance()));
        lieuColumn.setPrefWidth(120);

        TableColumn<Eleve, String> sexeColumn = new TableColumn<>("Sexe");
        sexeColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getSexe()));
        sexeColumn.setPrefWidth(80);

        TableColumn<Eleve, String> nationaliteColumn = new TableColumn<>("Nationalité");
        nationaliteColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getNationalite()));
        nationaliteColumn.setPrefWidth(140);

        studentsTable.getColumns().setAll(matriculeColumn, nameColumn, naissanceColumn, lieuColumn, sexeColumn, nationaliteColumn);

        TableColumn<Evaluation, String> matiereColumn = new TableColumn<>("Matière");
        matiereColumn.setCellValueFactory(data -> {
            Set<String> matieres = data.getValue().getClasses().stream()
                    .flatMap(c -> c.getNiveauEtude().getMatieres().stream())
                    .map(Matiere::getDesignation)
                    .collect(java.util.stream.Collectors.toSet());
            return new ReadOnlyStringWrapper(matieres.isEmpty() ? "" : matieres.iterator().next());
        });
        matiereColumn.setPrefWidth(200);

        TableColumn<Evaluation, String> noteColumn = new TableColumn<>("Note");
        noteColumn.setCellValueFactory(data -> {
            Double note = data.getValue().getNotes().isEmpty() ? 0.0 : data.getValue().getNotes().iterator().next();
            return new ReadOnlyStringWrapper(String.format("%.1f", note));
        });
        noteColumn.setPrefWidth(80);

        TableColumn<Evaluation, String> sequenceColumn = new TableColumn<>("Séquence");
        sequenceColumn.setCellValueFactory(data -> {
            Set<String> sequences = data.getValue().getTrimestre().stream()
                    .flatMap(t -> t.getSequences().stream())
                    .map(Sequence::getLibelle)
                    .collect(java.util.stream.Collectors.toSet());
            return new ReadOnlyStringWrapper(sequences.isEmpty() ? "" : sequences.iterator().next());
        });
        sequenceColumn.setPrefWidth(140);

        TableColumn<Evaluation, String> commentaireColumn = new TableColumn<>("Commentaire");
        commentaireColumn.setCellValueFactory(data -> {
            String comment = data.getValue().getCommentaire().isEmpty() ? "" : data.getValue().getCommentaire().iterator().next();
            return new ReadOnlyStringWrapper(comment);
        });
        commentaireColumn.setPrefWidth(300);

        //By MKR_fire
        matiereComboBox.setItems(FXCollections.observableArrayList(repository.getMatieres()));
        sequenceComboBox.setItems(FXCollections.observableArrayList(repository.getSequences()));
        evaluationsData.setAll(repository.getEvaluations());
        evaluationsTable.setItems(evaluationsData);

        TableColumn<Evaluation, String> eleveEvalColumn = new TableColumn<>("Élève");
        eleveEvalColumn.setCellValueFactory(data -> {
            Set<String> eleves = data.getValue().getClasses().stream()
                    .flatMap(c -> c.getEleves().stream())
                    .map(Eleve::getMatricule)
                    .collect(java.util.stream.Collectors.toSet());
            return new ReadOnlyStringWrapper(eleves.isEmpty() ? "" : eleves.iterator().next());
        });

        TableColumn<Evaluation, String> matiereEvalColumn = new TableColumn<>("Matière");
        matiereEvalColumn.setCellValueFactory(data -> {
            Set<String> matieres = data.getValue().getClasses().stream()
                    .flatMap(c -> c.getNiveauEtude().getMatieres().stream())
                    .map(Matiere::getDesignation)
                    .collect(java.util.stream.Collectors.toSet());
            return new ReadOnlyStringWrapper(matieres.isEmpty() ? "" : matieres.iterator().next());
        });

        TableColumn<Evaluation, String> noteEvalColumn = new TableColumn<>("Note");
        noteEvalColumn.setCellValueFactory(data -> {
            Double note = data.getValue().getNotes().isEmpty() ? 0.0 : data.getValue().getNotes().iterator().next();
            return new ReadOnlyStringWrapper(String.format("%.1f", note));
        });

        TableColumn<Evaluation, String> sequenceEvalColumn = new TableColumn<>("Séquence");
        sequenceEvalColumn.setCellValueFactory(data -> {
            Set<String> sequences = data.getValue().getTrimestre().stream()
                    .flatMap(t -> t.getSequences().stream())
                    .map(Sequence::getLibelle)
                    .collect(java.util.stream.Collectors.toSet());
            return new ReadOnlyStringWrapper(sequences.isEmpty() ? "" : sequences.iterator().next());
        });

        evaluationsTable.getColumns().setAll(
                eleveEvalColumn,
                matiereEvalColumn,
                noteEvalColumn,
                sequenceEvalColumn);
        
        reportTable.getColumns().setAll(matiereColumn, noteColumn, sequenceColumn, commentaireColumn);

        studentsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateStudentForm(newSelection);
                reportMatriculeField.setText(newSelection.getMatricule());
                updateReportTable(newSelection);
            }
        });

        showStatus("Bienvenue ! / Welcome! Sélectionnez un élève ou créez-en un nouveau.");
    }

    @FXML
    protected void onCreateEvaluation() {
        Eleve eleve = repository.findEleveByMatricule(evaluationMatriculeField.getText().trim());

        if (eleve == null) {
            showStatus("Aucun élève trouvé pour ce matricule.");
            return;
        }

        Matiere matiere = matiereComboBox.getValue();
        Sequence sequence = sequenceComboBox.getValue();

        if (matiere == null || sequence == null) {
            showStatus("Choisissez une matière et une séquence.");
            return;
        }

        double note;
        try {
            note = Double.parseDouble(noteField.getText().trim());
        } catch (NumberFormatException exception) {
            showStatus("La note doit être un nombre.");
            return;
        }

        try {
            Evaluation evaluation = new Evaluation(
                    java.util.Collections.emptySet(),
                    java.util.Collections.emptySet(),
                    java.util.Collections.emptySet(),
                    java.util.Collections.singleton(repository.findClasseForEleve(eleve)),
                    java.util.Collections.singleton(note),
                    java.util.Collections.singleton(commentaireField.getText().trim())
            );

            repository.addEvaluation(evaluation);
            evaluationsData.add(evaluation);

            if (eleve.equals(studentsTable.getSelectionModel().getSelectedItem())) {
                updateReportTable(eleve);
            }

            showStatus("Évaluation ajoutée avec succès.");
        } catch (Exception e) {
            showStatus("Erreur lors de l'ajout de l'évaluation: " + e.getMessage());
        }
    }

    @FXML
    protected void onDeleteEvaluation() {
        Evaluation selected = evaluationsTable.getSelectionModel().getSelectedItem();

            if (selected == null) {
                showStatus("Sélectionnez une évaluation à supprimer.");
                return;
            }

            repository.deleteEvaluation(selected);
            evaluationsData.remove(selected);

            Eleve selectedEleve = studentsTable.getSelectionModel().getSelectedItem();
            if (selectedEleve != null) {
                updateReportTable(selectedEleve);
            }

            showStatus("Évaluation supprimée.");
        }


    @FXML
    protected void onCreateStudent() {
        if (isStudentFormInvalid()) {
            showStatus("Veuillez remplir tous les champs de l'élève.");
            return;
        }
        String matricule = matriculeField.getText().trim();
        if (eleveService.rechercherEleveParMatricule(matricule) != null) {
            showStatus("Un élève avec ce matricule existe déjà.");
            return;
        }

        try {
            LocalDate.parse(dateNaissanceField.getText().trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Eleve eleve = new Eleve(
                matricule,
                nomField.getText().trim(),
                prenomField.getText().trim(),
                dateNaissanceField.getText().trim(),
                lieuNaissanceField.getText().trim(),
                sexeField.getText().trim(),
                "",
                nationaliteField.getText().trim(),
                null
            );

            eleveService.enregistrementEleve(eleve);
            studentsData.setAll(eleveService.listerEleves());
            showStatus("Élève ajouté avec succès.");
        } catch (Exception e) {
            showStatus("Erreur lors de l'ajout de l'élève: " + e.getMessage());
        }
    }

    @FXML
    protected void onUpdateStudent() {
        Eleve selected = studentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showStatus("Sélectionnez un élève pour mettre à jour.");
            return;
        }
        if (isStudentFormInvalid()) {
            showStatus("Veuillez remplir tous les champs de l'élève.");
            return;
        }

        try {
            LocalDate.parse(dateNaissanceField.getText().trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Eleve updatedEleve = new Eleve(
                matriculeField.getText().trim(),
                nomField.getText().trim(),
                prenomField.getText().trim(),
                dateNaissanceField.getText().trim(),
                lieuNaissanceField.getText().trim(),
                sexeField.getText().trim(),
                selected.getPhoto(),
                nationaliteField.getText().trim(),
                selected.getClasse()
            );

            eleveService.modifierEleve(updatedEleve);
            studentsData.setAll(eleveService.listerEleves());
            showStatus("Élève mis à jour avec succès.");
        } catch (Exception e) {
            showStatus("Erreur lors de la mise à jour de l'élève: " + e.getMessage());
        }
    }

    @FXML
    protected void onDeleteStudent() {
        Eleve selected = studentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showStatus("Sélectionnez un élève pour le supprimer.");
            return;
        }
        try {
            eleveService.SupprimerEleve(selected.getMatricule());
            studentsData.setAll(eleveService.listerEleves());
            clearStudentForm();
            reportData.clear();
            showStatus("Élève supprimé.");
        } catch (Exception e) {
            showStatus("Erreur lors de la suppression de l'élève: " + e.getMessage());
        }
    }

    @FXML
    protected void onClearForm() {
        clearStudentForm();
        studentsTable.getSelectionModel().clearSelection();
        showStatus("Formulaire réinitialisé.");
    }

    @FXML
    protected void onLoadReport() {
        String matricule = reportMatriculeField.getText().trim();
        if (matricule.isEmpty()) {
            showStatus("Indiquez un matricule pour charger le bulletin.");
            return;
        }
        Eleve eleve = eleveService.rechercherEleveParMatricule(matricule);
        if (eleve == null) {
            showStatus("Aucun élève trouvé pour ce matricule.");
            reportData.clear();
            return;
        }
        studentsTable.getSelectionModel().select(eleve);
        populateStudentForm(eleve);
        updateReportTable(eleve);
        showStatus("Bulletin chargé pour " + eleve.getNomComplet() + ".");
    }

    @FXML
    protected void onGeneratePdf() {
        String matricule = reportMatriculeField.getText().trim();
        if (matricule.isEmpty()) {
            showStatus("Indiquez un matricule avant de générer le PDF.");
            return;
        }
        Eleve eleve = eleveService.rechercherEleveParMatricule(matricule);
        if (eleve == null) {
            showStatus("Aucun élève trouvé pour ce matricule.");
            return;
        }

        List<Evaluation> evaluations = repository.getEvaluationsForStudent(eleve);
        Path outputPath = Paths.get(System.getProperty("user.home"), "StudIAReports", "bulletin_" + eleve.getMatricule() + ".pdf");
        try {
            ReportCardGenerator.generateReportCard(eleve, evaluations, outputPath.toFile());
            showStatus("PDF généré: " + outputPath.toAbsolutePath());
        } catch (IOException exception) {
            showStatus("Erreur pendant la génération du PDF: " + exception.getMessage());
        }
    }

    private boolean isStudentFormInvalid() {
        return matriculeField.getText().trim().isEmpty() || nomField.getText().trim().isEmpty()
                || prenomField.getText().trim().isEmpty() || dateNaissanceField.getText().trim().isEmpty()
                || lieuNaissanceField.getText().trim().isEmpty() || sexeField.getText().trim().isEmpty()
                || nationaliteField.getText().trim().isEmpty();
    }

    private void clearStudentForm() {
        matriculeField.clear();
        nomField.clear();
        prenomField.clear();
        dateNaissanceField.clear();
        lieuNaissanceField.clear();
        sexeField.clear();
        nationaliteField.clear();
    }

    private void populateStudentForm(Eleve eleve) {
        if (eleve == null) {
            clearStudentForm();
            return;
        }
        matriculeField.setText(eleve.getMatricule());
        nomField.setText(eleve.getNom());
        prenomField.setText(eleve.getPrenom());
        dateNaissanceField.setText(eleve.getDateNaissance());
        lieuNaissanceField.setText(eleve.getLieuNaissance());
        sexeField.setText(eleve.getSexe());
        nationaliteField.setText(eleve.getNationalite());
    }

    private void updateReportTable(Eleve eleve) {
        reportData.setAll(repository.getEvaluationsForStudent(eleve));
    }

    private void showStatus(String message) {
        statusLabel.setText(message);
    }
}
