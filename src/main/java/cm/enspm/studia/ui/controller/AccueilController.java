package cm.enspm.studia.ui.controller;

import cm.enspm.studia.model.fx.FxAccueil;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;

public class AccueilController implements Initializable {

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

    private ObservableList<FxAccueil> data;

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
        //tcFname.setCellValueFactory(data -> data.getValue().getFname());
        tcFname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        tcLname.setCellValueFactory(new PropertyValueFactory<>("Last name"));
        tcPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));

        data = FXCollections.observableArrayList(
                new FxAccueil("Fname1", "lname1", "phone1", "email1"),
                new FxAccueil("Fname2", "lname2", "phone2", "email2")
        );
        tvAccueil.setItems(data);
    }
}
