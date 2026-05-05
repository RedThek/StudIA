package cm.enspm.studia.model.fx;

import cm.enspm.studia.model.dto.personnes.ParentDTO;
import cm.enspm.studia.model.personnes.Parent;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FxParent {

    /**
     * L'attribut identifiant statique de FxParent.
     */
    private final IntegerProperty identifiant = new SimpleIntegerProperty();

    /**
     * Le numero de CNI statique de FxParent
     */
    private final StringProperty numeroCNI = new SimpleStringProperty();

    /**
     * Le nom statique de FxParent
     */
    private final StringProperty nom = new SimpleStringProperty();
    private final StringProperty prenom = new SimpleStringProperty();
    private final StringProperty dateNaissance = new SimpleStringProperty();

    /**
     * Le numero de telephone statique de FxParent
     */
    private final StringProperty telephone = new SimpleStringProperty();

    /**
     * L'adresse mail statique de FxParent
     */
    private final StringProperty email = new SimpleStringProperty();

    /**
     * La profession statique de FxParent
     */
    private final StringProperty profession = new SimpleStringProperty();

    /**
     * La nationalite statique de FxParent
     */
    private final StringProperty nationalite = new SimpleStringProperty();

    /**
     * L'adresse de residence statique de FxParent
     */
    private final StringProperty adresse = new SimpleStringProperty();

    /**
     * Le lien de parenté statique de FxParent
     */
    private final StringProperty lienParental = new SimpleStringProperty();

    /**
     * Le 1er constructeur de FxParent
     * @param ParentDTO dto
     * @return FxParent
     */
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
        this.lienParental.set("");
    }

    /**
     * Le 2nd constructeur de FxParent
     * @param Parent parent
     * @return FxParent
     */
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

    /**
     * Creer et remplit un objet ParentDTO avec les attributs de FxParent
     * @param void
     * @return ParentDTO
     */
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
        );
    }

    /**
     * Creer et remplit un objet Parent avec les attributs de FxParent
     * @param void
     * @return Parent
     */
    public Parent toDomain() {
        return new Parent(
            numeroCNI.get(),
            nom.get(),
            prenom.get(),
            telephone.get(),
            email.get(),
            "M",
            profession.get(),
            adresse.get(),
            nationalite.get(),
            lienParental.get(),
            null
        );
    }

    /**
     * Getter identifiantParent de FxParent
     * @param void
     * @return IntegerProperty identifiant
     */
    public IntegerProperty identifiantParent() { return identifiant; }
    public StringProperty numeroCNIParent() { return numeroCNI; }
    public StringProperty nomParent() { return nom; }
    public StringProperty prenomParent() { return prenom; }
    public StringProperty dateNaissanceParent() { return dateNaissance; }
    public StringProperty telephoneParent() { return telephone; }
    public StringProperty emailParent() { return email; }
    public StringProperty professionParent() { return profession; }

    /**
     * Getter nationaliteParent de FxParent
     * @param void
     * @return StringProperty nationalite
     */
    public StringProperty nationaliteParent() { return nationalite; }
    public StringProperty adresseParent() { return adresse; }
    public StringProperty lienParentalParent() { return lienParental; }
}
