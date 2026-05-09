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
    // Observable list holding the student data filtered for modification
    private final ObservableList<FxEleve> donneesEleve = FXCollections.observableArrayList();
    // Currently selected student in the table for editing/deleting
    private FxEleve selectedEleve;

    //Boutons du menu gauche
    @FXML private Button btnMenuAccueilFx; // Bouton pour aller à l'écran d'accueil
    @FXML private Button btnMenuClassesFx; // Bouton pour aller à l'écran des classes
    @FXML private Button btnMenuCompteFx; // Bouton pour aller à l'écran du compte utilisateur
    @FXML private Button btnMenuElevesFx; // Bouton pour aller à l'écran des élèves
    @FXML private Button btnMenuExamensFx; // Bouton pour aller à l'écran des examens
    @FXML private Button btnMenuMatieresFx; // Bouton pour aller à l'écran des matières
    @FXML private Button btnMenuParentsFx; // Bouton pour aller à l'écran des parents
    @FXML private Button btnMenuPersonnelFx; // Bouton pour aller à l'écran du personnel
    @FXML private Button btnMenuPlannification; // Bouton pour aller à l'écran de planification

    //Onglet General
    @FXML private Button btnRafraichirAllEleves; // Bouton pour rafraîchir la liste de tous les élèves

    @FXML private TableView<FxEleve> tableAllElevesFx; // Table principale affichant les élèves

    @FXML private TableColumn<FxEleve, String> colonneMatriculesFx; // Colonne matricule
    @FXML private TableColumn<FxEleve, String> colonneNomsFx; // Colonne nom
    @FXML private TableColumn<FxEleve, String> colonnePrenomsFx; // Colonne prénom
    @FXML private TableColumn<FxEleve, String> colonneSexesFx; // Colonne sexe
    @FXML private TableColumn<FxEleve, String> colonneStatutsFx; // Colonne statut
    @FXML private TableColumn<FxEleve, String> colonneDatesNaissFx; // Colonne date de naissance
    @FXML private TableColumn<FxEleve, String> colonneLieuxNaissFx; // Colonne lieu de naissance

    //Onglet Consulter
    @FXML private TextField champRechercheEleveFx; // Champ de recherche de matricule pour consultation

    @FXML private Button btnRechercheEleveFx; // Bouton pour lancer la recherche
    @FXML private Button btnExporterInfosEleveFx; // Bouton pour exporter les informations de l'élève
    
    @FXML private Label consulterNomEleveFx; // Affiche le nom de l'élève recherché
    @FXML private Label consulterPrenomEleveFx; // Affiche le prénom de l'élève recherché
    @FXML private Label consulterDateNaissEleveFx; // Affiche la date de naissance
    @FXML private Label consulterLieuNaissEleveFx; // Affiche le lieu de naissance
    @FXML private Label consulterSexeEleveFx; // Affiche le sexe
    @FXML private Label consulterClasseEleveFx; // Affiche la classe associée
    @FXML private Label consulterParentEleveFx; // Affiche le parent associé
    @FXML private Label consulterPaysEleveFx; // Affiche la nationalité
    @FXML private Label consulterDateInscripEleveFx; // Affiche la date d'inscription

    //Onglet Enregistrer
    @FXML private TextField champSaveMatriculeEleveFx; // Champ de saisie du matricule
    @FXML private TextField champSaveNomEleveFx; // Champ de saisie du nom
    @FXML private TextField champSavePrenomEleveFx; // Champ de saisie du prénom
    @FXML private TextField champSaveDateNaissEleveFx; // Champ de saisie de la date de naissance
    @FXML private TextField champSaveLieuNaissEleveFx; // Champ de saisie du lieu de naissance
    @FXML private TextField champSaveSexeEleveFx; // Champ de saisie du sexe
    @FXML private TextField champSavePaysEleveFx; // Champ de saisie de la nationalité

    @FXML private Button btnEnregistrerEleve; // Bouton pour enregistrer un nouvel élève

    //Onglet Modifier & Supprimer
    @FXML private TextField champRechercheModifEleveFx; // Champ de recherche de l'élève à modifier ou supprimer

    @FXML private Button btnRechercheModifEleveFx; // Bouton de recherche pour modification
    @FXML private Button btnModifEleveFx; // Bouton de validation de modification
    @FXML private Button btnSupprimEleveFx; // Bouton de suppression

    @FXML private TextField champDateNaissModifEleveFx; // Champ date de naissance pour modification
    @FXML private TextField champLieuNaissModifEleveFx; // Champ lieu de naissance pour modification
    @FXML private TextField champMatriculeModifEleveFx; // Champ matricule pour modification
    @FXML private TextField champNomModifEleveFx; // Champ nom pour modification
    @FXML private TextField champPaysModifEleveFx; // Champ nationalité pour modification
    @FXML private TextField champPrenomModifEleveFx; // Champ prénom pour modification
    @FXML private TextField champSexeModifEleveFx; // Champ sexe pour modification
    @FXML private TextField champStatutModifEleveFx; // Champ statut pour modification

    @FXML private TableView<FxEleve> tableModifEleveFx;  // Table affichant l'élève sélectionné pour modification

    @FXML private TableColumn<FxEleve, String> colonneModifMatriculeFx; // Colonne matricule modification
    @FXML private TableColumn<FxEleve, String> colonneModifNomFx; // Colonne nom modification
    @FXML private TableColumn<FxEleve, String> colonneModifPrenomFx; // Colonne prénom modification
    @FXML private TableColumn<FxEleve, String> colonneModifDateNaissFx; // Colonne date de naissance modification
    @FXML private TableColumn<FxEleve, String> colonneModifLieuNaissFx; // Colonne lieu de naissance modification
    @FXML private TableColumn<FxEleve, String> colonneModifSexeFx; // Colonne sexe modification
    @FXML private TableColumn<FxEleve, String> colonneModifPaysFx; // Colonne nationalité modification
    @FXML private TableColumn<FxEleve, String> colonneModifStatutFx; // Colonne statut modification

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
    /**
     * Gère le clic sur le bouton Accueil.
     */
    @FXML
    void handleClickMenuAccueil(MouseEvent event) {

    }

    /**
     * Gère le clic sur le bouton Élèves.
     */
    @FXML
    void handleClickMenuEleves(MouseEvent event) {

    }

    /**
     * Gère le clic sur le bouton Personnel.
     */
    @FXML
    void handleClickMenuPersonnel(MouseEvent event) {

    }

    /**
     * Gère le clic sur le bouton Parents.
     */
    @FXML
    void handleClickMenuParents(MouseEvent event) {

    }

    /**
     * Gère le clic sur le bouton Classes.
     */
    @FXML
    void handleClickMenuClasses(MouseEvent event) {

    }

    /**
     * Gère le clic sur le bouton Matières.
     */
    @FXML
    void handleClickMenuMatieres(MouseEvent event) {

    }

    /**
     * Gère le clic sur le bouton Examens.
     */
    @FXML
    void handleClickMenuExamens(MouseEvent event) {

    }

    /**
     * Gère le clic sur le bouton Planification.
     */
    @FXML
    void handleClickMenuPlannification(MouseEvent event) {

    }

    /**
     * Gère le clic sur le bouton Compte.
     */
    @FXML
    void handleClickMenuCompte(MouseEvent event) {

    }

    //Onglet General
    /**
     * Rafraîchit la liste de tous les élèves dans la table générale.
     */
    @FXML
    void handleBtnRafraichirAllEleves(MouseEvent event) {
        refreshEleveTable();
    }

    //Onglet Consulter
    /**
     * Recherche un élève à partir du matricule saisi dans l'onglet consultation.
     */
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

    /**
     * Lance l'export des informations de l'élève sélectionné.
     */
    @FXML
    void handleClickExporterInfosEleve(MouseEvent event) {
            if (selectedEleve == null) {
                showAlert(Alert.AlertType.WARNING, "Aucun élève sélectionné", "Veuillez sélectionner un élève à exporter.");
                return;
            }else {
                // Placeholder for export functionality
                showAlert(Alert.AlertType.INFORMATION, "Fonctionnalité non implémentée", "L'export des informations de l'élève n'est pas encore implémenté.");
            }
    }
    
    //Onglet Enregistrer
    /**
     * Enregistre un nouvel élève avec les données du formulaire.
     */
    @FXML
    void handleClickBtnSaveEleveFx(MouseEvent event) {
        try {
            Eleve eleve = new Eleve(
                    champSaveMatriculeEleveFx.getText().trim(),
                    champSaveNomEleveFx.getText().trim(),
                    champSavePrenomEleveFx.getText().trim(),
                    champSaveDateNaissEleveFx.getText().trim(),
                    champSaveLieuNaissEleveFx.getText().trim(),
                    champSaveSexeEleveFx.getText().trim(),
                    " ", // photo
                    champSavePaysEleveFx.getText().trim(),
                    " "
            );
            servicesEleve.enregistrerEleve(eleve);
            showAlert(Alert.AlertType.INFORMATION, "Élève ajouté", "L'élève a bien été enregistré.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur d'enregistrement", e.getMessage());
        }
    }
    
    //Onglet Modifier  
    /**
     * Recherche un élève pour modification et affiche ses données dans le formulaire.
     */
    @FXML
    void handleClickRechercheModifEleveFx(MouseEvent event) {
        String matricule = champRechercheModifEleveFx.getText().trim();
        if (matricule.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Recherche invalide", "Veuillez entrer un matricule pour la recherche.");
            return;
        }
        Eleve eleve = servicesEleve.rechercherEleveParMatricule(matricule);
        if (eleve != null) {
            donneesEleve.setAll(EleveMapper.toFxEleve(eleve));
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Élève non trouvé", "Aucun élève trouvé avec ce matricule.");
            donneesEleve.clear();
        }
    }

    /**
     * Applique les modifications saisies au seul élève sélectionné.
     */
    @FXML
    void handleClickModifEleveFx(MouseEvent event) {

    }

    /**
     * Supprime l'élève sélectionné après confirmation.
     */
    @FXML
    void handleClickSupprimEleveFx(MouseEvent event) {

    }

    /**
     * Initialise le contrôleur EleveController en construisant le service avec le dépôt de données.
     */
    public EleveController() {
        try {
            var eleveRepository = DatabaseService.getInstance().getEleveRepository();
            this.servicesEleve = new ServicesEleve(eleveRepository, new SessionUtilisateur());
        } catch (Exception e) {
            throw new RuntimeException("Erreur d'initialisation du service élève", e);
        }
    }

    /**
     * Initialise les colonnes de la table et configure la sélection de l'élève.
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
    /**
     * Copie les données de l'élève sélectionné dans le formulaire de modification.
     * @param eleve élève sélectionné
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

    /**
     * Recharge la liste des élèves depuis le service et l'affiche dans la table.
     */
    private void refreshEleveTable() {
        var eleves = servicesEleve.listerEleves();
        elevesData.setAll(eleves.stream().map(EleveMapper::toFxEleve).toList());
    }

    /**
     * Crée un nouvel élève depuis le formulaire et le sauvegarde en base.
     */
    /**
     * Crée un nouvel élève depuis le formulaire et le sauvegarde en base.
     */
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

    /**
     * Met à jour l'élève sélectionné avec les données du formulaire de modification.
     */
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

    /**
     * Supprime l'élève actuellement sélectionné après confirmation utilisateur.
     */
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

    /**
     * Vide le formulaire de modification et désélectionne l'élève actif.
     */
    @FXML
    public void onClearForm() {
        clearForm();
    }

    /**
     * Réinitialise les champs de saisie et la sélection dans la table.
     */
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

    /**
     * Affiche les détails de l'élève recherché dans l'onglet consultation.
     * @param eleve élève à afficher ou null pour vider les champs
     */
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

    /**
     * Affiche une boîte de dialogue d'alerte.
     * @param type type d'alerte (INFORMATION, WARNING, ERROR, etc.)
     * @param title titre de la fenêtre d'alerte
     * @param message contenu du message
     */
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
