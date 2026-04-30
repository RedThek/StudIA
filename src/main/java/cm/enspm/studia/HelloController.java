package cm.enspm.studia;

import cm.enspm.studia.model.Evaluation;
import cm.enspm.studia.model.Eleve;
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

    private final SchoolRepository repository = new SchoolRepository();
    private final ObservableList<Eleve> studentsData = FXCollections.observableArrayList();
    private final ObservableList<Evaluation> reportData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        studentsData.setAll(repository.getEleves());
        studentsTable.setItems(studentsData);
        reportTable.setItems(reportData);

        TableColumn<Eleve, String> matriculeColumn = new TableColumn<>("Matricule");
        matriculeColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getMatricule()));
        matriculeColumn.setPrefWidth(140);

        TableColumn<Eleve, String> nameColumn = new TableColumn<>("Nom complet");
        nameColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getNomComplet()));
        nameColumn.setPrefWidth(220);

        TableColumn<Eleve, String> naissanceColumn = new TableColumn<>("Naissance");
        naissanceColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getDateNaissance()));
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
        matiereColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getMatiere().getLibelle()));
        matiereColumn.setPrefWidth(200);

        TableColumn<Evaluation, String> noteColumn = new TableColumn<>("Note");
        noteColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.format("%.1f", data.getValue().getNote())));
        noteColumn.setPrefWidth(80);

        TableColumn<Evaluation, String> sequenceColumn = new TableColumn<>("Séquence");
        sequenceColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getSequence().getLibelle()));
        sequenceColumn.setPrefWidth(140);

        TableColumn<Evaluation, String> commentaireColumn = new TableColumn<>("Commentaire");
        commentaireColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getCommentaire()));
        commentaireColumn.setPrefWidth(300);

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
    protected void onCreateStudent() {
        if (isStudentFormInvalid()) {
            showStatus("Veuillez remplir tous les champs de l'�l�ve.");
            return;
        }
        String matricule = matriculeField.getText().trim();
        if (repository.findEleveByMatricule(matricule) != null) {
            showStatus("Un �l�ve avec ce matricule existe d�j�.");
            return;
        }

        Eleve eleve = new Eleve(
                matricule,
                nomField.getText().trim(),
                prenomField.getText().trim(),
                dateNaissanceField.getText().trim(),
                lieuNaissanceField.getText().trim(),
                sexeField.getText().trim(),
                "",
                nationaliteField.getText().trim()
        );

        repository.addEleve(eleve);
        studentsData.add(eleve);
        studentsTable.getSelectionModel().select(eleve);
        showStatus("�l�ve ajout� avec succ�s.");
    }

    @FXML
    protected void onUpdateStudent() {
        Eleve selected = studentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showStatus("S�lectionnez un �l�ve pour mettre � jour.");
            return;
        }
        if (isStudentFormInvalid()) {
            showStatus("Veuillez remplir tous les champs de l'�l�ve.");
            return;
        }

        selected.setMatricule(matriculeField.getText().trim());
        selected.setNom(nomField.getText().trim());
        selected.setPrenom(prenomField.getText().trim());
        selected.setDateNaissance(dateNaissanceField.getText().trim());
        selected.setLieuNaissance(lieuNaissanceField.getText().trim());
        selected.setSexe(sexeField.getText().trim());
        selected.setNationalite(nationaliteField.getText().trim());
        studentsTable.refresh();
        showStatus("�l�ve mis � jour avec succ�s.");
    }

    @FXML
    protected void onDeleteStudent() {
        Eleve selected = studentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showStatus("S�lectionnez un �l�ve pour le supprimer.");
            return;
        }
        repository.deleteEleve(selected);
        studentsData.remove(selected);
        clearStudentForm();
        reportData.clear();
        showStatus("�l�ve supprim� et ses �valuations supprim�es.");
    }

    @FXML
    protected void onClearForm() {
        clearStudentForm();
        studentsTable.getSelectionModel().clearSelection();
        showStatus("Formulaire r�initialis�.");
    }

    @FXML
    protected void onLoadReport() {
        String matricule = reportMatriculeField.getText().trim();
        if (matricule.isEmpty()) {
            showStatus("Indiquez un matricule pour charger le bulletin.");
            return;
        }
        Eleve eleve = repository.findEleveByMatricule(matricule);
        if (eleve == null) {
            showStatus("Aucun �l�ve trouv� pour ce matricule.");
            reportData.clear();
            return;
        }
        studentsTable.getSelectionModel().select(eleve);
        populateStudentForm(eleve);
        updateReportTable(eleve);
        showStatus("Bulletin charg� pour " + eleve.getNomComplet() + ".");
    }

    @FXML
    protected void onGeneratePdf() {
        String matricule = reportMatriculeField.getText().trim();
        if (matricule.isEmpty()) {
            showStatus("Indiquez un matricule avant de g�n�rer le PDF.");
            return;
        }
        Eleve eleve = repository.findEleveByMatricule(matricule);
        if (eleve == null) {
            showStatus("Aucun �l�ve trouv� pour ce matricule.");
            return;
        }

        List<Evaluation> evaluations = repository.getEvaluationsForStudent(eleve);
        Path outputPath = Paths.get(System.getProperty("user.home"), "StudIAReports", "bulletin_" + eleve.getMatricule() + ".pdf");
        try {
            ReportCardGenerator.generateReportCard(eleve, evaluations, outputPath.toFile());
            showStatus("PDF g�n�r�: " + outputPath.toAbsolutePath());
        } catch (IOException exception) {
            showStatus("Erreur pendant la g�n�ration du PDF: " + exception.getMessage());
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
