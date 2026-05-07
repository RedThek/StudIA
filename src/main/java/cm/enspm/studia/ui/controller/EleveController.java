package cm.enspm.studia.ui.controller;

import cm.enspm.studia.core.ViewManager;
import cm.enspm.studia.mapper.EleveMapper;
import cm.enspm.studia.model.fx.FxEleve;
import cm.enspm.studia.model.personnes.Eleve;
import cm.enspm.studia.service.ServicesEleve;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class EleveController {
    
    // Service layer for student business logic and database operations
    private final ServicesEleve servicesEleve;
    // Observable list holding the student data displayed in the table
    private final ObservableList<FxEleve> elevesData = FXCollections.observableArrayList();
    // Currently selected student in the table for editing/deleting
    private FxEleve selectedEleve;

    // FXML-injected text fields for student form input
    @FXML private TextField matriculeField; // Student matricule input
    @FXML private TextField nomField; // Last name input
    @FXML private TextField prenomField; // First name input
    @FXML private TextField dateNaissanceField; // Birth date input
    @FXML private TextField lieuNaissanceField; // Birth place input
    @FXML private TextField sexeField; // Gender input
    @FXML private TextField nationaliteField; // Nationality input
    
    // FXML-injected table and columns for displaying student data
    @FXML private TableView<FxEleve> studentsTable; // Main table for students
    @FXML private TableColumn<FxEleve, Number> idColumn; // ID column
    @FXML private TableColumn<FxEleve, String> matriculeColumn; // Matricule column
    @FXML private TableColumn<FxEleve, String> nomColumn; // Last name column
    @FXML private TableColumn<FxEleve, String> prenomColumn; // First name column
    @FXML private TableColumn<FxEleve, String> dateNaissanceColumn; // Birth date column
    @FXML private TableColumn<FxEleve, String> lieuNaissanceColumn; // Birth place column
    @FXML private TableColumn<FxEleve, String> sexeColumn; // Gender column
    @FXML private TableColumn<FxEleve, String> nationaliteColumn; // Nationality column

    public EleveController() {
        try {
            var eleveRepository = cm.enspm.studia.service.DatabaseService.getInstance().getEleveRepository();
            this.servicesEleve = new ServicesEleve(eleveRepository, new cm.enspm.studia.session.SessionUtilisateur());
        } catch (Exception e) {
            throw new RuntimeException("Erreur d'initialisation du service élève", e);
        }
    }

    /**
     * Initializes the controller after FXML loading.
     * Sets up table column value factories and selection listener.
     */
    @FXML
    public void initialize() {
        studentsTable.setItems(elevesData);
        matriculeColumn.setCellValueFactory(data -> data.getValue().matriculeEleve());
        nomColumn.setCellValueFactory(data -> data.getValue().nomEleve());
        prenomColumn.setCellValueFactory(data -> data.getValue().prenomEleve());
        dateNaissanceColumn.setCellValueFactory(data -> data.getValue().dateNaissanceEleve());
        lieuNaissanceColumn.setCellValueFactory(data -> data.getValue().lieuNaissanceEleve());
        sexeColumn.setCellValueFactory(data -> data.getValue().sexeEleve());
        nationaliteColumn.setCellValueFactory(data -> data.getValue().nationaliteEleve());

        studentsTable.setItems(elevesData);
        studentsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedEleve = newSelection;
            if (newSelection != null) {
                bindSelectedEleve(newSelection);
            }
        });

        refreshEleveTable();
    }

    /**
     * Binds the selected student's data to the form fields.
     * @param eleve the selected FxEleve to bind
     */
    private void bindSelectedEleve(FxEleve eleve) {
        matriculeField.setText(eleve.matriculeEleve().get());
        nomField.setText(eleve.nomEleve().get());
        prenomField.setText(eleve.prenomEleve().get());
        dateNaissanceField.setText(eleve.dateNaissanceEleve().get());
        lieuNaissanceField.setText(eleve.lieuNaissanceEleve().get());
        sexeField.setText(eleve.sexeEleve().get());
        nationaliteField.setText(eleve.nationaliteEleve().get());
    }

    private void refreshEleveTable() {
        var eleves = servicesEleve.listerEleves();
        elevesData.setAll(eleves.stream().map(EleveMapper::toFxEleve).toList());
    }

    @FXML
    public void onCreateStudent() {
        try {
            Eleve eleve = new Eleve(
                    matriculeField.getText().trim(),
                    nomField.getText().trim(),
                    prenomField.getText().trim(),
                    dateNaissanceField.getText().trim(),
                    lieuNaissanceField.getText().trim(),
                    sexeField.getText().trim(),
                    "", // photo
                    nationaliteField.getText().trim(),
                    null
            );
            servicesEleve.enregistrerEleve(eleve);
            refreshEleveTable();
            clearForm();
            showAlert(Alert.AlertType.INFORMATION, "Élève ajouté", "L'élève a bien été enregistré.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur d'enregistrement", e.getMessage());
        }
    }

    @FXML
    public void onUpdateStudent() {
        if (selectedEleve == null) {
            showAlert(Alert.AlertType.WARNING, "Aucun élève sélectionné", "Veuillez sélectionner un élève dans la table.");
            return;
        }
        try {
            selectedEleve.matriculeEleve().set(matriculeField.getText().trim());
            selectedEleve.nomEleve().set(nomField.getText().trim());
            selectedEleve.prenomEleve().set(prenomField.getText().trim());
            selectedEleve.dateNaissanceEleve().set(dateNaissanceField.getText().trim());
            selectedEleve.lieuNaissanceEleve().set(lieuNaissanceField.getText().trim());
            selectedEleve.sexeEleve().set(sexeField.getText().trim());
            selectedEleve.nationaliteEleve().set(nationaliteField.getText().trim());

            servicesEleve.modifierEleve(selectedEleve.toDomain());
            refreshEleveTable();
            showAlert(Alert.AlertType.INFORMATION, "Élève modifié", "L'élève a été mis à jour.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur de mise à jour", e.getMessage());
        }
    }

    @FXML
    public void onDeleteStudent() {
        if (selectedEleve == null) {
            showAlert(Alert.AlertType.WARNING, "Aucun élève sélectionné", "Veuillez sélectionner un élève à supprimer.");
            return;
        }
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,
                "Voulez-vous vraiment supprimer cet élève ?", ButtonType.YES, ButtonType.NO);
        confirmation.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.YES) {
                try {
                    servicesEleve.SupprimerEleve(selectedEleve.matriculeEleve().get());
                    refreshEleveTable();
                    clearForm();
                    showAlert(Alert.AlertType.INFORMATION, "Élève supprimé", "L'élève a été supprimé.");
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Erreur de suppression", e.getMessage());
                }
            }
        });
    }

    @FXML
    public void onClearForm() {
        clearForm();
    }

    private void clearForm() {
        selectedEleve = null;
        matriculeField.clear();
        nomField.clear();
        prenomField.clear();
        dateNaissanceField.clear();
        lieuNaissanceField.clear();
        sexeField.clear();
        nationaliteField.clear();
        studentsTable.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
