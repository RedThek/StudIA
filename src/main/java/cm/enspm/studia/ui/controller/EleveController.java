package cm.enspm.studia.ui.controller;

import cm.enspm.studia.core.ViewManager;
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
    
    private final ServicesEleve servicesEleve = new ServicesEleve();
    private final ViewManager viewManager;
    private final ObservableList<FxEleve> elevesData = FXCollections.observableArrayList();
    private FxEleve eleveActuel; // Reactive model bound to UI

    @FXML
    private TextField nameField;
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
    private TextField photoField;
    @FXML
    private TextField nationaliteField;
    
    @FXML
    private Label statusLabel;

    @FXML
    private TableView<Eleve> elevesTable;

    @FXML private TableColumn<FxEleve, String> matriculeColumn;
    @FXML private TableColumn<FxEleve, String> nomColumn;
    @FXML private TableColumn<FxEleve, String> prenomColumn;
    @FXML private TableColumn<FxEleve, String> dateNaissanceColumn;
    @FXML private TableColumn<FxEleve, String> lieuNaissanceColumn;
    @FXML private TableColumn<FxEleve, String> sexeColumn;
    @FXML private TableColumn<FxEleve, String> nationaliteColumn;

    // Injected via ViewManager's Controller Factory
    public EleveController(ServicesEleve servicesEleve, ViewManager viewManager) {
        this.servicesEleve = servicesEleve;
        this.viewManager = viewManager;
    }

    @FXML
    public void initialize() {
        // Assume currentStudent is initialized. We bind the text field bidirectionally
        // so typing in the UI automatically updates the FxStudent model.
        // nameField.textProperty().bindBidirectional(currentStudent.nameProperty());
        matriculeColumn.setCellValueFactory(data -> data.getValue().matriculeEleve());
        nomColumn.setCellValueFactory(data -> data.getValue().nomEleve());
        prenomColumn.setCellValueFactory(data -> data.getValue().prenomEleve());
        dateNaissanceColumn.setCellValueFactory(data -> data.getValue().dateNaissanceEleve());
        lieuNaissanceColumn.setCellValueFactory(data -> data.getValue().lieuNaissanceEleve());
        sexeColumn.setCellValueFactory(data -> data.getValue().sexeEleve());
        nationaliteColumn.setCellValueFactory(data -> data.getValue().nationaliteEleve());

        elevesTable.setItems(elevesData);
        elevesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            eleveActuel = newSelection;
            if (newSelection != null) {
                bindSelectedEleve(newSelection);
            }
        });

        refreshEleveTable();
    }

    private void bindSelectedEleve(FxEleve eleve) {
        matriculeField.setText(eleve.matriculeEleve().get());
        nomField.setText(eleve.nomEleve().get());
        prenomField.setText(eleve.prenomEleve().get());
        dateNaissanceField.setText(eleve.dateNaissanceEleve().toString());
        lieuNaissanceField.setText(eleve.lieuNaissanceEleve().get());
        sexeField.setText(eleve.sexeEleve().get());
        nationaliteField.setText(eleve.nationaliteEleve().get());
        photoField.setText(eleve.photoEleve().get());
    }

    private void refreshEleveTable() {
        elevesData.setAll(servicesEleve.listerEleves());
    }

    @FXML
    public void onCreateEleve() {
        try {
            Eleve eleve = new Eleve(
                    matriculeField.getText().trim(),
                    nomField.getText().trim(),
                    prenomField.getText().trim(),
                    dateNaissanceField.getText().trim(),
                    lieuNaissanceField.getText().trim(),
                    sexeField.getText().trim(),
                    photoField.getText().trim(),
                    nationaliteField.getText().trim()
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
    public void handleDeleteAction() {
        try {
            // Attempt to delete. The Service checks the Session Context!
            servicesEleve.SupprimerEleve(eleveActuel.matriculeEleve().get());
            
            // If successful, navigate back to the dashboard
            viewManager.switchScene("/fxml/Accueil.fxml", "Accueil");
            
        } catch (SecurityException e) {
            // If the user is BASIC, the requireAdmin() check fails, and we catch it here
            Alert alert = new Alert(Alert.AlertType.ERROR, "Accèss Non Autorisé: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void onClearForm() {
        clearForm();
    }

    private void clearForm() {
        selectedEleve= null;
        matriculeField.clear();
        nomField.clear();
        prenomField.clear();
        dateNaissanceField.clear();
        lieuNaissanceField.clear();
        sexeField.clear();
        nationaliteField.clear();
        elevesTable.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
