package cm.enspm.studia.ui.controller;

import cm.enspm.studia.model.personnes.Parent;
import cm.enspm.studia.core.ViewManager;
import cm.enspm.studia.model.fx.FxParent;
import cm.enspm.studia.service.ServiceParent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller for managing parent data in the JavaFX application.
 * Handles CRUD operations for parents, binding UI components to data models.
 */
public class ParentController {

    // Service layer for parent business logic and database operations
    private final ServiceParent serviceParent = new ServiceParent();
    // Observable list holding the parent data displayed in the table
    private final ObservableList<FxParent> parentsData = FXCollections.observableArrayList();
    // Currently selected parent in the table for editing/deleting
    private FxParent selectedParent;

    // FXML-injected text fields for parent form input
    @FXML private TextField cniField; // National ID card number input
    @FXML private TextField nomField; // Last name input
    @FXML private TextField prenomField; // First name input
    @FXML private TextField dateNaissanceField; // Birthdate input
    @FXML private TextField telephoneField; // Phone number input
    @FXML private TextField emailField; // Email address input
    @FXML private TextField professionField; // Profession input
    @FXML private TextField nationaliteField; // Nationality input
    @FXML private TextField adresseField; // Address input
    @FXML private TextField lienParentalField; // Parental relationship input

    // FXML-injected table and columns for displaying parent data
    @FXML private TableView<FxParent> parentsTable; // Main table for parents
    @FXML private TableColumn<FxParent, Number> idColumn; // ID column
    @FXML private TableColumn<FxParent, String> cniColumn; // CNI column
    @FXML private TableColumn<FxParent, String> nomColumn; // Last name column
    @FXML private TableColumn<FxParent, String> prenomColumn; // First name column
    @FXML private TableColumn<FxParent, String> telephoneColumn; // Phone column
    @FXML private TableColumn<FxParent, String> emailColumn; // Email column
    @FXML private TableColumn<FxParent, String> professionColumn; // Profession column
    @FXML private TableColumn<FxParent, String> nationaliteColumn; // Nationality column

    /**
     * Initializes the controller after FXML loading.
     * Sets up table column value factories and selection listener.
     */
    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(data -> data.getValue().identifiantParent()/*.asObject()*/);
        cniColumn.setCellValueFactory(data -> data.getValue().numeroCNIParent());
        nomColumn.setCellValueFactory(data -> data.getValue().nomParent());
        prenomColumn.setCellValueFactory(data -> data.getValue().prenomParent());
        telephoneColumn.setCellValueFactory(data -> data.getValue().telephoneParent());
        emailColumn.setCellValueFactory(data -> data.getValue().emailParent());
        professionColumn.setCellValueFactory(data -> data.getValue().professionParent());
        nationaliteColumn.setCellValueFactory(data -> data.getValue().nationaliteParent());

        parentsTable.setItems(parentsData);
        parentsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedParent = newSelection;
            if (newSelection != null) {
                bindSelectedParent(newSelection);
            }
        });

        refreshParentTable();
    }

    /**
     * Binds the selected parent's data to the form fields.
     * @param parent the selected FxParent to bind
     */
    private void bindSelectedParent(FxParent parent) {
        cniField.setText(parent.numeroCNIParent().get());
        nomField.setText(parent.nomParent().get());
        prenomField.setText(parent.prenomParent().get());
        dateNaissanceField.setText(parent.dateNaissanceParent().get());
        telephoneField.setText(parent.telephoneParent().get());
        emailField.setText(parent.emailParent().get());
        professionField.setText(parent.professionParent().get());
        nationaliteField.setText(parent.nationaliteParent().get());
        adresseField.setText(parent.adresseParent().get());
        lienParentalField.setText(parent.lienParentalParent().get());
    }

    /**
     * Refreshes the parent table with latest data from the service.
     */
    private void refreshParentTable() {
        parentsData.setAll(serviceParent.listerParents());
    }

    /**
     * Handles the create parent button click.
     * Validates input, creates a new parent, and refreshes the table.
     */
    @FXML
    public void onCreateParent() {
        try {
            Parent parent = new Parent(
                    cniField.getText().trim(),
                    nomField.getText().trim(),
                    prenomField.getText().trim(),
                    telephoneField.getText().trim(),
                    emailField.getText().trim(),
                    "H",
                    professionField.getText().trim(),
                    adresseField.getText().trim(),
                    nationaliteField.getText().trim(),
                    lienParentalField.getText().trim(),
                    null
            );
            serviceParent.enregistrerParent(parent);
            refreshParentTable();
            clearForm();
            showAlert(Alert.AlertType.INFORMATION, "Parent ajouté", "Le parent a bien été enregistré.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur d'enregistrement", e.getMessage());
        }
    }

    /**
     * Handles the update parent button click.
     * Updates the selected parent with form data and refreshes the table.
     */
    @FXML
    public void onUpdateParent() {
        if (selectedParent == null) {
            showAlert(Alert.AlertType.WARNING, "Aucun parent sélectionné", "Veuillez sélectionner un parent dans la table.");
            return;
        }
        try {
            selectedParent.numeroCNIParent().set(cniField.getText().trim());
            selectedParent.nomParent().set(nomField.getText().trim());
            selectedParent.prenomParent().set(prenomField.getText().trim());
            selectedParent.dateNaissanceParent().set(dateNaissanceField.getText().trim());
            selectedParent.telephoneParent().set(telephoneField.getText().trim());
            selectedParent.emailParent().set(emailField.getText().trim());
            selectedParent.professionParent().set(professionField.getText().trim());
            selectedParent.nationaliteParent().set(nationaliteField.getText().trim());
            selectedParent.adresseParent().set(adresseField.getText().trim());
            selectedParent.lienParentalParent().set(lienParentalField.getText().trim());

            serviceParent.modifierParent(selectedParent.toDomain());
            refreshParentTable();
            showAlert(Alert.AlertType.INFORMATION, "Parent modifié", "Le parent a été mis à jour.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur de mise à jour", e.getMessage());
        }
    }

    /**
     * Handles the delete parent button click.
     * Shows confirmation dialog and deletes the selected parent.
     */
    @FXML
    public void onDeleteParent() {
        if (selectedParent == null) {
            showAlert(Alert.AlertType.WARNING, "Aucun parent sélectionné", "Veuillez sélectionner un parent à supprimer.");
            return;
        }
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,
                "Voulez-vous vraiment supprimer ce parent ?", ButtonType.YES, ButtonType.NO);
        confirmation.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.YES) {
                try {
                    serviceParent.supprimerParent(selectedParent.identifiantParent().get());
                    refreshParentTable();
                    clearForm();
                    showAlert(Alert.AlertType.INFORMATION, "Parent supprimé", "Le parent a été supprimé.");
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Erreur de suppression", e.getMessage());
                }
            }
        });
    }

    /**
     * Handles the clear form button click.
     * Clears all form fields and selection.
     */
    @FXML
    public void onClearForm() {
        clearForm();
    }

    /**
     * Clears all form fields and resets the selected parent.
     */
    private void clearForm() {
        selectedParent = null;
        cniField.clear();
        nomField.clear();
        prenomField.clear();
        dateNaissanceField.clear();
        telephoneField.clear();
        emailField.clear();
        professionField.clear();
        nationaliteField.clear();
        adresseField.clear();
        lienParentalField.clear();
        parentsTable.getSelectionModel().clearSelection();
    }

    /**
     * Shows an alert dialog with the specified type, title, and message.
     * @param type the alert type (e.g., INFORMATION, ERROR)
     * @param title the alert title
     * @param message the alert message
     */
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
