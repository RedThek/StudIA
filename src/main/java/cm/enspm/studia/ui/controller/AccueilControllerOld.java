package cm.enspm.studia.ui.controller;

import cm.enspm.studia.model.fx.FxAccueil;
import cm.enspm.studia.session.ContexteSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AccueilControllerOld implements Initializable {

    @FXML
    private Label welcomeLabel;
    @FXML
    private Button addStudentButton;
    @FXML
    private Button deleteStudentButton;
    @FXML
    private VBox adminMenuPanel; // A panel containing user management features
    @FXML
    private TableView tvAccueil;
    @FXML
    private TableColumn<FxAccueil, String> tcFname;
    @FXML
    private TableColumn<FxAccueil, String> tcLname;
    @FXML
    private TableColumn<FxAccueil, String> tcPhone;
    @FXML
    private TableColumn<FxAccueil, String> tcEmail;

    private final ContexteSession contexteSession;
    private final ObservableList<FxAccueil> accueilData = FXCollections.observableArrayList();

    // Constructor injection guarantees the controller always has a session
    public AccueilControllerOld(ContexteSession contexteSession) {
        this.contexteSession = contexteSession;
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param url  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resourcesBundle The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourcesBundle) {

    }

    /**
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
    }**/
}
