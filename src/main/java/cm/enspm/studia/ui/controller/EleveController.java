package cm.enspm.studia.ui.controller;

import cm.enspm.studia.core.ViewManager;
import cm.enspm.studia.model.fx.FxEleve;
import cm.enspm.studia.service.ServicesEleve;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class EleveController {
    
    private final ServicesEleve servicesEleve;
    private final ViewManager viewManager;
    private FxEleve eleveActuel; // Reactive model bound to UI

    @FXML private TextField nameField;

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

}
