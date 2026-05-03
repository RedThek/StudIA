package cm.enspm.studia.model.fx;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import cm.enspm.studia.model.dto.personnes.Eleve;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

public class FxEleve {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final IntegerProperty identifiant = new SimpleIntegerProperty();
    private final StringProperty matricule = new SimpleStringProperty();
    private final StringProperty nom = new SimpleStringProperty();
    private final StringProperty prenom = new SimpleStringProperty();
    private final LocalDate dateNaissance;
    private final StringProperty lieuNaissance = new SimpleStringProperty();
    private final StringProperty sexe = new SimpleStringProperty();
    private final StringProperty photo = new SimpleStringProperty();
    private final StringProperty nationalite = new SimpleStringProperty();

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

    public FxEleve(cm.enspm.studia.model.personnes.Eleve eleveDomain) {
        this.identifiant.set(0);
        this.matricule.set(eleveDomain.getMatricule());
        this.nom.set(eleveDomain.getNom());
        this.prenom.set(eleveDomain.getPrenom());
        this.dateNaissance = LocalDate.parse(eleveDomain.getDateNaissance(), DATE_FORMATTER);
        this.lieuNaissance.set(eleveDomain.getLieuNaissance());
        this.sexe.set(eleveDomain.getSexe());
        this.photo.set(eleveDomain.getPhoto());
        this.nationalite.set(eleveDomain.getNationalite());
    }

    public Eleve toDto() {
        return new Eleve(
            identifiant.get(), matricule.get(), nom.get(), prenom.get(), 
            dateNaissance, lieuNaissance.get(), sexe.get(), 
            photo.get(), nationalite.get()); 
    }

    public cm.enspm.studia.model.personnes.Eleve toDomain() {
        return new cm.enspm.studia.model.personnes.Eleve(
            matricule.get(),
            nom.get(),
            prenom.get(),
            dateNaissance.format(DATE_FORMATTER),
            lieuNaissance.get(),
            sexe.get(),
            photo.get(),
            nationalite.get(),
            null
        );
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
