package cm.enspm.studia.ui.controller;

import cm.enspm.studia.model.dto.comptes.Utilisateur;
import cm.enspm.studia.session.ContexteSession;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AccueilController {
    
    private final ContexteSession contexteSession;

    @FXML private Label welcomeLabel;
    @FXML private Button addStudentButton;
    @FXML private Button deleteStudentButton;
    @FXML private VBox adminMenuPanel; // A panel containing user management features

    // Constructor injection guarantees the controller always has a session
    public AccueilController(ContexteSession contexteSession) {
        this.contexteSession = contexteSession;
    }

    @FXML
    public void initialisation() {
        // Fetch the user (throws exception if someone bypassed the login screen)
        Utilisateur utilisateurActuel = contexteSession.getUtilisateurCourant()
            .orElseThrow(() -> new IllegalStateException("Aucun utilisateur connecté!"));

        welcomeLabel.setText("Bienvenue, " + utilisateurActuel.nomUtilisateur() + " (" + utilisateurActuel.role() + ")");

        // Enforce UI restrictions based on the Role
        configureViewPermissions(utilisateurActuel);
    }

    private void configureViewPermissions(Utilisateur utilisateur) {
        // BASIC users can register students (addStudentButton remains visible)
        // Only ADMIN users see the delete button and the user management panel
        
        boolean estAdmin = utilisateur.role().gestionUtilisateurs();
        
        deleteStudentButton.setVisible(estAdmin);
        deleteStudentButton.setManaged(estAdmin); // Removes it from layout calculations
        
        adminMenuPanel.setVisible(estAdmin);
        adminMenuPanel.setManaged(estAdmin);
    }

    @FXML
    private void handleDeconnexion() {
        contexteSession.deconnexionUtilisateur();
        // Call ViewFactory to navigate back to the Login screen
    }
}
