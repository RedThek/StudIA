package cm.enspm.studia.core;

import java.io.IOException;
import java.util.function.Function;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewManager {
    
    private final Stage primaryStage;
    // The factory that knows how to instantiate controllers with their dependencies
    private final Function<Class<?>, Object> controllerFactory;

    public ViewManager(Stage primaryStage, Function<Class<?>, Object> controllerFactory) {
        this.primaryStage = primaryStage;
        this.controllerFactory = controllerFactory;
    }

    /**
     * Swaps the entire root scene. Used for major transitions (Login -> Dashboard).
     */
    public void switchScene(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            // Crucial: Instructs the loader to use our DI container for controllers
            loader.setControllerFactory(controllerFactory::apply);
            
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Echec du chargement de la vue: " + fxmlPath);
            e.printStackTrace();
        }
    }
}
