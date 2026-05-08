package cm.enspm.studia.ui.controller;

import cm.enspm.studia.service.DatabaseService;
import cm.enspm.studia.session.SessionUtilisateur;
import cm.enspm.studia.core.ViewManager;
import cm.enspm.studia.mapper.EleveMapper;
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
    private final ObservableList<FxEleve> donneesEleve = FXCollections.observableArrayList();
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

    @FXML private TableView<FxEleve> tableAllElevesFx; //FXML-injected table for displaying all student data

    @FXML private TableColumn<FxEleve, String> colonneMatriculesFx; // FXML-injected columns for displaying student data
    @FXML private TableColumn<FxEleve, String> colonneNomsFx; 
    @FXML private TableColumn<FxEleve, String> colonnePrenomsFx;
    @FXML private TableColumn<FxEleve, String> colonneSexesFx;
    @FXML private TableColumn<FxEleve, String> colonneStatutsFx;
    @FXML private TableColumn<FxEleve, String> colonneDatesNaissFx;
    @FXML private TableColumn<FxEleve, String> colonneLieuxNaissFx;

    //Onglet Consulter
    @FXML private TextField champRechercheEleveFx;

    @FXML private Button btnRechercheEleveFx;
    @FXML private Button btnExporterInfosEleveFx;
    
    @FXML private Label consulterNomEleveFx;
    @FXML private Label consulterPrenomEleveFx;
    @FXML private Label consulterDateNaissEleveFx;
    @FXML private Label consulterLieuNaissEleveFx;
    @FXML private Label consulterSexeEleveFx;
    @FXML private Label consulterClasseEleveFx;
    @FXML private Label consulterParentEleveFx;
    @FXML private Label consulterPaysEleveFx;
    @FXML private Label consulterDateInscripEleveFx;

    //Onglet Enregistrer
    @FXML private TextField champSaveMatriculeEleveFx;
    @FXML private TextField champSaveNomEleveFx;
    @FXML private TextField champSavePrenomEleveFx;
    @FXML private TextField champSaveDateNaissEleveFx;
    @FXML private TextField champSaveLieuNaissEleveFx;
    @FXML private TextField champSaveSexeEleveFx;
    @FXML private TextField champSavePaysEleveFx;

    @FXML private Button btnEnregistrerEleve;

    //Onglet Modifier & Supprimer
    @FXML private TextField champRechercheModifEleveFx;

    @FXML private Button btnRechercheModifEleveFx;
    @FXML private Button btnModifEleveFx;
    @FXML private Button btnSupprimEleveFx;

    @FXML private TextField champDateNaissModifEleveFx;
    @FXML private TextField champLieuNaissModifEleveFx;
    @FXML private TextField champMatriculeModifEleveFx;
    @FXML private TextField champNomModifEleveFx;
    @FXML private TextField champPaysModifEleveFx;
    @FXML private TextField champPrenomModifEleveFx;
    @FXML private TextField champSexeModifEleveFx;
    @FXML private TextField champStatutModifEleveFx;

    @FXML private TableView<FxEleve> tableModifEleveFx;  //FXML-injected table for displaying a modified student data

    @FXML private TableColumn<FxEleve, String> colonneModifMatriculeFx;
    @FXML private TableColumn<FxEleve, String> colonneModifNomFx;
    @FXML private TableColumn<FxEleve, String> colonneModifPrenomFx;
    @FXML private TableColumn<FxEleve, String> colonneModifDateNaissFx;
    @FXML private TableColumn<FxEleve, String> colonneModifLieuNaissFx;
    @FXML private TableColumn<FxEleve, String> colonneModifSexeFx;
    @FXML private TableColumn<FxEleve, String> colonneModifPaysFx;
    @FXML private TableColumn<FxEleve, String> colonneModifStatutFx;

    /** FXML-injected text fields for student form input
    @FXML private TextField champMatriculeFx; // Student matricule input
    @FXML private TextField champNomsFx; // Last name input
    @FXML private TextField champPrenomFx; // First name input
    @FXML private TextField champSexeFx; // Gender input
    @FXML private TextField champClasse; // Class input
    @FXML private TextField champDateNaissanceFx; // Birthdate input
    @FXML private TextField champLieuNaissance; // Birthplace input
    **/

    //Menu gauche
    @FXML
    void handleClickMenuAccueil(MouseEvent event) {

    }

    @FXML
    void handleClickMenuEleves(MouseEvent event) {

    }

    @FXML
    void handleClickMenuPersonnel(MouseEvent event) {

    }

    @FXML
    void handleClickMenuParents(MouseEvent event) {

    }

    @FXML
    void handleClickMenuClasses(MouseEvent event) {

    }

    @FXML
    void handleClickMenuMatieres(MouseEvent event) {

    }

    @FXML
    void handleClickMenuExamens(MouseEvent event) {

    }

    @FXML
    void handleClickMenuPlannification(MouseEvent event) {

    }

    @FXML
    void handleClickMenuCompte(MouseEvent event) {

    }

    //Onglet General
    @FXML
    void handleBtnRafraichirAllEleves(MouseEvent event) {
        refreshEleveTable();
    }

    //Onglet Consulter
    @FXML
    void handleClickRechercheEleve(MouseEvent event) {
        String matricule = champRechercheEleveFx.getText().trim();
        if (matricule.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Recherche invalide", "Veuillez entrer un matricule pour la recherche.");
            return;
        }
        Eleve eleve = servicesEleve.rechercherEleveParMatricule(matricule);
        if (eleve != null) {
            afficherDetailEleve(eleve);
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Élève non trouvé", "Aucun élève trouvé avec ce matricule.");
            afficherDetailEleve(null);
        }
    }

    @FXML
    void handleClickExporterInfosEleve(MouseEvent event) {

    }
    
    //Onglet Enregistrer
    @FXML
    void handleClickBtnSaveEleveFx(MouseEvent event) {

    }
    
    //Onglet Modifier  
    @FXML
    void handleClickRechercheModifEleveFx(MouseEvent event) {

    }

    @FXML
    void handleClickModifEleveFx(MouseEvent event) {

    }

    @FXML
    void handleClickSupprimEleveFx(MouseEvent event) {

    }

    public EleveController() {
        try {
            var eleveRepository = DatabaseService.getInstance().getEleveRepository();
            this.servicesEleve = new ServicesEleve(eleveRepository, new SessionUtilisateur());
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
        colonneMatriculesFx.setCellValueFactory(data -> data.getValue().matriculeEleve());
        colonneNomsFx.setCellValueFactory(data -> data.getValue().nomEleve());
        colonnePrenomsFx.setCellValueFactory(data -> data.getValue().prenomEleve());
        colonneSexesFx.setCellValueFactory(data -> data.getValue().sexeEleve());
        colonneStatutsFx.setCellValueFactory(data -> data.getValue().statutEleve());
        colonneDatesNaissFx.setCellValueFactory(data -> data.getValue().dateNaissanceEleve());
        colonneLieuxNaissFx.setCellValueFactory(data -> data.getValue().lieuNaissanceEleve());
        
        tableAllElevesFx.setItems(elevesData);

        colonneModifMatriculeFx.setCellValueFactory(data -> data.getValue().matriculeEleve());
        colonneModifNomFx.setCellValueFactory(data -> data.getValue().nomEleve());
        colonneModifPrenomFx.setCellValueFactory(data -> data.getValue().prenomEleve());
        colonneModifDateNaissFx.setCellValueFactory(data -> data.getValue().dateNaissanceEleve());
        colonneModifLieuNaissFx.setCellValueFactory(data -> data.getValue().lieuNaissanceEleve());
        colonneModifSexeFx.setCellValueFactory(data -> data.getValue().sexeEleve());
        colonneModifPaysFx.setCellValueFactory(data -> data.getValue().nationaliteEleve());
        colonneModifStatutFx.setCellValueFactory(data -> data.getValue().statutEleve());

        tableModifEleveFx.setItems(donneesEleve);

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
        champMatriculeModifEleveFx.setText(eleve.matriculeEleve().get());
        champNomModifEleveFx.setText(eleve.nomEleve().get());
        champPrenomModifEleveFx.setText(eleve.prenomEleve().get());
        champDateNaissModifEleveFx.setText(eleve.dateNaissanceEleve().get());
        champLieuNaissModifEleveFx.setText(eleve.lieuNaissanceEleve().get());
        champSexeModifEleveFx.setText(eleve.sexeEleve().get());
        champPaysModifEleveFx.setText(eleve.nationaliteEleve().get());
        champStatutModifEleveFx.setText(eleve.statutEleve().get());
    }

    private void refreshEleveTable() {
        var eleves = servicesEleve.listerEleves();
        elevesData.setAll(eleves.stream().map(EleveMapper::toFxEleve).toList());
    }

    @FXML
    public void onCreateStudent() {
        try {
            Eleve eleve = new Eleve(
                    champSaveMatriculeEleveFx.getText().trim(),
                    champSaveNomEleveFx.getText().trim(),
                    champSavePrenomEleveFx.getText().trim(),
                    champSaveDateNaissEleveFx.getText().trim(),
                    champSaveLieuNaissEleveFx.getText().trim(),
                    champSaveSexeEleveFx.getText().trim(),
                    "", // photo
                    champSavePaysEleveFx.getText().trim(),
                    ""
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
            selectedEleve.matriculeEleve().set(champMatriculeModifEleveFx.getText().trim());
            selectedEleve.nomEleve().set(champNomModifEleveFx.getText().trim());
            selectedEleve.prenomEleve().set(champPrenomModifEleveFx.getText().trim());
            selectedEleve.dateNaissanceEleve().set(champDateNaissModifEleveFx.getText().trim());
            selectedEleve.lieuNaissanceEleve().set(champLieuNaissModifEleveFx.getText().trim());
            selectedEleve.sexeEleve().set(champSexeModifEleveFx.getText().trim());
            selectedEleve.nationaliteEleve().set(champPaysModifEleveFx.getText().trim());

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
        champMatriculeModifEleveFx.clear();
        champNomModifEleveFx.clear();
        champPrenomModifEleveFx.clear();
        champDateNaissModifEleveFx.clear();
        champLieuNaissModifEleveFx.clear();
        champSexeModifEleveFx.clear();
        champPaysModifEleveFx.clear();
        tableModifEleveFx.getSelectionModel().clearSelection();
    }

    private void afficherDetailEleve(Eleve eleve) {
        if (eleve != null) {
            consulterNomEleveFx.setText(eleve.getNom());
            consulterPrenomEleveFx.setText(eleve.getPrenom());
            consulterDateNaissEleveFx.setText(eleve.getDateNaissance());
            consulterLieuNaissEleveFx.setText(eleve.getLieuNaissance());
            consulterSexeEleveFx.setText(eleve.getSexe());
            consulterClasseEleveFx.setText(eleve.getClasse());
            consulterParentEleveFx.setText("N/A"); // Placeholder for parent info
            consulterPaysEleveFx.setText(eleve.getNationalite());
            consulterDateInscripEleveFx.setText("N/A"); // Placeholder for enrollment date
        } else {
            consulterNomEleveFx.setText("");
            consulterPrenomEleveFx.setText("");
            consulterDateNaissEleveFx.setText("");
            consulterLieuNaissEleveFx.setText("");
            consulterSexeEleveFx.setText("");
            consulterClasseEleveFx.setText("");
            consulterParentEleveFx.setText("");
            consulterPaysEleveFx.setText("");
            consulterDateInscripEleveFx.setText("");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
