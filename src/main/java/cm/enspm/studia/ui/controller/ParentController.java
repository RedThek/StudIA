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

public class ParentController {

    private final ServiceParent serviceParent = new ServiceParent();
    //private final ViewManager viewManager;
    private final ObservableList<FxParent> parentsData = FXCollections.observableArrayList();
    private FxParent selectedParent;

    @FXML private TextField cniField;
    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField dateNaissanceField;
    @FXML private TextField telephoneField;
    @FXML private TextField emailField;
    @FXML private TextField professionField;
    @FXML private TextField nationaliteField;
    @FXML private TextField adresseField;
    @FXML private TextField lienParentalField;

    @FXML private TableView<FxParent> parentsTable;
    @FXML private TableColumn<FxParent, Number> idColumn;
    @FXML private TableColumn<FxParent, String> cniColumn;
    @FXML private TableColumn<FxParent, String> nomColumn;
    @FXML private TableColumn<FxParent, String> prenomColumn;
    @FXML private TableColumn<FxParent, String> telephoneColumn;
    @FXML private TableColumn<FxParent, String> emailColumn;
    @FXML private TableColumn<FxParent, String> professionColumn;
    @FXML private TableColumn<FxParent, String> nationaliteColumn;

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

    private void refreshParentTable() {
        parentsData.setAll(serviceParent.listerParents());
    }

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

    @FXML
    public void onClearForm() {
        clearForm();
    }

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

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
