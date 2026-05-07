package cm.enspm.studia.ui.controller;

import cm.enspm.studia.core.ViewManager;
import cm.enspm.studia.mapper.EleveMapper;
import cm.enspm.studia.model.fx.FxClasses;
import cm.enspm.studia.model.fx.FxEleve;
import cm.enspm.studia.model.personnes.Eleve;
import cm.enspm.studia.service.ServicesEleve;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;

public class EleveController {
    
    // Service layer for student business logic and database operations
    private final ServicesEleve servicesEleve;
    // Observable list holding the student data displayed in the table
    private final ObservableList<FxEleve> elevesData = FXCollections.observableArrayList();
    // Currently selected student in the table for editing/deleting
    private FxEleve selectedEleve;

    //Boutons du menu gauche
    @FXML private Button btnMenuAccueilFx;
    @FXML private Button btnMenuClassesFx;
    @FXML private Button btnMenuCompteFx;
    @FXML private Button btnMenuElevesFx;
    @FXML private Button btnMenuExamensFx;
    @FXML private Button btnMenuMatieresFx;
    @FXML private Button btnMenuParentsFx;
    @FXML private Button btnMenuPersonnelFx;
    @FXML private Button btnMenuPlannification;

    //Onglet General
    @FXML private Button btnRafraichirAllEleves;

    //Onglet Consulter
    @FXML private Button btnExporterInfosEleveFx;
    @FXML private Button btnRechercheEleveFx;

    // FXML-injected text fields for student form input
    @FXML private TextField champMatriculeFx; // Student matricule input
    @FXML private TextField champNomsFx; // Last name input
    @FXML private TextField champPrenomFx; // First name input
    @FXML private TextField champSexeFx; // Gender input
    @FXML private TextField champClasse; // Class input
    @FXML private TextField champDateNaissanceFx; // Birthdate input
    @FXML private TextField champLieuNaissance; // Birthplace input
    
    // FXML-injected columns for displaying student data
    @FXML private TableColumn<FxEleve, String> colonneClasseFx;
    @FXML private TableColumn<FxEleve, String> colonneDateNaissance;
    @FXML private TableColumn<FxEleve, String> colonneLieuNaissance;
    @FXML private TableColumn<FxEleve, String> colonneMatriculeFx;
    @FXML private TableColumn<FxEleve, String> colonneNomsFx;
    @FXML private TableColumn<FxEleve, String> colonnePrenomsFx;
    @FXML private TableColumn<FxEleve, String> colonneSexeFx;

    //FXML-injected table for displaying all student data
    @FXML
    private TableView<FxEleve> tableAllElevesFx;

    //FXML-injected table for displaying a modified student data
    @FXML
    private TableView<FxEleve> tableModifEleveFx;

    @FXML
    void handleBtnRafraichirAllEleves(MouseEvent event) {
        refreshEleveTable();
    }

    @FXML
    void handleBtnExporterInfosEleve(MouseEvent event) {

    }

    @FXML
    void handleClickMenuAccueil(MouseEvent event) {

    }

    @FXML
    void handleClickMenuClasses(MouseEvent event) {

    }

    @FXML
    void handleClickMenuCompte(MouseEvent event) {

    }

    @FXML
    void handleClickMenuEleves(MouseEvent event) {

    }

    @FXML
    void handleClickMenuExamens(MouseEvent event) {

    }

    @FXML
    void handleClickMenuMatieres(MouseEvent event) {

    }

    @FXML
    void handleClickMenuParents(MouseEvent event) {

    }

    @FXML
    void handleClickMenuPersonnel(MouseEvent event) {

    }

    @FXML
    void handleClickMenuPlannification(MouseEvent event) {

    }

    @FXML
    void handleClickRechercheEleve(MouseEvent event) {

    }

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
        colonneMatriculeFx.setCellValueFactory(data -> data.getValue().matriculeEleve());
        colonneNomsFx.setCellValueFactory(data -> data.getValue().nomEleve());
        colonnePrenomsFx.setCellValueFactory(data -> data.getValue().prenomEleve());
        colonneDateNaissance.setCellValueFactory(data -> data.getValue().dateNaissanceEleve());
        colonneLieuNaissance.setCellValueFactory(data -> data.getValue().lieuNaissanceEleve());
        colonneSexeFx.setCellValueFactory(data -> data.getValue().sexeEleve());
        colonneClasseFx.setCellValueFactory(data -> data.getValue().nationaliteEleve());

        tableAllElevesFx.setItems(elevesData);
        tableModifEleveFx.setItems(elevesData);
        tableModifEleveFx.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
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
        champMatriculeFx.setText(eleve.matriculeEleve().get());
        champNomsFx.setText(eleve.nomEleve().get());
        champPrenomFx.setText(eleve.prenomEleve().get());
        champDateNaissanceFx.setText(eleve.dateNaissanceEleve().get());
        champLieuNaissance.setText(eleve.lieuNaissanceEleve().get());
        champSexeFx.setText(eleve.sexeEleve().get());
        champClasse.setText(eleve.nationaliteEleve().get());
    }

    private void refreshEleveTable() {
        var eleves = servicesEleve.listerEleves();
        elevesData.setAll(eleves.stream().map(EleveMapper::toFxEleve).toList());
    }

    @FXML
    public void onCreateStudent() {
        try {
            Eleve eleve = new Eleve(
                    champMatriculeFx.getText().trim(),
                    champNomsFx.getText().trim(),
                    champPrenomFx.getText().trim(),
                    champDateNaissanceFx.getText().trim(),
                    champLieuNaissance.getText().trim(),
                    champSexeFx.getText().trim(),
                    "", // photo
                    "Camerounaise",
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
            selectedEleve.matriculeEleve().set(champMatriculeFx.getText().trim());
            selectedEleve.nomEleve().set(champNomsFx.getText().trim());
            selectedEleve.prenomEleve().set(champPrenomFx.getText().trim());
            selectedEleve.dateNaissanceEleve().set(champDateNaissanceFx.getText().trim());
            selectedEleve.lieuNaissanceEleve().set(champLieuNaissance.getText().trim());
            selectedEleve.sexeEleve().set(champSexeFx.getText().trim());
            selectedEleve.nationaliteEleve().set(champClasse.getText().trim());

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
        champMatriculeFx.clear();
        champNomsFx.clear();
        champPrenomFx.clear();
        champDateNaissanceFx.clear();
        champLieuNaissance.clear();
        champSexeFx.clear();
        champClasse.clear();
        tableModifEleveFx.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
