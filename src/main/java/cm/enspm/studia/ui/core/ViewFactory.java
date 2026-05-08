package cm.enspm.studia.ui.core;

import java.io.IOException;

import cm.enspm.studia.session.ContexteSession;
import cm.enspm.studia.ui.controller.AccueilController;
import cm.enspm.studia.ui.controller.AccueilControllerOld;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewFactory {
    
    private final ContexteSession contexteSession;
    // You would also hold your Services (e.g., StudentService) here to inject them

    public ViewFactory(ContexteSession contexteSession) {
        this.contexteSession = contexteSession;
    }

    public void afficherAccueil(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Accueil.fxml"));
            
            // DEPENDENCY INJECTION: JavaFX will use this callback to create controllers.
            loader.setControllerFactory(controllerClass -> {
                if (controllerClass == AccueilController.class) {
                    return new AccueilControllerOld(contexteSession);
                }
                // Handle other controllers...
                try {
                    return controllerClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Accueil Gestion des Eleves");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
