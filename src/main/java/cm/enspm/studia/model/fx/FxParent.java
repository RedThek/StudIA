package cm.enspm.studia.model.fx;

import cm.enspm.studia.model.dto.personnes.ParentDTO;
import cm.enspm.studia.model.personnes.Parent;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FxParent {

    private final IntegerProperty identifiant = new SimpleIntegerProperty();
    private final StringProperty numeroCNI = new SimpleStringProperty();
    private final StringProperty nom = new SimpleStringProperty();
    private final StringProperty prenom = new SimpleStringProperty();
    private final StringProperty dateNaissance = new SimpleStringProperty();
    private final StringProperty telephone = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty profession = new SimpleStringProperty();
    private final StringProperty nationalite = new SimpleStringProperty();
    private final StringProperty adresse = new SimpleStringProperty();
    private final StringProperty lienParental = new SimpleStringProperty();

    public FxParent(ParentDTO dto) {
        this.identifiant.set(dto.identifiant());
        this.numeroCNI.set(dto.numeroCNI());
        this.nom.set(dto.nom());
        this.prenom.set(dto.prenom());
        this.dateNaissance.set(dto.dateNaissance());
        this.telephone.set(dto.telephone());
        this.email.set(dto.email());
        this.profession.set(dto.profession());
        this.nationalite.set(dto.nationalite());
        this.adresse.set(dto.adresse());
        //this.lienParental.set(dto.lienParental());
    }

    public FxParent(Parent parent) {
        this.identifiant.set(0);
        this.numeroCNI.set(parent.getNumeroCNI());
        this.nom.set(parent.getNom());
        this.prenom.set(parent.getPrenom());
        this.dateNaissance.set(parent.getDateNaissance());
        this.telephone.set(parent.getTelephone());
        this.email.set(parent.getEmail());
        this.profession.set(parent.getProfession());
        this.nationalite.set(parent.getNationalite());
        this.adresse.set(parent.getAdresse());
        this.lienParental.set(parent.getLienParental());
    }

    public ParentDTO toDto() {
        return new ParentDTO(
            identifiant.get(),
            numeroCNI.get(),
            nom.get(),
            prenom.get(),
            dateNaissance.get(),
            telephone.get(),
            email.get(),
            profession.get(),
            nationalite.get(),
            adresse.get()
            //lienParental.get()
        );
    }

    public Parent toDomain() {
        return new Parent(
            numeroCNI.get(),
            nom.get(),
            prenom.get(),
            telephone.get(),
            email.get(),
            null,
            profession.get(),
            adresse.get(),
            nationalite.get(),
            lienParental.get(),
            null
        );
    }

    public IntegerProperty identifiantParent() { return identifiant; }
    public StringProperty numeroCNIParent() { return numeroCNI; }
    public StringProperty nomParent() { return nom; }
    public StringProperty prenomParent() { return prenom; }
    public StringProperty dateNaissanceParent() { return dateNaissance; }
    public StringProperty telephoneParent() { return telephone; }
    public StringProperty emailParent() { return email; }
    public StringProperty professionParent() { return profession; }
    public StringProperty nationaliteParent() { return nationalite; }
    public StringProperty adresseParent() { return adresse; }
    public StringProperty lienParentalParent() { return lienParental; }
}
