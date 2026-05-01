package cm.enspm.studia.model.fx;

import java.time.LocalDate;

import cm.enspm.studia.model.dto.Eleve;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

public class FxEleve {
    
    private final IntegerProperty identifiant = new SimpleIntegerProperty();
    private final StringProperty matricule = new SimpleStringProperty();
    private final StringProperty nom = new SimpleStringProperty();
    private final StringProperty prenom = new SimpleStringProperty();
    private final LocalDate dateNaissance;
    private final StringProperty lieuNaissance = new SimpleStringProperty();
    private final StringProperty sexe = new SimpleStringProperty();
    private final StringProperty photo = new SimpleStringProperty();
    private final StringProperty nationalite = new SimpleStringProperty();

    // Constructor to map from DB DTO to UI Model
    public FxEleve(Eleve eleveDto) {
        this.identifiant.set(eleveDto.identifiant());
        this.matricule.set(eleveDto.matricule());
        this.nom.set(eleveDto.nom());
        this.prenom.set(eleveDto.prenom());
        this.dateNaissance = eleveDto.dateNaissance();
        this.lieuNaissance.set(eleveDto.lieuNaissance());
        this.sexe.set(eleveDto.sexe());
        this.photo.set(eleveDto.photo());
        this.nationalite.set(eleveDto.nationalite());
    }

    // Converters to send data back to the Service/DB
    public Eleve toDto() {
        // In a real app, parse the date properly
        return new Eleve(
            identifiant.get(), matricule.get(), nom.get(), prenom.get(), 
            dateNaissance, lieuNaissance.get(), sexe.get(), 
            photo.get(), nationalite.get()); 
    }

    // Getters for Property Binding in TableViews
    public IntegerProperty identifiantEleve() { return identifiant; }
    public StringProperty matriculeEleve() { return matricule; }
    public StringProperty nomEleve() { return nom; }
    public StringProperty prenomEleve() { return prenom; }
    public LocalDate dateNaissanceEleve() { return dateNaissance; }
    public StringProperty lieuNaissanceEleve() { return lieuNaissance; }
    public StringProperty sexeEleve() { return sexe; }
    public StringProperty photoEleve() { return photo; }
    public StringProperty nationaliteEleve() { return nationalite; }
}
